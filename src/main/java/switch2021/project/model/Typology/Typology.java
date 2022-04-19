package switch2021.project.model.Typology;

import lombok.Getter;
import switch2021.project.model.valueObject.ID_Typology;
import switch2021.project.utils.Entity;

import java.util.Objects;

/**
 * Typology must be a part of information which compose the project.
 * In this way, typology is considerate an entity even though it is really close of a
 * value object. It was defined to implement the feature to create a typology and make
 * possible the user maintenance.
 * Typology has as attribute a description which will provide the typology´s value
 * and that will be considerate as ID for itself.
 */

@Getter
public class Typology implements Entity<Typology> {

    /**
     * Typology attributes are composed of a description of the Typology.
     **/
    private final ID_Typology id_description;


    /**
     * Constructors of typology´s class. Creates a new typology instance.
     **/
    public Typology(String description) {
        this.id_description = new ID_Typology(description);
    }


    /**
     * Methods
     */
    public boolean hasID_Description(String description) {
       if(this.id_description.hasDescription(description)) {
           return true;
       }
       return false;
    }


    /**
     * Override Methods
     **/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Typology that = (Typology) o;
        return (this.id_description.equals(that.id_description));
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_description);
    }

    @Override
    public boolean sameIdentityAs(Typology other) {
        return this.id_description.sameValueAs(other.getId_description());
    }
}