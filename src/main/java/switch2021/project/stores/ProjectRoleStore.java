package switch2021.project.stores;

import lombok.Getter;
import switch2021.project.model.ProjectRole;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class ProjectRoleStore {

    /*** Project Role List **/
    private List<ProjectRole> projectRoleList;


    /*** Project Role Store Constructor **/
    public ProjectRoleStore() {
        projectRoleList = new ArrayList<>();
    }

    /*** Project Role Store populator. Populates the Project Role List with pre-set objects **/

    public void populateDefault() {
        saveProjectRole(new ProjectRole("Project Manager"));
        saveProjectRole(new ProjectRole("Product Owner"));
        saveProjectRole(new ProjectRole("Scrum Master"));
        saveProjectRole(new ProjectRole("Team Member"));
    }

    /*** Create Method **/

    public ProjectRole createProjectRole(String name) {
        return new ProjectRole(name);
    }


    /*** Get Project Role by Name Methods **/

    public ProjectRole getProjectRole(String name) {
        ProjectRole projRole = null;

        for (ProjectRole i : projectRoleList) {
            if (i.getName().getText().equals(name)) {
                projRole = i;
                break;
            }
        }
        return projRole;
    }

    /*** Validation Method **/
    //Check if profile already exist
    private boolean validateProjectRole(ProjectRole role) {
        boolean msg = false;
        for (ProjectRole up : projectRoleList) {
            if (up.equals(role)) {
                msg = true;
                break;
            }
        }
        return msg;
    }

    /*** Save Project Role Method. Save a new Project Role object to the Project Role List **/
    public boolean saveProjectRole(ProjectRole role) {
        if (validateProjectRole(role)) {
            throw new IllegalArgumentException("Repeated Project Role inserted.");
        }
        return this.projectRoleList.add(role);
    }

    /*** Override Equals **/
    @Override
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
