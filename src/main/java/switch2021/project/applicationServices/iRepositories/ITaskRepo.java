package switch2021.project.applicationServices.iRepositories;

import switch2021.project.entities.aggregates.Task.Task;


import java.util.List;
import java.util.Optional;

public interface ITaskRepo {

    /**
     * Finds all objects from this repository.
     */
    List<Task> findAll();

    /**
     * The repository should be able to find a object using given Identity.
     */
    Task findById(String code);

    Optional<Task> save (Task newTask);


    boolean existById(String id);

}
