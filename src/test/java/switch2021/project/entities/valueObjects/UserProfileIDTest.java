package switch2021.project.entities.valueObjects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.entities.valueObjects.vos.Description;
import switch2021.project.entities.valueObjects.vos.UserProfileID;


import static org.junit.jupiter.api.Assertions.*;

class UserProfileIDTest {

    @Test
    @DisplayName("CreateUserProfile")
    public void createUserProfileTest() {
        //Arrange
        Description des = new Description("Teste");
        UserProfileID userProfile = new UserProfileID();
        userProfile.setUserProfileName(des);
        //Assert
        assertEquals("Teste", userProfile.getUserProfileName().getText());
    }

    @Test
    @DisplayName("Throw exception when creating profile")
    public void shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new UserProfileID(new Description("")));
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTests() {
        // Arrange
        UserProfileID description = new UserProfileID(new Description("Teste"));
        UserProfileID description2 = new UserProfileID(new Description("Teste"));
        UserProfileID description3 = null;
        // Act
        assertEquals(description, description2);
        assertNotEquals(description, description3);
    }

}

