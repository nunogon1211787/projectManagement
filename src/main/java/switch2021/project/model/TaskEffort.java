package switch2021.project.model;

import java.time.LocalDate;

public class TaskEffort {

    private int workHours;
    private int workMinutes;
    private LocalDate workDate;
    private String comment;
    private String attachment;
    private SystemUser user;

    public TaskEffort(int workHours, int workMinutes, LocalDate workDate, String comment, String attachment, SystemUser user) {
        checkWorkTimeRules(workHours, workMinutes);
        checkWorkDateRules(workDate);
        this.workHours = workHours;
        this.workMinutes = workMinutes;
        this.comment = comment;
        this.attachment = attachment;
        this.user = user;
    }

    private void checkWorkTimeRules(double workHours, double workMinutes) {
        if (workHours < 0 || workMinutes < 0 || workHours == 0 && workMinutes == 0 || workHours >= 24 || workMinutes >= 60)
            throw new IllegalArgumentException("Not valid work time values.");
    }

    private void checkWorkDateRules(LocalDate workDate) {
        if (workDate == null || workDate.toString().isEmpty()) {
            this.workDate = LocalDate.now();
        } else if (workDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Invalid workHours value.");
        } else {
            this.workDate = workDate;
        }
    }

    public double getWorkHours() {
        return workHours;
    }

    public int getWorkMinutes() {
        return workMinutes;
    }

    public LocalDate getWorkDate() {
        return workDate;
    }

    public String getComment() {
        return comment;
    }

    public String getAttachment() {
        return attachment;
    }

    public SystemUser getUser() {
        return user;
    }
}
