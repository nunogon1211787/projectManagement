package switch2021.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.model.Company;
import switch2021.project.model.UserProfile;

import static org.junit.jupiter.api.Assertions.*;


public class CreateUserProfileControllerTest {


    @Test
    @DisplayName("Create new profile with success")
    public void createProfileWithSuccess() {
        // Arrange
        Company company = new Company();
        CreateUserProfileController createUserProfileController = new CreateUserProfileController(company);
        String name = "Cris_Dani";
        UserProfile up = new UserProfile(name);
        // Act
      createUserProfileController.createUserProfile(name);
        // Assert
        assertNotNull(name);
        assertEquals(name, up.getUserProfileName());


    }



    @Test
    @DisplayName("Return exception message, no name for profile inserted")
    public void createProfileWithEmptyName() {
        // Arrange
        Company company = new Company();
        CreateUserProfileController createUserProfileController = new CreateUserProfileController(company);
        String name = "";
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> createUserProfileController.createUserProfile(name));
        // Assert
        assertTrue(exception.getMessage().contains("Name cannot be blank."));
    }

    @Test
    @DisplayName("Return exception message, profile already exist")
    public void createProfileAlreadyExist() {
        // Arrange
        Company company = new Company();
        CreateUserProfileController createUserProfileController = new CreateUserProfileController(company);
        String name = "Cris_Dani";
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            createUserProfileController.createUserProfile(name);
            createUserProfileController.createUserProfile(name);
        });
        // Assert
        assertTrue(exception.getMessage().contains("Repeated user profile name inserted."));
    }

}
