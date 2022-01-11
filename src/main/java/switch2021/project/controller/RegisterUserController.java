package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.SystemUser;
import switch2021.project.model.UserProfile;

public class RegisterUserController {
    private Company company;
    private SystemUser user;

    public RegisterUserController(Company company) {
        this.company = company;
        this.user = null;
    }

    //with photo
    public boolean createSystemUser(String userName, String email, String function, String password, String passwordConfirmation, String photo) {

        UserProfile visitor = this.company.getUserProfileStore().getProfile("visitor");

        this.user = this.company.getSystemUserStore().createSystemUser(userName, email, function, password, passwordConfirmation, photo, visitor);

        return this.company.getSystemUserStore().validateSystemUser(user);
    }

}
