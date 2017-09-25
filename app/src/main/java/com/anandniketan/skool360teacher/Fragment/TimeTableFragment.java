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
import android.widget.TextView;

import com.anandniketan.skool360teacher.Activities.DashBoardActivity;
import com.anandniketan.skool360teacher.Adapter.ExpandableListAdapterTimeTable;
import com.anandniketan.skool360teacher.AsyncTasks.GetTeacherGetTimetableAsyncTask;
import com.anandniketan.skool360teacher.Models.TeacherGetTimetableModel;
import com.anandniketan.skool360teacher.R;
import com.anandniketan.skool360teacher.Utility.Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TimeTableFragment extends Fragment {
    private View rootView;
    private Button btnMenu, btnBackTimeTable;
    private TextView txtNoRecordsTimetable;
    private Context mContext;
    private ProgressDialog progressDialog = null;
    private GetTeacherGetTimetableAsyncTask getTeacherGetTimetableAsyncTask = null;
    private ArrayList<TeacherGetTimetableModel> timetableModels = new ArrayList<>();
    private int lastExpandedPosition = -1;

    ExpandableListAdapterTimeTable listAdapterTimeTable;
    ExpandableListView lvExpTimeTable;
    List<String> listDataHeader;
    HashMap<String, ArrayList<TeacherGetTimetableModel.TimeTable.TimeTableData>> listDataChild;


    public TimeTableFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_time_table, container, false);
        mContext = getActivity();

        initViews();
        setListners();

        return rootView;
    }

    public void initViews() {
        btnMenu = (Button) rootView.findViewById(R.id.btnMenu);
        txtNoRecordsTimetable = (TextView) rootView.findViewById(R.id.txtNoRecordsTimetable);
        btnBackTimeTable = (Button) rootView.findViewById(R.id.btnBackTimeTable);
        lvExpTimeTable = (ExpandableListView) rootView.findViewById(R.id.lvExpTimeTable);

        getTimeTableData();
    }

    public void setListners() {
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DashBoardActivity.onLeft();
            }
        });

        btnBackTimeTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new HomeFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .setCustomAnimations(0,0)
                        .replace(R.id.frame_container, fragment).commit();
            }
        });
        lvExpTimeTable.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpandedPosition != -1
                        && groupPosition != lastExpandedPosition) {
                    lvExpTimeTable.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = groupPosition;
            }
        });
    }

    public void getTimeTableData(){
        if(Utility.isNetworkConnected(mContext)) {
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
                        getTeacherGetTimetableAsyncTask = new GetTeacherGetTimetableAsyncTask(params);
                        timetableModels = getTeacherGetTimetableAsyncTask.execute().get();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                                if (timetableModels.size() > 0) {
                                    txtNoRecordsTimetable.setVisibility(View.GONE);
                                    prepaareList();
                                    listAdapterTimeTable = new ExpandableListAdapterTimeTable(getActivity(), listDataHeader, listDataChild);
                                    lvExpTimeTable.setAdapter(listAdapterTimeTable);
                                } else {
                                    progressDialog.dismiss();
                                    txtNoRecordsTimetable.setVisibility(View.VISIBLE);
                                }
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }else{
            Utility.ping(mContext,"Network not available");
        }
    }

    public void prepaareList(){
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, ArrayList<TeacherGetTimetableModel.TimeTable.TimeTableData>>();

        for(int i = 0;i < timetableModels.get(0).getGetTimeTable().size();i++){
            listDataHeader.add(timetableModels.get(0).getGetTimeTable().get(i).getDay());
            ArrayList<TeacherGetTimetableModel.TimeTable.TimeTableData> rows = new ArrayList<TeacherGetTimetableModel.TimeTable.TimeTableData>();
            for(int j = 0;j < timetableModels.get(0).getGetTimeTable().get(i).getGetTimeTableData().size();j++){

                rows.add(timetableModels.get(0).getGetTimeTable().get(i).getGetTimeTableData().get(j));

            }
            listDataChild.put(listDataHeader.get(i), rows);
        }
    }
}
