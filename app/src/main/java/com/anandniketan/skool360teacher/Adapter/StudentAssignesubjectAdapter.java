package com.anandniketan.skool360teacher.Adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.TextView;

import com.anandniketan.skool360teacher.Models.StudentAssignSubjectResponse.FinalArrayStudentSubject;
import com.anandniketan.skool360teacher.Models.StudentAssignSubjectResponse.MainResponseStudentSubject;
import com.anandniketan.skool360teacher.Models.StudentAssignSubjectResponse.StudentSubject;
import com.anandniketan.skool360teacher.Models.TeacherAssignedSubjectModel;
import com.anandniketan.skool360teacher.Models.TeacherGetAssignStudentSubjectmModel;
import com.anandniketan.skool360teacher.R;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by admsandroid on 9/25/2017.
 */

public class StudentAssignesubjectAdapter extends BaseAdapter {
    private Context mContext;
    private MainResponseStudentSubject studentsubjectarrayList;
    private ArrayList<String> studentnamelist;
    private StudentGridsubjectAdapter adapter;

    // Constructor
    public StudentAssignesubjectAdapter(Context c, ArrayList<String> studentnamelist, MainResponseStudentSubject studentsubjectarrayList) {
        mContext = c;
        this.studentsubjectarrayList = studentsubjectarrayList;
        this.studentnamelist = studentnamelist;
    }

    private class ViewHolder {
        TextView txt_studentName;
        GridView subject_grid_view;
    }

    @Override
    public int getCount() {
        return studentnamelist.size();
    }

    @Override
    public Object getItem(int position) {
        return studentsubjectarrayList.getFinalArray().get(position);
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
            convertView = mInflater.inflate(R.layout.list_row_student_assign, null);
            viewHolder.txt_studentName = (TextView) convertView.findViewById(R.id.txt_studentName);
            viewHolder.subject_grid_view = (GridView) convertView.findViewById(R.id.subject_grid_view);


            try {
                viewHolder.txt_studentName.setText(studentnamelist.get(position));
                adapter = new StudentGridsubjectAdapter(mContext, studentsubjectarrayList.getFinalArray().get(position));
                viewHolder.subject_grid_view.setAdapter(adapter);
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

