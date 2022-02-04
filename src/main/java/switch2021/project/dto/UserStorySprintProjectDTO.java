package switch2021.project.dto;

import lombok.Getter;

@Getter

public class UserStorySprintProjectDTO {

    /**
     * Attributes
     **/

    private String projectCode;
    private int sprintId;
    private int userStoryId;

    /**
     * Constructor to test (without SINGLETON)
     **/

    public UserStorySprintProjectDTO(String projectCode, int sprintId, int userStoryId) {
        this.projectCode = projectCode;
        this.sprintId = sprintId;
        this.userStoryId = userStoryId;
    }

}
