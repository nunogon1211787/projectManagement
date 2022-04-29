package switch2021.project.mapper;

import org.springframework.stereotype.Component;
import switch2021.project.dto.OutputUsDTO;
import switch2021.project.model.UserStory.UserStory;

@Component
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
