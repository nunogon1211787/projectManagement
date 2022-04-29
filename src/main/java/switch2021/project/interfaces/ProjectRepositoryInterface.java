package switch2021.project.interfaces;


import org.springframework.stereotype.Component;
import switch2021.project.model.Project.ProjectReeng;

import java.util.List;

@Component
public interface ProjectRepositoryInterface {

    /**
     * Finds all objects from this repository.
     */
    List<ProjectReeng> findAll();

    /**
     * The repository should be able to find a object using given Identity.
     */
    ProjectReeng findById(String code);

    /**Check if project exists in repository**/
    boolean existById(String id);

    /**Save Projects Method**/
    ProjectReeng save(ProjectReeng newProject);

}
