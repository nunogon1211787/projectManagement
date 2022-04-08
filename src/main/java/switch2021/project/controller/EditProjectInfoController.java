package switch2021.project.controller;

import switch2021.project.model.valueObject.Budget;
import switch2021.project.model.valueObject.Description;
import switch2021.project.model.*;
import switch2021.project.model.Project.Project;
import switch2021.project.repositories.ProjectTeam;
import switch2021.project.model.Project.ProjectStatus;
import switch2021.project.model.valueObject.SprintDuration;

import java.time.LocalDate;
import java.util.List;

public class EditProjectInfoController {

    /**
     * Attributes
     **/
    private final Company company;
    private Project project;
    List<Project> arrayProject;


    /**
     * Constructor to test (without SINGLETON)
     **/
    public EditProjectInfoController(Company company) {
        this.company = company;
    }


    /**
     * Methods
     **/
    public List<Project> getProjectList() {
        this.arrayProject = this.company.getProjectStore().getProjects();
        return arrayProject;
    }

    public Project getProjectRequested(String code) {
        this.project = this.company.getProjectStore().getProjectByCode(code);
        return this.project;
    }

    public boolean editProject(String name, String description, LocalDate startDate, LocalDate endDate, int numberOfSprints,
                               double budget, int sprintDuration, ProjectStatus status, ProjectTeam projectTeam) {

        boolean result = true;

        try {
            project.validateProjectFields(numberOfSprints);
        } catch (IllegalArgumentException e) {
            result = false;
        }

        if (result) {
            this.project.setProjectName(new Description(name));
            this.project.setDescription(new Description(description));
            this.project.setStartDate(startDate);
            this.project.setEndDate(endDate);
            this.project.setNumberOfSprints(numberOfSprints);
            this.project.setBudget(new Budget(budget));
            this.project.setProjectStatus(status);
            this.project.setSprintDuration(new SprintDuration(sprintDuration));
            this.project.setProjectTeam(projectTeam);
        }
        return result;
    }
}



