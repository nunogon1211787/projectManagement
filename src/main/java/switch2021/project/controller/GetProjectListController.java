package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.Project;
import switch2021.project.stores.ProjectStore;
import switch2021.project.utils.App;

import java.util.List;

public class GetProjectListController {
    private Company company;
    private ProjectStore projStore;
    private List<Project> projectList;

    /**
     * Constructor to UI (with SINGLETON).
     */
    public GetProjectListController() { this.company = App.getInstance().getCompany(); }

    /**
     * Constructor to test (without SINGLETON).
     */
    public GetProjectListController(Company company){ this.company = company; }

    public List<Project> getProjectList() {
        this.projStore = this.company.getProjectStore();
        this.projectList = this.projStore.getProjectList();
        return this.projectList;
    }

    public Company getCompany() {
        return this.company;
    }
}