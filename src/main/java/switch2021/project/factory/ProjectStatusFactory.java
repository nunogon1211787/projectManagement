package switch2021.project.factory;

import switch2021.project.model.ProjectStatus;

public class ProjectStatusFactory {

    public ProjectStatus createProjectStatus(String description){
        return new ProjectStatus(description);
    }
}
