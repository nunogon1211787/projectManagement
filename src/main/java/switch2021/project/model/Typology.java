package switch2021.project.model;

import java.util.concurrent.atomic.AtomicInteger;

public class Typology {



    /** Typology attributes **/

    private int id_Typology;
    private String description;

    // Constructor with parameters
    public Typology(String description) {
        this.id_Typology = ID_TYPOLOGY_GENERATOR.getAndIncrement();
        this.description = description;
    }

    /** Constructors of typology´s class **/

    //Create ID automatically
    private static AtomicInteger ID_TYPOLOGY_GENERATOR = new AtomicInteger();

    /** Getters and Setters **/

    public int getId_Typology() {
        return id_Typology;
    }

    public String getDescription() {
        return description;
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
        return (this.description.equals(that.description));
    }
    //Este override foi feito expecíficamente para os teste... uma vez que os IDs da classe
    // vão sempre seguir uma sequência! Aceito sugestões para melhorar isto...
}
