package switch2021.project.stores;

import switch2021.project.model.ProjectRole;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProjectRoleStore {

    /**
     * Project Role List
     **/
    private List<ProjectRole> projectRoleList;


    /**
     * Project Role Store Constructor
     **/
    public ProjectRoleStore() {
        projectRoleList = new ArrayList<>();
    }


    /**
     * Project Role Store populator. Populates the Project Role List with pre-set objects.
     **/
    public void populateDefault() {
        projectRoleList.add(new ProjectRole(idGenerator(), "Project Manager"));
        projectRoleList.add(new ProjectRole(idGenerator(), "Product Owner"));
        projectRoleList.add(new ProjectRole(idGenerator(), "Scrum Master"));
        projectRoleList.add(new ProjectRole(idGenerator(), "Team Member"));
    }


    /**
     * Create Method
     **/
    public ProjectRole createProjectRole(String name) {
        int id = idGenerator();
        return new ProjectRole(id, name);
    }


    /**
     * ID Generator
     **/
    public int idGenerator() {
        return getProjectRolesList().size();
    }


    /**
     * Add Method
     **/
    public boolean addProjectRole(ProjectRole role) {
        boolean msg = false;
        if (validateIdProjectRole(role)) {
            this.projectRoleList.add(role);
            msg = true;
        } else {
            role.setId_Role(idGenerator());
            this.projectRoleList.add(role);
            msg = true;
        }
        return msg;
    }


    /**
     * Getter Methods
     **/
    public List<ProjectRole> getProjectRolesList() {
        return this.projectRoleList;
    }

    //Get Project Role by Name
    public ProjectRole getProjectRole(String name) {
        ProjectRole projRole = null;

        for (ProjectRole i : projectRoleList) {
            if (i.getName().equals(name)) {
                projRole = i;
            }
        }
        return projRole;
    }

    //Get Project Role by ID
    public ProjectRole getProjectRole(int id) {
        ProjectRole projRole = null;

        for (ProjectRole i : projectRoleList) {
            if (i.getId_Role() == id) {
                projRole = i;
            }
        }
        return projRole;
    }


    /**
     * Validation Method
     **/
    private boolean validateProjectRole(ProjectRole role) {
        //Check empty fields on name and type
        if (role.getName().trim().isEmpty()) {
            return false;
        }

        //Check if profile already exist
        for (ProjectRole up : projectRoleList) {
            if (up.equals(role)) {
                return false;
            }
        }
        return true;
    }

    private boolean validateIdProjectRole(ProjectRole roles) {
        boolean msg = false;
        for (ProjectRole up : projectRoleList) {
            if (up.getId_Role() == (roles.getId_Role())) {
                msg = true;
                break;
            }
        }
        return msg;
    }


    /**
     * Override Equals
     **/
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ProjectRoleStore)) return false;
        ProjectRoleStore that = (ProjectRoleStore) obj;
        return
                (this.projectRoleList.equals(that.projectRoleList));
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectRoleList);
    }
}
