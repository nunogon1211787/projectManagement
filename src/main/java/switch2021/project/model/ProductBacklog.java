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

    public List<UserStory> getActiveUserStoryList() {
        List<UserStory> activeUSList = new ArrayList<>();
        for (UserStory us : userStoryList) {
            if (!us.getUserStoryStatus().getDescription().equals("Completed")) {
                activeUSList.add(us);
            }
        }
        return activeUSList;
    }

    public UserStory getUserStoryById(int id) {
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

    public UserStory createUserStory(int priority, String description) {
        return new UserStory(priority, description);
    }

//    public UserStory createUserStory(int userStoryId, UserStoryStatus userStoryStatus, int priority, String description) {
//        return new UserStory(userStoryId, userStoryStatus, priority, description);
//    }

    public UserStory createUserStoryRefine(UserStory userStoryParent, UserStoryStatus userStoryStatus, int priority, String description) {
        return new UserStory(userStoryParent, userStoryStatus, priority, description);
    }

    /**
     * Methods for save UserStory to the productBacklog - validate duplicate for description (Cris US009)
     **/

    public boolean saveUserStory(UserStory userStory) {
        if (!validateUserStory(userStory)) {
            throw new IllegalArgumentException("Repeated user story inserted, same code project and description.");
        } else {
            userStory.setId_UserStory(id_UserStoryGenerator());
        }
        return addUserStory(userStory);
    }

    /**
     * Methods for addUserStory to the productBacklog (Cris US009)
     **/

    public boolean addUserStory(UserStory us) {
        if (validateIdUserStory(us)) {
            this.userStoryList.add(us);
        } else {
            us.setId_UserStory(id_UserStoryGenerator());
            this.userStoryList.add(us);
        }
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

    private boolean validateIdUserStory(UserStory userStory) {
        boolean msg = true;
        for (UserStory i : userStoryList) {
            if (i.getId_UserStory() == userStory.getId_UserStory()) {
                msg = false;
                break;
            }
        }
        return msg;
    }


    public List<UserStory> getUsSortedByPriority() {

        List<UserStory> returnList = new LinkedList<>();
        List<UserStory> noPriorityStories = new LinkedList<>();
        List<UserStory> closeAndDoneUserStories = new LinkedList<>();

        userStoryList.sort(Comparator.comparingInt(UserStory::getPriority));

        for (UserStory userStory : userStoryList) {
            if (!userStory.getUserStoryStatus().getDescription().equals("Cancelled") &&
                    !userStory.getUserStoryStatus().getDescription().equals("Done") &&
                     userStory.getPriority()!=0) {
                returnList.add(userStory);
            } else if(userStory.getPriority()==0) {
                noPriorityStories.add(userStory);
            } else {
                closeAndDoneUserStories.add(userStory);
            }
        }
        returnList.addAll(noPriorityStories);
        returnList.addAll(closeAndDoneUserStories);
        userStoryList = returnList;
        return userStoryList;
    }

    /**
     * ID_UserStory Generator
     **/
    public int id_UserStoryGenerator() {
        int id = 1;
        if (this.userStoryList.size() > 0) {
            id = this.userStoryList.get(userStoryList.size() - 1).getId_UserStory() + 1;
        }
        return id;
    } //if the object isn´t saved on the list, the id will be the same for all
    //objects. This issue will be solved when calling the save method.
}
