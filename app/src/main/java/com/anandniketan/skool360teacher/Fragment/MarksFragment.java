package com.anandniketan.skool360teacher.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anandniketan.skool360teacher.Activities.DashBoardActivity;
import com.anandniketan.skool360teacher.Adapter.ExpandableListAdapterMarks;
import com.anandniketan.skool360teacher.Adapter.ExpandableListAdapterTimeTable;
import com.anandniketan.skool360teacher.AsyncTasks.GetTeacherGetTimetableAsyncTask;
import com.anandniketan.skool360teacher.AsyncTasks.TeacherGetTestMarksAsyncTask;
import com.anandniketan.skool360teacher.Models.TeacherGetTestMarksModel;
import com.anandniketan.skool360teacher.Models.TeacherGetTimetableModel;
import com.anandniketan.skool360teacher.R;
import com.anandniketan.skool360teacher.Utility.Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MarksFragment extends Fragment {
    private View rootView;
    private Button btnMenu, btnBackMarks;
    private TextView txtNoRecordsMarks;
    private Context mContext;
    private ProgressDialog progressDialog = null;
    private TeacherGetTestMarksAsyncTask getTestMarksAsyncTask = null;
    private ArrayList<TeacherGetTestMarksModel> teacherGetTestMarksModels = new ArrayList<>();
    private int lastExpandedPosition = -1;
    private LinearLayout Marks_header;

    ExpandableListAdapterMarks listAdapterMarks;
    ExpandableListView lvExpMarks;
    List<String> listDataHeader;
    HashMap<String, ArrayList<TeacherGetTestMarksModel.studentDetail.TestDetail.subjectMarks>> listDataChild;


    public MarksFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_marks, container, false);
        mContext = getActivity();

        initViews();
        setListners();

        return rootView;
    }

    public void initViews() {
        btnMenu = (Button) rootView.findViewById(R.id.btnMenu);
        txtNoRecordsMarks = (TextView) rootView.findViewById(R.id.txtNoRecordsMarks);
        btnBackMarks = (Button) rootView.findViewById(R.id.btnBackMarks);
        lvExpMarks = (ExpandableListView) rootView.findViewById(R.id.lvExpMarks);
        Marks_header = (LinearLayout) rootView.findViewById(R.id.Marks_header);

        getTimeTableData();
    }

    public void setListners() {
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DashBoardActivity.onLeft();
            }
        });

        btnBackMarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new HomeFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .setCustomAnimations(0, 0)
                        .replace(R.id.frame_container, fragment).commit();
            }
        });
        lvExpMarks.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpandedPosition != -1
                        && groupPosition != lastExpandedPosition) {
                    lvExpMarks.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = groupPosition;
            }
        });
    }

    public void getTimeTableData() {
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
                        getTestMarksAsyncTask = new TeacherGetTestMarksAsyncTask(params);
                        teacherGetTestMarksModels = getTestMarksAsyncTask.execute().get();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                                if (teacherGetTestMarksModels.size() > 0) {
                                    txtNoRecordsMarks.setVisibility(View.GONE);
                                    prepaareList();
                                    listAdapterMarks = new ExpandableListAdapterMarks(getActivity(), listDataHeader, listDataChild);
                                    lvExpMarks.setAdapter(listAdapterMarks);
                                } else {
                                    progressDialog.dismiss();
                                    txtNoRecordsMarks.setVisibility(View.VISIBLE);
                                    Marks_header.setVisibility(View.GONE);
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

    public void prepaareList() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, ArrayList<TeacherGetTestMarksModel.studentDetail.TestDetail.subjectMarks>>();

        for (int k = 0; k < teacherGetTestMarksModels.get(0).getGetstudentDetail().size(); k++) {
            for (int i = 0; i < teacherGetTestMarksModels.get(0).getGetstudentDetail().get(k).getGettestDetail().size(); i++) {
                Marks marksdemo = new Marks();
                marksdemo.studentname = teacherGetTestMarksModels.get(0).getGetstudentDetail().get(i).getGettestDetail().get(i).getStudentName().toString();
                marksdemo.grno = teacherGetTestMarksModels.get(0).getGetstudentDetail().get(i).getGettestDetail().get(i).getGRNO().toString();
                marksdemo.percentage = teacherGetTestMarksModels.get(0).getGetstudentDetail().get(i).getGettestDetail().get(i).getPercentage().toString();

                listDataHeader.add(marksdemo.studentname.toString() + "|" + marksdemo.grno.toString() + "|" + marksdemo.percentage.toString());

                ArrayList<TeacherGetTestMarksModel.studentDetail.TestDetail.subjectMarks> rows = new ArrayList<TeacherGetTestMarksModel.studentDetail.TestDetail.subjectMarks>();
                for (int j = 0; j < teacherGetTestMarksModels.get(0).getGetstudentDetail().get(i).getGettestDetail().get(i).getGetsubjectMarks().size(); j++) {

                    rows.add(teacherGetTestMarksModels.get(0).getGetstudentDetail().get(i).getGettestDetail().get(i).getGetsubjectMarks().get(j));

                }
                listDataChild.put(listDataHeader.get(i), rows);
            }
        }
    }

    public class Marks {
        private String studentname;
        private String grno;
        private String percentage;
    }
}
