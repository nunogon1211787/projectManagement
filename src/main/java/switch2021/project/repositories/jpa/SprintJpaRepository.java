package switch2021.project.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import switch2021.project.datamodel.SprintJpa;
import switch2021.project.model.valueObject.SprintID;

public interface SprintJpaRepository extends JpaRepository<SprintJpa, SprintID> {
}
