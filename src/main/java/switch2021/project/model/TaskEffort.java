package switch2021.project.model;

import lombok.Getter;

import java.time.LocalDate;

@Getter

public class TaskEffort {

    private final int effortHours;
    private final int effortMinutes;
    private LocalDate effortDate;
    private final String comment;
    private final String attachment;
    private static final int MIN_HOUR = 0;
    private static final int MAX_HOUR = 24;
    private static final int MIN_MINUTE = 0;
    private static final int MAX_MINUTE = 60;

    public TaskEffort(int effortHours, int effortMinutes, LocalDate effortDate, String comment, String attachment) {
        checkWorkTimeRules(effortHours, effortMinutes);
        checkWorkDateRules(effortDate);
        this.effortHours = effortHours;
        this.effortMinutes = effortMinutes;
        this.comment = comment;
        this.attachment = attachment;
    }

    private void checkWorkTimeRules(double effortHours, double effortMinutes) {
        if (effortHours < MIN_HOUR || effortMinutes < MIN_MINUTE || effortHours == MIN_HOUR && effortMinutes == MIN_MINUTE
                || effortHours > MAX_HOUR || effortMinutes >= MAX_MINUTE)
            throw new IllegalArgumentException("Not valid work time values." + " Minute interval: [" + MIN_MINUTE + " - " + MAX_MINUTE + "]" +
                    " || Hour interval: [" + MIN_HOUR + " - " + MAX_HOUR + "]");
    }

    private void checkWorkDateRules(LocalDate effortDate) {
        if (effortDate == null) {
            this.effortDate = LocalDate.now();
        } else if (effortDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Invalid workHours value.");
        } else {
            this.effortDate = effortDate;
        }
    }
}
