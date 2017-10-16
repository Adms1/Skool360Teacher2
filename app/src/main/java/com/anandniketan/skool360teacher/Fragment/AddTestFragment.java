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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.anandniketan.skool360teacher.Adapter.MysubjectAdapetr;
import com.anandniketan.skool360teacher.Adapter.Test_syllabusAdapter;
import com.anandniketan.skool360teacher.AsyncTasks.GetTeacherAssignedSubjectAsyncTask;
import com.anandniketan.skool360teacher.AsyncTasks.TeacherGetTestNameGradeWiseAsyncTask;
import com.anandniketan.skool360teacher.AsyncTasks.TeacherInsertTestDetailAsyncTask;
import com.anandniketan.skool360teacher.Models.TeacherAssignedSubjectModel;
import com.anandniketan.skool360teacher.Models.TeacherGetTestNameModel;
import com.anandniketan.skool360teacher.Models.TeacherInsertTestDetailModel;
import com.anandniketan.skool360teacher.R;
import com.anandniketan.skool360teacher.Utility.Utility;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;


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
    private AlertDialog alertDialogAndroid = null;
    private Button close_btn, add_btn;
    private TextView add_test_txt, add_test_date_txt, add_test_grade_txt, add_test_subject_txt;
    private LinearLayout llListData;
    private EditText syllbus_edt;
    private CheckBox checkBox;
    private TeacherInsertTestDetailAsyncTask teacherInsertTestDetailAsyncTask = null;
    private ArrayList<TeacherInsertTestDetailModel> insertTest = new ArrayList<>();
    private LinearLayout checkbox_linear, main_linear_add;
    private TeacherGetTestNameGradeWiseAsyncTask teacherGetTestNameGradeWiseAsyncTask = null;
    private ArrayList<TeacherGetTestNameModel> teacherGetTestNameModels = new ArrayList<>();
    private String standardId;
    AddTest addTest = new AddTest();
    private int selectedPosition = -1;
    private ArrayList<String> text = new ArrayList<>();

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
//        test_section_spinner = (Spinner) rootView.findViewById(R.id.test_section_spinner);
        test_spinner = (Spinner) rootView.findViewById(R.id.test_spinner);
        test_date = (Button) rootView.findViewById(R.id.test_date);
        Add_btn = (Button) rootView.findViewById(R.id.Add_btn);
        txtNoRecords = (TextView) rootView.findViewById(R.id.txtNoRecordstest);
        checkbox_linear = (LinearLayout) rootView.findViewById(R.id.checkbox_linear);
        main_linear_add = (LinearLayout) rootView.findViewById(R.id.main_linear_add);
        setUserVisibleHint(true);
        test_date.setText(Utility.getTodaysDate());


    }

    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            setTodayschedule();
            fillsubjectspinner();
        }
        // execute your data loading logic.
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
                addTest.TestDate = test_date.getText().toString();
                Dialog();

            }
        });

        standard_subject_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (teacherAssignedSubjectModels.size() > 0) {
                    teacherAssignedSubjectModels.get(0).setStandardsubject((String) adapterView.getItemAtPosition(i).toString());
                    standardsubjectsectionspinner();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        test_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (teacherGetTestNameModels.size() > 0) {
                    addTest.testName = adapterView.getItemAtPosition(i).toString();
                    addTest.AddTestID = teacherGetTestNameModels.get(i).getTestID();
                }
                String testvalue;
                testvalue=adapterView.getItemAtPosition(i).toString();
                if(testvalue.equalsIgnoreCase("-Please Select-")){
                    Add_btn.setEnabled(false);
                    Add_btn.setAlpha((float) 0.5);
                }else
                {
                    Add_btn.setEnabled(true);
                    Add_btn.setAlpha(1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void Dialog() {
        LayoutInflater lInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View layout = lInflater.inflate(R.layout.list_add_row, null);

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
        add_btn = (Button) layout.findViewById(R.id.add_btn);
        add_test_txt = (TextView) layout.findViewById(R.id.add_test_txt);
        add_test_date_txt = (TextView) layout.findViewById(R.id.add_test_date_txt);
        add_test_grade_txt = (TextView) layout.findViewById(R.id.add_test_grade_txt);
        add_test_subject_txt = (TextView) layout.findViewById(R.id.add_test_subject_txt);
        llListData = (LinearLayout) layout.findViewById(R.id.llListData);


        add_test_txt.setText(addTest.testName);
        add_test_date_txt.setText(addTest.TestDate);
        add_test_grade_txt.setText(addTest.standard + " - " + addTest.classname);
        add_test_subject_txt.setText(addTest.SubjectName);

        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogAndroid.dismiss();
            }
        });
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtstr = "";
                text = new ArrayList<String>();
                for (int i = 0; i < llListData.getChildCount(); i++) {

                    View mView = llListData.getChildAt(i);
                    EditText myEditText = (EditText) mView.findViewById(R.id.syllabus_txt);
                    if (!myEditText.getText().toString().trim().equalsIgnoreCase("")) {
                        txtstr = txtstr + myEditText.getText().toString() + "|&";
                    }
                }
                text.add(txtstr);
                Log.d("join", "" + text.toString());
                if (Utility.isNetworkConnected(mContext)) {
//                                    progressDialog = new ProgressDialog(mContext);
//                                    progressDialog.setMessage("Please Wait...");
//                                    progressDialog.setCancelable(false);
//                                    progressDialog.show();
                    final String finalTxtstr = txtstr;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                HashMap<String, String> params = new HashMap<String, String>();
                                params.put("StaffID", Utility.getPref(mContext, "StaffID"));
                                params.put("TSMasterID", "0");
                                params.put("TestID", addTest.AddTestID);
                                params.put("TestDate", add_test_date_txt.getText().toString());
                                params.put("SubjectID", addTest.SubjectID);
                                params.put("SectionID", addTest.SectionID);
                                params.put("Arydetail", finalTxtstr);

                                teacherInsertTestDetailAsyncTask = new TeacherInsertTestDetailAsyncTask(params);
                                insertTest = teacherInsertTestDetailAsyncTask.execute().get();
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Utility.ping(mContext, "Update Test");
//                                                        progressDialog.dismiss();
//                                                        if (insertTest.size() > 0) {
//                                                            Utility.ping(mContext, "Update Test");
//                                                        } else {
////                                                            progressDialog.dismiss();
//                                                        }
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
        });

        if (llListData.getChildCount() > 0) {
            llListData.removeAllViews();
        }
        try {
            for (int i = 0; i < 10; i++) {
                View convertView = LayoutInflater.from(mContext).inflate(R.layout.list_edittext, null);
                syllbus_edt = (EditText) convertView.findViewById(R.id.syllabus_txt);

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
                                    main_linear_add.setVisibility(View.VISIBLE);

                                } else {
                                    progressDialog.dismiss();
                                    txtNoRecords.setVisibility(View.VISIBLE);
                                    main_linear_add.setVisibility(View.GONE);
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

    public void getTestName() {
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
                        params.put("StandardID", standardId);

                        teacherGetTestNameGradeWiseAsyncTask = new TeacherGetTestNameGradeWiseAsyncTask(params);
                        teacherGetTestNameModels = teacherGetTestNameGradeWiseAsyncTask.execute().get();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
//                                progressDialog.dismiss();
                                if (teacherGetTestNameModels.size() > 0) {
                                    txtNoRecords.setVisibility(View.GONE);
                                    fillTestSpinner();
                                } else {
//                                    progressDialog.dismiss();
                                    ArrayList<String> str = new ArrayList<String>();
                                    str.add("-Please Select-");
                                    ArrayAdapter<String> adaptertest = new ArrayAdapter<String>(mContext, R.layout.spinner_layout, str);
                                    test_spinner.setAdapter(adaptertest);

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


    public void fillsubjectspinner() {
        ArrayList<String> row = new ArrayList<String>();

        for (int z = 0; z < teacherAssignedSubjectModels.size(); z++) {
            row.add(teacherAssignedSubjectModels.get(z).getStandard() + " -> " + teacherAssignedSubjectModels.get(z).getSubject());
        }

//        HashSet<String> duplicates = new HashSet<String>();
//        duplicates.add(String.valueOf(row));
//        row.clear();
//        row.addAll(duplicates);
        ArrayAdapter<String> adapterYear = new ArrayAdapter<String>(mContext, R.layout.spinner_layout, row);
        standard_subject_spinner.setAdapter(adapterYear);

    }

    public void standardsubjectsectionspinner() {
        ArrayList<String> arrayList = new ArrayList<>();
        String getsubjectstandardstr = teacherAssignedSubjectModels.get(0).getStandardsubject();

        if (!getsubjectstandardstr.equalsIgnoreCase("")) {
            main_linear_add.setVisibility(View.VISIBLE);
            String[] str = getsubjectstandardstr.split("\\->");
            addTest.SubjectName = str[1].trim();
            addTest.standard = str[0].trim();
            for (int i = 0; i < teacherAssignedSubjectModels.size(); i++) {
                if (str[0].trim().equalsIgnoreCase(teacherAssignedSubjectModels.get(i).getStandard()) && str[1].trim().equalsIgnoreCase(teacherAssignedSubjectModels.get(i).getSubject())) {
                    arrayList.add(teacherAssignedSubjectModels.get(i).getClassname());
                    addTest.SubjectID = teacherAssignedSubjectModels.get(i).getSubjectID();
                    addTest.SectionID = teacherAssignedSubjectModels.get(i).getClassID();
                    Log.d("arrayList", "" + arrayList);
                }
                if (str[0].trim().equalsIgnoreCase(teacherAssignedSubjectModels.get(i).getStandard())) {
                    standardId = teacherAssignedSubjectModels.get(i).getStandardID();
                    Log.d("IDDDD", standardId);
                }
            }
            getTestName();
            if (checkbox_linear.getChildCount() > 0) {
                checkbox_linear.removeAllViews();
            }
            try {
                for (int i = 0; i < arrayList.size(); i++) {
                    View convertView = LayoutInflater.from(mContext).inflate(R.layout.list_checkbox, null);
                    checkBox = (CheckBox) convertView.findViewById(R.id.checkbox);

                    checkBox.setText(arrayList.get(i));
//                textView.setText(arrayList.get(i));

                    if (i == 0) {
                        checkBox.setChecked(true);
                    }
                    checkBox.setOnClickListener(onStateChangedListener(checkBox, i));
                    checkbox_linear.addView(convertView);
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }


        } else {
            txtNoRecords.setVisibility(View.VISIBLE);
            main_linear_add.setVisibility(View.GONE);
        }
    }

    public void fillTestSpinner() {
        ArrayList<String> rowtest = new ArrayList<String>();

        for (int k = 0; k < teacherGetTestNameModels.size(); k++) {
            rowtest.add(teacherGetTestNameModels.get(k).getTestName() + teacherGetTestNameModels.get(k).getTestID());
        }
        ArrayAdapter<String> adaptertest = new ArrayAdapter<String>(mContext, R.layout.spinner_layout, rowtest);
        test_spinner.setAdapter(adaptertest);
    }


    public class AddTest {

        private String AddTestID;
        private String TestDate;
        private String SubjectID;
        private String SectionID;
        private String SubjectName;
        private String testName;
        private String standard;
        private String classname;

    }

    private View.OnClickListener onStateChangedListener(final CheckBox checkBox, final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    checkBox.setChecked(true);
                    selectedPosition = position;
                    addTest.classname = checkBox.getText().toString();
                } else {
                    selectedPosition = -1;
                    checkBox.setChecked(false);

                }


            }
        };
    }
}
