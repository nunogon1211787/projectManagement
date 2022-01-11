package switch2021.project.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import switch2021.project.model.*;
import switch2021.project.utils.App;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateProfileControllerTest {

    Company company;
    Profile profile;


    @BeforeEach
    public void init() {
        company = App.getInstance().getCompany(); // sempre a mesma instancia
        profile = company.createProfile("Cris", "System Profile");
        company.addProfile(profile);
    }

    @Test
    public void createProfileAlreadyExist() {
        CreateProfileController createProfileController = new CreateProfileController();
        boolean ProfileCreated = createProfileController.createProfile(profile.getName(), profile.getType());
        assertFalse(ProfileCreated);
    }


    @Test
    public void createProfileWithSucess() {
        CreateProfileController createProfileController = new CreateProfileController();
        String name = "Cris_Dani";
        String type = "System Profile";
        boolean profileCreated = createProfileController.createProfile(name, type);
        assertTrue(profileCreated);
    }

    @Test
    public void createProfileWithEmptyName(){
        CreateProfileController createProfileController = new CreateProfileController();
        String name = "";
        String type = "System Profile";
        boolean profileCreated = createProfileController.createProfile(name, type);
        assertFalse(profileCreated);
    }

    @Test
    public void createProfileWithEmptyProfile(){
        CreateProfileController createProfileController = new CreateProfileController();
        String name = "Cris_Dani";
        String type = "";
        boolean profileCreated = createProfileController.createProfile(name, type);
        assertFalse(profileCreated);
    }

    @Test
    public void createProfileWithInvalidProfileType(){
        CreateProfileController createProfileController = new CreateProfileController();
        String name = "Cris_Dani";
        String type = "teste";
        boolean profileCreated = createProfileController.createProfile(name, type);
        assertFalse(profileCreated);
    }
}
