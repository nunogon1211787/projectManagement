package switch2021.project.model;

import java.util.ArrayList;
import java.util.List;

public class TypologyStore {

    /**
     * Classe ProjectSettings que vai conter a lista de Typology -
     * Atributos
     **/

    private List<Typology> typologyList;


    /**
     * Constructor
     **/

    public TypologyStore() {
        this.typologyList = new ArrayList<>();
    }

    public void populateTypologyList() {
        this.typologyList.add(new Typology(0, "Fixed Cost"));
        this.typologyList.add(new Typology(1, "Time and Materials"));
    }

    public Typology createTypology(int id, String description) {
        Typology typo = new Typology(id, description);

        return typo;
    }

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

    public Typology getTypologyById(int index) {
        return typologyList.get(index);
    }

    public boolean validateTypology(Typology typo) {
        if (typo.getDescription() == null || typo.getDescription() == "") {
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

    public boolean equals(Object obj){
        if (this == obj) return true;
        if (!(obj instanceof UserProfileStore)) return false;
        TypologyStore that = (TypologyStore) obj;
        return
                (this.typologyList.equals(that.getTypologyList()));
    }

}
