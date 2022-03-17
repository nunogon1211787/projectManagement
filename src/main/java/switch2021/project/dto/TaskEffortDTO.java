package switch2021.project.dto;

import lombok.Getter;
import switch2021.project.immutable.Date;

@Getter

public class TaskEffortDTO {

    /**
     * Attributes
     **/

    private int effortHours;
    private int effortMinutes;
    private Date effortDate;
    private String comment;
    private String attachment;

    /**
     * Constructor to test (without SINGLETON)
     **/

    public TaskEffortDTO(int effortHours, int effortMinutes, Date effortDate, String comment, String attachment) {
        this.effortHours = effortHours;
        this.effortMinutes = effortMinutes;
        this.effortDate = effortDate;
        this.comment = comment;
        this.attachment = attachment;
    }

}
