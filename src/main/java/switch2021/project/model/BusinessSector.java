package switch2021.project.model;

import lombok.Getter;
import switch2021.project.immutable.Description;


@Getter
public class BusinessSector {

    /**
     * Business Sector class
     * Business Sector atributes are composed of a description of the business sector
     **/

    private Description description; // agora é uma classe, não é uma string

    /**
     * Business Sector Constructor
     * Creates a new Business Sector instance.
     **/

    public BusinessSector(String description) {

        this.description = new Description(description);
    }

}
