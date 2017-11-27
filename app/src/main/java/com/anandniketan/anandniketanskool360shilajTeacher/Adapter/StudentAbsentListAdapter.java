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

import com.anandniketan.anandniketanskool360shilajTeacher.Interfacess.getCheckconsistentAb;
import com.anandniketan.anandniketanskool360shilajTeacher.Models.AllAttendance.FinalArray_ConsistentAb;
import com.anandniketan.anandniketanskool360shilajTeacher.Models.AllAttendance.GetConsistentAbModel;
import com.anandniketan.anandniketanskool360shilajTeacher.Models.AllAttendance.GetStudentAbModel;
import com.anandniketan.anandniketanskool360shilajTeacher.R;

import java.util.ArrayList;

/**
 * Created by admsandroid on 11/27/2017.
 */

public class StudentAbsentListAdapter {//extends BaseAdapter {
//    private Context mContext;
//    private ArrayList<String> dataCheck = new ArrayList<String>();
//    private ArrayList<String> mobiledata = new ArrayList<>();
//    private getCheckconsistentAb listner;
//    private GetStudentAbModel getStudentAbModel;
//
//    // Constructor
//    public StudentAbsentListAdapter(Context c, GetStudentAbModel getStudentAbModel, getCheckconsistentAb listner) {
//        mContext = c;
//        this.getStudentAbModel = getStudentAbModel;
//        this.listner = listner;
//    }
//
//    private class ViewHolder {
//        TextView student_name_txt, Standard_txt, sms_txt;
//        CheckBox edit_chk;
//    }
//
//    @Override
//    public int getCount() {
//        return getStudentAbModel.getFinalArray().size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return getStudentAbModel.getFinalArray().get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return 0;
//    }
//
//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent) {
//        final ViewHolder viewHolder;
//        View view = null;
//        convertView = null;
//        if (convertView == null) {
//            viewHolder = new ViewHolder();
//            final LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
//            convertView = mInflater.inflate(R.layout.list_row_student_absent, null);
//
//            viewHolder.student_name_txt = (TextView) convertView.findViewById(R.id.student_name_txt);
//            viewHolder.Standard_txt = (TextView) convertView.findViewById(R.id.Standard_txt);
//            viewHolder.sms_txt = (TextView) convertView.findViewById(R.id.sms_txt);
//            viewHolder.edit_chk = (CheckBox) convertView.findViewById(R.id.edit_chk);
//            convertView.setTag(viewHolder);
//
//        }else{
//            viewHolder = (ViewHolder) convertView.getTag();
//        }
//        final FinalArray_ConsistentAb detail = getStudentAbModel.getFinalArray().get(position);
//
//        try {
//            viewHolder.student_name_txt.setText(detail.getStudent());
//            viewHolder.Standard_txt.setText(detail.getStandard());
//            viewHolder.sms_txt.setText(detail.getSmsNo());
//
//            viewHolder.edit_chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    String checkvalue, mobilenovalue;
//
//                    if (isChecked) {
//                        detail.setCheckstatus("1");
//                        checkvalue = detail.getPkStudentID().toString();
//                        dataCheck.add(checkvalue);
//                        Log.d("dataCheck", dataCheck.toString());
//                        mobilenovalue = detail.getSmsNo().toString();
//                        mobiledata.add(mobilenovalue);
//                        Log.d("mobiledata", mobiledata.toString());
//                        listner.getCheckconsistentAb();
//                    } else {
//                        detail.setCheckstatus("0");
//                        dataCheck.remove(detail.getPkStudentID().toString());
//                        mobilenovalue = detail.getSmsNo().toString();
//                        mobiledata.remove(mobilenovalue);
//                        Log.d("dataUnCheck", dataCheck.toString());
//                        Log.d("mobiledata", mobiledata.toString());
//                        listner.getCheckconsistentAb();
//                    }
//                }
//            });
//            if (detail.getCheckstatus().equalsIgnoreCase("1")) {
//                viewHolder.edit_chk.setChecked(true);
//            } else {
//                viewHolder.edit_chk.setChecked(false);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//        return convertView;
//    }
//
//    public ArrayList<String> getData() {
//        return dataCheck;
//    }
//
//    public ArrayList<String> getMobileData() {
//        return mobiledata;
//    }
}




