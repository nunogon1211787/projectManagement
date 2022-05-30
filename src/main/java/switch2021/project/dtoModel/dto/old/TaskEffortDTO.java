package switch2021.project.dtoModel.dto.old;

import lombok.Getter;
import switch2021.project.entities.valueObjects.vos.Date;
import switch2021.project.entities.valueObjects.vos.Hours;
import switch2021.project.entities.valueObjects.vos.Minutes;

@Getter

public class TaskEffortDTO {

    /**
     * Attributes
     **/

    private Hours effortHours;
    private Minutes effortMinutes;
    private Date effortDate;
    private String comment;
    private String attachment;

    /**
     * Constructor to test (without SINGLETON)
     **/

    public TaskEffortDTO(int effortHours, int effortMinutes, Date effortDate, String comment, String attachment) {
        this.effortHours = new Hours(effortHours);
        this.effortMinutes = new Minutes(effortMinutes);
        this.effortDate = effortDate;
        this.comment = comment;
        this.attachment = attachment;
    }

}
