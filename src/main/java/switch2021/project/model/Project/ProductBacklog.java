package switch2021.project.model.Project;

import lombok.Getter;
import lombok.Setter;
import switch2021.project.dto.UserStoryDto;
import switch2021.project.factory.UserStoryFactory;
import switch2021.project.mapper.UserStoryMapper;
import switch2021.project.model.UserStory.UserStory;
import switch2021.project.valueObject.UserStoryStatus;
import java.util.*;

@Getter
@Setter

public class ProductBacklog {

    /**
     * UserStory Store Attributes (backlog). Contains a UserStory list.
     **/
    private List<UserStory> userStoryList;
    private UserStoryFactory userStoryFactory;


    /**
     * Constructor
     **/
    public ProductBacklog(UserStoryFactory userStoryFactory) {
        this.userStoryList = new ArrayList<>();
        this.userStoryFactory = userStoryFactory == null ? new UserStoryFactory() : userStoryFactory;
    }

    public ProductBacklog() {
        this(null);
    }


    /**
     * Methods for create UserStory to the productBacklog
     **/
    public boolean createAndSaveUserStory(String title, int priority, String description, int estimateEffort) {

        UserStory newUserStory = this.userStoryFactory.createUserStory(title, priority, description, estimateEffort);

        validateUserStory(newUserStory);

        if (validateIdUserStory(newUserStory)) {
            newUserStory.setIdUserStory(idUserStoryGenerator());
        }
        return this.userStoryList.add(newUserStory);
    }

    public UserStory RefineUserStory(UserStory userStoryParent, UserStoryStatus userStoryStatus, int priority, String description) {
        UserStory userStory = new UserStory(userStoryParent, userStoryStatus, priority, description);
        validateUserStory(userStory);

        return userStory;
    }

    public UserStory createUserStoryWithDto(UserStoryDto createUserStoryDto, UserStoryMapper mapperUS) {
        return mapperUS.toModel(createUserStoryDto);
    }


    /**
     * Validation Methods.
     **/
    private void validateUserStory(UserStory newUserStory) {
        // check duplicate story
        for (UserStory us : userStoryList) {
            if (us.getDescription().getText().trim().equalsIgnoreCase(newUserStory.getDescription().getText().trim())) {
                throw new IllegalArgumentException("Repeated user story inserted, same code project and description.");
            }
        }
    }

    private boolean validateIdUserStory(UserStory newUserStory) {
        boolean msg = true;
        for (UserStory i : userStoryList) {
            if (i.getIdUserStory() == newUserStory.getIdUserStory()) {
                msg = false;
                break;
            }
        }
        return msg;
    }


    /**
     * Get Methods.
     **/
    public List<UserStory> getUsSortedByPriority() {

        List<UserStory> returnList = new LinkedList<>();
        List<UserStory> noPriorityStories = new LinkedList<>();
        List<UserStory> closeAndDoneUserStories = new LinkedList<>();

        userStoryList.sort(Comparator.comparingInt(UserStory::getPriority));

        for (UserStory userStory : userStoryList) {
            if (!userStory.getUserStoryStatus().getDescription().getText().equals("Cancelled") &&
                    !userStory.getUserStoryStatus().getDescription().getText().equals("Done") &&
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

    public List<UserStory> getActiveUserStoryList() {
        List<UserStory> activeUSList = new ArrayList<>();
        for (UserStory us : userStoryList) {
            if (!us.getUserStoryStatus().getDescription().getText().equals("Completed")) {
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
}
