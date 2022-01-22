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

    public GetProjectListController() {
        this.company = App.getInstance().getCompany();
        this.projStore = null;
        this.projectList = null;
    }

    public List<Project> getProjectList() {
        this.projStore = this.company.getProjectStore();
        this.projectList = this.projStore.getProjectList();
        return this.projectList;
    }

    public Company getCompany() {
        return this.company;
    }
}