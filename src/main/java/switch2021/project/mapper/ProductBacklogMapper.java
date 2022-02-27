package switch2021.project.mapper;

import switch2021.project.dto.ProjectDTO;
import switch2021.project.dto.UserStoryDto;
import switch2021.project.model.Project;
import switch2021.project.model.UserStory;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class ProductBacklogMapper {


    public List<UserStoryDto> toDto(List<UserStory> userStoryList) {
        List<UserStoryDto> userStoryListDtoList = new LinkedList<>();
        for (UserStory userStory : userStoryList) {
            UserStoryDto userStoryListDto = new UserStoryDto(userStory.getName(), userStory.getUserStoryStatus(),
                    userStory.getPriority(), userStory.getDescription());
            userStoryListDtoList.add(userStoryListDto);
        }
        return userStoryListDtoList;
    }
}