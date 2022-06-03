package switch2021.project.dtoModel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UpdateUserStoryDTO extends RepresentationModel<UpdateUserStoryDTO> {

    /**
     * Attributes
     **/
    public int priority;
    public double timeEstimate;
}
