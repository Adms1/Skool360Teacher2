package com.anandniketan.anandniketanskool360shilajTeacher.AsyncTasks;

import android.os.AsyncTask;

import com.anandniketan.anandniketanskool360shilajTeacher.Models.AllAttendance.GetConsistentAbModel;
import com.anandniketan.anandniketanskool360shilajTeacher.Models.AllAttendance.GetDateWiseAbsentStudentModel;
import com.anandniketan.anandniketanskool360shilajTeacher.Utility.AppConfiguration;
import com.anandniketan.anandniketanskool360shilajTeacher.WebServicesCall.WebServicesCall;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * Created by admsandroid on 11/28/2017.
 */

public class GetDateWiseAbsentStudentAsyncTask  extends AsyncTask<Void, Void, GetDateWiseAbsentStudentModel> {
    HashMap<String, String> param = new HashMap<String, String>();

    public GetDateWiseAbsentStudentAsyncTask(HashMap<String, String> param) {
        this.param = param;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected GetDateWiseAbsentStudentModel doInBackground(Void... params) {
        String responseString = null;
        GetDateWiseAbsentStudentModel getDateWiseAbsentStudentModel = null;
        try {
            responseString = WebServicesCall.RunScript(AppConfiguration.getUrl(AppConfiguration.GetDateWiseAbsentStudent), param);
            Gson gson = new Gson();
            getDateWiseAbsentStudentModel = gson.fromJson(responseString, GetDateWiseAbsentStudentModel.class);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return getDateWiseAbsentStudentModel;
    }

    @Override
    protected void onPostExecute(GetDateWiseAbsentStudentModel result) {
        super.onPostExecute(result);
    }
}



