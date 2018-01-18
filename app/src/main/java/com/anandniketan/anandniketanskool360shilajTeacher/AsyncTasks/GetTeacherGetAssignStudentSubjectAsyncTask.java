package com.anandniketan.anandniketanskool360shilajTeacher.AsyncTasks;

import android.os.AsyncTask;

import com.anandniketan.anandniketanskool360shilajTeacher.Models.StudentAssignSubjectResponse.MainResponseStudentSubject;
import com.anandniketan.anandniketanskool360shilajTeacher.Utility.AppConfiguration;
import com.anandniketan.anandniketanskool360shilajTeacher.WebServicesCall.WebServicesCall;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * Created by admsandroid on 9/25/2017.
 */

public class GetTeacherGetAssignStudentSubjectAsyncTask  extends AsyncTask<Void, Void,MainResponseStudentSubject> {
    HashMap<String, String> param = new HashMap<String, String>();

    public GetTeacherGetAssignStudentSubjectAsyncTask(HashMap<String, String> param) {
        this.param = param;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected MainResponseStudentSubject doInBackground(Void... params) {
        String responseString = null;
        MainResponseStudentSubject mainResponseStudentSubject=null;
        try {
            responseString = WebServicesCall.RunScript(AppConfiguration.getUrl(AppConfiguration.GetTeacherGetAssignStudentSubject), param);
            Gson gson = new Gson();
            mainResponseStudentSubject = gson.fromJson(responseString, MainResponseStudentSubject.class);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return mainResponseStudentSubject;
    }

    @Override
    protected void onPostExecute(MainResponseStudentSubject result) {
        super.onPostExecute(result);
    }
}