package com.anandniketan.skool360teacher.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.anandniketan.skool360teacher.Utility.AppConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admsandroid on 9/18/2017.
 */

public class Pager extends FragmentStatePagerAdapter {
    //integer to count number of tabs
    private final List<Fragment> mFragmentList;
    private final List<String> getmFragmentstdid;
    private final List<String> getmFragmentclsid;
    private final List<String> mFragmentTitleList;

    //Constructor to the class
    public Pager(FragmentManager fm) {
        super(fm);
        mFragmentList = new ArrayList<>();
        mFragmentTitleList = new ArrayList<>();
        getmFragmentstdid = new ArrayList<>();
        getmFragmentclsid = new ArrayList<>();
    }

    public Fragment getItem(int position) {
//Returning the current tabs
        return mFragmentList.get(position);
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFrag(Fragment fragment, String title, String studentid, String classid) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
        getmFragmentstdid.add(studentid);
        getmFragmentclsid.add(classid);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }


}

