package switch2021.project.controller;

import switch2021.project.model.*;
import switch2021.project.utils.App;
import java.time.LocalDate;
import java.util.List;

public class AssignScrumMasterController {

    /**
     * Attributes
     **/
    private final Company company;
    private Project project;
    private List<Resource> projectTeamList;
    private Resource resource;

    /**
     * Constructor to UI (with SINGLETON).
     **/
    public AssignScrumMasterController() {
        this.company = App.getInstance().getCompany();
    }

    /**
     * Constructor to test (without SINGLETON).
     */
    public AssignScrumMasterController(Company company) {
        this.company = company;
    }


    /**
     * Method to receive a project and than send to UI
     */
    public Project getProject(String code) {
        this.project = company.getProjectStore().getProjectByCode(code);
        return this.project;
    }

    /**
     * Method to receive a project team list (resource list of the project) and send to UI
     */
    public List<Resource> getProjectTeamList() {
        this.projectTeamList = project.getProjectTeam().getProjectTeamList();
        return this.projectTeamList;
    }

    /**
     * Method to receive a resource of the project and send to UI.
     */
    public Resource getResource(String email) {
        this.resource = project.getProjectTeam().getResource(email);
        return this.resource;
    }

    /**
     * Method to define a new role to a resource in the project that it belong.
     */
    public boolean assignRole(String email, String roleName) {
        boolean msg = false;

        LocalDate startDateNextSprint = project.getSprints().getCurrentSprint().getEndDate().plusDays(1);
        int sprintDuration = project.getSprintDuration();
        ProjectRole role = company.getProjectRoleStore().getProjectRole(roleName);
        if (this.resource == getResource(email)) {
            if(project.getProjectTeam().assignProjectRole(resource, startDateNextSprint, sprintDuration, role)) {
                msg = true;
            }
        }
        return msg;
    }
}
