package switch2021.project.model;

import switch2021.project.stores.ProjectRoleStore;

import java.util.ArrayList;
import java.util.List;

public class ProjectTeam {

//    Lista de Resources no Projecto

    private List<Resource> projectTeamList;
    private ProjectRoleStore role = null;

    public ProjectTeam() {
        this.projectTeamList = new ArrayList<>();
    }

//    public ProjectTeam(Resource projectManager) {
//        this.projectTeamList = new ArrayList<>();
//        this.projectTeamList.add(projectManager);
//        projectManager.setRole(role.getProjectRoleByName("Project Manager"));
//    }

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

    public boolean checkProjectRolesExists(ProjectRole role) {
        boolean msg = true;
        for (int i = 0; i < projectTeamList.size(); i++) {
            if (projectTeamList.get(i).getRole().equals(role)) {
                msg = false;
            } else {
                msg = true;
            }
        }
        return msg;
    }
}

