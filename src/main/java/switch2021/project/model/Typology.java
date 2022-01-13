package switch2021.project.model;

import java.util.concurrent.atomic.AtomicInteger;

public class Typology {



    /** Typology attributes **/

    private final int id_Typology;
    private String description;

    // Constructor with parameters
    public Typology(int id_Typology, String description) {
        this.id_Typology = id_Typology;
        if(description != "") {
            this.description = description;
        } else {
            throw new IllegalArgumentException("Description is empty");
        }
    }

    /** Constructors of typology´s class **/

    //Create ID automatically
    private static final AtomicInteger ID_TYPOLOGY_GENERATOR = new AtomicInteger();

    /** Getters and Setters **/

    public int getId_Typology() {
        return id_Typology;
    }

    public String getDescription() {
        return this.description;
    }

    public void updateDescription(String description) {
        this.description = description;
    }

    /** Metodos Override  para comparar Typologys diferentes **/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Typology that = (Typology) o;
        return ((this.description.equals(that.description) && (this.id_Typology == that.id_Typology)));
    }
    //Este override foi feito expecíficamente para os teste... uma vez que os IDs da classe
    // vão sempre seguir uma sequência! Aceito sugestões para melhorar isto...
}
