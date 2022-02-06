package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.SystemUser;
import switch2021.project.stores.SystemUserStore;
import switch2021.project.utils.App;

public class NonRegisteredUserActivationController {

    /**
     * Attributes
     **/

    private Company company;


    /**
     * Constructor to UI (with SINGLETON)
     **/

    //public NonRegisteredUserActivationController(){ this.company = App.getInstance().getCompany();}

    /**
     * Constructor to test (without SINGLETON)
     **/

    public NonRegisteredUserActivationController(Company company) { this.company = company; }

    /**
     * Methods
     **/

    public boolean activateNonRegisteredUser (String email) {
        this.company.getSystemUserStore().getUserByEmail(email).setActivateUser(true);
        return true;
    }

}
