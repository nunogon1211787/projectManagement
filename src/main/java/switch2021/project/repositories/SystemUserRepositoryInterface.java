package switch2021.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import switch2021.project.repositories.jpa.SystemUserJpa;

@Repository
public interface SystemUserRepositoryInterface extends JpaRepository<SystemUserJpa, Long> {
}
