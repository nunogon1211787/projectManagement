package switch2021.project.mapper;

import org.springframework.stereotype.Service;
import switch2021.project.dto.OutPutUsDTO;
import switch2021.project.model.UserStory.UserStory;

@Service
public class UserStoryMapper {



    public OutPutUsDTO toDto(UserStory newUserStory) {
        String projectID = newUserStory.getUserStoryID().getProjectID().getCode();
        String userStoryID = newUserStory.getUserStoryID().toString();
        String title = newUserStory.getUserStoryID().getUsTitle().getTitleUs();
        int priority = newUserStory.getPriority().getPriorityUs();
        String description = newUserStory.getDescription().getText();
        double timeEstimate = newUserStory.getTimeEstimate().getUsHours();
        return new OutPutUsDTO(userStoryID, projectID, title, priority, description, timeEstimate);


    }

}
