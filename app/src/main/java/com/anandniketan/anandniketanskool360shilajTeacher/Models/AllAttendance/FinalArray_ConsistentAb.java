package com.anandniketan.anandniketanskool360shilajTeacher.Models.AllAttendance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by admsandroid on 11/15/2017.
 */

public class FinalArray_ConsistentAb {
    @SerializedName("Student")
    @Expose
    private String student;
    @SerializedName("Pk_StudentID")
    @Expose
    private Integer pkStudentID;
    @SerializedName("Standard")
    @Expose
    private String standard;
    @SerializedName("Sms No")
    @Expose
    private String smsNo;
    @SerializedName("Absent From")
    @Expose
    private Integer absentFrom;

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public Integer getPkStudentID() {
        return pkStudentID;
    }

    public void setPkStudentID(Integer pkStudentID) {
        this.pkStudentID = pkStudentID;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getSmsNo() {
        return smsNo;
    }

    public void setSmsNo(String smsNo) {
        this.smsNo = smsNo;
    }

    public Integer getAbsentFrom() {
        return absentFrom;
    }

    public void setAbsentFrom(Integer absentFrom) {
        this.absentFrom = absentFrom;
    }

}
