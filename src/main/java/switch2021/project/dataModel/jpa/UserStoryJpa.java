package switch2021.project.dataModel.jpa;

import lombok.*;
import switch2021.project.entities.valueObjects.vos.UserStoryID;

import javax.persistence.*;

@Entity(name = "UserStoryJpa")
@Table(name = "userStories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserStoryJpa {

    @EmbeddedId
    private UserStoryID id;
    private int priority;
    private String description;
    private double timeEstimate;
    private String parentUserStory;
    private String startDate;
    private String endDate;
    private String  cancelled;
    private String  refined;
}
