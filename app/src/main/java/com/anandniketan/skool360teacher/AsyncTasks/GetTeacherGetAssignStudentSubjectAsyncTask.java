package com.anandniketan.skool360teacher.AsyncTasks;

import android.os.AsyncTask;

import com.anandniketan.skool360teacher.Models.NewResponse.MainResponse;
import com.anandniketan.skool360teacher.Models.StudentAssignSubjectResponse.MainResponseStudentSubject;
import com.anandniketan.skool360teacher.Models.TeacherGetAssignStudentSubjectmModel;
import com.anandniketan.skool360teacher.Models.UserProfileModel;
import com.anandniketan.skool360teacher.Utility.AppConfiguration;
import com.anandniketan.skool360teacher.Utility.ParseJSON;
import com.anandniketan.skool360teacher.WebServicesCall.WebServicesCall;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by admsandroid on 9/25/2017.
 */

public class GetTeacherGetAssignStudentSubjectAsyncTask  extends AsyncTask<Void, Void,MainResponseStudentSubject> {
    HashMap<String, String> param = new HashMap<String, String>();

    public GetTeacherGetAssignStudentSubjectAsyncTask(HashMap<String, String> param) {
        this.param = param;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected MainResponseStudentSubject doInBackground(Void... params) {
        String responseString = null;
        MainResponseStudentSubject mainResponseStudentSubject=null;
        try {
            responseString = WebServicesCall.RunScript(AppConfiguration.getUrl(AppConfiguration.GetTeacherGetAssignStudentSubject), param);
//            responseString="{\n" +
//                    "  \"Success\": \"True\",\n" +
//                    "  \"FinalArray\": [\n" +
//                    "    {\n" +
//                    "      \"StudentName\": \"Aadit Shah\",\n" +
//                    "      \"StudentID\": 294,\n" +
//                    "      \"StudentSubject\": [\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 3,\n" +
//                    "          \"Subject\": \"JAva\",\n" +
//                    "          \"CheckedStatus\": \"1\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 77,\n" +
//                    "          \"Subject\": \"Computers\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 2,\n" +
//                    "          \"Subject\": \"English\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 79,\n" +
//                    "          \"Subject\": \"Gujarati\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 5,\n" +
//                    "          \"Subject\": \"Hindi\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 1,\n" +
//                    "          \"Subject\": \"Mathematics\",\n" +
//                    "          \"CheckedStatus\": \"1\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 69,\n" +
//                    "          \"Subject\": \"Practical\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 37,\n" +
//                    "          \"Subject\": \"Sanskrit\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 21,\n" +
//                    "          \"Subject\": \"Science\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 24,\n" +
//                    "          \"Subject\": \"Social Studies\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        }\n" +
//                    "      ]\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"StudentName\": \"Aanya Bachhawat\",\n" +
//                    "      \"StudentID\": 295,\n" +
//                    "      \"StudentSubject\": [\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 3,\n" +
//                    "          \"Subject\": \"Book Club\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 77,\n" +
//                    "          \"Subject\": \"Computers\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 2,\n" +
//                    "          \"Subject\": \"English\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 79,\n" +
//                    "          \"Subject\": \"Gujarati\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 5,\n" +
//                    "          \"Subject\": \"Hindi\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 1,\n" +
//                    "          \"Subject\": \"Mathematics\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 69,\n" +
//                    "          \"Subject\": \"Practical\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 37,\n" +
//                    "          \"Subject\": \"Sanskrit\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 21,\n" +
//                    "          \"Subject\": \"Science\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 24,\n" +
//                    "          \"Subject\": \"Social Studies\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        }\n" +
//                    "      ]\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"StudentName\": \"Aarya Patel\",\n" +
//                    "      \"StudentID\": 296,\n" +
//                    "      \"StudentSubject\": [\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 3,\n" +
//                    "          \"Subject\": \"Book Club\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 77,\n" +
//                    "          \"Subject\": \"Computers\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 2,\n" +
//                    "          \"Subject\": \"English\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 79,\n" +
//                    "          \"Subject\": \"Gujarati\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 5,\n" +
//                    "          \"Subject\": \"Hindi\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 1,\n" +
//                    "          \"Subject\": \"Mathematics\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 69,\n" +
//                    "          \"Subject\": \"Practical\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 37,\n" +
//                    "          \"Subject\": \"Sanskrit\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 21,\n" +
//                    "          \"Subject\": \"Science\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 24,\n" +
//                    "          \"Subject\": \"Social Studies\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        }\n" +
//                    "      ]\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"StudentName\": \"Aayush Shah\",\n" +
//                    "      \"StudentID\": 299,\n" +
//                    "      \"StudentSubject\": [\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 3,\n" +
//                    "          \"Subject\": \"Book Club\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 77,\n" +
//                    "          \"Subject\": \"Computers\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 2,\n" +
//                    "          \"Subject\": \"English\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 79,\n" +
//                    "          \"Subject\": \"Gujarati\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 5,\n" +
//                    "          \"Subject\": \"Hindi\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 1,\n" +
//                    "          \"Subject\": \"Mathematics\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 69,\n" +
//                    "          \"Subject\": \"Practical\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 37,\n" +
//                    "          \"Subject\": \"Sanskrit\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 21,\n" +
//                    "          \"Subject\": \"Science\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 24,\n" +
//                    "          \"Subject\": \"Social Studies\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        }\n" +
//                    "      ]\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"StudentName\": \"Arya Patel\",\n" +
//                    "      \"StudentID\": 304,\n" +
//                    "      \"StudentSubject\": [\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 3,\n" +
//                    "          \"Subject\": \"Book Club\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 77,\n" +
//                    "          \"Subject\": \"Computers\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 2,\n" +
//                    "          \"Subject\": \"English\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 79,\n" +
//                    "          \"Subject\": \"Gujarati\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 5,\n" +
//                    "          \"Subject\": \"Hindi\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 1,\n" +
//                    "          \"Subject\": \"Mathematics\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 69,\n" +
//                    "          \"Subject\": \"Practical\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 37,\n" +
//                    "          \"Subject\": \"Sanskrit\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 21,\n" +
//                    "          \"Subject\": \"Science\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 24,\n" +
//                    "          \"Subject\": \"Social Studies\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        }\n" +
//                    "      ]\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"StudentName\": \"Bhrithi Gajaria\",\n" +
//                    "      \"StudentID\": 307,\n" +
//                    "      \"StudentSubject\": [\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 3,\n" +
//                    "          \"Subject\": \"Book Club\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 77,\n" +
//                    "          \"Subject\": \"Computers\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 2,\n" +
//                    "          \"Subject\": \"English\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 79,\n" +
//                    "          \"Subject\": \"Gujarati\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 5,\n" +
//                    "          \"Subject\": \"Hindi\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 1,\n" +
//                    "          \"Subject\": \"Mathematics\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 69,\n" +
//                    "          \"Subject\": \"Practical\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 37,\n" +
//                    "          \"Subject\": \"Sanskrit\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 21,\n" +
//                    "          \"Subject\": \"Science\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 24,\n" +
//                    "          \"Subject\": \"Social Studies\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        }\n" +
//                    "      ]\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"StudentName\": \"Deepam Dave\",\n" +
//                    "      \"StudentID\": 309,\n" +
//                    "      \"StudentSubject\": [\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 3,\n" +
//                    "          \"Subject\": \"Book Club\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 77,\n" +
//                    "          \"Subject\": \"Computers\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 2,\n" +
//                    "          \"Subject\": \"English\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 79,\n" +
//                    "          \"Subject\": \"Gujarati\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 5,\n" +
//                    "          \"Subject\": \"Hindi\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 1,\n" +
//                    "          \"Subject\": \"Mathematics\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 69,\n" +
//                    "          \"Subject\": \"Practical\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 37,\n" +
//                    "          \"Subject\": \"Sanskrit\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 21,\n" +
//                    "          \"Subject\": \"Science\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 24,\n" +
//                    "          \"Subject\": \"Social Studies\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        }\n" +
//                    "      ]\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"StudentName\": \"Deepika Sinha\",\n" +
//                    "      \"StudentID\": 310,\n" +
//                    "      \"StudentSubject\": [\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 3,\n" +
//                    "          \"Subject\": \"Book Club\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 77,\n" +
//                    "          \"Subject\": \"Computers\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 2,\n" +
//                    "          \"Subject\": \"English\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 79,\n" +
//                    "          \"Subject\": \"Gujarati\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 5,\n" +
//                    "          \"Subject\": \"Hindi\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 1,\n" +
//                    "          \"Subject\": \"Mathematics\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 69,\n" +
//                    "          \"Subject\": \"Practical\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 37,\n" +
//                    "          \"Subject\": \"Sanskrit\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 21,\n" +
//                    "          \"Subject\": \"Science\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 24,\n" +
//                    "          \"Subject\": \"Social Studies\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        }\n" +
//                    "      ]\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"StudentName\": \"Diya Patel\",\n" +
//                    "      \"StudentID\": 318,\n" +
//                    "      \"StudentSubject\": [\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 3,\n" +
//                    "          \"Subject\": \"Book Club\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 77,\n" +
//                    "          \"Subject\": \"Computers\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 2,\n" +
//                    "          \"Subject\": \"English\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 79,\n" +
//                    "          \"Subject\": \"Gujarati\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 5,\n" +
//                    "          \"Subject\": \"Hindi\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 1,\n" +
//                    "          \"Subject\": \"Mathematics\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 69,\n" +
//                    "          \"Subject\": \"Practical\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 37,\n" +
//                    "          \"Subject\": \"Sanskrit\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 21,\n" +
//                    "          \"Subject\": \"Science\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 24,\n" +
//                    "          \"Subject\": \"Social Studies\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        }\n" +
//                    "      ]\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"StudentName\": \"Hiyaa Gandhi\",\n" +
//                    "      \"StudentID\": 328,\n" +
//                    "      \"StudentSubject\": [\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 3,\n" +
//                    "          \"Subject\": \"Book Club\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 77,\n" +
//                    "          \"Subject\": \"Computers\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 2,\n" +
//                    "          \"Subject\": \"English\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 79,\n" +
//                    "          \"Subject\": \"Gujarati\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 5,\n" +
//                    "          \"Subject\": \"Hindi\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 1,\n" +
//                    "          \"Subject\": \"Mathematics\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 69,\n" +
//                    "          \"Subject\": \"Practical\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 37,\n" +
//                    "          \"Subject\": \"Sanskrit\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 21,\n" +
//                    "          \"Subject\": \"Science\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 24,\n" +
//                    "          \"Subject\": \"Social Studies\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        }\n" +
//                    "      ]\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"StudentName\": \"Khushi Gandhi\",\n" +
//                    "      \"StudentID\": 340,\n" +
//                    "      \"StudentSubject\": [\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 3,\n" +
//                    "          \"Subject\": \"Book Club\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 77,\n" +
//                    "          \"Subject\": \"Computers\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 2,\n" +
//                    "          \"Subject\": \"English\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 79,\n" +
//                    "          \"Subject\": \"Gujarati\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 5,\n" +
//                    "          \"Subject\": \"Hindi\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 1,\n" +
//                    "          \"Subject\": \"Mathematics\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 69,\n" +
//                    "          \"Subject\": \"Practical\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 37,\n" +
//                    "          \"Subject\": \"Sanskrit\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 21,\n" +
//                    "          \"Subject\": \"Science\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 24,\n" +
//                    "          \"Subject\": \"Social Studies\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        }\n" +
//                    "      ]\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"StudentName\": \"Maanank Shah\",\n" +
//                    "      \"StudentID\": 345,\n" +
//                    "      \"StudentSubject\": [\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 3,\n" +
//                    "          \"Subject\": \"Book Club\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 77,\n" +
//                    "          \"Subject\": \"Computers\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 2,\n" +
//                    "          \"Subject\": \"English\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 79,\n" +
//                    "          \"Subject\": \"Gujarati\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 5,\n" +
//                    "          \"Subject\": \"Hindi\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 1,\n" +
//                    "          \"Subject\": \"Mathematics\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 69,\n" +
//                    "          \"Subject\": \"Practical\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 37,\n" +
//                    "          \"Subject\": \"Sanskrit\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 21,\n" +
//                    "          \"Subject\": \"Science\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 24,\n" +
//                    "          \"Subject\": \"Social Studies\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        }\n" +
//                    "      ]\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"StudentName\": \"Manav Kotadia\",\n" +
//                    "      \"StudentID\": 347,\n" +
//                    "      \"StudentSubject\": [\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 3,\n" +
//                    "          \"Subject\": \"Book Club\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 77,\n" +
//                    "          \"Subject\": \"Computers\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 2,\n" +
//                    "          \"Subject\": \"English\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 79,\n" +
//                    "          \"Subject\": \"Gujarati\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 5,\n" +
//                    "          \"Subject\": \"Hindi\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 1,\n" +
//                    "          \"Subject\": \"Mathematics\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 69,\n" +
//                    "          \"Subject\": \"Practical\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 37,\n" +
//                    "          \"Subject\": \"Sanskrit\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 21,\n" +
//                    "          \"Subject\": \"Science\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 24,\n" +
//                    "          \"Subject\": \"Social Studies\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        }\n" +
//                    "      ]\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"StudentName\": \"Manvi Gaur\",\n" +
//                    "      \"StudentID\": 349,\n" +
//                    "      \"StudentSubject\": [\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 3,\n" +
//                    "          \"Subject\": \"Book Club\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 77,\n" +
//                    "          \"Subject\": \"Computers\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 2,\n" +
//                    "          \"Subject\": \"English\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 79,\n" +
//                    "          \"Subject\": \"Gujarati\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 5,\n" +
//                    "          \"Subject\": \"Hindi\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 1,\n" +
//                    "          \"Subject\": \"Mathematics\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 69,\n" +
//                    "          \"Subject\": \"Practical\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 37,\n" +
//                    "          \"Subject\": \"Sanskrit\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 21,\n" +
//                    "          \"Subject\": \"Science\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 24,\n" +
//                    "          \"Subject\": \"Social Studies\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        }\n" +
//                    "      ]\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"StudentName\": \"Nikki Shah\",\n" +
//                    "      \"StudentID\": 355,\n" +
//                    "      \"StudentSubject\": [\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 3,\n" +
//                    "          \"Subject\": \"Book Club\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 77,\n" +
//                    "          \"Subject\": \"Computers\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 2,\n" +
//                    "          \"Subject\": \"English\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 79,\n" +
//                    "          \"Subject\": \"Gujarati\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 5,\n" +
//                    "          \"Subject\": \"Hindi\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 1,\n" +
//                    "          \"Subject\": \"Mathematics\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 69,\n" +
//                    "          \"Subject\": \"Practical\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 37,\n" +
//                    "          \"Subject\": \"Sanskrit\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 21,\n" +
//                    "          \"Subject\": \"Science\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 24,\n" +
//                    "          \"Subject\": \"Social Studies\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        }\n" +
//                    "      ]\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"StudentName\": \"Nish Patel\",\n" +
//                    "      \"StudentID\": 357,\n" +
//                    "      \"StudentSubject\": [\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 3,\n" +
//                    "          \"Subject\": \"Book Club\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 77,\n" +
//                    "          \"Subject\": \"Computers\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 2,\n" +
//                    "          \"Subject\": \"English\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 79,\n" +
//                    "          \"Subject\": \"Gujarati\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 5,\n" +
//                    "          \"Subject\": \"Hindi\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 1,\n" +
//                    "          \"Subject\": \"Mathematics\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 69,\n" +
//                    "          \"Subject\": \"Practical\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 37,\n" +
//                    "          \"Subject\": \"Sanskrit\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 21,\n" +
//                    "          \"Subject\": \"Science\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 24,\n" +
//                    "          \"Subject\": \"Social Studies\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        }\n" +
//                    "      ]\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"StudentName\": \"Nityashree Bardia\",\n" +
//                    "      \"StudentID\": 361,\n" +
//                    "      \"StudentSubject\": [\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 3,\n" +
//                    "          \"Subject\": \"Book Club\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 77,\n" +
//                    "          \"Subject\": \"Computers\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 2,\n" +
//                    "          \"Subject\": \"English\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 79,\n" +
//                    "          \"Subject\": \"Gujarati\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 5,\n" +
//                    "          \"Subject\": \"Hindi\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 1,\n" +
//                    "          \"Subject\": \"Mathematics\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 69,\n" +
//                    "          \"Subject\": \"Practical\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 37,\n" +
//                    "          \"Subject\": \"Sanskrit\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 21,\n" +
//                    "          \"Subject\": \"Science\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 24,\n" +
//                    "          \"Subject\": \"Social Studies\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        }\n" +
//                    "      ]\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"StudentName\": \"Prathita Gadhavi\",\n" +
//                    "      \"StudentID\": 367,\n" +
//                    "      \"StudentSubject\": [\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 3,\n" +
//                    "          \"Subject\": \"Book Club\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 77,\n" +
//                    "          \"Subject\": \"Computers\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 2,\n" +
//                    "          \"Subject\": \"English\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 79,\n" +
//                    "          \"Subject\": \"Gujarati\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 5,\n" +
//                    "          \"Subject\": \"Hindi\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 1,\n" +
//                    "          \"Subject\": \"Mathematics\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 69,\n" +
//                    "          \"Subject\": \"Practical\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 37,\n" +
//                    "          \"Subject\": \"Sanskrit\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 21,\n" +
//                    "          \"Subject\": \"Science\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 24,\n" +
//                    "          \"Subject\": \"Social Studies\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        }\n" +
//                    "      ]\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"StudentName\": \"Heer  Shah\",\n" +
//                    "      \"StudentID\": 410,\n" +
//                    "      \"StudentSubject\": [\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 3,\n" +
//                    "          \"Subject\": \"Book Club\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 77,\n" +
//                    "          \"Subject\": \"Computers\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 2,\n" +
//                    "          \"Subject\": \"English\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 79,\n" +
//                    "          \"Subject\": \"Gujarati\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 5,\n" +
//                    "          \"Subject\": \"Hindi\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 1,\n" +
//                    "          \"Subject\": \"Mathematics\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 69,\n" +
//                    "          \"Subject\": \"Practical\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 37,\n" +
//                    "          \"Subject\": \"Sanskrit\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 21,\n" +
//                    "          \"Subject\": \"Science\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 24,\n" +
//                    "          \"Subject\": \"Social Studies\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        }\n" +
//                    "      ]\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"StudentName\": \"Tamanna  Gogia\",\n" +
//                    "      \"StudentID\": 1625,\n" +
//                    "      \"StudentSubject\": [\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 3,\n" +
//                    "          \"Subject\": \"Book Club\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 77,\n" +
//                    "          \"Subject\": \"Computers\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 2,\n" +
//                    "          \"Subject\": \"English\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 79,\n" +
//                    "          \"Subject\": \"Gujarati\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 5,\n" +
//                    "          \"Subject\": \"Hindi\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 1,\n" +
//                    "          \"Subject\": \"Mathematics\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 69,\n" +
//                    "          \"Subject\": \"Practical\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 37,\n" +
//                    "          \"Subject\": \"Sanskrit\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 21,\n" +
//                    "          \"Subject\": \"Science\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 24,\n" +
//                    "          \"Subject\": \"Social Studies\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        }\n" +
//                    "      ]\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"StudentName\": \"Devam Patel\",\n" +
//                    "      \"StudentID\": 1941,\n" +
//                    "      \"StudentSubject\": [\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 3,\n" +
//                    "          \"Subject\": \"Book Club\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 77,\n" +
//                    "          \"Subject\": \"Computers\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 2,\n" +
//                    "          \"Subject\": \"English\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 79,\n" +
//                    "          \"Subject\": \"Gujarati\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 5,\n" +
//                    "          \"Subject\": \"Hindi\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 1,\n" +
//                    "          \"Subject\": \"Mathematics\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 69,\n" +
//                    "          \"Subject\": \"Practical\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 37,\n" +
//                    "          \"Subject\": \"Sanskrit\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 21,\n" +
//                    "          \"Subject\": \"Science\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 24,\n" +
//                    "          \"Subject\": \"Social Studies\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        }\n" +
//                    "      ]\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"StudentName\": \"Priyanka Devjani\",\n" +
//                    "      \"StudentID\": 1973,\n" +
//                    "      \"StudentSubject\": [\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 3,\n" +
//                    "          \"Subject\": \"Book Club\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 77,\n" +
//                    "          \"Subject\": \"Computers\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 2,\n" +
//                    "          \"Subject\": \"English\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 79,\n" +
//                    "          \"Subject\": \"Gujarati\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 5,\n" +
//                    "          \"Subject\": \"Hindi\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 1,\n" +
//                    "          \"Subject\": \"Mathematics\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 69,\n" +
//                    "          \"Subject\": \"Practical\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 37,\n" +
//                    "          \"Subject\": \"Sanskrit\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 21,\n" +
//                    "          \"Subject\": \"Science\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 24,\n" +
//                    "          \"Subject\": \"Social Studies\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        }\n" +
//                    "      ]\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"StudentName\": \"Aarav Patel\",\n" +
//                    "      \"StudentID\": 1974,\n" +
//                    "      \"StudentSubject\": [\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 3,\n" +
//                    "          \"Subject\": \"Book Club\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 77,\n" +
//                    "          \"Subject\": \"Computers\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 2,\n" +
//                    "          \"Subject\": \"English\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 79,\n" +
//                    "          \"Subject\": \"Gujarati\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 5,\n" +
//                    "          \"Subject\": \"Hindi\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 1,\n" +
//                    "          \"Subject\": \"Mathematics\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 69,\n" +
//                    "          \"Subject\": \"Practical\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 37,\n" +
//                    "          \"Subject\": \"Sanskrit\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 21,\n" +
//                    "          \"Subject\": \"Science\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 24,\n" +
//                    "          \"Subject\": \"Social Studies\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        }\n" +
//                    "      ]\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"StudentName\": \"Tirth Barot\",\n" +
//                    "      \"StudentID\": 1980,\n" +
//                    "      \"StudentSubject\": [\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 3,\n" +
//                    "          \"Subject\": \"Book Club\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 77,\n" +
//                    "          \"Subject\": \"Computers\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 2,\n" +
//                    "          \"Subject\": \"English\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 79,\n" +
//                    "          \"Subject\": \"Gujarati\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 5,\n" +
//                    "          \"Subject\": \"Hindi\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 1,\n" +
//                    "          \"Subject\": \"Mathematics\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 69,\n" +
//                    "          \"Subject\": \"Practical\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 37,\n" +
//                    "          \"Subject\": \"Sanskrit\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 21,\n" +
//                    "          \"Subject\": \"Science\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 24,\n" +
//                    "          \"Subject\": \"Social Studies\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        }\n" +
//                    "      ]\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"StudentName\": \"Bhavya Mehta\",\n" +
//                    "      \"StudentID\": 1986,\n" +
//                    "      \"StudentSubject\": [\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 3,\n" +
//                    "          \"Subject\": \"Book Club\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 77,\n" +
//                    "          \"Subject\": \"Computers\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 2,\n" +
//                    "          \"Subject\": \"English\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 79,\n" +
//                    "          \"Subject\": \"Gujarati\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 5,\n" +
//                    "          \"Subject\": \"Hindi\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 1,\n" +
//                    "          \"Subject\": \"Mathematics\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 69,\n" +
//                    "          \"Subject\": \"Practical\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 37,\n" +
//                    "          \"Subject\": \"Sanskrit\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 21,\n" +
//                    "          \"Subject\": \"Science\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 24,\n" +
//                    "          \"Subject\": \"Social Studies\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        }\n" +
//                    "      ]\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"StudentName\": \"Samarth Ruparelia\",\n" +
//                    "      \"StudentID\": 1990,\n" +
//                    "      \"StudentSubject\": [\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 3,\n" +
//                    "          \"Subject\": \"Book Club\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 77,\n" +
//                    "          \"Subject\": \"Computers\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 2,\n" +
//                    "          \"Subject\": \"English\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 79,\n" +
//                    "          \"Subject\": \"Gujarati\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 5,\n" +
//                    "          \"Subject\": \"Hindi\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 1,\n" +
//                    "          \"Subject\": \"Mathematics\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 69,\n" +
//                    "          \"Subject\": \"Practical\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 37,\n" +
//                    "          \"Subject\": \"Sanskrit\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 21,\n" +
//                    "          \"Subject\": \"Science\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 24,\n" +
//                    "          \"Subject\": \"Social Studies\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        }\n" +
//                    "      ]\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"StudentName\": \"DIYA SHAH\",\n" +
//                    "      \"StudentID\": 2325,\n" +
//                    "      \"StudentSubject\": [\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 3,\n" +
//                    "          \"Subject\": \"Book Club\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 77,\n" +
//                    "          \"Subject\": \"Computers\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 2,\n" +
//                    "          \"Subject\": \"English\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 79,\n" +
//                    "          \"Subject\": \"Gujarati\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 5,\n" +
//                    "          \"Subject\": \"Hindi\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 1,\n" +
//                    "          \"Subject\": \"Mathematics\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 69,\n" +
//                    "          \"Subject\": \"Practical\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 37,\n" +
//                    "          \"Subject\": \"Sanskrit\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 21,\n" +
//                    "          \"Subject\": \"Science\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 24,\n" +
//                    "          \"Subject\": \"Social Studies\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        }\n" +
//                    "      ]\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"StudentName\": \"PUSHPENDRA POPAT\",\n" +
//                    "      \"StudentID\": 2339,\n" +
//                    "      \"StudentSubject\": [\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 3,\n" +
//                    "          \"Subject\": \"Book Club\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 77,\n" +
//                    "          \"Subject\": \"Computers\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 2,\n" +
//                    "          \"Subject\": \"English\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 79,\n" +
//                    "          \"Subject\": \"Gujarati\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 5,\n" +
//                    "          \"Subject\": \"Hindi\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 1,\n" +
//                    "          \"Subject\": \"Mathematics\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 69,\n" +
//                    "          \"Subject\": \"Practical\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 37,\n" +
//                    "          \"Subject\": \"Sanskrit\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 21,\n" +
//                    "          \"Subject\": \"Science\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 24,\n" +
//                    "          \"Subject\": \"Social Studies\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        }\n" +
//                    "      ]\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"StudentName\": \"SAIKRISHNAN .\",\n" +
//                    "      \"StudentID\": 2395,\n" +
//                    "      \"StudentSubject\": [\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 3,\n" +
//                    "          \"Subject\": \"Book Club\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 77,\n" +
//                    "          \"Subject\": \"Computers\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 2,\n" +
//                    "          \"Subject\": \"English\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 79,\n" +
//                    "          \"Subject\": \"Gujarati\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 5,\n" +
//                    "          \"Subject\": \"Hindi\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 1,\n" +
//                    "          \"Subject\": \"Mathematics\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 69,\n" +
//                    "          \"Subject\": \"Practical\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 37,\n" +
//                    "          \"Subject\": \"Sanskrit\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 21,\n" +
//                    "          \"Subject\": \"Science\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 24,\n" +
//                    "          \"Subject\": \"Social Studies\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        }\n" +
//                    "      ]\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"StudentName\": \"MALHAR PILLAI\",\n" +
//                    "      \"StudentID\": 2404,\n" +
//                    "      \"StudentSubject\": [\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 3,\n" +
//                    "          \"Subject\": \"Book Club\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 77,\n" +
//                    "          \"Subject\": \"Computers\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 2,\n" +
//                    "          \"Subject\": \"English\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 79,\n" +
//                    "          \"Subject\": \"Gujarati\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 5,\n" +
//                    "          \"Subject\": \"Hindi\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 1,\n" +
//                    "          \"Subject\": \"Mathematics\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 69,\n" +
//                    "          \"Subject\": \"Practical\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 37,\n" +
//                    "          \"Subject\": \"Sanskrit\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 21,\n" +
//                    "          \"Subject\": \"Science\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"SubjectID\": 24,\n" +
//                    "          \"Subject\": \"Social Studies\",\n" +
//                    "          \"CheckedStatus\": \"0\"\n" +
//                    "        }\n" +
//                    "      ]\n" +
//                    "    }\n" +
//                    "  ]\n" +
//                    "}";
            Gson gson = new Gson();
            mainResponseStudentSubject = gson.fromJson(responseString, MainResponseStudentSubject.class);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return mainResponseStudentSubject;
    }

    @Override
    protected void onPostExecute(MainResponseStudentSubject result) {
        super.onPostExecute(result);
    }
}