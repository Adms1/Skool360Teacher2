package com.anandniketan.skool360teacher.AsyncTasks;

import android.os.AsyncTask;

import com.anandniketan.skool360teacher.Models.TeacherGetTimetableModel;
import com.anandniketan.skool360teacher.Utility.AppConfiguration;
import com.anandniketan.skool360teacher.Utility.ParseJSON;
import com.anandniketan.skool360teacher.WebServicesCall.WebServicesCall;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by admsandroid on 9/25/2017.
 */

public class GetTeacherGetTimetableAsyncTask extends AsyncTask<Void, Void, ArrayList<TeacherGetTimetableModel>> {
    HashMap<String, String> param = new HashMap<String, String>();

    public GetTeacherGetTimetableAsyncTask(HashMap<String, String> param) {
        this.param = param;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ArrayList<TeacherGetTimetableModel> doInBackground(Void... params) {
        String responseString = null;
        ArrayList<TeacherGetTimetableModel> result = null;
        try {
            responseString = WebServicesCall.RunScript(AppConfiguration.getUrl(AppConfiguration.GetTeacherGetTimetable), param);
            result = ParseJSON.parseTeachertTimetableJson(responseString);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(ArrayList<TeacherGetTimetableModel> result) {
        super.onPostExecute(result);
    }
}