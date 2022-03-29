package switch2021.project.controller;

import switch2021.project.dto.ProjectDTO;
import switch2021.project.dto.ResourceDTO;
import switch2021.project.mapper.ProjectTeamMapper;
import switch2021.project.mapper.ProjectsMapper;
import switch2021.project.model.*;
import switch2021.project.model.Project.Project;
import switch2021.project.model.Project.Resource;
import switch2021.project.model.ProjectRole.ProjectRole;

import java.time.LocalDate;

public class AssignProductOwnerController {

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
    public AssignProductOwnerController(Company company, ProjectsMapper projectsMapper, ProjectTeamMapper projectTeamMapper) {
        this.company = company;
        this.projectTeamMapper = projectTeamMapper;
        this.projectsMapper = projectsMapper;
    }


    /**
     * Method to receive a project and then send to UI
     **/
    public ProjectDTO getProject(String code) {
        this.project = company.getProjectStore().getProjectByCode(code);
        return projectsMapper.toDTO(project);
    }


    /**
     * Method to receive a resource of the project and send to UI
     */
    public ResourceDTO getResource(String name) {
        this.resource = project.getProjectTeam().getResourceByName(name);
        return projectTeamMapper.toDto(resource);
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
