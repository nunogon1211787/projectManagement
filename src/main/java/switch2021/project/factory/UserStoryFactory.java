package switch2021.project.factory;

import switch2021.project.model.UserStory;

public class UserStoryFactory {

    public UserStory createUserStory(String name, int priority, String description, int estimateEffort) {
        return new UserStory(name, priority, description, estimateEffort);
    }

}