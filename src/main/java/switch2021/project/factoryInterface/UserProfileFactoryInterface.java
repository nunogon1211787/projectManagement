package switch2021.project.factoryInterface;

import org.springframework.stereotype.Component;
import switch2021.project.dto.UserProfileDTO;
import switch2021.project.model.UserProfile.UserProfile;
import switch2021.project.model.UserProfile.UserProfileReeng;

@Component
public interface UserProfileFactoryInterface {

    UserProfileReeng createUserProfile (UserProfileDTO dto);
}
