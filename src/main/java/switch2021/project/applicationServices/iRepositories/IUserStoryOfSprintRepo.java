package switch2021.project.applicationServices.iRepositories;

import switch2021.project.entities.valueObjects.vos.SprintID;
import switch2021.project.entities.valueObjects.vos.UserStoryID;
import switch2021.project.entities.valueObjects.vos.UserStoryOfSprint;

import java.util.List;
import java.util.Optional;

public interface IUserStoryOfSprintRepo {

    List<UserStoryOfSprint> findAllUserStoryOfSprint();

    Optional<UserStoryOfSprint> findByUserStoryByUsId(UserStoryID userStoryID);

    List<UserStoryOfSprint> findAllUserStoriesBySprintID(SprintID sprintID);

    Optional<UserStoryOfSprint> save(UserStoryOfSprint userStoryOfSprint);

    boolean deleteUserStoryOfSprint(UserStoryID userStoryID);

}