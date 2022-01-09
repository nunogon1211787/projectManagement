package switch2021.project.controller;

import switch2021.project.model.Profile;
import switch2021.project.model.ProjectSettings;
import switch2021.project.model.Typology;
import switch2021.project.utils.App;

public class CreateNewTypologyController {

    /** Attributes **/
    private ProjectSettings projectSettings;
    private Typology typology;

    public CreateNewTypologyController() { this(App.getInstance().getProjectSettings());}

    public CreateNewTypologyController(ProjectSettings projectSettings) {
        this.projectSettings = projectSettings;
        this.typology = null;
    }

    public boolean createTypology(String description) {
        this.typology = this.projectSettings.createTypology(description);
        return this.projectSettings.saveTypology(typology);
    }
}
