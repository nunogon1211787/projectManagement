package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.SystemUser.SystemUser;

public class UpdatePersonalDataController {

    /**
     * Attributes
     **/

    private final Company company;
    private SystemUser user;

    /**
     * Constructor to test (without SINGLETON)
     **/

    public UpdatePersonalDataController(Company company) { this.company = company; }

    /**
     * Methods
     **/

    public SystemUser getUser(String email) {
        this.user = this.company.getSystemUserStore().getUserByEmail(email);
        return this.user; }

    public boolean updateSystemUserData(String username, String function, String photo) {
        return this.user.setAllData(username, function, photo);
    }
}
