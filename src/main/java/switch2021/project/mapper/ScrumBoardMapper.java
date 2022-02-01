package switch2021.project.mapper;

import switch2021.project.dto.ScrumBoardDTO;
import switch2021.project.model.UserStory;
import java.util.ArrayList;
import java.util.List;

public class ScrumBoardMapper {

    public ScrumBoardDTO toDTO (UserStory userStory) {
        return new ScrumBoardDTO(userStory.getName(),userStory.getUserStoryStatus().getDescription(),
                userStory.getDescription(), userStory.getTimeEstimate(), userStory.getPriority());
    }

    public List<ScrumBoardDTO> toDtoList(List<UserStory> userStoryList){
        List<ScrumBoardDTO> userStoryDtoList = new ArrayList<>();
        for(UserStory i: userStoryList) {
            userStoryDtoList.add(toDTO(i));
        }
        return userStoryDtoList;
    }
}
