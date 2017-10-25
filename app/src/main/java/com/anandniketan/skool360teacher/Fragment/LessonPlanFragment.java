package com.anandniketan.skool360teacher.Fragment;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
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
import android.widget.Toast;

import com.anandniketan.skool360teacher.Adapter.ExpandableListAdapterMarks;
import com.anandniketan.skool360teacher.Adapter.TeacherLessonPlanAdapter;
import com.anandniketan.skool360teacher.AsyncTasks.GetTeacherLessonPlanAsyncTask;
import com.anandniketan.skool360teacher.Models.LessonPlanResponse.FinalArrayLesson;
import com.anandniketan.skool360teacher.Models.LessonPlanResponse.LessonDatum;
import com.anandniketan.skool360teacher.Models.LessonPlanResponse.MainResponseLesson;
import com.anandniketan.skool360teacher.Models.NewResponse.FinalArray;
import com.anandniketan.skool360teacher.Models.TeacherLessonPlanModel;
import com.anandniketan.skool360teacher.R;
import com.anandniketan.skool360teacher.Utility.Utility;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class LessonPlanFragment extends Fragment {

    public LessonPlanFragment() {
        // Required empty public constructor
    }

    private Context mContext;
    private View rootView;
    private ProgressDialog progressDialog = null;
    private GetTeacherLessonPlanAsyncTask getTeacherLessonPlanAsyncTask = null;
    private ArrayList<TeacherLessonPlanModel> teacherLessonPlanModels = new ArrayList<>();
    TeacherLessonPlanAdapter teacherLessonPlanAdapter = null;
    private ListView lesson_list;
    private LinearLayout header_linear;
    private TextView txtNoRecordslessonplan;
    private Spinner class_spinner;
    private Button btnBacktest_lessonplan;
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
        header_linear = (LinearLayout) rootView.findViewById(R.id.header_linear);
        txtNoRecordslessonplan = (TextView) rootView.findViewById(R.id.txtNoRecordslessonplan);
        lesson_list = (ListView) rootView.findViewById(R.id.lesson_list);
        class_spinner = (Spinner) rootView.findViewById(R.id.class_spinner);
        btnBacktest_lessonplan = (Button) rootView.findViewById(R.id.btnBacktest_lessonplan);
    }

    public void setListner() {
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
                                    fillspinner();

                                } else {
                                    progressDialog.dismiss();
                                    txtNoRecordslessonplan.setVisibility(View.VISIBLE);
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
        ArrayAdapter<String> adapterYear = new ArrayAdapter<String>(mContext, R.layout.spinner_layout, row);
        class_spinner.setAdapter(adapterYear);
    }

}
