package switch2021.project.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import switch2021.project.dto.UserProfileDTO;
import switch2021.project.factoryInterface.IDescriptionFactory;
import switch2021.project.factoryInterface.IUserProfileIDFactory;
import switch2021.project.model.UserProfile.UserProfile;
import switch2021.project.model.valueObject.Description;
import switch2021.project.model.valueObject.UserProfileID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserProfileFactoryTest {

    @Mock
    private IDescriptionFactory iDescriptionFactory;
    @Mock
    private Description description;
    @Mock
    private IUserProfileIDFactory iUserProfileIDFactory;
    @Mock
    private UserProfileID userProfileID;
    @Mock
    UserProfileDTO userProfileDTO;

    @InjectMocks
    private UserProfileFactory userProfileFactory;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test to create a user profile, with success")
    public void createSprint_Success(){
        //Arrange

        when(iDescriptionFactory.createDescription(userProfileDTO.userProfileName)).thenReturn(description);
        when(iUserProfileIDFactory.createUserProfileID(userProfileDTO.userProfileName)).thenReturn(userProfileID);

        //Act
        UserProfile userProfile = userProfileFactory.createUserProfile(userProfileDTO);

        //Assert
        assertNotNull(userProfile);
    }
}

