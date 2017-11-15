package com.anandniketan.anandniketanskool360shilajTeacher.Models.AllAttendance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admsandroid on 11/14/2017.
 */

public class GetAttendenceData_AllModel {
    @SerializedName("Success")
    @Expose
    private String success;
    @SerializedName("Date")
    @Expose
    private String date;
    @SerializedName("FinalArray")
    @Expose
    private List<FinalArray_All_Attendance> finalArray = null;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<FinalArray_All_Attendance> getFinalArray() {
        return finalArray;
    }

    public void setFinalArray(List<FinalArray_All_Attendance> finalArray) {
        this.finalArray = finalArray;
    }

}
