package com.anandniketan.skool360teacher.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.anandniketan.skool360teacher.Adapter.ExpandableListAdapterLessonPlan;
import com.anandniketan.skool360teacher.AsyncTasks.GetTeacherLessonPlanScheduleAsyncTask;
import com.anandniketan.skool360teacher.Models.LessonPlanModel;
import com.anandniketan.skool360teacher.R;
import com.anandniketan.skool360teacher.Utility.Utility;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class LessonplanscheduleFragment extends Fragment implements DatePickerDialog.OnDateSetListener {
    private View rootView;
    private Button btnMenu, btnFilterlessonplan, btnBacktest_lessonplan;
    private static TextView fromDate, toDate;
    private TextView txtNoRecordslesson;
    private static String dateFinal;
    private Context mContext;
    private ProgressDialog progressDialog = null;
    private static boolean isFromDate = false;
    private int lastExpandedPosition = -1;
    private DatePickerDialog datePickerDialog;
    int Year, Month, Day;
    Calendar calendar;
    int mYear, mMonth, mDay;
    ExpandableListView lvExplessonplan;
    ExpandableListAdapterLessonPlan listAdapter;
    List<String> listDataHeader;
    HashMap<String, ArrayList<LessonPlanModel.LessonplanData>> listDataChild;
    private GetTeacherLessonPlanScheduleAsyncTask getTeacherLessonPlanScheduleAsyncTask = null;
    private ArrayList<LessonPlanModel> lessonPlanModels = new ArrayList<>();
    private RelativeLayout date_rel;
    private LinearLayout lesson_plan_header;
    Typeface typeface;

    public LessonplanscheduleFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_lessonplanschedule, container, false);
        mContext = getActivity();

        initViews();
        setListners();

        return rootView;
    }

    public void initViews() {
        fromDate = (TextView) rootView.findViewById(R.id.fromDate);
        toDate = (TextView) rootView.findViewById(R.id.toDate);
        btnFilterlessonplan = (Button) rootView.findViewById(R.id.btnFilterlessonplan);
        txtNoRecordslesson = (TextView) rootView.findViewById(R.id.txtNoRecordslesson);
        lvExplessonplan = (ExpandableListView) rootView.findViewById(R.id.lvExplessonplan);
        date_rel = (RelativeLayout) rootView.findViewById(R.id.date_rel);
        lesson_plan_header = (LinearLayout) rootView.findViewById(R.id.lesson_plan_header);

        calendar = Calendar.getInstance();
        Year = calendar.get(Calendar.YEAR);
        Month = calendar.get(Calendar.MONTH);
        Day = calendar.get(Calendar.DAY_OF_MONTH);

        typeface = Typeface.createFromAsset(mContext.getAssets(), "Roboto-Medium.ttf");
        fromDate.setTypeface(typeface);
        toDate.setTypeface(typeface);

        //load today's data first
        fromDate.setText(Utility.getTodaysDate());

        toDate.setText(Utility.getTodaysDate());
        getHomeworkData(fromDate.getText().toString(), toDate.getText().toString());
    }

    public void setListners() {


        fromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFromDate = true;
                datePickerDialog = DatePickerDialog.newInstance(LessonplanscheduleFragment.this, Year, Month, Day);
                datePickerDialog.setThemeDark(false);
                datePickerDialog.setOkText("Done");
                datePickerDialog.showYearPickerFirst(false);
                datePickerDialog.setAccentColor(Color.parseColor("#1B88C8"));
                datePickerDialog.setTitle("Select Date From DatePickerDialog");
                datePickerDialog.show(getActivity().getFragmentManager(), "DatePickerDialog");
            }
        });

        toDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFromDate = false;
                datePickerDialog = DatePickerDialog.newInstance(LessonplanscheduleFragment.this, Year, Month, Day);
                datePickerDialog.setThemeDark(false);
                datePickerDialog.setOkText("Done");

                datePickerDialog.showYearPickerFirst(false);
                datePickerDialog.setAccentColor(Color.parseColor("#1B88C8"));
                datePickerDialog.setTitle("Select Date From DatePickerDialog");
                datePickerDialog.show(getActivity().getFragmentManager(), "DatePickerDialog");
            }
        });

        btnFilterlessonplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!fromDate.getText().toString().equalsIgnoreCase("")) {
                    if (!toDate.getText().toString().equalsIgnoreCase("")) {

                        getHomeworkData(fromDate.getText().toString(), toDate.getText().toString());

                    } else {
                        Utility.pong(mContext, "You need to select a to date");
                    }
                } else {
                    Utility.pong(mContext, "You need to select a from date");
                }
            }
        });
        lvExplessonplan.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpandedPosition != -1
                        && groupPosition != lastExpandedPosition) {
                    lvExplessonplan.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = groupPosition;
            }
        });
    }


    public void populateSetDate(int year, int month, int day) {
        String d, m, y;
        d = Integer.toString(day);
        m = Integer.toString(month);
        y = Integer.toString(year);
        if (day < 10) {
            d = "0" + d;
        }
        if (month < 10) {
            m = "0" + m;
        }

        dateFinal = d + "/" + m + "/" + y;
        if (isFromDate) {
            fromDate.setText(dateFinal);
        } else {
            toDate.setText(dateFinal);
        }
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        populateSetDate(year, monthOfYear + 1, dayOfMonth);
    }

    public void getHomeworkData(final String fromDate, final String toDate) {
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
                        params.put("StaffID", Utility.getPref(mContext, "StaffID"));//
                        params.put("FromDate", fromDate);
                        params.put("ToDate", toDate);
                        lessonPlanModels.clear();
                        getTeacherLessonPlanScheduleAsyncTask = new GetTeacherLessonPlanScheduleAsyncTask(params);
                        lessonPlanModels = getTeacherLessonPlanScheduleAsyncTask.execute().get();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                                if (lessonPlanModels.size() > 0) {
                                    txtNoRecordslesson.setVisibility(View.GONE);
                                    date_rel.setVisibility(View.VISIBLE);
                                    lesson_plan_header.setVisibility(View.VISIBLE);
                                    lvExplessonplan.setVisibility(View.VISIBLE);
                                    progressDialog.dismiss();
                                    prepaareList();
                                    listAdapter = new ExpandableListAdapterLessonPlan(getActivity(), listDataHeader, listDataChild);
                                    lvExplessonplan.setAdapter(listAdapter);
                                } else {
                                    progressDialog.dismiss();
                                    txtNoRecordslesson.setVisibility(View.VISIBLE);
                                    lesson_plan_header.setVisibility(View.GONE);
                                    lvExplessonplan.setVisibility(View.GONE);
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

    public void prepaareList() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, ArrayList<LessonPlanModel.LessonplanData>>();

        for (int j = 0; j < lessonPlanModels.get(0).getGethomeworkdata().size(); j++) {
            listDataHeader.add(lessonPlanModels.get(0).getGethomeworkdata().get(j).getDate() + "|"
                    + lessonPlanModels.get(0).getGethomeworkdata().get(j).getStandard() + "|"
                    + lessonPlanModels.get(0).getGethomeworkdata().get(j).getClassName() + "|"
                    + lessonPlanModels.get(0).getGethomeworkdata().get(j).getSubject());

            ArrayList<LessonPlanModel.LessonplanData> rows = new ArrayList<LessonPlanModel.LessonplanData>();

            rows.add(lessonPlanModels.get(0).getGethomeworkdata().get(j));

            listDataChild.put(listDataHeader.get(j), rows);
        }


    }
}
