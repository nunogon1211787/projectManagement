package switch2021.project.model;

import lombok.Getter;
import lombok.Setter;
import switch2021.project.Immutables.Description;

import java.util.Objects;

@Getter
@Setter
public class ProjectStatus {

    /**
     * Project Status class
     * Project Status attributes are composed of a description of the Project Status.
     **/
    private Description description;

    /**
     * Constructors of Project Status class
     * Creates a new Project Status instance.
     **/
    public ProjectStatus(String description) {
        this.description = new Description(description);
    }

    /**
     * Override Methods
     **/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectStatus that = (ProjectStatus) o;
        return (this.description.getDescriptionF().equals(that.description.getDescriptionF()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }
}