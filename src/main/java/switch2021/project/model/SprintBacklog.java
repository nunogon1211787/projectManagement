package switch2021.project.model;

import lombok.Getter;
import switch2021.project.depracated.UserStoryOfSprint;

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

    public boolean saveUserStoryToSprintBacklog(UserStory userStory) {
        boolean status = false;
        if(validateUserStory(userStory)) {
            status = true;
            userStoryList.add(userStory);
        }

        return status;
    }

    public boolean validateUserStory(UserStory userStory){
        boolean status = true;
        for(UserStory i: userStoryList)
            if(i.equals(userStory)) {
                status = false;
                break;
            }

        return status;
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


    ////Deprecated Methods
    /** Create User Story Of Sprint **/
    @Deprecated
    public UserStoryOfSprint createUSerStoryOfSprint (UserStory userStory, int effort, UserStoryStatus status) {
        return new UserStoryOfSprint(userStory, effort, status);
    }

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
    @Deprecated
    public boolean saveUserStoryOfSprint(UserStoryOfSprint story) {
        if (!validateUserStoryOfSprint(story)) {
            story.setId_UserStoryOfSprint(id_UserStoryOfSprintGenerator());
        }
        this.userStoryOfSprintList.add(story);
        return true;
    }

    /** Validate UserStoryOfSprint Addition - checks if already exists **/
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
    @Deprecated
    public int id_UserStoryOfSprintGenerator() {
        int id = 1;
        if(this.userStoryOfSprintList.size() > 0) {
            id = this.userStoryOfSprintList.get(userStoryOfSprintList.size()-1).getId_UserStoryOfSprint() + 1;
        }
        return id;
    }

    /**
     * Method to get user story of sprint tasks
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
