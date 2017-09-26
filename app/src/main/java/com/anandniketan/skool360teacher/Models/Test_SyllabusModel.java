package com.anandniketan.skool360teacher.Models;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by admsandroid on 9/26/2017.
 */

public class Test_SyllabusModel {

    public Test_SyllabusModel() {
    }

    private int Position;
    private String TestName;
    private String StandardClass;
    private String Subject;
    private String TestDate;
    private String TestID;
    private ArrayList<TestSyllabus> getSyllabusData;


    public int getPosition() {
        return Position;
    }

    public void setPosition(int position) {
        Position = position;
    }

    public String getTestName() {
        return TestName;
    }

    public void setTestName(String testName) {
        TestName = testName;
    }

    public String getStandardClass() {
        return StandardClass;
    }

    public void setStandardClass(String standardClass) {
        StandardClass = standardClass;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getTestDate() {
        return TestDate;
    }

    public void setTestDate(String testDate) {
        TestDate = testDate;
    }

    public String getTestID() {
        return TestID;
    }

    public void setTestID(String testID) {
        TestID = testID;
    }

    public ArrayList<TestSyllabus> getGetSyllabusData() {
        return getSyllabusData;
    }

    public void setGetSyllabusData(ArrayList<TestSyllabus> getSyllabusData) {
        this.getSyllabusData = getSyllabusData;
    }

    public class TestSyllabus {

        public TestSyllabus() {
        }

        private String Syllabus;

        public String getSyllabus() {
            return Syllabus;
        }

        public void setSyllabus(String syllabus) {
            Syllabus = syllabus;
        }
    }
}
