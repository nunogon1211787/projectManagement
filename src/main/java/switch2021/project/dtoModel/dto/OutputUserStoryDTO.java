package switch2021.project.dtoModel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import switch2021.project.entities.valueObjects.vos.UsTitle;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Relation(collectionRelation = "User Stories")
public class OutputUserStoryDTO extends RepresentationModel<OutputUserStoryDTO> {

    /**
     * Attributes
     **/
    public String id;
    public int priority;
    public String description;
    public double timeEstimate;
    public String parentUserStory;
    public String usStartDate;
    public String usEndDate;
    public String usRefined;
    public String status;

    public OutputUserStoryDTO(String id, int priority, String description, double timeEstimate) {
        this.id = id;
        this.priority = priority;
        this.description = description;
        this.timeEstimate = timeEstimate;
    }
}
