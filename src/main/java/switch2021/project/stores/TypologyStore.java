package switch2021.project.stores;

import switch2021.project.model.Typology;

import java.util.ArrayList;
import java.util.List;

public class TypologyStore {

    /** Typology Store Attributes. Contains a Typology list. **/
    private List<Typology> typologyList;


    /** Typology Store Constructor **/
    public TypologyStore() {
        this.typologyList = new ArrayList<>();
    }


    /** Typology populator. Populates the typology List with pre-set objects. **/
    public void populateTypologyList() {
        saveTypology(new Typology("Fixed Cost"));
        saveTypology(new Typology("Time and Materials"));
    }


    /** Create Typology. Creates a new Typology object. **/
    public Typology createTypology(String description) {
       return new Typology(description);
    }


    /** ID_Typology Generator **/
    public int id_TypologyGenerator() {
        int id = 1;
        if(this.typologyList.size() > 0) {
           id = this.typologyList.get(typologyList.size()-1).getId_Typology() + 1;
        }
        return id;
    } //if the object isn´t saved on the list, the id will be the same for all
    //objects. This issue will be solved when calling the save method.


    /** Add and Remove Typology Methods. Adds or remove a Typology object to the Typology List **/
    private boolean addTypology(Typology typo) {
        if (validateId_Typology(typo)) {
            this.typologyList.add(typo);
        } else {
            typo.setId_Typology(id_TypologyGenerator());
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


    /** Getters and Setters Methods. **/
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


    /** Validation Methods. **/
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
        //Check if Typology already exist
        boolean msg = true;
        for (Typology i : this.typologyList) {
            if(i.equals(typo)) {
                msg = false;
                break;
            }
        }
        return msg;
    }

    /** Save Typology Method. Save a new Typology object to the Typology List **/
    public boolean saveTypology(Typology typo) {
        if (!validateTypology(typo)) {
            throw new IllegalArgumentException("Repeated typology description inserted.");
        } else {
            typo.setId_Typology(id_TypologyGenerator());
        }
        return addTypology(typo);
    }

    /** Override Methods **/
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof UserProfileStore)) return false;
        TypologyStore that = (TypologyStore) obj;
        return (this.typologyList.equals(that.getTypologyList()));
    }
}
