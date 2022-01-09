package switch2021.project.model;

import java.util.ArrayList;
import java.util.List;


public class ProductBacklog {

    private List<UserStory> userStoryList;

    public ProductBacklog() {
        userStoryList = new ArrayList<>();
    }

    public List<UserStory> getUserStoryList() {
        return userStoryList;
    }

    public void setUserStoryList(List<UserStory> userStoryList) {
        this.userStoryList = userStoryList;
    }

    /**
     * Methods for addUserStory to the productBacklog (Cris US009)
     **/

    public boolean addUserStory(UserStory us) {
        this.userStoryList.add(us);
        return true;
    }

    /**
     * Methods for validate data (Cris US009)
     **/

    private boolean isValidUserStory(String code, UserStoryStatus userStoryStatus, int priority, String description, int timeEstimate) {
        //check if priority is invalid
        if (priority < 0) {
            return false;
        }

        //check if description is invalid
        if (description == null || description.trim().isEmpty()) {
            return false;
        }

        // check duplicate story
        for (UserStory userStory : userStoryList) {
            if (description.trim().equalsIgnoreCase(userStory.getDescription().trim()) && userStory.getProjectCode().equals(code)) {
                return false;
            }
        }

        // check invalid project code
        if (code == null || code.trim().isEmpty()) {
            return false;
        }

        // check estimated time is invalid
        if (timeEstimate < 0) {
            return false;
        }

        return true;
    }

    public UserStory createUserStory(String code, UserStoryStatus userStoryStatus, int priority, String description, int timeEstimate){
        boolean isDataValid = isValidUserStory(code, userStoryStatus, priority, description, timeEstimate);
        return isDataValid ? new UserStory(code, userStoryStatus, priority, description, timeEstimate) : null;
    }

}
