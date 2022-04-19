package switch2021.project.model.UserStory;

import lombok.*;
import switch2021.project.dto.UserStoryDto;
import switch2021.project.factory.UserStoryFactory;
import switch2021.project.mapper.UserStoryMapper;
import switch2021.project.model.valueObject.ProjectID;

import java.util.*;

@Getter
@Setter

public class UserStoryStore {

    /**
     * UserStory Store Attributes (backlog). Contains a UserStory list.
     **/
    private List<UserStory> userStoryList;
    private UserStoryFactory userStoryFactory;


    /**
     * Constructor
     **/
    public UserStoryStore(UserStoryFactory userStoryFactory) {
        this.userStoryList = new ArrayList<>();
        this.userStoryFactory = userStoryFactory == null ? new UserStoryFactory() : userStoryFactory;
    }

    public UserStoryStore() {
        this(null);
    }


    /**
     * Methods for create UserStory to the productBacklog
     **/
    public void createAndSaveUserStory(String userStoryId, String title, int priority, String description, int estimateEffort) {

        UserStory newUserStory = this.userStoryFactory.createUserStory(userStoryId, title, priority, description, estimateEffort);
        validateIdUserStory(newUserStory);

        this.userStoryList.add(newUserStory);
    }


    public UserStory refineUserStory(UserStory userStoryParent, int priority, String description) {
        UserStory userStory = new UserStory(userStoryParent, priority, description);
        validateUserStory(userStory);

        return userStory;
    }

    public UserStory createUserStoryWithDto(UserStoryDto createUserStoryDto, UserStoryMapper mapperUS) {
        return mapperUS.toModel(createUserStoryDto);
    }


    /**
     * Validation Methods.
     *
     * return exception if case
     */
    private void validateUserStory(UserStory newUserStory) {
        for (UserStory us : userStoryList) {
            if (us.getDescription().getText().trim().equalsIgnoreCase(newUserStory.getDescription().getText())) {
                throw new IllegalArgumentException("Repeated user story, inserted same title");
            }
        }
    }

    private void validateIdUserStory(UserStory newUserStory) {
        for (UserStory id : userStoryList) {
            if (Objects.equals(id.getUserStoryId().toString(), newUserStory.getUserStoryId().toString())) {
                throw new IllegalArgumentException("Repeated user story inserted, same code project and title.");
            }
        }
    }

    /**
     * Get US sorted by Priority
     **/
    public List<UserStory> getUsSortedByPriority() {

        List<UserStory> returnList = new LinkedList<>();
        List<UserStory> noPriorityStories = new LinkedList<>();
        List<UserStory> closeAndDoneUserStories = new LinkedList<>();

        userStoryList.sort(Comparator.comparing(userStory -> userStory.getPriority().getPriorityUs(), Comparator.naturalOrder()));


        for (UserStory userStory : userStoryList) {
            if (userStory.getUsCancelled() != null && userStory.getUsEndDate() != null &&
                    userStory.getPriority().getPriorityUs() != 0) {
                returnList.add(userStory);
            } else if (userStory.getPriority().getPriorityUs() == 0) {
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
     * Finds all user story all activeUserStoryList.
     *
     * @return userStoryList if found, else {@code null}
     */

    public List<UserStory> findActiveUserStoryList() {
        List<UserStory> activeUSList = new ArrayList<>();
        for (UserStory us : userStoryList) {
            if (us.getUsEndDate() != null && us.getUsCancelled() != null) {
                activeUSList.add(us);
            }
        }
        return activeUSList;
    }

    /**
     * Finds a user story using given ID.
     *
     * @param idUserStory id
     * @return userStory if found, else {@code null}
     */
    public UserStory findUserStoryById(UserStoryId idUserStory) {
        UserStory userStory = null;
        for (UserStory us : userStoryList) {
            if (Objects.equals(us.getUserStoryId().toString(), idUserStory.toString())) {
                userStory = us;
                break;
            }
        }
        return userStory;
    }

    /**
     * Finds a user story using given projectID.
     *
     * @param projectID id
     * @return allUserStoriesInAProject if found, else {@code null}
     */
    public List<UserStory> findAllUserStoryByProjectID(ProjectID projectID) {
        List<UserStory> allUserStoriesInAProject = new ArrayList<>();
        for (UserStory us : userStoryList) {
            if (us.hasProjectID(projectID)) {
                allUserStoriesInAProject.add(us);
            }
        }
        return allUserStoriesInAProject;
    }

    /**
     * Delete a user story using given ID.
     *
     * @param idUserStory id
     * return UserStory byID if found, else {@code null}
     */

    public void removeUserStoryById(UserStoryId idUserStory) {
        this.userStoryList.remove(idUserStory);
    }
}
