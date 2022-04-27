package switch2021.project.dto.old;

import lombok.Getter;

/**
 * The DTO calls for the use of objects that aggregate and encapsulate
 * data for transfer. It should not contain any business logic. That contains
 * all or partial data from a source.
 * Is used mappers for that to convert data between the DTO and any entity objects.
 */

@Getter
public class TypologyDTO {

    /**
     * Attribute.
     */
    private String description;

    /**
     * Constructor.
     */
    public TypologyDTO (String desc) {
        this.description = desc;
    }
}
