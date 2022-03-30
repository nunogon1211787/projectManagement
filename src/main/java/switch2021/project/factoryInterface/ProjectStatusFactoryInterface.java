package switch2021.project.factoryInterface;

import switch2021.project.valueObject.ProjectStatus;

public interface ProjectStatusFactoryInterface {
    ProjectStatus createProjectStatus(String description);
}
