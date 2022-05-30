package switch2021.project.entities.valueObjects.vos.enums;

import java.util.ArrayList;
import java.util.List;

public enum ProjectRoleReeng {

    ProjectManager,
    ProductOwner,
    ScrumMaster,
    TeamMember;

    public static List<String> getProjectRole() {
        ProjectRoleReeng[] projectRolesValues = ProjectRoleReeng.values();
        List<String> projectRoles = new ArrayList<>();

        for (ProjectRoleReeng projectRoleEnum : projectRolesValues) {
            projectRoles.add(projectRoleEnum.toString());
        }
        return projectRoles;
    }

}
