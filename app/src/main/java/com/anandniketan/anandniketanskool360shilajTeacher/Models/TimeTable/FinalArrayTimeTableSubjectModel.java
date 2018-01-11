package com.anandniketan.anandniketanskool360shilajTeacher.Models.TimeTable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by admsandroid on 1/11/2018.
 */

public class FinalArrayTimeTableSubjectModel {
    @SerializedName("Subject")
    @Expose
    private String subject;
    @SerializedName("SubjectID")
    @Expose
    private Integer subjectID;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(Integer subjectID) {
        this.subjectID = subjectID;
    }

}
