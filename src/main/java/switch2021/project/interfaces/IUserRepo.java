package switch2021.project.interfaces;

import switch2021.project.model.SystemUser.User;
import switch2021.project.model.valueObject.UserProfileID;
import java.util.List;
import java.util.Optional;

public interface IUserRepo{

    Optional<User> findUserById(String id);

    List<User> findAll();

    boolean save(User user); //Delete when nor needed (must return an Optional)

    boolean existsByEmail(String email);

    List<User> findAllBySystemUserIdContains(String id);

    List<User> findAllByNameContains(String name);

    List<User> findAllByFunctionContains(String function);

    List<User> findAllByUserProfileId(UserProfileID profile);

    Optional<User> saveReeng(User newUser);

    boolean delete(String systemUserID);
}
