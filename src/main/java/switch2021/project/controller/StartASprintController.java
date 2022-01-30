package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.SystemUser;
import switch2021.project.utils.App;

public class StartASprintController {

    /**
     * Attributes
     **/

    private final Company company;


    /**
     * Constructor to UI (with SINGLETON)
     **/

    public StartASprintController() {
        this.company = App.getInstance().getCompany();
    }

    /**
     * Constructor to test (without SINGLETON)
     **/

    public StartASprintController(Company company) {
        this.company = company;
    }

    /**
     * Methods
     **/
}

