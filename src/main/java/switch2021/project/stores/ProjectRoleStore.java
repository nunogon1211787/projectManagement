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
     * Project Role Store populator. Populates the Project Role List with pre-set objects
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
    public int idProjectRoleGenerator() {
        int id = 1;
        if (this.projectRoleList.size() > 0) {
            id = this.projectRoleList.get(projectRoleList.size() - 1).getIdRole() + 1;
        }
        return id;
    } //if the object isn´t saved on the list, the id will be the same for all
    //objects. This issue will be solved when calling the save method.


    /**
     * Add Method
     **/

    public boolean addProjectRole(ProjectRole role) {
        boolean msg = false;
        if (validateIdProjectRole(role)) {
            this.projectRoleList.add(role);
            msg = true;
        } else {
            role.setIdRole(idProjectRoleGenerator());
            this.projectRoleList.add(role);
            msg = true;
        }
        return msg;
    }

    /**
     * Get Method
     **/

    public List<ProjectRole> getProjectRolesList() {
        return this.projectRoleList;
    }

    /**
     * Get Project Role by Name Methods
     **/

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


    /**
     * Get Project Role by ID Method
     **/

    public ProjectRole getProjectRole(int id) {
        ProjectRole projRole = null;

        for (ProjectRole i : projectRoleList) {
            if (i.getIdRole() == id) {
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
            if (up.getIdRole() == (role.getIdRole())) {
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
            role.setIdRole(idProjectRoleGenerator());
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

    /**
     * Hash
     **/

    @Override
    public int hashCode() {
        return Objects.hash(projectRoleList);
    }
}
