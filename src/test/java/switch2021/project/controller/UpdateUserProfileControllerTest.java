package switch2021.project.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import switch2021.project.model.*;
import switch2021.project.model.SystemUser.SystemUser;
import switch2021.project.model.UserProfile.UserProfile;
import switch2021.project.stores.SystemUserStore;
import switch2021.project.stores.UserProfileStore;

import static org.junit.jupiter.api.Assertions.*;

public class UpdateUserProfileControllerTest {

    private Company company;
    private SystemUserStore systemUserStore;
    private UserProfileStore userProfileStore;
    private SystemUser user;


    @BeforeEach
    public void init() {
        company = new Company(); // sempre a mesma instancia
        systemUserStore = company.getSystemUserStore();
        userProfileStore = company.getUserProfileStore();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        user = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt",
                "tester", "Qwerty_1", "Qwerty_1", "IMG_123", userProfile);
        systemUserStore.saveSystemUser(user);
    }

    @Test
    public void constructorUpdateUserProfileTest() {
        //Arrange
        SystemUser user = systemUserStore.getUserByEmail("xxxx@isep.ipp.pt");
        Company company2 = new Company();
        UpdateUserProfileController controllerTest2 = new UpdateUserProfileController(company2);
        //Act
        company2.getSystemUserStore().saveSystemUser(user);
        SystemUser expected = controllerTest2.getUser("xxxx@isep.ipp.pt");
        //Assert
        assertEquals(user, expected);
    }

    @Test
    public void getUserTest() {
        //Assert
        assertThrows (IllegalArgumentException.class, () -> {
            //Arrange
            Company company2 = new Company();
            UpdateUserProfileController controllerTest2 = new UpdateUserProfileController(company2);
            //Act
            controllerTest2.getUser("xxxx@isep.ipp.pt");
        });
    }

    @Test
    public void getUserProfileListTest() {
        //Arrange
        UpdateUserProfileController controllerTest1 = new UpdateUserProfileController(company);
        UpdateUserProfileController controllerTest2 = new UpdateUserProfileController(company);
        //Assert
        assertEquals(controllerTest2.getUserProfileList(), controllerTest1.getUserProfileList());
    }

    @Test
    public void updateProfileTest() {
        //Arrange
        SystemUser user = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt",
                "tester", "Qwerty_1", "Qwerty_1", "", userProfileStore.getUserProfile("Visitor"));
        UpdateUserProfileController controllerTest = new UpdateUserProfileController(company);
        //Act
        company.getSystemUserStore().getSystemUsers().add(user);
        //Assert
        assertTrue(controllerTest.updateProfile(user, userProfileStore.getUserProfile("Administrator"), userProfileStore.getUserProfile("Director")));
    }
}