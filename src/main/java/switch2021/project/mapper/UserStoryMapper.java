package switch2021.project.mapper;

import org.springframework.stereotype.Service;
import switch2021.project.dto.IdDTO;
import switch2021.project.dto.OutputUserStoryDTO;
import switch2021.project.dto.UserStoryIdDTO;
import switch2021.project.model.UserStory.UserStory;

@Service
public class UserStoryMapper {



    public OutputUserStoryDTO toDto(UserStory newUserStory) {

        int priority = newUserStory.getPriority().getPriorityUs();
        String description = newUserStory.getDescription().getText();
        double timeEstimate = newUserStory.getTimeEstimate().getUsHours();

        String userStoryID = newUserStory.getUserStoryID().getProjectID().getCode() + "&" + newUserStory.getUserStoryID().getUsTitle().getTitleUs();

        return new OutputUserStoryDTO(userStoryID, priority, description, timeEstimate);


    }

}
