package switch2021.project.model.Sprint;

import lombok.Getter;
import switch2021.project.model.UserStory.UserStory;
import switch2021.project.model.valueObject.UserStoryID;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
class ScrumBoard {

    /** Class Attributes **/
    private final List<UserStory> userStoryList;

    protected ScrumBoard() {
        this.userStoryList = new ArrayList<>();
    }

    /**Save new User Story in SprintBacklog **/
    protected boolean saveUserStoryToSprintBacklog(UserStory userStory) {
//        boolean status = false;
        if(validateUserStory(userStory)) {
//            UserStoryStatusStore statusStore = new UserStoryStatusStore();
//            statusStore.populateDefault();
//            status = userStory.setUserStoryStatusBoolean(statusStore.getUserStoryStatusByDescription("To do"));
//            userStoryList.add(userStory);
        }

        return userStoryList.add(userStory);
    }

    /**Validate User Story in SprintBacklog **/
    protected boolean validateUserStory(UserStory userStory){
        for(UserStory i: userStoryList)
            if(i.equals(userStory) || userStory == null) {
                throw new IllegalArgumentException("User Story already exists in the sprintbacklog or is null");  //Improve Method
            }

        return true;
    }

    protected UserStory getUserStory(UserStoryID idUs){
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
        ScrumBoard that = (ScrumBoard) o;
        return Objects.equals(userStoryList, that.userStoryList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userStoryList);
    }
}
