package switch2021.project.presistence;

import org.springframework.data.jpa.repository.JpaRepository;
import switch2021.project.dataModel.jpa.UserJpa;
import switch2021.project.entities.valueObjects.vos.UserID;

public interface UserJpaRepository extends JpaRepository<UserJpa, UserID> {

}
