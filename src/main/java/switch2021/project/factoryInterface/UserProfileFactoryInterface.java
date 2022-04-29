package switch2021.project.factoryInterface;

import org.springframework.stereotype.Component;
import switch2021.project.model.UserProfile.UserProfile;

@Component
public interface UserProfileFactoryInterface {

    UserProfile createUserProfile (String description);
}
