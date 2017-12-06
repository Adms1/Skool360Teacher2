package com.anandniketan.anandniketanskool360shilajTeacher.Models.AllAttendance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by admsandroid on 11/28/2017.
 */

public class FinalArrayAbsentStudentModel  {
    @SerializedName("StudentID")
    @Expose
    private Integer studentID;
    @SerializedName("StudentName")
    @Expose
    private String studentName;
    @SerializedName("GRNO")
    @Expose
    private String gRNO;
    @SerializedName("Standard")
    @Expose
    private String standard;
    @SerializedName("Section")
    @Expose
    private String section;
    @SerializedName("SmsNo")
    @Expose
    private String smsNo;
    @SerializedName("CheckboxStatus")
    @Expose
    private String CheckboxStatus;

    public String getCheckboxStatus() {
        return CheckboxStatus;
    }

    public void setCheckboxStatus(String checkboxStatus) {
        CheckboxStatus = checkboxStatus;
    }

    public Integer getStudentID() {
        return studentID;
    }

    public void setStudentID(Integer studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getGRNO() {
        return gRNO;
    }

    public void setGRNO(String gRNO) {
        this.gRNO = gRNO;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSmsNo() {
        return smsNo;
    }

    public void setSmsNo(String smsNo) {
        this.smsNo = smsNo;
    }

}
