package switch2021.project.model.UserStory;

import lombok.*;
import switch2021.project.dto.UserStoryDto;
import switch2021.project.factory.UserStoryFactory;
import switch2021.project.interfaces.UserStoryRepositoryInterface;
import switch2021.project.mapper.UserStoryMapper;
import switch2021.project.model.valueObject.ProjectID;

import java.util.*;

@Getter
@Setter

public class UserStoryStore implements UserStoryRepositoryInterface {

    /**
     * UserStory Store Attributes (backlog). Contains a UserStory list.
     **/
    private List<UserStory> userStoryList;
    private UserStoryFactory userStoryFactory;
    private UserStoryRepositoryInterface userStoryRepositoryInterface;


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
     * Create and save a User Story
     *
     * @param userStoryId @param title @param priority @param description @param estimateEffort
     */
    public void createAndSaveUserStory(String userStoryId, String title, int priority, String description, int estimateEffort) {

        UserStory newUserStory = this.userStoryFactory.createUserStory(userStoryId, title, priority, description, estimateEffort);
        existsByUserStoryId(userStoryId);

        this.userStoryList.add(newUserStory);
    }

    /**
     * Create user story using DTO
     *
     * @param createUserStoryDto @param mapperUS
     */

    public void createAndSaveUserStoryWithDto(UserStoryDto createUserStoryDto, UserStoryMapper mapperUS){
        UserStory userStory = mapperUS.toModel(createUserStoryDto);
        existsByUserStoryId(userStory.getUserStoryId().toString());
        this.userStoryList.add(userStory);
    }

    /**
     * Refine a User Story in more user stories
     *
     * @param userStoryParent @param priority  @param description
     * @return user story, else {exception}
     */

    public UserStory refineUserStory(UserStory userStoryParent, int priority, String description) {
        UserStory userStory = new UserStory(userStoryParent, priority, description);
        validateUserStory(userStory);

        return userStory;
    }

    /**
     * Validation Methods.
     * <p>
     * return exception if case
     */
    private void validateUserStory(UserStory newUserStory) {
        for (UserStory us : userStoryList) {
            if (us.getDescription().getText().trim().equalsIgnoreCase(newUserStory.getDescription().getText())) {
                throw new IllegalArgumentException("Repeated user story, inserted same title");
            }
        }
    }

    /**
     * Get a list of user stories sorted by priority
     *
     * @return user stories with priority appear first followed by user stories without priority,
     * user stories canceled and completed will appear at the end
     */

    @Override
    public List<UserStory> findUsSortedByPriority() {

        List<UserStory> returnList = new LinkedList<>();
        List<UserStory> noPriorityStories = new LinkedList<>();
        List<UserStory> closeAndDoneUserStories = new LinkedList<>();

        userStoryList.sort(Comparator.comparing(userStory -> userStory.getPriority().getPriorityUs(), Comparator.naturalOrder()));

        for (UserStory userStory : userStoryList) {
            if (userStory.getUsCancelled() == null && userStory.getUsEndDate() == null &&
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

    @Override
    public List<UserStory> findActiveUserStoryList() {
        List<UserStory> activeUSList = new ArrayList<>();
        for (UserStory us : userStoryList) {
            if (us.getUsEndDate() == null && us.getUsCancelled() == null) {
                activeUSList.add(us);
            }
        }
        return activeUSList;
    }

    /**
     * Finds a user story using given ID.
     *
     * @param userStoryId id
     * @return userStory if found, else {@code null}
     */

    @Override
    public UserStory findUserStoryById(String userStoryId) {
        for (UserStory us : userStoryList){
            if (us.getUserStoryId().toString().equalsIgnoreCase(userStoryId)){
                return us;
            }
        }
        return null;
    }

    /**
     * Finds a user story using given projectID.
     *
     * @param projectID id
     * @return allUserStoriesInAProject if found, else {@code null}
     */

    @Override
    public List<UserStory> findAllUserStoryByProjectID(String projectID) {
        List<UserStory> allUserStoriesInAProject = new ArrayList<>();
        for (UserStory us : userStoryList) {
            if (us.getUserStoryId().getProjectID().getCode().trim().equalsIgnoreCase(projectID)){
                allUserStoriesInAProject.add(us);
            }
        }
        return allUserStoriesInAProject;
    }

    /**
     * Finds all user stories.
     *
     * @return allUserStories if found, else {@code null}
     */

    @Override
    public List<UserStory> findAllUserStories() {
        return new ArrayList<>(this.userStoryList);
    }

    /**
     * Validate that user story exists given ID.
     *
     * @param userStoryId id
     */

    @Override
    public void existsByUserStoryId(String userStoryId) {
        for (UserStory us : userStoryList) {
            if (us.getUserStoryId().toString().equalsIgnoreCase(userStoryId.trim())) {
                throw new IllegalArgumentException("Repeated user story inserted, same code project and title.");
            }
        }
    }
}