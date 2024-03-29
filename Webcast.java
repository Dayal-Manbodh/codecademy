import java.sql.Time;
import java.util.Date;

public class Webcast {
    private int webcastID;
    private String title;
    private String description;
    private String speakerName;
    private String speakerOrganization;
    private Time duration;
    private Date publicationDate;
    private String url;

    // Constructor

    public Webcast(String title, String description, String speakerName, String speakerOrganization, Time duration, Date publicationDate, String url) {
        this.title = title;
        this.description = description;
        this.speakerName = speakerName;
        this.speakerOrganization = speakerOrganization;
        this.duration = duration;
        this.publicationDate = publicationDate;
        this.url = url;
    }

    public Webcast(int webcastID, String title, String description, String speakerName, String speakerOrganization, Time duration, Date publicationDate, String url) {
        this.webcastID = webcastID;
        this.title = title;
        this.description = description;
        this.speakerName = speakerName;
        this.speakerOrganization = speakerOrganization;
        this.duration = duration;
        this.publicationDate = publicationDate;
        this.url = url;
    }

    public Webcast(int webcastID, String Title) {
        this.webcastID = webcastID;
        this.title = title;
    }

    // Getters and Setters
    public int getWebcastID() {
        return webcastID;
    }

    public void setWebcastID(int webcastID) {
        this.webcastID = webcastID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpeakerName() {
        return speakerName;
    }

    public void setSpeakerName(String speakerName) {
        this.speakerName = speakerName;
    }

    public String getSpeakerOrganization() {
        return speakerOrganization;
    }

    public void setSpeakerOrganization(String speakerOrganization) {
        this.speakerOrganization = speakerOrganization;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
