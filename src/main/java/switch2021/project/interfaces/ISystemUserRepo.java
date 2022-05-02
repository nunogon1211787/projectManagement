package switch2021.project.interfaces;

import switch2021.project.model.SystemUser.SystemUser;

import java.util.List;

//@Component
public interface ISystemUserRepo/* extends CrudRepository<Object, Long> */{

    SystemUser findSystemUserByEmail(String email);

    List<SystemUser> findAllSystemUsers();

    boolean saveSystemUser(SystemUser user);

    boolean existsByEmail(String email);
}
