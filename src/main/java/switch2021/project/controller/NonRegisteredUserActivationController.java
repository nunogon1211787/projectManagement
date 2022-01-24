package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.SystemUser;
import switch2021.project.stores.SystemUserStore;
import switch2021.project.utils.App;

public class NonRegisteredUserActivationController {
    private Company company;
    private SystemUserStore systemUserStore;
    private SystemUser user;


    /**
     * Constructor to UI (with SINGLETON).
     */
    public NonRegisteredUserActivationController(){ this.company = App.getInstance().getCompany();}

    /**
     * Constructor to test (without SINGLETON).
     */
    public NonRegisteredUserActivationController(Company company) { this.company = company; }


    public boolean activateNonRegisteredUser (String email) {
        this.company.getSystemUserStore().getUserByEmail(email).setActivateUser();
        return true;
    }

}
