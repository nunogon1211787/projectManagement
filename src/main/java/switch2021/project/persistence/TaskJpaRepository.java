package switch2021.project.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import switch2021.project.dataModel.jpa.TaskJpa;
import switch2021.project.entities.valueObjects.vos.TaskID;


public interface TaskJpaRepository extends JpaRepository<TaskJpa, TaskID> {
}
