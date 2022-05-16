package switch2021.project.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import switch2021.project.dto.UserProfileDTO;
import switch2021.project.factoryInterface.IUserProfileFactory;
import switch2021.project.interfaces.IUserProfileRepo;
import switch2021.project.mapper.UserProfileMapper;
import switch2021.project.model.UserProfile.UserProfile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CreateUserProfileServiceTest {

    @InjectMocks
    CreateUserProfileService createUserProfileService;

    @Mock
    IUserProfileRepo iUserProfileRepo;
    @Mock
    UserProfileMapper userProfileMapper;
    @Mock
    IUserProfileFactory iUserProfileFactory;
    @Mock
    UserProfile userProfile;
    @Mock
    UserProfileDTO userProfileDTO;

    @Test
    @DisplayName("Test to create and save sprint - get SprintName")
    public void createAndSaveUserProfile() {

        //Arrange
        when(iUserProfileFactory.createUserProfile(userProfileDTO)).thenReturn(userProfile);
        when(iUserProfileRepo.saveUserProfile(userProfile)).thenReturn(true);
        when(userProfileMapper.toDto(userProfile)).thenReturn(userProfileDTO);
        //Act
        UserProfileDTO dto = createUserProfileService.createAndSaveUserProfile(userProfileDTO);
        //Assert
        assertEquals(dto.userProfileName, createUserProfileService.createAndSaveUserProfile(userProfileDTO).userProfileName);
    }

    @Test
    @DisplayName("Test to create a repeated user profile")
    public void createAndSaveRepeatedUserProfile() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            when(iUserProfileFactory.createUserProfile(userProfileDTO)).thenReturn(userProfile);
            when(iUserProfileRepo.saveUserProfile(userProfile)).thenReturn(false);
            when(userProfileMapper.toDto(userProfile)).thenReturn(userProfileDTO);
            //Act
            createUserProfileService.createAndSaveUserProfile(userProfileDTO);
        });
    }
}
