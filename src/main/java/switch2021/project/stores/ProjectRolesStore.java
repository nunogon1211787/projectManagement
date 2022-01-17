package switch2021.project.stores;

import switch2021.project.model.ProjectRoles;

import java.util.ArrayList;
import java.util.List;

public class ProjectRolesStore {

    List<ProjectRoles> projectRolesList;

    public ProjectRolesStore(){ projectRolesList = new ArrayList<>();}

    public void populateDefault() {
        projectRolesList.add(new ProjectRoles(idGenerator(),"Project Manager"));
        projectRolesList.add(new ProjectRoles(idGenerator(),"Product Owner"));
        projectRolesList.add(new ProjectRoles(idGenerator(),"Scrum Master"));
        projectRolesList.add(new ProjectRoles(idGenerator(),"Team Member"));
    }

    /**
     * Create Method
     **/

    public ProjectRoles createProjectRole(String name) {
        int id = idGenerator();
        return new ProjectRoles(id, name);
    }

    /**
     * ID Generator
     */
    public int idGenerator () {
        int id = getProjectRolesList().size();
        return id;
    }

    /**
     * Add Method
     **/

    public boolean addProjectRole(ProjectRoles role) {
        boolean msg = false;
        if (!validateProjectRole(role)) {
            msg = false;
        } else if (validateIdProjectRole(role)){
            this.projectRolesList.add(role);
            msg = true;
        } else{
            role.setId_Role(idGenerator());
            this.projectRolesList.add(role);
            msg = true;
        }
        return msg;
    }

    /**
     * Getter Methods
     **/

    public List<ProjectRoles> getProjectRolesList() {
        return this.projectRolesList;
    }


    public ProjectRoles getProjectRoleByName(String projectRoleName) {
        int result = -1;

        for (int i = 0; i < projectRolesList.size(); i++) {
            if (projectRolesList.get(i).getName().equals(projectRoleName)) {
                result = i;
            }
        }
        return projectRolesList.get(result);
    }

    /**
     * Validation Method
     **/

    private boolean validateProjectRole(ProjectRoles role) {
        //Check empty fields on name and type
        if (role.getName().trim().isEmpty()) {
            return false;
        }

        //Check if profile already exist
        for (ProjectRoles up : projectRolesList) {
            if (up.equals(role)) {
                return false;
            }
        }
        return true;
    }

    private boolean validateIdProjectRole(ProjectRoles roles){
        boolean msg = false;
        for(ProjectRoles up : projectRolesList) {
            if(up.getId_Role() == (roles.getId_Role())){
                msg = true;
            }
        }
        return msg;
    }

    /**
     * Override Equals
     **/

    public boolean equals(Object obj){
        if (this == obj) return true;
        if (!(obj instanceof ProjectRolesStore)) return false;
        ProjectRolesStore that = (ProjectRolesStore) obj;
        return
                (this.projectRolesList.equals(that.getProjectRolesList()));
    }
}
