package switch2021.project.factoryInterface;

import switch2021.project.model.valueObject.UserProfileID;

public interface IUserProfileIDFactory {
    UserProfileID createUserProfileID (String userProfileName);
}
