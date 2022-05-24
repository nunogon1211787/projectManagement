package switch2021.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Getter
@AllArgsConstructor
@Relation(collectionRelation = "User")
public class OutputUserDTO extends RepresentationModel<OutputUserDTO> {

    /**
     * Attributes
     */

    public String userName;
    public String email;
    public String function;
    public String photo;
    public String isActive;
}
