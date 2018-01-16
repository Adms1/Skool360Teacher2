package com.anandniketan.anandniketanskool360shilajTeacher.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.anandniketan.anandniketanskool360shilajTeacher.AsyncTasks.TeacherGetTestSyllabusAsyncTask;
import com.anandniketan.anandniketanskool360shilajTeacher.AsyncTasks.TeacherUpdateTestDetailAsyncTask;
import com.anandniketan.anandniketanskool360shilajTeacher.Interfacess.CallBack;
import com.anandniketan.anandniketanskool360shilajTeacher.Interfacess.onEditTest;
import com.anandniketan.anandniketanskool360shilajTeacher.Models.Test_SyllabusModel;
import com.anandniketan.anandniketanskool360shilajTeacher.Models.UpdateTestDetailModel;
import com.anandniketan.anandniketanskool360shilajTeacher.R;
import com.anandniketan.anandniketanskool360shilajTeacher.Utility.Utility;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by admsandroid on 11/8/2017.
 */

public class TestsyllabusListAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Test_SyllabusModel> test_syllabusModels = new ArrayList<>();
    FragmentManager activity;
    private ArrayList<String> editTestData = new ArrayList<String>();
    private ArrayList<String> setData = new ArrayList<String>();
    private ArrayList<String> syllbusarray = new ArrayList<String>();
    private onEditTest onEditTest;

    // Constructor
    public TestsyllabusListAdapter(Context c, FragmentManager activity, ArrayList<Test_SyllabusModel> test_syllabusModels, onEditTest onEditTest) {
        mContext = c;
        this.test_syllabusModels = test_syllabusModels;
        this.activity = activity;
        this.onEditTest = onEditTest;
    }
    private class ViewHolder {
        TextView srno_txt, test_name_txt, grade_txt, subject_txt;
        ImageView edit_txt;
    }

    @Override
    public int getCount() {
        return test_syllabusModels.size();
    }

    @Override
    public Object getItem(int position) {
        return test_syllabusModels.get(position);
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
            convertView = mInflater.inflate(R.layout.list_row_test_syllabus, null);

            viewHolder.srno_txt = (TextView) convertView.findViewById(R.id.srno_txt);
            viewHolder.test_name_txt = (TextView) convertView.findViewById(R.id.test_name_txt);
            viewHolder.grade_txt = (TextView) convertView.findViewById(R.id.grade_txt);
            viewHolder.edit_txt = (ImageView) convertView.findViewById(R.id.edit_txt);
            viewHolder.subject_txt = (TextView) convertView.findViewById(R.id.subject_txt);

            try {
                String sr = String.valueOf(position + 1);
                viewHolder.srno_txt.setText(sr);
                viewHolder.test_name_txt.setText(test_syllabusModels.get(position).getTestName().trim());
                viewHolder.grade_txt.setText(test_syllabusModels.get(position).getStandardClass());
                viewHolder.subject_txt.setText(test_syllabusModels.get(position).getSubject());

                viewHolder.edit_txt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setData.clear();
                        syllbusarray.clear();
                        setData.add(test_syllabusModels.get(position).getTestName() + "|" +
                                test_syllabusModels.get(position).getStandardClass() + "|" +
                                test_syllabusModels.get(position).getSubject() + "|" +
                                test_syllabusModels.get(position).getTestDate());
                        editTestData.add(test_syllabusModels.get(position).getTSMasterID() + "|" + test_syllabusModels.get(position).getTestID() + "|" +
                                test_syllabusModels.get(position).getSubjectID() + "|" + test_syllabusModels.get(position).getSectionID() + "|" +
                                test_syllabusModels.get(position).getTestDate());
                        for (int i = 0; i < test_syllabusModels.get(position).getGetSyllabusData().size(); i++) {
                            syllbusarray.add(test_syllabusModels.get(position).getGetSyllabusData().get(i).getSyllabus());
                        }
                        onEditTest.getEditTest();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return convertView;
    }

    public ArrayList<String> getDataforEditTest() {
        return editTestData;
    }

    public ArrayList<String> getSetData() {
        return setData;
    }

    public ArrayList<String> syllbusArray() {
        return syllbusarray;
    }
}




