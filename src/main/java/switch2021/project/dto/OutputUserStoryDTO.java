package switch2021.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Getter
@AllArgsConstructor
@Relation(collectionRelation = "User Stories")
public class OutputUserStoryDTO extends RepresentationModel<OutputUserStoryDTO> {

    /**
     * Attributes
     **/
    public String id;
    public int priority;
    public String description;
    public double timeEstimate;

}
