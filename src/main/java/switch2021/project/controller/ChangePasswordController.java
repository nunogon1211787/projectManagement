package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.SystemUser;
import switch2021.project.utils.App;

public class ChangePasswordController {

    private Company company;

    /**
     * Constructor to UI (with SINGLETON).
     */
    public ChangePasswordController() {
        this.company = App.getInstance().getCompany();
    }

    /**
     * Constructor to test (without SINGLETON).
     */
    public ChangePasswordController(Company company) { this.company = company; }

    public ChangePasswordController(Company company, SystemUser user) {
        this.company = company;
    }

    public boolean changePassword(String email, String oldpasswordUI, String newpassword) {


       SystemUser user = this.company.getSystemUserStore().getUserByEmail(email);
       user.updatePassword(oldpasswordUI, newpassword);
       return this.company.getSystemUserStore().saveSystemUser(user);
    }
}

