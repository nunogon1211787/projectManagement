package switch2021.project.controller.old;

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
        this.company.getSystemUserStore().findSystemUserByEmail(email).setActive(true);
        return true;
    }

}
