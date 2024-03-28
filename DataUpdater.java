import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Time;

public class DataUpdater {
    public void update(Object object) throws SQLException {
        String updateQuery;

        switch (object.getClass().getSimpleName()) {
            case "Student":
                updateQuery = "UPDATE student SET name = ?, birthdate = ?, gender = ?, address = ?, city = ?, country = ? WHERE emailAddress = ?";
                break;
            case "Course":
                updateQuery = "UPDATE course SET courseName = ?, subject = ?, introductionText = ?, level = ? WHERE courseID = ?";
                break;
            case "Module":
                updateQuery = "UPDATE module SET title = ?, version = ?, description = ?, contactPersonName = ?, contactPersonEmail = ?, courseID = ? WHERE moduleID = ?";
                break;
            case "Webcast":
                updateQuery = "UPDATE webcast SET title = ?, description = ?, speakerName = ?, speakerOrganization = ?, duration = ?, publicationDate = ?, url = ? WHERE webcastID = ?";
                break;
            case "Enrollment":
                updateQuery = "UPDATE enrollment SET studentEmailAddress = ?, courseID = ?, enrollmentDate = ? WHERE enrollmentID = ?";
                break;
            case "Progress":
                updateQuery = "UPDATE progress SET enrollmentID = ?, contentItemID = ?, percentageWatched = ? WHERE progressID = ?";
                break;
            default:
                throw new IllegalArgumentException("Invalid object type: " + object.getClass().getSimpleName());
        }

        try (Connection con = Database.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(updateQuery)) {

            switch (object.getClass().getSimpleName()) {
                case "Student":
                    Student student = (Student) object;
                    preparedStatement.setString(1, student.getName());
                    preparedStatement.setDate(2, student.getBirthdate());
                    preparedStatement.setString(3, student.getGender());
                    preparedStatement.setString(4, student.getAddress());
                    preparedStatement.setString(5, student.getCity());
                    preparedStatement.setString(6, student.getCountry());
                    preparedStatement.setString(7, student.getEmail());
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
                    preparedStatement.setInt(7, module.getModuleID());
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
                    preparedStatement.setInt(8, webcast.getWebcastID());
                    break;
                case "Enrollment":
                    Enrollment enrollment = (Enrollment) object;

                    java.util.Date enrollmentUtilDate = enrollment.getEnrollmentDate();
                    java.sql.Date enrollmentSqlDate = new java.sql.Date(enrollmentUtilDate.getTime());

                    preparedStatement.setString(1, enrollment.getStudentEmailAddress());
                    preparedStatement.setInt(2, enrollment.getCourseID());
                    preparedStatement.setDate(3, enrollmentSqlDate);
                    preparedStatement.setInt(4, enrollment.getEnrollmentID());
                    break;
                case "Progress":
                    Progress progress = (Progress) object;
                    preparedStatement.setInt(1, progress.getEnrollmentID());
                    preparedStatement.setInt(2, progress.getContentItemID());
                    preparedStatement.setDouble(3, progress.getPercentageWatched());
                    preparedStatement.setInt(4, progress.getProgressID());
                    break;
                default:
                    throw new IllegalArgumentException("Invalid object type: " + object.getClass().getSimpleName());
            }

            preparedStatement.executeUpdate();
        }
    }
}
