package switch2021.project.model;

import switch2021.project.dto.UserStoryDto;
import switch2021.project.mapper.UserStoryMapper;

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
        return Collections.unmodifiableList(userStoryList);
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
            if (us.getIdUserStory() == id) {
                userStory = us;
                break;
            }
        }
        return userStory;
    }

    public void setUserStoryList(List<UserStory> userStoryList) {
        this.userStoryList = Collections.unmodifiableList(userStoryList);
    }

    /**
     * Methods for create UserStory to the productBacklog (Cris US009)
     **/

    public UserStory createUserStory(String name, int priority, String description, int estimateEffort) {
        return new UserStory(name, priority, description, estimateEffort);
    }

    public UserStory createUserStoryRefine(UserStory userStoryParent, UserStoryStatus userStoryStatus, int priority, String description) {
        return new UserStory(userStoryParent, userStoryStatus, priority, description);
    }

    /**
     * Methods for save UserStory to the productBacklog - validate duplicate for description (Cris US009)
     **/

    public boolean saveUserStory(UserStory userStory) {
        validateUserStory(userStory);
        if (validateIdUserStory(userStory)) {
            userStory.setIdUserStory(idUserStoryGenerator());
            this.userStoryList.add(userStory);
        }
        return true;
    }

    /**
     * Validation Methods.
     **/
    private void validateUserStory(UserStory userStory) {
        // check duplicate story
        for (UserStory us : userStoryList) {
            if (us.getDescription().getDescriptionF().trim().equalsIgnoreCase(userStory.getDescription().getDescriptionF().trim())) {
                throw new IllegalArgumentException("Repeated user story inserted, same code project and description.");
            }
        }
    }

    private boolean validateIdUserStory(UserStory userStory) {
        boolean msg = true;
        for (UserStory i : userStoryList) {
            if (i.getIdUserStory() == userStory.getIdUserStory()) {
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
                    userStory.getPriority() != 0) {
                returnList.add(userStory);
            } else if (userStory.getPriority() == 0) {
                noPriorityStories.add(userStory);
            } else {
                closeAndDoneUserStories.add(userStory);
            }
        }
        returnList.addAll(noPriorityStories);
        returnList.addAll(closeAndDoneUserStories);
        userStoryList = returnList;
        return Collections.unmodifiableList(userStoryList);
    }

    /**
     * ID_UserStory Generator
     **/
    public int idUserStoryGenerator() {
        int id = 1;
        if (!this.userStoryList.isEmpty()) {
            id = this.userStoryList.get(userStoryList.size() - 1).getIdUserStory() + 1;
        }
        return id;
    } //if the object isn´t saved on the list, the id will be the same for all
    //objects. This issue will be solved when calling the save method.

    public UserStory createUserStoryWithDto(UserStoryDto createUserStoryDto, UserStoryMapper mapperUS) {
        return mapperUS.toModel(createUserStoryDto);
    }
}
