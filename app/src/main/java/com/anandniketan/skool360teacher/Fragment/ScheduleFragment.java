package com.anandniketan.skool360teacher.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.anandniketan.skool360teacher.Activities.DashBoardActivity;
import com.anandniketan.skool360teacher.Adapter.Pager;
import com.anandniketan.skool360teacher.Adapter.SchedulepagerAdapter;
import com.anandniketan.skool360teacher.R;
import com.anandniketan.skool360teacher.Utility.AppConfiguration;

import java.util.ArrayList;

public class ScheduleFragment extends Fragment {
    private View rootView;
    private Button btnMenu, btnBackSchedule;

    private TabLayout tabLayout_schedule;
    private ViewPager viewPager;
    private Context mContext;
    private ArrayList<String> tabArray;


    public ScheduleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_schedule, container, false);
        mContext = getActivity();
        init();
        setListner();
        return rootView;

    }

    public void init() {
//Initializing the tablayout

        btnMenu = (Button) rootView.findViewById(R.id.btnMenu);
        btnBackSchedule = (Button) rootView.findViewById(R.id.btnBackSchedule);
        viewPager = (ViewPager) rootView.findViewById(R.id.pager);


        tabLayout_schedule = (TabLayout) rootView.findViewById(R.id.tabLayout_schedule);

        tabLayout_schedule.addTab(tabLayout_schedule.newTab().setText("Today Schedule"), true);
//        tabLayout_schedule.addTab(tabLayout_schedule.newTab().setText("Lesson Plan Schedule"));
        tabLayout_schedule.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout_schedule.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout_schedule.setupWithViewPager(viewPager);
        SchedulepagerAdapter adapter = new SchedulepagerAdapter(getFragmentManager(), tabLayout_schedule.getTabCount());
//Adding adapter to pager
        viewPager.setAdapter(adapter);

    }

    public void setListner() {
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DashBoardActivity.onLeft();
            }
        });
        btnBackSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new HomeFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .setCustomAnimations(0, 0)
                        .replace(R.id.frame_container, fragment).commit();
            }
        });

    }
}
