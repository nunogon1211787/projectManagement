package switch2021.project.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class ProjectStatus {

    /**
     * Project Status class
     * Project Status atributes are composed of a description of the Project Status.
     **/

    private String description;

    /**
     * Constructors of Project Status class
     * Creates a new Project Status instance.
     **/

    public ProjectStatus(String description) {
        checkDescriptionRules(description);
        this.description = description;
    }

    private void checkDescriptionRules(String description) {
        if (description.trim().isEmpty())
            throw new IllegalArgumentException("Description cannot be empty.");
        if ((description.length() < 3))
            throw new IllegalArgumentException("Description must be at least 3 characters");
    }

    /**
     * Override Methods
     **/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectStatus that = (ProjectStatus) o;
        return (this.description.equals(that.description));
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }
}