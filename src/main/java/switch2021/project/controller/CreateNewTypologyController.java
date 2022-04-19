package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.Typology.TypologyService;

public class CreateNewTypologyController {

    /**
     * Attributes
     **/
    private final Company company;
    private final TypologyService service;


    /**
     * Constructor to test (without SINGLETON)
     **/
    public CreateNewTypologyController(Company company) {
        this.company = company;
        this.service = new TypologyService(company.getTypologyRepository());
    }


    /**
     * Method
     **/
    public void createTypology(String description) {
        if (description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description can't be blank.");
        }
        service.createAndSaveTypology(description);
    }
}
