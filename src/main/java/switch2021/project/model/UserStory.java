package switch2021.project.model;

import lombok.Data;
import switch2021.project.utils.App;


import java.util.concurrent.atomic.AtomicInteger;

/**
 * Getters e Setters e Override - Project Lombok is a java library that automatically plugs into your editor and build tools, spicing up your java.
 */

@Data

public class UserStory {

    /**
     * Attributes
     */

//    private String projectCode;
    private UserStoryStatus userStoryStatus;
    private int priority;
    private String description;
    private int timeEstimate;
    private long id_UserStory;
    private long id_ParentUserStory;

    /**
     * Constructor
     */

    public UserStory(UserStoryStatus userStoryStatus, int priority, String description, int timeEstimate) {
        isValidUserStory(userStoryStatus, priority, description, timeEstimate);
        this.id_UserStory = ID_GENERATOR.getAndIncrement();
//        this.projectCode = projectCode;
        this.userStoryStatus = userStoryStatus;
        this.priority = priority;
        this.description = description;
        this.timeEstimate = timeEstimate;
        this.id_ParentUserStory = 0;
    }

    /**
     * Set parentUserStory
     *
     */

    public void setId_ParentUserStory(long id_ParentUserStory) {
        this.id_ParentUserStory = id_ParentUserStory;
    }

    /**
     * Method to validate entered data (name) by Product Owner
     * (Cris US009)
     */
    private boolean isValidUserStory(UserStoryStatus userStoryStatus, int priority, String description, int timeEstimate) {
        //check if priority is invalid
        if (priority < 0) {
            throw new IllegalArgumentException("Check priority, cannot be < 0.");
        }

        //check if description is invalid
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be blank.");
        }
        if (description.length() < 5) {
            throw new IllegalArgumentException("Description must be at least 5 characters");
        }

        // check invalid project code
//        if (code == null || code.trim().isEmpty()) {
//            throw new IllegalArgumentException("Project does not exist.");
//        }

        // check estimated time is invalid
        if (timeEstimate < 0) {
            throw new IllegalArgumentException("Check time estimate, cannot be < 0.");
        }


        return true;
    }

    /**
     * ID_UserProfile Generator.
     */
    private static AtomicInteger ID_GENERATOR = new AtomicInteger(1);
}

/**
 * Get
 *
 */

