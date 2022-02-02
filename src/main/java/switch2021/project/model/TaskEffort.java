package switch2021.project.model;

import java.time.LocalDate;

public class TaskEffort {

    private int effortHours;
    private int effortMinutes;
    private LocalDate effortDate;
    private String comment;
    private String attachment;
    private Resource effortResponsible;

    public TaskEffort(int effortHours, int effortMinutes, LocalDate effortDate, String comment, String attachment, Resource effortResponsible) {
        this.effortResponsible = effortResponsible;
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

    private void checkWorkDateRules(LocalDate workDate) {
        if (workDate == null || workDate.toString().isEmpty()) {
            this.effortDate = LocalDate.now();
        } else if (workDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Invalid workHours value.");
        } else if (workDate.isAfter(this.effortResponsible.getEndDate()) || workDate.isBefore(this.effortResponsible.getStartDate())) {
            throw new IllegalArgumentException("work date not match with the resource allocation dates");
        } else {
            this.effortDate = workDate;
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

    public Resource getEffortResponsible() {
        return effortResponsible;
    }

}
