package switch2021.project.applicationServices.iRepositories;

import org.springframework.stereotype.Repository;
import switch2021.project.entities.aggregates.Project.Project;

import java.util.List;

@Repository
public interface IProjectWebRepository {

    List<Project> findAll();
}
