package com.anandniketan.skool360teacher.Models;

import java.util.ArrayList;

/**
 * Created by admsandroid on 9/18/2017.
 */

public class StaffAttendanceModel {

    private String Date;
    private ArrayList<AttendanceDetails> attendanceList;

    public StaffAttendanceModel() {
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public ArrayList<AttendanceDetails> getAttendanceList() {
        return attendanceList;
    }

    public void setAttendanceList(ArrayList<AttendanceDetails> attendanceList) {
        this.attendanceList = attendanceList;
    }

    public class AttendanceDetails {
        private String StandardID;
        private String ClassID;
        private String Total;
        private String TotalPresent;
        private String TotalAbsent;
        private String TotalLeave;

        public AttendanceDetails() {
        }

        private ArrayList<StudentDetails> studentList;

        public String getStandardID() {
            return StandardID;
        }

        public void setStandardID(String standardID) {
            StandardID = standardID;
        }

        public String getClassID() {
            return ClassID;
        }

        public void setClassID(String classID) {
            ClassID = classID;
        }

        public String getTotal() {
            return Total;
        }

        public void setTotal(String total) {
            Total = total;
        }

        public String getTotalPresent() {
            return TotalPresent;
        }

        public void setTotalPresent(String totalPresent) {
            TotalPresent = totalPresent;
        }

        public String getTotalAbsent() {
            return TotalAbsent;
        }

        public void setTotalAbsent(String totalAbsent) {
            TotalAbsent = totalAbsent;
        }

        public String getTotalLeave() {
            return TotalLeave;
        }

        public void setTotalLeave(String totalLeave) {
            TotalLeave = totalLeave;
        }

        public ArrayList<StudentDetails> getStudentList() {
            return studentList;
        }

        public void setStudentList(ArrayList<StudentDetails> studentList) {
            this.studentList = studentList;
        }

        public class StudentDetails {
            private String StudentID;
            private String StudentName;
            private String StudentImage;
            private String AttendanceID;
            private String AttendenceStatus;
            private String Comment;

            public StudentDetails() {
            }


            public String getStudentID() {
                return StudentID;
            }

            public void setStudentID(String studentID) {
                StudentID = studentID;
            }

            public String getStudentName() {
                return StudentName;
            }

            public void setStudentName(String studentName) {
                StudentName = studentName;
            }

            public String getStudentImage() {
                return StudentImage;
            }

            public void setStudentImage(String studentImage) {
                StudentImage = studentImage;
            }

            public String getAttendanceID() {
                return AttendanceID;
            }

            public void setAttendanceID(String attendanceID) {
                AttendanceID = attendanceID;
            }

            public String getAttendenceStatus() {
                return AttendenceStatus;
            }

            public void setAttendenceStatus(String attendenceStatus) {
                AttendenceStatus = attendenceStatus;
            }

            public String getComment() {
                return Comment;
            }

            public void setComment(String comment) {
                Comment = comment;
            }
        }


    }
}
