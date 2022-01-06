package switch2021.project.model;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Getters e Setters - Project Lombok is a java library that automatically plugs into your editor and build tools, spicing up your java.
 */
@Getter
@Setter

public class UserStory {


    /**
     * Attributes
     */

    private String projectCode;
    private UserStoryStatus userStoryStatus;
    private String description;
    private int priority;
    private int timeEstimate;
    private long id_UserStory;

    /**
     * Constructors
     */

    public UserStory(String projectCode, UserStoryStatus userStoryStatus, String description, int priority, int timeEstimate) {
        this.id_UserStory = ID_GENERATOR.getAndIncrement();
        this.projectCode = projectCode;
        this.userStoryStatus = userStoryStatus;
        this.description = description;
        this.priority = priority;
        this.timeEstimate = timeEstimate;
    }

    //Create ID automatically
    private static AtomicInteger ID_GENERATOR = new AtomicInteger(001);

    /**
     * @return the join between the project code and the ID that will be generated automatically
     */

    public String getUserStoryStringIdentifier() {
        return projectCode + "-" + id_UserStory;
    }
}
