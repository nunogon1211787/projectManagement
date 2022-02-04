package switch2021.project.stores;

import lombok.Getter;
import lombok.Setter;
import switch2021.project.model.Typology;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter

public class TypologyStore {

    /**
     * Attributes
     **/

    private List<Typology> typologyList;


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
        saveTypology(new Typology("Fixed Cost"));
        saveTypology(new Typology("Time and Materials"));
    }


    /**
     * Create Typology (Creates a new Typology object)
     **/

    public Typology createTypology(String description) {
        return new Typology(description);
    }


    /**
     * ID_Typology Generator (if the object isn´t saved on the list, the id will be the same for all
     * objects. This issue will be solved when calling the save method.
     **/

    public int idTypologyGenerator() {
        int id = 1;
        if (this.typologyList.size() > 0) {
            id = this.typologyList.get(typologyList.size() - 1).getIdTypology() + 1;
        }
        return id;
    }

    /**
     * Add and Remove Typology Methods. Adds or remove a Typology object to the Typology List
     **/

    private boolean addTypology(Typology typo) {
        if (validateIdTypology(typo)) {
            this.typologyList.add(typo);
        } else {
            typo.setIdTypology(idTypologyGenerator());
            this.typologyList.add(typo);
        }
        return true;
    }

    public boolean removeTypology(Typology typo) {
        boolean msg = false;
        if (typologyList.contains(typo)) {
            getOriginalTypologyList().remove(typo);
            msg = true;
        }
        return msg;
    }

    /**
     * Getters and Setters Methods
     **/

    private List<Typology> getOriginalTypologyList() {
        return this.typologyList;
    }

    public List<Typology> getTypologyList() {
        return new ArrayList<>(this.typologyList);
    }

    /**
     * Getter Method - typology by description
     **/

    public Typology getTypology(String description) {
        Typology typo = null;

        for (Typology i : this.typologyList) {
            if (i.getDescription().equals(description)) {
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

    private boolean validateIdTypology(Typology typo) {
        boolean msg = true;

        for (Typology i : this.typologyList) {
            if (i.getIdTypology() == typo.getIdTypology()) {
                msg = false;
                break;
            }
        }
        return msg;
    }

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
        return addTypology(typo);
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

    /**
     * Hash
     **/

    @Override
    public int hashCode() {
        return Objects.hash(typologyList);
    }
}
