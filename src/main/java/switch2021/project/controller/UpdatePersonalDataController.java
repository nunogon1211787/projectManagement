package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.SystemUser;

public class UpdatePersonalDataController {

    private Company company;
    private SystemUser user;

    public UpdatePersonalDataController(Company company, SystemUser user) {
        this.company = company;
        this.user = user;
    }

    public SystemUser getUser(String email) {
        return this.company.getSystemUserstore().getUserByEmail(email);
    }

    public SystemUser updateSystemUserData(String username, String function, String photo) {
        this.user.setUserName(username);
        this.user.setFunction(function);
        this.user.setPhoto(photo);
        return user;
    }

    public boolean saveSystemUser() {
        return this.company.getSystemUserstore().saveSystemUser(this.user);
    }

}
