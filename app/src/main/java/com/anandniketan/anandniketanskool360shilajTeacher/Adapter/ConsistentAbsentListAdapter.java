package com.anandniketan.anandniketanskool360shilajTeacher.Adapter;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.anandniketan.anandniketanskool360shilajTeacher.Interfacess.CallBack;
import com.anandniketan.anandniketanskool360shilajTeacher.Interfacess.getCheckconsistentAb;
import com.anandniketan.anandniketanskool360shilajTeacher.Interfacess.onCheckBoxChnage;
import com.anandniketan.anandniketanskool360shilajTeacher.Models.AllAttendance.FinalArray_ConsistentAb;
import com.anandniketan.anandniketanskool360shilajTeacher.Models.AllAttendance.GetConsistentAbModel;
import com.anandniketan.anandniketanskool360shilajTeacher.Models.PTMCreateResponse.StudentDatum;
import com.anandniketan.anandniketanskool360shilajTeacher.R;

import java.util.ArrayList;

/**
 * Created by admsandroid on 11/15/2017.
 */

public class ConsistentAbsentListAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<String> dataCheck = new ArrayList<String>();
    private ArrayList<String> mobiledata = new ArrayList<>();
    private getCheckconsistentAb listner;
    private GetConsistentAbModel getConsistentAbModel;

    // Constructor
    public ConsistentAbsentListAdapter(Context c, GetConsistentAbModel getConsistentAbModel, getCheckconsistentAb listner) {
        mContext = c;
        this.getConsistentAbModel = getConsistentAbModel;
        this.listner = listner;
    }

    private class ViewHolder {
        TextView student_name_txt, Standard_txt, sms_txt, absent_from_txt;
        CheckBox edit_chk;
    }

    @Override
    public int getCount() {
        return getConsistentAbModel.getFinalArray().size();
    }

    @Override
    public Object getItem(int position) {
        return getConsistentAbModel.getFinalArray().get(position);
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
            convertView = mInflater.inflate(R.layout.list_row_consistent_absent, null);

            viewHolder.student_name_txt = (TextView) convertView.findViewById(R.id.student_name_txt);
            viewHolder.Standard_txt = (TextView) convertView.findViewById(R.id.Standard_txt);
            viewHolder.sms_txt = (TextView) convertView.findViewById(R.id.sms_txt);
            viewHolder.absent_from_txt = (TextView) convertView.findViewById(R.id.absent_from_txt);
            viewHolder.edit_chk = (CheckBox) convertView.findViewById(R.id.edit_chk);

            final FinalArray_ConsistentAb detail = getConsistentAbModel.getFinalArray().get(position);

            try {
                viewHolder.student_name_txt.setText(detail.getStudent());
                viewHolder.Standard_txt.setText(detail.getStandard());
                viewHolder.sms_txt.setText(detail.getSmsNo());
                viewHolder.absent_from_txt.setText(String.valueOf(detail.getAbsentFrom()));

                viewHolder.edit_chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        String checkvalue, mobilenovalue;

                        if (isChecked) {
                            checkvalue = detail.getPkStudentID().toString();
                            dataCheck.add(checkvalue);
                            Log.d("dataCheck", dataCheck.toString());
                            mobilenovalue = detail.getSmsNo().toString();
                            mobiledata.add(mobilenovalue);
                            Log.d("mobiledata", mobiledata.toString());
                            listner.getCheckconsistentAb();
                        } else {
                            dataCheck.remove(detail.getPkStudentID().toString());
                            Log.d("dataUnCheck", dataCheck.toString());
                            listner.getCheckconsistentAb();
                        }
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return convertView;
    }

    public ArrayList<String> getData() {
        return dataCheck;
    }

    public ArrayList<String> getMobileData() {
        return mobiledata;
    }
}



