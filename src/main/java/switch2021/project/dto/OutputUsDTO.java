package switch2021.project.dto;

import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@Getter
public class OutputUsDTO extends RepresentationModel<OutputUsDTO> {

    public String userStoryID;
    public String projectID;
    public String title;

    public OutputUsDTO() {
    }
}
