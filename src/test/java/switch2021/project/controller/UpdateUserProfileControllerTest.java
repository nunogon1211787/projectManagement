package switch2021.project.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import switch2021.project.model.*;
import switch2021.project.stores.SystemUserStore;
import switch2021.project.stores.UserProfileStore;
import switch2021.project.utils.App;
import static org.junit.jupiter.api.Assertions.*;

public class UpdateUserProfileControllerTest {

    private Company company;
    private SystemUserStore systemUserStore;
    private UserProfileStore userProfileStore;
    private SystemUser user;


    @BeforeEach
    public void init() {
        company = App.getInstance().getCompany(); // sempre a mesma instancia
        systemUserStore = company.getSystemUserStore();
        userProfileStore = company.getUserProfileStore();
        user = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt",
                "tester", "123456", "123456", "IMG_123", userProfileStore.getUserProfile(0));
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
        UpdateUserProfileController controllerTest2 = new UpdateUserProfileController();
        //Assert
        assertEquals(controllerTest2.getUserProfileList(), userProfileStore.getUserProfileList());
    }

    @Test
    public void updateProfileTest() {
        //Arrange
        SystemUser user = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt",
                "tester", "123456", "123456", "", userProfileStore.getUserProfile("Visitor"));
        UpdateUserProfileController controllerTest = new UpdateUserProfileController(company);
        //Act
        company.getSystemUserStore().getSystemUserList().add(user);
        //Assert
        assertTrue(controllerTest.updateProfile(user, userProfileStore.getUserProfile(1), userProfileStore.getUserProfile(2)));
    }
}