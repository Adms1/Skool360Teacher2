package com.anandniketan.skool360teacher.AsyncTasks;

import android.os.AsyncTask;

import com.anandniketan.skool360teacher.Models.TeacherGetAssignStudentSubjectmModel;
import com.anandniketan.skool360teacher.Models.UserProfileModel;
import com.anandniketan.skool360teacher.Utility.AppConfiguration;
import com.anandniketan.skool360teacher.Utility.ParseJSON;
import com.anandniketan.skool360teacher.WebServicesCall.WebServicesCall;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by admsandroid on 9/25/2017.
 */

public class GetTeacherGetAssignStudentSubjectAsyncTask  extends AsyncTask<Void, Void, ArrayList<TeacherGetAssignStudentSubjectmModel>> {
    HashMap<String, String> param = new HashMap<String, String>();

    public GetTeacherGetAssignStudentSubjectAsyncTask(HashMap<String, String> param) {
        this.param = param;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ArrayList<TeacherGetAssignStudentSubjectmModel> doInBackground(Void... params) {
        String responseString = null;
        ArrayList<TeacherGetAssignStudentSubjectmModel> result = null;
        try {
            responseString = WebServicesCall.RunScript(AppConfiguration.getUrl(AppConfiguration.GetTeacherGetAssignStudentSubject), param);
            result = ParseJSON.parseStudentAssignedSubjectJson(responseString);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(ArrayList<TeacherGetAssignStudentSubjectmModel> result) {
        super.onPostExecute(result);
    }
}