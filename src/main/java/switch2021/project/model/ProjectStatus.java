package switch2021.project.model;

import lombok.Getter;
import switch2021.project.immutable.Description;

import java.util.Objects;

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
    public ProjectStatus(String description) {
        this.description = new Description(description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectStatus that = (ProjectStatus) o;
        return (this.description.getText().equals(that.description.getText()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }
}