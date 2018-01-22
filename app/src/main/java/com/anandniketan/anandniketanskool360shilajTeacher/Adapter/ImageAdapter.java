package com.anandniketan.anandniketanskool360shilajTeacher.Adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.anandniketan.anandniketanskool360shilajTeacher.R;
import com.anandniketan.anandniketanskool360shilajTeacher.Utility.AppConfiguration;
import com.bumptech.glide.Glide;


/**
 * Created by admsandroid on 9/20/2017.
 */

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public String[] mThumbIds = {
            AppConfiguration.DOMAIN_LIVE_IMAGES +"Schedule.png",
            AppConfiguration.DOMAIN_LIVE_IMAGES +"Subjects.png",
            AppConfiguration.DOMAIN_LIVE_IMAGES +"Time%20Table.png",
            AppConfiguration.DOMAIN_LIVE_IMAGES +"Attendance.png",
            AppConfiguration.DOMAIN_LIVE_IMAGES +"Lesson%20Plan.png",
            AppConfiguration.DOMAIN_LIVE_IMAGES +"Home%20Work.png",
            AppConfiguration.DOMAIN_LIVE_IMAGES +"Test_Syllabus.png",
            AppConfiguration.DOMAIN_LIVE_IMAGES +"Marks.png",
            AppConfiguration.DOMAIN_LIVE_IMAGES +"PTM.png"
    };

    public String[] mThumbNames = {"Schedule", "Subjects", "Time Table", "Attendence", "Lesson Plan", "Home Work",
            "Test/Syllabus", "Marks", "PTM"};

    // Constructor
    public ImageAdapter(Context c) {
        mContext = c;
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return mThumbIds[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imgGridOptions = null;
        TextView txtGridOptionsName = null;

        LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        convertView = mInflater.inflate(R.layout.grid_cell, null);

        imgGridOptions = (ImageView) convertView.findViewById(R.id.imgGridOptions);
        txtGridOptionsName = (TextView) convertView.findViewById(R.id.txtGridOptionsName);

//        imgGridOptions.setImageResource(mThumbIds[position]);
        String url = mThumbIds[position];
        Log.d("url", url);

        Glide.with(mContext)
                .load(url)
                .fitCenter()
                .into(imgGridOptions);
        txtGridOptionsName.setText(mThumbNames[position]);
        return convertView;
    }

}