package switch2021.project.controller;

import org.junit.jupiter.api.Test;
import switch2021.project.model.*;
import switch2021.project.utils.App;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CreateUserProfileControllerTest {

    @Test
    public void createProfileWithSucess() {
        CreateUserProfileController createUserProfileController = new CreateUserProfileController();
        String name = "Cris_Dani";
        String profileCreated = createUserProfileController.createProfile(name);
        assertEquals("Profile created.", profileCreated);
    }

    @Test
    public void createProfileWithEmptyName() {
        CreateUserProfileController createUserProfileController = new CreateUserProfileController();
        String name = "";
        String profileCreated = createUserProfileController.createProfile(name);
        assertEquals("Name cannot be blank.", profileCreated);
    }

}
