package com.anandniketan.anandniketanskool360shilajTeacher.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.anandniketan.anandniketanskool360shilajTeacher.Activities.LoginActivity;
import com.anandniketan.anandniketanskool360shilajTeacher.Adapter.StudentAbsentListAdapter;
import com.anandniketan.anandniketanskool360shilajTeacher.AsyncTasks.GetDateWiseAbsentStudentAsyncTask;
import com.anandniketan.anandniketanskool360shilajTeacher.AsyncTasks.GetStandardSectionAsyncTask;
import com.anandniketan.anandniketanskool360shilajTeacher.AsyncTasks.InsertConsistentAbSMSAsyncTask;
import com.anandniketan.anandniketanskool360shilajTeacher.AsyncTasks.SendAbsentStudentSMSAsyncTask;
import com.anandniketan.anandniketanskool360shilajTeacher.Interfacess.getStudentAbCheck;
import com.anandniketan.anandniketanskool360shilajTeacher.Models.AllAttendance.FinalArrayAbsentStudentModel;
import com.anandniketan.anandniketanskool360shilajTeacher.Models.AllAttendance.GetAbsentStudentSMSStatusModel;
import com.anandniketan.anandniketanskool360shilajTeacher.Models.AllAttendance.GetDateWiseAbsentStudentModel;
import com.anandniketan.anandniketanskool360shilajTeacher.Models.AllAttendance.GetStandardSectionModel;
import com.anandniketan.anandniketanskool360shilajTeacher.Models.AllAttendance.InsertConsistentAbSMSModel;
import com.anandniketan.anandniketanskool360shilajTeacher.Models.AllAttendance.SectionDetailModel;
import com.anandniketan.anandniketanskool360shilajTeacher.R;
import com.anandniketan.anandniketanskool360shilajTeacher.Utility.Utility;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;


public class StudentAbsentFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    public StudentAbsentFragment() {
        // Required empty public constructor
    }

    private Context mContext;
    private View rootView;
    private ProgressDialog progressDialog = null;
    private TextView txtNoRecordsstudentabsent;
    private Button btnBacktest_student_absent, btnLogout, start_date;
    private LinearLayout student_absent_header;
    private ListView student_absent_list;
    private ImageView insert_message_img;

    //use for fill listview
    private GetDateWiseAbsentStudentAsyncTask getDateWiseAbsentStudentAsyncTask = null;
    private GetDateWiseAbsentStudentModel getDateWiseAbsentStudentModelResponse;
    private StudentAbsentListAdapter studentAbsentListAdapter;

    //use for insert sms
    private AlertDialog alertDialogAndroid = null;
    private EditText insert_consistent_Message_txt;
    private Button send_consistent_message_btn, close_btn;
    private DatePickerDialog datePickerDialog;
    int Year, Month, Day;
    Calendar calendar;
    int mYear, mMonth, mDay;
    String FinalDateStr, FinalStandardStr = "", FinalSectionStr = "";

    //fillspinner
    private Spinner standard_attendace_spinner, standard_division_spinner;
    private GetStandardSectionAsyncTask getStandardSectionAsyncTask = null;
    private GetStandardSectionModel getStandardSectionModelResponse;
    HashMap<Integer, String> spinnerStandard;
    HashMap<Integer, String> spinnerSection;
    String selectedstandardStr, selectedstandardIdStr, selectedsectionstr, selectedsectionId;


    //use for send sms
    private SendAbsentStudentSMSAsyncTask sendAbsentStudentSMSAsyncTask = null;
    private GetAbsentStudentSMSStatusModel getAbsentStudentSMSStatusModel;
    String finalStudentIdArray, finalmessageMessageLine;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_student_absent, container, false);
        mContext = getActivity();
        init();
        setListner();
        setStudentAbData();
        getSpinnerData();
        return rootView;
    }

    public void init() {
        calendar = Calendar.getInstance();
        Year = calendar.get(Calendar.YEAR);
        Month = calendar.get(Calendar.MONTH);
        Day = calendar.get(Calendar.DAY_OF_MONTH);


        txtNoRecordsstudentabsent = (TextView) rootView.findViewById(R.id.txtNoRecordsstudentabsent);
        btnBacktest_student_absent = (Button) rootView.findViewById(R.id.btnBacktest_student_absent);
        btnLogout = (Button) rootView.findViewById(R.id.btnLogout);
        student_absent_header = (LinearLayout) rootView.findViewById(R.id.student_absent_header);
        student_absent_list = (ListView) rootView.findViewById(R.id.student_absent_list);
        insert_message_img = (ImageView) rootView.findViewById(R.id.insert_message_img);
        start_date = (Button) rootView.findViewById(R.id.start_date);
        standard_attendace_spinner = (Spinner) rootView.findViewById(R.id.standard_attendace_spinner);
        standard_division_spinner = (Spinner) rootView.findViewById(R.id.standard_division_spinner);
        start_date.setText(Utility.getTodaysDate());
    }

    public void setListner() {
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new android.app.AlertDialog.Builder(new android.view.ContextThemeWrapper(getActivity(), R.style.AppTheme))
                        .setCancelable(false)
                        .setTitle("Logout")
                        .setIcon(mContext.getResources().getDrawable(R.drawable.ic_launcher))
                        .setMessage("Are you sure you want to logout? ")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Utility.setPref(mContext, "StaffID", "");
                                Utility.setPref(mContext, "Emp_Code", "");
                                Utility.setPref(mContext, "Emp_Name", "");
                                Utility.setPref(mContext, "DepratmentID", "");
                                Utility.setPref(mContext, "DesignationID", "");
                                Utility.setPref(mContext, "DeviceId", "");
                                Utility.setPref(mContext, "unm", "");
                                Utility.setPref(mContext, "pwd", "");
                                Utility.setPref(mContext, "LoginType", "");

                                Intent i = new Intent(getActivity(), LoginActivity.class);
                                getActivity().startActivity(i);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(R.drawable.ic_launcher)
                        .show();
            }
        });
        btnBacktest_student_absent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new OtherLoginHomeFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .setCustomAnimations(0, 0)
                        .replace(R.id.frame_container, fragment).commit();
            }
        });
        start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog = DatePickerDialog.newInstance(StudentAbsentFragment.this, Year, Month, Day);
                datePickerDialog.setThemeDark(false);
                datePickerDialog.setOkText("Done");
                datePickerDialog.showYearPickerFirst(false);
                datePickerDialog.setAccentColor(Color.parseColor("#1B88C8"));
                datePickerDialog.setTitle("Select Date From DatePickerDialog");
                datePickerDialog.show(getActivity().getFragmentManager(), "DatePickerDialog");
            }
        });
        standard_attendace_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedstandardStr = standard_attendace_spinner.getSelectedItem().toString();
                String getid = spinnerStandard.get(standard_attendace_spinner.getSelectedItemPosition());

                Log.d("value", selectedstandardStr + " " + getid);
                selectedstandardIdStr = getid.toString();
                FinalStandardStr = selectedstandardStr;

                Log.d("requestfor", selectedstandardIdStr);

                fillsectionspinner();
                setStudentAbData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        standard_division_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selectedsectionstr = standard_division_spinner.getSelectedItem().toString();
                String getid = spinnerSection.get(standard_division_spinner.getSelectedItemPosition());

                Log.d("value", selectedsectionstr + " " + getid);
                selectedsectionId = getid.toString();
                Log.d("selectedsectionId", selectedsectionId);
                FinalSectionStr = selectedsectionstr;
                setStudentAbData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        insert_message_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendMessage();
            }
        });
    }

    public void getSpinnerData() {
        if (Utility.isNetworkConnected(mContext)) {
            progressDialog = new ProgressDialog(mContext);
            progressDialog.setMessage("Please Wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        HashMap<String, String> params = new HashMap<String, String>();

                        getStandardSectionAsyncTask = new GetStandardSectionAsyncTask(params);
                        getStandardSectionModelResponse = getStandardSectionAsyncTask.execute().get();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                                if (getStandardSectionModelResponse.getFinalArray().size() > 0) {
//                                    txtNoRecordsstudentabsent.setVisibility(View.GONE);
                                    fillstandanrdspinner();
                                } else {
                                    progressDialog.dismiss();
//                                    txtNoRecordsstudentabsent.setVisibility(View.VISIBLE);

                                }
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        } else {
            Utility.ping(mContext, "Network not available");
        }
    }

    public void fillstandanrdspinner() {
        ArrayList<String> firstValue = new ArrayList<>();
        firstValue.add("All");

        ArrayList<String> standardname = new ArrayList<>();
        for (int z = 0; z < firstValue.size(); z++) {
            standardname.add(firstValue.get(z));
            for (int i = 0; i < getStandardSectionModelResponse.getFinalArray().size(); i++) {
                standardname.add(getStandardSectionModelResponse.getFinalArray().get(i).getStandard());
            }
        }
        ArrayList<Integer> firstValueId = new ArrayList<>();
        firstValueId.add(0);
        ArrayList<Integer> standardId = new ArrayList<>();
        for (int m = 0; m < firstValueId.size(); m++) {
            standardId.add(firstValueId.get(m));
            for (int j = 0; j < getStandardSectionModelResponse.getFinalArray().size(); j++) {
                standardId.add(getStandardSectionModelResponse.getFinalArray().get(j).getStandardID());
            }
        }
        String[] spinnerstandardIdArray = new String[standardId.size()];

        spinnerStandard = new HashMap<Integer, String>();
        for (int i = 0; i < standardId.size(); i++) {
            spinnerStandard.put(i, String.valueOf(standardId.get(i)));
            spinnerstandardIdArray[i] = standardname.get(i).trim();
        }

        try {
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);

            // Get private mPopup member variable and try cast to ListPopupWindow
            android.widget.ListPopupWindow popupWindow = (android.widget.ListPopupWindow) popup.get(standard_attendace_spinner);

            popupWindow.setHeight(spinnerstandardIdArray.length > 5 ? 500 : spinnerstandardIdArray.length * 100);
//            popupWindow1.setHeght(200);
        } catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException e) {
            // silently fail...
        }


        ArrayAdapter<String> adapterstandard = new ArrayAdapter<String>(mContext, R.layout.spinner_layout, spinnerstandardIdArray);
        standard_attendace_spinner.setAdapter(adapterstandard);


    }

    public void fillsectionspinner() {
        ArrayList<String> sectionname = new ArrayList<>();
        ArrayList<Integer> sectionId = new ArrayList<>();
        ArrayList<String> firstSectionValue = new ArrayList<String>();
        firstSectionValue.add("All");
        ArrayList<Integer> firstSectionId = new ArrayList<>();
        firstSectionId.add(0);

        if (selectedstandardStr.equalsIgnoreCase("All")) {
            for (int j = 0; j < firstSectionValue.size(); j++) {
                sectionname.add(firstSectionValue.get(j));
            }
            for (int i = 0; i < firstSectionId.size(); i++) {
                sectionId.add(firstSectionId.get(i));
            }

        }
        for (int z = 0; z < getStandardSectionModelResponse.getFinalArray().size(); z++) {
            if (selectedstandardStr.equalsIgnoreCase(getStandardSectionModelResponse.getFinalArray().get(z).getStandard())) {
                for (int j = 0; j < firstSectionValue.size(); j++) {
                    sectionname.add(firstSectionValue.get(j));
                    for (int i = 0; i < getStandardSectionModelResponse.getFinalArray().get(z).getSectionDetail().size(); i++) {
                        sectionname.add(getStandardSectionModelResponse.getFinalArray().get(z).getSectionDetail().get(i).getSection());
                    }
                }
                for (int j = 0; j < firstSectionId.size(); j++) {
                    sectionId.add(firstSectionId.get(j));
                    for (int m = 0; m < getStandardSectionModelResponse.getFinalArray().get(z).getSectionDetail().size(); m++) {
                        sectionId.add(getStandardSectionModelResponse.getFinalArray().get(z).getSectionDetail().get(m).getSectionID());
                    }
                }
            }
        }

        String[] spinnersectionIdArray = new String[sectionId.size()];

        spinnerSection = new HashMap<Integer, String>();
        for (int i = 0; i < sectionId.size(); i++) {
            spinnerSection.put(i, String.valueOf(sectionId.get(i)));
            spinnersectionIdArray[i] = sectionname.get(i).trim();
        }
        try {
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);

            // Get private mPopup member variable and try cast to ListPopupWindow
            android.widget.ListPopupWindow popupWindow = (android.widget.ListPopupWindow) popup.get(standard_division_spinner);

            popupWindow.setHeight(spinnersectionIdArray.length > 5 ? 500 : spinnersectionIdArray.length * 100);
        } catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException e) {
            // silently fail...
        }
        ArrayAdapter<String> adapterstandard = new ArrayAdapter<String>(mContext, R.layout.spinner_layout, spinnersectionIdArray);
        standard_division_spinner.setAdapter(adapterstandard);

    }

    public void setStudentAbData() {
        FinalDateStr = start_date.getText().toString();
        if (FinalStandardStr.equalsIgnoreCase("All")) {
            FinalStandardStr = "";
        }
        if (FinalSectionStr.equalsIgnoreCase("All")) {
            FinalSectionStr = "";
        }
        if (Utility.isNetworkConnected(mContext)) {
//            progressDialog = new ProgressDialog(mContext);
//            progressDialog.setMessage("Please Wait...");
//            progressDialog.setCancelable(false);
//            progressDialog.show();


            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        HashMap<String, String> params = new HashMap<String, String>();
                        params.put("Date", FinalDateStr);
                        params.put("Standard", FinalStandardStr);//FinalStandardStr 325|8672952197
                        params.put("Section", FinalSectionStr);
                        getDateWiseAbsentStudentAsyncTask = new GetDateWiseAbsentStudentAsyncTask(params);
                        getDateWiseAbsentStudentModelResponse = getDateWiseAbsentStudentAsyncTask.execute().get();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                                if (getDateWiseAbsentStudentModelResponse.getFinalArray().size() > 0) {
                                    txtNoRecordsstudentabsent.setVisibility(View.GONE);
                                    student_absent_header.setVisibility(View.VISIBLE);
                                    student_absent_list.setVisibility(View.VISIBLE);
                                    for (int i = 0; i < getDateWiseAbsentStudentModelResponse.getFinalArray().size(); i++) {
                                        getDateWiseAbsentStudentModelResponse.getFinalArray().get(i).setCheckboxStatus("0");
                                    }
                                    studentAbsentListAdapter = new StudentAbsentListAdapter(getActivity(),
                                            getDateWiseAbsentStudentModelResponse, new getStudentAbCheck() {
                                        @Override
                                        public void getStudentAbCheck() {
                                            GetDateWiseAbsentStudentModel updatedData = studentAbsentListAdapter.getData();
                                            Boolean data = false;
                                            for (int i = 0; i <updatedData.getFinalArray().size(); i++) {
                                                if (updatedData.getFinalArray().get(i).getCheckboxStatus().equalsIgnoreCase("1")) {
                                                    data = true;
                                                    Log.d("Position , Checked or not", "" + i + " : " + updatedData.getFinalArray().get(i).getCheckboxStatus());
                                                }
                                            }
                                            if (data) {
                                                insert_message_img.setVisibility(View.VISIBLE);
                                            } else {
                                                insert_message_img.setVisibility(View.GONE);
                                            }
                                        }
                                    });
                                    student_absent_list.setAdapter(studentAbsentListAdapter);
                                    student_absent_list.deferNotifyDataSetChanged();
                                } else {
                                    progressDialog.dismiss();
                                    txtNoRecordsstudentabsent.setVisibility(View.VISIBLE);
                                    student_absent_header.setVisibility(View.GONE);
                                    student_absent_list.setVisibility(View.GONE);
                                }
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        } else {
            Utility.ping(mContext, "Network not available");
        }

    }

    public void SendMessage() {
        LayoutInflater lInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View layout = lInflater.inflate(R.layout.student_ab_message_popup, null);

        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(mContext);
        alertDialogBuilderUserInput.setView(layout);

        alertDialogAndroid = alertDialogBuilderUserInput.create();
        alertDialogAndroid.setCancelable(false);
        alertDialogAndroid.show();
        Window window = alertDialogAndroid.getWindow();
        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.CENTER;
        wlp.flags = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        window.setAttributes(wlp);
        alertDialogAndroid.show();

        insert_consistent_Message_txt = (EditText) layout.findViewById(R.id.insert_consistent_Message_txt);
        send_consistent_message_btn = (Button) layout.findViewById(R.id.send_consistent_message_btn);
        close_btn = (Button) layout.findViewById(R.id.close_btn);

        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogAndroid.dismiss();
            }
        });


        send_consistent_message_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> id = new ArrayList<>();

                ArrayList<String> array = studentAbsentListAdapter.getData1();
                for (int j = 0; j < array.size(); j++) {
                    id.add(array.get(j).toString());
                }
                Log.d("id", "" + id);
                finalStudentIdArray = String.valueOf(id);
                finalStudentIdArray = finalStudentIdArray.substring(1, finalStudentIdArray.length() - 1);

                finalmessageMessageLine = insert_consistent_Message_txt.getText().toString();
                Log.d("StudentArray", "" + id);


                if (Utility.isNetworkConnected(mContext)) {
                    if (!finalStudentIdArray.equalsIgnoreCase("") && !finalmessageMessageLine.equalsIgnoreCase("")) {
                        progressDialog = new ProgressDialog(mContext);
                        progressDialog.setMessage("Please Wait...");
                        progressDialog.setCancelable(false);
                        progressDialog.show();


                        Log.d("finalStudentIdArray", finalStudentIdArray);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    HashMap<String, String> params = new HashMap<String, String>();
                                    params.put("StaffID", Utility.getPref(mContext, "StaffID"));
                                    params.put("StudentIDs",finalStudentIdArray);//finalStudentIdArray "325|8672952197"
                                  params.put("SMSText", finalmessageMessageLine);

                                    sendAbsentStudentSMSAsyncTask = new SendAbsentStudentSMSAsyncTask(params);
                                    getAbsentStudentSMSStatusModel = sendAbsentStudentSMSAsyncTask.execute().get();
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            progressDialog.dismiss();
                                            if (getAbsentStudentSMSStatusModel.getSuccess().equalsIgnoreCase("True")) {
                                                Utility.ping(mContext, "Send Sucessfully");
                                                alertDialogAndroid.dismiss();
                                            } else {
                                                progressDialog.dismiss();

                                            }
                                        }
                                    });
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                    } else {
                        Utility.ping(mContext, "Blank field not allowed.");
                    }
                } else {
                    Utility.ping(mContext, "Network not available");
                }
            }
        });

    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        mDay = dayOfMonth;
        mMonth = monthOfYear + 1;
        mYear = year;
        String d, m, y;
        d = Integer.toString(mDay);
        m = Integer.toString(mMonth);
        y = Integer.toString(mYear);

        if (mDay < 10) {
            d = "0" + d;
        }
        if (mMonth < 10) {
            m = "0" + m;
        }

        start_date.setText(d + "/" + m + "/" + y);
    }
}
