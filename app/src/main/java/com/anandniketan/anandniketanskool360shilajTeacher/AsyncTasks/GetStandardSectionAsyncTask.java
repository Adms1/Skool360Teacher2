package com.anandniketan.anandniketanskool360shilajTeacher.AsyncTasks;

import android.os.AsyncTask;

import com.anandniketan.anandniketanskool360shilajTeacher.Models.AllAttendance.GetStandardSectionModel;
import com.anandniketan.anandniketanskool360shilajTeacher.Models.DeviceVersionModel;
import com.anandniketan.anandniketanskool360shilajTeacher.Utility.AppConfiguration;
import com.anandniketan.anandniketanskool360shilajTeacher.WebServicesCall.WebServicesCall;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * Created by admsandroid on 11/14/2017.
 */

public class GetStandardSectionAsyncTask extends AsyncTask<Void, Void, GetStandardSectionModel> {
    HashMap<String, String> param = new HashMap<String, String>();

    public GetStandardSectionAsyncTask(HashMap<String, String> param) {
        this.param = param;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected GetStandardSectionModel doInBackground(Void... params) {
        String responseString = null;
        GetStandardSectionModel getStandardSectionModel = null;
        try {
            responseString = WebServicesCall.RunScript(AppConfiguration.getUrl(AppConfiguration.GetStandardSection), param);
            Gson gson = new Gson();
            getStandardSectionModel = gson.fromJson(responseString, GetStandardSectionModel.class);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return getStandardSectionModel;
    }

    @Override
    protected void onPostExecute(GetStandardSectionModel result) {
        super.onPostExecute(result);
    }
}
