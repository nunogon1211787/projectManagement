package switch2021.project.model.Typology;

import lombok.Getter;
import switch2021.project.valueObject.Description;
import java.util.Objects;

@Getter
public class Typology {

    /** Typology attributes are composed of a description of the Typology. **/
    private final Description description;

    /** Constructors of typology´s class. Creates a new typology instance. **/
    public Typology(String description) {
        this.description = new Description(description);
    }

    /** Override Methods **/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Typology that = (Typology) o;
        return (this.description.equals(that.description));
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }
}