package com.anandniketan.skool360teacher.AsyncTasks;

import android.os.AsyncTask;

import com.anandniketan.skool360teacher.Models.InsertAttendanceModel;
import com.anandniketan.skool360teacher.Models.StaffAttendanceModel;
import com.anandniketan.skool360teacher.Utility.AppConfiguration;
import com.anandniketan.skool360teacher.Utility.ParseJSON;
import com.anandniketan.skool360teacher.WebServicesCall.WebServicesCall;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by admsandroid on 9/18/2017.
 */

public class GetInsertAttendanceAsyncTask extends AsyncTask<Void, Void, ArrayList<InsertAttendanceModel>> {
    HashMap<String, String> param = new HashMap<String, String>();

    public GetInsertAttendanceAsyncTask(HashMap<String, String> param) {
        this.param = param;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ArrayList<InsertAttendanceModel> doInBackground(Void... params) {
        String responseString = null;
        ArrayList<InsertAttendanceModel> result = null;
        try {
            responseString = WebServicesCall.RunScript(AppConfiguration.getUrl(AppConfiguration.GetInsertAttendance), param);
            result = ParseJSON.parseInsertAttendanceJson(responseString);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(ArrayList<InsertAttendanceModel> result) {
        super.onPostExecute(result);
    }
}