package switch2021.project.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import switch2021.project.datamodel.Task.TaskJpa;
import switch2021.project.model.Task.TaskID;

public interface TaskJpaRepository extends JpaRepository<TaskJpa, TaskID> {
}
