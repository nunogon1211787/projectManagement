package switch2021.project.model.Sprint;

import lombok.Getter;
import switch2021.project.model.SystemUser.User;
import switch2021.project.model.UserStory.UserStory;
import switch2021.project.model.valueObject.SprintID;
import switch2021.project.model.valueObject.UserStoryID;
import switch2021.project.model.valueObject.UserStoryOfSprint;
import switch2021.project.utils.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
class ScrumBoard implements Entity<ScrumBoard> {

    /** Class Attributes **/
    private SprintID sprintID;

    private final List<UserStoryOfSprint> userStoriesOfSprint;

    protected ScrumBoard() {
        this.userStoriesOfSprint = new ArrayList<>();
    }

    protected ScrumBoard(List<UserStoryOfSprint> userStoriesOfSprint) {
        this.userStoriesOfSprint = userStoriesOfSprint;
    }

    /**Save new User Story in SprintBacklog **/
/*    protected boolean saveUserStoryToSprintBacklog(UserStory userStory) {
//        boolean status = false;
        if(validateUserStory(userStory)) {
//            UserStoryStatusStore statusStore = new UserStoryStatusStore();
//            statusStore.populateDefault();
//            status = userStory.setUserStoryStatusBoolean(statusStore.getUserStoryStatusByDescription("To do"));
//            userStoryList.add(userStory);
        }

        return userStories.add(userStory);
    }

    /**Validate User Story in SprintBacklog **/
/*    protected boolean validateUserStory(UserStory userStory){
        for(UserStory i: userStories)
            if(i.equals(userStory) || userStory == null) {
                throw new IllegalArgumentException("User Story already exists in the sprintbacklog or is null");  //Improve Method
            }

        return true;
    }

    protected UserStory getUserStory(UserStoryID idUs){
        UserStory us = null;
        for (UserStory i : this.userStories) {
            if (i.hasCode(idUs)) {
                us = i;
                break;
            }
        }
        return us;
    }

    /**
     * Override Methods
     *
     * @param o to compare
     * @return True if they have the same identity
     * @see #sameIdentityAs(ScrumBoard)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScrumBoard that = (ScrumBoard) o;
        return sameIdentityAs(that);
    }

    @Override
    public boolean sameIdentityAs(ScrumBoard other) {
        return other != null && sprintID.sameValueAs(other.sprintID);
    }

    /**
     * @return Hash code of sprint id.
     */
    @Override
    public int hashCode() {
        return sprintID.hashCode();
    }
}
