package com.anandniketan.skool360teacher.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.anandniketan.skool360teacher.Fragment.LessonplanscheduleFragment;
import com.anandniketan.skool360teacher.Fragment.TodayscheduleFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admsandroid on 9/22/2017.
 */

public class SchedulepagerAdapter extends FragmentStatePagerAdapter {
    //integer to count number of tabs
    int tabCount;

    //Constructor to the class
    public SchedulepagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
//Initializing tab count
        this.tabCount = tabCount;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
//Returning the current tabs
        switch (position) {
            case 0:
                TodayscheduleFragment tab1 = new TodayscheduleFragment();
                return tab1;
//            case 1:
//                LessonplanscheduleFragment tab2 = new LessonplanscheduleFragment();
//                return tab2;
            default:
                return null;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }
}


