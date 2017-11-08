package com.anandniketan.skool360teacher.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.anandniketan.skool360teacher.Models.Attendance.StaffNewAttendenceModel;
import com.anandniketan.skool360teacher.Models.Attendance.StudentDetailAttedance;
import com.anandniketan.skool360teacher.R;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by admsandroid on 11/8/2017.
 */

public class AddTestDetalisListAdapter extends BaseAdapter {
    private Context mContext;
    ArrayList<String> number;
    // Constructor
    public AddTestDetalisListAdapter(Context c, ArrayList<String> number) {
        mContext = c;
        this.number = number;

    }

    private class ViewHolder {
        TextView syllbus_edt;

    }

    @Override
    public int getCount() {
        return number.size();
    }

    @Override
    public Object getItem(int position) {
        return number.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        View view = null;
        convertView = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.list_edittext, null);
            viewHolder.syllbus_edt = (EditText) convertView.findViewById(R.id.syllabus_txt);


            try {

            } catch (Exception e) {
                e.printStackTrace();
            }
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }
}



