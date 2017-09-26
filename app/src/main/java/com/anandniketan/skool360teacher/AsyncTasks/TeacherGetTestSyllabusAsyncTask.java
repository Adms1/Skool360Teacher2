package com.anandniketan.skool360teacher.AsyncTasks;

import android.os.AsyncTask;

import com.anandniketan.skool360teacher.Models.TeacherGetTimetableModel;
import com.anandniketan.skool360teacher.Models.Test_SyllabusModel;
import com.anandniketan.skool360teacher.Utility.AppConfiguration;
import com.anandniketan.skool360teacher.Utility.ParseJSON;
import com.anandniketan.skool360teacher.WebServicesCall.WebServicesCall;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by admsandroid on 9/26/2017.
 */

public class TeacherGetTestSyllabusAsyncTask extends AsyncTask<Void, Void, ArrayList<Test_SyllabusModel>> {
    HashMap<String, String> param = new HashMap<String, String>();

    public TeacherGetTestSyllabusAsyncTask(HashMap<String, String> param) {
        this.param = param;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ArrayList<Test_SyllabusModel> doInBackground(Void... params) {
        String responseString = null;
        ArrayList<Test_SyllabusModel> result = null;
        try {
            responseString = WebServicesCall.RunScript(AppConfiguration.getUrl(AppConfiguration.GetTeacherGetTestSyllabus), param);
            result = ParseJSON.parseTestSyllabusJson(responseString);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(ArrayList<Test_SyllabusModel> result) {
        super.onPostExecute(result);
    }
}
