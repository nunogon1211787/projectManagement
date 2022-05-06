package switch2021.project.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import switch2021.project.model.UserProfile.UserProfileReeng;
import switch2021.project.model.valueObject.Description;
import switch2021.project.model.valueObject.UserProfileId;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class UserProfileRepositoryReengTest {

    @DisplayName("Test constructor")
    @Test
    public void createUserProfile() {
        //Arrange
        UserProfileRepositoryReeng userProfileRepositoryReeng = new UserProfileRepositoryReeng();
        UserProfileId userProfileId = mock(UserProfileId.class);
        Description userProfileName = mock(Description.class);
        UserProfileReeng userProfileReeng = mock(UserProfileReeng.class);

        //Act
        when(userProfileId.getUserProfileName()).thenReturn(userProfileName);
        when(userProfileName.getText()).thenReturn("Developer");
        when(userProfileReeng.getUserProfileId()).thenReturn(userProfileId);
        userProfileRepositoryReeng.saveUserProfile(userProfileReeng);

        //Assert
        assertTrue(userProfileRepositoryReeng.existsByDescription("Developer"));
    }

    @DisplayName("Confirm if there is a User Profile created")
    @Test
    public void existsUserProfileByDescription() {
        //Arrange
        UserProfileRepositoryReeng userProfileRepositoryReeng = new UserProfileRepositoryReeng();
        UserProfileId userProfileId = mock(UserProfileId.class); //@Mock UserProfileId userProfileId;
        Description userProfileName = mock(Description.class);
        UserProfileReeng userProfileReeng = mock(UserProfileReeng.class);

        //Act
        when(userProfileId.getUserProfileName()).thenReturn(userProfileName);
        when(userProfileName.getText()).thenReturn("Developer");
        when(userProfileReeng.getUserProfileId()).thenReturn(userProfileId);
        userProfileRepositoryReeng.saveUserProfile(userProfileReeng);

        //Assert
        assertTrue(userProfileRepositoryReeng.existsByDescription("Developer"));
    }

    @DisplayName("Confirm if there is a User Profile already created")
    @Test
    public void repeatedUserProfileCreation() {
        //Arrange
        UserProfileRepositoryReeng userProfileRepositoryReeng = new UserProfileRepositoryReeng();
        UserProfileId userProfileId = mock(UserProfileId.class);
        Description userProfileName = mock(Description.class);
        UserProfileReeng userProfileReeng = mock(UserProfileReeng.class);

        //Act
        when(userProfileId.getUserProfileName()).thenReturn(userProfileName);
        when(userProfileName.getText()).thenReturn("Developer");
        when(userProfileReeng.getUserProfileId()).thenReturn(userProfileId);
        userProfileRepositoryReeng.saveUserProfile(userProfileReeng);

        //Assert
        assertFalse(userProfileRepositoryReeng.saveUserProfile(userProfileReeng));
    }
}
