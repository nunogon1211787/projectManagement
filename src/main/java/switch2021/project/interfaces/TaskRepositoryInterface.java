package switch2021.project.interfaces;

import org.springframework.stereotype.Component;
import switch2021.project.model.Project.Project;

import java.util.List;

@Component
public interface TaskRepositoryInterface {


    /**
     * Finds all objects from this repository.
     */
    List<Project> findAll();

    /**
     * The repository should be able to find a object using given Identity.
     */
    Project findById(String id);


    boolean existById(String id);

}
