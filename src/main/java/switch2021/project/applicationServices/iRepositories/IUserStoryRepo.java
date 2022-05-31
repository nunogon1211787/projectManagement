package switch2021.project.applicationServices.iRepositories;


import switch2021.project.entities.aggregates.UserStory.UserStory;
import switch2021.project.entities.valueObjects.vos.UserStoryID;

import java.util.List;
import java.util.Optional;

public interface IUserStoryRepo {

    /**
     * Finds a user story using given ID.
     */

    Optional<UserStory> findByUserStoryId(UserStoryID userStoryID);

    /**
     * Finds all user story
     */
    List<UserStory> findAll();

    /**
     * Save user story
     */

    Optional<UserStory> save(UserStory newUserStory);

    boolean deleteByUserStoryId(UserStoryID usId);
}
