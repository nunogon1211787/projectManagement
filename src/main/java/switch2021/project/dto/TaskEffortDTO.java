package switch2021.project.dto;

import lombok.Getter;
import switch2021.project.Immutables.Date;
import switch2021.project.Immutables.HoursMinutes;

import java.time.LocalDate;

@Getter

public class TaskEffortDTO {

    /**
     * Attributes
     **/

    private HoursMinutes effort;
    private Date effortDate;
    private String comment;
    private String attachment;

    /**
     * Constructor to test (without SINGLETON)
     **/

    public TaskEffortDTO(int effortHours, int effortMinutes, Date effortDate, String comment, String attachment) {
        this.effort = new HoursMinutes(effortHours, effortMinutes);
        this.effortDate = effortDate;
        this.comment = comment;
        this.attachment = attachment;
    }

}
