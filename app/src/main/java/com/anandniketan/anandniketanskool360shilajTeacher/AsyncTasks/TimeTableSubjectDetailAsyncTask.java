package com.anandniketan.anandniketanskool360shilajTeacher.AsyncTasks;

import android.os.AsyncTask;

import com.anandniketan.anandniketanskool360shilajTeacher.Models.PTMInboxResponse.MainPtmInboxResponse;
import com.anandniketan.anandniketanskool360shilajTeacher.Models.TimeTable.GetTimeTableSubjectModel;
import com.anandniketan.anandniketanskool360shilajTeacher.Utility.AppConfiguration;
import com.anandniketan.anandniketanskool360shilajTeacher.WebServicesCall.WebServicesCall;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * Created by admsandroid on 1/11/2018.
 */

public class TimeTableSubjectDetailAsyncTask extends AsyncTask<Void, Void, GetTimeTableSubjectModel> {
    HashMap<String, String> param = new HashMap<String, String>();

    public TimeTableSubjectDetailAsyncTask(HashMap<String, String> param) {
        this.param = param;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected GetTimeTableSubjectModel doInBackground(Void... params) {
        String responseString = null;
        GetTimeTableSubjectModel timeTableSubjectModel = null;
        try {
            responseString = WebServicesCall.RunScript(AppConfiguration.getUrl(AppConfiguration.GetAssignedSubjectForTimeTable), param);
            Gson gson = new Gson();
            timeTableSubjectModel = gson.fromJson(responseString, GetTimeTableSubjectModel.class);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return timeTableSubjectModel;
    }

    @Override
    protected void onPostExecute(GetTimeTableSubjectModel result) {
        super.onPostExecute(result);
    }
}