package switch2021.project.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Locale;
import java.util.Objects;

@Getter
@Setter
public class ProjectRole {


    /**
     * Class ProjectRoles Attributes
     **/
    private int idRole;
    private String name;

    /**
     * Constructor
     **/
    public ProjectRole(String name) {
        if (!name.equals("")) {
            this.name = name;
        } else {
            throw new NullPointerException("Description cannot be null");
        }
    }

    /**
     * Method to verify if the name to the Project Role is valid
     **/
    public boolean isValidName(String name) {
        return name.equalsIgnoreCase(this.name);
    }

    /**
     * Override Equals
     **/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectRole that = (ProjectRole) o;
        return (this.name.equals(that.name));
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
