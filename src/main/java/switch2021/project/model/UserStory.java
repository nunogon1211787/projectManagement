package switch2021.project.model;

import lombok.Data;
import switch2021.project.stores.TaskList;

import java.util.Objects;

/**
 * ---> Getters e Setters e Override <--- - Project Lombok is a java library that automatically plugs into your editor and build tools, spicing up your java.
 */
@Data

public class UserStory {

    /**
     * ---> Attributes <---
     **/

    private int idUserStory;
    private String name;
    private UserStoryStatus userStoryStatus;
    private int priority;
    private String description;
    private UserStory parentUserStory;
    private int timeEstimate;
    private TaskList tasks;
    private double workDone;

    /**
     * ---> Constructor <---
     **/
    public UserStory(String name, int priority, String description, int timeEstimateInHours) {
        isValidUserStory(name, priority, description);

        this.name = name;
        this.description = description;
        this.userStoryStatus = new UserStoryStatus("To do");
        this.priority = priority;
        this.timeEstimate = timeEstimateInHours;
        this.tasks = new TaskList();
    }

    public UserStory(UserStory userStoryToRefine, UserStoryStatus userStoryStatus, int priority, String description) {
        this.name = userStoryToRefine.getName() + " _Refined";

        isValidUserStory(name, priority, description);

        this.userStoryStatus = userStoryStatus;
        this.priority = priority;
        this.description = description;
        this.parentUserStory = userStoryToRefine;
    }

    public UserStory(String name, UserStoryStatus userStoryStatus, int priority, String description) {
        isValidUserStory(name, priority, description);

        this.name = name;
        this.userStoryStatus = userStoryStatus;
        this.priority = priority;
        this.description = description;
    }

    public int getIdUserStory() {
        return idUserStory;
    }

    public boolean hasCode(long idUserStory) {

        return this.idUserStory == idUserStory;
    }

    /**
     * ---> Set Priority <---
     */

    public boolean setPriority(int priority) {
        if (validatePriority(priority)) {
            this.priority = priority;
            return true;
        }
        return false;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUserStoryStatus(UserStoryStatus userStoryStatus) {
        this.userStoryStatus = userStoryStatus;
    }

    public boolean setUserStoryStatusBoolean(UserStoryStatus userStoryStatus) {
        this.userStoryStatus = userStoryStatus;
        return true;
    }

    /**
     * ---> Set parentUserStory <---
     */

    public void setParentUserStory(UserStory parentUserStory) {
        this.parentUserStory = parentUserStory;
    }

    /**
     * ---> Method to validate entered info by Product Owner <---
     * (Cris US009)
     */
    public boolean isValidUserStory(String name, int priority, String description) {
        //check if priority is invalid
        if (priority < 0 || priority > 5) {
            throw new IllegalArgumentException("Check priority, cannot be < 0 or superior to 5.");
        }
        //check if description is invalid
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be blank.");
        }
        if (description.length() < 5) {
            throw new IllegalArgumentException("Description must be at least 5 characters");
        }
        //check if Name is invalid
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be blank.");
        }
        if (name.length() <= 2) {
            throw new IllegalArgumentException("Name must be at least 3 characters");
        }
        return true;
    }


    public boolean validatePriority(int x) {
        return x >= 0 && x <= 5;
    }

    public boolean updateWorkDone(Task task) {
        this.workDone = task.getHoursSpent();

        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserStory userStory = (UserStory) o;
        return idUserStory == userStory.idUserStory
                && priority == userStory.priority
                && timeEstimate == userStory.timeEstimate
                && Objects.equals(name, userStory.name)
                && Objects.equals(description, userStory.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUserStory, name, userStoryStatus, priority, description, parentUserStory, timeEstimate, tasks);
    }
}


