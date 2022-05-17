package switch2021.project.factoryInterface;

import switch2021.project.dto.ProjectDTO;
import switch2021.project.model.Project.ProjectReeng;


public interface IProjectFactory {

    ProjectReeng createProject(ProjectDTO projectDTO);
}
