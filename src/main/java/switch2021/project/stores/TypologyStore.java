package switch2021.project.stores;

import lombok.Getter;
import switch2021.project.model.Typology.Typology;

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
        createTypology("Fixed Cost");
        createTypology("Time and Materials");
    }


    /**
     * Create Typology (Creates a new Typology object)
     **/
    public boolean createTypology(String description) {
        Typology newTypo = new Typology(description);
        return saveTypology(newTypo);
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
    public Typology getTypologyByDescription(String description) {
        Typology typo = null;

        for (Typology i : this.typologyList) {
            if (i.getDescription().getText().equals(description)) {
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
            return this.typologyList.add(typo);
        }
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