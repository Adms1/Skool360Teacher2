package com.anandniketan.skool360teacher.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.anandniketan.skool360teacher.Adapter.TodayscheduleAdapter;
import com.anandniketan.skool360teacher.AsyncTasks.GetTeacherTodayScheduleAsyncTask;
import com.anandniketan.skool360teacher.Models.TeacherTodayScheduleModel;
import com.anandniketan.skool360teacher.R;
import com.anandniketan.skool360teacher.Utility.Utility;

import java.util.ArrayList;
import java.util.HashMap;

public class LessonplanscheduleFragment extends Fragment {

    public LessonplanscheduleFragment() {
        // Required empty public constructor
    }

    private Context mContext;
    private View rootView;
    private ProgressDialog progressDialog = null;
    private LinearLayout header_linear;
    private TextView txtNoRecords;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_lessonplanschedule, container, false);
        mContext = getActivity();

        init();
        setListner();
        return rootView;
    }

    public void init() {


        header_linear = (LinearLayout) rootView.findViewById(R.id.header_linear);
        txtNoRecords = (TextView) rootView.findViewById(R.id.txtNoRecords);
        setUserVisibleHint(false);
    }

    public void setListner() {
    }



}
