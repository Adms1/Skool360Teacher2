package com.anandniketan.anandniketanskool360shilajTeacher.AsyncTasks;

import android.os.AsyncTask;

import com.anandniketan.anandniketanskool360shilajTeacher.Models.PTMResponse.MainPtmSentMessageResponse;
import com.anandniketan.anandniketanskool360shilajTeacher.Utility.AppConfiguration;
import com.anandniketan.anandniketanskool360shilajTeacher.WebServicesCall.WebServicesCall;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * Created by admsandroid on 10/26/2017.
 */

public class PTMTeacherStudentInsertDetailAsyncTask extends AsyncTask<Void, Void, MainPtmSentMessageResponse> {
    HashMap<String, String> param = new HashMap<String, String>();

    public PTMTeacherStudentInsertDetailAsyncTask(HashMap<String, String> param) {
        this.param = param;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected MainPtmSentMessageResponse doInBackground(Void... params) {
        String responseString = null;
        MainPtmSentMessageResponse mainPtmSentMessageResponse = null;
        try {
            responseString = WebServicesCall.RunScript(AppConfiguration.getUrl(AppConfiguration.PTMTeacherStudentInsertDetail), param);
            Gson gson = new Gson();
            mainPtmSentMessageResponse = gson.fromJson(responseString, MainPtmSentMessageResponse.class);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return mainPtmSentMessageResponse;
    }

    @Override
    protected void onPostExecute(MainPtmSentMessageResponse result) {
        super.onPostExecute(result);
    }
}

