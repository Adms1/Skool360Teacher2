package com.anandniketan.skool360teacher.AsyncTasks;

import android.os.AsyncTask;

import com.anandniketan.skool360teacher.Models.DeviceVersionModel;
import com.anandniketan.skool360teacher.Models.UpdateTestDetailModel;
import com.anandniketan.skool360teacher.Utility.AppConfiguration;
import com.anandniketan.skool360teacher.WebServicesCall.WebServicesCall;
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

