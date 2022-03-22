package switch2021.project.model;

import lombok.Getter;
import switch2021.project.immutable.Description;

@Getter
public class ProjectStatus {

    /**
     * Project Status class
     * Project Status attributes are composed of a description of the Project Status.
     **/
    private final Description description;

    /**
     * Constructors of Project Status class
     * Creates a new Project Status instance.
     **/
    public ProjectStatus(String text) {
        this.description = new Description(text);
    }
}