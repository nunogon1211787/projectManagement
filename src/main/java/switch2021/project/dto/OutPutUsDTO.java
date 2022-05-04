package switch2021.project.dto;

import org.springframework.hateoas.RepresentationModel;

public class OutPutUsDTO extends RepresentationModel<OutPutUsDTO> {

    /**
     * Attributes
     **/
    public String userStoryID;
    public String projectID;
    public String title;
    public int priority;
    public String description;
    public double timeEstimate;

}
