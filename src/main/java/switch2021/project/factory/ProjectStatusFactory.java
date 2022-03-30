package switch2021.project.factory;

import switch2021.project.factoryInterface.ProjectStatusFactoryInterface;
import switch2021.project.valueObject.ProjectStatus;

public class ProjectStatusFactory implements ProjectStatusFactoryInterface {
    @Override
    public ProjectStatus createProjectStatus(String description) {
        return new ProjectStatus(description);
    }
}
