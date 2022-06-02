package switch2021.project.entities.aggregates.UserStory;

import lombok.*;
import switch2021.project.entities.valueObjects.vos.*;

import switch2021.project.utils.Entity;

import java.time.LocalDate;
import java.util.Objects;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserStory implements Entity<UserStory> {

    /**
     * Attributes
     **/
    private UserStoryID userStoryID;
    private UsPriority priority;
    private Description description;
    private UsHour timeEstimate;

    private UserStory parentUserStory;
    private LocalDate usStartDate; //US started to be worked on a sprint - we were able to determine the "age/pending"
    private LocalDate usEndDate; // It means it's "done"
    private LocalDate usCancelled; //It means it's  "cancelled"


    /**
     * Constructor User Story
     **/
    public UserStory(UserStoryID userStoryID, UsPriority priority, Description description, UsHour timeEstimate) {
        this.userStoryID = userStoryID;
        this.priority = priority;
        this.description = description;
        this.timeEstimate = timeEstimate;
    }


    /**
     * Constructor User Story Refined
     **/
    public UserStory(UserStory parentUserStory) {
        this.userStoryID = new UserStoryID(parentUserStory.userStoryID.toString() + " - Refined");
    }


    /**
     * Methods has - used in scrum board
     */
    public boolean hasCode(UserStoryID idUserStory) {
        return this.userStoryID.equals(idUserStory);
    }

    public boolean hasProjectId(String projectId) {
        return this.userStoryID.getProjectID().getCode().equals(projectId);
    }


    /**
     * Override Methods
     */
    @Override
    public boolean sameIdentityAs(UserStory other) {
        return other != null && userStoryID.sameValueAs(other.userStoryID);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserStory)) return false;
        UserStory userStory = (UserStory) o;
        return sameIdentityAs(userStory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userStoryID);
    }
}


