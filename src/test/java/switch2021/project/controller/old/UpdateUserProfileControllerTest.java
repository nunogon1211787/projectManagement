package switch2021.project.controller.old;

import org.junit.jupiter.api.Test;
import switch2021.project.model.*;
import switch2021.project.repositories.SystemUserRepository;
import switch2021.project.repositories.UserProfileRepository;

import static org.junit.jupiter.api.Assertions.*;

public class UpdateUserProfileControllerTest {

    private Company company;
    private SystemUserRepository systemUserStore;
    private UserProfileRepository userProfileStore;


/*    @BeforeEach
    public void init() {
        company = new Company(); // sempre a mesma instancia
        systemUserStore = company.getSystemUserStore();
        userProfileStore = company.getUserProfileStore();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt",
                "tester", "Qwerty_1", "Qwerty_1", "photo.png", userProfile.getUserProfileId());
        systemUserStore.saveSystemUser(user);
    }

    @Test
    public void constructorUpdateUserProfileTest() {
        //Arrange
        SystemUser user = systemUserStore.findSystemUserByEmail("xxxx@isep.ipp.pt");
        Company company2 = new Company();
        UpdateUserProfileController controllerTest2 = new UpdateUserProfileController(company2);
        //Act
        company2.getSystemUserStore().saveSystemUser(user);
        SystemUser expected = controllerTest2.getUser("xxxx@isep.ipp.pt");
        //Assert
        assertEquals(user, expected);
    }

 */

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

/*    @Test
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
                "tester", "Qwerty_1", "Qwerty_1", "photo.png", userProfileStore.getUserProfile("Visitor").getUserProfileId());
        UpdateUserProfileController controllerTest = new UpdateUserProfileController(company);
        //Act
        company.getSystemUserStore().findAllSystemUsers().add(user);
        //Assert
        assertTrue(controllerTest.updateProfile(user, userProfileStore.getUserProfile("Administrator"), userProfileStore.getUserProfile("Director")));
    }

 */
}