package com.anandniketan.skool360teacher.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.anandniketan.skool360teacher.Adapter.MysubjectAdapetr;
import com.anandniketan.skool360teacher.Adapter.Test_syllabusAdapter;
import com.anandniketan.skool360teacher.AsyncTasks.GetTeacherAssignedSubjectAsyncTask;
import com.anandniketan.skool360teacher.Models.TeacherAssignedSubjectModel;
import com.anandniketan.skool360teacher.R;
import com.anandniketan.skool360teacher.Utility.Utility;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;


public class AddTestFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    public AddTestFragment() {
        // Required empty public constructor
    }

    private Button test_date, Add_btn;
    private Spinner standard_subject_spinner, test_section_spinner, test_spinner;
    private Context mContext;
    private View rootView;
    private TextView txtNoRecords;
    private DatePickerDialog datePickerDialog;
    int Year, Month, Day;
    Calendar calendar;
    int mYear, mMonth, mDay;
    private ProgressDialog progressDialog = null;
    private GetTeacherAssignedSubjectAsyncTask getTeacherAssignedSubjectAsyncTask = null;
    private ArrayList<TeacherAssignedSubjectModel> teacherAssignedSubjectModels = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_add_test, container, false);
        mContext = getActivity();

        init();
        setListner();
        return rootView;
    }

    public void init() {
        calendar = Calendar.getInstance();
        Year = calendar.get(Calendar.YEAR);
        Month = calendar.get(Calendar.MONTH);
        Day = calendar.get(Calendar.DAY_OF_MONTH);

        standard_subject_spinner = (Spinner) rootView.findViewById(R.id.standard_subject_spinner);
        test_section_spinner = (Spinner) rootView.findViewById(R.id.test_section_spinner);
        test_spinner = (Spinner) rootView.findViewById(R.id.test_spinner);
        test_date = (Button) rootView.findViewById(R.id.test_date);
        Add_btn = (Button) rootView.findViewById(R.id.Add_btn);
        txtNoRecords = (TextView) rootView.findViewById(R.id.txtNoRecordstest);
        setUserVisibleHint(false);
        test_date.setText(Utility.getTodaysDate());
        setTodayschedule();
        fillsubjectspinner();

    }

    public void setListner() {
        test_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog = DatePickerDialog.newInstance(AddTestFragment.this, Year, Month, Day);
                datePickerDialog.setThemeDark(false);
                datePickerDialog.showYearPickerFirst(false);
                datePickerDialog.setAccentColor(Color.parseColor("#1B88C8"));
                datePickerDialog.setTitle("Select Date From DatePickerDialog");
                datePickerDialog.show(getActivity().getFragmentManager(), "DatePickerDialog");
            }
        });


    }

    public void setTodayschedule() {
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

                        getTeacherAssignedSubjectAsyncTask = new GetTeacherAssignedSubjectAsyncTask(params);
                        teacherAssignedSubjectModels = getTeacherAssignedSubjectAsyncTask.execute().get();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                                if (teacherAssignedSubjectModels.size() > 0) {
                                    txtNoRecords.setVisibility(View.GONE);
                                } else {
                                    progressDialog.dismiss();
                                    txtNoRecords.setVisibility(View.VISIBLE);
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

        test_date.setText(d + "/" + m + "/" + y);
    }

    public void fillsubjectspinner() {
        ArrayList<String> row = new ArrayList<String>();

        for (int z = 0; z < teacherAssignedSubjectModels.size(); z++) {
            row.add(teacherAssignedSubjectModels.get(z).getStandard() + " -> " + teacherAssignedSubjectModels.get(z).getSubject());
        }
        ArrayAdapter<String> adapterYear = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_dropdown_item, row);
        standard_subject_spinner.setAdapter(adapterYear);
    }

}
