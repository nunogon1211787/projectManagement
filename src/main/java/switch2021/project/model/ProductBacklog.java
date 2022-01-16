package switch2021.project.model;

import java.util.ArrayList;
import java.util.List;


public class ProductBacklog {

    /** UserStory Store Attributes (backlog). Contains a UserStory list. **/
    private List<UserStory> userStoryList;

    /** Constructor **/
    public ProductBacklog() {
        userStoryList = new ArrayList<>();
    }

    /** Getters and Setters Methods. **/
    public List<UserStory> getUserStoryList() {
        return userStoryList;
    }

    public void setUserStoryList(List<UserStory> userStoryList) {
        this.userStoryList = userStoryList;
    }

    /**
     * Methods for create UserStory to the productBacklog (Cris US009)
     **/

    public UserStory createUserStory(String code, UserStoryStatus userStoryStatus, int priority, String description, int timeEstimate) {
        return new UserStory(code, userStoryStatus, priority, description, timeEstimate);
    }

    /**
     * Methods for save UserStory to the productBacklog - validate duplicate for description (Cris US009)
     **/

    public boolean saveUserStory(UserStory userStory){
        if(!validateUserStory(userStory)){
            throw new IllegalArgumentException("Repeated user profile name inserted.");
        }
        return addUserStory(userStory);
    }

    /**
     * Methods for addUserStory to the productBacklog (Cris US009)
     **/

    private boolean addUserStory(UserStory us) {
        this.userStoryList.add(us);
        return true;
    }

    /** Validation Methods. **/
    private boolean validateUserStory(UserStory userStory) {
        // check duplicate story
        for (UserStory us : userStoryList) {
            if (us.getDescription().trim().equalsIgnoreCase(userStory.getDescription().trim()) && userStory.getProjectCode().equals(us.getProjectCode())) {
                throw new IllegalArgumentException("Repeated user story description inserted.");
            }
        }
        return true;
    }
}