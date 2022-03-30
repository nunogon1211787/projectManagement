package switch2021.project.controller;

import switch2021.project.dto.ProjectDTO;
import switch2021.project.dto.ResourceDTO;
import switch2021.project.valueObject.ProjectRole;
import switch2021.project.mapper.ProjectTeamMapper;
import switch2021.project.mapper.ProjectsMapper;
import switch2021.project.model.*;
import switch2021.project.model.Project.Project;
import switch2021.project.valueObject.Resource;

import java.time.LocalDate;

public class AssignScrumMasterController {

    /**
     * Attributes
     **/
    private final Company company;
    private final ProjectsMapper projectsMapper;
    private final ProjectTeamMapper projectTeamMapper;
    private Project project;
    private Resource resource;


    /**
     * Constructor to test (without SINGLETON)
     **/
    public AssignScrumMasterController(Company company, ProjectsMapper projectsMapper, ProjectTeamMapper projectTeamMapper) {
        this.company = company;
        this.projectTeamMapper = projectTeamMapper;
        this.projectsMapper = projectsMapper;
    }


    /**
     * Method to receive a project and then send to UI
     **/
    public ProjectDTO getProject(String code) {
        this.project = company.getProjectStore().getProjectByCode(code);
        return this.projectsMapper.toDTO(project);
    }


    /**
     * Method to receive a resource of the project and send to UI
     */
    public ResourceDTO getResource(String email) {
        this.resource = project.getProjectTeam().getResourceByEmail(email);
        return this.projectTeamMapper.toDto(resource);
    }


    /**
     * Method to define a new role, to a resource, in the project that it belong
     **/
    public boolean assignRole(String name, String roleName) {
        boolean msg = false;

        LocalDate startDateNextSprint = project.getSprintList().getCurrentSprint().getEndDate().plusDays(1);
        int sprintDuration = project.getSprintDuration();
        ProjectRole role = company.getProjectRoleStore().getProjectRole(roleName);
        if (this.resource.getUser().getUserName().getNameF().equals(name)
                && project.getProjectTeam().assignProjectRole(resource, startDateNextSprint, sprintDuration, role)) {
            msg = true;
        }
        return msg;
    }
}
