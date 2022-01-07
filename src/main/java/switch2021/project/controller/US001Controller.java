package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.SystemUser;

public class US001Controller {
    private Company company;
    private SystemUser user;

    public US001Controller() {
        this.company = new Company();
        this.user = null;
    }

    public boolean createSystemUser(String userName, String email, String function, String password) {
        this.user = this.company.createSystemUser(userName, email, function, password);
        return this.company.validateSystemUser(user);
    }

    public boolean createSystemUser(String userName, String email, String function, String photo, String password) {
        this.user = this.company.createSystemUser(userName, email, function, photo, password);
        return this.company.validateSystemUser(user);
    }
}
