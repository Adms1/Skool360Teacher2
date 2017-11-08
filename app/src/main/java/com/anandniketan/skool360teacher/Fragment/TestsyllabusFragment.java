package com.anandniketan.skool360teacher.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.anandniketan.skool360teacher.Adapter.EditTestDetailsListAdapter;
import com.anandniketan.skool360teacher.Adapter.Test_syllabusAdapter;
import com.anandniketan.skool360teacher.Adapter.TestsyllabusListAdapter;
import com.anandniketan.skool360teacher.AsyncTasks.TeacherGetTestSyllabusAsyncTask;
import com.anandniketan.skool360teacher.AsyncTasks.TeacherUpdateTestDetailAsyncTask;
import com.anandniketan.skool360teacher.Interfacess.CallBack;
import com.anandniketan.skool360teacher.Interfacess.onEditTest;
import com.anandniketan.skool360teacher.Models.Test_SyllabusModel;
import com.anandniketan.skool360teacher.Models.UpdateTestDetailModel;
import com.anandniketan.skool360teacher.R;
import com.anandniketan.skool360teacher.Utility.Utility;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;


public class TestsyllabusFragment extends Fragment {
    private View rootView;
    private TextView txtNoRecordstest;
    private Context mContext;
    private ProgressDialog progressDialog = null;
    private TeacherGetTestSyllabusAsyncTask teacherGetTestSyllabusAsyncTask = null;
    private ArrayList<Test_SyllabusModel> test_syllabusModels = new ArrayList<>();
    private ListView test_syllabus_list;
    Test_syllabusAdapter test_syllabusAdapter = null;
    TestsyllabusListAdapter testsyllabusListAdapter = null;
    private LinearLayout test_header;
    FragmentManager fragmentManager;
    //use for Dialog
    private AlertDialog alertDialogAndroid = null;
    private Button close_btn, Edit_btn;
    private TextView edit_test_txt, edit_test_date_txt, edit_test_grade_txt, edit_test_subject_txt;
    //    private ListView listData;
    int Year, Month, Day;
    int mYear, mMonth, mDay;
    Calendar calendar;
    private DatePickerDialog datePickerDialog;
    ArrayList<String> syllbusarray = new ArrayList<>();
    String spiltStr, splitarray;
    //use for fill Listdata
    EditTestDetailsListAdapter editTestDetailsListAdapter;
    ArrayList<String> text;

    private TeacherUpdateTestDetailAsyncTask updateTestDetailAsyncTask = null;
    UpdateTestDetailModel updateTestDetailModel;

    public TestsyllabusFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_testsyllabus, container, false);
        mContext = getActivity();

        initViews();
        setListners();
        return rootView;
    }

    public void initViews() {
        txtNoRecordstest = (TextView) rootView.findViewById(R.id.txtNoRecordstest);
        test_syllabus_list = (ListView) rootView.findViewById(R.id.test_syllabus_list);
        test_header = (LinearLayout) rootView.findViewById(R.id.test_header);

        setUserVisibleHint(true);
    }

    public void setListners() {
    }

    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && rootView != null) {
            getTestSyllabusData();
        }
        // execute your data loading logic.
    }

    public void getTestSyllabusData() {
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
                        teacherGetTestSyllabusAsyncTask = new TeacherGetTestSyllabusAsyncTask(params);
                        test_syllabusModels = teacherGetTestSyllabusAsyncTask.execute().get();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                                if (test_syllabusModels.size() > 0) {
                                    txtNoRecordstest.setVisibility(View.GONE);
                                    testsyllabusListAdapter = new TestsyllabusListAdapter(getActivity(), getActivity().getFragmentManager(), test_syllabusModels, new onEditTest() {
                                        @Override
                                        public void getEditTest() {
                                            EditTestSubmit();
                                        }
                                    });
                                    test_syllabus_list.setAdapter(testsyllabusListAdapter);
                                    test_syllabus_list.deferNotifyDataSetChanged();
                                } else {
                                    progressDialog.dismiss();
                                    txtNoRecordstest.setVisibility(View.VISIBLE);
                                    test_header.setVisibility(View.GONE);
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

    public void EditTestSubmit() {
        String TSMasterIDstr, TestIDstr, TestDatestr, SubjectIDstr, SectionIDstr,Datestr;
        String value = String.valueOf(testsyllabusListAdapter.getDataforEditTest());
        Log.d("value", value);

        String[] splitvalue = value.split("\\|");
        TSMasterIDstr = splitvalue[0];
        TestIDstr = splitvalue[1];
        SubjectIDstr = splitvalue[2];
        SectionIDstr = splitvalue[3];
        Datestr = splitvalue[4];

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");
        Date d = null;
        try {
            d = sdf.parse(Datestr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        TestDatestr = output.format(d);

        String txtstr = "";
        text = new ArrayList<String>();

        for (int j = 0; j < test_syllabus_list.getChildCount(); j++) {
            View view = test_syllabus_list.getChildAt(j);

            TextView editText=(TextView)view.findViewById(R.id.edit_txt);

            ListView list = (ListView) view.findViewById(R.id.listData);
            for (int i = 0; i < list.getChildCount(); i++) {
                View mView = list.getChildAt(i);
                EditText myEditText = (EditText) mView.findViewById(R.id.syllabus_txt);
                if (!myEditText.getText().toString().trim().equalsIgnoreCase("")) {
                    txtstr = txtstr + myEditText.getText().toString() + "|&";
                }
            }
        }
        text.add(txtstr);
        Log.d("join", "" + text.toString());

//        if (Utility.isNetworkConnected(mContext)) {
//            final String finalTxtstr = txtstr;
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        HashMap<String, String> params = new HashMap<String, String>();
//                        params.put("StaffID", Utility.getPref(mContext, "StaffID"));
//                        params.put("TSMasterID",TSMasterIDstr);
//                        params.put("TestID",TestIDstr);
//                        params.put("TestDate", TestDatestr);
//                        params.put("SubjectID", SubjectIDstr);
//                        params.put("SectionID", SectionIDstr);
//                        params.put("Arydetail", finalTxtstr);
//
//                        updateTestDetailAsyncTask = new TeacherUpdateTestDetailAsyncTask(params);
//                        updateTestDetailModel = updateTestDetailAsyncTask.execute().get();
//                        getActivity().runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                if (updateTestDetailModel.getSuccess().equalsIgnoreCase("True")) {
//                                  getTestSyllabusData();
//                                } else {
//                                    Utility.ping(mContext, "Something error");
//                                }
//                            }
//                        });
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }).start();
//        } else {
//            Utility.ping(mContext, "Network not available");
//        }
    }

}

