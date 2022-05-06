package switch2021.project.model.valueObject;

import lombok.Getter;

import java.util.Objects;

@Getter
public class UserStoryOfSprint {

    /**
     * Attributes
     */
    private final UserStoryID userStoryId;
    private final UserStoryOfSprintStatus userStoryOfSprintStatus;


    /**
     * Constructor
     */
    public UserStoryOfSprint(UserStoryID userStoryId, UserStoryOfSprintStatus userStoryOfSprintStatus) {
        this.userStoryId = userStoryId;
        this.userStoryOfSprintStatus = userStoryOfSprintStatus;
    }

    /**
     * Method to Have a User Story Of Sprint
     */
    public boolean hasUserStoryOfSprint(String userStoryOfSprint) {
        return Objects.equals(this.userStoryOfSprintStatus.toString(), userStoryOfSprint);
    }
}
