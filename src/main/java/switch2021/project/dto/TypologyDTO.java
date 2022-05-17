package switch2021.project.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

/**
 * The DTO calls for the use of objects that aggregate and encapsulate
 * data for transfer. It should not contain any business logic. That contains
 * all or partial data from a source.
 * Is used mappers for that to convert data between the DTO and any entity objects.
 */

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class TypologyDTO extends RepresentationModel<TypologyDTO> {

    /**
     * Attributes.
     */
    public String description;
}
