package switch2021.project.model;

import lombok.Getter;
import switch2021.project.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
//@Deprecated
public class UserStoryOfSprint {

    /**
     * Class Attributes
     **/
    private int id_UserStoryOfSprint;
    private int estimateEffort;
    private UserStory userStoryOfSprint;
    private UserStoryStatus status;
    private List<Task> userStoryOfSprintTasks;

    /**
     * ---> Constructor <---
     **/

    public UserStoryOfSprint(UserStory story, int effort, UserStoryStatus status) {
        validateFields(effort, story);
        this.estimateEffort = effort;
        this.userStoryOfSprint = story;
        this.userStoryOfSprintTasks = new ArrayList<>();
        this.status = status;
    }

    public UserStoryOfSprint(UserStory story, int effort, int userStoryId) {
        validateFields(effort, story);
        this.estimateEffort = effort;
        this.userStoryOfSprint = story;
        this.id_UserStoryOfSprint = userStoryId;
        this.userStoryOfSprintTasks = new ArrayList<>();
    }

    public boolean hasCode(long id_UserStoryofSprint) {

        return this.id_UserStoryOfSprint == id_UserStoryofSprint;
    }

    public void validateFields(int estimateEffort, UserStory userStory) {
        if (estimateEffort <= 0)
            throw new IllegalArgumentException("Estimate Effort cannot be 0 or lower than.");
        if (userStory == null)
            throw new IllegalArgumentException("User Story cannot be found.");
        if (userStory.getUserStoryStatus().getDescription().equals("Done"))
            throw new IllegalArgumentException("User Story is already finished.");
        ;
    }

    public UserStory getUserStoryOfSprint() {
        return userStoryOfSprint;
    }

    public void setId_UserStoryOfSprint(int id_UserStoryOfSprint) {
        this.id_UserStoryOfSprint = id_UserStoryOfSprint;
    }

    private boolean validateEffort(int x) {
        /*if (x < 0 || x == 4 || x == 6 || x == 7 || x > 8 && x < 13 || x > 13 && x < 20 || x > 20)
            return false;
        return true;*/
        return Utils.isFibonacci(x);
    }

    public boolean setEstimateEffort(int estimateEffort) {
        if (validateEffort(estimateEffort)) {
            this.estimateEffort = estimateEffort;
            return true;
        }
        return false;
    }
    /**
     * Override
     **/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserStoryOfSprint)) return false;
        UserStoryOfSprint that = (UserStoryOfSprint) o;
        return estimateEffort == that.estimateEffort && userStoryOfSprint.equals(that.userStoryOfSprint)
                && userStoryOfSprintTasks.equals(that.userStoryOfSprintTasks);//TODO This override doesnt compare ID
    }

    @Override
    public int hashCode() {
        return Objects.hash(estimateEffort, userStoryOfSprint);
    }

    @Override
    public String toString() {
        return "UserStoryOfSprint{" +
                "estimateEffort=" + estimateEffort +
                ", userStoryOfSprint=" + userStoryOfSprint +
                '}';
    }

}