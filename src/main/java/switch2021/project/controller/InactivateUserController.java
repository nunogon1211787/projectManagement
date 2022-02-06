package switch2021.project.controller;

import switch2021.project.model.Company;

public class InactivateUserController {

    /**
     * Attributes
     **/

    private final Company company;

    /**
     * Constructor to test (without SINGLETON)
     **/

    public InactivateUserController (Company company) {
        this.company = company;
    }

    /**
     * Method
     **/

    public boolean inactivateUser (String email) {
        this.company.getSystemUserStore().getUserByEmail(email).setActivateUser(false);
        return true;
    }

}







