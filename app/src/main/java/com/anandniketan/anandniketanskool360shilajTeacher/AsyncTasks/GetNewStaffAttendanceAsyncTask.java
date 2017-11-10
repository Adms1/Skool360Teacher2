package com.anandniketan.anandniketanskool360shilajTeacher.AsyncTasks;

import android.os.AsyncTask;

import com.anandniketan.anandniketanskool360shilajTeacher.Models.Attendance.StaffNewAttendenceModel;
import com.anandniketan.anandniketanskool360shilajTeacher.Utility.AppConfiguration;
import com.anandniketan.anandniketanskool360shilajTeacher.WebServicesCall.WebServicesCall;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * Created by admsandroid on 11/8/2017.
 */

public class GetNewStaffAttendanceAsyncTask extends AsyncTask<Void, Void, StaffNewAttendenceModel> {
    HashMap<String, String> param = new HashMap<String, String>();

    public GetNewStaffAttendanceAsyncTask(HashMap<String, String> param) {
        this.param = param;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected StaffNewAttendenceModel doInBackground(Void... params) {
        String responseString = null;
        StaffNewAttendenceModel staffNewAttendenceModel = null;
        try {
            responseString = WebServicesCall.RunScript(AppConfiguration.getUrl(AppConfiguration.GetStaffAttendence), param);
            Gson gson = new Gson();
            staffNewAttendenceModel = gson.fromJson(responseString, StaffNewAttendenceModel.class);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return staffNewAttendenceModel;
    }

    @Override
    protected void onPostExecute(StaffNewAttendenceModel result) {
        super.onPostExecute(result);
    }
}

