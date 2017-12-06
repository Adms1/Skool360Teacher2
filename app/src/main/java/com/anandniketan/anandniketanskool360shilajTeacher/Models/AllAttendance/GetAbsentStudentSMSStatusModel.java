package com.anandniketan.anandniketanskool360shilajTeacher.Models.AllAttendance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by admsandroid on 11/29/2017.
 */

public class GetAbsentStudentSMSStatusModel {
    @SerializedName("Success")
    @Expose
    private String success;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }


}
