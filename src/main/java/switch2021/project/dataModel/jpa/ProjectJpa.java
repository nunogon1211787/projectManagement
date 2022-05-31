package switch2021.project.dataModel.jpa;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import switch2021.project.entities.aggregates.Project.ProjectIDGeneratorStrategy;
import switch2021.project.entities.valueObjects.vos.Customer;

import javax.persistence.*;
import java.util.List;

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
            strategy = "switch2021.project.entities.aggregates.Project.ProjectIDGeneratorStrategy",
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
    private double budget;
    private String endDate;
    private String typology;
    private String customer;
    private String status;


}
