package switch2021.project.controller;

import switch2021.project.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class EditProjectInfoController {

    private Company company;
    private Project project;
    private ProjectStore projectStore;
    private String code;
    List<Project> arrayProject;
    private List projectTeam;


    public EditProjectInfoController(Company company, Project project){
        this.company = company;
        this.project = project;
    }

    public List getProjectList (){
        this.arrayProject = this.company.getProjectStore().getArrayProject();
        return arrayProject;
    }

    public Project getProjectRequested(String code){
        this.code = code;
        return this.project = this.company.getProjectStore().getProject(code);
    }

    public Project editProject(String name, LocalDate startDate, LocalDate endDate, int numberOfSprints, int sprintDuration, String status, List projectTeam){
        this.project.setProjectName(name);
        this.project.setStartDate(startDate);
        this.project.setEndDate(endDate);
        this.project.setNumberOfSprints(numberOfSprints);
        this.project.setProjectStatus(status);
        this.project.setSprintDuration(sprintDuration);
        this.project.setProjectTeam(projectTeam);
        return this.project;
    }

    public boolean saveProject(Project proj){
        this.company.getProjectStore().saveProject(proj, this.code);
        return true;
    }



}



