package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.Project;
import switch2021.project.model.Resource;
import switch2021.project.model.SystemUser;

import java.time.LocalDate;
import java.util.List;

public class AssociateResourceController {

    /**
     * Attributes
     **/

    private final Company company;
    List<Project> arrayProject;
    List<SystemUser> arraySystemUser;

    /**
     * Constructor to test (without SINGLETON)
     **/

    public AssociateResourceController(Company company){ this.company = company; }

    /**
     * Methods
     **/

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
        SystemUser user = this.company.getSystemUserStore().getUserByEmail(email);
        if(this.company.getProjectStore().validateAllocation(user, percentageOfAllocation, startDate,endDate)) {
            Project project = this.company.getProjectStore().getProjectByCode(projectCode);
            Resource resource = project.createResource(user, startDate, endDate, costPerHour, percentageOfAllocation);
            project.addResource(resource);
            msg = true;
        }
        return msg;
    }
}
