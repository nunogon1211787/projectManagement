package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.SystemUser;
import switch2021.project.model.SystemUserStore;
import switch2021.project.model.UserProfile;

public class RegisterUserController {
    private Company company;
    private SystemUserStore store;
    private SystemUser user;

    public RegisterUserController() {
        this.company = new Company();
        this.store = null;
        this.user = null;
    }

    public boolean createSystemUser(String userName, String email, String function, String password, String passwordConfirmation, String photo) {
        this.user = this.company.getSystemUserStore().createSystemUser(userName, email, function, password, passwordConfirmation, photo);
        return this.company.getSystemUserStore().validateSystemUser(user);
    }
}
