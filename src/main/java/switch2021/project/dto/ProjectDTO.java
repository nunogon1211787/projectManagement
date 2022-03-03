package switch2021.project.dto;

import lombok.Getter;

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

    /**
     * Constructor to test (without SINGLETON)
     **/
    public ProjectDTO(String code, String projectName, String startDate, String endDate) {
        this.code = code;
        this.projectName = projectName;
        this.startDate = startDate;
        this.endDate = endDate;
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
