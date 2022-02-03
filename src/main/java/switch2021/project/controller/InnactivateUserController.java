package switch2021.project.controller;

import switch2021.project.model.Company;

public class InnactivateUserController {
    /**
     * Attributes
     **/

    private Company company;

    /**
     * Constructor to test (without SINGLETON)
     **/

    public InnactivateUserController (Company company) {
        this.company = company;
    }

    /**
     * Methods
     **/

    public boolean innactivateUser (String email) {
        this.company.getSystemUserStore().getUserByEmail(email).setInactivateUser();
        return true;
    }

}







