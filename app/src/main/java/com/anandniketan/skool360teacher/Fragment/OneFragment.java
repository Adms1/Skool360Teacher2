package com.anandniketan.skool360teacher.Fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.anandniketan.skool360teacher.Adapter.AttendanceAdapter;
import com.anandniketan.skool360teacher.Adapter.Test_syllabusAdapter;
import com.anandniketan.skool360teacher.AsyncTasks.GetStaffAttendanceAsyncTask;
import com.anandniketan.skool360teacher.Models.StaffAttendanceModel;
import com.anandniketan.skool360teacher.R;
import com.anandniketan.skool360teacher.Utility.Utility;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;


public class OneFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    public OneFragment() {
        // Required empty public constructor
    }

    int position;
    String classId, standardId;

    @SuppressLint("ValidFragment")
    public OneFragment(int i, String classId, String StandardId) {
        this.position = i;
        this.classId = classId;
        this.standardId = StandardId;

    }

    private Context mContext;
    private View rootView;
    private ProgressDialog progressDialog = null;
    private static Button start_date;
    private DatePickerDialog datePickerDialog;
    int mYear, mMonth, mDay;
    int Year, Month, Day;
    Calendar calendar;
    private TextView total_student_txt, present_txt, absent_txt, leave_txt, txtNoRecords;
    private LinearLayout student_list_linear;
    private ListView student_list;
    private GetStaffAttendanceAsyncTask getstaffAttendanceAsyncTask = null;
    private ArrayList<StaffAttendanceModel> staffattendanceModels = new ArrayList<>();
    AttendanceAdapter attendanceAdapter = null;
    List<String> listDataHeader;
    List<StaffAttendanceModel.AttendanceDetails.StudentDetails> Rowchild;
    HashMap<String, ArrayList<StaffAttendanceModel.AttendanceDetails.StudentDetails>> listDataChild;
    private ImageView insert_attendance_img;
    private LinearLayout header_linear;
    private static String dateFinal;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_one, container, false);
        mContext = getActivity();
        Log.d("Current Position ", "Position = " + position + " user id  : " + classId + "standard id :" + standardId);
        init();
        setListner();
        setGetstaffAttendanceDetail();

        return rootView;
    }

    public void init() {

        calendar = Calendar.getInstance();
        Year = calendar.get(Calendar.YEAR);
        Month = calendar.get(Calendar.MONTH);
        Day = calendar.get(Calendar.DAY_OF_MONTH);

        header_linear = (LinearLayout) rootView.findViewById(R.id.header_linear);
        start_date = (Button) rootView.findViewById(R.id.start_date);
        total_student_txt = (TextView) rootView.findViewById(R.id.total_student_txt);
        present_txt = (TextView) rootView.findViewById(R.id.present_txt);
        absent_txt = (TextView) rootView.findViewById(R.id.absent_txt);
        leave_txt = (TextView) rootView.findViewById(R.id.leave_txt);
        txtNoRecords = (TextView) rootView.findViewById(R.id.txtNoRecords);

        student_list_linear = (LinearLayout) rootView.findViewById(R.id.student_list_linear);
        student_list = (ListView) rootView.findViewById(R.id.student_list);
        insert_attendance_img = (ImageView) rootView.findViewById(R.id.insert_attendance_img);
        start_date.setText(Utility.getTodaysDate());
        setUserVisibleHint(true);
    }

//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if (isVisibleToUser && rootView != null) {// && rootView != null
//            setGetstaffAttendanceDetail();
//        }
//        // execute your data loading logic.
//    }

    public void setListner() {


        insert_attendance_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InsertAttendance();
            }
        });
//        start_date.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DialogFragment newFragment = new SelectDateFragment();
//                newFragment.show(getFragmentManager(), "DatePicker");
//            }
//        });
        start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog = DatePickerDialog.newInstance(OneFragment.this, Year, Month, Day);
                datePickerDialog.setThemeDark(false);
                datePickerDialog.setOkText("Done");
                datePickerDialog.showYearPickerFirst(false);
                datePickerDialog.setAccentColor(Color.parseColor("#1B88C8"));
                datePickerDialog.setTitle("Select Date From DatePickerDialog");
                datePickerDialog.show(getActivity().getFragmentManager(), "DatePickerDialog");
            }
        });
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

        start_date.setText(d + "/" + m + "/" + y);
    }

//    public static class SelectDateFragment extends DialogFragment implements android.app.DatePickerDialog.OnDateSetListener {
//
//        @Override
//        public Dialog onCreateDialog(Bundle savedInstanceState) {
//            final Calendar calendar = Calendar.getInstance();
//            int yy = calendar.get(Calendar.YEAR);
//            int mm = calendar.get(Calendar.MONTH);
//            int dd = calendar.get(Calendar.DAY_OF_MONTH);
//            return new android.app.DatePickerDialog(getActivity(), this, yy, mm, dd);
//        }
//
//        public void onDateSet(DatePicker view, int yy, int mm, int dd) {
//            populateSetDate(yy, mm + 1, dd);
//        }
//
//        public void populateSetDate(int year, int month, int day) {
//            int mYear, mMonth, mDay;
//            mDay = day;
//            mMonth = month + 1;
//            mYear = year;
//            String d, m, y;
//            d = Integer.toString(mDay);
//            m = Integer.toString(mMonth);
//            y = Integer.toString(mYear);
//
//            if (mDay < 10) {
//                d = "0" + d;
//            }
//            if (mMonth < 10) {
//                m = "0" + m;
//            }
//
//
//            dateFinal = d + "/" + m + "/" + year;
//
//            start_date.setText(dateFinal);
//        }
//    }

    public void setGetstaffAttendanceDetail() {
        if (Utility.isNetworkConnected(mContext)) {
            progressDialog = new ProgressDialog(mContext);
            progressDialog.setMessage("Please Wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        HashMap<String, String> params = new HashMap<String, String>();
                        params.put("StaffID", Utility.getPref(mContext, "StaffID"));
                        params.put("AttDate", start_date.getText().toString());
                        params.put("StdID", standardId);//AppConfiguration.stdid
                        params.put("ClsID", classId);//AppConfiguration.clsid

                        getstaffAttendanceAsyncTask = new GetStaffAttendanceAsyncTask(params);
                        staffattendanceModels = getstaffAttendanceAsyncTask.execute().get();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                                if (staffattendanceModels.size() > 0) {
                                    txtNoRecords.setVisibility(View.GONE);
                                    prepareList();

                                    attendanceAdapter = new AttendanceAdapter(getActivity(), staffattendanceModels);
                                    student_list.setAdapter(attendanceAdapter);
                                    student_list.deferNotifyDataSetChanged();
                                    insert_attendance_img.setVisibility(View.VISIBLE);
                                } else {
                                    progressDialog.dismiss();
                                    txtNoRecords.setVisibility(View.VISIBLE);
                                    insert_attendance_img.setVisibility(View.GONE);
                                    header_linear.setVisibility(View.GONE);
                                }
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        } else {
            Utility.ping(mContext, "Network not available");
        }
    }

    public void prepareList() {
        total_student_txt.setText(Html.fromHtml("Total Student : " + "<font color='#1B88C8'>" + "<b>" + staffattendanceModels.get(0).getAttendanceList().get(0).getTotal() + "</b>"));
        present_txt.setText(Html.fromHtml("Present : " + "<font color='#a4c639'>" + "<b>" + staffattendanceModels.get(0).getAttendanceList().get(0).getTotalPresent() + "</b>"));
        absent_txt.setText(Html.fromHtml("Absent : " + "<font color='#ff0000'>" + "<b>" + staffattendanceModels.get(0).getAttendanceList().get(0).getTotalAbsent() + "</b>"));
        leave_txt.setText(Html.fromHtml("Leave : " + "<font color='#ff9623'>" + "<b>" + staffattendanceModels.get(0).getAttendanceList().get(0).getTotalLeave() + "</b>"));


        if (!staffattendanceModels.get(0).getAttendanceList().get(0).getStudentList().get(0).getAttendenceStatus().equalsIgnoreCase("-2")) {
            insert_attendance_img.setBackgroundResource(R.drawable.update_1);
        } else {
            insert_attendance_img.setBackgroundResource(R.drawable.submit);
        }


    }

    public void InsertAttendance() {
        final ArrayList<String> id = new ArrayList<>();
        final ArrayList<String> status = new ArrayList<>();
        final ArrayList<String> studid = new ArrayList<>();

        ArrayList<StaffAttendanceModel> array = attendanceAdapter.getData();
        ArrayList<StaffAttendanceModel.AttendanceDetails.StudentDetails> StudentArray = array.get(0).getAttendanceList().get(0).getStudentList();



            for (int i = 0; i < StudentArray.size(); i++) {

                id.add(StudentArray.get(i).getAttendanceID());
                status.add(StudentArray.get(i).getAttendenceStatus());
                studid.add(StudentArray.get(i).getStudentID());

                Log.d("AttendanceID", id.toString());
                Log.d("statusID", status.toString());
                Log.d("studID", studid.toString());

            }

        String finalstatusStr = "";
        for (String s : status) {
            finalstatusStr = finalstatusStr + "," + s;
        }
        Log.d("idStr", finalstatusStr.toString());
        finalstatusStr = finalstatusStr.substring(1, finalstatusStr.length());
        Log.d("finalstatusStr", finalstatusStr.toString());

        String finalidStr = "";
        for (String s : id) {
            finalidStr = finalidStr + "," + s;
        }
        Log.d("finalidStr", finalidStr.toString());
        finalidStr = finalidStr.substring(1, finalidStr.length());
        Log.d("finalidStr", finalidStr.toString());

        String finalstudid = "";
        for (String s : studid) {
            finalstudid = finalstudid + "," + s;
        }
        Log.d("finalstudid", finalstudid.toString());
        finalstudid = finalstudid.substring(1, finalstudid.length());
        Log.d("finalstudid", finalstudid.toString());
//            if (Utility.isNetworkConnected(mContext)) {
//                progressDialog = new ProgressDialog(mContext);
//                progressDialog.setMessage("Please Wait...");
//                progressDialog.setCancelable(false);
//                progressDialog.show();
//
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            HashMap<String, String> params = new HashMap<String, String>();
//                            params.put("StaffID", Utility.getPref(mContext, "StaffID"));
//                            params.put("StandardID", standardId);
//                            params.put("ClassID", classId);
//                            params.put("Date", start_date.getText().toString());
//                            params.put("Comment", "test");
//                            params.put("AttendacneStatus",finalstatusStr);
//                            params.put("CurrantDate", Utility.getTodaysDate());
//                            params.put("StudentID", finalstudid);
//                            params.put("AttendanceID",finalidStr);
//
//
//                            getstaffAttendanceAsyncTask = new GetStaffAttendanceAsyncTask(params);
//                            staffattendanceModels = getstaffAttendanceAsyncTask.execute().get();
//                            getActivity().runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    progressDialog.dismiss();
//                                    if (staffattendanceModels.size() > 0) {
//                                        txtNoRecords.setVisibility(View.GONE);
//                                        prepareList();
//                                        attendanceAdapter = new AttendanceAdapter(getActivity(), staffattendanceModels);
//                                        student_list.setAdapter(attendanceAdapter);
//                                        student_list.deferNotifyDataSetChanged();
//                                        insert_attendance_img.setVisibility(View.VISIBLE);
//                                    } else {
//                                        progressDialog.dismiss();
//                                        txtNoRecords.setVisibility(View.VISIBLE);
//                                        insert_attendance_img.setVisibility(View.GONE);
//                                    }
//                                }
//                            });
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }).start();
//
//            } else {
//                Utility.ping(mContext, "Network not available");
//            }
//
//
//        }

    }

}
