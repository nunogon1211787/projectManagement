package switch2021.project.factory;

import org.springframework.beans.factory.annotation.Autowired;
import switch2021.project.dto.UserProfileDTO;
import org.springframework.stereotype.Component;
import switch2021.project.factoryInterface.IUserProfileFactory;
import switch2021.project.factoryInterface.IUserProfileIDFactory;
import switch2021.project.model.UserProfile.UserProfile;
import switch2021.project.model.valueObject.UserProfileID;

@Component
public class UserProfileFactory implements IUserProfileFactory {

    @Autowired
    private IUserProfileIDFactory idFactory;


    @Override
    public UserProfile createUserProfile(UserProfileDTO dto) {
        UserProfileID userProfileID = idFactory.createUserProfileID(dto.userProfileName);
        return new UserProfile(userProfileID);
    }
}


