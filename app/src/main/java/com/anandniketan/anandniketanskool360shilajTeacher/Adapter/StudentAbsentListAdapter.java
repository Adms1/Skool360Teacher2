package com.anandniketan.anandniketanskool360shilajTeacher.Adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.anandniketan.anandniketanskool360shilajTeacher.Interfacess.getStudentAbCheck;
import com.anandniketan.anandniketanskool360shilajTeacher.Models.AllAttendance.FinalArrayAbsentStudentModel;
import com.anandniketan.anandniketanskool360shilajTeacher.Models.AllAttendance.GetDateWiseAbsentStudentModel;
import com.anandniketan.anandniketanskool360shilajTeacher.R;

import java.util.ArrayList;

/**
 * Created by admsandroid on 11/27/2017.
 */

public class StudentAbsentListAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<String> dataCheck = new ArrayList<String>();
    private ArrayList<String> mobiledata = new ArrayList<String>();
    String checkvalue, mobilenovalue, arrayvalue;
    private getStudentAbCheck listner;
    private GetDateWiseAbsentStudentModel getStudentAbModel;


    // Constructor
    public StudentAbsentListAdapter(Context c, GetDateWiseAbsentStudentModel getStudentAbModel, getStudentAbCheck listner) {
        mContext = c;
        this.getStudentAbModel = getStudentAbModel;
        this.listner = listner;
    }

    private class ViewHolder {
        TextView name_txt, standard_txt, section_txt,sr_txt;
        CheckBox edit_chk;
    }

    @Override
    public int getCount() {
        return getStudentAbModel.getFinalArray().size();
    }

    @Override
    public Object getItem(int position) {
        return getStudentAbModel.getFinalArray().get(position);
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
            final LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.list_row_student_absent, null);
            viewHolder.name_txt = (TextView) convertView.findViewById(R.id.name_txt);
            viewHolder.standard_txt = (TextView) convertView.findViewById(R.id.standard_txt);
            viewHolder.section_txt = (TextView) convertView.findViewById(R.id.section_txt);
            viewHolder.edit_chk = (CheckBox) convertView.findViewById(R.id.edit_chk);
            viewHolder.sr_txt=(TextView)convertView.findViewById(R.id.sr_txt);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final FinalArrayAbsentStudentModel detail = getStudentAbModel.getFinalArray().get(position);
        String sr = String.valueOf(position + 1);


        try {
            viewHolder.sr_txt.setText(sr);
            viewHolder.name_txt.setText(detail.getStudentName());
            viewHolder.standard_txt.setText(detail.getStandard());
            viewHolder.section_txt.setText(detail.getSection());


            viewHolder.edit_chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        detail.setCheckboxStatus("1");
                        checkvalue = detail.getStudentID().toString();
                        mobilenovalue = detail.getSmsNo().toString();
                        arrayvalue = checkvalue + "|" + mobilenovalue;
                        dataCheck.add(arrayvalue);
                        Log.d("dataCheck", dataCheck.toString());
                        mobiledata.add(mobilenovalue);
                        Log.d("mobiledata", mobiledata.toString());
                        listner.getStudentAbCheck();
                    } else {
                        detail.setCheckboxStatus("0");
                        dataCheck.remove(detail.getStudentID().toString() + "|" + detail.getSmsNo().toString());
                        Log.d("dataUnCheck", dataCheck.toString());
                        listner.getStudentAbCheck();
                    }
                }
            });
            if (detail.getCheckboxStatus().equalsIgnoreCase("1")) {
                viewHolder.edit_chk.setChecked(true);
            } else {
                viewHolder.edit_chk.setChecked(false);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        return convertView;
    }

    public ArrayList<String> getData1() {
        return dataCheck;
    }

    public GetDateWiseAbsentStudentModel getData() {
        return getStudentAbModel;
    }

    public ArrayList<String> getMobileData() {
        return mobiledata;
    }
}




