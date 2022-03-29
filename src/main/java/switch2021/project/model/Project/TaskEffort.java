package switch2021.project.model.Project;

import lombok.Getter;
import switch2021.project.immutable.Date;
import switch2021.project.immutable.HoursMinutes;

@Getter
public class TaskEffort {

    /**
     * Attributes
     **/

    private final HoursMinutes effort;
    private final Date effortDate;
    private final String comment;
    private final String attachment;

    /**
     * Constructor (without SINGLETON)
     **/

    public TaskEffort(int effortHours, int effortMinutes, Date effortDate, String comment, String attachment) {
        this.effortDate = effortDate;
        this.effort = new HoursMinutes(effortHours, effortMinutes);
        this.comment = comment;
        this.attachment = attachment;
    }

}
