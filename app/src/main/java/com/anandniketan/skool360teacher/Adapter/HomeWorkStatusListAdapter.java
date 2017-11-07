package com.anandniketan.skool360teacher.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.anandniketan.skool360teacher.Models.FinalArrayhomeworkstatus;
import com.anandniketan.skool360teacher.Models.StaffAttendanceModel;
import com.anandniketan.skool360teacher.Models.StudentAssignSubjectResponse.StudentSubject;
import com.anandniketan.skool360teacher.Models.TeacherGetTestMarksModel;
import com.anandniketan.skool360teacher.Models.TeacherStudentHomeworkStatusModel;
import com.anandniketan.skool360teacher.R;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by admsandroid on 11/7/2017.
 */

public class HomeWorkStatusListAdapter extends BaseAdapter {
    private Context mContext;
    private TeacherStudentHomeworkStatusModel teacherStudentHomeworkStatusModel;

    // Constructor
    public HomeWorkStatusListAdapter(Context c, TeacherStudentHomeworkStatusModel teacherStudentHomeworkStatusResponse) {
        mContext = c;
        this.teacherStudentHomeworkStatusModel = teacherStudentHomeworkStatusModel;

    }

    private class ViewHolder {
        private TextView homework_status_student_name_txt;
        private CheckBox done_chk, pendding_chk;


    }

    @Override
    public int getCount() {
        return teacherStudentHomeworkStatusModel.getFinalArray().size();
    }

    @Override
    public Object getItem(int position) {
        return teacherStudentHomeworkStatusModel.getFinalArray().get(position);
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
            convertView = mInflater.inflate(R.layout.list_adapter_item, null);

            viewHolder.homework_status_student_name_txt = (TextView) convertView.findViewById(R.id.homework_status_student_name_txt);
            viewHolder.done_chk = (CheckBox) convertView.findViewById(R.id.done_chk);
            viewHolder.pendding_chk = (CheckBox) convertView.findViewById(R.id.pendding_chk);

            final FinalArrayhomeworkstatus subjObj = teacherStudentHomeworkStatusModel.getFinalArray().get(position);
            try {

                viewHolder.homework_status_student_name_txt.setText(subjObj.getStudentName());
                switch (Integer.parseInt(subjObj.getHomeWorkStatus())) {
                    case 0:
                        viewHolder.pendding_chk.setChecked(true);
                        break;
                    case 1:
                        viewHolder.done_chk.setChecked(true);
                        break;
                    case -1:
                        viewHolder.done_chk.setChecked(true);
                        viewHolder.done_chk.setClickable(false);
                    default:
                }

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

