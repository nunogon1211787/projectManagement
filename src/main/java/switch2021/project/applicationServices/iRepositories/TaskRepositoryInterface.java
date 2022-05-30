package switch2021.project.applicationServices.iRepositories;

import switch2021.project.entities.aggregates.Task.TaskReeng;


import java.util.List;

public interface TaskRepositoryInterface {

    /**
     * Finds all objects from this repository.
     */
    List<TaskReeng> findAll();

    /**
     * The repository should be able to find a object using given Identity.
     */
    TaskReeng findById(String code);

    TaskReeng save (TaskReeng taskReeng);


    boolean existById(String id);

}
