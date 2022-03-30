package switch2021.project.valueObject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserProfileTest {

    @Test
    @DisplayName("Create Valid UserProfile")
    public void shouldCreateAValidProfile()
    {
        //Arrange
       UserProfile userProfile = new UserProfile("Teste");
       //Assert
       assertEquals("Teste", userProfile.getUserProfileName().getText());
    }

    @Test
    @DisplayName("Throw exception when creating profile")
    public void shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {

            new UserProfile("");
        });
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTests() {
        // Arrange
        UserProfile description = new UserProfile("Teste");
        UserProfile description2 = new UserProfile("Teste");
        UserProfile description3 = null;
        Typology test = new Typology("test");
        // Act
        assertEquals(description,description2);
        assertNotEquals(description, description3);
        assertNotEquals(description, test);
    }

}

