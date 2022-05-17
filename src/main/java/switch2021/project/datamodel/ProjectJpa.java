package switch2021.project.datamodel;

import lombok.*;
import switch2021.project.model.Project.ProjectStatusEnum;
import switch2021.project.model.Typology.Typology;
import switch2021.project.model.valueObject.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity(name = "projects")
@Table/*(name = "Projects", uniqueConstraints = {@UniqueConstraint(name = "ProjectCodeUnique", columnNames = "id")} )*/
public class ProjectJpa {

    @EmbeddedId
    private ProjectID id;

    private String name;

    private String description;

    private String businessSector;

    private String startDate;

    private int numberOfSprints;

    private int sprintDuration;

    //@Column(name = "Budget", nullable = false)
    private double budget;

    private String endDate;

    private String typology;

    private String customer;

    private String status;

    public ProjectJpa(ProjectID code,
                      Description projectName,
                      Description description,
                      BusinessSector businessSector,
                      LocalDate startDate,
                      SprintDuration sprintDuration,
                      NumberOfSprints numberOfSprints,
                      Budget budget) {


        this.id = code;
        this.name = projectName.getText();
        this.description = description.getText();
        this.businessSector = businessSector.getDescription().getText();
        this.startDate = startDate.toString();
        this.numberOfSprints = numberOfSprints.getNumberOfSprintsVO();
        this.sprintDuration = sprintDuration.getSprintDurationDays();
        this.budget = budget.getBudgetVO();
        this.status  = ProjectStatusEnum.PLANNED.toString();

    }

}
