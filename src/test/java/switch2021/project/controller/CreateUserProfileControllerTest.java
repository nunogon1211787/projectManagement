package switch2021.project.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class CreateUserProfileControllerTest {


    @Test
    public void createProfileWithSuccess() {
        CreateUserProfileController createUserProfileController = new CreateUserProfileController();
        String name = "Cris_Dani";
        boolean userProfileCreated = createUserProfileController.createProfile(name);
        assertTrue(userProfileCreated);
    }

    @Test
    public void createProfileWithEmptyName() {
        // Arrange
        CreateUserProfileController createUserProfileController = new CreateUserProfileController();
        String name = "";
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            createUserProfileController.createProfile(name);
        });
        // Assert
        assertTrue(exception.getMessage().contains("Name cannot be blank."));
    }

    @Test
    public void createProfileAlreadyExist() {
        // Arrange
        CreateUserProfileController createUserProfileController = new CreateUserProfileController();
        String name = "Cris_Dani";
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            createUserProfileController.createProfile(name);
            createUserProfileController.createProfile(name);
        });
        // Assert
        assertTrue(exception.getMessage().contains("Repeated user profile name inserted."));
    }

}
