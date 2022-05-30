package switch2021.project.presistence;

import org.springframework.data.jpa.repository.JpaRepository;
import switch2021.project.dataModel.jpa.UserProfileJpa;
import switch2021.project.entities.valueObjects.vos.UserProfileID;

public interface UserProfileJpaRepository extends JpaRepository<UserProfileJpa, UserProfileID> {
}