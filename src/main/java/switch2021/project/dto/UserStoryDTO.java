package switch2021.project.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;


@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserStoryDTO  extends RepresentationModel<UserStoryDTO> {

    /**
     * Attributes
     **/

    public String projectID;
    public String userStoryID;
    public String title;
    public int priority;
    public String description;
    public double timeEstimate;


}
