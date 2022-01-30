package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.Project;
import switch2021.project.stores.ProjectStore;
import switch2021.project.utils.App;

import java.util.*;


public class GetCurrentProjectListController {

    /**
     * Attributes
     **/

    private Company company;
    private List<Project> currentProjectListByUser;

    /**
     * Constructor to UI (with SINGLETON)
     **/

    public GetCurrentProjectListController() { this.company = App.getInstance().getCompany(); }

    /**
     * Constructor to test (without SINGLETON)
     **/

    public GetCurrentProjectListController(Company company){ this.company = company; }

    /**
     * Methods
     **/

    public List<Project> getCurrentProjectsByUserEmail(String email) {
        ProjectStore projStore = this.company.getProjectStore();
        this.currentProjectListByUser = projStore.getCurrentProjectsByUserEmail(email);
        return this.currentProjectListByUser;
    }
}