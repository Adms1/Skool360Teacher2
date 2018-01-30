package com.anandniketan.anandniketanskool360shilajTeacher.Adapter;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.anandniketan.anandniketanskool360shilajTeacher.R;

import java.util.ArrayList;

/**
 * Created by admsandroid on 11/8/2017.
 */

public class AddTestDetalisListAdapter extends BaseAdapter {
    private Context mContext;
    ArrayList<String> number;
    ArrayList<String> data=new ArrayList<String>();
    // Constructor
    public AddTestDetalisListAdapter(Context c, ArrayList<String> number) {
        mContext = c;
        this.number = number;

    }
    public ArrayList<String> getUpdatedValues() {
        return data;
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
        View view = null;
        convertView = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.list_edittext, null);
            viewHolder.syllbus_edt = (EditText) convertView.findViewById(R.id.syllabus_txt);

            try {
                viewHolder.syllbus_edt.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        data.add(position,editable.toString());
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
}



