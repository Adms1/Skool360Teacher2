package com.anandniketan.anandniketanskool360shilajTeacher.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.anandniketan.anandniketanskool360shilajTeacher.Activities.LoginActivity;
import com.anandniketan.anandniketanskool360shilajTeacher.Adapter.ConsistentAbsentListAdapter;
import com.anandniketan.anandniketanskool360shilajTeacher.AsyncTasks.GetConsistentAbAsyncTask;
import com.anandniketan.anandniketanskool360shilajTeacher.AsyncTasks.InsertConsistentAbSMSAsyncTask;
import com.anandniketan.anandniketanskool360shilajTeacher.Interfacess.getCheckconsistentAb;
import com.anandniketan.anandniketanskool360shilajTeacher.Models.AllAttendance.GetConsistentAbModel;
import com.anandniketan.anandniketanskool360shilajTeacher.Models.AllAttendance.InsertConsistentAbSMSModel;
import com.anandniketan.anandniketanskool360shilajTeacher.R;
import com.anandniketan.anandniketanskool360shilajTeacher.Utility.AppConfiguration;
import com.anandniketan.anandniketanskool360shilajTeacher.Utility.Utility;

import com.bumptech.glide.Glide;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class ConsistentAbsentFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    public ConsistentAbsentFragment() {
        // Required empty public constructor
    }

    private Context mContext;
    private View rootView;
    private ProgressDialog progressDialog = null;
    private TextView txtNoRecordsconsistentabsent;
    private Button btnBacktest_consistent_absent, btnLogout;
    private LinearLayout consistent_absent_header;
    private ListView consistent_absent_list;
    private ImageView insert_message_img;

    //use for fill listview
    private GetConsistentAbAsyncTask getConsistentAbAsyncTask = null;
    private GetConsistentAbModel getConsistentAbModelResponse;
    private ConsistentAbsentListAdapter consistentAbsentListAdapter;

    //use for insert sms
    private AlertDialog alertDialogAndroid = null;
    private EditText insert_consistent_Message_txt;
    private Button send_consistent_message_btn, close_btn;
    private static TextView insert_message_date_txt;
    private DatePickerDialog datePickerDialog;
    int Year, Month, Day;
    Calendar calendar;
    int mYear, mMonth, mDay;


    //use for send sms
    private InsertConsistentAbSMSAsyncTask insertConsistentAbSMSAsyncTask = null;
    private InsertConsistentAbSMSModel insertConsistentAbSMSModelResponse;
    String finalStudentIdArray, finalStudentmobileno;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_consistent_absent, container, false);
        mContext = getActivity();
        init();
        setListner();
        setConsistentAbData();
        return rootView;
    }

    public void init() {
        txtNoRecordsconsistentabsent = (TextView) rootView.findViewById(R.id.txtNoRecordsconsistentabsent);
        btnBacktest_consistent_absent = (Button) rootView.findViewById(R.id.btnBacktest_consistent_absent);
        btnLogout = (Button) rootView.findViewById(R.id.btnLogout);
        consistent_absent_header = (LinearLayout) rootView.findViewById(R.id.consistent_absent_header);
        consistent_absent_list = (ListView) rootView.findViewById(R.id.consistent_absent_list);
        insert_message_img = (ImageView) rootView.findViewById(R.id.insert_message_img);
        Glide.with(mContext)
                .load(AppConfiguration.DOMAIN_LIVE_ICONS+"Done.png")
                .fitCenter()
                .into(insert_message_img);
    }

    public void setListner() {
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new android.app.AlertDialog.Builder(new android.view.ContextThemeWrapper(getActivity(), R.style.AppTheme))
                        .setCancelable(false)
                        .setTitle("Logout")
                        .setIcon(mContext.getResources().getDrawable(R.drawable.ic_launcher))
                        .setMessage("Are you sure you want to logout? ")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Utility.setPref(mContext, "StaffID", "");
                                Utility.setPref(mContext, "Emp_Code", "");
                                Utility.setPref(mContext, "Emp_Name", "");
                                Utility.setPref(mContext, "DepratmentID", "");
                                Utility.setPref(mContext, "DesignationID", "");
                                Utility.setPref(mContext, "DeviceId", "");
                                Utility.setPref(mContext, "unm", "");
                                Utility.setPref(mContext, "pwd", "");
                                Utility.setPref(mContext, "LoginType", "");

                                Intent i = new Intent(getActivity(), LoginActivity.class);
                                getActivity().startActivity(i);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(R.drawable.ic_launcher)
                        .show();
            }
        });
        btnBacktest_consistent_absent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new OtherLoginHomeFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .setCustomAnimations(0, 0)
                        .replace(R.id.frame_container, fragment).commit();
            }
        });
        insert_message_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendMessage();
            }
        });
    }

    public void setConsistentAbData() {
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

                        getConsistentAbAsyncTask = new GetConsistentAbAsyncTask(params);
                        getConsistentAbModelResponse = getConsistentAbAsyncTask.execute().get();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                                if (getConsistentAbModelResponse.getFinalArray().size() > 0) {
                                    txtNoRecordsconsistentabsent.setVisibility(View.GONE);
                                    consistent_absent_header.setVisibility(View.VISIBLE);
                                    for (int i = 0; i < getConsistentAbModelResponse.getFinalArray().size(); i++) {
                                        getConsistentAbModelResponse.getFinalArray().get(i).setCheckstatus("0");
                                    }
                                    consistentAbsentListAdapter = new ConsistentAbsentListAdapter(getActivity(), getConsistentAbModelResponse, new getCheckconsistentAb() {
                                        @Override
                                        public void getCheckconsistentAb() {

                                            GetConsistentAbModel updatedData = consistentAbsentListAdapter.getDatas();
                                            Boolean data = false;
                                            for (int i = 0; i <updatedData.getFinalArray().size(); i++) {
                                                if (updatedData.getFinalArray().get(i).getCheckstatus().equalsIgnoreCase("1")) {
                                                    data = true;
                                                    Log.d("Position , Checked or not", "" + i + " : " + updatedData.getFinalArray().get(i).getCheckstatus());
                                                }
//                                                else {
//                                                    data = false;
//                                                }
                                            }
                                            if (data) {
                                                insert_message_img.setVisibility(View.VISIBLE);
                                            } else {
                                                insert_message_img.setVisibility(View.GONE);
                                            }

                                        }
                                    });
                                    consistent_absent_list.setAdapter(consistentAbsentListAdapter);
                                    consistent_absent_list.deferNotifyDataSetChanged();
                                } else {
                                    progressDialog.dismiss();
                                    txtNoRecordsconsistentabsent.setVisibility(View.VISIBLE);
                                    consistent_absent_header.setVisibility(View.GONE);
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

    public void SendMessage() {
        LayoutInflater lInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View layout = lInflater.inflate(R.layout.consistent_message_popup, null);

        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(mContext);
        alertDialogBuilderUserInput.setView(layout);

        alertDialogAndroid = alertDialogBuilderUserInput.create();
        alertDialogAndroid.setCancelable(false);
        alertDialogAndroid.show();
        Window window = alertDialogAndroid.getWindow();
        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.CENTER;
        wlp.flags = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        window.setAttributes(wlp);
        alertDialogAndroid.show();

        insert_message_date_txt = (TextView) layout.findViewById(R.id.insert_message_date_txt);
        insert_consistent_Message_txt = (EditText) layout.findViewById(R.id.insert_consistent_Message_txt);
        send_consistent_message_btn = (Button) layout.findViewById(R.id.send_consistent_message_btn);
        close_btn = (Button) layout.findViewById(R.id.close_btn);

        insert_message_date_txt.setText(Utility.getTodaysDate());


        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogAndroid.dismiss();
            }
        });

        insert_message_date_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog = DatePickerDialog.newInstance(ConsistentAbsentFragment.this, Year, Month, Day);
                datePickerDialog.setThemeDark(false);
                datePickerDialog.setOkText("Done");
                datePickerDialog.showYearPickerFirst(false);
                datePickerDialog.setAccentColor(Color.parseColor("#1B88C8"));
                datePickerDialog.setTitle("Select Date From DatePickerDialog");
                datePickerDialog.show(getActivity().getFragmentManager(), "DatePickerDialog");
            }
        });
        send_consistent_message_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> id = new ArrayList<>();
                final String messageDate, messageSubject, messageMessageLine;
                ArrayList<String> array = consistentAbsentListAdapter.getData();
                for (int j = 0; j < array.size(); j++) {
                    id.add(array.get(j).toString());
                }
                finalStudentIdArray = String.valueOf(id);
                finalStudentIdArray = finalStudentIdArray.substring(1, finalStudentIdArray.length() - 1);

                ArrayList<String> mobileno = new ArrayList<>();

                ArrayList<String> array1 = consistentAbsentListAdapter.getMobileData();
                for (int j = 0; j < array1.size(); j++) {
                    mobileno.add(array1.get(j).toString());
                }
                finalStudentmobileno = String.valueOf(mobileno);
                finalStudentmobileno = finalStudentmobileno.substring(1, finalStudentmobileno.length() - 1);
                Log.d("finalStudentmobileno", finalStudentmobileno);

                messageDate = insert_message_date_txt.getText().toString();
                messageMessageLine = insert_consistent_Message_txt.getText().toString();
                Log.d("StudentArray", "" + id);


                if (Utility.isNetworkConnected(mContext)) {
                    if (!finalStudentIdArray.equalsIgnoreCase("") && !messageDate.equalsIgnoreCase("") && !messageMessageLine.equalsIgnoreCase("") && !finalStudentmobileno.equalsIgnoreCase("")) {
                        progressDialog = new ProgressDialog(mContext);
                        progressDialog.setMessage("Please Wait...");
                        progressDialog.setCancelable(false);
                        progressDialog.show();


                        Log.d("finalStudentIdArray", finalStudentIdArray);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    HashMap<String, String> params = new HashMap<String, String>();
                                    params.put("StaffID", Utility.getPref(mContext, "StaffID"));
                                    params.put("StudentID", finalStudentIdArray);
                                    params.put("Date", messageDate);
                                    params.put("MobileNo", finalStudentmobileno);
                                    params.put("SMS", messageMessageLine);

                                    insertConsistentAbSMSAsyncTask = new InsertConsistentAbSMSAsyncTask(params);
                                    insertConsistentAbSMSModelResponse = insertConsistentAbSMSAsyncTask.execute().get();
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            progressDialog.dismiss();
                                            if (insertConsistentAbSMSModelResponse.getSuccess().equalsIgnoreCase("True")) {
                                                Utility.ping(mContext, "Send Sucessfully");
                                                alertDialogAndroid.dismiss();
                                            } else {
                                                progressDialog.dismiss();

                                            }
                                        }
                                    });
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                    } else {
                        Utility.ping(mContext, "Blank field not allowed.");
                    }
                } else {
                    Utility.ping(mContext, "Network not available");
                }
            }
        });

    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
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

        insert_message_date_txt.setText(d + "/" + m + "/" + y);
    }
}
