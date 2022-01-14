package switch2021.project.model;

import java.util.ArrayList;
import java.util.List;

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
        saveTypology(new Typology("Fixed Cost"));
        saveTypology(new Typology("Time and Materials"));
    }

    /**
     * Create Typology
     * Creates a new Typology object
     **/

    public Typology createTypology(String description) {
       return new  Typology(description);
    }

    /**
     * ID Generator
     */

    public int idGenerator () {
        int id = 1;
        if(this.typologyList.size() > 0) {
           id = this.typologyList.get(typologyList.size()-1).getId_Typology() + 1;
        }
        return id;
    } //if the object isn´t saved on the list, the id will be the same for all
    //objects. This issue will be solved when calling the save method.

    /**
     * Add Typology Method
     * Adds a new Typology object to the Project Status List
     **/

    private boolean addTypology(Typology typo) {
        if (validateId_Typology(typo)) {
            this.typologyList.add(typo);
        } else {
            typo.setId_Typology(idGenerator());
            this.typologyList.add(typo);
        }
        return true;
    }

    public boolean removeTypology(Typology typo) {
        boolean msg = false;
        if(typologyList.contains(typo)) {
            getOriginalTypologyList().remove(typo);
            msg = true;
        }
        return msg;
    }

    /**
     * Getter and Setter
     */

    private List<Typology> getOriginalTypologyList() {
        return this.typologyList;
    }

    public List<Typology> getTypologyList() {
        return new ArrayList<>(this.typologyList);
    }

    //Get typology by description
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

    // Get typology by ID
    public Typology getTypology(int id_Typology) {
        Typology typo = null;

        for (Typology i : this.typologyList) {
            if (i.getId_Typology() == id_Typology) {
                typo = i;
                break;
            }
        }
        return typo;
    }

    private boolean validateId_Typology(Typology typo) {
        boolean msg = true;

        for (Typology i : this.typologyList) {
            if (i.getId_Typology() == typo.getId_Typology()) {
                msg = false;
                break;
            }
        }
        return msg;
    }

    public boolean validateTypology(Typology typo) {

        boolean msg = true;
        for (Typology i : this.typologyList) {
            if(typo.getDescription().equals(i.getDescription())) {
                msg = false;
                break;
            }
        }
        return msg;
    }

    public boolean saveTypology(Typology typo) {
        if (!validateTypology(typo)) {
            return false;
        }
        typo.setId_Typology(idGenerator());
        return addTypology(typo);
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof UserProfileStore)) return false;
        TypologyStore that = (TypologyStore) obj;
        return
                (this.typologyList.equals(that.getTypologyList()));
    }
}
