package switch2021.project.factory;

import switch2021.project.model.ProjectRole;

public class ProjectRoleFactory {


        public ProjectRole createProjectRole (String x){
                return new ProjectRole(x);
        }
}
