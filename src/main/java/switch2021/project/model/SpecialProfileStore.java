package switch2021.project.model;

import java.util.List;

public class SpecialProfileStore {

    List<SpecialProfile> specialProfileList;

    public SpecialProfileStore () {
        specialProfileList.add(new SpecialProfile("Project Manager", "Special Profile"));
        specialProfileList.add(new SpecialProfile("Product Owner", "Special Profile"));
        specialProfileList.add(new SpecialProfile("Scrum Master", "Special Profile"));
        specialProfileList.add(new SpecialProfile("Project Team", "Special Profile"));

    }
}
