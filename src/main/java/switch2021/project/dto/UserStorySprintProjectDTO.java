package switch2021.project.dto;

import lombok.Getter;
import switch2021.project.model.Sprint.SprintID;
import switch2021.project.model.UserStory.UserStoryId;

@Getter

public class UserStorySprintProjectDTO {

    /**
     * Attributes
     **/

    private String projectCode;
    private SprintID sprintId;
    private UserStoryId userStoryId;

    /**
     * Constructor to test (without SINGLETON)
     **/

    public UserStorySprintProjectDTO(String projectCode, String sprintId, String userStoryId) {
        this.projectCode = projectCode;
        this.sprintId = new SprintID(sprintId);
        this.userStoryId = new UserStoryId(userStoryId);
    }

}
