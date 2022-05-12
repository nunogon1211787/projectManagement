package switch2021.project.model.valueObject;

import lombok.Getter;
import lombok.ToString;
import switch2021.project.utils.ValueObject;

import java.util.Objects;
@ToString
@Getter
public class Description implements ValueObject<Description> {

    /**
     * Attributes
     **/
    private final String text;
    private static final int MINDESCRIPTIONLENGTH = 1;
    private static final int MAXDESCRIPTIONLENGTH = 1000;

    /**
     * Constructor
     **/
    public Description (String description) {
        if (description == null)
            throw new NullPointerException("Description field requires at least " + MINDESCRIPTIONLENGTH + " characters");
        if (description.trim().length() < MINDESCRIPTIONLENGTH)
            throw new IllegalArgumentException("Description field requires at least " + MINDESCRIPTIONLENGTH + " characters");
        if (description.trim().length() > MAXDESCRIPTIONLENGTH)
            throw new IllegalArgumentException("Description field cannot have more than " + MAXDESCRIPTIONLENGTH + " characters");
        this.text = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Description that = (Description) o;
        return Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }

    @Override
    public boolean sameValueAs(Description other) {
        return false;
    }
}
