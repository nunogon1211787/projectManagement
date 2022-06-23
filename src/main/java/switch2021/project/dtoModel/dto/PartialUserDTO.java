package switch2021.project.dtoModel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Relation(collectionRelation = "User")
public class PartialUserDTO extends RepresentationModel<PartialUserDTO> {

    public String userName;
    public String email;
    public String function;
}
