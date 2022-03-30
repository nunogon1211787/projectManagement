package switch2021.project.mapper;

import switch2021.project.dto.UserStoryStatusDTO;
import switch2021.project.model.UserStory.UserStory;

import java.util.ArrayList;
import java.util.List;

public class ScrumBoardMapper {

    /**
     * Method to change data in to a Scrum Board DTO
     **/
    public UserStoryStatusDTO toDTO(UserStory userStory) {
        return new UserStoryStatusDTO(userStory.getTitle(), userStory.getUserStoryStatus().getDescription().getText(),
                userStory.getDescription().getText(), userStory.getTimeEstimate(), userStory.getPriority(), userStory.getIdUserStory());
    }


    /**
     * Method to change data in to a Resource DTO
     **/
    public List<UserStoryStatusDTO> toDtoList(List<UserStory> userStoryList) {
        List<UserStoryStatusDTO> userStoryDtoList = new ArrayList<>();
        for (UserStory i : userStoryList) {
            userStoryDtoList.add(toDTO(i));
        }
        return userStoryDtoList;
    }
}
