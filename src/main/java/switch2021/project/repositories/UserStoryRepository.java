package switch2021.project.repositories;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import switch2021.project.datamodel.UserStoryJpa;
import switch2021.project.datamodel.assembler.UserStoryJpaAssembler;
import switch2021.project.interfaces.IUserStoryRepo;
import switch2021.project.model.UserStory.UserStory;
import switch2021.project.model.valueObject.ProjectID;
import switch2021.project.model.valueObject.UserStoryID;
import switch2021.project.repositories.jpa.UserStoryJpaRepository;

import javax.swing.text.html.Option;
import java.util.*;

@Getter
@Setter
@Repository
public class UserStoryRepository implements IUserStoryRepo {

    /**
     * UserStory Store Attributes (backlog). Contains a UserStory list.
     **/
    private List<UserStory> userStoryList;

    @Autowired
    private UserStoryJpaRepository jpaRepository;

    @Autowired
    private UserStoryJpaAssembler assembler;

    /**
     * Constructor
     **/
    public UserStoryRepository() {
        this.userStoryList = new ArrayList<>();
    }

    /**
     * Refine a User Story in more user stories
     *
     * @param userStoryParent @param priority  @param description
     * @return user story, else {exception}
     */

    //TODO -----> CDC
    public UserStory refineUserStory(UserStory userStoryParent, int priority, String description) {
        UserStory userStory = new UserStory(userStoryParent);
        validateUserStory(userStory);

        return userStory;
    }

    /**
     * Validation Methods.
     * <p>
     * return exception if case
     */

    //TODO -----> CDC
    private void validateUserStory(UserStory newUserStory) {
        for (UserStory us : userStoryList) {
            if (us.getDescription().getText().trim().equalsIgnoreCase(newUserStory.getDescription().getText())) {
                throw new IllegalArgumentException("Repeated user story, inserted same title");
            }
        }
    }

    /**
     * Save User Story
     *
     * @return boolean result
     */
    @Override
    public boolean save(UserStory newUserStory) {
        existsByUserStoryId(newUserStory.getUserStoryID().toString());
        return this.userStoryList.add(newUserStory);
    }

    @Override
    public Optional<UserStory> saveReeng(UserStory newUserStory) {

        UserStoryJpa usJpa = assembler.toData(newUserStory);
        Optional<UserStory> userStory = Optional.empty();

        if(!jpaRepository.existsById(usJpa.getId())) {
            UserStoryJpa usJpaSaved = jpaRepository.save(usJpa);
            userStory = Optional.of(assembler.toDomain(usJpaSaved));
        }

        return userStory;
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

        userStoryList.sort(Comparator.comparing(userStory -> userStory.getPriority().getPriorityUs(),
                Comparator.naturalOrder()));

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
    public UserStory findByUserStoryId(String userStoryId) {
        for (UserStory us : userStoryList) {
            if (us.getUserStoryID().toString().equalsIgnoreCase(userStoryId)) {
                return us;
            }
        }
        return null;
    }

    @Override
    public Optional<UserStory> findByUserStoryIdJPA(UserStoryID userStoryID) {

        Optional<UserStoryJpa> usJpa = jpaRepository.findById(userStoryID);

        Optional<UserStory> result = Optional.empty();

        if(usJpa.isPresent()){
            result = Optional.of(assembler.toDomain(usJpa.get()));
        }

        return result;
    }

    /**
     * Finds a user story using given projectID.
     *
     * @param projectID id
     * @return allUserStoriesInAProject if found, else {@code null}
     */

    @Override
    public List<UserStory> findAllByProjectID(ProjectID projectID) {
        List<UserStory> allUserStoriesInAProject = new ArrayList<>();
        for (UserStory us : userStoryList) {
            if (us.getUserStoryID().getProjectID().equals(projectID)) {
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
            if (us.getUserStoryID().toString().equalsIgnoreCase(userStoryId.trim())) {
                throw new IllegalArgumentException("Repeated user story inserted, same code project and title.");
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserStoryRepository)) return false;
        UserStoryRepository that = (UserStoryRepository) o;
        return Objects.equals(userStoryList, that.userStoryList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userStoryList);
    }
}