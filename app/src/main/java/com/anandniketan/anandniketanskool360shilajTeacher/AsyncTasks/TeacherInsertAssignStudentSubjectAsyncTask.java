package com.anandniketan.anandniketanskool360shilajTeacher.AsyncTasks;

import android.os.AsyncTask;

import com.anandniketan.anandniketanskool360shilajTeacher.Models.TeacherInsertAssignStudentSubjectModel.TeacherInsertSubjectMainResponse;
import com.anandniketan.anandniketanskool360shilajTeacher.Utility.AppConfiguration;
import com.anandniketan.anandniketanskool360shilajTeacher.WebServicesCall.WebServicesCall;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * Created by admsandroid on 10/30/2017.
 */

public class TeacherInsertAssignStudentSubjectAsyncTask  extends AsyncTask<Void, Void,TeacherInsertSubjectMainResponse> {
    HashMap<String, String> param = new HashMap<String, String>();

    public TeacherInsertAssignStudentSubjectAsyncTask(HashMap<String, String> param) {
        this.param = param;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected TeacherInsertSubjectMainResponse doInBackground(Void... params) {
        String responseString = null;
        TeacherInsertSubjectMainResponse response =null;
        try {
            responseString = WebServicesCall.RunScript(AppConfiguration.getUrl(AppConfiguration.TeacherInsertAssignStudentSubject), param);

            Gson gson = new Gson();
            response = gson.fromJson(responseString, TeacherInsertSubjectMainResponse.class);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return response;
    }

    @Override
    protected void onPostExecute(TeacherInsertSubjectMainResponse result) {
        super.onPostExecute(result);
    }
}

