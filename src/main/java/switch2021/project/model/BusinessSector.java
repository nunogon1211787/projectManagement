package switch2021.project.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
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

}
