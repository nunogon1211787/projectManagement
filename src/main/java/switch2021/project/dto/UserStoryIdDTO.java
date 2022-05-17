package switch2021.project.dto;

import org.springframework.hateoas.RepresentationModel;

public class UserStoryIdDTO extends RepresentationModel<UserStoryIdDTO> {

    public String projectID;
    public String title;

}
