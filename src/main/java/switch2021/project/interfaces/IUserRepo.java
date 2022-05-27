package switch2021.project.interfaces;

import switch2021.project.model.SystemUser.User;
import switch2021.project.model.valueObject.SystemUserID;
import switch2021.project.model.valueObject.UserProfileID;
import java.util.List;
import java.util.Optional;

public interface IUserRepo{

    Optional<User> findByUserId(SystemUserID id);

    List<User> findAll();

    boolean existsById(SystemUserID id);

    List<User> findAllBySystemUserIdContains(String id);

    List<User> findAllByNameContains(String name);

    List<User> findAllByFunctionContains(String function);

    List<User> findAllByUserProfileId(UserProfileID profile);

    Optional<User> save(User newUser);

    boolean delete(SystemUserID systemUserID);
}
