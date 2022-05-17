package switch2021.project.interfaces;

import switch2021.project.model.SystemUser.User;
import switch2021.project.model.valueObject.UserProfileID;

import java.util.List;

public interface IUserRepo/* extends CrudRepository<Object, Long> */{

    User findByUserID(String email);

    List<User> findAllSystemUsers();

    boolean save(User user);

    boolean existsByEmail(String email);

    List<User> findAllBySystemUserIdContains(String id);

    List<User> findAllByNameContains(String name);

    List<User> findAllByFunctionContains(String function);

    List<User> findAllByUserProfileId(UserProfileID profile);
}
