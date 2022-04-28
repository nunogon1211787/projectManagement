package switch2021.project.model.Project;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import switch2021.project.model.Project.ProjectStatusEnum;
import switch2021.project.model.Typology.Typology;
import switch2021.project.model.valueObject.*;
import switch2021.project.utils.Entity;
import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
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


    public ProjectReeng(String name, String description, String businessSector, LocalDate startDate,
                        int numberOfSprints, double budget) {

        if(startDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Project can´t start before today's date");
        }

        this.projectName = new Description(name);
        this.description = new Description(description);
        this.businessSector = new BusinessSector(businessSector);
        this.projectStatus = ProjectStatusEnum.PLANNED;
        this.startDate = startDate;
        this.numberOfSprints = new NumberOfSprints(numberOfSprints);
        this.budget = new Budget(budget);

    }

    /**
     * Method that checks if given code is this projects code
     * **/

    public boolean hasCode(String code) {
        return this.projectCode.getCode().equalsIgnoreCase(code);
    }

}
