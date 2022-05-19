package switch2021.project.controller.old;

import switch2021.project.model.Company;
import switch2021.project.model.Project.Project;
import switch2021.project.repositories.old.ProjectStore;

import java.util.Collections;
import java.util.List;

public class GetCurrentProjectListController {

    /**
     * Attributes
     **/

    private final Company company;


    /**
     * Constructor to test (without SINGLETON)
     **/

    public GetCurrentProjectListController(Company company) {
        this.company = company;
    }

    /**
     * Method
     **/

    public List<Project> getCurrentProjectsByUserEmail(String email) {
        ProjectStore projStore = this.company.getProjectStore();

        List<Project> currentProjectListByUser = projStore.getCurrentProjectsByUserEmail(email);
        return Collections.unmodifiableList(currentProjectListByUser);
    }
}
