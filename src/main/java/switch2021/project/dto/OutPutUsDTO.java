package switch2021.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@AllArgsConstructor
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
