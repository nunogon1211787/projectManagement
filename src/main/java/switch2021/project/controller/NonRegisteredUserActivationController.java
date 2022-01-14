package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.SystemUser;
import switch2021.project.model.SystemUserStore;

public class NonRegisteredUserActivationController {
    private Company company;
    private SystemUserStore systemUserStore;
    private SystemUser user;

    public NonRegisteredUserActivationController(Company company,SystemUserStore systemUserStore,SystemUser user) {
        this.company = company;
        this.systemUserStore= null;
        this.user= null;
    }

    public boolean activateNonRegisteredUser (String email) {
        this.company.getSystemUserStore().getUserByEmail(email).setActivateUser();
        // é possivel misturar booleans com não booleans?
        return true;
    }



}
