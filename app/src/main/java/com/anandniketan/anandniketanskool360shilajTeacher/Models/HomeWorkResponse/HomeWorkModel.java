package com.anandniketan.anandniketanskool360shilajTeacher.Models.HomeWorkResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admsandroid on 1/16/2018.
 */

public class HomeWorkModel {
    @SerializedName("Success")
    @Expose
    private String success;
    @SerializedName("FromDate")
    @Expose
    private String fromDate;
    @SerializedName("ToDate")
    @Expose
    private String toDate;
    @SerializedName("FinalArray")
    @Expose
    private List<FinalArrayHomeWorkDataModel> finalArray = new ArrayList<FinalArrayHomeWorkDataModel>();

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public List<FinalArrayHomeWorkDataModel> getFinalArray() {
        return finalArray;
    }

    public void setFinalArray(List<FinalArrayHomeWorkDataModel> finalArray) {
        this.finalArray = finalArray;
    }

}
