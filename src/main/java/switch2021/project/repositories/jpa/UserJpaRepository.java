package switch2021.project.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import switch2021.project.datamodel.UserJpa;
import switch2021.project.model.valueObject.SystemUserID;

public interface UserJpaRepository extends JpaRepository<UserJpa, String> {

}
