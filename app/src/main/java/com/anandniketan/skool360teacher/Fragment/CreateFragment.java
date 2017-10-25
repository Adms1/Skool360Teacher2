package com.anandniketan.skool360teacher.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.anandniketan.skool360teacher.Adapter.ListAdapterCreate;
import com.anandniketan.skool360teacher.AsyncTasks.TeacherGetClassSubjectWiseStudentAsyncTask;
import com.anandniketan.skool360teacher.Models.PTMCreateResponse.FinalArrayStudentForCreate;
import com.anandniketan.skool360teacher.Models.PTMCreateResponse.MainResponseDisplayStudent;
import com.anandniketan.skool360teacher.Models.PTMCreateResponse.StudentDatum;
import com.anandniketan.skool360teacher.R;
import com.anandniketan.skool360teacher.Utility.Utility;

import java.io.UTFDataFormatException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class CreateFragment extends Fragment {
    private View rootView;
    private TextView txtNoRecordsCreate,txtNoRecordsCreateStudent;
    private Context mContext;
    private ProgressDialog progressDialog = null;
    private TeacherGetClassSubjectWiseStudentAsyncTask getClassSubjectWiseStudentAsyncTask = null;
    private LinearLayout Create_header, Create_class_linear;
    private Spinner Create_class_spinner;
    private ListView lvCreate;
    ListAdapterCreate listAdapterCreate;

    private ArrayList<StudentDatum> arrayList;
    String spinnerSelectedValue, value;
    MainResponseDisplayStudent response;

    public CreateFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_create, container, false);
        mContext = getActivity();

        initViews();
        setListners();

        return rootView;
    }

    public void initViews() {
        txtNoRecordsCreate = (TextView) rootView.findViewById(R.id.txtNoRecordsCreate);
        txtNoRecordsCreateStudent=(TextView)rootView.findViewById(R.id.txtNoRecordsCreateStudent);
        Create_header = (LinearLayout) rootView.findViewById(R.id.Create_header);
        Create_class_linear = (LinearLayout) rootView.findViewById(R.id.Create_class_linear);
        Create_class_spinner = (Spinner) rootView.findViewById(R.id.Create_class_spinner);
        lvCreate = (ListView) rootView.findViewById(R.id.lvCreate);
        setUserVisibleHint(true);


    }

    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && rootView != null) {
            getStudentData();
        }
        // execute your data loading logic.
    }

    public void setListners() {
        Create_class_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int j, long l) {
                spinnerSelectedValue = adapterView.getItemAtPosition(j).toString();
                Log.d("spinner", spinnerSelectedValue);
                String[] array = spinnerSelectedValue.split("-");
                String value = array[2].replaceFirst(">", "");
                Log.d("Array", Arrays.toString(array));
                Log.d("Array1", value);
                List<FinalArrayStudentForCreate> filterFinalArray = new ArrayList<FinalArrayStudentForCreate>();
                for (FinalArrayStudentForCreate arrayObj : response.getFinalArraycreate()) {
                    if (arrayObj.getStandard().equalsIgnoreCase(array[0].trim()) &&
                            arrayObj.getClassname().equalsIgnoreCase(array[1].trim()) && arrayObj.getSubject().equalsIgnoreCase(value.trim())) {
                        filterFinalArray.add(arrayObj);
                    }
                }
                setExpandableListView(filterFinalArray);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    public void getStudentData() {
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
                        getClassSubjectWiseStudentAsyncTask = new TeacherGetClassSubjectWiseStudentAsyncTask(params);
                        response = getClassSubjectWiseStudentAsyncTask.execute().get();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                                if (response.getFinalArraycreate().size() > 0) {
                                    txtNoRecordsCreate.setVisibility(View.GONE);
                                    fillspinner();
                                } else {
                                    progressDialog.dismiss();
                                    txtNoRecordsCreate.setVisibility(View.VISIBLE);
                                    Create_header.setVisibility(View.GONE);
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

    private void setExpandableListView(List<FinalArrayStudentForCreate> array) {
        arrayList = new ArrayList<>();
        arrayList.clear();

        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).getStudentData().size() > 0) {
                Create_header.setVisibility(View.VISIBLE);
                txtNoRecordsCreateStudent.setVisibility(View.GONE);
                for (int j = 0; j < array.get(i).getStudentData().size(); j++) {
                    arrayList.add(array.get(i).getStudentData().get(j));
                }

                Log.d("arrayList", "" + arrayList.size());
            }else {
                txtNoRecordsCreateStudent.setVisibility(View.VISIBLE);
                Create_header.setVisibility(View.GONE);
            }
        }
        listAdapterCreate = new ListAdapterCreate(getActivity(), arrayList, getActivity().getFragmentManager());
        lvCreate.setAdapter(listAdapterCreate);
        lvCreate.deferNotifyDataSetChanged();
        }

    public void fillspinner() {
        ArrayList<String> row = new ArrayList<String>();

        for (int z = 0; z < response.getFinalArraycreate().size(); z++) {
            row.add(response.getFinalArraycreate().get(z).getStandard() + "-" +
                    response.getFinalArraycreate().get(z).getClassname() + "->" +
                    response.getFinalArraycreate().get(z).getSubject());
        }
        ArrayAdapter<String> adapterYear = new ArrayAdapter<String>(mContext, R.layout.spinner_layout, row);
        Create_class_spinner.setAdapter(adapterYear);
    }
}
