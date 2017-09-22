package com.anandniketan.skool360teacher.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.anandniketan.skool360teacher.Adapter.AttendanceAdapter;
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

    private Context mContext;
    private View rootView;
    private ProgressDialog progressDialog = null;
    private Button start_date;
    private DatePickerDialog datePickerDialog;
    DatePickerDialog.OnDateChangedListener startDate_Listener, Receive_Listener;
    int Year, Month, Day;
    Calendar calendar;
    int mYear, mMonth, mDay;
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
//    Recycler_adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_one, container, false);
        mContext = getActivity();

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
        setUserVisibleHint(false);
    }

    public void setListner() {
        start_date.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                datePickerDialog = DatePickerDialog.newInstance(OneFragment.this, Year, Month, Day);
                datePickerDialog.setThemeDark(false);
                datePickerDialog.showYearPickerFirst(false);
                datePickerDialog.setAccentColor(Color.parseColor("#1B88C8"));
                datePickerDialog.setTitle("Select Date From DatePickerDialog");
                datePickerDialog.show(getActivity().getFragmentManager(), "DatePickerDialog");
            }
        });
        insert_attendance_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InsertAttendance();
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
                        params.put("StdID", "8");//AppConfiguration.stdid
                        params.put("ClsID", "28");//AppConfiguration.clsid

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
//                            params.put("StandardID","");
//                            params.put("ClassID", "");
//                            params.put("Date", start_date.getText().toString());
//                            params.put("Comment", "test");
//                            params.put("AttendacneStatus", String.valueOf(status));
//                            params.put("CurrantDate", Utility.getTodaysDate());
//                            params.put("StudentID",String.valueOf(studid));
//                            params.put("AttendanceID", String.valueOf(id));
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

        }

    }

}
