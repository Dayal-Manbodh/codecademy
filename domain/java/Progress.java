public class Progress {
    private int progressID;
    private int enrollmentID;
    private int contentItemID;
    private double percentageWatched;

    // Constructor
    public Progress(int progressID, int enrollmentID, int contentItemID, double percentageWatched) {
        this.progressID = progressID;
        this.enrollmentID = enrollmentID;
        this.contentItemID = contentItemID;
        this.percentageWatched = percentageWatched;
    }

    // Getters and Setters
    public int getProgressID() {
        return progressID;
    }

    public void setProgressID(int progressID) {
        this.progressID = progressID;
    }

    public int getEnrollmentID() {
        return enrollmentID;
    }

    public void setEnrollmentID(int enrollmentID) {
        this.enrollmentID = enrollmentID;
    }

    public int getContentItemID() {
        return contentItemID;
    }

    public void setContentItemID(int contentItemID) {
        this.contentItemID = contentItemID;
    }

    public double getPercentageWatched() {
        return percentageWatched;
    }

    public void setPercentageWatched(double percentageWatched) {
        this.percentageWatched = percentageWatched;
    }
}
