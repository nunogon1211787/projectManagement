package switch2021.project.interfaces;

import org.springframework.stereotype.Repository;
import switch2021.project.model.Project.ProjectReeng;
import java.util.List;
import java.util.Optional;

//@Repository
public interface IProjectRepo {

    /**
     * Finds all objects from this repository.
     */
    List<ProjectReeng> findAll();

    /**
     * The repository should be able to find a object using given Identity.
     */
    Optional<ProjectReeng> findById(String code);

    ProjectReeng findByIdDeprecated(String code);

    /**Check if project exists in repository**/
    boolean existsById(String id);

    boolean existsByName(String id);

    /**Save Projects Method**/
    ProjectReeng save(ProjectReeng newProject);

}
