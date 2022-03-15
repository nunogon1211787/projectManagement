package switch2021.project.model;

import lombok.Getter;
import lombok.Setter;
import switch2021.project.Immutables.Description;

import java.util.Objects;

@Getter
@Setter
public class Typology {

    /** Typology attributes are composed of a description of the Typology. **/
    private int idTypology;
    private final Description description;

    public static final int CONSTANT_MINIMUM_DESCRIPTION = 3;


    /** Constructors of typology´s class. Creates a new typology instance. **/
    public Typology(Description description) {
//        checkDescriptionRules(description);
        this.description = description;
    }

//    /** Methods to validate attributes data. **/
//
//    private void checkDescriptionRules(String description) {
//        if (description.trim().isEmpty())
//            throw new IllegalArgumentException("Description cannot be empty.");
//        if ((description.length() < CONSTANT_MINIMUM_DESCRIPTION))
//            throw new IllegalArgumentException("Description must be at least" + CONSTANT_MINIMUM_DESCRIPTION + "characters");
//    }

    /** Override Methods **/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Typology that = (Typology) o;
        return (this.description.equals(that.description));
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }


}
