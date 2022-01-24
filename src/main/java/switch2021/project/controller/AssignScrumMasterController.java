package switch2021.project.controller;

import switch2021.project.model.*;
import switch2021.project.utils.App;

import java.time.LocalDate;
import java.util.List;

public class AssignScrumMasterController {


    /**
     * Attributes
     **/
    private Company company;
    private Project project;
    private List<Resource> projectTeamList;
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

    public List<Resource> getProjectTeamList() {return this.projectTeamList = project.getProjectTeam().getProjectTeamList();}

    public Resource getResource(String email) {return this.resource = project.getProjectTeam().getResource(email);}

    public boolean assignRole(Resource resource, String roleName) {
        boolean msg;
        LocalDate startDateNextSprint = project.getSprintStore().getCurrentSprint().getEndDate().plusDays(1);
        int sprintDuration = project.getSprintDuration();
        ProjectRole role = company.getProjectRoleStore().getProjectRole(roleName);

        return project.getProjectTeam().assignProjectRole(resource, startDateNextSprint, sprintDuration, role);
    }
}
