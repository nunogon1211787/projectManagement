package switch2021.project.dto;

import lombok.Getter;

@Getter
public class ProjectDTO {

    /**
     * Attributes
     **/
    private final String code;
    private final String projectName;
    private final String startDate;
    private final String endDate;

    /**
     * Constructor to test (without SINGLETON)
     **/
    public ProjectDTO(String code, String projectName, String startDate, String endDate) {
        this.code = code;
        this.projectName = projectName;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
