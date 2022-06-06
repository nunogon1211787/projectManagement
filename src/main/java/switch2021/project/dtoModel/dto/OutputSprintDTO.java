package switch2021.project.dtoModel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OutputSprintDTO extends RepresentationModel<OutputSprintDTO> {

    /**
     * Attributes
     */

    public String projectID;
    public String sprintID;
    public String name;

}

