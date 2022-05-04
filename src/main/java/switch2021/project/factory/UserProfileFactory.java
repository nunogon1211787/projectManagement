package switch2021.project.factory;

import org.springframework.stereotype.Component;
import switch2021.project.factoryInterface.UserProfileFactoryInterface;
import switch2021.project.model.UserProfile.UserProfile;
import switch2021.project.model.valueObject.Description;

@Component
public class UserProfileFactory implements UserProfileFactoryInterface {

    @Override
    public UserProfile createUserProfile(String description) {

        return new UserProfile(description);
    }
}


