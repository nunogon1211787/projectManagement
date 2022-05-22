package switch2021.project.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import switch2021.project.datamodel.UserJpa;

public interface UserJpaRepository extends JpaRepository<UserJpa, String> {

}
