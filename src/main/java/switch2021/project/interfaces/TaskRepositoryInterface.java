package switch2021.project.interfaces;

import org.springframework.stereotype.Component;
import switch2021.project.model.Task.TaskReeng;


import java.util.List;

@Component
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
