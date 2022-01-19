package switch2021.project.controller;

import org.junit.jupiter.api.Test;
import switch2021.project.model.Company;

import static org.junit.jupiter.api.Assertions.*;


public class CreateUserProfileControllerTest {


    @Test
    public void createProfileWithSuccess() {
        Company company = new Company();
        CreateUserProfileController createUserProfileController = new CreateUserProfileController(company);
        String name = "Cris_Dani";
        boolean userProfileCreated = createUserProfileController.createProfile(name);
        assertTrue(userProfileCreated);
    }

    @Test
    public void createProfileWithEmptyName() {
        // Arrange
        Company company = new Company();
        CreateUserProfileController createUserProfileController = new CreateUserProfileController(company);
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
        Company company = new Company();
        CreateUserProfileController createUserProfileController = new CreateUserProfileController(company);
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
