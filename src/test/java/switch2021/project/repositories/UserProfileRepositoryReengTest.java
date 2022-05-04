package switch2021.project.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import switch2021.project.model.UserProfile.UserProfileReeng;
import switch2021.project.model.valueObject.Description;
import switch2021.project.model.valueObject.UserProfileId;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class UserProfileRepositoryReengTest {

    @DisplayName("Test controller")
    @Test
    public void createUserProfile() {
        //Arrange
        UserProfileRepositoryReeng userProfileRepositoryReeng = new UserProfileRepositoryReeng();
        UserProfileId userProfileId = mock(UserProfileId.class);
        Description userProfileName = mock(Description.class);
        UserProfileReeng userProfile = new UserProfileReeng(userProfileId);

        //Act
        when(userProfileId.getUserProfileName()).thenReturn(userProfileName);
        when(userProfileName.getText()).thenReturn("Developer");
        userProfileRepositoryReeng.saveUserProfile(userProfile);

        //Assert
        assertEquals(1,userProfileRepositoryReeng.getUserProfileList().size());
    }

    @DisplayName("Confirm if there is a User Profile created")
    @Test
    public void findUserProfileByDescription() {
        //Arrange
        UserProfileRepositoryReeng userProfileRepositoryReeng = new UserProfileRepositoryReeng();
        UserProfileId userProfileId = mock(UserProfileId.class);
        Description userProfileName = mock(Description.class);
        UserProfileReeng userProfile = new UserProfileReeng(userProfileId);

        //Act
        when(userProfileId.getUserProfileName()).thenReturn(userProfileName);
        when(userProfileName.getText()).thenReturn("Developer");
        userProfileRepositoryReeng.saveUserProfile(userProfile);

        //Assert
        assertNotNull(userProfileRepositoryReeng.findUserProfileByDescription("Developer"));
    }
}
