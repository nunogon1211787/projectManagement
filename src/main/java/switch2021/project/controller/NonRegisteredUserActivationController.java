package switch2021.project.controller;

import switch2021.project.model.Company;


public class NonRegisteredUserActivationController {

    /**
     * Attributes
     **/

    private final Company company;



    /**
     * Constructor to test (without SINGLETON)
     **/

    public NonRegisteredUserActivationController(Company company) { this.company = company; }

    /**
     * Methods
     **/

    public boolean activateNonRegisteredUser (String email) {
        return this.company.getSystemUserStore().findSystemUserByEmail(email).setActive(true);
    }

}
