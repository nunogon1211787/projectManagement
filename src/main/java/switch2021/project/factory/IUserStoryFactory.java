package switch2021.project.factory;

import switch2021.project.model.UserStory.UserStory;

public class IUserStoryFactory implements switch2021.project.factoryInterface.IUserStoryFactory {


    @Override
    public UserStory createUserStory(String projectId, String userStoryId, String title, int priority, String description, double timeEstimate) {
        return (new UserStory(projectId, userStoryId,  title, priority, description, timeEstimate));
    }

}