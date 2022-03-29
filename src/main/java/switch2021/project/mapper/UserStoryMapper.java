package switch2021.project.mapper;

import switch2021.project.dto.UserStoryDto;
import switch2021.project.model.Project.UserStory;

public class UserStoryMapper {


    public UserStory toModel(UserStoryDto createUserStoryDto) {
        return new UserStory(createUserStoryDto.getTitle(), createUserStoryDto.getUserStoryStatus(), createUserStoryDto.getPriority(), createUserStoryDto.getDescription().getText());
    }


}
