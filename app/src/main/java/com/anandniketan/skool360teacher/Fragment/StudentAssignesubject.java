package com.anandniketan.skool360teacher.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.anandniketan.skool360teacher.Adapter.MysubjectAdapetr;
import com.anandniketan.skool360teacher.Adapter.StudentAssignesubjectAdapter;
import com.anandniketan.skool360teacher.Adapter.TodayscheduleAdapter;
import com.anandniketan.skool360teacher.AsyncTasks.GetTeacherAssignedSubjectAsyncTask;
import com.anandniketan.skool360teacher.AsyncTasks.GetTeacherGetAssignStudentSubjectAsyncTask;
import com.anandniketan.skool360teacher.AsyncTasks.GetTeacherTodayScheduleAsyncTask;
import com.anandniketan.skool360teacher.Models.TeacherAssignedSubjectModel;
import com.anandniketan.skool360teacher.Models.TeacherGetAssignStudentSubjectmModel;
import com.anandniketan.skool360teacher.Models.TeacherTodayScheduleModel;
import com.anandniketan.skool360teacher.R;
import com.anandniketan.skool360teacher.Utility.AppConfiguration;
import com.anandniketan.skool360teacher.Utility.Utility;

import java.util.ArrayList;
import java.util.HashMap;


public class StudentAssignesubject extends Fragment {

    public StudentAssignesubject() {
        // Required empty public constructor
    }

    private Context mContext;
    private View rootView;
    private ProgressDialog progressDialog = null;
    private GetTeacherGetAssignStudentSubjectAsyncTask getTeacherGetAssignStudentSubjectAsyncTask = null;
    private ArrayList<TeacherGetAssignStudentSubjectmModel> teacherGetAssignStudentSubjectmModels = new ArrayList<>();
    StudentAssignesubjectAdapter studentAssignesubjectAdapter = null;
    private ListView studentassignesubject_list;
    private LinearLayout header_linear;
    private TextView txtNoRecords, standard_txt, class_txt;
    private LinearLayout class_checknox_linear, standard_checknox_linear;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_student_assignesubject, container, false);
        mContext = getActivity();

        init();
        setListner();
        setTodayschedule();

        return rootView;
    }

    public void init() {

        header_linear = (LinearLayout) rootView.findViewById(R.id.header_linear);
        txtNoRecords = (TextView) rootView.findViewById(R.id.txtNoRecords);
        studentassignesubject_list = (ListView) rootView.findViewById(R.id.subject_list);
        standard_txt = (TextView) rootView.findViewById(R.id.standard_txt);
        class_txt = (TextView) rootView.findViewById(R.id.standard_txt);
        class_checknox_linear = (LinearLayout) rootView.findViewById(R.id.class_checknox_linear);
        standard_checknox_linear = (LinearLayout) rootView.findViewById(R.id.standard_checknox_linear);

        setUserVisibleHint(true);
    }

    public void setListner() {
    }

    public void setStandardandClass() {

        for (int i = 0; i < AppConfiguration.rows.size(); i++) {

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

                        getTeacherGetAssignStudentSubjectAsyncTask = new GetTeacherGetAssignStudentSubjectAsyncTask(params);
                        teacherGetAssignStudentSubjectmModels = getTeacherGetAssignStudentSubjectAsyncTask.execute().get();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                                if (teacherGetAssignStudentSubjectmModels.size() > 0) {
                                    txtNoRecords.setVisibility(View.GONE);

                                    studentAssignesubjectAdapter = new StudentAssignesubjectAdapter(getActivity(), teacherGetAssignStudentSubjectmModels);
                                    studentassignesubject_list.setAdapter(studentAssignesubjectAdapter);
                                    studentassignesubject_list.deferNotifyDataSetChanged();
                                } else {
                                    progressDialog.dismiss();
                                    txtNoRecords.setVisibility(View.VISIBLE);
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
}
