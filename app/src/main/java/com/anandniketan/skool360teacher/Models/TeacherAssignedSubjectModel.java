package com.anandniketan.skool360teacher.Models;

/**
 * Created by admsandroid on 9/25/2017.
 */

public class TeacherAssignedSubjectModel {

    public TeacherAssignedSubjectModel(){}

    private String Standard;
    private String classname;
    private String Subject;

    public String getStandard() {
        return Standard;
    }

    public void setStandard(String standard) {
        Standard = standard;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }
}
