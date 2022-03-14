package switch2021.project.Immutables;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Description {

    /**
     * Attributes
     **/
    private  String descriptionF;
    private static final int MIN_DESCRIPTION_LENGTH = 1;
    private static final int MAX_DESCRIPTION_LENGTH = 1000;

    /**
     * Constructor
     **/
    public Description (String description) {
        if (description.trim().length() < MIN_DESCRIPTION_LENGTH)
            throw new IllegalArgumentException("Description field requires at least " + MIN_DESCRIPTION_LENGTH + " characters");
        if (description.trim().length() > MAX_DESCRIPTION_LENGTH)
            throw new IllegalArgumentException("Description field cannot have more than " + MAX_DESCRIPTION_LENGTH + " characters");
        this.descriptionF = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Description that = (Description) o;
        return Objects.equals(descriptionF, that.descriptionF);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descriptionF);
    }
}
