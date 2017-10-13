package com.anandniketan.skool360teacher.AsyncTasks;

import android.os.AsyncTask;

import com.anandniketan.skool360teacher.Models.HomeworkModel;
import com.anandniketan.skool360teacher.Models.StaffAttendanceModel;
import com.anandniketan.skool360teacher.Utility.AppConfiguration;
import com.anandniketan.skool360teacher.Utility.ParseJSON;
import com.anandniketan.skool360teacher.WebServicesCall.WebServicesCall;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by admsandroid on 10/13/2017.
 */

public class GetTeacherLessonPlanScheduledHomeworkAsyncTask extends AsyncTask<Void, Void, ArrayList<HomeworkModel>> {
    HashMap<String, String> param = new HashMap<String, String>();

    public GetTeacherLessonPlanScheduledHomeworkAsyncTask(HashMap<String, String> param) {
        this.param = param;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ArrayList<HomeworkModel> doInBackground(Void... params) {
        String responseString = null;
        ArrayList<HomeworkModel> result = null;
        try {
            responseString = WebServicesCall.RunScript(AppConfiguration.getUrl(AppConfiguration.GetTeacherLessonPlanScheduledHomework), param);
            result = ParseJSON.parseTeacherHomeworkJson(responseString);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(ArrayList<HomeworkModel> result) {
        super.onPostExecute(result);
    }
}