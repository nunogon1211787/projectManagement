package switch2021.project.valueObject;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Description {

    /**
     * Attributes
     **/
    private final String text;
    private final int MIN_DESCRIPTION_LENGTH = 1;
    private final int MAX_DESCRIPTION_LENGTH = 1000;

    /**
     * Constructor
     **/
    public Description (String description) {
        if (description == null)
            throw new NullPointerException("Description field requires at least " + MIN_DESCRIPTION_LENGTH + " characters");
        if (description.trim().length() < MIN_DESCRIPTION_LENGTH)
            throw new IllegalArgumentException("Description field requires at least " + MIN_DESCRIPTION_LENGTH + " characters");
        if (description.trim().length() > MAX_DESCRIPTION_LENGTH)
            throw new IllegalArgumentException("Description field cannot have more than " + MAX_DESCRIPTION_LENGTH + " characters");
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
}
