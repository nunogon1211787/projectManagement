package switch2021.project.stores;

import lombok.Getter;
import lombok.Setter;
import switch2021.project.model.ProjectRole;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
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
        saveProjectRole(new ProjectRole("Project Manager"));
        saveProjectRole(new ProjectRole("Product Owner"));
        saveProjectRole(new ProjectRole("Scrum Master"));
        saveProjectRole(new ProjectRole("Team Member"));
    }


    /**
     * Create Method
     **/
    public ProjectRole createProjectRole(String name) {
        return new ProjectRole(name);
    }


    /**
     * ID Generator
     **/
    public int id_ProjectRoleGenerator() {
        int id = 1;
        if (this.projectRoleList.size() > 0) {
            id = this.projectRoleList.get(projectRoleList.size() - 1).getId_Role() + 1;
        }
        return id;
    } //if the object isn´t saved on the list, the id will be the same for all
    //objects. This issue will be solved when calling the save method.


    /**
     * Add Method
     **/
    private boolean addProjectRole(ProjectRole role) {
        boolean msg = false;
        if (validateIdProjectRole(role)) {
            this.projectRoleList.add(role);
            msg = true;
        } else {
            role.setId_Role(id_ProjectRoleGenerator());
            this.projectRoleList.add(role);
            msg = true;
        }
        return msg;
    }


    /**
     * Getter Methods
     **/
    //Get Project Role by Name
    public ProjectRole getProjectRole(String name) {
        ProjectRole projRole = null;

        for (ProjectRole i : projectRoleList) {
            if (i.getName().equals(name)) {
                projRole = i;
                break;
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
        boolean msg = false;

        if (validateProjectRoleExist(role)) {
            msg = true;
        }
        return msg;
    }

    //Check if profile already exist
    private boolean validateProjectRoleExist(ProjectRole role) {
        boolean msg = false;
        for (ProjectRole up : projectRoleList) {
            if (up.equals(role)) {
                msg = true;
                break;
            }
        }
        return msg;
    }

    private boolean validateIdProjectRole(ProjectRole role) {
        boolean msg = false;
        for (ProjectRole up : projectRoleList) {
            if (up.getId_Role() == (role.getId_Role())) {
                msg = true;
                break;
            }
        }
        return msg;
    }


    /**
     * Save Typology Method. Save a new Typology object to the Typology List
     **/
    public boolean saveProjectRole(ProjectRole role) {
        if (validateProjectRole(role)) {
            throw new IllegalArgumentException("Repeated Project Role inserted.");
        } else {
            role.setId_Role(id_ProjectRoleGenerator());
        }
        return addProjectRole(role);
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
