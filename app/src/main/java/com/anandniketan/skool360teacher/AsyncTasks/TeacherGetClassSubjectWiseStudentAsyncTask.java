package com.anandniketan.skool360teacher.AsyncTasks;

import android.os.AsyncTask;

import com.anandniketan.skool360teacher.Models.PTMCreateResponse.MainResponseDisplayStudent;
import com.anandniketan.skool360teacher.Models.PTMInboxResponse.MainPtmInboxResponse;
import com.anandniketan.skool360teacher.Utility.AppConfiguration;
import com.anandniketan.skool360teacher.WebServicesCall.WebServicesCall;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * Created by admsandroid on 10/25/2017.
 */

public class TeacherGetClassSubjectWiseStudentAsyncTask extends AsyncTask<Void, Void, MainResponseDisplayStudent> {
    HashMap<String, String> param = new HashMap<String, String>();

    public TeacherGetClassSubjectWiseStudentAsyncTask(HashMap<String, String> param) {
        this.param = param;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected MainResponseDisplayStudent doInBackground(Void... params) {
        String responseString = null;
        MainResponseDisplayStudent mainResponseDisplayStudent = null;
        try {
            responseString = WebServicesCall.RunScript(AppConfiguration.getUrl(AppConfiguration.TeacherGetClassSubjectWiseStudent), param);
            Gson gson = new Gson();
            mainResponseDisplayStudent = gson.fromJson(responseString, MainResponseDisplayStudent.class);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return mainResponseDisplayStudent;
    }

    @Override
    protected void onPostExecute(MainResponseDisplayStudent result) {
        super.onPostExecute(result);
    }
}
