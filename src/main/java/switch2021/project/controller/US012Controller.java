package switch2021.project.controller;

import switch2021.project.model.Profile;
import switch2021.project.model.ProjectSettings;
import switch2021.project.model.Typology;

public class US012Controller {

    /** Attributes **/
    private ProjectSettings projectSettings;
    private Typology typology;


    public US012Controller(ProjectSettings projectSettings, Typology typo) {
        this.projectSettings = projectSettings;
        this.typology = typo;
    }

    public boolean createTypology(String description) {
        this.typology = this.projectSettings.createTypology(description);
        return this.projectSettings.validateTypology(typology);
    }
}
