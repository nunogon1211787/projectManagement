package switch2021.project.dto;

import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;


@Getter
public class UserStoryDTO  extends RepresentationModel<UserStoryDTO> {

    /**
     * Attributes
     **/

    public String projectId;
    public String userStoryId;
    public String title;
    public int priority;
    public String description;
    public double timeEstimate;

    /**
     * Constructor to test (without SINGLETON)
     **/

    public UserStoryDTO() {
    }
}
