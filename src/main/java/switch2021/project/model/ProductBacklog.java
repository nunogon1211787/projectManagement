package switch2021.project.model;

import java.util.*;


public class ProductBacklog {

    /**
     * UserStory Store Attributes (backlog). Contains a UserStory list.
     **/
    private List<UserStory> userStoryList;

    /**
     * Constructor
     **/
    public ProductBacklog() {
        userStoryList = new ArrayList<>();
    }

    /**
     * Getters and Setters Methods.
     **/
    public List<UserStory> getUserStoryList() {
        return userStoryList;
    }

    public UserStory getUserStoryById(long id) {
        UserStory userStory = null;
        for (UserStory us : userStoryList) {
            if (us.getId_UserStory() == id) {
                userStory = us;
                break;
            }
        }
        return userStory;
    }

    public void setUserStoryList(List<UserStory> userStoryList) {
        this.userStoryList = userStoryList;
    }

    /**
     * Methods for create UserStory to the productBacklog (Cris US009)
     **/

    public UserStory createUserStory(UserStoryStatus userStoryStatus, int priority, String description) {
        return new UserStory(userStoryStatus, priority, description);
    }

    /**
     * Methods for save UserStory to the productBacklog - validate duplicate for description (Cris US009)
     **/

    public boolean saveUserStory(UserStory userStory) {
        if (!validateUserStory(userStory)) {
            throw new IllegalArgumentException("Repeated user story inserted, same code project and description.");
        }
        return addUserStory(userStory);
    }

    /**
     * Methods for addUserStory to the productBacklog (Cris US009)
     **/

    public boolean addUserStory(UserStory us) {
        this.userStoryList.add(us);
        return true;
    }

    /**
     * Validation Methods.
     **/
    private boolean validateUserStory(UserStory userStory) {
        // check duplicate story
        for (UserStory us : userStoryList) {
            if (us.getDescription().trim().equalsIgnoreCase(userStory.getDescription().trim())) {
                throw new IllegalArgumentException("Repeated user story inserted, same code project and description.");
            }
        }
        return true;
    }


    public List<UserStory> getUsSortedByPriority(){
        userStoryList.sort(Comparator.comparingInt(UserStory::getPriority));
        return userStoryList;
    }
}
