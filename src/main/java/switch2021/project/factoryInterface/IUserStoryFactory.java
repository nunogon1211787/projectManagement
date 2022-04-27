package switch2021.project.factoryInterface;

import switch2021.project.model.UserStory.UserStory;

public interface IUserStoryFactory {

    public UserStory createUserStory(String projectId, String userStoryId, String name, int priority, String description, double estimateEffort);

}
