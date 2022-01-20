package switch2021.project.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class SprintBacklog {

    /** Class Attributes **/
    private final List<UserStoryOfSprint> userStoryOfSprintList;

    public SprintBacklog() {
        this.userStoryOfSprintList = new ArrayList<>();
    }

    /** Create User Story Of Sprint **/
    public UserStoryOfSprint createUSerStoryOfSprint (UserStory userStory, int effort) {
        return new UserStoryOfSprint(userStory, effort );
    }

    /**Getter **/
    public List<UserStoryOfSprint> getUserStoryOfSprintList() {
        return userStoryOfSprintList;
    }

    /** Add User Story Of Sprint **/
    public void addUserStory(UserStoryOfSprint story) {
        this.userStoryOfSprintList.add(story);
    }

    /** Override **/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SprintBacklog)) return false;
        SprintBacklog that = (SprintBacklog) o;
        return Objects.equals(userStoryOfSprintList, that.userStoryOfSprintList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userStoryOfSprintList);
    }

    @Override
    public String toString() {
        return "SprintBacklog{" +
                "userStoryOfSprintList=" + userStoryOfSprintList +
                '}';
    }
}
