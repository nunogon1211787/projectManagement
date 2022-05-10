package switch2021.project.model.valueObject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.model.Typology.Typology;


import static org.junit.jupiter.api.Assertions.*;

class UserProfileIdTest {

    @Test
    @DisplayName("CreateUserProfile")
    public void createUserProfileTest()
    {
        //Arrange
       UserProfileId userProfile = new UserProfileId(new Description("Teste"));
       //Assert
       assertEquals("Teste", userProfile.getUserProfileName().getText());
    }

    @Test
    @DisplayName("Throw exception when creating profile")
    public void shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new UserProfileId(new Description("")));
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTests() {
        // Arrange
        UserProfileId description = new UserProfileId(new Description("Teste"));
        UserProfileId description2 = new UserProfileId(new Description("Teste"));
        UserProfileId description3 = null;
        // Act
        assertEquals(description,description2);
        assertNotEquals(description, description3);
    }

}

