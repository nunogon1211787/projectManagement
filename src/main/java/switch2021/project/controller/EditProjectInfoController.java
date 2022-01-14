package switch2021.project.controller;

import switch2021.project.model.*;

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
        this.project = this.company.getProjectStore().getProject(code);
        return this.project ;
    }

    public Project editProject(String name, LocalDate startDate, LocalDate endDate, int numberOfSprints,
                               int sprintDuration, String status, List projectTeam){
        this.project.setProjectName(name);
        this.project.setStartDate(startDate);
        this.project.setEndDate(endDate);
        this.project.setNumberOfSprints(numberOfSprints);
        this.project.setProjectStatus(status);
        this.project.setSprintDuration(sprintDuration);
        this.project.setProjectTeam(projectTeam);
        return this.project;
    }

    public boolean validateProject(Project proj){
        return this.projectStore.validateProject(proj);
    }




}



