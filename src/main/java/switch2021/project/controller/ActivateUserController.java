package switch2021.project.controller;

import switch2021.project.model.Company;

public class ActivateUserController {
    /**
     * Attributes
     **/

    private Company company;

    /**
     * Constructor to test (without SINGLETON)
     **/

    public ActivateUserController (Company company) {
        this.company = company;
    }

    /**
     * Methods
     **/

    public boolean activateUser (String email) {
        this.company.getSystemUserStore().getUserByEmail(email).setActivateUser();
        return true;
    }

}
