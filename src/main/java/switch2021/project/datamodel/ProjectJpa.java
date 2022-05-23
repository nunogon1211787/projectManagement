package switch2021.project.datamodel;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import switch2021.project.model.Project.ProjectIDGeneratorStrategy;
import switch2021.project.model.Project.ProjectStatusEnum;
import switch2021.project.model.Typology.Typology;
import switch2021.project.model.valueObject.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "projects")
@Table/*(name = "Projects", uniqueConstraints = {@UniqueConstraint(name = "ProjectCodeUnique", columnNames = "id")} )*/
public class ProjectJpa {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_trial")
    @GenericGenerator(
            name = "seq_trial",
            strategy = "switch2021.project.model.Project.ProjectIDGeneratorStrategy",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = ProjectIDGeneratorStrategy.SEQUENCE_PARAM, value =
                            "project_seq"),
                    @org.hibernate.annotations.Parameter(name = ProjectIDGeneratorStrategy.VALUE_PREFIX_PARAMETER,
                            value = "Project_"),
                    @org.hibernate.annotations.Parameter(name = ProjectIDGeneratorStrategy.NUMBER_FORMAT_PARAMETER,
                            value = "%d")})
    @Column(name = "project_code")
    private String projectCode;
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


    public ProjectJpa(Description projectName,
                      Description description,
                      BusinessSector businessSector,
                      LocalDate startDate,
                      NumberOfSprints numberOfSprints,
                      SprintDuration sprintDuration,
                      Budget budget
//                      LocalDate endDate
//                      Typology typology,
//                      Customer customer
    ) {


        this.name = projectName.getText();
        this.description = description.getText();
        this.businessSector = businessSector.getDescription().getText();
        this.startDate = startDate.toString();
        this.numberOfSprints = numberOfSprints.getNumberOfSprintsVO();
        this.sprintDuration = sprintDuration.getSprintDurationDays();
        this.budget = budget.getBudgetVO();
//        this.endDate = endDate.toString();
//        this.typology = typology.getId_description().getDescription().getText();
//        this.customer = customer.getCustomerName().getText();
        this.status = ProjectStatusEnum.PLANNED.toString();

    }

}
