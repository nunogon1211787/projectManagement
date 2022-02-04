package switch2021.project.mapper;

import switch2021.project.dto.ScrumBoardDTO;
import switch2021.project.model.UserStory;
import java.util.ArrayList;
import java.util.List;

public class ScrumBoardMapper {

    /**
     * Method to change data in to a Scrum Board DTO
     **/

    public ScrumBoardDTO toDTO (UserStory userStory) {
        return new ScrumBoardDTO(userStory.getName(),userStory.getUserStoryStatus().getDescription(),
                userStory.getDescription(), userStory.getTimeEstimate(), userStory.getPriority(), userStory.getId_UserStory());
    }

    /**
     * Method to change data in to a Resource DTO
     **/

    public List<ScrumBoardDTO> toDtoList(List<UserStory> userStoryList){
        List<ScrumBoardDTO> userStoryDtoList = new ArrayList<>();
        for(UserStory i: userStoryList) {
            userStoryDtoList.add(toDTO(i));
        }
        return userStoryDtoList;
    }
}
