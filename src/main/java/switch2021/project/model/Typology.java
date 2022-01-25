package switch2021.project.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Typology {

    /** Typology attributes are composed of a description of the Typology. **/
    private int id_Typology;
    private final String description;


    /** Constructors of typology´s class. Creates a new typology instance. **/
    public Typology(String description) {
        if (!description.equals("")) {
            this.description = description;
        } else {
            throw new IllegalArgumentException("Description is empty");
        }
    }

    /** Getters and Setters **/

    public void setId_Typology(int id_Typology) {
        this.id_Typology = id_Typology;
    }


    /** Override Methods **/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Typology that = (Typology) o;
        return ((this.description.equals(that.description)));
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }
}
