package com.anandniketan.skool360teacher.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.anandniketan.skool360teacher.Activities.DashBoardActivity;
import com.anandniketan.skool360teacher.Adapter.ExpandableListAdapterMarks;
import com.anandniketan.skool360teacher.AsyncTasks.TeacherGetTestMarksAsyncTask;
import com.anandniketan.skool360teacher.Models.TeacherGetTestMarksModel;
import com.anandniketan.skool360teacher.R;
import com.anandniketan.skool360teacher.Utility.Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MarksFragment extends Fragment {
    private View rootView;
    private Button btnMenu, btnBackMarks;
    private TextView txtNoRecordsMarks;
    private Context mContext;
    private ProgressDialog progressDialog = null;
    private TeacherGetTestMarksAsyncTask getTestMarksAsyncTask = null;
    private ArrayList<TeacherGetTestMarksModel> teacherGetTestMarksModels = new ArrayList<>();
    private int lastExpandedPosition = -1;
    private LinearLayout Marks_header, class_linear, search_linear;
    private Spinner class_spinner;
    private ImageView search_img;
    private EditText search_edt;
    boolean searchflag = false;

    ExpandableListAdapterMarks listAdapterMarks;
    ExpandableListView lvExpMarks;
    List<String> listDataHeader;
    HashMap<String, ArrayList<TeacherGetTestMarksModel.studentDetail.TestDetail.subjectMarks>> listDataChild;
    String spinnerSelectedValue, value;

    public MarksFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_marks, container, false);
        mContext = getActivity();

        initViews();
        setListners();

        return rootView;
    }

    public void initViews() {
        btnMenu = (Button) rootView.findViewById(R.id.btnMenu);
        txtNoRecordsMarks = (TextView) rootView.findViewById(R.id.txtNoRecordsMarks);
        btnBackMarks = (Button) rootView.findViewById(R.id.btnBackMarks);
        lvExpMarks = (ExpandableListView) rootView.findViewById(R.id.lvExpMarks);
        Marks_header = (LinearLayout) rootView.findViewById(R.id.Marks_header);
        class_linear = (LinearLayout) rootView.findViewById(R.id.class_linear);
        search_linear = (LinearLayout) rootView.findViewById(R.id.search_linear);
        class_spinner = (Spinner) rootView.findViewById(R.id.class_spinner);
        search_img = (ImageView) rootView.findViewById(R.id.search_img);
        search_edt = (EditText) rootView.findViewById(R.id.search_edt);

        getMarksData();
    }

    public void setListners() {
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DashBoardActivity.onLeft();
            }
        });

        btnBackMarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new HomeFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .setCustomAnimations(0, 0)
                        .replace(R.id.frame_container, fragment).commit();
            }
        });
        lvExpMarks.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpandedPosition != -1
                        && groupPosition != lastExpandedPosition) {
                    lvExpMarks.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = groupPosition;
            }
        });
        class_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int j, long l) {
//                Toast.makeText(adapterView.getContext(),
//                        "On Item Select : \n" + adapterView.getItemAtPosition(j).toString(),
//                        Toast.LENGTH_LONG).show();
                spinnerSelectedValue = adapterView.getItemAtPosition(j).toString();
                Log.d("spinner", spinnerSelectedValue);
                for (int i = 0; i < teacherGetTestMarksModels.get(0).getGetstudentDetail().size(); i++) {
                    value = teacherGetTestMarksModels.get(0).getGetstudentDetail().get(i).getStandardClass() + " -> "
                            + teacherGetTestMarksModels.get(0).getGetstudentDetail().get(i).getTestName();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        search_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (searchflag == false) {
                    search_linear.setVisibility(View.VISIBLE);
                    searchflag = true;
                } else {
                    search_linear.setVisibility(View.GONE);
                    searchflag = false;
                }
            }
        });
        search_edt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> temprray = new ArrayList<>();
                for (int i = 0; i < teacherGetTestMarksModels.get(0).getGetstudentDetail().size(); i++) {
                    for(int j=0;j<teacherGetTestMarksModels.get(i).getGetstudentDetail().get(i).getGettestDetail().size();j++)
                    if(teacherGetTestMarksModels.get(i).getGetstudentDetail().get(i).getGettestDetail().get(j).getStudentName().equalsIgnoreCase(search_edt.getText().toString())){
                        temprray.add(teacherGetTestMarksModels.get(0).getGetstudentDetail().get(i).getGettestDetail().get(j).getStudentName());
                    }
                }
//                listAdapterMarks = new ExpandableListAdapterMarks(this, temprray);
//                lvExpMarks.setAdapter(listAdapterMarks);
//                listAdapterMarks.notifyDataSetChanged();
            }
        });
    }

    public void getMarksData() {
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
                        getTestMarksAsyncTask = new TeacherGetTestMarksAsyncTask(params);
                        teacherGetTestMarksModels = getTestMarksAsyncTask.execute().get();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                                if (teacherGetTestMarksModels.size() > 0) {
                                    txtNoRecordsMarks.setVisibility(View.GONE);
                                    fillspinner();
//                                    prepaareList();
                                    listAdapterMarks = new ExpandableListAdapterMarks(getActivity(), listDataHeader, listDataChild);
                                    lvExpMarks.setAdapter(listAdapterMarks);
                                } else {
                                    progressDialog.dismiss();
                                    txtNoRecordsMarks.setVisibility(View.VISIBLE);
                                    Marks_header.setVisibility(View.GONE);
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

//    public void prepaareList() {
//        listDataHeader = new ArrayList<String>();
//        listDataChild = new HashMap<String, ArrayList<TeacherGetTestMarksModel.studentDetail.TestDetail.subjectMarks>>();
//        Marks_header.setVisibility(View.VISIBLE);
//        search_img.setVisibility(View.VISIBLE);
////        search_linear.setVisibility(View.VISIBLE);
//        for (int i = 0; i < teacherGetTestMarksModels.get(0).getGetstudentDetail().size(); i++) {
//            if (teacherGetTestMarksModels.get(0).getGetstudentDetail().get(i).getGettestDetail().size() > 0) {
////                Marks_header.setVisibility(View.VISIBLE);
//
//                for (int j = 0; j < teacherGetTestMarksModels.get(0).getGetstudentDetail().get(i).getGettestDetail().size(); j++) {
//                    Marks marksdemo = new Marks();
//                    marksdemo.studentname = teacherGetTestMarksModels.get(0).getGetstudentDetail().get(i).getGettestDetail().get(j).getStudentName();
//                    marksdemo.grno = teacherGetTestMarksModels.get(0).getGetstudentDetail().get(i).getGettestDetail().get(j).getGRNO();
//                    marksdemo.percentage = teacherGetTestMarksModels.get(0).getGetstudentDetail().get(i).getGettestDetail().get(j).getPercentage();
//                    listDataHeader.add(marksdemo.studentname.toString() + "|" + marksdemo.grno.toString() + "|" + marksdemo.percentage);
//                    ArrayList<TeacherGetTestMarksModel.studentDetail.TestDetail.subjectMarks> rows = new ArrayList<TeacherGetTestMarksModel.studentDetail.TestDetail.subjectMarks>();
//                    for (int k = 0; k < teacherGetTestMarksModels.get(0).getGetstudentDetail().get(i).getGettestDetail().get(j).getGetsubjectMarks().size(); k++) {
//                        rows.add(teacherGetTestMarksModels.get(0).getGetstudentDetail().get(i).getGettestDetail().get(j).getGetsubjectMarks().get(k));
//                    }
//                    Log.d("row", rows.toString());
//                    listDataChild.put(listDataHeader.get(j), rows);
//                    Log.d("listDataChild", "" + listDataChild.size());
//                }
//            } else {
////                Marks_header.setVisibility(View.GONE);
////                search_img.setVisibility(View.GONE);
////                search_linear.setVisibility(View.GONE);
//            }
//
//        }
//    }

    public class Marks {
        private String studentname;
        private String grno;
        private String percentage;
    }

    public void fillspinner() {
        ArrayList<String> row = new ArrayList<String>();

        for (int z = 0; z < teacherGetTestMarksModels.get(0).getGetstudentDetail().size(); z++) {
            row.add(teacherGetTestMarksModels.get(0).getGetstudentDetail().get(z).getStandardClass() + " -> " + teacherGetTestMarksModels.get(0).getGetstudentDetail().get(z).getTestName());
        }
        ArrayAdapter<String> adapterYear = new ArrayAdapter<String>(mContext, R.layout.spinner_layout, row);
        class_spinner.setAdapter(adapterYear);
    }
}
