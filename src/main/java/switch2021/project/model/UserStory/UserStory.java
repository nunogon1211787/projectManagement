package switch2021.project.model.UserStory;

import lombok.*;
import switch2021.project.model.valueObject.Description;
import switch2021.project.model.valueObject.ProjectID;

import switch2021.project.utils.Entity;

import java.time.LocalDate;


@EqualsAndHashCode
@Setter
@Getter
public class UserStory implements Entity<UserStory> {

    /**
     * Attributes
     **/
    private UserStoryID userStoryID;
    private ProjectID projectID;

    private UsTitle title; //The title of a US follows AS <role> I WANT <objective> https://productcoalition.com/anatomy-of-a-great-user-story-f56fb1b63e38
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
    public UserStory(String projectID, String userStoryID, String title, int priority, String description, double timeEstimateInHours) {
        this.projectID = new ProjectID(projectID);
        this.userStoryID = new UserStoryID(userStoryID);
        this.title = new UsTitle(title);
        this.description = new Description(description);
        this.priority = new UsPriority(priority);
        this.timeEstimate = new UsHour(timeEstimateInHours);
    }

    /**
     * Constructor User Story Refined
     **/
    public UserStory(UserStory userStoryToRefine, int priority, String description) {
        this.title = new UsTitle(userStoryToRefine.getTitle().getTitleUs() + " _Refined");
        this.priority = new UsPriority(priority);
        this.description = new Description(description);
        this.parentUserStory = userStoryToRefine;
    }

    /**
     * Methods set (outside lombok)
     */
    public boolean setPriority(int priority) {
        this.priority = new UsPriority(priority);
        return true;
    }

    public void setDescription(String description) {
        this.description = new Description(description);
    }

    public void setTimeEstimate(int timeEstimate) {
        this.timeEstimate = new UsHour(timeEstimate);
    }

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
}


