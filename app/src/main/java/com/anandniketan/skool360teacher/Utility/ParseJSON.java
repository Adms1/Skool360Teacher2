package com.anandniketan.skool360teacher.Utility;

import com.anandniketan.skool360teacher.Models.InsertAttendanceModel;
import com.anandniketan.skool360teacher.Models.LoginModel;
import com.anandniketan.skool360teacher.Models.StaffAttendanceModel;
import com.anandniketan.skool360teacher.Models.TeacherAssignedSubjectModel;
import com.anandniketan.skool360teacher.Models.TeacherGetAssignStudentSubjectmModel;
import com.anandniketan.skool360teacher.Models.TeacherGetTimetableModel;
import com.anandniketan.skool360teacher.Models.TeacherTodayScheduleModel;
import com.anandniketan.skool360teacher.Models.Test_SyllabusModel;
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
                    teacherTodayScheduleModel.setLecture(jsonChildNode.getString("Lecture"));
                    teacherTodayScheduleModel.setStandard(jsonChildNode.getString("Standard"));
                    teacherTodayScheduleModel.setClassname(jsonChildNode.getString("classname"));
                    teacherTodayScheduleModel.setSubject(jsonChildNode.getString("Subject"));
                    teacherTodayScheduleModel.setTiming(jsonChildNode.getString("Timing"));

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

    public static ArrayList<TeacherAssignedSubjectModel> parseTeacherAssignedSubjectJson(String responseString) {
        ArrayList<TeacherAssignedSubjectModel> result = new ArrayList<>();

        try {
            JSONObject reader = new JSONObject(responseString);
            String data_load_basket = reader.getString("Success");
            TeacherAssignedSubjectModel teacherAssignedSubjectModel = null;

            if (data_load_basket.toString().equals("True")) {

                JSONArray jsonMainNode = reader.optJSONArray("FinalArray");
                for (int a = 0; a < jsonMainNode.length(); a++) {
                    teacherAssignedSubjectModel = new TeacherAssignedSubjectModel();
                    JSONObject jsonChildNode = jsonMainNode.getJSONObject(a);
                    teacherAssignedSubjectModel.setSubject(jsonChildNode.getString("Subject"));
                    teacherAssignedSubjectModel.setStandard(jsonChildNode.getString("Standard"));
                    teacherAssignedSubjectModel.setClassname(jsonChildNode.getString("classname"));

                    result.add(teacherAssignedSubjectModel);
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

    public static ArrayList<TeacherGetAssignStudentSubjectmModel> parseStudentAssignedSubjectJson(String responseString) {
        ArrayList<TeacherGetAssignStudentSubjectmModel> result = new ArrayList<>();

        try {
            JSONObject reader = new JSONObject(responseString);
            String data_load_basket = reader.getString("Success");
            TeacherGetAssignStudentSubjectmModel teacherGetAssignStudentSubjectmModel = null;

            if (data_load_basket.toString().equals("True")) {


                JSONArray jsonMainNode = reader.optJSONArray("FinalArray");
                for (int a = 0; a < jsonMainNode.length(); a++) {
                    teacherGetAssignStudentSubjectmModel = new TeacherGetAssignStudentSubjectmModel();
                    JSONObject jsonChildNode = jsonMainNode.getJSONObject(a);
                    teacherGetAssignStudentSubjectmModel.setStudentID(jsonChildNode.getString("StudentID"));
                    teacherGetAssignStudentSubjectmModel.setStudentName(jsonChildNode.getString("StudentName"));

                    TeacherGetAssignStudentSubjectmModel.StudentSubject data = null;
                    ArrayList<TeacherGetAssignStudentSubjectmModel.StudentSubject> dataArrayList = new ArrayList<>();
                    JSONArray jsonChildMainNode = jsonChildNode.optJSONArray("StudentSubject");
                    for (int i = 0; i < jsonChildMainNode.length(); i++) {
                        data = teacherGetAssignStudentSubjectmModel.new StudentSubject();
                        JSONObject jsonChildNode1 = jsonChildMainNode.getJSONObject(i);
                        data.setSubject(jsonChildNode1.getString("Subject"));
                        data.setSubjectID(jsonChildNode1.getString("SubjectID"));
                        data.setCheckedStatus(jsonChildNode1.getString("CheckedStatus"));
                        dataArrayList.add(data);
                    }
                    teacherGetAssignStudentSubjectmModel.setDataStudentSubjectArray(dataArrayList);

                    result.add(teacherGetAssignStudentSubjectmModel);
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

    public static ArrayList<TeacherGetTimetableModel> parseTeachertTimetableJson(String responseString) {
        ArrayList<TeacherGetTimetableModel> result = new ArrayList<>();

        try {
            JSONObject reader = new JSONObject(responseString);
            String data_load_basket = reader.getString("Success");
            TeacherGetTimetableModel teacherGetTimetableModel = new TeacherGetTimetableModel();

            if (data_load_basket.toString().equals("True")) {
                JSONArray jsonMainNode = reader.optJSONArray("FinalArray");
                TeacherGetTimetableModel.TimeTable timetable = null;
                ArrayList<TeacherGetTimetableModel.TimeTable> timetables = new ArrayList<>();
                for (int i = 0; i < jsonMainNode.length(); i++) {
                    JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                    timetable = teacherGetTimetableModel.new TimeTable();
                    timetable.setDay(jsonChildNode.getString("Day"));

                    JSONArray jsonMainNode1 = jsonChildNode.optJSONArray("Data");
                    TeacherGetTimetableModel.TimeTable.TimeTableData timetableData = null;
                    ArrayList<TeacherGetTimetableModel.TimeTable.TimeTableData> timetablesData = new ArrayList<>();
                    for (int j = 0; j < jsonMainNode1.length(); j++) {
                        JSONObject jsonChildNode1 = jsonMainNode1.getJSONObject(j);
                        timetableData = timetable.new TimeTableData();
                        timetableData.setLecture(jsonChildNode1.getString("Lecture"));
                        timetableData.setSubject(jsonChildNode1.getString("Subject"));
                        timetableData.setStandardClass(jsonChildNode1.getString("StandardClass"));
                        timetableData.setProxyStatus(jsonChildNode1.getString("ProxyStatus"));

                        timetablesData.add(timetableData);
                    }
                    timetable.setGetTimeTableData(timetablesData);
                    timetables.add(timetable);
                }
                teacherGetTimetableModel.setGetTimeTable(timetables);
                result.add(teacherGetTimetableModel);
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

    public static ArrayList<Test_SyllabusModel> parseTestSyllabusJson(String responseString) {
        ArrayList<Test_SyllabusModel> result = new ArrayList<>();

        try {
            JSONObject reader = new JSONObject(responseString);
            String data_load_basket = reader.getString("Success");
            Test_SyllabusModel test_syllabusModel = null;

            if (data_load_basket.toString().equals("True")) {

                JSONArray jsonMainNode = reader.optJSONArray("FinalArray");
                for (int a = 0; a < jsonMainNode.length(); a++) {
                    test_syllabusModel = new Test_SyllabusModel();
                    JSONObject jsonChildNode = jsonMainNode.getJSONObject(a);
                    test_syllabusModel.setTestID(jsonChildNode.getString("TestID"));
                    test_syllabusModel.setTestName(jsonChildNode.getString("TestName"));
                    test_syllabusModel.setTestDate(jsonChildNode.getString("TestDate"));
                    test_syllabusModel.setSubject(jsonChildNode.getString("Subject"));
                    test_syllabusModel.setStandardClass(jsonChildNode.getString("StandardClass"));


                    Test_SyllabusModel.TestSyllabus data = null;
                    ArrayList<Test_SyllabusModel.TestSyllabus> dataArrayList = new ArrayList<>();
                    JSONArray jsonChildMainNode = jsonChildNode.optJSONArray("TestSyllabus");
                    for (int i = 0; i < jsonChildMainNode.length(); i++) {
                        data = test_syllabusModel.new TestSyllabus();
                        JSONObject jsonChildNode1 = jsonChildMainNode.getJSONObject(i);
                        data.setSyllabus(jsonChildNode1.getString("Syllabus"));

                        dataArrayList.add(data);
                    }
                    test_syllabusModel.setGetSyllabusData(dataArrayList);

                    result.add(test_syllabusModel);
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
