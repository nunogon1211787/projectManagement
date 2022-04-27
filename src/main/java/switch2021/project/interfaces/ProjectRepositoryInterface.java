package switch2021.project.interfaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import switch2021.project.model.Project.Project;

import java.util.List;

@Component
public interface ProjectRepositoryInterface {

    /**
     * Finds all objects from this repository.
     */
    List<Project> findAll();

    /**
     * The repository should be able to find a object using given Identity.
     */
    Project findById(String code);


    boolean existById(String id);

}
