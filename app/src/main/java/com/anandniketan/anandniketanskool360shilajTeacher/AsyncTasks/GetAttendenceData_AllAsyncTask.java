package com.anandniketan.anandniketanskool360shilajTeacher.AsyncTasks;

import android.os.AsyncTask;

import com.anandniketan.anandniketanskool360shilajTeacher.Models.AllAttendance.GetAttendenceData_AllModel;
import com.anandniketan.anandniketanskool360shilajTeacher.Models.Attendance.StaffInsertAttendenceModel;
import com.anandniketan.anandniketanskool360shilajTeacher.Utility.AppConfiguration;
import com.anandniketan.anandniketanskool360shilajTeacher.WebServicesCall.WebServicesCall;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * Created by admsandroid on 11/14/2017.
 */

public class GetAttendenceData_AllAsyncTask  extends AsyncTask<Void, Void, GetAttendenceData_AllModel> {
    HashMap<String, String> param = new HashMap<String, String>();

    public GetAttendenceData_AllAsyncTask(HashMap<String, String> param) {
        this.param = param;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected GetAttendenceData_AllModel doInBackground(Void... params) {
        String responseString = null;
        GetAttendenceData_AllModel getAttendenceData_allModel = null;
        try {
            responseString = WebServicesCall.RunScript(AppConfiguration.getUrl(AppConfiguration.GetAttendenceData_All), param);
            Gson gson = new Gson();
            getAttendenceData_allModel = gson.fromJson(responseString, GetAttendenceData_AllModel.class);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return getAttendenceData_allModel;
    }

    @Override
    protected void onPostExecute(GetAttendenceData_AllModel result) {
        super.onPostExecute(result);
    }
}

