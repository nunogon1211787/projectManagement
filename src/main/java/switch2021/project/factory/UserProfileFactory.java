package switch2021.project.factory;

import switch2021.project.dto.UserProfileDTO;
import org.springframework.stereotype.Component;
import switch2021.project.factoryInterface.UserProfileFactoryInterface;
import switch2021.project.model.UserProfile.UserProfile;
import switch2021.project.model.valueObject.Description;

@Component
public class UserProfileFactory implements UserProfileFactoryInterface {

    @Override
    public UserProfile createUserProfile(UserProfileDTO dto) {

        return new UserProfile(dto.userProfileName);
    }
}


