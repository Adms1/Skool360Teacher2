package com.anandniketan.anandniketanskool360shilajTeacher.AsyncTasks;

import android.os.AsyncTask;

import com.anandniketan.anandniketanskool360shilajTeacher.Models.MarkResponse.MainResponse;
import com.anandniketan.anandniketanskool360shilajTeacher.Utility.AppConfiguration;
import com.anandniketan.anandniketanskool360shilajTeacher.WebServicesCall.WebServicesCall;
import com.google.gson.Gson;

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
            responseString = WebServicesCall.RunScript(AppConfiguration.getUrl(AppConfiguration.GetTeacherGetTestMarks), param);
//            responseString="{\n" +
//                    "  \"Success\": \"True\",\n" +
//                    "  \"FinalArray\": [\n" +
//                    "    {\n" +
//                    "      \"TestName\": \"Unit Test1\",\n" +
//                    "      \"StandardClass\": \"VI-A\",\n" +
//                    "      \"StudentData\": [\n" +
//                    "        {\n" +
//                    "          \"StudentName\": \"Aarya Trivedi\",\n" +
//                    "          \"StudentID\": 412,\n" +
//                    "          \"GRNO\": \"2661\",\n" +
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
//                    "              \"Subject\": \"Hindi\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            }\n" +
//                    "          ]\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentName\": \"Aaryan Modi\",\n" +
//                    "          \"StudentID\": 429,\n" +
//                    "          \"GRNO\": \"2633\",\n" +
//                    "          \"TotalMarks\": 0.00,\n" +
//                    "          \"TotalGainedMarks\": 0.00,\n" +
//                    "          \"Percentage\": \"0\",\n" +
//                    "          \"SubjectMarks\": [\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Book Club\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Computers\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Hindi\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            }\n" +
//                    "          ]\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentName\": \"Anushree Seth\",\n" +
//                    "          \"StudentID\": 425,\n" +
//                    "          \"GRNO\": \"2647\",\n" +
//                    "          \"TotalMarks\": 0.00,\n" +
//                    "          \"TotalGainedMarks\": 0.00,\n" +
//                    "          \"Percentage\": \"0\",\n" +
//                    "          \"SubjectMarks\": [\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Book Club\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Computers\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Hindi\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            }\n" +
//                    "          ]\n" +
//                    "        }\n" +
//                    "      ]\n" +
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
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"English\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Hindi\",\n" +
//                    "              \"Marks\": \"14.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Mathematics\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Sanskrit\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Science\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            }\n" +
//                    "          ]\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentName\": \"Aanya Bachhawat\",\n" +
//                    "          \"StudentID\": 295,\n" +
//                    "          \"GRNO\": \"2959\",\n" +
//                    "          \"TotalMarks\": 25.00,\n" +
//                    "          \"TotalGainedMarks\": 10.00,\n" +
//                    "          \"Percentage\": \"40\",\n" +
//                    "          \"SubjectMarks\": [\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Book Club\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Computers\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"English\",\n" +
//                    "              \"Marks\": \"10.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Hindi\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Mathematics\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Sanskrit\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Science\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            }\n" +
//                    "          ]\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentName\": \"Aarya Patel\",\n" +
//                    "          \"StudentID\": 296,\n" +
//                    "          \"GRNO\": \"2882\",\n" +
//                    "          \"TotalMarks\": 0.00,\n" +
//                    "          \"TotalGainedMarks\": 0.00,\n" +
//                    "          \"Percentage\": \"0\",\n" +
//                    "          \"SubjectMarks\": [\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Book Club\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Computers\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"English\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Hindi\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Mathematics\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Sanskrit\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Science\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            }\n" +
//                    "          ]\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentName\": \"Aayush Shah\",\n" +
//                    "          \"StudentID\": 299,\n" +
//                    "          \"GRNO\": \"2970\",\n" +
//                    "          \"TotalMarks\": 0.00,\n" +
//                    "          \"TotalGainedMarks\": 0.00,\n" +
//                    "          \"Percentage\": \"0\",\n" +
//                    "          \"SubjectMarks\": [\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Book Club\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Computers\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"English\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Hindi\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Mathematics\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Sanskrit\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Science\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            }\n" +
//                    "          ]\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentName\": \"Deepam Dave\",\n" +
//                    "          \"StudentID\": 309,\n" +
//                    "          \"GRNO\": \"2895\",\n" +
//                    "          \"TotalMarks\": 25.00,\n" +
//                    "          \"TotalGainedMarks\": 10.00,\n" +
//                    "          \"Percentage\": \"40\",\n" +
//                    "          \"SubjectMarks\": [\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Book Club\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Computers\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"English\",\n" +
//                    "              \"Marks\": \"10.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Hindi\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Mathematics\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Sanskrit\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Science\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            }\n" +
//                    "          ]\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentName\": \"Deepika Sinha\",\n" +
//                    "          \"StudentID\": 310,\n" +
//                    "          \"GRNO\": \"2885\",\n" +
//                    "          \"TotalMarks\": 0.00,\n" +
//                    "          \"TotalGainedMarks\": 0.00,\n" +
//                    "          \"Percentage\": \"0\",\n" +
//                    "          \"SubjectMarks\": [\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Book Club\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Computers\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"English\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Hindi\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Mathematics\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Sanskrit\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Science\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            }\n" +
//                    "          ]\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentName\": \"Hiyaa Gandhi\",\n" +
//                    "          \"StudentID\": 328,\n" +
//                    "          \"GRNO\": \"2933\",\n" +
//                    "          \"TotalMarks\": 0.00,\n" +
//                    "          \"TotalGainedMarks\": 0.00,\n" +
//                    "          \"Percentage\": \"0\",\n" +
//                    "          \"SubjectMarks\": [\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Book Club\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Computers\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"English\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Hindi\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Mathematics\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Sanskrit\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Science\",\n" +
//                    "              \"Marks\": \"0\"\n" +
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
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"English\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Hindi\",\n" +
//                    "              \"Marks\": \"75.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Mathematics\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Sanskrit\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Science\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            }\n" +
//                    "          ]\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentName\": \"Aanya Bachhawat\",\n" +
//                    "          \"StudentID\": 295,\n" +
//                    "          \"GRNO\": \"2959\",\n" +
//                    "          \"TotalMarks\": 80.00,\n" +
//                    "          \"TotalGainedMarks\": 12.00,\n" +
//                    "          \"Percentage\": \"15\",\n" +
//                    "          \"SubjectMarks\": [\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Book Club\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Computers\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"English\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Hindi\",\n" +
//                    "              \"Marks\": \"12.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Mathematics\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Sanskrit\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Science\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            }\n" +
//                    "          ]\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentName\": \"Aarya Patel\",\n" +
//                    "          \"StudentID\": 296,\n" +
//                    "          \"GRNO\": \"2882\",\n" +
//                    "          \"TotalMarks\": 0.00,\n" +
//                    "          \"TotalGainedMarks\": 0.00,\n" +
//                    "          \"Percentage\": \"0\",\n" +
//                    "          \"SubjectMarks\": [\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Book Club\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Computers\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"English\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Hindi\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Mathematics\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Sanskrit\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Science\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            }\n" +
//                    "          ]\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentName\": \"Aayush Shah\",\n" +
//                    "          \"StudentID\": 299,\n" +
//                    "          \"GRNO\": \"2970\",\n" +
//                    "          \"TotalMarks\": 0.00,\n" +
//                    "          \"TotalGainedMarks\": 0.00,\n" +
//                    "          \"Percentage\": \"0\",\n" +
//                    "          \"SubjectMarks\": [\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Book Club\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Computers\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"English\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Hindi\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Mathematics\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Sanskrit\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Science\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            }\n" +
//                    "          ]\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentName\": \"Deepam Dave\",\n" +
//                    "          \"StudentID\": 309,\n" +
//                    "          \"GRNO\": \"2895\",\n" +
//                    "          \"TotalMarks\": 0.00,\n" +
//                    "          \"TotalGainedMarks\": 0.00,\n" +
//                    "          \"Percentage\": \"0\",\n" +
//                    "          \"SubjectMarks\": [\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Book Club\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Computers\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"English\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Hindi\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Mathematics\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Sanskrit\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Science\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            }\n" +
//                    "          ]\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentName\": \"Deepika Sinha\",\n" +
//                    "          \"StudentID\": 310,\n" +
//                    "          \"GRNO\": \"2885\",\n" +
//                    "          \"TotalMarks\": 0.00,\n" +
//                    "          \"TotalGainedMarks\": 0.00,\n" +
//                    "          \"Percentage\": \"0\",\n" +
//                    "          \"SubjectMarks\": [\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Book Club\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Computers\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"English\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Hindi\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Mathematics\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Sanskrit\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Science\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            }\n" +
//                    "          ]\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentName\": \"Hiyaa Gandhi\",\n" +
//                    "          \"StudentID\": 328,\n" +
//                    "          \"GRNO\": \"2933\",\n" +
//                    "          \"TotalMarks\": 0.00,\n" +
//                    "          \"TotalGainedMarks\": 0.00,\n" +
//                    "          \"Percentage\": \"0\",\n" +
//                    "          \"SubjectMarks\": [\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Book Club\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Computers\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"English\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Hindi\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Mathematics\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Sanskrit\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Science\",\n" +
//                    "              \"Marks\": \"0\"\n" +
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
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"English\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Hindi\",\n" +
//                    "              \"Marks\": \"18.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Mathematics\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Sanskrit\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Science\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            }\n" +
//                    "          ]\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentName\": \"Aanya Bachhawat\",\n" +
//                    "          \"StudentID\": 295,\n" +
//                    "          \"GRNO\": \"2959\",\n" +
//                    "          \"TotalMarks\": 20.00,\n" +
//                    "          \"TotalGainedMarks\": 10.00,\n" +
//                    "          \"Percentage\": \"50\",\n" +
//                    "          \"SubjectMarks\": [\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Book Club\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Computers\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"English\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Hindi\",\n" +
//                    "              \"Marks\": \"10.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Mathematics\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Sanskrit\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Science\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            }\n" +
//                    "          ]\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentName\": \"Aarya Patel\",\n" +
//                    "          \"StudentID\": 296,\n" +
//                    "          \"GRNO\": \"2882\",\n" +
//                    "          \"TotalMarks\": 0.00,\n" +
//                    "          \"TotalGainedMarks\": 0.00,\n" +
//                    "          \"Percentage\": \"0\",\n" +
//                    "          \"SubjectMarks\": [\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Book Club\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Computers\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"English\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Hindi\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Mathematics\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Sanskrit\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Science\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            }\n" +
//                    "          ]\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentName\": \"Aayush Shah\",\n" +
//                    "          \"StudentID\": 299,\n" +
//                    "          \"GRNO\": \"2970\",\n" +
//                    "          \"TotalMarks\": 0.00,\n" +
//                    "          \"TotalGainedMarks\": 0.00,\n" +
//                    "          \"Percentage\": \"0\",\n" +
//                    "          \"SubjectMarks\": [\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Book Club\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Computers\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"English\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Hindi\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Mathematics\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Sanskrit\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Science\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            }\n" +
//                    "          ]\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentName\": \"Deepam Dave\",\n" +
//                    "          \"StudentID\": 309,\n" +
//                    "          \"GRNO\": \"2895\",\n" +
//                    "          \"TotalMarks\": 0.00,\n" +
//                    "          \"TotalGainedMarks\": 0.00,\n" +
//                    "          \"Percentage\": \"0\",\n" +
//                    "          \"SubjectMarks\": [\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Book Club\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Computers\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"English\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Hindi\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Mathematics\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Sanskrit\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Science\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            }\n" +
//                    "          ]\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentName\": \"Deepika Sinha\",\n" +
//                    "          \"StudentID\": 310,\n" +
//                    "          \"GRNO\": \"2885\",\n" +
//                    "          \"TotalMarks\": 0.00,\n" +
//                    "          \"TotalGainedMarks\": 0.00,\n" +
//                    "          \"Percentage\": \"0\",\n" +
//                    "          \"SubjectMarks\": [\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Book Club\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Computers\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"English\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Hindi\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Mathematics\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Sanskrit\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Science\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            }\n" +
//                    "          ]\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentName\": \"Hiyaa Gandhi\",\n" +
//                    "          \"StudentID\": 328,\n" +
//                    "          \"GRNO\": \"2933\",\n" +
//                    "          \"TotalMarks\": 20.00,\n" +
//                    "          \"TotalGainedMarks\": 5.00,\n" +
//                    "          \"Percentage\": \"25\",\n" +
//                    "          \"SubjectMarks\": [\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Book Club\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Computers\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"English\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Hindi\",\n" +
//                    "              \"Marks\": \"5.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Mathematics\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Sanskrit\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Science\",\n" +
//                    "              \"Marks\": \"0\"\n" +
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
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"English\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Hindi\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Mathematics\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Sanskrit\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Science\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            }\n" +
//                    "          ]\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentName\": \"Aanya Bachhawat\",\n" +
//                    "          \"StudentID\": 295,\n" +
//                    "          \"GRNO\": \"2959\",\n" +
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
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"English\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Hindi\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Mathematics\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Sanskrit\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Science\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            }\n" +
//                    "          ]\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentName\": \"Aarya Patel\",\n" +
//                    "          \"StudentID\": 296,\n" +
//                    "          \"GRNO\": \"2882\",\n" +
//                    "          \"TotalMarks\": 0.00,\n" +
//                    "          \"TotalGainedMarks\": 0.00,\n" +
//                    "          \"Percentage\": \"0\",\n" +
//                    "          \"SubjectMarks\": [\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Book Club\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Computers\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"English\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Hindi\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Mathematics\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Sanskrit\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Science\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            }\n" +
//                    "          ]\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentName\": \"Aayush Shah\",\n" +
//                    "          \"StudentID\": 299,\n" +
//                    "          \"GRNO\": \"2970\",\n" +
//                    "          \"TotalMarks\": 0.00,\n" +
//                    "          \"TotalGainedMarks\": 0.00,\n" +
//                    "          \"Percentage\": \"0\",\n" +
//                    "          \"SubjectMarks\": [\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Book Club\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Computers\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"English\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Hindi\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Mathematics\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Sanskrit\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Science\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            }\n" +
//                    "          ]\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentName\": \"Deepam Dave\",\n" +
//                    "          \"StudentID\": 309,\n" +
//                    "          \"GRNO\": \"2895\",\n" +
//                    "          \"TotalMarks\": 0.00,\n" +
//                    "          \"TotalGainedMarks\": 0.00,\n" +
//                    "          \"Percentage\": \"0\",\n" +
//                    "          \"SubjectMarks\": [\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Book Club\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Computers\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"English\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Hindi\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Mathematics\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Sanskrit\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Science\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            }\n" +
//                    "          ]\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentName\": \"Deepika Sinha\",\n" +
//                    "          \"StudentID\": 310,\n" +
//                    "          \"GRNO\": \"2885\",\n" +
//                    "          \"TotalMarks\": 0.00,\n" +
//                    "          \"TotalGainedMarks\": 0.00,\n" +
//                    "          \"Percentage\": \"0\",\n" +
//                    "          \"SubjectMarks\": [\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Book Club\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Computers\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"English\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Hindi\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Mathematics\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Sanskrit\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Science\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            }\n" +
//                    "          ]\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentName\": \"Hiyaa Gandhi\",\n" +
//                    "          \"StudentID\": 328,\n" +
//                    "          \"GRNO\": \"2933\",\n" +
//                    "          \"TotalMarks\": 0.00,\n" +
//                    "          \"TotalGainedMarks\": 0.00,\n" +
//                    "          \"Percentage\": \"0\",\n" +
//                    "          \"SubjectMarks\": [\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Book Club\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Computers\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"English\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Hindi\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Mathematics\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Sanskrit\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Science\",\n" +
//                    "              \"Marks\": \"0\"\n" +
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
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"TestName\": \"N/A\",\n" +
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
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"English\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Hindi\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Mathematics\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Sanskrit\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Science\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            }\n" +
//                    "          ]\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentName\": \"Aanya Bachhawat\",\n" +
//                    "          \"StudentID\": 295,\n" +
//                    "          \"GRNO\": \"2959\",\n" +
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
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"English\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Hindi\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Mathematics\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Sanskrit\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Science\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            }\n" +
//                    "          ]\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentName\": \"Aarya Patel\",\n" +
//                    "          \"StudentID\": 296,\n" +
//                    "          \"GRNO\": \"2882\",\n" +
//                    "          \"TotalMarks\": 0.00,\n" +
//                    "          \"TotalGainedMarks\": 0.00,\n" +
//                    "          \"Percentage\": \"0\",\n" +
//                    "          \"SubjectMarks\": [\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Book Club\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Computers\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"English\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Hindi\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Mathematics\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Sanskrit\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Science\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            }\n" +
//                    "          ]\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentName\": \"Aayush Shah\",\n" +
//                    "          \"StudentID\": 299,\n" +
//                    "          \"GRNO\": \"2970\",\n" +
//                    "          \"TotalMarks\": 0.00,\n" +
//                    "          \"TotalGainedMarks\": 0.00,\n" +
//                    "          \"Percentage\": \"0\",\n" +
//                    "          \"SubjectMarks\": [\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Book Club\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Computers\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"English\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Hindi\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Mathematics\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Sanskrit\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Science\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            }\n" +
//                    "          ]\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentName\": \"Deepam Dave\",\n" +
//                    "          \"StudentID\": 309,\n" +
//                    "          \"GRNO\": \"2895\",\n" +
//                    "          \"TotalMarks\": 0.00,\n" +
//                    "          \"TotalGainedMarks\": 0.00,\n" +
//                    "          \"Percentage\": \"0\",\n" +
//                    "          \"SubjectMarks\": [\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Book Club\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Computers\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"English\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Hindi\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Mathematics\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Sanskrit\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Science\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            }\n" +
//                    "          ]\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentName\": \"Deepika Sinha\",\n" +
//                    "          \"StudentID\": 310,\n" +
//                    "          \"GRNO\": \"2885\",\n" +
//                    "          \"TotalMarks\": 0.00,\n" +
//                    "          \"TotalGainedMarks\": 0.00,\n" +
//                    "          \"Percentage\": \"0\",\n" +
//                    "          \"SubjectMarks\": [\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Book Club\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Computers\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"English\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Hindi\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Mathematics\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Sanskrit\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Science\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            }\n" +
//                    "          ]\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentName\": \"Hiyaa Gandhi\",\n" +
//                    "          \"StudentID\": 328,\n" +
//                    "          \"GRNO\": \"2933\",\n" +
//                    "          \"TotalMarks\": 0.00,\n" +
//                    "          \"TotalGainedMarks\": 0.00,\n" +
//                    "          \"Percentage\": \"0\",\n" +
//                    "          \"SubjectMarks\": [\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Book Club\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Computers\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"English\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Hindi\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Mathematics\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Sanskrit\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Science\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            }\n" +
//                    "          ]\n" +
//                    "        }\n" +
//                    "      ]\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"TestName\": \"N/A\",\n" +
//                    "      \"StandardClass\": \"VII-C\",\n" +
//                    "      \"StudentData\": []\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"TestName\": \"N/A\",\n" +
//                    "      \"StandardClass\": \"VII-D\",\n" +
//                    "      \"StudentData\": []\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"TestName\": \"N/A\",\n" +
//                    "      \"StandardClass\": \"VII-E\",\n" +
//                    "      \"StudentData\": []\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"TestName\": \"Half Yearly\",\n" +
//                    "      \"StandardClass\": \"VI-A\",\n" +
//                    "      \"StudentData\": [\n" +
//                    "        {\n" +
//                    "          \"StudentName\": \"Aarya Trivedi\",\n" +
//                    "          \"StudentID\": 412,\n" +
//                    "          \"GRNO\": \"2661\",\n" +
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
//                    "              \"Subject\": \"Hindi\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            }\n" +
//                    "          ]\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentName\": \"Aaryan Modi\",\n" +
//                    "          \"StudentID\": 429,\n" +
//                    "          \"GRNO\": \"2633\",\n" +
//                    "          \"TotalMarks\": 0.00,\n" +
//                    "          \"TotalGainedMarks\": 0.00,\n" +
//                    "          \"Percentage\": \"0\",\n" +
//                    "          \"SubjectMarks\": [\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Book Club\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Computers\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Hindi\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            }\n" +
//                    "          ]\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentName\": \"Anushree Seth\",\n" +
//                    "          \"StudentID\": 425,\n" +
//                    "          \"GRNO\": \"2647\",\n" +
//                    "          \"TotalMarks\": 0.00,\n" +
//                    "          \"TotalGainedMarks\": 0.00,\n" +
//                    "          \"Percentage\": \"0\",\n" +
//                    "          \"SubjectMarks\": [\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Book Club\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Computers\",\n" +
//                    "              \"Marks\": \"0\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Hindi\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            }\n" +
//                    "          ]\n" +
//                    "        }\n" +
//                    "      ]\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"TestName\": \"Half Yearly\",\n" +
//                    "      \"StandardClass\": \"VII-E\",\n" +
//                    "      \"StudentData\": []\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"TestName\": \"Half Yearly\",\n" +
//                    "      \"StandardClass\": \"V-F\",\n" +
//                    "      \"StudentData\": []\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"TestName\": \"Half Yearly\",\n" +
//                    "      \"StandardClass\": \"K2-A\",\n" +
//                    "      \"StudentData\": []\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"TestName\": \"Unit Test1\",\n" +
//                    "      \"StandardClass\": \"K2-C\",\n" +
//                    "      \"StudentData\": []\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"TestName\": \"Half Yearly\",\n" +
//                    "      \"StandardClass\": \"VII-A\",\n" +
//                    "      \"StudentData\": [\n" +
//                    "        {\n" +
//                    "          \"StudentName\": \"Anjali Vaghela\",\n" +
//                    "          \"StudentID\": 555,\n" +
//                    "          \"GRNO\": \"2820\",\n" +
//                    "          \"TotalMarks\": 0.00,\n" +
//                    "          \"TotalGainedMarks\": 0.00,\n" +
//                    "          \"Percentage\": \"0\",\n" +
//                    "          \"SubjectMarks\": [\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Hindi\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            }\n" +
//                    "          ]\n" +
//                    "        }\n" +
//                    "      ]\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"TestName\": \"Half Yearly\",\n" +
//                    "      \"StandardClass\": \"VII-C\",\n" +
//                    "      \"StudentData\": []\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"TestName\": \"Half Yearly\",\n" +
//                    "      \"StandardClass\": \"VII-D\",\n" +
//                    "      \"StudentData\": []\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"TestName\": \"Unit Test1\",\n" +
//                    "      \"StandardClass\": \"VII-A\",\n" +
//                    "      \"StudentData\": [\n" +
//                    "        {\n" +
//                    "          \"StudentName\": \"Anjali Vaghela\",\n" +
//                    "          \"StudentID\": 555,\n" +
//                    "          \"GRNO\": \"2820\",\n" +
//                    "          \"TotalMarks\": 0.00,\n" +
//                    "          \"TotalGainedMarks\": 0.00,\n" +
//                    "          \"Percentage\": \"0\",\n" +
//                    "          \"SubjectMarks\": [\n" +
//                    "            {\n" +
//                    "              \"Subject\": \"Hindi\",\n" +
//                    "              \"Marks\": \"0.00\"\n" +
//                    "            }\n" +
//                    "          ]\n" +
//                    "        }\n" +
//                    "      ]\n" +
//                    "    }\n" +
//                    "  ]\n" +
//                    "}";
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
