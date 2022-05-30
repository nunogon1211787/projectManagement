package switch2021.project.entities.valueObjects.vos;

import lombok.Getter;
import switch2021.project.entities.valueObjects.vos.enums.UserStoryOfSprintStatus;
import switch2021.project.utils.ValueObject;

import java.util.Objects;

@Getter
public class UserStoryOfSprint implements ValueObject<UserStoryOfSprint> {

    /**
     * Attributes
     */
    private UserStoryID userStoryId;
    private UserStoryOfSprintStatus userStoryOfSprintStatus;

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

    @Override
    public boolean sameValueAs(UserStoryOfSprint other) {
        return other != null && this.userStoryId.equals(other.userStoryId)
                && this.userStoryOfSprintStatus.equals(other.userStoryOfSprintStatus);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final UserStoryOfSprint that = (UserStoryOfSprint) o;
        return sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userStoryId, userStoryOfSprintStatus);
    }
}
