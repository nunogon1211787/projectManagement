package switch2021.project.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import switch2021.project.dataModel.Task.TaskIDJpa;
import switch2021.project.dataModel.Task.TaskJpa;


public interface TaskJpaRepository extends JpaRepository<TaskJpa, TaskIDJpa> {
}
