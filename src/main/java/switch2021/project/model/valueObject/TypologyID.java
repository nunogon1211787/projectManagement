package switch2021.project.model.valueObject;

import lombok.Getter;
import switch2021.project.utils.ValueObject;

import java.util.Objects;

/**
 * Typology's ID is a description and contents for itself
 * a unique value.
 */

@Getter
public class TypologyID implements ValueObject<TypologyID> {

    /**
     * Attributes
     */
    private final Description description;


    /**
     * Constructor
     */
    public TypologyID(Description description) {
        this.description = description;
    }


    /**
     * Methods
     */
    public boolean hasDescription(String description) {
        return Objects.equals(this.description.getText(), description);
    }


    /**
     * Override Equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypologyID that = (TypologyID) o;
        return sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }

    @Override
    public boolean sameValueAs(TypologyID other) {
        return this.description.getText().equals(other.getDescription().getText());
    }
}
