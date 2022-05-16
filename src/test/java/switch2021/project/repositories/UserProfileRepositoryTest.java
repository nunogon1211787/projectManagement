package switch2021.project.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.model.UserProfile.UserProfile;
import switch2021.project.model.valueObject.Description;
import switch2021.project.model.valueObject.UserProfileID;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class UserProfileRepositoryTest {

    @DisplayName("Test to find User Profile By Description, with success")
    @Test
    public void findUserProfile_Success() {
        //Arrange
        UserProfileRepository userProfileRepository = new UserProfileRepository();
        UserProfileID userProfileId = mock(UserProfileID.class);
        Description userProfileName = mock(Description.class);
        UserProfile userProfile = mock(UserProfile.class);

        //Act
        when(userProfileId.getUserProfileName()).thenReturn(userProfileName);
        when(userProfileName.getText()).thenReturn("Developer");
        when(userProfile.getUserProfileId()).thenReturn(userProfileId);
        userProfileRepository.saveUserProfile(userProfile);

        //Assert
        assertTrue(userProfileRepository.existsByDescription("Developer"));
    }

    @DisplayName("Test to find User Profile By Description, with failure")
    @Test
    public void findUserProfile_Failure() {
        //Arrange
        UserProfileRepository userProfileRepository = new UserProfileRepository();
        UserProfileID userProfileId = mock(UserProfileID.class);
        Description userProfileName = mock(Description.class);
        UserProfile userProfile = mock(UserProfile.class);

        //Act
        when(userProfileId.getUserProfileName()).thenReturn(userProfileName);
        when(userProfileName.getText()).thenReturn("Developer");
        when(userProfile.getUserProfileId()).thenReturn(userProfileId);
        userProfileRepository.saveUserProfile(userProfile);

        //Assert
        assertFalse(userProfileRepository.existsByDescription("Project Manager"));
    }

    @DisplayName("Test to save user profile")
    @Test
    public void userProfileSaveSuccess() {
        //Arrange
        UserProfileRepository userProfileRepository = new UserProfileRepository();
        UserProfile userProfile = mock(UserProfile.class);
        UserProfileID userProfileID = mock(UserProfileID.class);
        Description description = mock(Description.class);
        //Act
        when(userProfile.getUserProfileId()).thenReturn(userProfileID);
        when(userProfileID.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("User Profile");
        //Assert
        assertTrue(userProfileRepository.saveUserProfile(userProfile));
    }

    @DisplayName("Confirm if there is a User Profile already created")
    @Test
    public void repeatedUserProfileCreation() {
        //Arrange
        UserProfileRepository userProfileRepository = new UserProfileRepository();
        UserProfileID userProfileId = mock(UserProfileID.class);
        Description userProfileName = mock(Description.class);
        UserProfile userProfile = mock(UserProfile.class);

        //Act
        when(userProfileId.getUserProfileName()).thenReturn(userProfileName);
        when(userProfileName.getText()).thenReturn("Developer");
        when(userProfile.getUserProfileId()).thenReturn(userProfileId);
        userProfileRepository.saveUserProfile(userProfile);

        //Assert
        assertFalse(userProfileRepository.saveUserProfile(userProfile));
    }

    @Test
    @DisplayName("Find Description Null Test")
    public void findUserProfileWithDescriptionNullTest() {
        //Arrange
        UserProfileRepository userProfileRepository = new UserProfileRepository();
        // Act
        UserProfile x = userProfileRepository.findUserProfileByDescription("User Profile");
        //Assert
        assertNull(x);
    }

    @Test
    @DisplayName("Find All Description List Test")
    public void findUserProfileListTest() {
        //Arrange
        UserProfileRepository userProfileRepository = new UserProfileRepository();
        UserProfile user1 = mock(UserProfile.class);
        UserProfile user2 = mock(UserProfile.class);
        UserProfileID id1 = mock(UserProfileID.class);
        UserProfileID id2 = mock(UserProfileID.class);
        Description description1 = mock(Description.class);
        Description description2 = mock(Description.class);
        //Act
        when(user1.getUserProfileId()).thenReturn(id1);
        when(user2.getUserProfileId()).thenReturn(id2);
        when(id1.getUserProfileName()).thenReturn(description1);
        when(id2.getUserProfileName()).thenReturn(description2);
        when(description1.getText()).thenReturn("User Profile 1");
        when(description2.getText()).thenReturn("User Profile 2");
        userProfileRepository.saveUserProfile(user1);
        userProfileRepository.saveUserProfile(user2);
        List<UserProfile> list = userProfileRepository.findAllUserProfiles();
        //Assert
        assertEquals(2, list.size());
    }

    @Test
    @DisplayName("Override Test")
    public void overrideTest_1() {
        //Arrange
        UserProfileRepository userProfileRepository = new UserProfileRepository();
        UserProfile user1 = mock(UserProfile.class);
        UserProfile user2 = mock(UserProfile.class);
        UserProfileID id1 = mock(UserProfileID.class);
        UserProfileID id2 = mock(UserProfileID.class);
        Description description1 = mock(Description.class);
        Description description2 = mock(Description.class);
        //Act
        when(user1.getUserProfileId()).thenReturn(id1);
        when(user2.getUserProfileId()).thenReturn(id2);
        when(id1.getUserProfileName()).thenReturn(description1);
        when(id2.getUserProfileName()).thenReturn(description2);
        when(description1.getText()).thenReturn("User Profile 1");
        when(description2.getText()).thenReturn("User Profile 2");
        userProfileRepository.saveUserProfile(user1);
        userProfileRepository.saveUserProfile(user2);
        List<UserProfile> list = userProfileRepository.findAllUserProfiles();
        //Assert
        assertEquals(user1, user1);
        assertNotEquals(user1, user2);
        assertEquals(list, list);
    }

    @Test
    @DisplayName("HashCode Test")
    public void hasCodeTest_1() {
        //Arrange
        UserProfileRepository userProfileRepository = new UserProfileRepository();
        UserProfile user1 = mock(UserProfile.class);
        UserProfile user2 = mock(UserProfile.class);
        UserProfileID id1 = mock(UserProfileID.class);
        UserProfileID id2 = mock(UserProfileID.class);
        Description description1 = mock(Description.class);
        Description description2 = mock(Description.class);
        //Act
        when(user1.getUserProfileId()).thenReturn(id1);
        when(user2.getUserProfileId()).thenReturn(id2);
        when(id1.getUserProfileName()).thenReturn(description1);
        when(id2.getUserProfileName()).thenReturn(description2);
        when(description1.getText()).thenReturn("User Profile 1");
        when(description2.getText()).thenReturn("User Profile 2");
        userProfileRepository.saveUserProfile(user1);
        userProfileRepository.saveUserProfile(user2);
        List<UserProfile> list = userProfileRepository.findAllUserProfiles();
        //Assert
        assertEquals(user1.hashCode(), user1.hashCode());
        assertNotEquals(user1.hashCode(), user2.hashCode());
        assertEquals(list.hashCode(), list.hashCode());
    }
}
