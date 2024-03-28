package domain.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import domain.java.Course;
import domain.java.Student;
import domain.java.Enrollment;
import domain.java.Progress;
import domain.java.Module;
import domain.java.Webcast;

import javafx.collections.ObservableList;

public class DataRetriever<T> {
    private final String tableName;
    private final Class<Object> entityType;

    public DataRetriever(String tableName, Class<Object> entityType) {
        this.tableName = tableName;
        this.entityType = entityType;
    }

    public void retrieveData(ObservableList<Object> data) throws SQLException {
        String query = "SELECT * FROM " + tableName;

        try (Connection con = Database.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                // Verwerk elke rij van het resultaat op basis van het type entiteit
                switch (entityType.getSimpleName()) {
                    case "Student":
                        // Verwerk een rij voor de Student-klasse
                        String email = resultSet.getString("emailAddress");
                        String name = resultSet.getString("name");
                        Date birthdate = resultSet.getDate("birthdate");
                        String gender = resultSet.getString("gender");
                        String address = resultSet.getString("address");
                        String city = resultSet.getString("city");
                        String country = resultSet.getString("country");

                        data.add(new Student(email, name, birthdate, gender, address, city, country));

                        break;
                    case "Course":
                        // Verwerk een rij voor de Course-klasse

                        String courseName = resultSet.getString("courseName");
                        String subject = resultSet.getString("subject");
                        String level = resultSet.getString("level");
                        String introductionText = resultSet.getString("introductionText");

                        data.add(new Course(courseName, subject, level, introductionText));

                        break;
                    case "Module":
                        // Verwerk een rij voor de Module-klasse

                        int moduleID = resultSet.getInt("moduleID");
                        String title = resultSet.getString("title");
                        String version = resultSet.getString("version");
                        String description = resultSet.getString("description");
                        String contactPersonName = resultSet.getString("contactPersonName");
                        String contactPersonEmail = resultSet.getString("contactPersonEmail");
                        int courseID = resultSet.getInt("courseID");

                        data.add(new Module(moduleID, title, version, description, contactPersonName,
                                contactPersonEmail, courseID));

                        break;
                    case "Webcast":
                        // Verwerk een rij voor de Webcast-klasse
                        int webcastID = resultSet.getInt("webcastID");
                        String webcastTitle = resultSet.getString("title");
                        String webcastDescription = resultSet.getString("description");
                        String speakerName = resultSet.getString("speakerName");
                        String speakerOrganization = resultSet.getString("speakerOrganization");
                        Time duration = resultSet.getTime("duration");
                        Date publicationDate = resultSet.getDate("publicationDate");
                        String url = resultSet.getString("url");

                        data.add(new Webcast(webcastID, webcastTitle, webcastDescription, speakerName,
                                speakerOrganization, duration,
                                publicationDate, url));
                        break;
                    case "Progress":
                        // Verwerk een rij voor de Progress-klasse

                        int progressID = resultSet.getInt("progressID");
                        int progressEnrollmentID = resultSet.getInt("enrollmentID");
                        int contentItemID = resultSet.getInt("contentItemID");
                        double percentageWatched = resultSet.getDouble("percentageWatched");

                        data.add(new Progress(progressID, progressEnrollmentID, contentItemID, percentageWatched));

                        break;
                    case "Enrollment":
                        // Verwerk een rij voor de Enrollment-klasse
                        int enrollmentID = resultSet.getInt("enrollmentID");
                        String studentEmailAddress = resultSet.getString("studentEmailAddress");
                        int progressCourseID = resultSet.getInt("courseID");
                        Date enrollmentDate = resultSet.getDate("enrollmentDate");

                        data.add(new Enrollment(enrollmentID, studentEmailAddress, progressCourseID, enrollmentDate));
                        break;
                    default:
                        throw new IllegalArgumentException("Ongeldig entity type: " + entityType.getSimpleName());
                }
            }
        }

        // Andere methoden voor het uitvoeren van databasebewerkingen kunnen hier worden
        // toegevoegd
    }
}
