package com.anandniketan.anandniketanskool360shilajTeacher.Models.AllAttendance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admsandroid on 11/15/2017.
 */

public class GetConsistentAbModel {
    @SerializedName("Success")
    @Expose
    private String success;
    @SerializedName("FinalArray")
    @Expose
    private List<FinalArray_ConsistentAb> finalArray = null;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<FinalArray_ConsistentAb> getFinalArray() {
        return finalArray;
    }

    public void setFinalArray(List<FinalArray_ConsistentAb> finalArray) {
        this.finalArray = finalArray;
    }

}
