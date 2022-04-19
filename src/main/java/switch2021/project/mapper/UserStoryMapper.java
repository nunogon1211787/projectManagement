package switch2021.project.mapper;

import switch2021.project.dto.UserStoryDto;
import switch2021.project.model.UserStory.UserStory;

public class UserStoryMapper {

    public UserStory toModel(UserStoryDto createUserStoryDto) {
        return new UserStory(createUserStoryDto.getUserStoryId().toString(), createUserStoryDto.getTitle().getTitleUs(), createUserStoryDto.getPriority().getPriorityUs(), createUserStoryDto.getDescription().getText(), createUserStoryDto.getTimeEstimate().getUsHours());
    }
}
