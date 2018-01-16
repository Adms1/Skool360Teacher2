package com.anandniketan.anandniketanskool360shilajTeacher.Utility;

import com.anandniketan.anandniketanskool360shilajTeacher.Models.LessonPlanModel;
import com.anandniketan.anandniketanskool360shilajTeacher.Models.LoginModel;
import com.anandniketan.anandniketanskool360shilajTeacher.Models.TeacherAssignedSubjectModel;
import com.anandniketan.anandniketanskool360shilajTeacher.Models.TeacherGetTestNameModel;
import com.anandniketan.anandniketanskool360shilajTeacher.Models.TeacherGetTimetableModel;
import com.anandniketan.anandniketanskool360shilajTeacher.Models.TeacherInsertTestDetailModel;
import com.anandniketan.anandniketanskool360shilajTeacher.Models.TeacherTodayScheduleModel;
import com.anandniketan.anandniketanskool360shilajTeacher.Models.Test_SyllabusModel;
import com.anandniketan.anandniketanskool360shilajTeacher.Models.UserProfileModel;

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
                    loginModel.setType(jsonChildNode.getString("Type"));

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
                    teacherAssignedSubjectModel.setSubjectID(jsonChildNode.getString("SubjectID"));
                    teacherAssignedSubjectModel.setClassID(jsonChildNode.getString("ClassID"));
                    teacherAssignedSubjectModel.setStandardID(jsonChildNode.getString("StandardID"));


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
                        timetableData.setTimetableID(jsonChildNode1.getString("TimetableID"));

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
                    test_syllabusModel.setTestID(jsonChildNode.getString("TestID"));
                    test_syllabusModel.setTSMasterID(jsonChildNode.getString("TSMasterID"));
                    test_syllabusModel.setSectionID(jsonChildNode.getString("SectionID"));
                    test_syllabusModel.setSubjectID(jsonChildNode.getString("SubjectID"));

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

    public static ArrayList<TeacherInsertTestDetailModel> parseTeacherInsertTestDetailJson(String responseString) {
        ArrayList<TeacherInsertTestDetailModel> result = new ArrayList<>();

        try {
            JSONObject reader = new JSONObject(responseString);
            String data_load_basket = reader.getString("Success");

            if (data_load_basket.toString().equals("True")) {

                JSONArray jsonMainNode = reader.optJSONArray("FinalArray");
            } else {
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static ArrayList<TeacherGetTestNameModel> parseTeacherGetTestNameJson(String responseString) {
        ArrayList<TeacherGetTestNameModel> result = new ArrayList<>();

        try {
            JSONObject reader = new JSONObject(responseString);
            String data_load_basket = reader.getString("Success");
            TeacherGetTestNameModel teacherGetTestNameModel = null;

            if (data_load_basket.toString().equals("True")) {

                JSONArray jsonMainNode = reader.optJSONArray("FinalArray");
                for (int a = 0; a < jsonMainNode.length(); a++) {
                    teacherGetTestNameModel = new TeacherGetTestNameModel();
                    JSONObject jsonChildNode = jsonMainNode.getJSONObject(a);
                    teacherGetTestNameModel.setTestID(jsonChildNode.getString("TestID"));
                    teacherGetTestNameModel.setTestName(jsonChildNode.getString("TestName"));

                    result.add(teacherGetTestNameModel);
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

//    public static ArrayList<HomeworkModel> parseTeacherHomeworkJson(String responseString) {
//        ArrayList<HomeworkModel> result = new ArrayList<>();
//
//        try {
//            JSONObject reader = new JSONObject(responseString);
//            String data_load_basket = reader.getString("Success");
//            HomeworkModel homeworkModel = new HomeworkModel();
//            if (data_load_basket.toString().equals("True")) {
//                homeworkModel.setFromDate(reader.getString("FromDate"));
//                homeworkModel.setToDate(reader.getString("ToDate"));
//
//                JSONArray jsonMainNode = reader.optJSONArray("FinalArray");
//
//                HomeworkModel.HomeworkData homeWorkData = null;
//                ArrayList<HomeworkModel.HomeworkData> homeWorkDatas = new ArrayList<>();
//
//                for (int j = 0; j < jsonMainNode.length(); j++) {
//                    JSONObject jsonChildNode = jsonMainNode.getJSONObject(j);
//                    homeWorkData = homeworkModel.new HomeworkData();
//                    homeWorkData.setDate(jsonChildNode.getString("Date"));
//                    homeWorkData.setStandard(jsonChildNode.getString("Standard"));
//                    homeWorkData.setClassName(jsonChildNode.getString("ClassName"));
//                    homeWorkData.setSubject(jsonChildNode.getString("Subject"));
//                    homeWorkData.setHomeWork(jsonChildNode.getString("HomeWork"));
//                    homeWorkData.setChapterName(jsonChildNode.getString("ChapterName"));
//                    homeWorkData.setObjective(jsonChildNode.getString("Objective"));
//                    homeWorkData.setAssessmentQue(jsonChildNode.getString("AssessmentQue"));
//                    homeWorkData.setStandardID(jsonChildNode.getString("StandardID"));
//                    homeWorkData.setClassID(jsonChildNode.getString("ClassID"));
//                    homeWorkData.setTermID(jsonChildNode.getString("TermID"));
//                    homeWorkData.setSubjectID(jsonChildNode.getString("SubjectID"));
//                    homeWorkData.setFont(jsonChildNode.getString("Font"));
//
//                    homeWorkDatas.add(homeWorkData);
//                }
//                homeworkModel.setGethomeworkdata(homeWorkDatas);
//                result.add(homeworkModel);
//
//
//            } else {
//                //invalid login
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return result;
//    }

    public static ArrayList<LessonPlanModel> parseTeacherLessonPlanScheduleJson(String responseString) {
        ArrayList<LessonPlanModel> result = new ArrayList<>();

        try {
            JSONObject reader = new JSONObject(responseString);
            String data_load_basket = reader.getString("Success");
            LessonPlanModel lessonPlanModel = new LessonPlanModel();
            if (data_load_basket.toString().equals("True")) {
                lessonPlanModel.setFromDate(reader.getString("FromDate"));
                lessonPlanModel.setToDate(reader.getString("ToDate"));

                JSONArray jsonMainNode = reader.optJSONArray("FinalArray");

                LessonPlanModel.LessonplanData lessonplanData = null;
                ArrayList<LessonPlanModel.LessonplanData> lessonplanDatas = new ArrayList<>();

                for (int j = 0; j < jsonMainNode.length(); j++) {
                    JSONObject jsonChildNode = jsonMainNode.getJSONObject(j);
                    lessonplanData = lessonPlanModel.new LessonplanData();
                    lessonplanData.setDate(jsonChildNode.getString("Date"));
                    lessonplanData.setStandard(jsonChildNode.getString("Standard"));
                    lessonplanData.setClassName(jsonChildNode.getString("ClassName"));
                    lessonplanData.setSubject(jsonChildNode.getString("Subject"));
                    lessonplanData.setHomeWork(jsonChildNode.getString("HomeWork"));
                    lessonplanData.setChapterName(jsonChildNode.getString("ChapterName"));
                    lessonplanData.setObjective(jsonChildNode.getString("Objective"));
                    lessonplanData.setAssessmentQue(jsonChildNode.getString("AssessmentQue"));
                    lessonplanData.setFont(jsonChildNode.getString("Font"));

                    lessonplanDatas.add(lessonplanData);
                }
                lessonPlanModel.setGethomeworkdata(lessonplanDatas);
                result.add(lessonPlanModel);


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
