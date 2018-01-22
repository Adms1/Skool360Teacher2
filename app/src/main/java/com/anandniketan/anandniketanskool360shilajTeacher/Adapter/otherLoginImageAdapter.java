package com.anandniketan.anandniketanskool360shilajTeacher.Adapter;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
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
 * Created by admsandroid on 11/15/2017.
 */

public class otherLoginImageAdapter extends BaseAdapter {
    private Context mContext;

    public String[] mThumbIds = {
            AppConfiguration.DOMAIN_LIVE_IMAGES + "consistent_absent.png",//consistent_absent.png
            AppConfiguration.DOMAIN_LIVE_IMAGES +"student_absent.png",//student_absent.png
            AppConfiguration.DOMAIN_LIVE_IMAGES +"Attendance.png",
    };
    public String[] mThumbNames = {"Consistent Absent","Student Absent","Attendance",};

    // Constructor
    public otherLoginImageAdapter(Context c) {
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

