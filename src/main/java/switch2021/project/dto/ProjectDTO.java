package switch2021.project.dto;

import lombok.Getter;
import switch2021.project.model.valueObject.Budget;
import switch2021.project.model.valueObject.NumberOfSprints;

import java.util.Objects;

@Getter
public class ProjectDTO {

    /**
     * Attributes
     **/
    private final String code;
    private final String projectName;
    private String description;
    private String startDate;
    private String endDate;
    private NumberOfSprints numberOfSprints;
    private Budget budget;

    /**
     * Constructor to test (without SINGLETON)
     **/
    public ProjectDTO(String code, String projectName, String startDate, String endDate) {
        this.code = code;
        this.projectName = projectName;
        this.startDate = startDate;
        this.endDate = Objects.requireNonNullElse(endDate, "No end date defined");
    }

    /**
     * Constructor to test (without SINGLETON)
     * Need this information to US018 DTO
     **/
    public ProjectDTO(String code, String projectName, String description) {
        this.code = code;
        this.projectName = projectName;
        this.description = description;
    }
}
