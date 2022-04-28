package switch2021.project.dto;

import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@Getter
public class SprintDTO extends RepresentationModel<SprintDTO> {

    /** Attributes*/

    public String projectID;
    public String sprintID;
    public String name;

    /** Constructor */

    public SprintDTO(){
    }

}
