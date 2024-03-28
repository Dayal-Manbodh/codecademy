import java.util.Date;

public class Enrollment {
    private int enrollmentID;
    private String studentEmailAddress;
    private int courseID;
    private Date enrollmentDate;

    // Constructor
    public Enrollment(String studentEmailAddress, int courseID, Date enrollmentDate) {
        this.studentEmailAddress = studentEmailAddress;
        this.courseID = courseID;
        this.enrollmentDate = enrollmentDate;
    }

    public Enrollment(int enrollmentID, String studentEmailAddress, int courseID, Date enrollmentDate) {
        this.enrollmentID = enrollmentID;
        this.studentEmailAddress = studentEmailAddress;
        this.courseID = courseID;
        this.enrollmentDate = enrollmentDate;
    }

    // Getters and Setters
    public int getEnrollmentID() {
        return enrollmentID;
    }

    public void setEnrollmentID(int enrollmentID) {
        this.enrollmentID = enrollmentID;
    }

    public String getStudentEmailAddress() {
        return studentEmailAddress;
    }

    public void setStudentEmailAddress(String studentEmailAddress) {
        this.studentEmailAddress = studentEmailAddress;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
}
