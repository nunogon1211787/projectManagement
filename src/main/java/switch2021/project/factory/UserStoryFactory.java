package switch2021.project.factory;

import switch2021.project.factoryInterface.UserStoryFactoryInterface;
import switch2021.project.model.UserStory.UserStory;

public class UserStoryFactory implements UserStoryFactoryInterface {

    public UserStory createUserStory(String name, int priority, String description, int estimateEffort) {
        return new UserStory(name, priority, description, estimateEffort);
    }
}