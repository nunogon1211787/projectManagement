package switch2021.project.mapper;

import switch2021.project.dto.OutputUsDTO;
import switch2021.project.model.UserStory.UserStory;

public class UserStoryMapper {

    private UserStoryMapper() {
    }

    public OutputUsDTO toDto(UserStory newUserStory) {
        OutputUsDTO outputUsDto = new OutputUsDTO();
        outputUsDto.userStoryID = newUserStory.getUserStoryID().toString();
        outputUsDto.projectID = newUserStory.getProjectID().getCode();
        outputUsDto.title = newUserStory.getTitle().getTitleUs();

        return outputUsDto;

    }
}
