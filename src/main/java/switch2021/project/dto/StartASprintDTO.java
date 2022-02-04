package switch2021.project.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
public class StartASprintDTO {

    /**
     * Attributes
     **/

    private String projectCode;
    private int sprintID;
    private LocalDate startDate;


    /**
     * Constructor to test (without SINGLETON)
     **/

    public StartASprintDTO (String projectCode, int sprintID, LocalDate startDate) {

        this.projectCode = projectCode;
        this.sprintID = sprintID;
        this.startDate = startDate;
    }
}
