package switch2021.project.entities.aggregates.UserProfile;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.entities.valueObjects.vos.Description;
import switch2021.project.entities.valueObjects.vos.UserProfileID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserProfileTest {

    @Test
    @DisplayName("Constructor Test, with success")
    void ConstructorUserProfile_Success() {
        //Arrange
        UserProfileID userProfileID = mock(UserProfileID.class);
        UserProfile userProfile = new UserProfile(userProfileID);
        Description description = mock(Description.class);
        //Act
        when(userProfileID.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("User Profile");
        //Assert
        assertEquals(userProfileID, userProfile.getUserProfileId());
    }

    @Test
    @DisplayName("Constructor Test, with failure")
    void ConstructorUserProfile_Failure() {
        //Arrange
        UserProfileID userProfileID = mock(UserProfileID.class);
        UserProfile userProfile = new UserProfile(userProfileID);
        Description description = mock(Description.class);
        //Act
        when(userProfileID.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("User Profile");
        //Assert
        assertNotEquals("Profile User", userProfile.getUserProfileId().getUserProfileName().getText());
    }

    @Test
    @DisplayName("HashCode Verification")
    public void hashCodeSuccess() {
        //Arrange
        UserProfileID userProfileID = mock(UserProfileID.class);
        UserProfile userProfile = new UserProfile(userProfileID);
        UserProfile userProfile2 = new UserProfile(userProfileID);
        //Act
        int id1 = userProfile.hashCode();
        int id2 = userProfile2.hashCode();
        //Assert
        assertEquals(id1, id2);
    }

    @Test
    @DisplayName("HashCode Verification")
    public void hashCodeFail() {
        //Arrange
        UserProfileID userProfileID = mock(UserProfileID.class);
        UserProfileID userProfileID2 = mock(UserProfileID.class);
        UserProfile userProfile = new UserProfile(userProfileID);
        UserProfile userProfile2 = new UserProfile(userProfileID2);
        //Act
        int id1 = userProfile.hashCode();
        int id2 = userProfile2.hashCode();
        //Assert
        assertNotEquals(id1, id2);
    }

    @Test
    @DisplayName("Override Verification")
    public void overrideTest() {
        //Arrange
        Description description = new Description("Description");
        Description description1 = new Description("Description 1");
        UserProfileID userProfileID = new UserProfileID(description);
        UserProfile userProfile = new UserProfile(userProfileID);
        UserProfileID userProfileID1 = new UserProfileID(description1);
        UserProfile userProfile1 = new UserProfile(userProfileID);
        UserProfile userProfile2 = new UserProfile(userProfileID1);
        //Assert
        assertEquals(userProfile, userProfile);
        assertNotEquals(userProfile2, userProfile1);
    }

    @Test
    @DisplayName("Override Verification")
    public void overrideTestTrue() {
        //Arrange
        UserProfileID userProfileID = mock(UserProfileID.class);
        UserProfile userProfile1 = new UserProfile(userProfileID);
        UserProfile userProfile2 = new UserProfile(userProfileID);
        //Act
        when(userProfileID.sameValueAs(userProfileID)).thenReturn(true);
        boolean expected = userProfile1.equals(userProfile2);
        // Assert
        assertTrue(expected);
    }

    @Test
    @DisplayName("Override Verification")
    public void overrideTestFalse() {
        //Arrange
        UserProfileID userProfileID = mock(UserProfileID.class);
        UserProfileID userProfileID2 = mock(UserProfileID.class);
        UserProfile userProfile1 = new UserProfile(userProfileID);
        UserProfile userProfile2 = new UserProfile(userProfileID2);
        //Act
        boolean expected = userProfile1.equals(userProfile2);
        // Assert
        assertFalse(expected);
    }

    @Test
    @DisplayName("Override Verification")
    public void overrideTestNull() {
        //Arrange
        UserProfileID userProfileID = mock(UserProfileID.class);
        UserProfile userProfile1 = new UserProfile(userProfileID);
        UserProfile userProfile2 = null;
        //Act
        boolean expected = userProfile1.equals(userProfile2);
        // Assert
        assertFalse(expected);
    }

    @Test
    @DisplayName("Same Identify As - True")
    public void sameIdentityAs_true() {
        //Arrange
        UserProfileID userProfileID = mock(UserProfileID.class);
        UserProfile userProfile1 = new UserProfile(userProfileID);
        UserProfile userProfile2 = new UserProfile(userProfileID);
        //Act
        when(userProfileID.sameValueAs(userProfileID)).thenReturn(true);
        boolean expected = userProfile1.sameIdentityAs(userProfile2);
        //Assert
        assertTrue(expected);
    }

    @Test
    @DisplayName("Same Identify As - False")
    public void sameIdentityAs_false() {
        //Arrange
        UserProfileID userProfileID1 = mock(UserProfileID.class);
        UserProfileID userProfileID2 = mock(UserProfileID.class);
        UserProfile userProfile1 = new UserProfile(userProfileID1);
        UserProfile userProfile2 = new UserProfile(userProfileID2);
        //Act
        when(userProfileID1.sameValueAs(userProfileID2)).thenReturn(false);
        boolean expected = userProfile1.sameIdentityAs(userProfile2);
        //Assert
        assertFalse(expected);
    }

    @Test
    @DisplayName("Same Identify As - Null")
    public void sameIdentityAs_Null() {
        //Arrange
        UserProfileID userProfileID = mock(UserProfileID.class);
        UserProfile userProfile = new UserProfile(userProfileID);
        UserProfile userProfile1 = new UserProfile(null);
        //Act
        when(userProfileID.sameValueAs(null)).thenReturn(false);
        //Assert
        assertFalse(userProfile.sameIdentityAs(userProfile1));
    }
}
