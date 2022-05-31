package switch2021.project.dtoModel.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.dtoModel.dto.UserProfileDTO;
import switch2021.project.entities.aggregates.UserProfile.UserProfile;
import switch2021.project.entities.valueObjects.vos.Description;
import switch2021.project.entities.valueObjects.vos.UserProfileID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserProfileMapperTest {

    @Test
    @DisplayName("toDTO test")
    public void toDTOSuccess() {
        //Arrange
        UserProfileMapper mapper = new UserProfileMapper();
        UserProfile userProfile = mock(UserProfile.class);
        UserProfileID userProfileID = mock(UserProfileID.class);
        Description description = mock(Description.class);
        //Act
        when(userProfile.getUserProfileId()).thenReturn(userProfileID);
        when(userProfileID.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("User Profile");
        UserProfileDTO userProfileDTO = mapper.toDto(userProfile);
        //Assert
        assertEquals(userProfile.getUserProfileId().getUserProfileName().getText(), userProfileDTO.description);
    }

    @Test
    @DisplayName("Test UserProfileDTO")
    public void userProfileDTO(){
        //Arrange
        UserProfileMapper userProfileMapper = new UserProfileMapper();
        UserProfile userProfile = mock(UserProfile.class);
        UserProfileID userProfileID = mock(UserProfileID.class);
        Description description = mock(Description.class);
        //Act
        when(userProfile.getUserProfileId()).thenReturn(userProfileID);
        when(userProfileID.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("User Profile");
        UserProfileDTO userProfileDTO = userProfileMapper.toDto(userProfile);
        //Assert
        assertEquals(userProfileDTO.getDescription(), "User Profile");
    }
}
