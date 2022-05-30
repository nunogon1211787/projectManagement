package switch2021.project.dtoModel.dto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@AllArgsConstructor
@Getter
public class CreateUserStoryDTO extends RepresentationModel<CreateUserStoryDTO> {

    /**
     * Attributes
     **/

    public String projectID;
    public String title;
    public int priority;
    public String description;
    public double timeEstimate;


}
