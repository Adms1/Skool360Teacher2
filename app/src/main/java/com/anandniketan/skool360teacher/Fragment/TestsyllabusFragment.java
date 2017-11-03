package com.anandniketan.skool360teacher.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.anandniketan.skool360teacher.Adapter.Test_syllabusAdapter;
import com.anandniketan.skool360teacher.AsyncTasks.TeacherGetTestSyllabusAsyncTask;
import com.anandniketan.skool360teacher.Interfacess.CallBack;
import com.anandniketan.skool360teacher.Models.Test_SyllabusModel;
import com.anandniketan.skool360teacher.R;
import com.anandniketan.skool360teacher.Utility.Utility;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;


public class TestsyllabusFragment extends Fragment implements CallBack {
    private View rootView;
    private TextView txtNoRecordstest;
    private Context mContext;
    private ProgressDialog progressDialog = null;
    private TeacherGetTestSyllabusAsyncTask teacherGetTestSyllabusAsyncTask = null;
    private ArrayList<Test_SyllabusModel> test_syllabusModels = new ArrayList<>();
    private ListView test_syllabus_list;
    Test_syllabusAdapter test_syllabusAdapter = null;
    private LinearLayout test_header;
    FragmentManager fragmentManager;

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
                                    test_syllabusAdapter = new Test_syllabusAdapter(getActivity(),getActivity().getFragmentManager(), test_syllabusModels);
                                    test_syllabus_list.setAdapter(test_syllabusAdapter);
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
}

