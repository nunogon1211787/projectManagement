package switch2021.project.model;

import java.util.concurrent.atomic.AtomicInteger;

public class Typology {

    /**
     * Typology class
     * Typology atributes are composed of a description of the Typology.
     **/

    private int id_Typology;
    private final String description;

    /**
     * Constructors of typology´s class
     * Creates a new typology instance.
     **/

    public Typology(String description) {

        if (!description.equals("")) {
            this.description = description;
        } else {
            throw new IllegalArgumentException("Description is empty");
        }
    }

    /**
     * Getters and Setters
     **/

    public int getId_Typology() { return this.id_Typology;}

    public String getDescription() {
        return this.description;
    }

    public void setId_Typology(int id_Typology) {
        this.id_Typology = id_Typology;
    }

    /**
     * Override Methods
     **/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Typology that = (Typology) o;
        return ((this.description.equals(that.description)));
    }
    //Este override foi feito expecíficamente para os teste... uma vez que os IDs da classe
    // vão sempre seguir uma sequência! Aceito sugestões para melhorar isto...
}
