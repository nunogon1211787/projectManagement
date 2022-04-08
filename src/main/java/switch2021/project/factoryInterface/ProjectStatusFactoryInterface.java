package switch2021.project.factoryInterface;

import switch2021.project.model.Project.ProjectStatus;

public interface ProjectStatusFactoryInterface {
    ProjectStatus createProjectStatus(String description);
}
