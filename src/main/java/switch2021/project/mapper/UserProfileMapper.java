package switch2021.project.mapper;

import org.springframework.stereotype.Component;
import switch2021.project.dto.UserProfileDTO;
import switch2021.project.model.UserProfile.UserProfile;
import switch2021.project.model.UserStory.UserStory;

@Component
public class UserProfileMapper {

    private UserProfileMapper() {
    }

    public UserProfileDTO toDto(UserProfile newUserProfile) {
        UserProfileDTO outDto = new UserProfileDTO();
        outDto.userProfileName = newUserProfile.getUserProfileId().getUserProfileName().getText();
        return outDto;

    }
}
