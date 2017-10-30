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

/**
 * Created by admsandroid on 9/25/2017.
 */

public class StudentAssignesubjectAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<StudentSubject> studentsubjectarrayList = new ArrayList<>();
    private ArrayList<String> studentnamelist = new ArrayList<String>();
    MainResponseStudentSubject mainResponseStudentSubject;

    // Constructor
    public StudentAssignesubjectAdapter(Context c, ArrayList<String> studentnamelist, MainResponseStudentSubject mainResponseStudentSubject) {
        mContext = c;
        this.studentnamelist = studentnamelist;
        this.mainResponseStudentSubject = mainResponseStudentSubject;
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
        return studentnamelist.get(position);
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
                StudentGridsubjectAdapter adapter = new StudentGridsubjectAdapter(mContext, mainResponseStudentSubject.getFinalArray().get(position));
                viewHolder.subject_grid_view.setAdapter(adapter);
                viewHolder.subject_grid_view.deferNotifyDataSetChanged();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return convertView;
    }
}

class StudentGridsubjectAdapter extends BaseAdapter {
    private Context mContext;
    private FinalArrayStudentSubject studentsubjectarrayList;

    // Constructor
    public StudentGridsubjectAdapter(Context c, FinalArrayStudentSubject studentsubjectarrayList) {
        mContext = c;
        this.studentsubjectarrayList = studentsubjectarrayList;
    }

    private class ViewHolder {
        CheckBox check_subject;
    }

    @Override
    public int getCount() {
        return studentsubjectarrayList.getStudentSubject().size();
    }

    @Override
    public Object getItem(int position) {
        return studentsubjectarrayList.getStudentSubject().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        View view = null;
        convertView = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.list_row_student_checkbox, null);
            viewHolder.check_subject = (CheckBox) convertView.findViewById(R.id.check_subject);
            StudentSubject subjObj = studentsubjectarrayList.getStudentSubject().get(position);
            try {

                viewHolder.check_subject.setText(subjObj.getSubject());
                viewHolder.check_subject.setTag(subjObj.getSubjectID());

                if (subjObj.getCheckedStatus().equalsIgnoreCase("1")) {
                    viewHolder.check_subject.setChecked(true);
                }

                viewHolder.check_subject.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked){
                            Log.d("checkvalue", viewHolder.check_subject.getTag().toString());
                        }
                    }
                });

//                convertView.setLayoutParams(new GridView.LayoutParams(GridView.AUTO_FIT, 150));

//                GridView.LayoutParams glp = (GridView.LayoutParams) convertView.getLayoutParams();
//                glp.height = (subjObj.getSubject() * 30) + 10;
//                convertView.setLayoutParams(glp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return convertView;
    }
}

