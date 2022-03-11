package switch2021.project.Imutables;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Description {

    /**
     * Attributes
     **/
    private  String description;
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
        this.description = description;
    }

}
