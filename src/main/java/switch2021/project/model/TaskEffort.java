package switch2021.project.model;

import lombok.Getter;
import switch2021.project.Immutables.HoursMinutes;

import java.time.LocalDate;

@Getter
public class TaskEffort {

    /**
     * Attributes
     **/

    private final HoursMinutes effort;
    private LocalDate effortDate;
    private final String comment;
    private final String attachment;

    /**
     * Constructor (without SINGLETON)
     **/

    public TaskEffort(int effortHours, int effortMinutes, LocalDate effortDate, String comment, String attachment) {
        checkWorkDateRules(effortDate);
        this.effort = new HoursMinutes(effortHours, effortMinutes);
        this.comment = comment;
        this.attachment = attachment;
    }

    /**
     * Methods
     **/

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
