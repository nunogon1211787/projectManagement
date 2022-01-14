package switch2021.project.model;

import java.util.List;

public class ProjectRolesStore {

    List<ProjectRoles> specialProfileList;

    public ProjectRolesStore() {
        specialProfileList.add(new ProjectRoles("Project Manager", "Special Profile"));
        specialProfileList.add(new ProjectRoles("Product Owner", "Special Profile"));
        specialProfileList.add(new ProjectRoles("Scrum Master", "Special Profile"));
        specialProfileList.add(new ProjectRoles("Project Team", "Special Profile"));

    }
}
