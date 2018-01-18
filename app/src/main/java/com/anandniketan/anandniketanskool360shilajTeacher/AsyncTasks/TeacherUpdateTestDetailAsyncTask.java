package com.anandniketan.anandniketanskool360shilajTeacher.AsyncTasks;

import android.os.AsyncTask;

import com.anandniketan.anandniketanskool360shilajTeacher.Models.TestModel.UpdateTestDetailModel;
import com.anandniketan.anandniketanskool360shilajTeacher.Utility.AppConfiguration;
import com.anandniketan.anandniketanskool360shilajTeacher.WebServicesCall.WebServicesCall;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * Created by admsandroid on 11/6/2017.
 */

public class TeacherUpdateTestDetailAsyncTask  extends AsyncTask<Void, Void, UpdateTestDetailModel> {
    HashMap<String, String> param = new HashMap<String, String>();

    public TeacherUpdateTestDetailAsyncTask(HashMap<String, String> param) {
        this.param = param;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected UpdateTestDetailModel doInBackground(Void... params) {
        String responseString = null;
        UpdateTestDetailModel updateTestDetailModel = null;
        try {
            responseString = WebServicesCall.RunScript(AppConfiguration.getUrl(AppConfiguration.GetTeacherInsertTestDetail), param);
            Gson gson = new Gson();
            updateTestDetailModel = gson.fromJson(responseString, UpdateTestDetailModel.class);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return updateTestDetailModel;
    }

    @Override
    protected void onPostExecute(UpdateTestDetailModel result) {
        super.onPostExecute(result);
    }
}

