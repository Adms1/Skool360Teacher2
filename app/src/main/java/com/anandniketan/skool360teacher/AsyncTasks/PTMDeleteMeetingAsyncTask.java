package com.anandniketan.skool360teacher.AsyncTasks;

import android.os.AsyncTask;

import com.anandniketan.skool360teacher.Models.MainPtmSentDeleteResponse;
import com.anandniketan.skool360teacher.Models.PTMInboxResponse.MainPtmInboxResponse;
import com.anandniketan.skool360teacher.Utility.AppConfiguration;
import com.anandniketan.skool360teacher.WebServicesCall.WebServicesCall;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * Created by admsandroid on 10/25/2017.
 */

public class PTMDeleteMeetingAsyncTask extends AsyncTask<Void, Void, MainPtmSentDeleteResponse> {
    HashMap<String, String> param = new HashMap<String, String>();

    public PTMDeleteMeetingAsyncTask(HashMap<String, String> param) {
        this.param = param;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected MainPtmSentDeleteResponse doInBackground(Void... params) {
        String responseString = null;
        MainPtmSentDeleteResponse mainPtmSentDeleteResponse = null;
        try {
            responseString = WebServicesCall.RunScript(AppConfiguration.getUrl(AppConfiguration.PTMDeleteMeeting), param);
            Gson gson = new Gson();
            mainPtmSentDeleteResponse = gson.fromJson(responseString, MainPtmSentDeleteResponse.class);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return mainPtmSentDeleteResponse;
    }

    @Override
    protected void onPostExecute(MainPtmSentDeleteResponse result) {
        super.onPostExecute(result);
    }
}
