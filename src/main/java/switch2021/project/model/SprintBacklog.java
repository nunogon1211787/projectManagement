package switch2021.project.model;

import java.util.ArrayList;
import java.util.List;

public class SprintBacklog {

    /** Calss Atributtes **/
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
    public boolean addUserStory(UserStoryOfSprint story) {
        this.userStoryOfSprintList.add(story);
        return true;
    }



}
