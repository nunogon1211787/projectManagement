package switch2021.project.mapper;

import org.springframework.stereotype.Component;
import switch2021.project.dto.UserProfileDTO;
import switch2021.project.model.UserProfile.UserProfile;
import switch2021.project.model.UserProfile.UserProfileReeng;


@Component
public class UserProfileMapper {

    private UserProfileMapper() {
    }

    public UserProfileDTO toDto(UserProfileReeng newUserProfile) {
        String userProfileName = newUserProfile.getUserProfileId().getUserProfileName().getText();
        return new UserProfileDTO(userProfileName);

    }
}
