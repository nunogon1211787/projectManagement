package switch2021.project.datamodel;

import lombok.*;
import switch2021.project.model.valueObject.UserStoryID;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity(name = "UserStoryOfSprintJpa")
@NoArgsConstructor
//@ToString
@Table(name = "userStoriesOfSprint")
public class UserStoryOfSprintJpa implements Serializable {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projectId", insertable = false, updatable = false)
    @JoinColumn(name= "text", insertable = false, updatable = false)
    private SprintJpa sprintJpa;
    @Embedded
    private UserStoryID userStoryId;
    private String status;

    public UserStoryOfSprintJpa(UserStoryID userStoryId, String status, SprintJpa sprintJpa) {
        this.userStoryId = userStoryId;
        this.status = status;
        this.sprintJpa = sprintJpa;
    }
}
