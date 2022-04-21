package switch2021.project.interfaces;

import switch2021.project.model.Project.Project;

import java.util.List;

public interface ProjectRepositoryInterface {

    /**
     * Finds all objects from this repository.
     */
    List<Project> findAllProjects();

    /**
     * The repository should be able to find a object using given Identity.
     */
    Project findProjectByID(String code);


    boolean hasProjectId(String id);

}
