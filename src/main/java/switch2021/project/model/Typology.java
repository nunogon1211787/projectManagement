package switch2021.project.model;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Typology {

    /**
     * Typology attributes
     **/

    private int id_Typology;
    private String description;

    /**
     * Constructors of typology´s class
     **/

    //Create ID automatically
    private static AtomicInteger ID_GENERATOR = new AtomicInteger(001);

    // Constructor with no parameters
    public Typology() {
    }

    // Constructor with parameters
    public Typology(String description) {
        this.id_Typology = ID_GENERATOR.getAndIncrement();
        this.description = description;
    }

    /**
     * Getters and Setters
     */

    public int getId_Typology() {
        return id_Typology;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
