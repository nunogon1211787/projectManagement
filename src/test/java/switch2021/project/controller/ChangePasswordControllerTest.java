package switch2021.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.controller.old.ChangePasswordController;
import switch2021.project.model.Company;
import switch2021.project.model.SystemUser.SystemUser;

import static org.junit.jupiter.api.Assertions.*;

public class ChangePasswordControllerTest {

/*    @Test
    public void changePasswordSuccess() {
        //Arrange
        Company company = new Company();
        SystemUser joana = new SystemUser("Joana", "123@isep.pt",
                "Aluna", "Qwerty_1", "Qwerty_1", "photo.png",
                company.getUserProfileStore().getUserProfile("visitor").getUserProfileId());
        ChangePasswordController controllerTest = new ChangePasswordController(company);
        //Act
        company.getSystemUserStore().saveSystemUser(joana);
        //Assert
        assertTrue(controllerTest.changePassword("123@isep.pt", "Qwerty_1", "Qwerty_2", "Qwerty_2"));
    }

    @Test
    @DisplayName("Using the wrong old password")
    public void changePasswordFail_Test0() {
        //Arrange
        Company company = new Company();
        ChangePasswordController controller = new ChangePasswordController(company);
        SystemUser user = new SystemUser("Joana", "123@isep.ipp.pt",
                "Aluna", "Qwerty_1", "Qwerty_1", "photo.png", company.getUserProfileStore().getUserProfile("visitor").getUserProfileId());
        company.getSystemUserStore().saveSystemUser(user);
        //Assert
        assertFalse(controller.changePassword("123@isep.ipp.pt", "Qwerty_2", "Qwerty_3", "123"));
    }

    @Test
    @DisplayName("Using the wrong new password confirmation")
    public void changePasswordFail_Test2() {
        //Arrange
        Company company = new Company();
        ChangePasswordController controller = new ChangePasswordController(company);
        SystemUser user = new SystemUser("Joana", "123@isep.ipp.pt",
                "Aluna", "Qwerty_1", "Qwerty_1", "photo.png", company.getUserProfileStore().getUserProfile("visitor").getUserProfileId());
        company.getSystemUserStore().saveSystemUser(user);
        //Assert
        assertFalse(controller.changePassword("123@isep.ipp.pt", "Qwerty_1", "Qwerty_3", "321"));
    }

    @Test
    @DisplayName("Using an empty new password")
    public void changePasswordFail_Test3() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            Company company = new Company();
            ChangePasswordController controller = new ChangePasswordController(company);
            SystemUser user = new SystemUser("Joana", "123@isep.ipp.pt",
                    "Aluna", "AAA", "AAA", "img_123", company.getUserProfileStore().getUserProfile("visitor").getUserProfileId());
            company.getSystemUserStore().saveSystemUser(user);
            controller.changePassword("123@isep.ipp.pt", "AAA", "", "");
        });
    }

    @Test
    @DisplayName("Using a new password with low length")
    public void changePasswordFail_Test4() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            Company company = new Company();
            ChangePasswordController controller = new ChangePasswordController(company);
            SystemUser user = new SystemUser("Joana", "123@isep.ipp.pt",
                    "Aluna", "AAA", "AAA", "img_123", company.getUserProfileStore().getUserProfile("visitor").getUserProfileId());
            company.getSystemUserStore().saveSystemUser(user);
            controller.changePassword("123@isep.ipp.pt", "AAA", "1", "1");
        });
    }

 */
}
