package switch2021.project.factory;

import switch2021.project.dto.UserProfileDTO;
import switch2021.project.factoryInterface.UserProfileFactoryInterface;
import switch2021.project.model.UserProfile.UserProfile;
import switch2021.project.model.UserProfile.UserProfileReeng;
import switch2021.project.model.valueObject.Description;
import switch2021.project.model.valueObject.UserProfileId;


public class UserProfileFactory implements UserProfileFactoryInterface {

    @Override
    public UserProfileReeng createUserProfile(UserProfileDTO dto) {
        UserProfileId userProfileID = new UserProfileId (dto.userProfileName);
        return new UserProfileReeng(userProfileID);
    }
}


