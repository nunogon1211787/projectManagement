package switch2021.project.model.UserStory;

import lombok.*;
import switch2021.project.model.valueObject.Description;
import switch2021.project.model.valueObject.ProjectID;

import switch2021.project.utils.Entity;

import java.time.LocalDate;
import java.util.Objects;


@Setter
@Getter
public class UserStory implements Entity<UserStory> {

    /**
     * Attributes
     **/
    private UserStoryID userStoryID;
    private UsPriority priority;
    private Description description;
    private UserStory parentUserStory;
    private UsHour timeEstimate;

    private LocalDate usStartDate; //US started to be worked on a sprint - we were able to determine the "age/pending"
    private LocalDate usEndDate; // It means it's "done"
    private LocalDate usCancelled; //It means it's  "cancelled"


    /**
     * Constructor User Story
     **/
    public UserStory(UserStoryID userStoryID) {
        this.userStoryID = userStoryID;
    }

    /**
     * Constructor User Story Refined
     **/
    public UserStory(UserStory userStoryToRefine, int priority, String description) {
//        this.title = new UsTitle(userStoryToRefine.getTitle().getTitleUs() + " _Refined");
        this.priority = new UsPriority(priority);
        this.description = new Description(description);
        this.parentUserStory = userStoryToRefine;
    }

//    /**
//     * Methods set (outside lombok)
//     */
//    public boolean setPriority(int priority) {
//        this.priority = new UsPriority(priority);
//        return true;
//    }

    /**
     * Methods has
     */

    public boolean hasCode(UserStoryID idUserStory) {
        return this.userStoryID == idUserStory;
    }


    @Override
    public boolean sameIdentityAs(UserStory other) {
        return other !=null && userStoryID.sameValueAs(other.userStoryID);
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


