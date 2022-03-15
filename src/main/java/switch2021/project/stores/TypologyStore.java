package switch2021.project.stores;

import lombok.Getter;
import switch2021.project.Immutables.Description;
import switch2021.project.model.Typology;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class TypologyStore {

    /**
     * Attributes
     **/
    private final List<Typology> typologyList;


    /**
     * Typology Store Constructor
     **/
    public TypologyStore() {
        this.typologyList = new ArrayList<>();
    }


    /**
     * Typology populator, that populates the typology List with pre-set objects
     **/
    public void populateDefault() {
        saveTypology(new Typology(new Description("Fixed Cost")));
        saveTypology(new Typology(new Description("Time and Materials")));
    }


    /**
     * Create Typology (Creates a new Typology object)
     **/
    public Typology createTypology(String description) {
        return new Typology(new Description(description));
    }


    /**
     * ID_Typology Generator (if the object isn´t saved on the list, the id will be the same for all
     * objects. This issue will be solved when calling the save method.
     **/
    public int idTypologyGenerator() {
        int id = 1;
        if (!this.typologyList.isEmpty()) {
            id = this.typologyList.get(typologyList.size() - 1).getIdTypology() + 1;
        }
        return id;
    }

    /**
     * Getters and Setters Methods
     **/
    public List<Typology> getTypologyList() {
        return new ArrayList<>(this.typologyList);
    }


    /**
     * Getter Method - typology by description
     **/
    public Typology getTypology(String description) {
        Typology typo = null;

        for (Typology i : this.typologyList) {
            if (i.getDescription().getDescriptionF().equals(description)) {
                typo = i;
                break;
            }
        }
        return typo;
    }

    /**
     * Getter Method - typology by ID
     **/
    public Typology getTypology(int id_Typology) {
        Typology typo = null;

        for (Typology i : this.typologyList) {
            if (i.getIdTypology() == id_Typology) {
                typo = i;
                break;
            }
        }
        return typo;
    }


    /**
     * Validation Methods
     **/

    public boolean validateTypology(Typology typo) {
        //Check if Typology already exist
        boolean msg = true;
        for (Typology i : this.typologyList) {
            if (i.equals(typo)) {
                msg = false;
                break;
            }
        }
        return msg;
    }

    /**
     * Save Typology Method. Save a new Typology object to the Typology List
     **/
    public boolean saveTypology(Typology typo) {
        if (!validateTypology(typo)) {
            throw new IllegalArgumentException("Repeated typology description inserted.");
        } else {
            typo.setIdTypology(idTypologyGenerator());
        }
        return this.typologyList.add(typo);
    }

    /**
     * Override Methods
     **/
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof TypologyStore)) return false;
        TypologyStore that = (TypologyStore) obj;
        return (this.typologyList.equals(that.getTypologyList()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(typologyList);
    }
}
