package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.Project;
import switch2021.project.stores.ProjectStore;
import switch2021.project.utils.App;

import java.util.*;


public class GetCurrentProjectListController {
    private Company company;
    private ProjectStore projStore;
    private List<Project> currentProjectListByUser;

    public GetCurrentProjectListController() {
        this.company = App.getInstance().getCompany();
        this.projStore = null;
        this.currentProjectListByUser = null;
    }

    public List<Project> getCurrentProjectListByUserEmail(String email) {
        this.projStore = this.company.getProjectStore();
        this.currentProjectListByUser = this.projStore.getCurrentProjectListByUserEmail(email);
        return this.currentProjectListByUser;
    }

    public Company getCompany() {
        return this.company;
    }
}