package switch2021.project.controller;

import switch2021.project.model.*;
import switch2021.project.utils.App;

import java.time.LocalDate;

public class AssignScrumMasterController {


    /**
     * Attributes
     **/
    private Company company;
    private Project project;
    private ProjectTeam projectTeam;
    private Resource resource;


    /**
     * Constructor
     **/
    public AssignScrumMasterController() {
        this.company = App.getInstance().getCompany();
    }

    public Project getProject(String code) {
        return this.project = company.getProjectStore().getProjectByCode(code);
    }

    public ProjectTeam getProjectTeam() {
        return this.projectTeam = project.getProjectTeam();
    }

    public Resource getResource(String email) {
        return this.resource = projectTeam.getResource(email);
    }

    public boolean assignRole(Resource resource, String roleName) {
        boolean msg;
        LocalDate startDateNewRole = project.getValidEndDate().plusDays(1);
        LocalDate endDateCurrentSprint = project.getValidEndDate();
        ProjectRole role = company.getProjectRolesStore().getProjectRole(roleName);

        return projectTeam.assignProjectRole(resource, startDateNewRole, endDateCurrentSprint, role);
    }
}
