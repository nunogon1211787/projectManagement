package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.Project;
import switch2021.project.model.UserProfile;
import switch2021.project.stores.ProjectStore;
import switch2021.project.stores.SystemUserStore;
import switch2021.project.stores.UserProfileStore;
import switch2021.project.utils.App;

import java.util.*;


public class GetCurrentProjectListController {
    private Company company;

    public GetCurrentProjectListController() {
        this.company = App.getInstance().getCompany();
    }

    public List<Project> getCurrentProjectListByUserEmail(String email) {
        ProjectStore projStore = company.getProjectStore();

        return projStore.getCurrentProjectListByUserEmail(email);
    }
}