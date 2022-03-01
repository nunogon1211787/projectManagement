package switch2021.project.model;

import lombok.Getter;
import switch2021.project.deprecated.UserStoryOfSprint;
import switch2021.project.stores.UserStoryStatusStore;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class SprintBacklog {

    /** Class Attributes **/
    private final List<UserStoryOfSprint> userStoryOfSprintList;
    private final List<UserStory> userStoryList;

    public SprintBacklog() {
        this.userStoryOfSprintList = new ArrayList<>();
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




    ////Deprecated Methods
    /** Create User Story Of Sprint **/
    /**
     * @deprecated (...)
     */
    @Deprecated
    public UserStoryOfSprint createUSerStoryOfSprint (UserStory userStory, int effort, UserStoryStatus status) {
        return new UserStoryOfSprint(userStory, effort, status);
    }
    /**
     * @deprecated (...)
     */
    @Deprecated
    public UserStoryOfSprint getUserStoryDeprecated(int id_UserStory) {

        UserStoryOfSprint us = null;
        for (UserStoryOfSprint i: this.userStoryOfSprintList) {
            if (i.hasCode(id_UserStory)){
                us = i;
                break;
            }
        }
        return us;
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

    /** Add User Story Of Sprint **/
    /**
     * @deprecated (...)
     */
    @Deprecated
    public boolean saveUserStoryOfSprint(UserStoryOfSprint story) {
        if (!validateUserStoryOfSprint(story)) {
            story.setIdUserStoryOfSprint(id_UserStoryOfSprintGenerator());
        }
        this.userStoryOfSprintList.add(story);
        return true;
    }

    /** Validate UserStoryOfSprint Addition - checks if already exists **/
    /**
     * @deprecated (...)
     */
    @Deprecated
    public boolean validateUserStoryOfSprint(UserStoryOfSprint userStoryOfSprint) {
        boolean msg = false;

        for (UserStoryOfSprint i : userStoryOfSprintList) {
            if (i.equals(userStoryOfSprint)) {
                msg = true;
                break;
            }
        }
        return msg;
    }

    /** ID_UserStory of Sprint Generator **/
    /**
     * @deprecated (...)
     */
    @Deprecated
    public int id_UserStoryOfSprintGenerator() {
        int id = 1;
        if(this.userStoryOfSprintList.size() > 0) {
            id = this.userStoryOfSprintList.get(userStoryOfSprintList.size()-1).getIdUserStoryOfSprint() + 1;
        }
        return id;
    }

    /**
     * Method to get user story of sprint tasks
     */
    /**
     * @deprecated (...)
     */
    @Deprecated
    public List<Task> getUserStoryOfSprintTasks(){
        List<Task> userStoryOfSprintTasksList = new ArrayList<>();

        for (UserStoryOfSprint i: userStoryOfSprintList) {
            userStoryOfSprintTasksList.addAll(i.getUserStoryOfSprintTasks());

        }
        return userStoryOfSprintTasksList;
    }

}
