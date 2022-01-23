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
        return new UserStoryOfSprint(userStory, effort);
    }

    /**Getter **/
    public List<UserStoryOfSprint> getUserStoryOfSprintList() {
        return userStoryOfSprintList;
    }

    public UserStoryOfSprint getUserStory(int id_UserStory) {
        UserStoryOfSprint us = null;
        for (UserStoryOfSprint i: this.userStoryOfSprintList) {
            if (i.hasCode(id_UserStory)){
                us = i;
                break;
            }
        }
        return us;
    }

    /** Add User Story Of Sprint **/
    public boolean addUserStory(UserStoryOfSprint story) {
        if (!validateId_UserStoryOfSprint(story)) {
            story.setId_UserStoryOfSprint(id_UserStoryOfSprintGenerator());
        }
        this.userStoryOfSprintList.add(story);
        return true;
    }

    /** Validate UserStoryOfSprint Addition - checks if already exists **/
    public boolean validateId_UserStoryOfSprint (UserStoryOfSprint userStoryOfSprint) {
        boolean msg = false;

        for (UserStoryOfSprint i : userStoryOfSprintList) {
            if (i.getUserStoryOfSprint().getId_UserStory() == userStoryOfSprint.getUserStoryOfSprint().getId_UserStory()) {
                msg = true;
                break;
            }
        }
        return msg;
    }

    private boolean validateUserStoryOfSprint(UserStoryOfSprint userStoryOfSprint){
        boolean msg = true;
        if(userStoryOfSprintList.size() == 0){
            msg = false; } else {
            for (UserStoryOfSprint i : userStoryOfSprintList) {
                if(i.equals(userStoryOfSprint)){
                    msg = false;
                    break;
                }
            }
        }
        return msg;
    }

    /** ID_UserStory of Sprint Generator **/
    public int id_UserStoryOfSprintGenerator() {
        int id = 1;
        if(this.userStoryOfSprintList.size() > 0) {
            id = this.userStoryOfSprintList.get(userStoryOfSprintList.size()-1).getId_UserStoryOfSprint() + 1;
        }
        return id;
    } //if the object isn´t saved on the list, the id will be the same for all
    //objects. This issue will be solved when calling the save method.

    /**
     * Save UserStoryOfSprint Method. Save a new UserStoryOfSprint object to the UserStoryOfSprint List
     **/
    public boolean saveUserStoryOfSprint(UserStoryOfSprint userStory) {
        if (!validateUserStoryOfSprint(userStory)) {
            throw new IllegalArgumentException("Repeated UserStoryOfSprint.");
        } else {
            userStory.setId_UserStoryOfSprint(id_UserStoryOfSprintGenerator());
        }
        return addUserStory(userStory);
    }

    /**
     * Method to get user story of sprint tasks
     */
    public List<Task> getUserStoryOfSprintTasks(){
        List<Task> userStoryOfSprintTasksList = new ArrayList<>();

        for (UserStoryOfSprint i: userStoryOfSprintList) {
            userStoryOfSprintTasksList.add(i.getUserStoryOfSprintTask());

        }
        return userStoryOfSprintTasksList;
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
