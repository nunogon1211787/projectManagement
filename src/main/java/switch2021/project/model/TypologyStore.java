package switch2021.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TypologyStore {

    /**
     * Typology Store Atributes
     * Contains a Typology list
     **/

    private List<Typology> typologyList;


    /**
     * Typology Constructor
     **/

    public TypologyStore() {
        this.typologyList = new ArrayList<>();
    }

    /**
     * Typology Populator
     * Populates the typology List with pre-set objects
     **/

    public void populateTypologyList() {
        this.typologyList.add(new Typology("Fixed Cost"));
        this.typologyList.add(new Typology("Time and Materials"));
    }

    /**
     * Create Typology
     * Creates a new Typology object
     **/

    public Typology createTypology(String description) {
        Typology typo = new Typology(description);

        return typo;
    }

    /**
     * Add Typology Method
     * Adds a new Typology object to the Project Status List
     **/

    public boolean add(Typology typo) {
        if (validateTypology(typo)) {
            this.typologyList.add(typo);
        } else {
            return false;
        }
        return true;
    }

    public List<Typology> getTypologyList() {
        return typologyList;
    }

    public Typology getTypologyByDescription(String description) {
        Typology typo = null;

        for (Typology i : this.typologyList) {
            if (i.getDescription().equals(description)) {
                typo = i;
                break;
            }
        }
        return typo;
    }

    public boolean validateTypology(Typology typo) {

        if (typo.getDescription() == null || typo.getDescription().equals("")) {
            return false;
        }
        return true;
    }

    public boolean saveTypology(Typology typo) {
        if (!validateTypology(typo)) {
            return false;
        }
        return add(typo);
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof UserProfileStore)) return false;
        TypologyStore that = (TypologyStore) obj;
        return
                (this.typologyList.equals(that.getTypologyList()));
    }

}
