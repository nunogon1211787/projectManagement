package switch2021.project.interfaces;

import switch2021.project.model.SystemUser.SystemUser;

import java.util.List;

//@Component
public interface ISystemUserRepo/* extends CrudRepository<Object, Long> */{

    /**
     * The repository should be able to find a object using given Identity.
     */
    SystemUser findSystemUserByEmail(String email);

    /**
     * Finds all objects from this repository.
     */
    List<SystemUser> findAllSystemUsers();

    /**
     * Save a object in the list of the repository.
     */
    boolean saveSystemUser(SystemUser user);

    boolean existsByEmail(String email);

}
