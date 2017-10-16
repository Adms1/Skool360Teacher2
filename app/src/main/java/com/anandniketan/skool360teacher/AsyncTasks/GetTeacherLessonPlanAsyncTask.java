package com.anandniketan.skool360teacher.AsyncTasks;

import android.os.AsyncTask;

import com.anandniketan.skool360teacher.Models.LessonPlanModel;
import com.anandniketan.skool360teacher.Models.LessonPlanResponse.MainResponseLesson;
import com.anandniketan.skool360teacher.Models.NewResponse.MainResponse;
import com.anandniketan.skool360teacher.Models.TeacherGetTimetableModel;
import com.anandniketan.skool360teacher.Models.TeacherLessonPlanModel;
import com.anandniketan.skool360teacher.Utility.AppConfiguration;
import com.anandniketan.skool360teacher.Utility.ParseJSON;
import com.anandniketan.skool360teacher.WebServicesCall.WebServicesCall;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by admsandroid on 10/13/2017.
 */

public class GetTeacherLessonPlanAsyncTask extends AsyncTask<Void, Void, MainResponseLesson> {
    HashMap<String, String> param = new HashMap<String, String>();

    public GetTeacherLessonPlanAsyncTask(HashMap<String, String> param) {
        this.param = param;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected MainResponseLesson doInBackground(Void... params) {
        String responseString = null;
        MainResponseLesson mainResponseLesson = null;
        try {
            responseString = WebServicesCall.RunScript(AppConfiguration.getUrl(AppConfiguration.GetTeacherLessonPlan), param);
            Gson gson = new Gson();
            mainResponseLesson = gson.fromJson(responseString, MainResponseLesson.class);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return mainResponseLesson;
    }

    @Override
    protected void onPostExecute(MainResponseLesson result) {
        super.onPostExecute(result);
    }
}