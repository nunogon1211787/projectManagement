package switch2021.project.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import switch2021.project.datamodel.UserProfileJpa;
import switch2021.project.model.valueObject.UserProfileID;

public interface UserProfileJpaRepository extends JpaRepository<UserProfileJpa, UserProfileID> {
}