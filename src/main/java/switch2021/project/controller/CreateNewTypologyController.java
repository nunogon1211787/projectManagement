package switch2021.project.controller;

import switch2021.project.model.Typology;
import switch2021.project.model.TypologyStore;

public class CreateNewTypologyController {

    /** Attributes **/
    private TypologyStore typologyStore;
    private Typology typology;

    public CreateNewTypologyController(TypologyStore typologyStore) {
        this.typologyStore = typologyStore;
        this.typology = null;
    }

    public boolean createTypology(String description) {
        this.typology = this.typologyStore.createTypology(description);
        return this.typologyStore.saveTypology(typology);
    }
}
