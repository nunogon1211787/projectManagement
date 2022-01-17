package switch2021.project.controller;

import switch2021.project.model.Typology;
import switch2021.project.stores.TypologyStore;

public class CreateNewTypologyController {

    /** Attributes **/
    private TypologyStore typologyStore;
    private Typology typology;

    public CreateNewTypologyController(TypologyStore typologyStore) {
        this.typologyStore = typologyStore;
        this.typology = null;
    }

    public boolean createTypology(String description) {

        if (description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description can't be blank.");
        } else {
            this.typology = this.typologyStore.createTypology(description);
            return this.typologyStore.saveTypology(typology);
        }
    }
}
