package com.anandniketan.skool360teacher.AsyncTasks;

import android.os.AsyncTask;

import com.anandniketan.skool360teacher.Models.LoginModel;
import com.anandniketan.skool360teacher.Models.UserProfileModel;
import com.anandniketan.skool360teacher.Utility.AppConfiguration;
import com.anandniketan.skool360teacher.Utility.ParseJSON;
import com.anandniketan.skool360teacher.WebServicesCall.WebServicesCall;

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