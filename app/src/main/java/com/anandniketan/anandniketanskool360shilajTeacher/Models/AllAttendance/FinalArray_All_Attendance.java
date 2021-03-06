package com.anandniketan.anandniketanskool360shilajTeacher.Models.AllAttendance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admsandroid on 11/14/2017.
 */

public class FinalArray_All_Attendance {
    @SerializedName("StandardID")
    @Expose
    private Integer standardID;
    @SerializedName("ClassID")
    @Expose
    private Integer classID;
    @SerializedName("Total")
    @Expose
    private Integer total;
    @SerializedName("TotalPresent")
    @Expose
    private Integer totalPresent;
    @SerializedName("TotalAbsent")
    @Expose
    private Integer totalAbsent;
    @SerializedName("TotalLeave")
    @Expose
    private Integer totalLeave;
    @SerializedName("TotalOnDuty")
    @Expose
    private Integer totalOnDuty;
    @SerializedName("StudentDetail")
    @Expose
    private List<StudentDetailAll_Attendance> studentDetail = null;

    public Integer getStandardID() {
        return standardID;
    }

    public void setStandardID(Integer standardID) {
        this.standardID = standardID;
    }

    public Integer getClassID() {
        return classID;
    }

    public void setClassID(Integer classID) {
        this.classID = classID;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalPresent() {
        return totalPresent;
    }

    public void setTotalPresent(Integer totalPresent) {
        this.totalPresent = totalPresent;
    }

    public Integer getTotalAbsent() {
        return totalAbsent;
    }

    public void setTotalAbsent(Integer totalAbsent) {
        this.totalAbsent = totalAbsent;
    }

    public Integer getTotalLeave() {
        return totalLeave;
    }

    public void setTotalLeave(Integer totalLeave) {
        this.totalLeave = totalLeave;
    }

    public Integer getTotalOnDuty() {
        return totalOnDuty;
    }

    public void setTotalOnDuty(Integer totalOnDuty) {
        this.totalOnDuty = totalOnDuty;
    }

    public List<StudentDetailAll_Attendance> getStudentDetail() {
        return studentDetail;
    }

    public void setStudentDetail(List<StudentDetailAll_Attendance> studentDetail) {
        this.studentDetail = studentDetail;
    }

}
