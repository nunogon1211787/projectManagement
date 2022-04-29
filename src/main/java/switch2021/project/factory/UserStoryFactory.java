package switch2021.project.factory;

import org.springframework.stereotype.Component;
import switch2021.project.dto.UserStoryDTO;
import switch2021.project.factoryInterface.IUserStoryFactory;
import switch2021.project.model.UserStory.*;
import switch2021.project.model.valueObject.Description;
import switch2021.project.model.valueObject.ProjectID;

@Component
public class UserStoryFactory implements IUserStoryFactory {


    @Override
    public UserStory createUserStory(UserStoryDTO UserStoryDTO) {
        ProjectID newProjectID = new ProjectID(UserStoryDTO.projectID);
        UsTitle newUsTitle = new UsTitle(UserStoryDTO.title);
        UserStoryID newUserStoryID = new UserStoryID(newProjectID, newUsTitle);

        UserStory newUserStory = new UserStory(newUserStoryID);

        UsPriority newPriority = new UsPriority(UserStoryDTO.priority);
        Description newDescription = new Description(UserStoryDTO.description);
        UsHour newTimeEstimate = new UsHour(UserStoryDTO.timeEstimate);

        newUserStory.setPriority(newPriority);
        newUserStory.setDescription(newDescription);
        newUserStory.setTimeEstimate(newTimeEstimate);

        return (newUserStory);
    }

}