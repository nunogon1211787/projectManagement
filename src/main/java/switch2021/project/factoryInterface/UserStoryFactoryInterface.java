package switch2021.project.factoryInterface;

import switch2021.project.model.Project.UserStory;

public interface UserStoryFactoryInterface {

    UserStory createUserStory(String name, int priority, String description, int estimateEffort);
}
