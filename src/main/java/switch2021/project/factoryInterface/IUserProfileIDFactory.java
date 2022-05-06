package switch2021.project.factoryInterface;

import switch2021.project.model.valueObject.UserProfileId;

public interface IUserProfileIDFactory {
    UserProfileId createUserProfileID (String userProfileName);
}
