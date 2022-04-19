package switch2021.project.dto;

import lombok.Getter;
import switch2021.project.model.Sprint.SprintID;

import java.time.LocalDate;

@Getter
public class StartASprintDTO {

    /**
     * Attributes
     **/

    private String projectCode;
    private SprintID sprintID;
    private LocalDate startDate;


    /**
     * Constructor to test (without SINGLETON)
     **/

    public StartASprintDTO (String projectCode, String sprintID, LocalDate startDate) {

        this.projectCode = projectCode;
        this.sprintID = new SprintID(sprintID);
        this.startDate = startDate;
    }
}
