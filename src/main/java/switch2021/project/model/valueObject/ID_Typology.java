package switch2021.project.model.valueObject;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import switch2021.project.model.valueObject.Description;
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
    public ID_Typology(Description description) {
        this.description = description;
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
        return sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }

    @Override
    public boolean sameValueAs(ID_Typology other) {
        return this.description.getText().equals(other.getDescription().getText());
    }
}
