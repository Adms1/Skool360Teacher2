package com.anandniketan.skool360teacher.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.anandniketan.skool360teacher.Activities.DashBoardActivity;
import com.anandniketan.skool360teacher.Adapter.SchedulepagerAdapter;
import com.anandniketan.skool360teacher.Adapter.TestMainAdapter;
import com.anandniketan.skool360teacher.R;

public class TestMainFragment extends Fragment {
    private View rootView;
    private Button btnMenu, btnBacktest_main;

    private TabLayout tabLayout_test_main;
    private ViewPager viewPager;
    private Context mContext;


    public TestMainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_test_main, container, false);
        mContext = getActivity();
        init();
        setListner();
        return rootView;

    }

    public void init() {
//Initializing the tablayout

        btnMenu = (Button) rootView.findViewById(R.id.btnMenu);
        btnBacktest_main= (Button) rootView.findViewById(R.id.btnBacktest_main);
        viewPager = (ViewPager) rootView.findViewById(R.id.pager);


        tabLayout_test_main = (TabLayout) rootView.findViewById(R.id.tabLayout_test_main);
        tabLayout_test_main.addTab(tabLayout_test_main.newTab().setText("Edit Test"),true);
        tabLayout_test_main.addTab(tabLayout_test_main.newTab().setText(Html.fromHtml("Add Test")));
        tabLayout_test_main.setTabMode(TabLayout.MODE_FIXED);
        tabLayout_test_main.setTabGravity(TabLayout.GRAVITY_FILL);


        TestMainAdapter adapter = new TestMainAdapter(getFragmentManager(), tabLayout_test_main.getTabCount());
//Adding adapter to pager
        viewPager.setAdapter(adapter);
//        tabLayout_schedule.setupWithViewPager(viewPager);
    }

    public void setListner() {
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DashBoardActivity.onLeft();
            }
        });
        btnBacktest_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new HomeFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .setCustomAnimations(0, 0)
                        .replace(R.id.frame_container, fragment).commit();
            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(
                tabLayout_test_main));
        tabLayout_test_main.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
