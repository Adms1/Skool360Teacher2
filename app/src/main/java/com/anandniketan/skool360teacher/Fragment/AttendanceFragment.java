package com.anandniketan.skool360teacher.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.anandniketan.skool360teacher.Adapter.Pager;
import com.anandniketan.skool360teacher.AsyncTasks.GetStaffAttendanceAsyncTask;
import com.anandniketan.skool360teacher.Models.StaffAttendanceModel;
import com.anandniketan.skool360teacher.R;
import com.anandniketan.skool360teacher.Utility.AppConfiguration;

import java.util.ArrayList;

public class AttendanceFragment extends Fragment {
    private View rootView;
    private Button btnMenu;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Context mContext;


    public AttendanceFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_attendance, container, false);
        mContext = getActivity();
        init();
        return rootView;

    }

    public void init() {
//Initializing the tablayout
        viewPager = (ViewPager) rootView.findViewById(R.id.pager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) rootView.findViewById(R.id.tabLayout);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {

        Pager adapter = new Pager(getActivity().getSupportFragmentManager());
        LayoutInflater inflator = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for (int i = 0; i < AppConfiguration.rows.size(); i++) {
            Log.d("size", "" + AppConfiguration.rows.size());
            OneFragment fView = new OneFragment();
            View view = fView.getView();

            adapter.addFrag(fView, String.valueOf(AppConfiguration.rows.get(i).getClasses() + "-" + AppConfiguration.rows.get(i).getStandard()));
        }

        viewPager.setAdapter(adapter);
    }


}
