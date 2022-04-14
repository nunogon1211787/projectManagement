package switch2021.project.interfaces;

import org.springframework.data.repository.CrudRepository;
import switch2021.project.model.SystemUser.SystemUser;
import switch2021.project.model.valueObject.Email;
import switch2021.project.model.valueObject.SystemUserId;

import java.util.List;
import java.util.Optional;

public interface SystemUserRepositoryInterface/* extends CrudRepository<Object, Long> */{

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
