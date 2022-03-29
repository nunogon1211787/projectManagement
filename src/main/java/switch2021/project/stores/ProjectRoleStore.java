package switch2021.project.stores;

import lombok.Getter;
import switch2021.project.factoryInterface.ProjectRoleFactoryInterface;
import switch2021.project.model.ProjectRole.ProjectRole;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ProjectRoleStore {

    /**
     * Attribute
     */
    private final ProjectRoleFactoryInterface projectRoleFactory;
    private final List<ProjectRole> projectRoleList = new ArrayList<>();


    /**
     * Constructor
     */
    public ProjectRoleStore(ProjectRoleFactoryInterface factory){
        this.projectRoleFactory = factory;
    }


    /**
     * Populate default method
     */
    public void populateDefault() {
        createAndAddProjectRole("Project Manager");
        createAndAddProjectRole("Product Owner");
        createAndAddProjectRole("Scrum Master");
        createAndAddProjectRole("Team Member");
    }

    /**
     * Create and Add method
     */
    public boolean createAndAddProjectRole(String title) {
        if( getProjectRole(title) != null ) {
            throw new IllegalArgumentException("Title already exist.");
        }
        ProjectRole x = this.projectRoleFactory.createProjectRole(title);
         this.projectRoleList.add(x);
        return true;
    }

    /**
     * Get Project Role method
     */
    public ProjectRole getProjectRole(String title ) {
        for (ProjectRole projectRole : projectRoleList) {
            if(projectRole.getName().getText().equals(title) )
                return projectRole;
        }
        return null;
    }
}
