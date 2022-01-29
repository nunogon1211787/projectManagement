package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.Project;
import switch2021.project.model.Resource;
import switch2021.project.model.SystemUser;
import switch2021.project.utils.App;

import java.time.LocalDate;
import java.util.List;

public class AssociateResourceController {
    private Company company;
    private SystemUser user;
    private Resource resource;
    List<Project> arrayProject;
    List<SystemUser> arraySystemUser;

    /**
     * Constructor to UI (with SINGLETON).
     */
    public AssociateResourceController(){ this.company = App.getInstance().getCompany(); }

    /**
     * Constructor to test (without SINGLETON).
     */
    public AssociateResourceController(Company company){ this.company = company; }

    public List<Project> getProjectList (){
        this.arrayProject = this.company.getProjectStore().getProjects();
        return arrayProject;
    }

    public List<SystemUser> getSystemUserList (){
        this.arraySystemUser = this.company.getSystemUserStore().getSystemUsers();
        return arraySystemUser;
    }

    public boolean associateResource(String email, String projectCode, LocalDate startDate, LocalDate endDate, double costPerHour, double percentageOfAllocation){
        boolean msg = false;
        this.user = this.company.getSystemUserStore().getUserByEmail(email);
        if(this.company.getProjectStore().validateAllocation(this.user, percentageOfAllocation, startDate,endDate)) {
            Project project = this.company.getProjectStore().getProjectByCode(projectCode);
            this.resource = project.createResource(user, startDate, endDate, costPerHour, percentageOfAllocation);
            project.addResource(resource);
            msg = true;
        }
        return msg;
    }
}
