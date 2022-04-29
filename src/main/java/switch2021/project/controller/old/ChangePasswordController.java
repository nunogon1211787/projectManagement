package switch2021.project.controller.old;

import switch2021.project.model.Company;
import switch2021.project.model.SystemUser.SystemUser;
import switch2021.project.stores.SystemUserStore;

public class ChangePasswordController {

    /**
     * Attributes
     */

    private final Company company;

    /**
     * Constructor to test (without SINGLETON)
     */

    public ChangePasswordController(Company company) { this.company = company; }
/*
    /**
     * Methods
     */

/*    public boolean changePassword(String email, String oldPasswordUI, String newPassword, String newPasswordConfirmation) {
        SystemUserStore systemUserStore = company.getSystemUserStore();
       SystemUser user = systemUserStore.findSystemUserByEmail(email);
       return user.updatePassword(oldPasswordUI, newPassword, newPasswordConfirmation);
    }

 */
}

