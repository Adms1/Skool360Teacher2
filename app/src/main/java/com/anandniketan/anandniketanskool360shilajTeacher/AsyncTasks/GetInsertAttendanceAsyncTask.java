package com.anandniketan.anandniketanskool360shilajTeacher.AsyncTasks;

import android.os.AsyncTask;

import com.anandniketan.anandniketanskool360shilajTeacher.Models.Attendance.StaffInsertAttendenceModel;
import com.anandniketan.anandniketanskool360shilajTeacher.Utility.AppConfiguration;
import com.anandniketan.anandniketanskool360shilajTeacher.WebServicesCall.WebServicesCall;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * Created by admsandroid on 9/18/2017.
 */

public class GetInsertAttendanceAsyncTask extends AsyncTask<Void, Void, StaffInsertAttendenceModel> {
    HashMap<String, String> param = new HashMap<String, String>();

    public GetInsertAttendanceAsyncTask(HashMap<String, String> param) {
        this.param = param;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected StaffInsertAttendenceModel doInBackground(Void... params) {
        String responseString = null;
        StaffInsertAttendenceModel staffInsertAttendenceModel = null;
        try {
            responseString = WebServicesCall.RunScript(AppConfiguration.getUrl(AppConfiguration.GetInsertAttendance), param);
            Gson gson = new Gson();
            staffInsertAttendenceModel = gson.fromJson(responseString, StaffInsertAttendenceModel.class);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return staffInsertAttendenceModel;
    }

    @Override
    protected void onPostExecute(StaffInsertAttendenceModel result) {
        super.onPostExecute(result);
    }
}

