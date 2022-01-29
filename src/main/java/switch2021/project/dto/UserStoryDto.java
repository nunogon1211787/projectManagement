package switch2021.project.dto;

import lombok.Data;
import switch2021.project.model.UserStoryStatus;

@Data

public class UserStoryDto {

    private UserStoryStatus userStoryStatus;
    private int priority;
    private String description;
    private int id_UserStory;


    /**
     * Constructor to test (without SINGLETON).
     */

    public UserStoryDto(UserStoryStatus userStoryStatus, int priority, String description, int id_UserStory) {
        this.userStoryStatus = userStoryStatus;
        this.priority = priority;
        this.description = description;
        this.id_UserStory = id_UserStory;
    }
}
