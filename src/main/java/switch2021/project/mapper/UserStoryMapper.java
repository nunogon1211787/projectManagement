package switch2021.project.mapper;

import switch2021.project.dto.UserStoryDto;
import switch2021.project.model.UserStory;

public class UserStoryMapper {


    public UserStory toModel(UserStoryDto createUserStoryDto) {
        return new UserStory(createUserStoryDto.getName(), createUserStoryDto.getUserStoryStatus(), createUserStoryDto.getPriority(), createUserStoryDto.getDescription().getDescriptionF());
    }


}
