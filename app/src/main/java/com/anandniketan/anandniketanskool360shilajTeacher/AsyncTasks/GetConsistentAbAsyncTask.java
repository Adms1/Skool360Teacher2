package com.anandniketan.anandniketanskool360shilajTeacher.AsyncTasks;

import android.os.AsyncTask;

import com.anandniketan.anandniketanskool360shilajTeacher.Models.AllAttendance.GetAttendenceData_AllModel;
import com.anandniketan.anandniketanskool360shilajTeacher.Models.AllAttendance.GetConsistentAbModel;
import com.anandniketan.anandniketanskool360shilajTeacher.Utility.AppConfiguration;
import com.anandniketan.anandniketanskool360shilajTeacher.WebServicesCall.WebServicesCall;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * Created by admsandroid on 11/15/2017.
 */

public class GetConsistentAbAsyncTask  extends AsyncTask<Void, Void, GetConsistentAbModel> {
    HashMap<String, String> param = new HashMap<String, String>();

    public GetConsistentAbAsyncTask(HashMap<String, String> param) {
        this.param = param;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected GetConsistentAbModel doInBackground(Void... params) {
        String responseString = null;
        GetConsistentAbModel getConsistentAbModel = null;
        try {
            responseString = WebServicesCall.RunScript(AppConfiguration.getUrl(AppConfiguration.GetConsistentAb), param);
            Gson gson = new Gson();
            getConsistentAbModel = gson.fromJson(responseString, GetConsistentAbModel.class);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return getConsistentAbModel;
    }

    @Override
    protected void onPostExecute(GetConsistentAbModel result) {
        super.onPostExecute(result);
    }
}


