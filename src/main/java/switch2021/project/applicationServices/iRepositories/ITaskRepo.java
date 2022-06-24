package switch2021.project.applicationServices.iRepositories;

import switch2021.project.entities.aggregates.Task.Task;
import switch2021.project.entities.valueObjects.vos.Description;
import switch2021.project.entities.valueObjects.vos.TaskID;
import java.util.List;
import java.util.Optional;


public interface ITaskRepo {

    List<Task> findAll();

    Optional<Task> findById(Description taskName);

    Task save (Task newTask);

    boolean existsById(Description taskName);
}
