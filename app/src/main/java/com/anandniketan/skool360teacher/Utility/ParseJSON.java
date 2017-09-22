package com.anandniketan.skool360teacher.Utility;

import com.anandniketan.skool360teacher.Models.InsertAttendanceModel;
import com.anandniketan.skool360teacher.Models.LoginModel;
import com.anandniketan.skool360teacher.Models.StaffAttendanceModel;
import com.anandniketan.skool360teacher.Models.TeacherTodayScheduleModel;
import com.anandniketan.skool360teacher.Models.UserProfileModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Megha on 15-Sep-17.
 */
public class ParseJSON {

    public static ArrayList<LoginModel> parseLoginJson(String responseString) {
        ArrayList<LoginModel> result = new ArrayList<>();

        try {
            JSONObject reader = new JSONObject(responseString);
            String data_load_basket = reader.getString("Success");
            LoginModel loginModel = null;

            if (data_load_basket.toString().equals("True")) {


                JSONArray jsonMainNode = reader.optJSONArray("FinalArray");
                for (int a = 0; a < jsonMainNode.length(); a++) {
                    loginModel = new LoginModel();
                    JSONObject jsonChildNode = jsonMainNode.getJSONObject(a);
                    loginModel.setStaffID(jsonChildNode.getString("StaffID"));
                    loginModel.setEmp_Name(jsonChildNode.getString("Emp_Name"));
                    loginModel.setEmp_Code(jsonChildNode.getString("Emp_Code"));
                    loginModel.setDeviceId(jsonChildNode.getString("DeviceId"));
                    loginModel.setDesignationID(jsonChildNode.getString("DesignationID"));
                    loginModel.setDepratmentID(jsonChildNode.getString("DepratmentID"));

                    LoginModel.ClassDetail data = null;
                    ArrayList<LoginModel.ClassDetail> dataArrayList = new ArrayList<>();
                    JSONArray jsonChildMainNode = jsonChildNode.optJSONArray("ClassDetail");
                    for (int i = 0; i < jsonChildMainNode.length(); i++) {
                        data = loginModel.new ClassDetail();
                        JSONObject jsonChildNode1 = jsonChildMainNode.getJSONObject(i);
                        data.setClassID(jsonChildNode1.getString("ClassID"));
                        data.setStandardID(jsonChildNode1.getString("StandardID"));
                        data.setStandard(jsonChildNode1.getString("Standard"));
                        data.setClasses(jsonChildNode1.getString("Class"));
                        dataArrayList.add(data);
                    }
                    loginModel.setGetGetclassDetailsArrayList(dataArrayList);

                    result.add(loginModel);
                }
            } else {
                //invalid login
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static ArrayList<UserProfileModel> parseUserProfileJson(String responseString) {
        ArrayList<UserProfileModel> result = new ArrayList<>();

        try {
            JSONObject reader = new JSONObject(responseString);
            String data_load_basket = reader.getString("Success");
            UserProfileModel userProfileModel = null;

            if (data_load_basket.toString().equals("True")) {


                JSONArray jsonMainNode = reader.optJSONArray("FinalArray");
                for (int a = 0; a < jsonMainNode.length(); a++) {
                    userProfileModel = new UserProfileModel();
                    JSONObject jsonChildNode = jsonMainNode.getJSONObject(a);
                    userProfileModel.setStaffID(jsonChildNode.getString("StaffID"));
                    userProfileModel.setEmp_Name(jsonChildNode.getString("Emp_Name"));
                    userProfileModel.setEmp_Code(jsonChildNode.getString("Emp_Code"));
                    userProfileModel.setDepratment(jsonChildNode.getString("Depratment"));
                    userProfileModel.setDesignation(jsonChildNode.getString("Designation"));
                    userProfileModel.setEmailID(jsonChildNode.getString("EmailID"));
                    userProfileModel.setMobile(jsonChildNode.getString("Mobile"));
                    userProfileModel.setImage(jsonChildNode.getString("Image"));

                    UserProfileModel.ClassDetail data = null;
                    ArrayList<UserProfileModel.ClassDetail> dataArrayList = new ArrayList<>();
                    JSONArray jsonChildMainNode = jsonChildNode.optJSONArray("ClassDetail");
                    for (int i = 0; i < jsonChildMainNode.length(); i++) {
                        data = userProfileModel.new ClassDetail();
                        JSONObject jsonChildNode1 = jsonChildMainNode.getJSONObject(i);
                        data.setClassID(jsonChildNode1.getString("ClassID"));
                        data.setStandardID(jsonChildNode1.getString("StandardID"));
                        data.setStandard(jsonChildNode1.getString("Standard"));
                        data.setClasses(jsonChildNode1.getString("Class"));
                        dataArrayList.add(data);
                    }
                    userProfileModel.setGetclassDetailsArrayList(dataArrayList);

                    result.add(userProfileModel);
                }
            } else {
                //invalid login
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static ArrayList<StaffAttendanceModel> parseStaffAttendanceJson(String responseString) {
        ArrayList<StaffAttendanceModel> result = new ArrayList<>();

        try {
            JSONObject reader = new JSONObject(responseString);
            String data_load_basket = reader.getString("Success");
            StaffAttendanceModel staffAttendanceModel = new StaffAttendanceModel();

            if (data_load_basket.toString().equals("True")) {
                staffAttendanceModel.setDate(reader.getString("Date"));
                JSONArray jsonMainNode = reader.optJSONArray("FinalArray");
                StaffAttendanceModel.AttendanceDetails attendanceDetails = null;
                ArrayList<StaffAttendanceModel.AttendanceDetails> attendanceDetailses = new ArrayList<>();
                for (int i = 0; i < jsonMainNode.length(); i++) {
                    JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                    attendanceDetails = staffAttendanceModel.new AttendanceDetails();
                    attendanceDetails.setStandardID(jsonChildNode.getString("StandardID"));
                    attendanceDetails.setClassID(jsonChildNode.getString("ClassID"));
                    attendanceDetails.setTotal(jsonChildNode.getString("Total"));
                    attendanceDetails.setTotalAbsent(jsonChildNode.getString("TotalAbsent"));
                    attendanceDetails.setTotalPresent(jsonChildNode.getString("TotalPresent"));
                    attendanceDetails.setTotalLeave(jsonChildNode.getString("TotalLeave"));

                    JSONArray jsonMainNode1 = jsonChildNode.optJSONArray("StudentDetail");
                    StaffAttendanceModel.AttendanceDetails.StudentDetails studentDetailsData = null;
                    ArrayList<StaffAttendanceModel.AttendanceDetails.StudentDetails> studentDetailsDatas = new ArrayList<>();
                    for (int j = 0; j < jsonMainNode1.length(); j++) {
                        JSONObject jsonChildNode1 = jsonMainNode1.getJSONObject(j);
                        studentDetailsData = attendanceDetails.new StudentDetails();
                        studentDetailsData.setStudentID(jsonChildNode1.getString("StudentID"));
                        studentDetailsData.setStudentName(jsonChildNode1.getString("StudentName"));
                        studentDetailsData.setStudentImage(jsonChildNode1.getString("StudentImage"));
                        studentDetailsData.setAttendanceID(jsonChildNode1.getString("AttendanceID"));
                        studentDetailsData.setAttendenceStatus(jsonChildNode1.getString("AttendenceStatus"));
                        studentDetailsData.setComment(jsonChildNode1.getString("Comment"));

                        studentDetailsDatas.add(studentDetailsData);
                    }
                    attendanceDetails.setStudentList(studentDetailsDatas);
                    attendanceDetailses.add(attendanceDetails);
                }
                staffAttendanceModel.setAttendanceList(attendanceDetailses);
                result.add(staffAttendanceModel);
            } else {
                //invalid login
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static ArrayList<InsertAttendanceModel> parseInsertAttendanceJson(String responseString) {
        ArrayList<InsertAttendanceModel> result = new ArrayList<>();

        try {
            JSONObject reader = new JSONObject(responseString);
            String data_load_basket = reader.getString("Success");
            InsertAttendanceModel insertAttendanceModel = null;

            if (data_load_basket.toString().equals("True")) {


                JSONArray jsonMainNode = reader.optJSONArray("FinalArray");
                for (int a = 0; a < jsonMainNode.length(); a++) {
                    insertAttendanceModel = new InsertAttendanceModel();
                    JSONObject jsonChildNode = jsonMainNode.getJSONObject(a);

//                    result.add();
                }
            } else {
                //invalid login
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static ArrayList<TeacherTodayScheduleModel> parseTeacherTodayscheduleJson(String responseString) {
        ArrayList<TeacherTodayScheduleModel> result = new ArrayList<>();

        try {
            JSONObject reader = new JSONObject(responseString);
            String data_load_basket = reader.getString("Success");
            TeacherTodayScheduleModel teacherTodayScheduleModel = null;

            if (data_load_basket.toString().equals("True")) {

                JSONArray jsonMainNode = reader.optJSONArray("FinalArray");
                for (int a = 0; a < jsonMainNode.length(); a++) {
                    teacherTodayScheduleModel = new TeacherTodayScheduleModel();
                    JSONObject jsonChildNode = jsonMainNode.getJSONObject(a);
                    teacherTodayScheduleModel.setLecture(jsonChildNode.getString("StaffID"));
                    teacherTodayScheduleModel.setStandard(jsonChildNode.getString("Emp_Name"));
                    teacherTodayScheduleModel.setClassname(jsonChildNode.getString("Emp_Code"));
                    teacherTodayScheduleModel.setSubject(jsonChildNode.getString("DeviceId"));
                    teacherTodayScheduleModel.setTiming(jsonChildNode.getString("DesignationID"));

                    result.add(teacherTodayScheduleModel);
                }
            } else {
                //invalid login
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }
}
