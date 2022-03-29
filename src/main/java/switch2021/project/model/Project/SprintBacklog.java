package switch2021.project.model.Project;

import lombok.Getter;
import switch2021.project.stores.UserStoryStatusStore;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class SprintBacklog {

    /** Class Attributes **/
    private final List<UserStory> userStoryList;

    public SprintBacklog() {
        this.userStoryList = new ArrayList<>();
    }

    /**Save new User Story in Sprintbacklog **/
    public boolean saveUserStoryToSprintBacklog(UserStory userStory) {
        boolean status = false;
        if(validateUserStory(userStory)) {
            UserStoryStatusStore statusStore = new UserStoryStatusStore();
            statusStore.populateDefault();
            status = userStory.setUserStoryStatusBoolean(statusStore.getUserStoryStatusByDescription("To do"));
            userStoryList.add(userStory);
        }

        return status;
    }

    /**Validate User Story in Sprintbacklog **/
    public boolean validateUserStory(UserStory userStory){
        for(UserStory i: userStoryList)
            if(i.equals(userStory) || userStory == null) {
                throw new IllegalArgumentException("User Story already exists in the sprintbacklog or is null");  //Improve Method
            }

        return true;
    }

    public UserStory getUserStory(int idUs){
        UserStory us = null;
        for (UserStory i : this.userStoryList) {
            if (i.hasCode(idUs)) {
                us = i;
                break;
            }
        }
        return us;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SprintBacklog that = (SprintBacklog) o;
        return Objects.equals(userStoryList, that.userStoryList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userStoryList);
    }
}
