package switch2021.project.dto;

import org.springframework.hateoas.RepresentationModel;

public class SprintDTO extends RepresentationModel<SprintDTO> {

    /** Attributes*/

    public String projectID;
    public String sprintID;
    public String name;

}
