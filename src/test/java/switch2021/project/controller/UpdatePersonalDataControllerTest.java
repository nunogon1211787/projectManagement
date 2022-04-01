package switch2021.project.controller;

import org.junit.jupiter.api.Test;
import switch2021.project.model.Company;
import switch2021.project.model.SystemUser.SystemUser;
import switch2021.project.model.valueObject.UserProfile;

import static org.junit.jupiter.api.Assertions.*;

public class UpdatePersonalDataControllerTest {


    @Test
    public void getUser() {
        //Arrange
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser joana = new SystemUser("Joana", "123@isep.pt",
                "Aluna", "Qwerty_1","Qwerty_1","photo.png",
                userProfile);
        UpdatePersonalDataController controllerTest = new UpdatePersonalDataController(company);
        company.getSystemUserStore().saveSystemUser(joana);
        //Act
        SystemUser userResult = controllerTest.getUser("123@isep.pt");
        //Assert
        assertEquals(joana, userResult);
    }

    @Test
    public void updateSystemUserDataSuccess() {
        //Arrange
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        UpdatePersonalDataController controllerTest = new UpdatePersonalDataController(company);
        SystemUser user1 = new SystemUser("Joana", "123@isep.ipp.pt", "Aluna", "Qwerty_1",
                "Qwerty_1","photo.png", userProfile);
        company.getSystemUserStore().saveSystemUser(user1);
        //Act
        controllerTest.getUser("123@isep.ipp.pt");
        //Assert
        assertTrue(controllerTest.updateSystemUserData("Joana Isabel Silva", "Aluna_numero10", "photo1.png"));
    }

    @Test
    public void updateSystemUserWithWrongUserName() {
        //Arrange
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        UpdatePersonalDataController controllerTest = new UpdatePersonalDataController(company);
        SystemUser user1 = new SystemUser("Joana", "123@isep.ipp.pt", "Aluna", "Qwerty_1",
                "Qwerty_1","photo.png", userProfile);
        company.getSystemUserStore().saveSystemUser(user1);
        //Act
        controllerTest.getUser("123@isep.ipp.pt");
        //Assert
        assertTrue(controllerTest.updateSystemUserData("Jo", "Aluna_numero10", "photo.png"));
    }

    @Test
    public void updateSystemUserWithWrongFunction() {
        //Arrange
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        UpdatePersonalDataController controllerTest = new UpdatePersonalDataController(company);
        SystemUser user1 = new SystemUser("Joana", "123@isep.ipp.pt", "Aluna", "Qwerty_1",
                "Qwerty_1","photo.png", userProfile);
        company.getSystemUserStore().saveSystemUser(user1);
        //Act
        controllerTest.getUser("123@isep.ipp.pt");
        //Assert
        assertTrue(controllerTest.updateSystemUserData("Joana Isabel Silva", "Al", "photo.png"));
    }

    @Test
    public void updateSystemUserWithWrongPhoto() {
        //Arrange
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        UpdatePersonalDataController controllerTest = new UpdatePersonalDataController(company);
        SystemUser user1 = new SystemUser("Joana", "123@isep.ipp.pt", "Aluna", "Qwerty_1",
                "Qwerty_1","photo.png", userProfile);
        company.getSystemUserStore().saveSystemUser(user1);
        //Act
        controllerTest.getUser("123@isep.ipp.pt");
        //Assert
        assertTrue(controllerTest.updateSystemUserData("Joana Isabel Silva", "Aluna_numero10", "photo.jpg"));
    }

}

