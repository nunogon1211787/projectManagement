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
    private long id_UserStory;
    private long id_ParentUserStory;


    /**
     * ---> Constructor <---
     **/

    public UserStory(UserStoryStatus userStoryStatus, int priority, String description) {
        this(ID_GENERATOR.getAndIncrement(), userStoryStatus, priority, description);
    }

    public UserStory(int userStoryID, UserStoryStatus userStoryStatus, int priority, String description) {
        isValidUserStory(userStoryStatus, priority, description);
        this.id_UserStory = userStoryID;
        this.userStoryStatus = userStoryStatus;
        this.priority = priority;
        this.description = description;
        this.id_ParentUserStory = 0;
    }

    /**
     * ---> ID_UserProfile Generator. <---
     */
    private static AtomicInteger ID_GENERATOR = new AtomicInteger(1);


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

    /**
     * ---> Set parentUserStory <---
     */

    public void setId_ParentUserStory(long id_ParentUserStory) {
        this.id_ParentUserStory = id_ParentUserStory;
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


