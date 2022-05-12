package switch2021.project.repositories.jpa;

import lombok.*;
import switch2021.project.model.valueObject.ProjectID;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity(name = "Projects")
@Table(name = "Projects"/*, uniqueConstraints = {@UniqueConstraint(name = "ProjectCodeUnique", columnNames = "id")}*/ )
public class ProjectJpa {

    @EmbeddedId
    private ProjectID id;

    @Column(name = "Name", nullable = false)
    private String name;
    @Column(name = "Description", nullable = false)
    private String description;
    @Column(name = "B.Sector", nullable = false)
    private String businessSector;
    @Column(name = "Starting Date", nullable = false)
    private String startDate;
    @Column(name = "Nº_Sprints", nullable = false)
    private String numberOfSprints;
    @Column(name = "Sprint_Duration", nullable = false)
    private String sprintDuration;
    @Column(name = "Budget", nullable = false)
    private String budget;

    private String endDate;
    private String typology;
    private String customer;
    private String status;

    public ProjectJpa(ProjectID code, String projectName, String description, String businessSector, String startDate
            , String sprintDuration, String numberOfSprints, String budget) {
        this.id = code;

        this.name = projectName;
        this.description = description;
        this.businessSector = businessSector;
        this.startDate = startDate;
        this.numberOfSprints = numberOfSprints;
        this.sprintDuration = sprintDuration;
        this.budget = budget;

    }


}
