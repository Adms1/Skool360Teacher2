package com.anandniketan.skool360teacher.AsyncTasks;

import android.os.AsyncTask;

import com.anandniketan.skool360teacher.Models.TeacherAssignedSubjectModel;
import com.anandniketan.skool360teacher.Models.TeacherTodayScheduleModel;
import com.anandniketan.skool360teacher.Utility.AppConfiguration;
import com.anandniketan.skool360teacher.Utility.ParseJSON;
import com.anandniketan.skool360teacher.WebServicesCall.WebServicesCall;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by admsandroid on 9/25/2017.
 */

public class GetTeacherAssignedSubjectAsyncTask extends AsyncTask<Void, Void, ArrayList<TeacherAssignedSubjectModel>> {
    HashMap<String, String> param = new HashMap<String, String>();

    public GetTeacherAssignedSubjectAsyncTask(HashMap<String, String> param) {
        this.param = param;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ArrayList<TeacherAssignedSubjectModel> doInBackground(Void... params) {
        String responseString = null;
        ArrayList<TeacherAssignedSubjectModel> result = null;
        try {
            responseString = WebServicesCall.RunScript(AppConfiguration.getUrl(AppConfiguration.GetTeacherAssignedSubject), param);
            result = ParseJSON.parseTeacherAssignedSubjectJson(responseString);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(ArrayList<TeacherAssignedSubjectModel> result) {
        super.onPostExecute(result);
    }
}
