package switch2021.project.dataModel.jpa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import switch2021.project.entities.valueObjects.vos.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "TaskJpa")
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskJpa {
    @Embedded
    ProjectID projectID;
    @Embedded
    private UsTitle usTitle;
    @Embedded
    private Description sprintName;
    @EmbeddedId
    private Name taskName;
    private String description;
    private String type;
    private double effortEstimate;
    private String startDate;
    private String endDate;
    @Embedded
    private UserID user;
    private String resourceStartDate;
    @ElementCollection
    @CollectionTable(name = "efforts")
    private List<TaskEffort> taskEffortList;
    @ElementCollection
    @CollectionTable(name = "tasks_precedences")
    private List<String> precedenceList;


}
