package switch2021.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@AllArgsConstructor
public class OutputUserStoryDTO extends RepresentationModel<OutputUserStoryDTO> {

    /**
     * Attributes
     **/
    public String id;
    public int priority;
    public String description;
    public double timeEstimate;

}
