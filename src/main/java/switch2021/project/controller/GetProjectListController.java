package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.Project;
import switch2021.project.stores.ProjectStore;
import switch2021.project.utils.App;

import java.util.List;

public class GetProjectListController {
    private Company company;

    public GetProjectListController() {
        this.company = App.getInstance().getCompany();
    }

    public List<Project> getProjectList() {
        ProjectStore projStore = company.getProjectStore();
        return projStore.getProjectList();
    }
}