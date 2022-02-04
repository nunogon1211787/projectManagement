package switch2021.project.dto;

import java.util.Objects;

public class ProjectDTO {

    /**
     * Attributes
     **/
    private String code;
    private String projectName;
    private String description;

    private String startDate;
    private String endDate;

    private int numberOfSprints;
    private double budget;
    private int sprintDuration;

    /**
     * Constructor to test (without SINGLETON)
     **/
    public ProjectDTO(String code, String projectName, String description, String startDate, String endDate, int numberOfSprints, double budget, int sprintDuration) {
        this.code = code;
        this.projectName = projectName;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfSprints = numberOfSprints;
        this.budget = budget;
        this.sprintDuration = sprintDuration;
    }

    /**
     * Override
     **/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjectDTO)) return false;
        ProjectDTO projectDTO = (ProjectDTO) o;
        return  Objects.equals(code, projectDTO.code)
                && Objects.equals(projectName, projectDTO.projectName)
                && Objects.equals(description, projectDTO.description)
                && Objects.equals(startDate, projectDTO.startDate)
                && Objects.equals(endDate, projectDTO.endDate)
                && numberOfSprints == projectDTO.numberOfSprints
                && Double.compare(projectDTO.budget, budget) == 0
                && sprintDuration == projectDTO.sprintDuration;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, projectName, description, startDate, endDate, numberOfSprints,
                budget, sprintDuration);
    }
}
