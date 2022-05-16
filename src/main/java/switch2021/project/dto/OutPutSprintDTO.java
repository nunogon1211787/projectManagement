package switch2021.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@AllArgsConstructor
public class OutPutSprintDTO extends RepresentationModel<OutPutSprintDTO> {

    /**
     * Attributes
     */

    public String projectID;
    public String sprintID;
    public String name;

}

