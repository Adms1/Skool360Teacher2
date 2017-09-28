package com.anandniketan.skool360teacher.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.anandniketan.skool360teacher.Adapter.MysubjectAdapetr;
import com.anandniketan.skool360teacher.Adapter.Test_syllabusAdapter;
import com.anandniketan.skool360teacher.AsyncTasks.GetTeacherAssignedSubjectAsyncTask;
import com.anandniketan.skool360teacher.Models.TeacherAssignedSubjectModel;
import com.anandniketan.skool360teacher.R;
import com.anandniketan.skool360teacher.Utility.Utility;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;


public class AddTestFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    public AddTestFragment() {
        // Required empty public constructor
    }

    private Button test_date, Add_btn;
    private Spinner standard_subject_spinner, test_section_spinner, test_spinner;
    private Context mContext;
    private View rootView;
    private TextView txtNoRecords;
    private DatePickerDialog datePickerDialog;
    int Year, Month, Day;
    Calendar calendar;
    int mYear, mMonth, mDay;
    private ProgressDialog progressDialog = null;
    private GetTeacherAssignedSubjectAsyncTask getTeacherAssignedSubjectAsyncTask = null;
    private ArrayList<TeacherAssignedSubjectModel> teacherAssignedSubjectModels = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_add_test, container, false);
        mContext = getActivity();

        init();
        setListner();
        return rootView;
    }

    public void init() {
        calendar = Calendar.getInstance();
        Year = calendar.get(Calendar.YEAR);
        Month = calendar.get(Calendar.MONTH);
        Day = calendar.get(Calendar.DAY_OF_MONTH);

        standard_subject_spinner = (Spinner) rootView.findViewById(R.id.standard_subject_spinner);
        test_section_spinner = (Spinner) rootView.findViewById(R.id.test_section_spinner);
        test_spinner = (Spinner) rootView.findViewById(R.id.test_spinner);
        test_date = (Button) rootView.findViewById(R.id.test_date);
        Add_btn = (Button) rootView.findViewById(R.id.Add_btn);
        txtNoRecords = (TextView) rootView.findViewById(R.id.txtNoRecordstest);
        setUserVisibleHint(false);
        test_date.setText(Utility.getTodaysDate());
        setTodayschedule();
        fillsubjectspinner();

    }

    public void setListner() {
        test_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog = DatePickerDialog.newInstance(AddTestFragment.this, Year, Month, Day);
                datePickerDialog.setThemeDark(false);
                datePickerDialog.showYearPickerFirst(false);
                datePickerDialog.setAccentColor(Color.parseColor("#1B88C8"));
                datePickerDialog.setTitle("Select Date From DatePickerDialog");
                datePickerDialog.show(getActivity().getFragmentManager(), "DatePickerDialog");
            }
        });
        Add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

    public void Dialog(){
        LayoutInflater lInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View layout = lInflater.inflate(R.layout.list_edit_row, null);

        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(mContext);
        alertDialogBuilderUserInput.setView(layout);

        alertDialogAndroid = alertDialogBuilderUserInput.create();
        alertDialogAndroid.setCancelable(false);
        alertDialogAndroid.show();
        Window window = alertDialogAndroid.getWindow();
        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, 1200);
        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.CENTER_HORIZONTAL;
        wlp.flags = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        window.setAttributes(wlp);
        alertDialogAndroid.show();


        close_btn = (Button) layout.findViewById(R.id.close_btn);
        Edit_btn = (Button) layout.findViewById(R.id.Edit_btn);
        edit_test_txt = (TextView) layout.findViewById(R.id.edit_test_txt);
        edit_test_date_txt = (TextView) layout.findViewById(R.id.edit_test_date_txt);
        edit_test_grade_txt = (TextView) layout.findViewById(R.id.edit_test_grade_txt);
        edit_test_subject_txt = (TextView) layout.findViewById(R.id.edit_test_subject_txt);
        llListData = (LinearLayout) layout.findViewById(R.id.llListData);

        calendar = Calendar.getInstance();
        Year = calendar.get(Calendar.YEAR);
        Month = calendar.get(Calendar.MONTH);
        Day = calendar.get(Calendar.DAY_OF_MONTH);

        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogAndroid.dismiss();
            }
        });
        Edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("text",""+text);
//                                if (Utility.isNetworkConnected(mContext)) {
////                                    progressDialog = new ProgressDialog(mContext);
////                                    progressDialog.setMessage("Please Wait...");
////                                    progressDialog.setCancelable(false);
////                                    progressDialog.show();
//
//                                    new Thread(new Runnable() {
//                                        @Override
//                                        public void run() {
//                                            try {
//                                                HashMap<String, String> params = new HashMap<String, String>();
//                                                params.put("StaffID", Utility.getPref(mContext, "StaffID"));
//                                                params.put("TSMasterID", test_syllabusModels.get(position).getTSMasterID());
//                                                params.put("TestID", test_syllabusModels.get(position).getTestID());
//                                                params.put("TestDate", edit_test_date_txt.getText().toString());
//                                                params.put("SubjectID", test_syllabusModels.get(position).getSubjectID());
//                                                params.put("SectionID", test_syllabusModels.get(position).getSectionID());
//                                                params.put("Arydetail", "test|&test|&test");
//
//                                                teacherInsertTestDetailAsyncTask = new TeacherInsertTestDetailAsyncTask(params);
//                                                insertTest = teacherInsertTestDetailAsyncTask.execute().get();
//                                                Test_syllabusAdapter.this.runOnUiThread(new Runnable() {
//                                                    @Override
//                                                    public void run() {
//                                                        Utility.ping(mContext, "Update Test");
////                                                        progressDialog.dismiss();
////                                                        if (insertTest.size() > 0) {
////                                                            Utility.ping(mContext, "Update Test");
////                                                        } else {
//////                                                            progressDialog.dismiss();
////                                                        }
//                                                    }
//                                                });
//                                            } catch (Exception e) {
//                                                e.printStackTrace();
//                                            }
//                                        }
//                                    }).start();
//                                } else {
//                                    Utility.ping(mContext, "Network not available");
//                                }
            }
        });
        edit_test_date_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog = DatePickerDialog.newInstance(Test_syllabusAdapter.this, Year, Month, Day);
                datePickerDialog.setThemeDark(false);
                datePickerDialog.showYearPickerFirst(false);
                datePickerDialog.setAccentColor(Color.parseColor("#1B88C8"));
                datePickerDialog.setTitle("Select Date From DatePickerDialog");
                datePickerDialog.show(activity, "DatePickerDialog");
            }
        });

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat output = new SimpleDateFormat("dd-MM-yyyy");
        Date d = null;
        try {
            d = sdf.parse(test_syllabusModels.get(position).getTestDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedTime = output.format(d);

        edit_test_txt.setText(Html.fromHtml("<b>Test :</b>" + test_syllabusModels.get(position).getTestName()));
        edit_test_date_txt.setText(formattedTime);
        edit_test_grade_txt.setText(Html.fromHtml("<b>Grade :</b>" + test_syllabusModels.get(position).getStandardClass()));
        edit_test_subject_txt.setText(Html.fromHtml("<b>Subject :</b>" + test_syllabusModels.get(position).getSubject()));


        if (llListData.getChildCount() > 0) {
            llListData.removeAllViews();
        }
        try {
            for (int i = 0; i < 10; i++) {
                View convertView = LayoutInflater.from(mContext).inflate(R.layout.list_edittext, null);
                syllbus_edt = (EditText) convertView.findViewById(R.id.syllabus_txt);

                if (i < test_syllabusModels.get(position).getGetSyllabusData().size()) {
                    syllbus_edt.setText(test_syllabusModels.get(position).getGetSyllabusData().get(i).getSyllabus());

                } else {
                    syllbus_edt.setText(" ");
                }

//                                test_syllabusModels.get(position).getGetSyllabusData().get(i).setSyllabus(syllbus_edt.getText().toString());
                syllbus_edt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean b) {
//                                        text.add();
                    }
                });
                llListData.addView(convertView);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
});
        } catch (Exception e) {
        e.printStackTrace();
        }
        }
    }


    public void setTodayschedule() {
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

                        getTeacherAssignedSubjectAsyncTask = new GetTeacherAssignedSubjectAsyncTask(params);
                        teacherAssignedSubjectModels = getTeacherAssignedSubjectAsyncTask.execute().get();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                                if (teacherAssignedSubjectModels.size() > 0) {
                                    txtNoRecords.setVisibility(View.GONE);
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

        test_date.setText(d + "/" + m + "/" + y);
    }

    public void fillsubjectspinner() {
        ArrayList<String> row = new ArrayList<String>();

        for (int z = 0; z < teacherAssignedSubjectModels.size(); z++) {
            row.add(teacherAssignedSubjectModels.get(z).getStandard() + " -> " + teacherAssignedSubjectModels.get(z).getSubject());
        }
        ArrayAdapter<String> adapterYear = new ArrayAdapter<String>(mContext, R.layout.spinner_layout, row);
        standard_subject_spinner.setAdapter(adapterYear);
    }

}
