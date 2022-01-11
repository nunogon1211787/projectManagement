package switch2021.project.controller;

import switch2021.project.model.ProjectSettings;
import switch2021.project.model.Typology;
import switch2021.project.model.TypologyStore;
import switch2021.project.utils.App;

public class CreateNewTypologyController {

    /** Attributes **/
    private TypologyStore typologyStore;
    private Typology typology;

    public CreateNewTypologyController() { this(App.getInstance().getTypologyStore());}

    public CreateNewTypologyController(TypologyStore typologyStore) {
        this.typologyStore = typologyStore;
        this.typology = null;
    }

    public boolean createTypology(String description) {
        this.typology = this.typologyStore.createTypology(description);
        return this.typologyStore.saveTypology(typology);
    }
}
