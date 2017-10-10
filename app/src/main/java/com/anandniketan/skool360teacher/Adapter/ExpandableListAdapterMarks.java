package com.anandniketan.skool360teacher.Adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.anandniketan.skool360teacher.Models.TeacherGetTestMarksModel;
import com.anandniketan.skool360teacher.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by admsandroid on 9/27/2017.
 */

public class ExpandableListAdapterMarks extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, ArrayList<TeacherGetTestMarksModel.studentDetail.TestDetail.subjectMarks>> _listDataChild;

    public ExpandableListAdapterMarks(Context context, List<String> listDataHeader,
                                      HashMap<String, ArrayList<TeacherGetTestMarksModel.studentDetail.TestDetail.subjectMarks>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public TeacherGetTestMarksModel.studentDetail.TestDetail.subjectMarks getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        LayoutInflater infalInflater = (LayoutInflater) this._context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (childPosition > 0 && childPosition < getChildrenCount(groupPosition) - 1) {

            TeacherGetTestMarksModel.studentDetail.TestDetail.subjectMarks currentchild = getChild(groupPosition, childPosition - 1);
            convertView = infalInflater.inflate(R.layout.list_item_marks, null);

            TextView txtSubject, txtMarks;
            txtSubject = (TextView) convertView.findViewById(R.id.txtSubject);
            txtMarks = (TextView) convertView.findViewById(R.id.txtMarks);

            txtSubject.setText(currentchild.getSubject());
            txtMarks.setText(currentchild.getMarks());

        } else if (childPosition == getChildrenCount(groupPosition) - 1) {
            convertView = infalInflater.inflate(R.layout.marks_footer, null);
            TextView txttotal_footer, txtgain_footer;
            txttotal_footer = (TextView) convertView.findViewById(R.id.txttotal_footer);
            txtgain_footer = (TextView) convertView.findViewById(R.id.txtgain_footer);

        } else {
            convertView = infalInflater.inflate(R.layout.list_item_marks_header, null);
            TextView txtSubject_header, txtMarks_header;
            txtSubject_header = (TextView) convertView.findViewById(R.id.txtSubject_header);
            txtMarks_header = (TextView) convertView.findViewById(R.id.txtMarks_header);
        }
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition)).size() + 2;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String[] headerTitle = getGroup(groupPosition).toString().split("\\|");

        String headerTitle1 = headerTitle[0];
        String headerTitle2 = headerTitle[1];
        String headerTitle3 = headerTitle[2];

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group_marks, null);
        }
        TextView Student_name_txt, gr_no_txt, percentage_txt, view_txt;
        Student_name_txt = (TextView) convertView.findViewById(R.id.Student_name_txt);
        gr_no_txt = (TextView) convertView.findViewById(R.id.gr_no_txt);
        percentage_txt = (TextView) convertView.findViewById(R.id.percentage_txt);
        view_txt = (TextView) convertView.findViewById(R.id.view_txt);

        Student_name_txt.setText(headerTitle1);
        gr_no_txt.setText(headerTitle2);
        percentage_txt.setText(headerTitle3);

        if (isExpanded) {
            view_txt.setTextColor(_context.getResources().getColor(R.color.present_header));
        } else {
            view_txt.setTextColor(_context.getResources().getColor(R.color.absent_header));
        }


        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

