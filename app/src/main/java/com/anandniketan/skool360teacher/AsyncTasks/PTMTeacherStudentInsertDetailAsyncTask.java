package com.anandniketan.skool360teacher.AsyncTasks;

import android.os.AsyncTask;

import com.anandniketan.skool360teacher.Models.MainPtmSentDeleteResponse;
import com.anandniketan.skool360teacher.Models.MainPtmSentMessageResponse;
import com.anandniketan.skool360teacher.Utility.AppConfiguration;
import com.anandniketan.skool360teacher.WebServicesCall.WebServicesCall;
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

