package com.anandniketan.skool360teacher.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.anandniketan.skool360teacher.Activities.DashBoardActivity;
import com.anandniketan.skool360teacher.Adapter.Pager;
import com.anandniketan.skool360teacher.R;
import com.anandniketan.skool360teacher.Utility.AppConfiguration;

public class AttendanceFragment extends Fragment {
    private View rootView;
    private Button btnMenu, btnBackAttendance;

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
        setListner();
        return rootView;

    }

    public void init() {
//Initializing the tablayout
        btnMenu = (Button) rootView.findViewById(R.id.btnMenu);
        btnBackAttendance = (Button) rootView.findViewById(R.id.btnBackAttendance);
        viewPager = (ViewPager) rootView.findViewById(R.id.pager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) rootView.findViewById(R.id.tabLayout);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(viewPager);

    }

    public void setListner() {
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DashBoardActivity.onLeft();
            }
        });
        btnBackAttendance.setOnClickListener(new View.OnClickListener() {
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


    private void setupViewPager(ViewPager viewPager) {

        Pager adapter = new Pager(getActivity().getSupportFragmentManager());
        LayoutInflater inflator = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for (int i = 0; i < AppConfiguration.rows.size(); i++) {
            Log.d("size", "" + AppConfiguration.rows.size());

            OneFragmentAtt fView = new OneFragmentAtt(i, AppConfiguration.rows.get(i).getClassID(), AppConfiguration.rows.get(i).getStandardID());
            View view = fView.getView();
//            AppConfiguration.stdid = AppConfiguration.rows.get(0).getStandardID();
//            AppConfiguration.clsid = AppConfiguration.rows.get(0).getClassID();
            adapter.addFrag(fView, String.valueOf(AppConfiguration.rows.get(i).getStandard() + "-" + AppConfiguration.rows.get(i).getClasses()));

        }
        viewPager.setAdapter(adapter);
    }


}
