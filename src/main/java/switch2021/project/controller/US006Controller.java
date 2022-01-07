package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.Profile;
import switch2021.project.model.SystemUser;

public class US006Controller {

    private Company company;
    private SystemUser user;

    public US006Controller(Company company, SystemUser user) {
        this.company = company;
        this.user = user;
    }

    public SystemUser getUser(String email) {
        return this.company.getUserByEmail(email);
    }

    public boolean updateProfile(Profile oldProfile, Profile newProfile) {

        this.user.updateProfile(oldProfile, newProfile);
        this.company.saveSystemUser(user);

        return true;
    }
}
