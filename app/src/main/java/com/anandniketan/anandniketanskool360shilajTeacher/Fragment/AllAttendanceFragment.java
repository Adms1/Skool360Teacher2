package com.anandniketan.anandniketanskool360shilajTeacher.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.anandniketan.anandniketanskool360shilajTeacher.Activities.LoginActivity;
import com.anandniketan.anandniketanskool360shilajTeacher.Adapter.All_AttendaceListAdapter;
import com.anandniketan.anandniketanskool360shilajTeacher.Adapter.AttendanceListAdapter;
import com.anandniketan.anandniketanskool360shilajTeacher.AsyncTasks.GetAttendenceData_AllAsyncTask;
import com.anandniketan.anandniketanskool360shilajTeacher.AsyncTasks.GetInsertAttendanceAsyncTask;
import com.anandniketan.anandniketanskool360shilajTeacher.AsyncTasks.GetNewStaffAttendanceAsyncTask;
import com.anandniketan.anandniketanskool360shilajTeacher.AsyncTasks.GetStandardSectionAsyncTask;
import com.anandniketan.anandniketanskool360shilajTeacher.Models.AllAttendance.GetAttendenceData_AllModel;
import com.anandniketan.anandniketanskool360shilajTeacher.Models.AllAttendance.GetStandardSectionModel;
import com.anandniketan.anandniketanskool360shilajTeacher.Models.Attendance.StaffInsertAttendenceModel;
import com.anandniketan.anandniketanskool360shilajTeacher.R;
import com.anandniketan.anandniketanskool360shilajTeacher.Utility.Utility;
import com.google.android.gms.maps.model.UrlTileProvider;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.lang.reflect.Field;
import java.net.InterfaceAddress;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class AllAttendanceFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    public AllAttendanceFragment() {
        // Required empty public constructor
    }

    private Context mContext;
    private View rootView;

    private ProgressDialog progressDialog = null;
    private Spinner standard_attendace_spinner, standard_division_spinner;
    private Button start_date, btnBackAllAttendance,btnLogout;
    private ImageView insert_attendance_img;
    private TextView all_total_student_txt, all_present_txt, all_absent_txt, all_leave_txt, all_onduty_txt, txtNoRecords;
    private LinearLayout header_linear, student_list_linear;
    private ListView student_list;
    private DatePickerDialog datePickerDialog;
    int mYear, mMonth, mDay;
    int Year, Month, Day;
    Calendar calendar;
    //use for standard-section
    private GetStandardSectionAsyncTask getStandardSectionAsyncTask = null;
    private GetStandardSectionModel getStandardSectionModelResponse;
    HashMap<Integer, String> spinnerStandard;
    HashMap<Integer, String> spinnerSection;
    String selectedstandardStr, selectedstandardIdStr, selectedsectionstr, selectedsectionId;
    //use for studentdetail
    private GetAttendenceData_AllAsyncTask getAttendenceData_allAsyncTask = null;
    private GetAttendenceData_AllModel getAttendenceData_allModelResponse;
    All_AttendaceListAdapter all_attendaceListAdapter = null;
    //insert Attendance
    String Attendanceidstr, Attendacestatusstr, studentidstr, commentstr;
    private GetInsertAttendanceAsyncTask getInsertAttendanceAsyncTask = null;
    StaffInsertAttendenceModel staffInsertAttendenceModelResponse;
    String responseString = "";

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_all_attendance, container, false);
        mContext = getActivity();
        init();
        setListner();
        getSpinnerData();
        return rootView;
    }

    public void init() {
        calendar = Calendar.getInstance();
        Year = calendar.get(Calendar.YEAR);
        Month = calendar.get(Calendar.MONTH);
        Day = calendar.get(Calendar.DAY_OF_MONTH);


        standard_attendace_spinner = (Spinner) rootView.findViewById(R.id.standard_attendace_spinner);
        standard_division_spinner = (Spinner) rootView.findViewById(R.id.standard_division_spinner);
        start_date = (Button) rootView.findViewById(R.id.start_date);
        btnBackAllAttendance = (Button) rootView.findViewById(R.id.btnBackAllAttendance);
        btnLogout=(Button)rootView.findViewById(R.id.btnLogout);
        insert_attendance_img = (ImageView) rootView.findViewById(R.id.insert_attendance_img);
        all_total_student_txt = (TextView) rootView.findViewById(R.id.all_total_student_txt);
        all_present_txt = (TextView) rootView.findViewById(R.id.all_present_txt);
        all_absent_txt = (TextView) rootView.findViewById(R.id.all_absent_txt);
        all_leave_txt = (TextView) rootView.findViewById(R.id.all_leave_txt);
        all_onduty_txt = (TextView) rootView.findViewById(R.id.all_onduty_txt);
        txtNoRecords = (TextView) rootView.findViewById(R.id.txtNoRecords);
        header_linear = (LinearLayout) rootView.findViewById(R.id.header_linear);
        student_list_linear = (LinearLayout) rootView.findViewById(R.id.student_list_linear);
        student_list = (ListView) rootView.findViewById(R.id.student_list);

        start_date.setText(Utility.getTodaysDate());

        try {
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);

            // Get private mPopup member variable and try cast to ListPopupWindow
            android.widget.ListPopupWindow popupWindow = (android.widget.ListPopupWindow) popup.get(standard_attendace_spinner);
            android.widget.ListPopupWindow popupWindow1 = (android.widget.ListPopupWindow) popup.get(standard_division_spinner);


            popupWindow.setHeight(200);
            popupWindow1.setHeight(200);
        }
        catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException e) {
            // silently fail...
        }



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
        btnBackAllAttendance.setOnClickListener(new View.OnClickListener() {
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
                datePickerDialog = DatePickerDialog.newInstance(AllAttendanceFragment.this, Year, Month, Day);
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
                Log.d("requestfor", selectedstandardIdStr);

                fillsectionspinner();
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
                getStudentData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        insert_attendance_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertAttendance();
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
                                    txtNoRecords.setVisibility(View.GONE);
                                    fillstandanrdspinner();
                                } else {
                                    progressDialog.dismiss();
                                    txtNoRecords.setVisibility(View.VISIBLE);

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
        ArrayList<String> standardname = new ArrayList<>();
        for (int i = 0; i < getStandardSectionModelResponse.getFinalArray().size(); i++) {
            standardname.add(getStandardSectionModelResponse.getFinalArray().get(i).getStandard());
        }

        ArrayList<Integer> standardId = new ArrayList<>();
        for (int j = 0; j < getStandardSectionModelResponse.getFinalArray().size(); j++) {
            standardId.add(getStandardSectionModelResponse.getFinalArray().get(j).getStandardID());
        }

        String[] spinnerstandardIdArray = new String[standardId.size()];

        spinnerStandard = new HashMap<Integer, String>();
        for (int i = 0; i < standardId.size(); i++) {
            spinnerStandard.put(i, String.valueOf(standardId.get(i)));
            spinnerstandardIdArray[i] = standardname.get(i).trim();
        }

        ArrayAdapter<String> adapterstandard = new ArrayAdapter<String>(mContext, R.layout.spinner_layout, spinnerstandardIdArray);
        standard_attendace_spinner.setAdapter(adapterstandard);
    }

    public void fillsectionspinner() {
        ArrayList<String> sectionname = new ArrayList<>();
        ArrayList<Integer> sectionId = new ArrayList<>();
        for (int i = 0; i < getStandardSectionModelResponse.getFinalArray().size(); i++) {
            if (selectedstandardStr.equalsIgnoreCase(getStandardSectionModelResponse.getFinalArray().get(i).getStandard())) {
                for (int j = 0; j < getStandardSectionModelResponse.getFinalArray().get(i).getSectionDetail().size(); j++) {
                    sectionname.add(getStandardSectionModelResponse.getFinalArray().get(i).getSectionDetail().get(j).getSection());
                    sectionId.add(getStandardSectionModelResponse.getFinalArray().get(i).getSectionDetail().get(j).getSectionID());
                }
            }
        }

        String[] spinnersectionIdArray = new String[sectionId.size()];

        spinnerSection = new HashMap<Integer, String>();
        for (int i = 0; i < sectionId.size(); i++) {
            spinnerSection.put(i, String.valueOf(sectionId.get(i)));
            spinnersectionIdArray[i] = sectionname.get(i).trim();
        }
        ArrayAdapter<String> adapterstandard = new ArrayAdapter<String>(mContext, R.layout.spinner_layout, spinnersectionIdArray);
        standard_division_spinner.setAdapter(adapterstandard);

    }

    public void getStudentData() {
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
                        params.put("AttDate", start_date.getText().toString());
                        params.put("StdID", selectedstandardIdStr);//AppConfiguration.stdid
                        params.put("ClsID", selectedsectionId);//AppConfiguration.clsid

                        getAttendenceData_allAsyncTask = new GetAttendenceData_AllAsyncTask(params);
                        getAttendenceData_allModelResponse = getAttendenceData_allAsyncTask.execute().get();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                                if (getAttendenceData_allModelResponse.getFinalArray().size() > 0) {
                                    txtNoRecords.setVisibility(View.GONE);
                                    prepareList();
                                    all_attendaceListAdapter = new All_AttendaceListAdapter(getActivity(), getAttendenceData_allModelResponse);
                                    student_list.setAdapter(all_attendaceListAdapter);
                                    student_list.deferNotifyDataSetChanged();
                                    insert_attendance_img.setVisibility(View.VISIBLE);
                                } else {
                                    progressDialog.dismiss();
                                    txtNoRecords.setVisibility(View.VISIBLE);
                                    insert_attendance_img.setVisibility(View.GONE);
                                    header_linear.setVisibility(View.GONE);
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

    public void prepareList() {
        String total_studentstr, prsent_str, absent_str, leave_str, onduty_str;
        total_studentstr = String.valueOf(getAttendenceData_allModelResponse.getFinalArray().get(0).getTotal());
        prsent_str = String.valueOf(getAttendenceData_allModelResponse.getFinalArray().get(0).getTotalPresent());
        absent_str = String.valueOf(getAttendenceData_allModelResponse.getFinalArray().get(0).getTotalAbsent());
        leave_str = String.valueOf(getAttendenceData_allModelResponse.getFinalArray().get(0).getTotalLeave());
        onduty_str = String.valueOf(getAttendenceData_allModelResponse.getFinalArray().get(0).getTotalOnDuty());

        all_total_student_txt.setText(Html.fromHtml("Total Student : " + "<font color='#1B88C8'>" + "<b>" + total_studentstr + "</b>"));
        all_present_txt.setText(Html.fromHtml("P : " + "<font color='#a4c639'>" + "<b>" + prsent_str + "</b>"));
        all_absent_txt.setText(Html.fromHtml("A : " + "<font color='#ff0000'>" + "<b>" + absent_str + "</b>"));
        all_leave_txt.setText(Html.fromHtml("L : " + "<font color='#ff9623'>" + "<b>" + leave_str + "</b>"));
        all_onduty_txt.setText(Html.fromHtml("O.D : " + "<font color='#1B88C8'>" + "<b>" + onduty_str + "</b>"));

        if (!getAttendenceData_allModelResponse.getFinalArray().get(0).getStudentDetail().get(0).getAttendenceStatus().equalsIgnoreCase("-2")) {
            insert_attendance_img.setBackgroundResource(R.drawable.update_1);
        } else {
            insert_attendance_img.setBackgroundResource(R.drawable.submit);
        }
        for (int j = 0; j < getAttendenceData_allModelResponse.getFinalArray().get(0).getStudentDetail().size(); j++) {
            if (getAttendenceData_allModelResponse.getFinalArray().get(0).getStudentDetail().get(j).getAttendenceStatus().equalsIgnoreCase("-2")) {
                getAttendenceData_allModelResponse.getFinalArray().get(0).getStudentDetail().get(j).setAttendenceStatus("1");
            }
        }
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = "Selected Date : " + Day + "/" + Month + "/" + Year;
        String datestr = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;

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
        getStudentData();
    }

    public void InsertAttendance() {
        final ArrayList<String> Attendanceid = new ArrayList<>();
        final ArrayList<String> Attendacestatus = new ArrayList<>();
        final ArrayList<String> studid = new ArrayList<>();
        final ArrayList<String> comment = new ArrayList<>();

        for (int i = 0; i < getAttendenceData_allModelResponse.getFinalArray().size(); i++) {
            for (int j = 0; j < getAttendenceData_allModelResponse.getFinalArray().get(i).getStudentDetail().size(); j++) {
                Attendanceid.add(String.valueOf(getAttendenceData_allModelResponse.getFinalArray().get(i).getStudentDetail().get(j).getAttendanceID()));
                Attendacestatus.add(String.valueOf(getAttendenceData_allModelResponse.getFinalArray().get(i).getStudentDetail().get(j).getAttendenceStatus()));
                studid.add(String.valueOf(getAttendenceData_allModelResponse.getFinalArray().get(i).getStudentDetail().get(j).getStudentID()));
                comment.add(String.valueOf(getAttendenceData_allModelResponse.getFinalArray().get(i).getStudentDetail().get(j).getComment()));
            }
        }
        Log.d("Attendanceid", "" + Attendanceid);
        Log.d("Attendacestatus", "" + Attendacestatus);
        Log.d("studid", "" + studid);
        Log.d("comment", "" + comment);

        Attendanceidstr = "";
        for (String s : Attendanceid) {
            Attendanceidstr = Attendanceidstr + "," + s;
        }
        Log.d("Attendanceidstr", Attendanceidstr);
        Attendanceidstr = Attendanceidstr.substring(1, Attendanceidstr.length());
        Log.d("finalstatusStr", Attendanceidstr);

        Attendacestatusstr = "";
        for (String s : Attendacestatus) {
            Attendacestatusstr = Attendacestatusstr + "," + s;
        }
        Log.d("Attendacestatusstr", Attendacestatusstr);
        Attendacestatusstr = Attendacestatusstr.substring(1, Attendacestatusstr.length());
        Log.d("Attendacestatusstr", Attendacestatusstr);

        studentidstr = "";
        for (String s : studid) {
            studentidstr = studentidstr + "," + s;
        }
        Log.d("studentidstr", studentidstr);
        studentidstr = studentidstr.substring(1, studentidstr.length());
        Log.d("studentidstr", studentidstr);

        commentstr = "";
        for (String s : comment) {
            commentstr = commentstr + "," + s;
        }
        Log.d("commentstr", commentstr);
        commentstr = commentstr.substring(1, commentstr.length());
        Log.d("commentstr", commentstr);
//================
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
                        params.put("StaffID", Utility.getPref(mContext, "StaffID"));
                        params.put("StandardID", selectedstandardIdStr);
                        params.put("ClassID", selectedsectionId);
                        params.put("Date", start_date.getText().toString());
                        params.put("Comment", commentstr);
                        params.put("AttendacneStatus", Attendacestatusstr);
                        params.put("CurrantDate", Utility.getTodaysDate());
                        params.put("StudentID", studentidstr);
                        params.put("AttendanceID", Attendanceidstr);


                        getInsertAttendanceAsyncTask = new GetInsertAttendanceAsyncTask(params);
                        staffInsertAttendenceModelResponse = getInsertAttendanceAsyncTask.execute().get();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                                if (staffInsertAttendenceModelResponse.getFinalArray().size() > 0) {
                                    Utility.ping(mContext, "Save Sucessfully");
                                    updateAttendace();
                                    getStudentData();
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
            Utility.ping(mContext, "Network not available");
        }
    }

    public void updateAttendace() {
        String total_studentstr, prsent_str, absent_str, leave_str, onduty_str;
        total_studentstr = String.valueOf(staffInsertAttendenceModelResponse.getFinalArray().get(0).getTotal());
        prsent_str = String.valueOf(staffInsertAttendenceModelResponse.getFinalArray().get(0).getTotalPresent());
        absent_str = String.valueOf(staffInsertAttendenceModelResponse.getFinalArray().get(0).getTotalAbsent());
        leave_str = String.valueOf(staffInsertAttendenceModelResponse.getFinalArray().get(0).getTotalLeave());
        onduty_str = String.valueOf(staffInsertAttendenceModelResponse.getFinalArray().get(0).getTotalOnDuty());

        all_total_student_txt.setText(Html.fromHtml("Total Student : " + "<font color='#1B88C8'>" + "<b>" + total_studentstr + "</b>"));
        all_present_txt.setText(Html.fromHtml("P : " + "<font color='#a4c639'>" + "<b>" + prsent_str + "</b>"));
        all_absent_txt.setText(Html.fromHtml("A : " + "<font color='#ff0000'>" + "<b>" + absent_str + "</b>"));
        all_leave_txt.setText(Html.fromHtml("L : " + "<font color='#ff9623'>" + "<b>" + leave_str + "</b>"));
        all_onduty_txt.setText(Html.fromHtml("O.D : " + "<font color='#1B88C8'>" + "<b>" + onduty_str + "</b>"));
    }
}
