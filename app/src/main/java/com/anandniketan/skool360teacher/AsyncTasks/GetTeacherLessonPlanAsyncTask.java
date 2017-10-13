package com.anandniketan.skool360teacher.AsyncTasks;

import android.os.AsyncTask;

import com.anandniketan.skool360teacher.Models.LessonPlanModel;
import com.anandniketan.skool360teacher.Models.TeacherGetTimetableModel;
import com.anandniketan.skool360teacher.Models.TeacherLessonPlanModel;
import com.anandniketan.skool360teacher.Utility.AppConfiguration;
import com.anandniketan.skool360teacher.Utility.ParseJSON;
import com.anandniketan.skool360teacher.WebServicesCall.WebServicesCall;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by admsandroid on 10/13/2017.
 */

public class GetTeacherLessonPlanAsyncTask extends AsyncTask<Void, Void, ArrayList<TeacherLessonPlanModel>> {
    HashMap<String, String> param = new HashMap<String, String>();

    public GetTeacherLessonPlanAsyncTask(HashMap<String, String> param) {
        this.param = param;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ArrayList<TeacherLessonPlanModel> doInBackground(Void... params) {
        String responseString = null;
        ArrayList<TeacherLessonPlanModel> result = null;
        try {
            responseString = WebServicesCall.RunScript(AppConfiguration.getUrl(AppConfiguration.GetTeacherLessonPlan), param);
            result = ParseJSON.parseTeacherLessonPlanJson(responseString);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(ArrayList<TeacherLessonPlanModel> result) {
        super.onPostExecute(result);
    }
}