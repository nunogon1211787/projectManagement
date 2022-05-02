package switch2021.project.mapper;

import org.springframework.stereotype.Component;
import switch2021.project.dto.UserStoryDTO;
import switch2021.project.model.UserStory.UserStory;

@Component
public class UserStoryMapper {

    private UserStoryMapper() {
    }

    public UserStoryDTO toDto(UserStory newUserStory) {
        UserStoryDTO outputUsDto = new UserStoryDTO();
        outputUsDto.userStoryID = newUserStory.getUserStoryID().toString();
//        outputUsDto.projectID = newUserStory.getUserStoryID().getProjectID().getCode();
//        outputUsDto.title = newUserStory.getUserStoryID().getUsTitle().getTitleUs();
//        outputUsDto.priority = newUserStory.getPriority().getPriorityUs();
//        outputUsDto.description = newUserStory.getDescription().getText();
//        outputUsDto.timeEstimate = newUserStory.getTimeEstimate().getUsHours();
        return outputUsDto;

    }
}
