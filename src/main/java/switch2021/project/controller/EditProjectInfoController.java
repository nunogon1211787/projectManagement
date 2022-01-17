package switch2021.project.controller;

import switch2021.project.model.*;
import switch2021.project.stores.ProjectStore;

import java.time.LocalDate;
import java.util.List;


public class EditProjectInfoController {

    private Company company;
    private Project project;
    private ProjectStore projectStore;
    List<Project> arrayProject;
    private List projectTeam;


    public List getProjectList (){
        this.arrayProject = this.company.getProjectStore().getProjectList();
        return arrayProject;
    }

    public Project getProjectRequested(String code){
        this.project = this.company.getProjectStore().getProjectByCode(code);
        return this.project;
    }

    public boolean editProject(String name,String description, LocalDate startDate, LocalDate endDate, int numberOfSprints,
                               double budget, int sprintDuration, String status, List projectTeam){

        //////è preciso apanhas as excepçoes - temos de ver como
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



