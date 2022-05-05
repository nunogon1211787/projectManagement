package switch2021.project.factoryInterface;


import switch2021.project.model.UserStory.UserStoryID;


public interface IUserStoryIDFactory {
    UserStoryID create(String projectID, String usTitle);
}
