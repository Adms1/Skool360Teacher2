package com.anandniketan.skool360teacher.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.anandniketan.skool360teacher.Adapter.StudentAssignesubjectAdapter;
import com.anandniketan.skool360teacher.AsyncTasks.GetTeacherGetAssignStudentSubjectAsyncTask;
import com.anandniketan.skool360teacher.AsyncTasks.TeacherInsertAssignStudentSubjectAsyncTask;
import com.anandniketan.skool360teacher.Models.StudentAssignSubjectResponse.MainResponseStudentSubject;
import com.anandniketan.skool360teacher.Models.StudentAssignSubjectResponse.StudentSubject;
import com.anandniketan.skool360teacher.Models.TeacherInsertAssignStudentSubjectModel.TeacherInsertSubjectMainResponse;
import com.anandniketan.skool360teacher.R;
import com.anandniketan.skool360teacher.Utility.AppConfiguration;
import com.anandniketan.skool360teacher.Utility.Utility;

import java.util.ArrayList;
import java.util.HashMap;


public class StudentAssignesubject extends Fragment implements CompoundButton.OnCheckedChangeListener {

    public StudentAssignesubject() {
        // Required empty public constructor
    }

    private Context mContext;
    private View rootView;
    private ProgressDialog progressDialog = null;
    private GetTeacherGetAssignStudentSubjectAsyncTask getTeacherGetAssignStudentSubjectAsyncTask = null;
    StudentAssignesubjectAdapter studentAssignesubjectAdapter = null;
    private ListView studentassignesubject_list;
    private LinearLayout header_linear;
    private TextView txtNoRecords, standard_txt, class_txt;
    private LinearLayout class_checkbox_linear, standard_checkbox_linear;
    MainResponseStudentSubject mainResponseStudentSubject;
    ArrayList<String> standardIdarray;
    ArrayList<String> classarray;
    String ClassId, standtext, classtext;
    private CheckBox standardcheckBox, classcheckBox;
    private int selectedPosition = -1;
    private TextView class_text_chk, stand_text_chk;
    private ImageView insert_subject_img;
    private ArrayList<StudentSubject> studentsubjectarrayList;
    private ArrayList<String> listDatastudentName;
    ArrayList<String> newArray;
    String res = "", studentsubjectIdstr;

    private TeacherInsertAssignStudentSubjectAsyncTask teacherInsertAssignStudentSubjectAsyncTask = null;
    TeacherInsertSubjectMainResponse teacherInsertSubjectMainResponse;

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_student_assignesubject, container, false);
        mContext = getActivity();

        init();
        setListner();


        return rootView;
    }

    public void init() {

        header_linear = (LinearLayout) rootView.findViewById(R.id.header_linear);
        txtNoRecords = (TextView) rootView.findViewById(R.id.txtNoRecords);
        studentassignesubject_list = (ListView) rootView.findViewById(R.id.studentassignesubject_list);
        standard_txt = (TextView) rootView.findViewById(R.id.standard_txt);
        class_txt = (TextView) rootView.findViewById(R.id.standard_txt);
        class_checkbox_linear = (LinearLayout) rootView.findViewById(R.id.class_checkbox_linear);
        standard_checkbox_linear = (LinearLayout) rootView.findViewById(R.id.standard_checkbox_linear);
        insert_subject_img = (ImageView) rootView.findViewById(R.id.insert_subject_img);

        setUserVisibleHint(true);

    }

    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && rootView != null) {
            setStandardandClass();
            setTodayschedule();
        }
        // execute your data loading logic.
    }

    public void setListner() {
        insert_subject_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertTeacherAssignSubject();
            }
        });
    }

    public void setStandardandClass() {
        standardIdarray = new ArrayList<String>();
        classarray = new ArrayList<String>();
        for (int i = 0; i < AppConfiguration.rows.size(); i++) {
            standardIdarray.add(AppConfiguration.rows.get(i).getStandard());
            classarray.add(AppConfiguration.rows.get(i).getClasses());
            ClassId = AppConfiguration.rows.get(0).getClassID();
        }
        DisplayStandardClass();
    }

    public void DisplayStandardClass() {
        if (standard_checkbox_linear.getChildCount() > 0 && class_checkbox_linear.getChildCount() > 0) {
            standard_checkbox_linear.removeAllViews();
            class_checkbox_linear.removeAllViews();
        }
        try {
            for (int j = 0; j < standardIdarray.size(); j++) {
                View convertView = LayoutInflater.from(mContext).inflate(R.layout.student_standard_radiobutton, null);
                standardcheckBox = (CheckBox) convertView.findViewById(R.id.standard_checkbox);
                stand_text_chk = (TextView) convertView.findViewById(R.id.stand_text_chk);
                standardcheckBox.setText(standardIdarray.get(j));
                stand_text_chk.setText(standardIdarray.get(j));
                Log.d("standard", standardIdarray.get(j));
//                textView.setText(arrayList.get(i));

                if (j == 0) {
                    standardcheckBox.setChecked(true);
                    standardcheckBox.setClickable(false);
                }
                standardcheckBox.setOnCheckedChangeListener(this);
                standard_checkbox_linear.addView(convertView);
            }
            for (int k = 0; k < classarray.size(); k++) {
                View convertView = LayoutInflater.from(mContext).inflate(R.layout.student_class_radiobutton, null);
                classcheckBox = (CheckBox) convertView.findViewById(R.id.class_checkbox);
                class_text_chk = (TextView) convertView.findViewById(R.id.class_text_chk);
                classcheckBox.setText(classarray.get(k));
                class_text_chk.setText(classarray.get(k));

                if (k == 0) {
                    classcheckBox.setChecked(true);
                    classcheckBox.setClickable(false);
                }

                classcheckBox.setOnCheckedChangeListener(this);
                class_checkbox_linear.addView(convertView);
            }

        } catch (Exception e) {
        }

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
                        params.put("ClassID", ClassId);

                        getTeacherGetAssignStudentSubjectAsyncTask = new GetTeacherGetAssignStudentSubjectAsyncTask(params);
                        mainResponseStudentSubject = getTeacherGetAssignStudentSubjectAsyncTask.execute().get();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                                if (mainResponseStudentSubject.getFinalArray().size() > 0) {
                                    txtNoRecords.setVisibility(View.GONE);
                                    setListData();
                                    insert_subject_img.setVisibility(View.VISIBLE);
                                } else {
                                    progressDialog.dismiss();
                                    txtNoRecords.setVisibility(View.VISIBLE);
                                    insert_subject_img.setVisibility(View.GONE);
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
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            switch (buttonView.getId()) {
                case R.id.standard_checkbox:
                    standtext = stand_text_chk.getText().toString();
                    Log.d("stantext", standtext);
                    break;
                case R.id.class_checkbox:
                    classtext = class_text_chk.getText().toString();
                    Log.d("classtext", classtext);
                    break;
                default:
            }
            getClassId();
        }
    }

    public void getClassId() {
        for (int m = 0; m < AppConfiguration.rows.size(); m++) {
            if (standtext.equalsIgnoreCase(AppConfiguration.rows.get(m).getStandard()) ||
                    classtext.equalsIgnoreCase(AppConfiguration.rows.get(m).getClasses())) {
                ClassId = AppConfiguration.rows.get(m).getClassID();
                Log.d("ClassId", ClassId);
            }
        }
        setTodayschedule();
    }


    public void setListData() {
        listDatastudentName = new ArrayList<String>();
        studentsubjectarrayList = new ArrayList<>();
        studentsubjectarrayList.clear();
        listDatastudentName.clear();

        for (int i = 0; i < mainResponseStudentSubject.getFinalArray().size(); i++) {
            listDatastudentName.add(mainResponseStudentSubject.getFinalArray().get(i).getStudentName());

        }
        for (int j = 0; j < mainResponseStudentSubject.getFinalArray().get(0).getStudentSubject().size(); j++) {
            studentsubjectarrayList.add(mainResponseStudentSubject.getFinalArray().get(0).getStudentSubject().get(j));
        }
        studentAssignesubjectAdapter = new StudentAssignesubjectAdapter(getActivity(), listDatastudentName, mainResponseStudentSubject);
        studentassignesubject_list.setAdapter(studentAssignesubjectAdapter);
        studentassignesubject_list.deferNotifyDataSetChanged();
    }


    public void InsertTeacherAssignSubject() {
        ArrayList<String> newArray = new ArrayList<>();
        for (int i = 0; i < studentassignesubject_list.getCount(); i++) {
            String customString = "";
            View childView = studentassignesubject_list.getChildAt(i);
            GridView gd = (GridView) childView.findViewById(R.id.subject_grid_view);
            boolean isFirstAdded = false;
            for (int j = 0; j < gd.getChildCount(); j++) {
                View gridViewChildView = gd.getChildAt(j);
                CheckBox checkBox = (CheckBox) gridViewChildView.findViewById(R.id.check_subject);
                if (checkBox.isChecked()) {
                    if (!isFirstAdded) {
                        customString = String.valueOf(mainResponseStudentSubject.getFinalArray().get(i).getStudentID());
                        customString = customString + "," + String.valueOf(checkBox.getTag());
                        isFirstAdded = true;
                    } else {
                        customString = customString + "," + String.valueOf(checkBox.getTag());
                    }
                }
            }
            newArray.add(customString);
        }
        String responseString = "";
        for (String s : newArray) {
            responseString = responseString + "|" + s;
        }
        Log.d("responseString ", responseString);
    }
//        newArray = new ArrayList<>();
//        newArray.clear();
//        res = "";
//        boolean isFirstAdded = false;
//        newArray = new ArrayList<>();
//
//        for (int i = 0; i < studentassignesubject_list.getChildCount(); i++) {
//            String customString = String.valueOf(mainResponseStudentSubject.getFinalArray().get(i).getStudentID());
//            View childView = studentassignesubject_list.getChildAt(i);
//            GridView gd = (GridView) childView.findViewById(R.id.subject_grid_view);
//            for (int j = 0; j < gd.getChildCount(); j++) {
//                View gridViewChildView = gd.getChildAt(j);
//                CheckBox checkBox = (CheckBox) gridViewChildView.findViewById(R.id.check_subject);
//                if (checkBox.isChecked()) {
//                    if (!isFirstAdded) {
//                        customString = customString + "," + String.valueOf(checkBox.getTag());
//                        Log.d("ifcustomString ", customString);
//                        isFirstAdded = true;
//                    } else {
//                        customString = customString + "|" + String.valueOf(checkBox.getTag());
//                        Log.d("elsecustomString ", customString);
////                            isFirstAdded = true;
//                    }
//                }
//
//            }
//
//            newArray.add(customString);
//
//            Log.d("newArray ", newArray.toString());
//
//        }
//        studentsubjectIdstr = res.replaceFirst("\\|", "");
//        Log.d("str ", studentsubjectIdstr.trim());
//        if (Utility.isNetworkConnected(mContext)) {
//            if (!ClassId.equalsIgnoreCase("") && !studentsubjectIdstr.equalsIgnoreCase("")) {
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
//                            params.put("ClassID", ClassId);
//                            params.put("StudentSubjectAry", studentsubjectIdstr);
//
//                            teacherInsertAssignStudentSubjectAsyncTask = new TeacherInsertAssignStudentSubjectAsyncTask(params);
//                            teacherInsertSubjectMainResponse = teacherInsertAssignStudentSubjectAsyncTask.execute().get();
//                            getActivity().runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    progressDialog.dismiss();
//                                    if (teacherInsertSubjectMainResponse.getFinalArray().size() >= 0) {
//                                        Utility.ping(mContext, "Save Sucessfully");
////                                        setTodayschedule();
//                                    } else {
//                                        progressDialog.dismiss();
//                                    }
//                                }
//                            });
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }).start();
//            } else {
//                Utility.ping(mContext, "Select One Subject.");
//            }
//        } else {
//            Utility.ping(mContext, "Network not available");
//        }
//    }
}
