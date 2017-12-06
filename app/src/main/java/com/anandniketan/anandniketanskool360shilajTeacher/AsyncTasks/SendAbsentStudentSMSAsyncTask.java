package com.anandniketan.anandniketanskool360shilajTeacher.AsyncTasks;

import android.os.AsyncTask;

import com.anandniketan.anandniketanskool360shilajTeacher.Models.AllAttendance.GetAbsentStudentSMSStatusModel;
import com.anandniketan.anandniketanskool360shilajTeacher.Models.AllAttendance.InsertConsistentAbSMSModel;
import com.anandniketan.anandniketanskool360shilajTeacher.Models.MainPtmSentDeleteResponse;
import com.anandniketan.anandniketanskool360shilajTeacher.Utility.AppConfiguration;
import com.anandniketan.anandniketanskool360shilajTeacher.WebServicesCall.WebServicesCall;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * Created by admsandroid on 11/28/2017.
 */

public class SendAbsentStudentSMSAsyncTask  extends AsyncTask<Void, Void, GetAbsentStudentSMSStatusModel> {
    HashMap<String, String> param = new HashMap<String, String>();

    public SendAbsentStudentSMSAsyncTask(HashMap<String, String> param) {
        this.param = param;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected GetAbsentStudentSMSStatusModel doInBackground(Void... params) {
        String responseString = null;
        GetAbsentStudentSMSStatusModel insertConsistentAbSMSModel = null;
        try {
            responseString = WebServicesCall.RunScript(AppConfiguration.getUrl(AppConfiguration.SendAbsentStudentSMS), param);
            Gson gson = new Gson();
            insertConsistentAbSMSModel = gson.fromJson(responseString, GetAbsentStudentSMSStatusModel.class);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return insertConsistentAbSMSModel;
    }

    @Override
    protected void onPostExecute(GetAbsentStudentSMSStatusModel result) {
        super.onPostExecute(result);
    }
}
