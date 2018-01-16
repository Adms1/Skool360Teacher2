package com.anandniketan.anandniketanskool360shilajTeacher.Models.HomeWorkResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by admsandroid on 1/16/2018.
 */

public class FinalArrayHomeWorkDataModel {
    @SerializedName("Date")
    @Expose
    private String date;
    @SerializedName("Standard")
    @Expose
    private String standard;
    @SerializedName("ClassName")
    @Expose
    private String className;
    @SerializedName("Subject")
    @Expose
    private String subject;
    @SerializedName("HomeWork")
    @Expose
    private String homeWork;
    @SerializedName("ChapterName")
    @Expose
    private String chapterName;
    @SerializedName("Objective")
    @Expose
    private String objective;
    @SerializedName("AssessmentQue")
    @Expose
    private String assessmentQue;
    @SerializedName("StandardID")
    @Expose
    private Integer standardID;
    @SerializedName("ClassID")
    @Expose
    private Integer classID;
    @SerializedName("SubjectID")
    @Expose
    private Integer subjectID;
    @SerializedName("TermID")
    @Expose
    private Integer termID;
    @SerializedName("Font")
    @Expose
    private String font;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getHomeWork() {
        return homeWork;
    }

    public void setHomeWork(String homeWork) {
        this.homeWork = homeWork;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getAssessmentQue() {
        return assessmentQue;
    }

    public void setAssessmentQue(String assessmentQue) {
        this.assessmentQue = assessmentQue;
    }

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

    public Integer getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(Integer subjectID) {
        this.subjectID = subjectID;
    }

    public Integer getTermID() {
        return termID;
    }

    public void setTermID(Integer termID) {
        this.termID = termID;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }


}
