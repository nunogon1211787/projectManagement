package switch2021.project.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import switch2021.project.datamodel.ProjectJpa;
import switch2021.project.model.valueObject.ProjectID;

import java.util.Optional;

public interface ProjectJpaRepository extends JpaRepository<ProjectJpa, String> {
}
