package com.anandniketan.skool360teacher.AsyncTasks;

import android.os.AsyncTask;

import com.anandniketan.skool360teacher.Models.StaffAttendanceModel;
import com.anandniketan.skool360teacher.Utility.AppConfiguration;
import com.anandniketan.skool360teacher.Utility.ParseJSON;
import com.anandniketan.skool360teacher.WebServicesCall.WebServicesCall;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by admsandroid on 9/18/2017.
 */

public class GetStaffAttendanceAsyncTask extends AsyncTask<Void, Void, ArrayList<StaffAttendanceModel>> {
    HashMap<String, String> param = new HashMap<String, String>();

    public GetStaffAttendanceAsyncTask(HashMap<String, String> param) {
        this.param = param;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ArrayList<StaffAttendanceModel> doInBackground(Void... params) {
        String responseString = null;
        ArrayList<StaffAttendanceModel> result = null;
        try {
            responseString = WebServicesCall.RunScript(AppConfiguration.getUrl(AppConfiguration.GetStaffAttendence), param);
            result = ParseJSON.parseStaffAttendanceJson(responseString);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(ArrayList<StaffAttendanceModel> result) {
        super.onPostExecute(result);
    }
}