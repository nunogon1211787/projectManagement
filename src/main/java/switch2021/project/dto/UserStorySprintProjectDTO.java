package switch2021.project.dto;

import lombok.Getter;
import switch2021.project.model.UserStory.UserStoryId;

@Getter

public class UserStorySprintProjectDTO {

    /**
     * Attributes
     **/

    private String projectCode;
    private int sprintId;
    private UserStoryId userStoryId;

    /**
     * Constructor to test (without SINGLETON)
     **/

    public UserStorySprintProjectDTO(String projectCode, int sprintId, String userStoryId) {
        this.projectCode = projectCode;
        this.sprintId = sprintId;
        this.userStoryId = new UserStoryId(userStoryId);
    }

}
