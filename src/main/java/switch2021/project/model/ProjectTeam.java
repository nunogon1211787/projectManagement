package switch2021.project.model;

import java.util.ArrayList;
import java.util.List;

public class ProjectTeam {

//    Lista de Resources no Projecto

    private List<Resource> projectTeamList;

    public ProjectTeam() {
        this.projectTeamList = new ArrayList<>();
    }

    public void addResourceToTeam(Resource resource) {
        this.projectTeamList.add(resource);
    }

    public List<Resource> getProjectTeamList() {
        return projectTeamList;
    }

    public Resource getResource(Resource resource) {
        for (int i = 0; i < projectTeamList.size(); i++) {
            if (projectTeamList.get(i).equals(resource)) {
                return resource;
            }
        }
        return null;
    }

    /**
     * Validation Methods
     **/

    public boolean checkProjectRolesExists(ProjectRoles role) {
        boolean msg = true;
        for (int i = 0; i < projectTeamList.size(); i++) {
            if (projectTeamList.get(i).getProjectRoles().equals(role)) {
                msg = false;
            } else {
                msg = true;
            }
        }
        return msg;
    }
}

