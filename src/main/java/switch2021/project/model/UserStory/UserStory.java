package switch2021.project.model.UserStory;

import lombok.Getter;
import lombok.Setter;
import switch2021.project.model.Task.Task;
import switch2021.project.model.Task.TaskStore;
import switch2021.project.model.valueObject.Description;
import switch2021.project.model.valueObject.UsPriority;
import switch2021.project.model.valueObject.UserStoryStatus;
import switch2021.project.model.valueObject.UsTitle;

import java.util.Objects;

/**
 * ---> Getters e Setters e Override <--- - Project Lombok is a java library
 * that automatically plugs into your editor and build tools, spicing up your java.
 */
@Setter
@Getter
public class UserStory {

    /**
     * Attributes
     **/
    private int idUserStory;
    private UsTitle title; //The title of a US follows AS <role> I WANT <objective> https://productcoalition.com/anatomy-of-a-great-user-story-f56fb1b63e38
    private UserStoryStatus userStoryStatus;
    private UsPriority priority;
    private Description description;
    private UserStory parentUserStory;
    private int timeEstimate;
    private TaskStore tasks;
    private double workDone;


    /**
     * Constructor
     **/
    public UserStory(String title, int priority, String description, int timeEstimateInHours) {
        this.title = new UsTitle(title);
        this.description = new Description(description);
        this.userStoryStatus = new UserStoryStatus("To do", true);
        this.priority = new UsPriority(priority);
        this.timeEstimate = timeEstimateInHours;
        this.tasks = new TaskStore();
    }

    public UserStory(UserStory userStoryToRefine, UserStoryStatus userStoryStatus, int priority, String description) {
        this.title = new UsTitle(userStoryToRefine.getTitle().getTitleUs() + " _Refined");
        this.userStoryStatus = userStoryStatus;
        this.priority = new UsPriority(priority);
        this.description = new Description(description);
        this.parentUserStory = userStoryToRefine;
    }

    public UserStory(String title, UserStoryStatus userStoryStatus, int priority, String description) {
        this.title = new UsTitle(title);
        this.userStoryStatus = userStoryStatus;
        this.priority = new UsPriority(priority);
        this.description = new Description(description);
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

    public boolean setUserStoryStatusBoolean(UserStoryStatus userStoryStatus) {
        this.userStoryStatus = userStoryStatus;
        return true;
    }

    public boolean updateWorkDone(Task task) {
        this.workDone = task.getHoursSpent();

        return true;
    }


    public boolean hasCode(long idUserStory) {
        return this.idUserStory == idUserStory;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserStory userStory = (UserStory) o;
        return idUserStory == userStory.idUserStory && timeEstimate == userStory.timeEstimate &&
               Double.compare(userStory.workDone, workDone) == 0 &&  Objects.equals(title, userStory.title) &&
                Objects.equals(userStoryStatus, userStory.userStoryStatus) && Objects.equals(priority, userStory.priority) &&
                Objects.equals(description, userStory.description) && Objects.equals(parentUserStory, userStory.parentUserStory) &&
                Objects.equals(tasks, userStory.tasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUserStory, title, userStoryStatus, priority, description, parentUserStory, timeEstimate, tasks, workDone);
    }
}


