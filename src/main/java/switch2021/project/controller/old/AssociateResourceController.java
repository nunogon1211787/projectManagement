package switch2021.project.controller.old;

import switch2021.project.model.Company;
import switch2021.project.model.Project.Project;
import switch2021.project.model.SystemUser.User;
import switch2021.project.model.Resource.old.Resource;

import java.time.LocalDate;
import java.util.List;

public class AssociateResourceController {

    /**
     * Attributes
     **/
    private final Company company;
    List<Project> arrayProject;
    List<User> arrayUser;


    /**
     * Constructor to test (without SINGLETON)
     **/
    public AssociateResourceController(Company company) {
        this.company = company;
    }


    /**
     * Methods
     **/
    public List<Project> getProjectList() {
        this.arrayProject = this.company.getProjectStore().findAll();
        return arrayProject;
    }

    public List<User> getSystemUserList() {
        this.arrayUser = this.company.getSystemUserStore().findAllSystemUsers();
        return arrayUser;
    }

    public boolean associateResource(String email, String projectCode, LocalDate startDate, LocalDate endDate, double costPerHour, double percentageOfAllocation) {
        boolean msg = false;
        User user = this.company.getSystemUserStore().findByUserID(email);
        if (this.company.getProjectStore().validateAllocation(user, percentageOfAllocation, startDate, endDate)) {
            Project project = this.company.getProjectStore().findById(projectCode);
            Resource resource = project.createResource(user, startDate, endDate, costPerHour, percentageOfAllocation);
            project.addResource(resource);
            msg = true;
        }
        return msg;
    }
}
