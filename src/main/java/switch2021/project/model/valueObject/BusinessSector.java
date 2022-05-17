package switch2021.project.model.valueObject;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class BusinessSector {

    /**
     * Business Sector class
     * Business Sector atributes are composed of a description of the business sector
     **/
    private final Description description;


    /**
     * Business Sector Constructor
     * Creates a new Business Sector instance.
     **/
    public BusinessSector(String description) {
        this.description = new Description(description);
    }
}
