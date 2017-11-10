package com.anandniketan.anandniketanskool360shilajTeacher.AsyncTasks;

import android.os.AsyncTask;

import com.anandniketan.anandniketanskool360shilajTeacher.Models.UserProfileModel;
import com.anandniketan.anandniketanskool360shilajTeacher.Utility.AppConfiguration;
import com.anandniketan.anandniketanskool360shilajTeacher.Utility.ParseJSON;
import com.anandniketan.anandniketanskool360shilajTeacher.WebServicesCall.WebServicesCall;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by admsandroid on 9/20/2017.
 */

public class GetStaffProfileAsyncTask extends AsyncTask<Void, Void, ArrayList<UserProfileModel>> {
    HashMap<String, String> param = new HashMap<String, String>();

    public GetStaffProfileAsyncTask(HashMap<String, String> param) {
        this.param = param;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ArrayList<UserProfileModel> doInBackground(Void... params) {
        String responseString = null;
        ArrayList<UserProfileModel> result = null;
        try {
            responseString = WebServicesCall.RunScript(AppConfiguration.getUrl(AppConfiguration.GetStaffProfile), param);
            result = ParseJSON.parseUserProfileJson(responseString);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(ArrayList<UserProfileModel> result) {
        super.onPostExecute(result);
    }
}