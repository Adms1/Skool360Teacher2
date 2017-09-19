package com.anandniketan.skool360teacher.Adapter;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.anandniketan.skool360teacher.Models.StaffAttendanceModel;
import com.anandniketan.skool360teacher.R;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.ArrayList;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by admsandroid on 9/19/2017.
 */

public class AttendanceAdapter extends BaseAdapter {
    private Context mContext;
    //    private HashMap<String, ArrayList<StaffAttendanceModel.AttendanceDetails.StudentDetails>> _listDataChild;
    private CircleImageView profile_image;
    private TextView student_name_txt;
    private TextView present_chk, absent_chk, leave_chk;
    ImageLoader imageLoader;
    private ArrayList<StaffAttendanceModel> staffattendaceModel = new ArrayList<>();

    // Constructor
    public AttendanceAdapter(Context c, ArrayList<StaffAttendanceModel> staffattendaceModel) {
        mContext = c;
        this.staffattendaceModel = staffattendaceModel;
    }


    @Override
    public int getCount() {
        return staffattendaceModel.size();
    }

    @Override
    public Object getItem(int position) {
        return staffattendaceModel.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.list_row_student_attendance, null);
            profile_image = (CircleImageView) convertView.findViewById(R.id.profile_image);
            student_name_txt = (TextView) convertView.findViewById(R.id.student_name_txt);
            present_chk = (RadioButton) convertView.findViewById(R.id.present_chk);
            absent_chk = (RadioButton) convertView.findViewById(R.id.absent_chk);
            leave_chk = (RadioButton) convertView.findViewById(R.id.leave_chk);


            imageLoader = ImageLoader.getInstance();
            DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .imageScaleType(ImageScaleType.EXACTLY)
                    .displayer(new FadeInBitmapDisplayer(300))
                    .build();
            ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                    mContext)
                    .threadPriority(Thread.MAX_PRIORITY)
                    .defaultDisplayImageOptions(defaultOptions)
                    .memoryCache(new WeakMemoryCache())
                    .denyCacheImageMultipleSizesInMemory()
                    .tasksProcessingOrder(QueueProcessingType.LIFO)// .enableLogging()
                    .build();
            imageLoader.init(config.createDefault(mContext));
            imageLoader.displayImage(staffattendaceModel.get(position).getAttendanceList().get(position).getStudentList().get(position).getStudentImage(), profile_image);
            student_name_txt.setText(staffattendaceModel.get(position).getAttendanceList().get(position).getStudentList().get(position).getStudentName());


        }
        return convertView;
    }

}

