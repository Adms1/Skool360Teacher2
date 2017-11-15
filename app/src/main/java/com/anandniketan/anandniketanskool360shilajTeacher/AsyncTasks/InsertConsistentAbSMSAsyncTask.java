package com.anandniketan.anandniketanskool360shilajTeacher.AsyncTasks;

import android.os.AsyncTask;

import com.anandniketan.anandniketanskool360shilajTeacher.Models.AllAttendance.GetConsistentAbModel;
import com.anandniketan.anandniketanskool360shilajTeacher.Models.AllAttendance.InsertConsistentAbSMSModel;
import com.anandniketan.anandniketanskool360shilajTeacher.Utility.AppConfiguration;
import com.anandniketan.anandniketanskool360shilajTeacher.WebServicesCall.WebServicesCall;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * Created by admsandroid on 11/15/2017.
 */

public class InsertConsistentAbSMSAsyncTask extends AsyncTask<Void, Void, InsertConsistentAbSMSModel> {
    HashMap<String, String> param = new HashMap<String, String>();

    public InsertConsistentAbSMSAsyncTask(HashMap<String, String> param) {
        this.param = param;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected InsertConsistentAbSMSModel doInBackground(Void... params) {
        String responseString = null;
        InsertConsistentAbSMSModel insertConsistentAbSMSModel = null;
        try {
            responseString = WebServicesCall.RunScript(AppConfiguration.getUrl(AppConfiguration.InsertConsistentAbSMS), param);
            Gson gson = new Gson();
            insertConsistentAbSMSModel = gson.fromJson(responseString, InsertConsistentAbSMSModel.class);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return insertConsistentAbSMSModel;
    }

    @Override
    protected void onPostExecute(InsertConsistentAbSMSModel result) {
        super.onPostExecute(result);
    }
}



