package switch2021.project.model;

import java.time.LocalDate;

public class TaskEffort {

    private int effortHours;
    private int effortMinutes;
    private LocalDate effortDate;
    private String comment;
    private String attachment;

    public TaskEffort(int effortHours, int effortMinutes, LocalDate effortDate, String comment, String attachment) {
        checkWorkTimeRules(effortHours, effortMinutes);
        checkWorkDateRules(effortDate);
        this.effortHours = effortHours;
        this.effortMinutes = effortMinutes;
        this.comment = comment;
        this.attachment = attachment;
    }

    private void checkWorkTimeRules(double effortHours, double effortMinutes) {
        if (effortHours < 0 || effortMinutes < 0 || effortHours == 0 && effortMinutes == 0 || effortHours >= 24 || effortMinutes >= 60)
            throw new IllegalArgumentException("Not valid work time values.");
    }

    private void checkWorkDateRules(LocalDate effortDate) {
        if (effortDate == null || effortDate.toString().isEmpty()) {
            this.effortDate = LocalDate.now();
        } else if (effortDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Invalid workHours value.");
        } else {
            this.effortDate = effortDate;
        }
    }

    public int getEffortHours() {
        return effortHours;
    }

    public int getEffortMinutes() {
        return effortMinutes;
    }

    public LocalDate getEffortDate() {
        return effortDate;
    }

    public String getComment() {
        return comment;
    }

    public String getAttachment() {
        return attachment;
    }

}
