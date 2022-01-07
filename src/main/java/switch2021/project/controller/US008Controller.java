package switch2021.project.controller;

import jdk.vm.ci.meta.Local;
import switch2021.project.model.*;

import java.time.LocalDate;
import java.util.List;

public class US008Controller {

    private Company company;
    private Project project;
    private Sprint sprint;
    private ProjectStatus status;

    public US008Controller(Company company, Project project, Sprint sprint){
        this.company = company;
        this.project = project;
        this.sprint = sprint;
        this.status = status;

    }

    public Project getProjectRequested(int index){
        return this.company.getProj(index);
    }

    public Project editProject(String name, LocalDate startDate, LocalDate endDate, int numberOfSprints, int sprintDuration, String status){
        this.project.setProjectName(name);
        this.project.setStartDate(startDate);
        this.project.setEndDate(endDate);
        this.project.setNumberOfSprints(numberOfSprints);
//        this.sprint.setDuration(sprintDuration);
//        this.status.setDescription(status);
        return this.project;
    }

    public boolean saveProject(Project project){
//        this.project.saveProject(project);
        return true;
    }



}



