package switch2021.project.controller;

import switch2021.project.model.*;
import switch2021.project.utils.App;

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
     * Constructor to UI (with SINGLETON)
     **/

    public EditProjectInfoController(){ this.company = App.getInstance().getCompany(); }

    /**
     * Constructor to test (without SINGLETON).
     **/

    public EditProjectInfoController(Company company){ this.company = company; }


    /**
     * Methods
     **/

    public List<Project> getProjectList (){
        this.arrayProject = this.company.getProjectStore().getProjects();
        return arrayProject;
    }

    public Project getProjectRequested(String code){
        this.project = this.company.getProjectStore().getProjectByCode(code);
        return this.project;
    }

    public boolean editProject(String name,String description, LocalDate startDate, LocalDate endDate, int numberOfSprints,
                               double budget, int sprintDuration, ProjectStatus status, ProjectTeam projectTeam){

        project.validateProjectFields(name,description,budget,numberOfSprints);

        this.project.setProjectName(name);
        this.project.setDescription(description);
        this.project.setStartDate(startDate);
        this.project.setEndDate(endDate);
        this.project.setNumberOfSprints(numberOfSprints);
        this.project.setBudget(budget);
        this.project.setProjectStatus(status);
        this.project.setSprintDuration(sprintDuration);
        this.project.setProjectTeam(projectTeam);
        return true;
    }
}



