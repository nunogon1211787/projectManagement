package switch2021.project.model.UserStory;

import lombok.*;
import switch2021.project.model.valueObject.Description;
import switch2021.project.model.valueObject.ProjectCode;

import java.time.LocalDate;


@EqualsAndHashCode
@Setter
@Getter
public class UserStory  {

    /**
     * Attributes
     **/

    private UserStoryId userStoryId;
    private ProjectCode projectID;

    private UsTitle title; //The title of a US follows AS <role> I WANT <objective> https://productcoalition.com/anatomy-of-a-great-user-story-f56fb1b63e38
    private UsPriority priority;
    private Description description;
    private UserStory parentUserStory;
    private UsHour timeEstimate;

    private LocalDate usStartDate; //US começou a ser trabalhada num sprint - conseguimos apurar a "antiguidade/pendencia"
    private LocalDate usEndDate; //Significa que está em "done"
    private LocalDate usCancelled; //Significa que está "cancelled"


    /**
     * Constructor User Story
     **/
    public UserStory(String userStoryId, String title, int priority, String description, int timeEstimateInHours) {
        this.userStoryId = new UserStoryId(userStoryId);
        this.title = new UsTitle(title);
        this.description = new Description(description);
        this.priority = new UsPriority(priority);
        this.timeEstimate = new UsHour(timeEstimateInHours);
    }
    public UserStory(String title, int priority, String description) {
        this.title = new UsTitle(title);
        this.description = new Description(description);
        this.priority = new UsPriority(priority);
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
     * Methods set
     */
    public boolean setPriority(int priority) {
        this.priority = new UsPriority(priority);
        return true;
    }

    public void setDescription(String description) {
        this.description = new Description(description);
    }

    /**
     * Methods has
     */

    public boolean hasCode(UserStoryId idUserStory) {
        return this.userStoryId == idUserStory;
    }

    public boolean hasProjectID(ProjectCode projectID) {
        return this.projectID == projectID;
    }
}


