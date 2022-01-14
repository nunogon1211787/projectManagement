package switch2021.project.model;

public class BusinessSector {

    /**
     * Business Sector class
     * Business Sector atributes are composed of a description of the business sector
     **/

    private String description;

    /**
     * Business Sector Constructor
     * Creates a new Business Sector instance.
     **/

    public BusinessSector(String description) {
        this.description = description;
    }

    /**
     * Getter and Setter
     **/

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Override Methods
     **/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusinessSector that = (BusinessSector) o;

        return (this.description.equals(that.description));
    }
}
