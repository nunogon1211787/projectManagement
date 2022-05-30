package switch2021.project.presistence;

import org.springframework.data.jpa.repository.JpaRepository;
import switch2021.project.dataModel.jpa.SprintJpa;
import switch2021.project.entities.valueObjects.vos.SprintID;

public interface SprintJpaRepository extends JpaRepository<SprintJpa, SprintID> {
}
