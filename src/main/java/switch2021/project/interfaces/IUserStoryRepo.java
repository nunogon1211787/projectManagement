package switch2021.project.interfaces;


import switch2021.project.model.UserStory.UserStory;

import java.util.List;

public interface IUserStoryRepo /* extends JpaRepository <UserStory, String>*/  {

    /**
     * Finds all user story all activeUserStoryList.
     *
     * @return userStoryList if found, else {@code null}
     */
    List<UserStory> findActiveUserStoryList();

    /**
     * Finds a user story using given ID.
     *
     * @param userStoryID id
     * @return userStory if found, else {@code null}
     */

    UserStory findUserStoryById(String userStoryID);


    /**
     * Finds a user story using given projectID.
     *
     * @param projectID id
     * @return allUserStoriesInAProject if found, else {@code null}
     */

    List<UserStory> findAllUserStoryByProjectID(String projectID);

    /**
     * Finds all user story
     *
     * @return allUserStories if found, else {@code null}
     */
    List<UserStory> findAllUserStories();


    /**
     * Validate that user story exists given ID.
     *
     * @param userStoryId id
     */
    void existsByUserStoryId(String userStoryId);


    /**
     * Get a list of user stories sorted by priority
     *
     * @return user stories with priority appear first followed by user stories without priority,
     * user stories canceled and completed will appear at the end
     */

    List<UserStory> findUsSortedByPriority();


    /**
     * Save user story
     *
     * @return boolean result
     */

    boolean save(UserStory newUserStory);

}
