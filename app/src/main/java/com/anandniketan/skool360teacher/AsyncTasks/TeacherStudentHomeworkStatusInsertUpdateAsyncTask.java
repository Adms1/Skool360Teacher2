package com.anandniketan.skool360teacher.AsyncTasks;

import android.os.AsyncTask;

import com.anandniketan.skool360teacher.Models.DeleteLectureModel;
import com.anandniketan.skool360teacher.Models.HomeworkStatusInsertUpdateModel;
import com.anandniketan.skool360teacher.Utility.AppConfiguration;
import com.anandniketan.skool360teacher.WebServicesCall.WebServicesCall;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * Created by admsandroid on 11/8/2017.
 */

public class TeacherStudentHomeworkStatusInsertUpdateAsyncTask extends AsyncTask<Void, Void, HomeworkStatusInsertUpdateModel> {
    HashMap<String, String> param = new HashMap<String, String>();

    public TeacherStudentHomeworkStatusInsertUpdateAsyncTask(HashMap<String, String> param) {
        this.param = param;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected HomeworkStatusInsertUpdateModel doInBackground(Void... params) {
        String responseString = null;
        HomeworkStatusInsertUpdateModel homeworkStatusInsertUpdateModel = null;
        try {
            responseString = WebServicesCall.RunScript(AppConfiguration.getUrl(AppConfiguration.TeacherStudentHomeworkStatusInsertUpdate), param);
            Gson gson = new Gson();
            homeworkStatusInsertUpdateModel = gson.fromJson(responseString, HomeworkStatusInsertUpdateModel.class);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return homeworkStatusInsertUpdateModel;
    }

    @Override
    protected void onPostExecute(HomeworkStatusInsertUpdateModel result) {
        super.onPostExecute(result);
    }
}
