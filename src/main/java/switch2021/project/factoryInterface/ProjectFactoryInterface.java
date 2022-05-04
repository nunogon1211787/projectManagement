package switch2021.project.factoryInterface;

import org.springframework.stereotype.Component;
import switch2021.project.dto.ProjectDTO;
import switch2021.project.model.Project.ProjectReeng;


public interface ProjectFactoryInterface {

    ProjectReeng createProject(ProjectDTO projectDTO, int nextId);
}
