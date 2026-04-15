package com.codecademy.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.codecademy.model.Student;
import com.codecademy.model.Course;
import com.codecademy.model.Module;
import com.codecademy.model.Webcast;
import com.codecademy.model.Enrollment;
import com.codecademy.model.Progress;

public class DataInserter {
    public void insert(Object object) throws SQLException {
        String insertQuery;

        switch (object.getClass().getSimpleName()) {
            case "Student":
                insertQuery = "INSERT INTO student (emailAddress, name, birthdate, gender, address, city, country) VALUES (?, ?, ?, ?, ?, ?, ?)";
                break;
            case "Course":
                insertQuery = "INSERT INTO course (courseName, subject, introductionText, level) VALUES (?, ?, ?, ?)";
                break;
            case "Module":
                insertQuery = "INSERT INTO module (title, version, description, contactPersonName, contactPersonEmail, courseID) VALUES (?, ?, ?, ?, ?, ?)";
                break;
            case "Webcast":
                insertQuery = "INSERT INTO webcast (title, description, speakerName, speakerOrganization, duration, publicationDate, url) VALUES (?, ?, ?, ?, ?, ?, ?)";
                break;
            case "Enrollment":
                insertQuery = "INSERT INTO enrollment (studentEmailAddress, courseID, enrollmentDate) VALUES (?, ?, ?)";
                break;
            case "Progress":
                insertQuery = "INSERT INTO progress (enrollmentID, contentItemID, percentageWatched) VALUES (?, ?, ?)";
                break;
            default:
                throw new IllegalArgumentException("Invalid object type: " + object.getClass().getSimpleName());
        }

        try (Connection con = Database.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(insertQuery)) {

            // Set the values based on the object type
            switch (object.getClass().getSimpleName()) {
                case "Student":
                    Student student = (Student) object;
                    preparedStatement.setString(1, student.getEmail());
                    preparedStatement.setString(2, student.getName());
                    preparedStatement.setDate(3, student.getBirthdate());
                    preparedStatement.setString(4, student.getGender());
                    preparedStatement.setString(5, student.getAddress());
                    preparedStatement.setString(6, student.getCity());
                    preparedStatement.setString(7, student.getCountry());
                    break;
                case "Course":
                    Course course = (Course) object;
                    preparedStatement.setString(1, course.getCourseName());
                    preparedStatement.setString(2, course.getSubject());
                    preparedStatement.setString(3, course.getIntroductionText());
                    preparedStatement.setString(4, course.getLevel());
                    break;
                case "Module":
                    Module module = (Module) object;
                    preparedStatement.setString(1, module.getTitle());
                    preparedStatement.setString(2, module.getVersion());
                    preparedStatement.setString(3, module.getDescription());
                    preparedStatement.setString(4, module.getContactPersonName());
                    preparedStatement.setString(5, module.getContactPersonEmail());
                    preparedStatement.setInt(6, module.getCourseID());
                    break;
                case "Webcast":
                    Webcast webcast = (Webcast) object;

                    java.util.Date utilDate = webcast.getPublicationDate();
                    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

                    preparedStatement.setString(1, webcast.getTitle());
                    preparedStatement.setString(2, webcast.getDescription());
                    preparedStatement.setString(3, webcast.getSpeakerName());
                    preparedStatement.setString(4, webcast.getSpeakerOrganization());
                    preparedStatement.setTime(5, webcast.getDuration());
                    preparedStatement.setDate(6,sqlDate);
                    preparedStatement.setString(7, webcast.getUrl());
                    break;
                case "Enrollment":
                    Enrollment enrollment = (Enrollment) object;

                    java.util.Date enrollmentUtilDate = enrollment.getEnrollmentDate();
                    java.sql.Date enrollmentSqlDate = new java.sql.Date(enrollmentUtilDate.getTime());

                    preparedStatement.setString(1, enrollment.getStudentEmailAddress());
                    preparedStatement.setInt(2, enrollment.getCourseID());
                    preparedStatement.setDate(3, enrollmentSqlDate);
                    break;
                case "Progress":
                    Progress progress = (Progress) object;
                    preparedStatement.setInt(1, progress.getEnrollmentID());
                    preparedStatement.setInt(2, progress.getContentItemID());
                    preparedStatement.setDouble(3, progress.getPercentageWatched());
                    break;
                default:
                    throw new IllegalArgumentException("Invalid object type: " + object.getClass().getSimpleName());
            }

            preparedStatement.executeUpdate();
        }
    }
}
