package com.anandniketan.anandniketanskool360shilajTeacher.AsyncTasks;

import android.os.AsyncTask;

import com.anandniketan.anandniketanskool360shilajTeacher.Models.PTMResponse.MainPtmInboxResponse;
import com.anandniketan.anandniketanskool360shilajTeacher.Utility.AppConfiguration;
import com.anandniketan.anandniketanskool360shilajTeacher.WebServicesCall.WebServicesCall;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * Created by admsandroid on 10/25/2017.
 */

public class PTMTeacherStudentGetDetailAsyncTask extends AsyncTask<Void, Void, MainPtmInboxResponse> {
    HashMap<String, String> param = new HashMap<String, String>();

    public PTMTeacherStudentGetDetailAsyncTask(HashMap<String, String> param) {
        this.param = param;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected MainPtmInboxResponse doInBackground(Void... params) {
        String responseString = null;
        MainPtmInboxResponse mainPtmInboxResponse = null;
        try {
            responseString = WebServicesCall.RunScript(AppConfiguration.getUrl(AppConfiguration.PTMTeacherStudentGetDetail), param);
            Gson gson = new Gson();
            mainPtmInboxResponse = gson.fromJson(responseString, MainPtmInboxResponse.class);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return mainPtmInboxResponse;
    }

    @Override
    protected void onPostExecute(MainPtmInboxResponse result) {
        super.onPostExecute(result);
    }
}