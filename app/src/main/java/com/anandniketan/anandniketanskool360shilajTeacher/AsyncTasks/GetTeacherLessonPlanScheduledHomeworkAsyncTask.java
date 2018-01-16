package com.anandniketan.anandniketanskool360shilajTeacher.AsyncTasks;

import android.os.AsyncTask;


import com.anandniketan.anandniketanskool360shilajTeacher.Models.HomeWorkResponse.HomeWorkModel;
import com.anandniketan.anandniketanskool360shilajTeacher.Models.PTMInboxResponse.MainPtmInboxResponse;
import com.anandniketan.anandniketanskool360shilajTeacher.Utility.AppConfiguration;
import com.anandniketan.anandniketanskool360shilajTeacher.WebServicesCall.WebServicesCall;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * Created by admsandroid on 10/13/2017.
 */

public class GetTeacherLessonPlanScheduledHomeworkAsyncTask extends AsyncTask<Void, Void, HomeWorkModel> {
    HashMap<String, String> param = new HashMap<String, String>();

    public GetTeacherLessonPlanScheduledHomeworkAsyncTask(HashMap<String, String> param) {
        this.param = param;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected HomeWorkModel doInBackground(Void... params) {
        String responseString = null;
        HomeWorkModel homeWorkModel = null;
        try {
            responseString = WebServicesCall.RunScript(AppConfiguration.getUrl(AppConfiguration.GetTeacherLessonPlanScheduledHomework), param);
            Gson gson = new Gson();
            homeWorkModel = gson.fromJson(responseString, HomeWorkModel.class);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return homeWorkModel;
    }

    @Override
    protected void onPostExecute(HomeWorkModel result) {
        super.onPostExecute(result);
    }
}