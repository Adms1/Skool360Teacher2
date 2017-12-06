package com.anandniketan.anandniketanskool360shilajTeacher.Models.AllAttendance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admsandroid on 11/28/2017.
 */

public class GetDateWiseAbsentStudentModel {
    @SerializedName("Success")
    @Expose
    private String success;
    @SerializedName("FinalArray")
    @Expose
    private List<FinalArrayAbsentStudentModel> finalArray = new ArrayList<FinalArrayAbsentStudentModel>();

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<FinalArrayAbsentStudentModel> getFinalArray() {
        return finalArray;
    }

    public void setFinalArray(List<FinalArrayAbsentStudentModel> finalArray) {
        this.finalArray = finalArray;
    }

}
