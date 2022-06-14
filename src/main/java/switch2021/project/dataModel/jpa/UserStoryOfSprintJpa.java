package switch2021.project.dataModel.jpa;

import lombok.*;
import switch2021.project.datamodel.jpa.SprintJpa;
import switch2021.project.entities.valueObjects.vos.UserStoryID;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity(name = "UserStoryOfSprintJpa")
@NoArgsConstructor
@Table(name = "userStoriesOfSprint")
public class UserStoryOfSprintJpa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "projectID", column = @Column(name = "ProjectID")),
            @AttributeOverride(name = "usTitle", column = @Column(name = "UsTitle"))
    })
    private UserStoryID userStoryId;
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sprintProjId")
    @JoinColumn(name= "sprintName")
    private SprintJpa sprintJpa;

    public UserStoryOfSprintJpa(UserStoryID userStoryId, String status, SprintJpa sprintJpa) {
        this.userStoryId = userStoryId;
        this.status = status;
        this.sprintJpa = sprintJpa;
    }
}
