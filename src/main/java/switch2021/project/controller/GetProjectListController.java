package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.Project;
import switch2021.project.stores.ProjectStore;
import switch2021.project.utils.App;

import java.util.Collections;
import java.util.List;

public class GetProjectListController {

    /**
     * Attributes
     **/

    private final Company company;


    /**
     * Constructor to test (without SINGLETON)
     **/

    public GetProjectListController(Company company){ this.company = company; }

    /**
     * Method
     **/

    public List<Project> getProjects() {
        ProjectStore projStore = this.company.getProjectStore();
        List<Project> projectList = projStore.getProjects();
        return Collections.unmodifiableList(projectList);
    }
}