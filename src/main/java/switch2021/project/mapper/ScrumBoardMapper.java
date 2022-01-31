package switch2021.project.mapper;

import switch2021.project.dto.ScrumBoardDTO;
import switch2021.project.model.UserStory;

public class ScrumBoardMapper {
    public static ScrumBoardDTO toDTO (UserStory userStory) {
        return new ScrumBoardDTO(userStory.getName(),userStory.getUserStoryStatus().getDescription(),
                userStory.getDescription(), userStory.getTimeEstimate(), userStory.getPriority());
    }


}
