package com.anandniketan.skool360teacher.AsyncTasks;

import android.os.AsyncTask;

import com.anandniketan.skool360teacher.Models.StaffAttendanceModel;
import com.anandniketan.skool360teacher.Utility.AppConfiguration;
import com.anandniketan.skool360teacher.Utility.ParseJSON;
import com.anandniketan.skool360teacher.WebServicesCall.WebServicesCall;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by admsandroid on 9/18/2017.
 */

public class GetStaffAttendanceAsyncTask extends AsyncTask<Void, Void, ArrayList<StaffAttendanceModel>> {
    HashMap<String, String> param = new HashMap<String, String>();

    public GetStaffAttendanceAsyncTask(HashMap<String, String> param) {
        this.param = param;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ArrayList<StaffAttendanceModel> doInBackground(Void... params) {
        String responseString = null;
        ArrayList<StaffAttendanceModel> result = null;
        try {
            responseString = WebServicesCall.RunScript(AppConfiguration.getUrl(AppConfiguration.GetStaffAttendence), param);
            result = ParseJSON.parseStaffAttendanceJson(responseString);

//            responseString="{\n" +
//                    "  \"Success\": \"True\",\n" +
//                    "  \"Date\": \"03/11/2017\",\n" +
//                    "  \"FinalArray\": [\n" +
//                    "    {\n" +
//                    "      \"StandardID\": 8,\n" +
//                    "      \"ClassID\": 28,\n" +
//                    "      \"Total\": 30,\n" +
//                    "      \"TotalPresent\": 0,\n" +
//                    "      \"TotalAbsent\": 0,\n" +
//                    "      \"TotalLeave\": 0,\n" +
//                    "      \"StudentDetail\": [\n" +
//                    "        {\n" +
//                    "          \"StudentID\": 2325,\n" +
//                    "          \"StudentName\": \"DIYA AMIT SHAH\",\n" +
//                    "          \"StudentImage\": \"http://103.8.216.132\",\n" +
//                    "          \"AttendanceID\": 0,\n" +
//                    "          \"AttendenceStatus\": \"-2\",\n" +
//                    "          \"Comment\": \"\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentID\": 2339,\n" +
//                    "          \"StudentName\": \"PUSHPENDRA ASHUTOSH POPAT\",\n" +
//                    "          \"StudentImage\": \"http://103.8.216.132\",\n" +
//                    "          \"AttendanceID\": 0,\n" +
//                    "          \"AttendenceStatus\": \"-2\",\n" +
//                    "          \"Comment\": \"\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentID\": 2395,\n" +
//                    "          \"StudentName\": \"SAIKRISHNAN  .\",\n" +
//                    "          \"StudentImage\": \"http://103.8.216.132\",\n" +
//                    "          \"AttendanceID\": 0,\n" +
//                    "          \"AttendenceStatus\": \"-2\",\n" +
//                    "          \"Comment\": \"\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentID\": 1974,\n" +
//                    "          \"StudentName\": \"Aarav Kunal Patel\",\n" +
//                    "          \"StudentImage\": \"http://103.8.216.132/test/3561.JPG\",\n" +
//                    "          \"AttendanceID\": 0,\n" +
//                    "          \"AttendenceStatus\": \"-2\",\n" +
//                    "          \"Comment\": \"\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentID\": 299,\n" +
//                    "          \"StudentName\": \"Aayush Pavan Shah\",\n" +
//                    "          \"StudentImage\": \"http://103.8.216.132/test/2970.JPG\",\n" +
//                    "          \"AttendanceID\": 0,\n" +
//                    "          \"AttendenceStatus\": \"-2\",\n" +
//                    "          \"Comment\": \"\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentID\": 304,\n" +
//                    "          \"StudentName\": \"Arya Jashubhai Patel\",\n" +
//                    "          \"StudentImage\": \"http://103.8.216.132/test/2980.JPG\",\n" +
//                    "          \"AttendanceID\": 0,\n" +
//                    "          \"AttendenceStatus\": \"-2\",\n" +
//                    "          \"Comment\": \"\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentID\": 310,\n" +
//                    "          \"StudentName\": \"Deepika Mahesh Sinha\",\n" +
//                    "          \"StudentImage\": \"http://103.8.216.132/test/2885.JPG\",\n" +
//                    "          \"AttendanceID\": 0,\n" +
//                    "          \"AttendenceStatus\": \"-2\",\n" +
//                    "          \"Comment\": \"\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentID\": 410,\n" +
//                    "          \"StudentName\": \"Heer  Yatin Shah\",\n" +
//                    "          \"StudentImage\": \"http://103.8.216.132/test/3211.JPG\",\n" +
//                    "          \"AttendanceID\": 0,\n" +
//                    "          \"AttendenceStatus\": \"-2\",\n" +
//                    "          \"Comment\": \"\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentID\": 347,\n" +
//                    "          \"StudentName\": \"Manav Pradip Kotadia\",\n" +
//                    "          \"StudentImage\": \"http://103.8.216.132/test/2960.JPG\",\n" +
//                    "          \"AttendanceID\": 0,\n" +
//                    "          \"AttendenceStatus\": \"-2\",\n" +
//                    "          \"Comment\": \"\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentID\": 361,\n" +
//                    "          \"StudentName\": \"Nityashree Deepak Bardia\",\n" +
//                    "          \"StudentImage\": \"http://103.8.216.132/test/2924.JPG\",\n" +
//                    "          \"AttendanceID\": 0,\n" +
//                    "          \"AttendenceStatus\": \"-2\",\n" +
//                    "          \"Comment\": \"\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentID\": 1625,\n" +
//                    "          \"StudentName\": \"Tamanna  Ram  Gogia\",\n" +
//                    "          \"StudentImage\": \"http://103.8.216.132/test/3398.JPG\",\n" +
//                    "          \"AttendanceID\": 0,\n" +
//                    "          \"AttendenceStatus\": \"-2\",\n" +
//                    "          \"Comment\": \"\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentID\": 294,\n" +
//                    "          \"StudentName\": \"Aadit Bhadresh Shah\",\n" +
//                    "          \"StudentImage\": \"http://103.8.216.132/test/2989.JPG\",\n" +
//                    "          \"AttendanceID\": 0,\n" +
//                    "          \"AttendenceStatus\": \"-2\",\n" +
//                    "          \"Comment\": \"\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentID\": 1941,\n" +
//                    "          \"StudentName\": \"Devam Niketan Patel\",\n" +
//                    "          \"StudentImage\": \"http://103.8.216.132/test/3533.JPG\",\n" +
//                    "          \"AttendanceID\": 0,\n" +
//                    "          \"AttendenceStatus\": \"-2\",\n" +
//                    "          \"Comment\": \"\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentID\": 318,\n" +
//                    "          \"StudentName\": \"Diya Mittal Patel\",\n" +
//                    "          \"StudentImage\": \"http://103.8.216.132/test/2937.JPG\",\n" +
//                    "          \"AttendanceID\": 0,\n" +
//                    "          \"AttendenceStatus\": \"-2\",\n" +
//                    "          \"Comment\": \"\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentID\": 328,\n" +
//                    "          \"StudentName\": \"Hiyaa Pankaj Gandhi\",\n" +
//                    "          \"StudentImage\": \"http://103.8.216.132/test/2933.JPG\",\n" +
//                    "          \"AttendanceID\": 0,\n" +
//                    "          \"AttendenceStatus\": \"-2\",\n" +
//                    "          \"Comment\": \"\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentID\": 1980,\n" +
//                    "          \"StudentName\": \"Tirth Mukeshbhai Barot\",\n" +
//                    "          \"StudentImage\": \"http://103.8.216.132/test/3566.JPG\",\n" +
//                    "          \"AttendanceID\": 0,\n" +
//                    "          \"AttendenceStatus\": \"-2\",\n" +
//                    "          \"Comment\": \"\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentID\": 1986,\n" +
//                    "          \"StudentName\": \"Bhavya Tushar Mehta\",\n" +
//                    "          \"StudentImage\": \"http://103.8.216.132/test/3570.JPG\",\n" +
//                    "          \"AttendanceID\": 0,\n" +
//                    "          \"AttendenceStatus\": \"-2\",\n" +
//                    "          \"Comment\": \"\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentID\": 307,\n" +
//                    "          \"StudentName\": \"Bhrithi Hemant Gajaria\",\n" +
//                    "          \"StudentImage\": \"http://103.8.216.132/test/2932.JPG\",\n" +
//                    "          \"AttendanceID\": 0,\n" +
//                    "          \"AttendenceStatus\": \"-2\",\n" +
//                    "          \"Comment\": \"\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentID\": 340,\n" +
//                    "          \"StudentName\": \"Khushi Ketan Gandhi\",\n" +
//                    "          \"StudentImage\": \"http://103.8.216.132/test/2942.JPG\",\n" +
//                    "          \"AttendanceID\": 0,\n" +
//                    "          \"AttendenceStatus\": \"-2\",\n" +
//                    "          \"Comment\": \"\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentID\": 367,\n" +
//                    "          \"StudentName\": \"Prathita Anil Gadhavi\",\n" +
//                    "          \"StudentImage\": \"http://103.8.216.132/test/2974.JPG\",\n" +
//                    "          \"AttendanceID\": 0,\n" +
//                    "          \"AttendenceStatus\": \"-2\",\n" +
//                    "          \"Comment\": \"\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentID\": 1990,\n" +
//                    "          \"StudentName\": \"Samarth  Dhavalkumar Ruparelia\",\n" +
//                    "          \"StudentImage\": \"http://103.8.216.132/test/3573.JPG\",\n" +
//                    "          \"AttendanceID\": 0,\n" +
//                    "          \"AttendenceStatus\": \"-2\",\n" +
//                    "          \"Comment\": \"\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentID\": 309,\n" +
//                    "          \"StudentName\": \"Deepam Samir Dave\",\n" +
//                    "          \"StudentImage\": \"http://103.8.216.132/test/2895.JPG\",\n" +
//                    "          \"AttendanceID\": 0,\n" +
//                    "          \"AttendenceStatus\": \"-2\",\n" +
//                    "          \"Comment\": \"\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentID\": 349,\n" +
//                    "          \"StudentName\": \"Manvi Alok Gaur\",\n" +
//                    "          \"StudentImage\": \"http://103.8.216.132/test/2995.JPG\",\n" +
//                    "          \"AttendanceID\": 0,\n" +
//                    "          \"AttendenceStatus\": \"-2\",\n" +
//                    "          \"Comment\": \"\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentID\": 1973,\n" +
//                    "          \"StudentName\": \"Priyanka Dilip  Devjani\",\n" +
//                    "          \"StudentImage\": \"http://103.8.216.132/test/3560.JPG\",\n" +
//                    "          \"AttendanceID\": 0,\n" +
//                    "          \"AttendenceStatus\": \"-2\",\n" +
//                    "          \"Comment\": \"\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentID\": 295,\n" +
//                    "          \"StudentName\": \"Aanya Kalpesh Bachhawat\",\n" +
//                    "          \"StudentImage\": \"http://103.8.216.132/test/2959.jpg\",\n" +
//                    "          \"AttendanceID\": 0,\n" +
//                    "          \"AttendenceStatus\": \"-2\",\n" +
//                    "          \"Comment\": \"\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentID\": 296,\n" +
//                    "          \"StudentName\": \"Aarya Ashish Patel\",\n" +
//                    "          \"StudentImage\": \"http://103.8.216.132/test/2882.jpg\",\n" +
//                    "          \"AttendanceID\": 0,\n" +
//                    "          \"AttendenceStatus\": \"-2\",\n" +
//                    "          \"Comment\": \"\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentID\": 345,\n" +
//                    "          \"StudentName\": \"Maanank Sujal Shah\",\n" +
//                    "          \"StudentImage\": \"http://103.8.216.132/test/2903.jpg\",\n" +
//                    "          \"AttendanceID\": 0,\n" +
//                    "          \"AttendenceStatus\": \"-2\",\n" +
//                    "          \"Comment\": \"\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentID\": 355,\n" +
//                    "          \"StudentName\": \"Nikki Sandip Shah\",\n" +
//                    "          \"StudentImage\": \"http://103.8.216.132/test/2923.jpg\",\n" +
//                    "          \"AttendanceID\": 0,\n" +
//                    "          \"AttendenceStatus\": \"-2\",\n" +
//                    "          \"Comment\": \"\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentID\": 357,\n" +
//                    "          \"StudentName\": \"Nish Sanjay Patel\",\n" +
//                    "          \"StudentImage\": \"http://103.8.216.132/test/2994.jpg\",\n" +
//                    "          \"AttendanceID\": 0,\n" +
//                    "          \"AttendenceStatus\": \"-2\",\n" +
//                    "          \"Comment\": \"\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "          \"StudentID\": 2404,\n" +
//                    "          \"StudentName\": \"MALHAR DEVA PILLAI\",\n" +
//                    "          \"StudentImage\": \"http://103.8.216.132\",\n" +
//                    "          \"AttendanceID\": 0,\n" +
//                    "          \"AttendenceStatus\": \"-2\",\n" +
//                    "          \"Comment\": \"\"\n" +
//                    "        }\n" +
//                    "      ]\n" +
//                    "    }\n" +
//                    "  ]\n" +
//                    "}";

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(ArrayList<StaffAttendanceModel> result) {
        super.onPostExecute(result);
    }
}