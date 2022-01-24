package switch2021.project.model;

import lombok.Data;


import java.util.concurrent.atomic.AtomicInteger;

/**
 * ---> Getters e Setters e Override <--- - Project Lombok is a java library that automatically plugs into your editor and build tools, spicing up your java.
 */
@Data


public class UserStory {

    /**
     * ---> Attributes <---
     **/

    private UserStoryStatus userStoryStatus;
    private int priority;
    private String description;
    private int id_UserStory;
    private UserStory ParentUserStory;


    /**
     * ---> Constructor <---
     **/


    public UserStory(int priority, String description) {
        this(new UserStoryStatus("To do") , priority, description);
    }

    public UserStory(UserStoryStatus userStoryStatus, int priority, String description) {
        isValidUserStory(userStoryStatus, priority, description);

        this.userStoryStatus = userStoryStatus;
        this.priority = priority;
        this.description = description;
    }

    public UserStory(UserStory userStoryToRefine, UserStoryStatus userStoryStatus, int priority, String description){
        isValidUserStory(userStoryStatus, priority, description);
        this.userStoryStatus = userStoryStatus;
        this.priority = priority;
        this.description = description;
        this.ParentUserStory = userStoryToRefine;
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

    /**
     * ---> Set parentUserStory <---
     */

    public void setParentUserStory(UserStory parentUserStory) {
        ParentUserStory = parentUserStory;
    }

    /**
     * ---> Method to validate entered info by Product Owner <---
     * (Cris US009)
     */
    private boolean isValidUserStory(UserStoryStatus userStoryStatus, int priority, String description) {
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
        return true;
    }


    private boolean validatePriority(int x) {
        if (x < 0 || x > 5)
            return false;
        return true;
    }

}


