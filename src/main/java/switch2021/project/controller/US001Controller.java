package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.Profile;
import switch2021.project.model.SystemUser;

public class US001Controller {
    private Company company;
    private SystemUser user;
    private Profile visitor;

    public US001Controller(Company company) {
        this.company = company;
        this.user = null;
        this.visitor = null;
    }

    public boolean createSystemUser(String userName, String email, String password, String function) {
        this.user = this.company.createSystemUser(userName, email, password, function);
        return this.company.validateSystemUser(user);
    }

    public boolean createSystemUser(String userName, String email, String password, String function, String photo) {
        this.user = this.company.createSystemUser(userName, email, password, function, photo);
        return this.company.validateSystemUser(user);
    }
}
