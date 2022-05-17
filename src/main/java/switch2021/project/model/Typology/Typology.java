package switch2021.project.model.Typology;

import lombok.NoArgsConstructor;
import switch2021.project.model.valueObject.TypologyID;
import switch2021.project.utils.Entity;

import javax.persistence.Embeddable;
import java.util.Objects;

/**
 * Typology must be a part of information which compose the project.
 * In this way, typology is considerate an entity even though it is really close of a
 * value object. It was defined to implement the feature to create a typology and make
 * possible the user maintenance.
 * Typology has as attribute a description which will provide the typology´s value
 * and that will be considerate as ID for itself.
 */


public class Typology implements Entity<Typology> {

    /**
     * Typology attributes are composed of a description of the Typology.
     **/
    private final TypologyID id_description;


    /**
     * Constructors of typology´s class. Creates a new typology instance.
     **/
    public Typology(TypologyID id) {
        this.id_description = id;
    }


    /**
     * Methods
     */
    public TypologyID getId_description() {return this.id_description;}

    public boolean hasID_Description(String description) {
        return this.id_description.hasDescription(description);
    }


    /**
     * Override Methods
     **/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Typology that = (Typology) o;
        return sameIdentityAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_description);
    }

    @Override
    public boolean sameIdentityAs(Typology other) {
        return other != null && this.id_description.sameValueAs(other.getId_description());
    }
}