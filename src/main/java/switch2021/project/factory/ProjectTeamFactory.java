package switch2021.project.factory;

import switch2021.project.model.ProjectTeam;

public class ProjectTeamFactory implements ProjectTeamFactoryInterface {

    private ProjectTeam projectTeam;
    private ResourceFactory resFac;

    public ProjectTeam createProjectTeam() {
        return this.projectTeam = new ProjectTeam(resFac);
    }
}
