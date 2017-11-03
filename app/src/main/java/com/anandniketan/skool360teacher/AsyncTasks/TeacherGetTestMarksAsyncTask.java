package com.anandniketan.skool360teacher.AsyncTasks;

import android.os.AsyncTask;

import com.anandniketan.skool360teacher.Models.NewResponse.MainResponse;
import com.anandniketan.skool360teacher.Models.TeacherGetTestMarksModel;
import com.anandniketan.skool360teacher.Models.TeacherGetTimetableModel;
import com.anandniketan.skool360teacher.Utility.AppConfiguration;
import com.anandniketan.skool360teacher.Utility.ParseJSON;
import com.anandniketan.skool360teacher.WebServicesCall.WebServicesCall;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by admsandroid on 9/27/2017.
 */

public class TeacherGetTestMarksAsyncTask extends AsyncTask<Void, Void,MainResponse > {
    HashMap<String, String> param = new HashMap<String, String>();

    public TeacherGetTestMarksAsyncTask(HashMap<String, String> param) {
        this.param = param;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected MainResponse doInBackground(Void... params) {
        String responseString = null;
        MainResponse response =null;
        try {

//            responseString="{\n" +
//                    "  \"Success\": \"True\",\n" +
//                    "  \"FinalArrayLesson\": [\n" +
//                    "    {\n" +
//                    "      \"TestName\": \"Unit Test1\",\n" +
//                    "      \"StandardClass\": \"VI-A\",\n" +
//                    "      \"StudentData\": []\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"TestName\": \"Unit Test1\",\n" +
//                    "      \"StandardClass\": \"VII-D\",\n" +
//                    "      \"StudentData\": []\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"TestName\": \"Weekly Evaluation Term-I\",\n" +
//                    "      \"StandardClass\": \"V-A\",\n" +
//                    "      \"StudentData\": [\n" +
//                    "        {\n" +
//                    "          \"StudentName\": \"Aadit Shah\",\n" +
//                    "          \"StudentID\": 294,\n" +
//                    "          \"GRNO\": \"2989\",\n" +
//                    "          \"TotalMarks\": 25.00,\n" +
//                    "          \"TotalGainedMarks\": 14.00,\n" +
//                    "          \"Percentage\": \"56\",\n" +
//                    "          \"SubjectMarks\": [\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Book Club\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Computers\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"English\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Gujarati\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Hindi\",\n" +
//                    "              \"Marks\": \"14.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Mathematics\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            }\n" +
//                    "          ]\n" +
//                    "        }\n" +
//                    "      ]\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"TestName\": \"Half Yearly\",\n" +
//                    "      \"StandardClass\": \"V-A\",\n" +
//                    "      \"StudentData\": [\n" +
//                    "        {\n" +
//                    "          \"StudentName\": \"Aadit Shah\",\n" +
//                    "          \"StudentID\": 294,\n" +
//                    "          \"GRNO\": \"2989\",\n" +
//                    "          \"TotalMarks\": 80.00,\n" +
//                    "          \"TotalGainedMarks\": 75.00,\n" +
//                    "          \"Percentage\": \"93.75\",\n" +
//                    "          \"SubjectMarks\": [\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Book Club\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Computers\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"English\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Gujarati\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Hindi\",\n" +
//                    "              \"Marks\": \"75.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Mathematics\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            }\n" +
//                    "          ]\n" +
//                    "        }\n" +
//                    "      ]\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"TestName\": \"Unit Test1\",\n" +
//                    "      \"StandardClass\": \"V-A\",\n" +
//                    "      \"StudentData\": [\n" +
//                    "        {\n" +
//                    "          \"StudentName\": \"Aadit Shah\",\n" +
//                    "          \"StudentID\": 294,\n" +
//                    "          \"GRNO\": \"2989\",\n" +
//                    "          \"TotalMarks\": 30.00,\n" +
//                    "          \"TotalGainedMarks\": 26.00,\n" +
//                    "          \"Percentage\": \"86.67\",\n" +
//                    "          \"SubjectMarks\": [\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Book Club\",\n" +
//                    "              \"Marks\": \"8.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Computers\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"English\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Gujarati\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Hindi\",\n" +
//                    "              \"Marks\": \"18.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Mathematics\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            }\n" +
//                    "          ]\n" +
//                    "        }\n" +
//                    "      ]\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"TestName\": \"Unit Test1\",\n" +
//                    "      \"StandardClass\": \"VI-B\",\n" +
//                    "      \"StudentData\": []\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"TestName\": \"Unit Test1\",\n" +
//                    "      \"StandardClass\": \"VI-C\",\n" +
//                    "      \"StudentData\": []\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"TestName\": \"Unit Test1\",\n" +
//                    "      \"StandardClass\": \"VI-D\",\n" +
//                    "      \"StudentData\": []\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"TestName\": \"Unit Test1\",\n" +
//                    "      \"StandardClass\": \"VI-E\",\n" +
//                    "      \"StudentData\": []\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"TestName\": \"Unit Test2\",\n" +
//                    "      \"StandardClass\": \"V-A\",\n" +
//                    "      \"StudentData\": [\n" +
//                    "        {\n" +
//                    "          \"StudentName\": \"Aadit Shah\",\n" +
//                    "          \"StudentID\": 294,\n" +
//                    "          \"GRNO\": \"2989\",\n" +
//                    "          \"TotalMarks\": 0.00,\n" +
//                    "          \"TotalGainedMarks\": 0.00,\n" +
//                    "          \"Percentage\": \"0\",\n" +
//                    "          \"SubjectMarks\": [\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Book Club\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Computers\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"English\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Gujarati\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Hindi\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Mathematics\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            }\n" +
//                    "          ]\n" +
//                    "        }\n" +
//                    "      ]\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"TestName\": \"Unit Test1\",\n" +
//                    "      \"StandardClass\": \"V-C\",\n" +
//                    "      \"StudentData\": []\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"TestName\": \"Unit Test1\",\n" +
//                    "      \"StandardClass\": \"V-F\",\n" +
//                    "      \"StudentData\": []\n" +
//                    "    }\n" +
//                    "]\n" +
//                    "}";
            responseString = WebServicesCall.RunScript(AppConfiguration.getUrl(AppConfiguration.GetTeacherGetTestMarks), param);
//            response = ParseJSON.parseTeacherGetTestMarksJson(responseString);
            Gson gson = new Gson();
            response = gson.fromJson(responseString, MainResponse.class);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return response;
    }

    @Override
    protected void onPostExecute(MainResponse result) {
        super.onPostExecute(result);
    }
}
