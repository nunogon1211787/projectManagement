package switch2021.project.factory;

import switch2021.project.factoryInterface.ProjectRoleFactoryInterface;
import switch2021.project.model.ProjectRole;

public class ProjectRoleFactory implements ProjectRoleFactoryInterface {

        @Override
        public ProjectRole createProjectRole(String x) {

                return new ProjectRole(x);
        }
}
