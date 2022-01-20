package switch2021.project.model;

import lombok.Getter;

import java.util.Objects;

@Getter
public class UserStoryOfSprint {

    /** Class Attributes **/
    private final int estimateEffort;
    private final UserStory userStoryOfSprint;
    private final long id_UserStoryofSprint;

    public UserStoryOfSprint(UserStory story, int effort){
        validateFields(effort,story);
        this.estimateEffort = effort;
        this.userStoryOfSprint = story;
        this.id_UserStoryofSprint= 0;
    }

    public boolean hasCode (long id_UserStoryofSprint) {
        return this.id_UserStoryofSprint==id_UserStoryofSprint;
    }

    public void validateFields(int estimateEffort, UserStory userStory) {
        if(estimateEffort <= 0)
            throw new IllegalArgumentException("Estimate Effort cannot be 0 or lower than.");
        if(userStory == null)
            throw new IllegalArgumentException("User Story cannot be found.");
        if(userStory.getUserStoryStatus().getDescription().equals("Done"))
            throw new IllegalArgumentException("User Story is already finished.");;
    }

    /** Override **/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserStoryOfSprint)) return false;
        UserStoryOfSprint that = (UserStoryOfSprint) o;
        return estimateEffort == that.estimateEffort && userStoryOfSprint.equals(that.userStoryOfSprint);
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
