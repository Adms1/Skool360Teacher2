package com.anandniketan.skool360teacher.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SearchRecentSuggestionsProvider;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anandniketan.skool360teacher.Models.TeacherAssignedSubjectModel;
import com.anandniketan.skool360teacher.Models.Test_SyllabusModel;
import com.anandniketan.skool360teacher.R;

import java.util.ArrayList;

/**
 * Created by admsandroid on 9/26/2017.
 */

public class Test_syllabusAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Test_SyllabusModel> test_syllabusModels = new ArrayList<>();
    AlertDialog alertDialogAndroid = null;

    // Constructor
    public Test_syllabusAdapter(Context c, ArrayList<Test_SyllabusModel> test_syllabusModels) {
        mContext = c;
        this.test_syllabusModels = test_syllabusModels;

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
            LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.list_row_test_syllabus, null);

            viewHolder.srno_txt = (TextView) convertView.findViewById(R.id.srno_txt);
            viewHolder.test_name_txt = (TextView) convertView.findViewById(R.id.test_name_txt);
            viewHolder.grade_txt = (TextView) convertView.findViewById(R.id.grade_txt);
            viewHolder.edit_txt = (ImageView) convertView.findViewById(R.id.edit_txt);
            viewHolder.subject_txt = (TextView) convertView.findViewById(R.id.subject_txt);

            try {

//                viewHolder.srno_txt.setText(test_syllabusModels.get(position).getPosition());
                viewHolder.test_name_txt.setText(test_syllabusModels.get(position).getTestName());
                viewHolder.grade_txt.setText(test_syllabusModels.get(position).getStandardClass());
                viewHolder.subject_txt.setText(test_syllabusModels.get(position).getSubject());

                viewHolder.edit_txt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        LayoutInflater lInflater = (LayoutInflater) mContext
                                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View layout = lInflater.inflate(R.layout.list_edit_row, null);

                        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(mContext);
                        alertDialogBuilderUserInput.setView(layout);

                        alertDialogAndroid = alertDialogBuilderUserInput.create();
                        alertDialogAndroid.setCancelable(false);
                        alertDialogAndroid.show();
                        Window window = alertDialogAndroid.getWindow();
                        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                        WindowManager.LayoutParams wlp = window.getAttributes();

                        wlp.gravity = Gravity.CENTER;
                        wlp.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                        window.setAttributes(wlp);
                        alertDialogAndroid.show();

                        Button close_btn, Edit_btn;
                        TextView edit_test_txt, edit_test_date_txt, edit_test_grade_txt, edit_test_subject_txt;
                        EditText syllabus_txt1, syllabus_txt2,
                                syllabus_txt3, syllabus_txt4, syllabus_txt5, syllabus_txt6, syllabus_txt7, syllabus_txt8, syllabus_txt9, syllabus_txt10;


                        close_btn = (Button) layout.findViewById(R.id.close_btn);
                        Edit_btn = (Button) layout.findViewById(R.id.Edit_btn);
                        edit_test_txt = (TextView) layout.findViewById(R.id.edit_test_txt);
                        edit_test_date_txt = (TextView) layout.findViewById(R.id.edit_test_date_txt);
                        edit_test_grade_txt = (TextView) layout.findViewById(R.id.edit_test_grade_txt);
                        edit_test_subject_txt = (TextView) layout.findViewById(R.id.edit_test_subject_txt);
                        syllabus_txt1 = (EditText) layout.findViewById(R.id.syllabus_txt1);
                        syllabus_txt2 = (EditText) layout.findViewById(R.id.syllabus_txt2);
                        syllabus_txt3 = (EditText) layout.findViewById(R.id.syllabus_txt3);
                        syllabus_txt4 = (EditText) layout.findViewById(R.id.syllabus_txt4);
                        syllabus_txt5 = (EditText) layout.findViewById(R.id.syllabus_txt5);
                        syllabus_txt6 = (EditText) layout.findViewById(R.id.syllabus_txt6);
                        syllabus_txt7 = (EditText) layout.findViewById(R.id.syllabus_txt7);
                        syllabus_txt8 = (EditText) layout.findViewById(R.id.syllabus_txt8);
                        syllabus_txt9 = (EditText) layout.findViewById(R.id.syllabus_txt9);
                        syllabus_txt10 = (EditText) layout.findViewById(R.id.syllabus_txt10);

                        close_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                alertDialogAndroid.dismiss();
                            }
                        });
                        Edit_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });
                        String Datestr = test_syllabusModels.get(position).getTestDate();
                        String[] spiltdate = Datestr.split("//T");

                        edit_test_txt.setText(Html.fromHtml("<b>Test :</b>" + test_syllabusModels.get(position).getTestName()));
                        edit_test_date_txt.setText(Html.fromHtml("<b>Test Date :</b>" + spiltdate[0]));
                        edit_test_grade_txt.setText(Html.fromHtml("<b>Grade :</b>" + test_syllabusModels.get(position).getStandardClass()));
                        edit_test_subject_txt.setText(Html.fromHtml("<b>Subject :</b>" + test_syllabusModels.get(position).getSubject()));

                        syllabus_txt1.setText(test_syllabusModels.get(position).getGetSyllabusData().get(position).getSyllabus());
                    }
                });


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return convertView;
    }

    public void EnteryFormDialog() {
    }

}



