package switch2021.project.interfaces;

import switch2021.project.model.SystemUser.SystemUser;
import switch2021.project.model.valueObject.UserProfileID;

import java.util.Collection;
import java.util.List;

public interface ISystemUserRepo/* extends CrudRepository<Object, Long> */{

    SystemUser findSystemUserByEmail(String email);

    List<SystemUser> findAllSystemUsers();

    boolean saveSystemUser(SystemUser user);

    boolean existsByEmail(String email);

    List<SystemUser> findAllBySystemUserIdContains(String id);

    List<SystemUser> findAllByNameContains(String name);

    List<SystemUser> findAllByFunctionContains(String function);

    List<SystemUser> findAllByUserProfileId(UserProfileID profile);
}
