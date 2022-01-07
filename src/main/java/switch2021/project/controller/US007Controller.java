package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.Project;
import switch2021.project.model.Resource;
import switch2021.project.model.SystemUser;

import java.time.LocalDate;

public class US007Controller {
    private Company company;
    private SystemUser user;
    private Project project;
    private Resource resource;


    public US007Controller(Company company, Project project){
        this.company = company;
        this.project = project;
        this.user = null;
        this.resource = null;
    }

    public boolean associateResource(String email, String projectCode, LocalDate startDate, LocalDate endDate, double costPerHour, double percentageOfAllocation){
        this.user = this.company.getUserByEmail(email);
        this.company.searchProject(projectCode);
        //this.company.validateAllocation(user, percentageOfAllocation,startDate, endDate);
        this.resource = this.project.createResource(user, startDate, endDate, costPerHour, percentageOfAllocation);
        this.project.addResource(resource);
        return true;
    }
}
