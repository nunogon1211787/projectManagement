package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.Typology;

public class CreateNewTypologyController {

    /**
     * Attributes
     **/

    private final Company company;

    /**
     * Constructor to test (without SINGLETON)
     **/

    public CreateNewTypologyController(Company company) {
        this.company = company;
    }

    /**
     * Method
     **/

    public boolean createTypology(String description) {
        boolean msg;
        if (description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description can't be blank.");
        } else {
            this.company.getTypologyStore().createTypology(description);
            msg = true;
        }
        return msg;
    }
}
