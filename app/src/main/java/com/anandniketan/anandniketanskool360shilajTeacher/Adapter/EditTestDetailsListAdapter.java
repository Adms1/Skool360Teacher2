package com.anandniketan.anandniketanskool360shilajTeacher.Adapter;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.anandniketan.anandniketanskool360shilajTeacher.Models.SyllbusDataModel;
import com.anandniketan.anandniketanskool360shilajTeacher.R;

import java.util.ArrayList;

/**
 * Created by admsandroid on 11/8/2017.
 */

public class EditTestDetailsListAdapter extends BaseAdapter {
    private Context mContext;
    ArrayList<String> syllbusarrayList;
    ArrayList<String> number;
    private ArrayList<String> syllbusDataList = new ArrayList<>();

    // Constructor
    public EditTestDetailsListAdapter(Context c, ArrayList<String> syllbusarrayList, ArrayList<String> number) {
        mContext = c;
        this.syllbusarrayList = syllbusarrayList;
        this.number = number;
    }

    private class ViewHolder {
        TextView syllbus_edt;

    }

    @Override
    public int getCount() {
        return number.size();
    }

    @Override
    public Object getItem(int position) {
        return number.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        convertView = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.list_edittext, null);
            viewHolder.syllbus_edt = (EditText) convertView.findViewById(R.id.syllabus_txt);

            String value = syllbusarrayList.get(position).toString();

            try {
                viewHolder.syllbus_edt.setText(value);
//                syllbusDataList.add(viewHolder.syllbus_edt.getText().toString());
//                Log.d("syllbusData", syllbusDataList.toString());
                viewHolder.syllbus_edt.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        syllbusDataList.add(String.valueOf(editable));
                        Log.d("syllbusData", syllbusDataList.toString());

                    }
                });



            } catch (Exception e) {
                e.printStackTrace();
            }
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    public ArrayList<String> syllbusDataList() {
        return syllbusDataList;
    }

}



