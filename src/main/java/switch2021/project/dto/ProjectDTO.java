package switch2021.project.dto;

import lombok.Getter;
import switch2021.project.model.valueObject.Budget;
import switch2021.project.model.valueObject.NumberOfSprints;

public class ProjectDTO {

    /**
     * Attributes
     **/
    public String code;
    public String projectName;
    public String description;
    public String startDate;
    public String endDate;
    public int numberOfSprints;
    public double budget;

    /**
     * Constructor to test (without SINGLETON)
     **/
    public ProjectDTO() {
    }

}
