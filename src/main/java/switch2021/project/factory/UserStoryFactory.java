package switch2021.project.factory;

import org.springframework.stereotype.Component;
import switch2021.project.factoryInterface.IUserStoryFactory;
import switch2021.project.model.UserStory.UserStory;

@Component
public class UserStoryFactory implements IUserStoryFactory {


    @Override
    public UserStory createUserStory(String projectID, String userStoryID, String title, int priority, String description, double timeEstimate) {
        return (new UserStory(projectID, userStoryID,  title, priority, description, timeEstimate));
    }

}