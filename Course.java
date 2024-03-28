public class Course {
    private String courseName;
    private String subject;
    private String introductionText;
    private String level;

    public Course(String courseName, String subject, String introductionText, String level) {
        this.courseName = courseName;
        this.subject = subject;
        this.introductionText = introductionText;
        this.level = level;
    }

    // Getters
    public String getCourseName() {
        return courseName;
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
