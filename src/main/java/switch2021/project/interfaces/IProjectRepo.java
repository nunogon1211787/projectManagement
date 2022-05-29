package switch2021.project.interfaces;

import switch2021.project.model.Project.Project;
import switch2021.project.model.valueObject.ProjectID;

import java.util.List;
import java.util.Optional;


public interface IProjectRepo {

    /**
     * Finds all objects from this repository.
     */
    List<Project> findAll();

    /**
     * The repository should be able to find a object using given Identity.
     */
    Optional<Project> findById(String id);

    /**Check if project exists in repository**/
    boolean existsById(ProjectID id);

    /**Save Projects Method**/
   Optional<Project> save(Project newProject);

    boolean deleteByProjectID(String id);

}
