package switch2021.project.model.UserStory;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import switch2021.project.utils.ValueObject;

@Getter
@EqualsAndHashCode
public class UsTitle implements ValueObject<UsTitle> {

    /**
     * Attributes
     **/
    private final String titleUs; //As a //I want to

    /**
     * Constructor
     **/
    public UsTitle(String titleUs) {
        checkUsTile(titleUs);
        this.titleUs = titleUs;
    }

    /**
     * Methods
     **/
    private void checkUsTile(String usTitle) {
        if (usTitle.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be blank");
        }
        if (!usTitle.toUpperCase().startsWith("AS")) {
            throw new IllegalArgumentException("Title need to begin with - as");
        }
        if (!usTitle.contains("want")) {
            throw new IllegalArgumentException("Title don't contain the word want");
        }
    }
    /**
     * Override
     **/
    @Override
    public boolean sameValueAs(UsTitle other) {
        return false;
    }
}




