import java.util.Date;

public class Module {
    private int moduleID;
    private String title;
    private String version;
    private String description;
    private String contactPersonName;
    private String contactPersonEmail;
    private Double averageProgressPercentage;
    private int courseID;

    // Constructor

    public Module(int moduleID, String title, Double averageProgressPercentage) {
        this.moduleID = moduleID;
        this.title = title;
        this.averageProgressPercentage = averageProgressPercentage;
    }

    public Module(String title, String version, String description, String contactPersonName, String contactPersonEmail) {
        this.title = title;
        this.version = version;
        this.description = description;
        this.contactPersonName = contactPersonName;
        this.contactPersonEmail = contactPersonEmail;
    }

    public Module(int moduleID, String title, String version, String description, String contactPersonName, String contactPersonEmail, int courseID) {
        this.moduleID = moduleID;
        this.title = title;
        this.version = version;
        this.description = description;
        this.contactPersonName = contactPersonName;
        this.contactPersonEmail = contactPersonEmail;
        this.courseID = courseID;
    }

    // Getters and Setters
    public Double getAverageProgressPercentage(){
        return averageProgressPercentage;
    }

    public int getModuleID() {
        return moduleID;
    }

    public void setModuleID(int moduleID) {
        this.moduleID = moduleID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public String getContactPersonEmail() {
        return contactPersonEmail;
    }

    public void setContactPersonEmail(String contactPersonEmail) {
        this.contactPersonEmail = contactPersonEmail;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }
}
