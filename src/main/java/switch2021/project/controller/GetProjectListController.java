package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.Project.Project;
import switch2021.project.repositories.ProjectStore;
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
        List<Project> projectList = projStore.findAllProjects();
        return Collections.unmodifiableList(projectList);
    }
}