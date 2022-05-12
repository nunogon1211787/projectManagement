package switch2021.project.dto.old;

import lombok.Getter;
import switch2021.project.model.valueObject.SprintID;
import switch2021.project.model.valueObject.UserStoryID;

@Getter

public class UserStorySprintProjectDTO {

    /**
     * Attributes
     **/

    private String projectCode;
    private SprintID sprintId;
    private UserStoryID userStoryId;

    /**
     * Constructor to test (without SINGLETON)
     **/

    public UserStorySprintProjectDTO(String projectCode, String sprintId, String userStoryId) {
        this.projectCode = projectCode;
        this.sprintId = new SprintID(sprintId);
        this.userStoryId = new UserStoryID(userStoryId);
    }

}
