package switch2021.project.applicationServices.iRepositories;

import switch2021.project.entities.aggregates.Task.Task;


import java.util.List;

public interface TaskRepositoryInterface {

    /**
     * Finds all objects from this repository.
     */
    List<Task> findAll();

    /**
     * The repository should be able to find a object using given Identity.
     */
    Task findById(String code);

    Task save (Task task);


    boolean existById(String id);

}
