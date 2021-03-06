package com.anandniketan.anandniketanskool360shilajTeacher.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.anandniketan.anandniketanskool360shilajTeacher.Activities.LoginActivity;
import com.anandniketan.anandniketanskool360shilajTeacher.Adapter.TeacherLessonPlanAdapter;
import com.anandniketan.anandniketanskool360shilajTeacher.AsyncTasks.GetTeacherLessonPlanAsyncTask;
import com.anandniketan.anandniketanskool360shilajTeacher.Models.LessonPlanResponse.FinalArrayLesson;
import com.anandniketan.anandniketanskool360shilajTeacher.Models.LessonPlanResponse.LessonDatum;
import com.anandniketan.anandniketanskool360shilajTeacher.Models.LessonPlanResponse.MainResponseLesson;
import com.anandniketan.anandniketanskool360shilajTeacher.R;
import com.anandniketan.anandniketanskool360shilajTeacher.Utility.Utility;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


public class LessonPlanFragment extends Fragment {

    public LessonPlanFragment() {
        // Required empty public constructor
    }

    private Context mContext;
    private View rootView;
    private ProgressDialog progressDialog = null;
    private GetTeacherLessonPlanAsyncTask getTeacherLessonPlanAsyncTask = null;
    TeacherLessonPlanAdapter teacherLessonPlanAdapter = null;
    private ListView lesson_list;
    private LinearLayout header_linear,class_linear;
    private TextView txtNoRecordslessonplan;
    private Spinner class_spinner;
    private Button btnBacktest_lessonplan,btnLogout;
    private ArrayList<LessonDatum> arrayList;
    MainResponseLesson responseLesson;

    String spinnerSelectedValue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_lesson_plan, container, false);
        mContext = getActivity();

        init();
        setListner();
        setTodayschedule();

        return rootView;
    }

    public void init() {
        class_linear=(LinearLayout)rootView.findViewById(R.id.class_linear);
        header_linear = (LinearLayout) rootView.findViewById(R.id.header_linear);
        txtNoRecordslessonplan = (TextView) rootView.findViewById(R.id.txtNoRecordslessonplan);
        lesson_list = (ListView) rootView.findViewById(R.id.lesson_list);
        class_spinner = (Spinner) rootView.findViewById(R.id.class_spinner);
        btnBacktest_lessonplan = (Button) rootView.findViewById(R.id.btnBacktest_lessonplan);
        btnLogout = (Button) rootView.findViewById(R.id.btnLogout);
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
        btnBacktest_lessonplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new HomeFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .setCustomAnimations(0, 0)
                        .replace(R.id.frame_container, fragment).commit();
            }
        });
        class_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerSelectedValue = parent.getItemAtPosition(position).toString();
                Log.d("spinner", spinnerSelectedValue);
                String[] array = spinnerSelectedValue.split("->");
                Log.d("Array", Arrays.toString(array));
                List<FinalArrayLesson> filterFinalArray = new ArrayList<FinalArrayLesson>();
                for (FinalArrayLesson arrayObj : responseLesson.getFinalArrayLesson()) {
                    if (arrayObj.getStandard().equalsIgnoreCase(array[0].trim()) && arrayObj.getSubject().equalsIgnoreCase(array[1].trim())) {
                        filterFinalArray.add(arrayObj);
                    }
                }
                setExpandableListView(filterFinalArray);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
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

                        getTeacherLessonPlanAsyncTask = new GetTeacherLessonPlanAsyncTask(params);
                        responseLesson = getTeacherLessonPlanAsyncTask.execute().get();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                                Log.d("response", "" + responseLesson.getFinalArrayLesson().size());
                                if (responseLesson.getFinalArrayLesson().size() > 0) {
                                    txtNoRecordslessonplan.setVisibility(View.GONE);
                                    class_linear.setVisibility(View.VISIBLE);
                                    fillspinner();

                                } else {
                                    progressDialog.dismiss();
                                    txtNoRecordslessonplan.setVisibility(View.VISIBLE);
                                    class_linear.setVisibility(View.GONE);
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

    private void setExpandableListView(List<FinalArrayLesson> array) {
        arrayList = new ArrayList<>();
        arrayList.clear();
        for (int i = 0; i < array.size(); i++) {
            for (int j = 0; j < array.get(i).getData().size(); j++) {
                arrayList.add(array.get(i).getData().get(j));
            }
        }
        Log.d("arrayList", "" + arrayList.toString());
        header_linear.setVisibility(View.VISIBLE);
        teacherLessonPlanAdapter = new TeacherLessonPlanAdapter(getActivity(), arrayList,getActivity().getFragmentManager());
        lesson_list.setAdapter(teacherLessonPlanAdapter);
        lesson_list.deferNotifyDataSetChanged();
    }

    public void fillspinner() {
        ArrayList<String> row = new ArrayList<String>();

        for (int z = 0; z < responseLesson.getFinalArrayLesson().size(); z++) {
            row.add(responseLesson.getFinalArrayLesson().get(z).getStandard() + " -> "
                    + responseLesson.getFinalArrayLesson().get(z).getSubject());
        }

        HashSet hs = new HashSet();
        hs.addAll(row);
        row.clear();
        row.addAll(hs);
        Log.d("row",""+row);
        Collections.sort(row);
        System.out.println("Sorted ArrayList in Java - Ascending order : " + row);
        try {
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);

            // Get private mPopup member variable and try cast to ListPopupWindow
            android.widget.ListPopupWindow popupWindow = (android.widget.ListPopupWindow) popup.get(class_spinner);

            popupWindow.setHeight(row.size() > 5 ? 500 : row.size() * 100);
        }
        catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException e) {
            // silently fail...
        }

        ArrayAdapter<String> adapterYear = new ArrayAdapter<String>(mContext, R.layout.spinner_layout, row);
        class_spinner.setAdapter(adapterYear);
    }

}
