package com.anandniketan.skool360teacher.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.anandniketan.skool360teacher.Models.TeacherGetTimetableModel;
import com.anandniketan.skool360teacher.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by admsandroid on 9/25/2017.
 */

public class ExpandableListAdapterTimeTable extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, ArrayList<TeacherGetTimetableModel.TimeTable.TimeTableData>> _listDataChild;

    public ExpandableListAdapterTimeTable(Context context, List<String> listDataHeader,
                                          HashMap<String, ArrayList<TeacherGetTimetableModel.TimeTable.TimeTableData>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public TeacherGetTimetableModel.TimeTable.TimeTableData getChild(int groupPosition, int childPosititon) {
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
        LayoutInflater infalInflater = null;
        if (convertView == null) {
            infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item_timetable, null);
        }

            TextView txtLecture, txtSubject, txtclass;

            txtLecture = (TextView) convertView.findViewById(R.id.txtLecture);
            txtSubject = (TextView) convertView.findViewById(R.id.txtSubject);
            txtclass = (TextView) convertView.findViewById(R.id.txtClass);

            txtLecture.setText(getChild(groupPosition, childPosition).getLecture());

            if (!getChild(groupPosition, childPosition).getSubject().equalsIgnoreCase("")) {
                txtSubject.setText(getChild(groupPosition, childPosition).getSubject());
            } else {
                txtSubject.setText("-");
            }
            if (!getChild(groupPosition, childPosition).getStandardClass().equalsIgnoreCase("")) {
                txtclass.setText(getChild(groupPosition, childPosition).getStandardClass());
            } else {
                txtclass.setText("-");
            }
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
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
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group_timetable, null);
        }
        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        if (isExpanded) {
            lblListHeader.setTextColor(R.color.present_header);
        } else {
            lblListHeader.setTextColor(R.color.orange);
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
