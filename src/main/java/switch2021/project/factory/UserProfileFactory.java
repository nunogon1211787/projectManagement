package switch2021.project.factory;

import org.springframework.beans.factory.annotation.Autowired;
import switch2021.project.dto.UserProfileDTO;
import org.springframework.stereotype.Component;
import switch2021.project.factoryInterface.IUserProfileIDFactory;
import switch2021.project.factoryInterface.UserProfileFactoryInterface;
import switch2021.project.model.UserProfile.UserProfile;
import switch2021.project.model.UserProfile.UserProfileReeng;
import switch2021.project.model.valueObject.Description;
import switch2021.project.model.valueObject.SystemUserID;
import switch2021.project.model.valueObject.UserProfileId;

@Component
public class UserProfileFactory implements UserProfileFactoryInterface {

    @Autowired
    private IUserProfileIDFactory idFactory;

    public UserProfileFactory(IUserProfileIDFactory idFactory) {
        this.idFactory = idFactory;
    }

    @Override
    public UserProfileReeng createUserProfile(UserProfileDTO dto) {
        UserProfileId userProfileID = idFactory.createUserProfileID(dto.userProfileName);
        return new UserProfileReeng(userProfileID);
    }
}


