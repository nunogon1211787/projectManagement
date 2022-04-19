package switch2021.project.factory;

import switch2021.project.factoryInterface.UserProfileFactoryInterface;
import switch2021.project.model.UserProfile.UserProfile;
import switch2021.project.model.valueObject.Description;


public class UserProfileFactory implements UserProfileFactoryInterface {

    @Override
    public UserProfile createUserProfile(String description) {

        return new UserProfile(description);
    }
}


