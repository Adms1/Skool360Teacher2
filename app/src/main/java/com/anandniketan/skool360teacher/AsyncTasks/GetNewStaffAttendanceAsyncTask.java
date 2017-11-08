package com.anandniketan.skool360teacher.AsyncTasks;

import android.os.AsyncTask;

import com.anandniketan.skool360teacher.Models.Attendance.StaffNewAttendenceModel;
import com.anandniketan.skool360teacher.Utility.AppConfiguration;
import com.anandniketan.skool360teacher.WebServicesCall.WebServicesCall;
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

