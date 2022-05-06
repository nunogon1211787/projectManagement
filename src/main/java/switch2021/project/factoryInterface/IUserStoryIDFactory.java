package switch2021.project.factoryInterface;


import switch2021.project.model.valueObject.UserStoryID;


public interface IUserStoryIDFactory {
    UserStoryID create(String projectID, String usTitle);
}
