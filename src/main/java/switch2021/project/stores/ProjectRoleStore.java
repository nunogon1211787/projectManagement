package switch2021.project.stores;

import switch2021.project.factory.ProjectRoleFactory;
import switch2021.project.model.ProjectRole;

import java.util.ArrayList;
import java.util.List;

public class ProjectRoleStore {

    private final ProjectRoleFactory projectRoleFactory;

    private final List<ProjectRole> projectRoleList = new ArrayList<>();

    public ProjectRoleStore(ProjectRoleFactory factory){
        this.projectRoleFactory = factory;
    }

    public boolean createAndAddProjectRole( String title) {

        if( getProjectRole(title) != null )
            throw new IllegalArgumentException("Título já existente.");

        ProjectRole newProjectRole = this.projectRoleFactory.createProjectRole(title);

        return this.projectRoleList.add( newProjectRole );
    }

    public ProjectRole getProjectRole(String title ) {
        for (ProjectRole projectRole : projectRoleList) {
            if(projectRole.getName().getText().equals(title) )
                return projectRole;
        }

        return null;
    }

    public void populateDefault() {
        createAndAddProjectRole("Project Manager");
        createAndAddProjectRole("Product Owner");
        createAndAddProjectRole("Scrum Master");
        createAndAddProjectRole("Team Member");

    }

    public List<ProjectRole> getProjectRoleList() {
        return projectRoleList;
    }
}
