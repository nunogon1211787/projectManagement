package switch2021.project.model;

public class Typology {

    /**
     * Typology attributes
     **/

    private int id_Typology;
    private String description;

    /**
     * Constructors of typology´s class
     **/

    // Constructor with no parameters
    public Typology() {
    }

    // Constructor with parameters
    public Typology(int id_Typology, String description) {
        this.id_Typology = id_Typology;
        this.description = description;
    }

    /**
     * Getters and Setters
     */

    public int getId_Typology() {
        return id_Typology;
    }

    public void setId_Typology(int id_Typology) {
        this.id_Typology = id_Typology;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
