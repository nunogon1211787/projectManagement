package switch2021.project.applicationServices.iRepositories;

import switch2021.project.entities.aggregates.Project.Project;

import java.util.List;

public interface IProjectWebRepository {

    List<Project> findAll();
}
