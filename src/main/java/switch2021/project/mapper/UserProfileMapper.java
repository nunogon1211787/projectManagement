package switch2021.project.mapper;

import org.springframework.stereotype.Component;
import switch2021.project.dto.UserProfileDTO;
import switch2021.project.model.UserProfile.UserProfile;

@Component
public class UserProfileMapper {

    public UserProfileDTO toDto(UserProfile newUserProfile) {
        String userProfileName = newUserProfile.getUserProfileId().getUserProfileName().getText();
        return new UserProfileDTO(userProfileName);

    }
}
