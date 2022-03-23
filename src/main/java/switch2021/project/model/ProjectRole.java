package switch2021.project.model;

import lombok.Getter;
import switch2021.project.immutable.Description;

import java.util.Objects;

@Getter
public class ProjectRole {

    /*** Class ProjectRole Attributes **/
    private final Description name;

    /*** Constructor **/
    public ProjectRole(String name) {
       this.name = new Description(name);
    }

    /*** Override Equals **/
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
