package com.anandniketan.skool360teacher.Utility;

import com.anandniketan.skool360teacher.Models.LoginModel;
import com.anandniketan.skool360teacher.Models.UserProfileModel;

import java.util.ArrayList;
import java.util.HashMap;

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
    public static String DOMAIN_LOCAL = "";
    public static String DOMAIN_LIVE = "http://192.168.1.4:8085/MobileApp_Service.asmx/";

    public static String GetStaffLogin = "StaffLogin";
    public static String GetStaffAttendence = "StaffAttendence";
    public static String GetInsertAttendance = "InsertAttendance";
    public static String GetStaffProfile = "StaffProfile";


    public static ArrayList<UserProfileModel.ClassDetail> rows = new ArrayList<UserProfileModel.ClassDetail>();
    public static String stdid;
    public static String clsid;

}
