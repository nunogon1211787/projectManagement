package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.Typology;
import switch2021.project.stores.TypologyStore;
import switch2021.project.utils.App;

public class CreateNewTypologyController {

    /** Attributes **/
    private Company company;
    private Typology typology;

    /**
     * Constructor to UI (with SINGLETON).
     */
    public CreateNewTypologyController(){ this.company = App.getInstance().getCompany();}

    /**
     * Constructor to test (without SINGLETON).
     */
    public CreateNewTypologyController(Company company) {
        this.company = company;
    }

    public boolean createTypology(String description) {

        if (description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description can't be blank.");
        } else {
            this.typology = this.company.getTypologyStore().createTypology(description);
            return this.company.getTypologyStore().saveTypology(typology);
        }
    }
}
