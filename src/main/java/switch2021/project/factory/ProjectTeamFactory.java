package switch2021.project.factory;

import switch2021.project.factoryInterface.ProjectTeamFactoryInterface;
import switch2021.project.factoryInterface.IResourceFactory;
import switch2021.project.repositories.old.ProjectTeam;

public class ProjectTeamFactory implements ProjectTeamFactoryInterface {

    private ProjectTeam projectTeam;
    private IResourceFactory resFac;

    public ProjectTeam createProjectTeam() {
        return this.projectTeam = new ProjectTeam(resFac);
    }
}
