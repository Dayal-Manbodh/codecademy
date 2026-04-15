package com.codecademy.model;

public class Course {
    private String courseName;
    private String subject;
    private String introductionText;
    private String level;
    private int courseID;

    public Course(String courseName, String subject, String introductionText, String level) {
        this.courseName = courseName;
        this.subject = subject;
        this.introductionText = introductionText;
        this.level = level;
    }

    public Course(String courseName, String subject, String introductionText, String level, int courseID) {
        this.courseName = courseName;
        this.subject = subject;
        this.introductionText = introductionText;
        this.level = level;
        this.courseID = courseID;
    }

    // Getters
    public String getCourseName() {
        return courseName;
    }

    public int getCourseID(){
        return courseID;
    }

    public String getSubject() {
        return subject;
    }

    public String getIntroductionText() {
        return introductionText;
    }

    public String getLevel() {
        return level;
    }

    // Setters
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setIntroductionText(String introductionText) {
        this.introductionText = introductionText;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
