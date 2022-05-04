package switch2021.project.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;


@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class UserStoryDTO extends RepresentationModel<UserStoryDTO> {

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
