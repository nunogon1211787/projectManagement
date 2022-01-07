package switch2021.project.controller;

import switch2021.project.model.*;

import java.time.LocalDate;


public class US008Controller {

    private Company company;
    private Project project;

    public US008Controller(Company company, Project project){
        this.company = company;
        this.project = project;
    }

    public Project getProjectRequested(int index){
        return this.company.getProjByIndex(index);
    }

    public Project editProject(String name, LocalDate startDate, LocalDate endDate, int numberOfSprints, int sprintDuration, String status, SystemUser scrumMaster, SystemUser productOwner){
        this.project.setProjectName(name);
        this.project.setStartDate(startDate);
        this.project.setEndDate(endDate);
        this.project.setNumberOfSprints(numberOfSprints);
        this.project.setProjectStatus(status);
        this.project.setSprintDuration(sprintDuration);
        this.project.setScrumMaster(scrumMaster);
        this.project.setProductOwner(productOwner);
        return this.project;
    }

    public boolean saveProject(Project project){
        this.company.saveProject(project);
        return true;
    }



}



