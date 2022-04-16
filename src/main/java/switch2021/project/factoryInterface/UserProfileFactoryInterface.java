package switch2021.project.factoryInterface;

import switch2021.project.model.UserProfile.UserProfile;
import switch2021.project.model.valueObject.Description;

public interface UserProfileFactoryInterface {

    UserProfile createUserProfile (String userProfileDescription);
}
