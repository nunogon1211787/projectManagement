package switch2021.project.controller;

import org.junit.jupiter.api.Test;
import switch2021.project.model.*;
import switch2021.project.utils.App;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CreateUserProfileControllerTest {


    @Test
    public void createProfileAlreadyExist() {
        Company company = App.getInstance().getCompany(); // sempre a mesma instância
        UserProfile userProfile = company.getUserProfileStore().createProfile("Cris");
        company.getUserProfileStore().addUserProfile(userProfile);
        CreateUserProfileController createUserProfileController = new CreateUserProfileController();
        String ProfileCreated = createUserProfileController.createProfile(userProfile.getName());
        assertEquals("Repeated user profile name inserted.", ProfileCreated);
    }


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
