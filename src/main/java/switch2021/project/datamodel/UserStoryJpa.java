package switch2021.project.datamodel;

import lombok.*;
import switch2021.project.model.valueObject.UserStoryID;

import javax.persistence.*;

@Entity(name = "UserStoryJpa")
//@Table(name = "user_stories")
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

    @OneToOne
//    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private UserStoryJpa parentUserStory;
    private String startDate;
    private String endDate;
    private String  cancelled;

}
