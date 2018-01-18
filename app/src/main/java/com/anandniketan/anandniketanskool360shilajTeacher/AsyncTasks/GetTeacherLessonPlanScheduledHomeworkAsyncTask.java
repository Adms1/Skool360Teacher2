package com.anandniketan.anandniketanskool360shilajTeacher.AsyncTasks;

import android.os.AsyncTask;


import com.anandniketan.anandniketanskool360shilajTeacher.Models.HomeWorkResponse.HomeWorkModel;
import com.anandniketan.anandniketanskool360shilajTeacher.Utility.AppConfiguration;
import com.anandniketan.anandniketanskool360shilajTeacher.WebServicesCall.WebServicesCall;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * Created by admsandroid on 10/13/2017.
 */

public class GetTeacherLessonPlanScheduledHomeworkAsyncTask extends AsyncTask<Void, Void, HomeWorkModel> {
    HashMap<String, String> param = new HashMap<String, String>();

    public GetTeacherLessonPlanScheduledHomeworkAsyncTask(HashMap<String, String> param) {
        this.param = param;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected HomeWorkModel doInBackground(Void... params) {
        String responseString = null;
        HomeWorkModel homeWorkModel = null;
        try {
            responseString = WebServicesCall.RunScript(AppConfiguration.getUrl(AppConfiguration.GetTeacherLessonPlanScheduledHomework), param);
            Gson gson = new Gson();
            homeWorkModel = gson.fromJson(responseString, HomeWorkModel.class);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return homeWorkModel;
    }

    @Override
    protected void onPostExecute(HomeWorkModel result) {
        super.onPostExecute(result);
    }
}
//Response
//{
//        "Success": "True",
//        "FromDate": "17/01/2018",
//        "ToDate": "17/01/2018",
//        "FinalArray": [
//        {
//        "Date": "2018-01-17T00:00:00",
//        "Standard": "V",
//        "ClassName": "A",
//        "Subject": "Skatting",
//        "HomeWork": "<p>trtrrytytrry</p>",
//        "ChapterName": "<h1>TYPE</h1>\n<ol>\n    <li><span style=\"font-family: Arial;\"><u><strong><span style=\"background-color: yellow;\">feeeeasddddddddaaaaaaaaaaa</span></strong></u></span></li>\n</ol>",
//        "Objective": "<p>  sdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd</p>",
//        "AssessmentQue": "<p>sddddddddddddddddddddddddddddddddddddddddddddddddddddddd</p>",
//        "StandardID": 8,
//        "ClassID": 28,
//        "SubjectID": 8,
//        "TermID": 5,
//        "Font": "-|-|-|-"
//        }
//        ]
//        }