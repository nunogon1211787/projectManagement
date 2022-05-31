package switch2021.project.entities.valueObjects.voFactories.voFactories;

import switch2021.project.entities.valueObjects.vos.ProjectRole;

public class IProjectRoleFactory implements switch2021.project.entities.valueObjects.voFactories.voInterfaces.IProjectRoleFactory {

        @Override
        public ProjectRole createProjectRole(String x) {
                return new ProjectRole(x);
        }
}
