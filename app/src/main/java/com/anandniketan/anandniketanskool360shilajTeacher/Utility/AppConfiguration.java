package com.anandniketan.anandniketanskool360shilajTeacher.Utility;

import com.anandniketan.anandniketanskool360shilajTeacher.Models.UserProfileModel;

import java.util.ArrayList;

/**
 * Created by Megha on 15-Sep-17.
 */
public class AppConfiguration {

    public enum Domain {
        LIVE, LOCAL
    }

    static Domain domain = Domain.LIVE;//only Change this for changing environment

    public static String getUrl(String methodName) {
        String url = "";
        switch (domain) {
            case LIVE:
                url = DOMAIN_LIVE + methodName;
                break;
            case LOCAL:
                url = DOMAIN_LOCAL + methodName;
                break;
            default:
                break;
        }
        return url;
    }


    //Local
    public static String DOMAIN_LOCAL = "http://192.168.1.22:8085/MobileApp_Service.asmx/";
    public static String DOMAIN_LIVE = "http://192.168.1.22:8085/MobileApp_Service.asmx/"; //use for only office
//    public static String DOMAIN_LIVE = "http://103.8.216.132/MobileApp_Service.asmx/"; //use for client


    //Image Url
    //Local
    public static String DOMAIN_LIVE_IMAGES = "http://192.168.1.22:8085/skool360-Category-Images/Teacher/";
    //Live
//    public static String DOMAIN_LIVE_IMAGES = "http://103.8.216.132/skool360-Category-Images/Teacher/";

    //ICONS URL
    //Local
    public static String DOMAIN_LIVE_ICONS = "http://192.168.1.22:8085/skool360-Design-Icons/Teacher/";
    //Live
//    public static String DOMAIN_LIVE_ICONS = "http://103.8.216.132/skool360-Design-Icons/Teacher/";

    public static String GetStaffLogin = "StaffLogin";
    public static String GetStaffAttendence = "StaffAttendence";
    public static String GetInsertAttendance = "InsertAttendance";
    public static String GetStaffProfile = "StaffProfile";
    public static String GetTeacherTodaySchedule = "TeacherTodaySchedule";
    public static String GetTeacherAssignedSubject = "TeacherAssignedSubject";
    public static String GetTeacherGetAssignStudentSubject = "TeacherGetAssignStudentSubject";
    public static String GetTeacherGetTimetable = "TeacherGetTimetable";
    public static String GetTeacherGetTestSyllabus = "TeacherGetTestSyllabus";
    public static String GetTeacherGetTestMarks = "TeacherGetTestMarks";
    public static String GetTeacherInsertTestDetail = "TeacherInsertTestDetail";
    public static String GetTeacherGetTestNameGradeWise = "TeacherGetTestNameGradeWise";
    public static String GetTeacherLessonPlanScheduledHomework = "TeacherLessonPlanScheduledHomework";
    public static String GetTeacherLessonPlanSchedule = "TeacherLessonPlanSchedule";
    public static String GetTeacherLessonPlan = "TeacherLessonPlan";
    public static String TeacherGetClassSubjectWiseStudent = "TeacherGetClassSubjectWiseStudent";
    public static String PTMTeacherStudentInsertDetail = "PTMTeacherStudentInsertDetail";
    public static String PTMTeacherStudentGetDetail = "PTMTeacherStudentGetDetail";
    public static String PTMDeleteMeeting = "PTMDeleteMeeting";
    public static String TeacherInsertAssignStudentSubject = "TeacherInsertAssignStudentSubject";
    public static String DeviceVersion = "DeviceVersion";
    public static String InsertTimetable = "InsertTimetable";
    public static String DeleteTimetable = "DeleteTimetable";
    public static String TeacherStudentHomeworkStatus = "TeacherStudentHomeworkStatus";
    public static String TeacherStudentHomeworkStatusInsertUpdate = "TeacherStudentHomeworkStatusInsertUpdate";
    public static String GetStandardSection = "GetStandardSection";
    public static String GetAttendenceData_All = "GetAttendenceData_All";
    public static String GetConsistentAb = "GetConsistentAb";
    public static String InsertConsistentAbSMS = "InsertConsistentAbSMS";
    public static String GetDateWiseAbsentStudent = "GetDateWiseAbsentStudent";
    public static String SendAbsentStudentSMS = "SendAbsentStudentSMS";
    public static String GetAssignedSubjectForTimeTable = "GetAssignedSubject";

    public static ArrayList<UserProfileModel.ClassDetail> rows = new ArrayList<UserProfileModel.ClassDetail>();
    public static String Logintype;

}
