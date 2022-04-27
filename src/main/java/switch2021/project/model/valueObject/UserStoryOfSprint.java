package switch2021.project.model.valueObject;

import lombok.Getter;
import switch2021.project.model.Sprint.UserStoryOfSprintStatus;
import switch2021.project.model.UserStory.UserStoryId;

import java.util.Objects;

@Getter
public class UserStoryOfSprint {

    /**
     * Attributes
     */
    private final UserStoryId userStoryId;
    private final UserStoryOfSprintStatus userStoryOfSprintStatus;


    /**
     * Constructor
     */
    public UserStoryOfSprint(UserStoryId userStoryId, UserStoryOfSprintStatus userStoryOfSprintStatus) {
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
