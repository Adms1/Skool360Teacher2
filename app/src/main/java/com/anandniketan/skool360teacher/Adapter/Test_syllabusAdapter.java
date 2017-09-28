package com.anandniketan.skool360teacher.Adapter;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
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
import android.widget.TextView;

import com.anandniketan.skool360teacher.AsyncTasks.TeacherGetTestSyllabusAsyncTask;
import com.anandniketan.skool360teacher.AsyncTasks.TeacherInsertTestDetailAsyncTask;
import com.anandniketan.skool360teacher.Models.StaffAttendanceModel;
import com.anandniketan.skool360teacher.Models.TeacherInsertTestDetailModel;
import com.anandniketan.skool360teacher.Models.Test_SyllabusModel;
import com.anandniketan.skool360teacher.R;
import com.anandniketan.skool360teacher.Utility.Utility;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by admsandroid on 9/26/2017.
 */

public class Test_syllabusAdapter extends BaseAdapter implements DatePickerDialog.OnDateSetListener {
    private Context mContext;
    private ArrayList<Test_SyllabusModel> test_syllabusModels = new ArrayList<>();
    AlertDialog alertDialogAndroid = null;
    private DatePickerDialog datePickerDialog;
    int Year, Month, Day;
    Calendar calendar;
    int mYear, mMonth, mDay;
    Button close_btn, Edit_btn;
    TextView edit_test_txt, edit_test_date_txt, edit_test_grade_txt, edit_test_subject_txt;
    LinearLayout llListData;
    FragmentManager activity;
    private ProgressDialog progressDialog = null;
    private TeacherInsertTestDetailAsyncTask teacherInsertTestDetailAsyncTask = null;
    private ArrayList<TeacherInsertTestDetailModel> insertTest = new ArrayList<>();
    private ArrayList<String> text = new ArrayList<String>();
    EditText syllbus_edt;

    // Constructor
    public Test_syllabusAdapter(Context c, FragmentManager activity, ArrayList<Test_SyllabusModel> test_syllabusModels) {
        mContext = c;
        this.test_syllabusModels = test_syllabusModels;
        this.activity = activity;

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
                viewHolder.test_name_txt.setText(test_syllabusModels.get(position).getTestName());
                viewHolder.grade_txt.setText(test_syllabusModels.get(position).getStandardClass());
                viewHolder.subject_txt.setText(test_syllabusModels.get(position).getSubject());

                viewHolder.edit_txt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        LayoutInflater lInflater = (LayoutInflater) mContext
                                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        final View layout = lInflater.inflate(R.layout.list_edit_row, null);

                        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(mContext);
                        alertDialogBuilderUserInput.setView(layout);

                        alertDialogAndroid = alertDialogBuilderUserInput.create();
                        alertDialogAndroid.setCancelable(false);
                        alertDialogAndroid.show();
                        Window window = alertDialogAndroid.getWindow();
                        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, 1200);
                        WindowManager.LayoutParams wlp = window.getAttributes();

                        wlp.gravity = Gravity.CENTER_HORIZONTAL;
                        wlp.flags = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
                        window.setAttributes(wlp);
                        alertDialogAndroid.show();


                        close_btn = (Button) layout.findViewById(R.id.close_btn);
                        Edit_btn = (Button) layout.findViewById(R.id.Edit_btn);
                        edit_test_txt = (TextView) layout.findViewById(R.id.edit_test_txt);
                        edit_test_date_txt = (TextView) layout.findViewById(R.id.edit_test_date_txt);
                        edit_test_grade_txt = (TextView) layout.findViewById(R.id.edit_test_grade_txt);
                        edit_test_subject_txt = (TextView) layout.findViewById(R.id.edit_test_subject_txt);
                        llListData = (LinearLayout) layout.findViewById(R.id.llListData);

                        calendar = Calendar.getInstance();
                        Year = calendar.get(Calendar.YEAR);
                        Month = calendar.get(Calendar.MONTH);
                        Day = calendar.get(Calendar.DAY_OF_MONTH);

                        close_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                alertDialogAndroid.dismiss();
                            }
                        });
                        Edit_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                Log.d("text",""+text);
//                                if (Utility.isNetworkConnected(mContext)) {
////                                    progressDialog = new ProgressDialog(mContext);
////                                    progressDialog.setMessage("Please Wait...");
////                                    progressDialog.setCancelable(false);
////                                    progressDialog.show();
//
//                                    new Thread(new Runnable() {
//                                        @Override
//                                        public void run() {
//                                            try {
//                                                HashMap<String, String> params = new HashMap<String, String>();
//                                                params.put("StaffID", Utility.getPref(mContext, "StaffID"));
//                                                params.put("TSMasterID", test_syllabusModels.get(position).getTSMasterID());
//                                                params.put("TestID", test_syllabusModels.get(position).getTestID());
//                                                params.put("TestDate", edit_test_date_txt.getText().toString());
//                                                params.put("SubjectID", test_syllabusModels.get(position).getSubjectID());
//                                                params.put("SectionID", test_syllabusModels.get(position).getSectionID());
//                                                params.put("Arydetail", "test|&test|&test");
//
//                                                teacherInsertTestDetailAsyncTask = new TeacherInsertTestDetailAsyncTask(params);
//                                                insertTest = teacherInsertTestDetailAsyncTask.execute().get();
//                                                Test_syllabusAdapter.this.runOnUiThread(new Runnable() {
//                                                    @Override
//                                                    public void run() {
//                                                        Utility.ping(mContext, "Update Test");
////                                                        progressDialog.dismiss();
////                                                        if (insertTest.size() > 0) {
////                                                            Utility.ping(mContext, "Update Test");
////                                                        } else {
//////                                                            progressDialog.dismiss();
////                                                        }
//                                                    }
//                                                });
//                                            } catch (Exception e) {
//                                                e.printStackTrace();
//                                            }
//                                        }
//                                    }).start();
//                                } else {
//                                    Utility.ping(mContext, "Network not available");
//                                }
                            }
                        });
                        edit_test_date_txt.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                datePickerDialog = DatePickerDialog.newInstance(Test_syllabusAdapter.this, Year, Month, Day);
                                datePickerDialog.setThemeDark(false);
                                datePickerDialog.showYearPickerFirst(false);
                                datePickerDialog.setAccentColor(Color.parseColor("#1B88C8"));
                                datePickerDialog.setTitle("Select Date From DatePickerDialog");
                                datePickerDialog.show(activity, "DatePickerDialog");
                            }
                        });

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                        SimpleDateFormat output = new SimpleDateFormat("dd-MM-yyyy");
                        Date d = null;
                        try {
                            d = sdf.parse(test_syllabusModels.get(position).getTestDate());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        String formattedTime = output.format(d);

                        edit_test_txt.setText(Html.fromHtml("<b>Test :</b>" + test_syllabusModels.get(position).getTestName()));
                        edit_test_date_txt.setText(formattedTime);
                        edit_test_grade_txt.setText(Html.fromHtml("<b>Grade :</b>" + test_syllabusModels.get(position).getStandardClass()));
                        edit_test_subject_txt.setText(Html.fromHtml("<b>Subject :</b>" + test_syllabusModels.get(position).getSubject()));


                        if (llListData.getChildCount() > 0) {
                            llListData.removeAllViews();
                        }
                        try {
                            for (int i = 0; i < 10; i++) {
                                View convertView = LayoutInflater.from(mContext).inflate(R.layout.list_edittext, null);
                                syllbus_edt = (EditText) convertView.findViewById(R.id.syllabus_txt);

                                if (i < test_syllabusModels.get(position).getGetSyllabusData().size()) {
                                    syllbus_edt.setText(test_syllabusModels.get(position).getGetSyllabusData().get(i).getSyllabus());

                                } else {
                                    syllbus_edt.setText(" ");
                                }

//                                test_syllabusModels.get(position).getGetSyllabusData().get(i).setSyllabus(syllbus_edt.getText().toString());
                                syllbus_edt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                                    @Override
                                    public void onFocusChange(View view, boolean b) {
//                                        text.add();
                                    }
                                });
                                llListData.addView(convertView);
                            }
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        } catch (IndexOutOfBoundsException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return convertView;
    }

    private void runOnUiThread(Runnable runnable) {
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = "Selected Date : " + Day + "/" + Month + "/" + Year;
        String datestr = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;

        mDay = dayOfMonth;
        mMonth = monthOfYear + 1;
        mYear = year;
        String d, m, y;
        d = Integer.toString(mDay);
        m = Integer.toString(mMonth);
        y = Integer.toString(mYear);

        if (mDay < 10) {
            d = "0" + d;
        }
        if (mMonth < 10) {
            m = "0" + m;
        }

        edit_test_date_txt.setText(d + "/" + m + "/" + y);
    }

    public ArrayList<Test_SyllabusModel> getInsertTest() {
        return test_syllabusModels;
    }
}



