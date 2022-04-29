package switch2021.project.mapper.old;

import switch2021.project.dto.UserStoryDTO;
import switch2021.project.model.UserStory.UserStory;

import java.util.LinkedList;
import java.util.List;

public class ProductBacklogMapper {

    //TODO -----> Validar testes CDC

    public List<UserStoryDTO> toDto(List<UserStory> userStoryList) {
        List<UserStoryDTO> userStoryListDtoList = new LinkedList<>();
        for (UserStory userStory : userStoryList) {
            UserStoryDTO userStoryListDto = new UserStoryDTO();
            userStoryListDto.projectID =userStory.getProjectID().toString();
            userStoryListDto.userStoryID = userStory.getUserStoryID().toString();
            userStoryListDto.title=userStory.getTitle().getTitleUs();
            userStoryListDto.priority=userStory.getPriority().getPriorityUs();
            userStoryListDto.description=userStory.getDescription().getText();
            userStoryListDto.timeEstimate=userStory.getTimeEstimate().getUsHours();
            userStoryListDtoList.add(userStoryListDto);
        }
        return userStoryListDtoList;
    }
}