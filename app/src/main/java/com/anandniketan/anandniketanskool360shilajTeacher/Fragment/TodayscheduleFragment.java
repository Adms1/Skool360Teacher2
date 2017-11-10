package com.anandniketan.anandniketanskool360shilajTeacher.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.anandniketan.anandniketanskool360shilajTeacher.Adapter.TodayscheduleAdapter;
import com.anandniketan.anandniketanskool360shilajTeacher.AsyncTasks.GetTeacherTodayScheduleAsyncTask;
import com.anandniketan.anandniketanskool360shilajTeacher.Models.TeacherTodayScheduleModel;
import com.anandniketan.anandniketanskool360shilajTeacher.R;
import com.anandniketan.anandniketanskool360shilajTeacher.Utility.Utility;

import java.util.ArrayList;
import java.util.HashMap;


public class TodayscheduleFragment extends Fragment {

    public TodayscheduleFragment() {
        // Required empty public constructor
    }

    private Context mContext;
    private View rootView;
    private ProgressDialog progressDialog = null;
    private GetTeacherTodayScheduleAsyncTask getTeacherTodayScheduleAsyncTask = null;
    private ArrayList<TeacherTodayScheduleModel> teacherTodayScheduleModels = new ArrayList<>();
    TodayscheduleAdapter todayscheduleAdapter = null;
    private ListView schedule_list;
    private LinearLayout header_linear;
    private TextView txtNoRecords;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_todayschedule, container, false);
        mContext = getActivity();

        init();
        setListner();


        return rootView;
    }

    public void init() {


        header_linear = (LinearLayout) rootView.findViewById(R.id.header_linear);
        txtNoRecords = (TextView) rootView.findViewById(R.id.txtNoRecords);
        schedule_list = (ListView) rootView.findViewById(R.id.schedule_list);

        setUserVisibleHint(true);
    }

    public void setListner() {
    }
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && rootView != null) {
            setTodayschedule();
        }
        // execute your data loading logic.
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

                        getTeacherTodayScheduleAsyncTask = new GetTeacherTodayScheduleAsyncTask(params);
                        teacherTodayScheduleModels = getTeacherTodayScheduleAsyncTask.execute().get();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                                if (teacherTodayScheduleModels.size() > 0) {
                                    txtNoRecords.setVisibility(View.GONE);

                                    todayscheduleAdapter = new TodayscheduleAdapter(getActivity(), teacherTodayScheduleModels);
                                    schedule_list.setAdapter(todayscheduleAdapter);
                                    schedule_list.deferNotifyDataSetChanged();
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
