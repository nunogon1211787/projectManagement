package switch2021.project.model.valueObject;

import lombok.Getter;

@Getter
public class UsTitle {

    /**
     * Attributes
     **/
    private final String usTitle; //As a //I want to


    /**
     * Constructor
     **/
    public UsTitle(String usTitle) {
        checkUsTile(usTitle);
        this.usTitle = usTitle;
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
}




