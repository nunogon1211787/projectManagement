package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.SystemUser;
import switch2021.project.stores.SystemUserStore;
import switch2021.project.utils.App;

public class ChangePasswordController {

    /**
     * Attributes
     */

    private final Company company;

    /**
     * Constructor to UI (with SINGLETON)
     */

    //public ChangePasswordController() {this.company = App.getInstance().getCompany();}

    /**
     * Constructor to test (without SINGLETON)
     */

    public ChangePasswordController(Company company) { this.company = company; }

    /**
     * Methods
     */

    public boolean changePassword(String email, String oldPasswordUI, String newPassword, String newPasswordConfirmation) {
        SystemUserStore systemUserStore = company.getSystemUserStore();
       SystemUser user = systemUserStore.getUserByEmail(email);
       return user.updatePassword(oldPasswordUI, newPassword, newPasswordConfirmation);
    }
}

