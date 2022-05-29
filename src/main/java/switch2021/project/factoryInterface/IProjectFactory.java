package switch2021.project.factoryInterface;

import switch2021.project.dto.ProjectDTO;
import switch2021.project.model.Project.Project;


public interface IProjectFactory {

    Project createProject(ProjectDTO projectDTO);
}
