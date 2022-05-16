package switch2021.project.factoryInterface;

import switch2021.project.dto.UserProfileDTO;
import switch2021.project.model.UserProfile.UserProfile;

public interface IUserProfileFactory {

    UserProfile createUserProfile (UserProfileDTO dto);
}
