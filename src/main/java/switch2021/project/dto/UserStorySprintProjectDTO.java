package switch2021.project.dto;

public class UserStorySprintProjectDTO {
    private String projectCode;
    private int sprintId;
    private int userStoryId;

    public UserStorySprintProjectDTO(String projectCode, int sprintId, int userStoryId) {
        this.projectCode = projectCode;
        this.sprintId = sprintId;
        this.userStoryId = userStoryId;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public int getSprintId() {
        return sprintId;
    }

    public int getUserStoryId() {
        return userStoryId;
    }
}
