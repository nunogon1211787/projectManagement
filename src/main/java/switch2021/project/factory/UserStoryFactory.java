package switch2021.project.factory;

import switch2021.project.factoryInterface.UserStoryFactoryInterface;
import switch2021.project.model.UserStory.UserStory;

public class UserStoryFactory implements UserStoryFactoryInterface {

    @Override
    public UserStory createUserStory(String userStoryId, String title, int priority, String description, int estimateEffort) {
        return new UserStory(userStoryId,  title, priority, description, estimateEffort);
    }
}