package com.anandniketan.skool360teacher.AsyncTasks;

import android.os.AsyncTask;

import com.anandniketan.skool360teacher.Models.TeacherInsertTestDetailModel;
import com.anandniketan.skool360teacher.Models.Test_SyllabusModel;
import com.anandniketan.skool360teacher.Utility.AppConfiguration;
import com.anandniketan.skool360teacher.Utility.ParseJSON;
import com.anandniketan.skool360teacher.WebServicesCall.WebServicesCall;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by admsandroid on 9/28/2017.
 */

public class TeacherInsertTestDetailAsyncTask  extends AsyncTask<Void, Void, ArrayList<TeacherInsertTestDetailModel>> {
    HashMap<String, String> param = new HashMap<String, String>();

    public TeacherInsertTestDetailAsyncTask(HashMap<String, String> param) {
        this.param = param;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ArrayList<TeacherInsertTestDetailModel> doInBackground(Void... params) {
        String responseString = null;
        ArrayList<TeacherInsertTestDetailModel> result = null;
        try {
            responseString = WebServicesCall.RunScript(AppConfiguration.getUrl(AppConfiguration.GetTeacherInsertTestDetail), param);
            result = ParseJSON.parseTeacherInsertTestDetailJson(responseString);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(ArrayList<TeacherInsertTestDetailModel> result) {
        super.onPostExecute(result);
    }
}

