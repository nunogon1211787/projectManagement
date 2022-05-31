package switch2021.project.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import switch2021.project.dataModel.jpa.ProjectJpa;

public interface ProjectJpaRepository extends JpaRepository<ProjectJpa, String> {
}
