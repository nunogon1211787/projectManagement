package switch2021.project.model.valueObject;

import lombok.Getter;

import java.util.Objects;

@Getter
public class UsTitle {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsTitle usTitle1 = (UsTitle) o;
        return Objects.equals(titleUs, usTitle1.titleUs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titleUs);
    }
}




