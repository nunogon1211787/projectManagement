package switch2021.project.dto;

import lombok.Getter;

import java.util.Objects;

@Getter
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
    public ProjectDTO(String code, String projectName, String description, String startDate,
                      String endDate, int numberOfSprints, double budget, int sprintDuration) {
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
        ProjectDTO that = (ProjectDTO) o;
        return  this.code.equals(that.code)
                && this.projectName.equals(that.projectName)
                && this.description.equals(that.description)
                && this.startDate.equals(that.startDate)
                && this.endDate == that.endDate
                && this.numberOfSprints == that.numberOfSprints
                && this.budget == that.budget
                && this.sprintDuration == that.sprintDuration;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, projectName, description, startDate, endDate, numberOfSprints,
                budget, sprintDuration);
    }
}
