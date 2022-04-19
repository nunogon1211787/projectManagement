package switch2021.project.mapper;

import switch2021.project.dto.UserStoryDto;
import switch2021.project.model.UserStory.UserStory;

import java.util.LinkedList;
import java.util.List;

public class ProductBacklogMapper {

    public List<UserStoryDto> toDto(List<UserStory> userStoryList) {
        List<UserStoryDto> userStoryListDtoList = new LinkedList<>();
        for (UserStory userStory : userStoryList) {
            UserStoryDto userStoryListDto = new UserStoryDto(userStory.getUserStoryId().toString(), userStory.getTitle().getTitleUs(),
                    userStory.getPriority().getPriorityUs(), userStory.getDescription().getText(), userStory.getTimeEstimate().getUsHours());
            userStoryListDtoList.add(userStoryListDto);
        }
        return userStoryListDtoList;
    }
}