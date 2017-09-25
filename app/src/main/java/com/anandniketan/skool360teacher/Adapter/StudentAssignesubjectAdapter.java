package com.anandniketan.skool360teacher.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.anandniketan.skool360teacher.Models.TeacherAssignedSubjectModel;
import com.anandniketan.skool360teacher.Models.TeacherGetAssignStudentSubjectmModel;
import com.anandniketan.skool360teacher.R;

import java.util.ArrayList;

/**
 * Created by admsandroid on 9/25/2017.
 */

public class StudentAssignesubjectAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<TeacherGetAssignStudentSubjectmModel> teacherGetAssignStudentSubjectmModels = new ArrayList<>();

    // Constructor
    public StudentAssignesubjectAdapter(Context c, ArrayList<TeacherGetAssignStudentSubjectmModel> teacherGetAssignStudentSubjectmModels) {
        mContext = c;
        this.teacherGetAssignStudentSubjectmModels = teacherGetAssignStudentSubjectmModels;

    }

    private class ViewHolder {
        TextView standard_txt, class_txt, subject_txt;
    }

    @Override
    public int getCount() {
        return teacherGetAssignStudentSubjectmModels.size();
    }

    @Override
    public Object getItem(int position) {
        return teacherGetAssignStudentSubjectmModels.get(position);
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
            convertView = mInflater.inflate(R.layout.list_row_mysubject, null);

            viewHolder.standard_txt = (TextView) convertView.findViewById(R.id.standard_txt);
            viewHolder.class_txt = (TextView) convertView.findViewById(R.id.class_txt);
            viewHolder.subject_txt = (TextView) convertView.findViewById(R.id.subject_txt);

            try {


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return convertView;
    }

}



