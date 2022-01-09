package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.Profile;
import switch2021.project.model.SystemUser;
import switch2021.project.utils.App;

public class UpdateUserProfileController {

    private Company company;
    private SystemUser user;

    public UpdateUserProfileController() {
        this(App.getInstance().getCompany());
    }

    public UpdateUserProfileController(Company company) {
        this.company = company;
        this.user = null;
    }

    public SystemUser getUser(String email) {
        return this.company.getUserByEmail(email);
    }

    public boolean updateProfile(SystemUser user, Profile oldProfile, Profile newProfile) {

        this.user = user;
        this.user.updateProfile(oldProfile, newProfile);
        this.company.saveSystemUser(user);

        return true;
    }
}
