package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.Typology;

public class CreateNewTypologyController {

    /**
     * Attributes
     **/

    private Company company;
    private Typology typology;

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

        if (description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description can't be blank.");
        } else {
            this.typology = this.company.getTypologyStore().createTypology(description);
            return this.company.getTypologyStore().saveTypology(typology);
        }
    }
}
