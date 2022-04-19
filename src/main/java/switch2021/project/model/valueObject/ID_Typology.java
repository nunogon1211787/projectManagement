package switch2021.project.model.valueObject;

import lombok.Getter;
import switch2021.project.model.Typology.Typology;
import switch2021.project.utils.ValueObject;

import java.util.Objects;

/**
 * Typology's ID is a description and contents for itself
 * a unique value.
 */

@Getter
public class ID_Typology implements ValueObject<ID_Typology> {

    /**
     * Attributes
     */
    private final Description description;


    /**
     * Constructor
     */
    public ID_Typology(String description) {
        this.description = new Description(description);
    }


    /**
     * Methods
     */
    public boolean hasDescription(String description) {
        if(this.description.getText() == description) {
            return true;
        }
        return false;
    }


    /**
     * Override Equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ID_Typology that = (ID_Typology) o;
        return Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }

    @Override
    public boolean sameValueAs(ID_Typology other) {
        return this.description.getText() == other.getDescription().getText();
    }
}
