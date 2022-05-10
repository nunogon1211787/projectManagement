package switch2021.project.model.Project;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import switch2021.project.model.Project.ProjectStatusEnum;
import switch2021.project.model.Typology.Typology;
import switch2021.project.model.valueObject.*;
import switch2021.project.utils.Entity;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
public class ProjectReeng {

    /**
     * Class Attributes
     **/
    private ProjectID projectCode;
    private Description projectName;
    private Description description;
    private Typology typology;
    private ProjectStatusEnum projectStatus;
    private Customer customer;
    private BusinessSector businessSector;
    private NumberOfSprints numberOfSprints;
    private Budget budget;
    private SprintDuration sprintDuration;
    private LocalDate startDate;
    private LocalDate endDate;


    public ProjectReeng(Description name, Description description, BusinessSector sector, LocalDate startDate,
                        NumberOfSprints numberOfSprints, SprintDuration sprintDuration, Budget budget) {

        this.projectName = name;
        this.description = description;
        this.businessSector = sector;
        this.startDate = startDate;
        this.numberOfSprints = numberOfSprints;
        this.sprintDuration = sprintDuration;
        this.budget = budget;

    }

    /**
     * Method that checks if given code is this projects code
     * **/

    public boolean hasCode(String code) {
        return this.projectCode.getCode().equalsIgnoreCase(code);
    }

    public boolean isActiveInThisDate(LocalDate date) {

        boolean result = false;

        if(this.startDate.isBefore(date) || this.startDate.isEqual(date)){

            if(this.endDate.isEqual(date) || this.endDate.isAfter(date)){
                result = true;
            }

        }

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectReeng that = (ProjectReeng) o;
        return Objects.equals(projectCode, that.projectCode) && Objects.equals(projectName, that.projectName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectCode, projectName);
    }
}
