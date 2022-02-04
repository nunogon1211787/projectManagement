package switch2021.project.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter

public class TaskEffortDTO {

    /**
     * Attributes
     **/

    private int effortHours;
    private int effortMinutes;
    private LocalDate effortDate;
    private String comment;
    private String attachment;

    /**
     * Constructor to test (without SINGLETON)
     **/

    public TaskEffortDTO(int effortHours, int effortMinutes, LocalDate effortDate, String comment, String attachment) {
        this.effortHours = effortHours;
        this.effortMinutes = effortMinutes;
        this.effortDate = effortDate;
        this.comment = comment;
        this.attachment = attachment;
    }

}
