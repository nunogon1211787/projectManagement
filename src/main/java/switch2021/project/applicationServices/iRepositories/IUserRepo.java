package switch2021.project.applicationServices.iRepositories;

import switch2021.project.entities.aggregates.User.User;
import switch2021.project.entities.valueObjects.vos.UserID;
import switch2021.project.entities.valueObjects.vos.UserProfileID;
import java.util.List;
import java.util.Optional;

public interface IUserRepo{

    Optional<User> findByUserId(UserID id);

    List<User> findAll();

    List<User> findAllByNameContains(String name);

    List<User> findAllByFunctionContains(String function);

    List<User> findAllByUserProfileId(UserProfileID profile);

    boolean existsById(UserID id);

    Optional<User> update(User user);

    Optional<User> save(User newUser);

    boolean delete(UserID userID);
}
