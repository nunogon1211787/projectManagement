package switch2021.project.dtoModel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Relation(collectionRelation = "User")
public class UpdateUserProfileDTO extends RepresentationModel<UpdateUserProfileDTO> {

    /**
     * Attributes
     **/
    public String profileId;
}