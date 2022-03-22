package switch2021.project.factory;

import switch2021.project.model.ProjectStatus;

public interface ProjectStatusFactoryInterface {
    ProjectStatus createProjectStatus(String description);
}
