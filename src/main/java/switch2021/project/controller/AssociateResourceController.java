package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.Project;
import switch2021.project.model.Resource;
import switch2021.project.model.SystemUser;

import java.time.LocalDate;
import java.util.List;

public class AssociateResourceController {
    private Company company;
    private SystemUser user;
    private Project project;
    private Resource resource;
    List<Project> arrayProject;
    List<SystemUser> arraySystemUser;


    public AssociateResourceController(Company company, Project project){
        this.company = company;
        this.project = project;
        this.user = null;
        this.resource = null;
    }

    public List getProjectList (){
        this.arrayProject = this.company.getProjectStore().getProjectList();
        return arrayProject;
    }

    public List getSystemUserList (){
        this.arraySystemUser = this.company.getSystemUserStore().getSystemUserList();
        return arraySystemUser;
    }

    public boolean associateResource(String email, String projectCode, LocalDate startDate, LocalDate endDate, double costPerHour, double percentageOfAllocation){
        boolean msg = false;
        this.user = this.company.getSystemUserStore().getUserByEmail(email);
        if(this.company.getProjectStore().validateAllocation(this.user, percentageOfAllocation, startDate,endDate)) {
            this.company.getProjectStore().getProjectByCode(projectCode);
            this.resource = this.project.createResource(user, startDate, endDate, costPerHour, percentageOfAllocation);
            this.project.addResource(resource);
            msg = true;
        }
        return msg;
    }
}
