package switch2021.project.applicationServices.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import switch2021.project.dtoModel.dto.OutputUserProfileDTO;
import switch2021.project.dtoModel.dto.UserProfileDTO;
import switch2021.project.entities.factories.factoryInterfaces.IUserProfileFactory;
import switch2021.project.applicationServices.iRepositories.IUserProfileRepo;
import switch2021.project.dtoModel.mapper.UserProfileMapper;
import switch2021.project.entities.aggregates.UserProfile.UserProfile;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserProfileServiceTest {

    @Mock
    UserProfileDTO userProfileDTO;
    @Mock
    OutputUserProfileDTO outputUserProfileDTO;


    @InjectMocks
    UserProfileService createUserProfileService;
    @Mock
    IUserProfileRepo iUserProfileRepo;
    @Mock
    UserProfileMapper userProfileMapper;
    @Mock
    IUserProfileFactory iUserProfileFactory;
    @Mock
    UserProfile userProfile;


    @Test
    @DisplayName("Test to create and save user profile")
    public void createAndSaveUserProfile() throws Exception {
        //Arrange
       /* UserProfileDTO userProfileDTO = mock(UserProfileDTO.class);
        OutputUserProfileDTO outputUserProfileDTO = mock(OutputUserProfileDTO.class);*/

        when(iUserProfileFactory.createUserProfile(userProfileDTO)).thenReturn(userProfile);
        when(iUserProfileRepo.save(userProfile)).thenReturn(Optional.of(userProfile));
        when(userProfileMapper.toDTO(userProfile)).thenReturn(outputUserProfileDTO);

        //Act
        OutputUserProfileDTO dto = createUserProfileService.createAndSaveUserProfile(userProfileDTO);

        //Assert
        assertEquals(dto.userProfileName, createUserProfileService.createAndSaveUserProfile(userProfileDTO).userProfileName);
    }

/*    @Test
    @DisplayName("Test to create a repeated user profile")
    public void createAndSaveRepeatedUserProfile() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {

            //Arrange
            UserProfileDTO userProfileDTO = mock(UserProfileDTO.class);
            OutputUserProfileDTO outputUserProfileDTO = mock(OutputUserProfileDTO.class);

            when(iUserProfileFactory.createUserProfile(userProfileDTO)).thenReturn(userProfile);
            when(iUserProfileRepo.save(userProfile)).thenReturn(Optional.empty());
            when(userProfileMapper.toDTO(userProfile)).thenReturn(outputUserProfileDTO);

            //Act
            createUserProfileService.createAndSaveUserProfile(userProfileDTO);
        });
    }*/
}
