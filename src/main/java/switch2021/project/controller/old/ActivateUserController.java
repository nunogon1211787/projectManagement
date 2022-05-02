package switch2021.project.controller.old;

import switch2021.project.model.Company;

public class ActivateUserController {

    /**
     * Attributes
     **/

    private final Company company;

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
        return this.company.getSystemUserStore().findSystemUserByEmail(email).setActive(true);
    }

}