package switch2021.project.model.Project;

import lombok.Getter;
import lombok.Setter;
import switch2021.project.valueObject.Description;
import switch2021.project.model.UserStoryStatus.UserStoryStatus;
import switch2021.project.stores.TaskList;

import java.util.Objects;

/**
 * ---> Getters e Setters e Override <--- - Project Lombok is a java library that automatically plugs into your editor and build tools, spicing up your java.
 */
@Setter
@Getter

public class UserStory {

    /**
     * ---> Attributes <---
     **/

    private int idUserStory;
    private String title; //The title of a US follows AS <role> I WANT <objective> FOR <motivation> https://productcoalition.com/anatomy-of-a-great-user-story-f56fb1b63e38
    private UserStoryStatus userStoryStatus;
    private int priority;
    private Description description;
    private UserStory parentUserStory;
    private int timeEstimate;
    private TaskList tasks;
    private double workDone;

    /**
     * ---> Constructor <---
     **/
    public UserStory(String title, int priority, String description, int timeEstimateInHours) {
        isValidUserStory(title, priority);

        this.title = title;
        this.description = new Description(description);
        this.userStoryStatus = new UserStoryStatus("To do");
        this.priority = priority;
        this.timeEstimate = timeEstimateInHours;
        this.tasks = new TaskList();
    }

    public UserStory(UserStory userStoryToRefine, UserStoryStatus userStoryStatus, int priority, String description) {
        this.title = userStoryToRefine.getTitle() + " _Refined";

        isValidUserStory(title, priority);

        this.userStoryStatus = userStoryStatus;
        this.priority = priority;
        this.description = new Description(description);
        this.parentUserStory = userStoryToRefine;
    }

    public UserStory(String title, UserStoryStatus userStoryStatus, int priority, String description) {
        isValidUserStory(title, priority);

        this.title = title;
        this.userStoryStatus = userStoryStatus;
        this.priority = priority;
        this.description = new Description(description);
    }

    /**
     * ---> Methods set <---
     */

    public boolean setPriority(int priority) {
        if (validatePriority(priority)) {
            this.priority = priority;
            return true;
        }
        return false;
    }

    public void setDescription(String description) {
        this.description = new Description(description);
    }

    public boolean setUserStoryStatusBoolean(UserStoryStatus userStoryStatus) {
        this.userStoryStatus = userStoryStatus;
        return true;
    }

    /**
     * ---> Method to validate entered info by Product Owner <---
     *
     */
    public void isValidUserStory(String title, int priority) {
        //check if priority is invalid
        if (priority < 0 || priority > 5) {
            throw new IllegalArgumentException("Check priority, cannot be < 0 or superior to 5.");
        }
        //check if title is invalid
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be blank.");
        }
        if (title.length() <= 2) {
            throw new IllegalArgumentException("Name must be at least 3 characters");
        }
    }

    public boolean validatePriority(int x) {
        return x >= 0 && x <= 5;
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
        return idUserStory == userStory.idUserStory
                && priority == userStory.priority
                && timeEstimate == userStory.timeEstimate
                && Objects.equals(title, userStory.title)
                && Objects.equals(description, userStory.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUserStory, title, userStoryStatus, priority, description, parentUserStory, timeEstimate, tasks);
    }
}


