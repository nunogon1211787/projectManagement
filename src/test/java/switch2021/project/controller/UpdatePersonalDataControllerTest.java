package switch2021.project.controller;

import org.junit.jupiter.api.Test;
import switch2021.project.model.Company;
import switch2021.project.model.SystemUser;
import switch2021.project.model.UserProfile;

import static org.junit.jupiter.api.Assertions.*;

public class UpdatePersonalDataControllerTest {


    @Test
    public void getUser() {
        //Arrange
        Company company = new Company();
        SystemUser joana = new SystemUser("Joana", "123@isep.pt",
                "Aluna", "AAA","AAA","img_123", new UserProfile("TTT"));
        UpdatePersonalDataController controllerTest = new UpdatePersonalDataController(company);
        company.getSystemUserStore().saveSystemUser(joana);
        //Act
        SystemUser userResult = controllerTest.getUser("123@isep.pt");
        //Assert
        assertEquals(joana, userResult);
    }

    @Test
    public void updateSystemUserData() {
        //Arrange
        Company company = new Company();
        UpdatePersonalDataController controllerTest = new UpdatePersonalDataController(company);
        SystemUser user1 = new SystemUser("Joana", "123@isep.ipp.pt", "Aluna", "AAA","AAA","img_123", new UserProfile("TTT"));
        company.getSystemUserStore().saveSystemUser(user1);
        //Act
        controllerTest.getUser("123@isep.ipp.pt");
        //Assert
        assertTrue(controllerTest.updateSystemUserData("Joana Isabel Silva", "Aluna_numero10", "imagem_300"));
    }

    @Test
    public void updateSystemUserWithWrongEmail() {
        //Arrange
        Company company = new Company();
        UpdatePersonalDataController controllerTest = new UpdatePersonalDataController(company);
        SystemUser user1 = new SystemUser("Joana", "123@isep.ipp.pt", "Aluna", "AAA","AAA","img_12", new UserProfile("TTT"));
        company.getSystemUserStore().saveSystemUser(user1);
        controllerTest.getUser("123@isep.ipp.pt");
        boolean user = controllerTest.updateSystemUserData("Joana Isabel Silva", "Aluna_numero10", "imagem_300");
        //Act
        SystemUser expected = new SystemUser("Joana", "123@isep.ipp.pt",
                "Aluna", "AAA","AAA","img_12", new UserProfile("TTT"));
        //Assert
        assertNotEquals(user, expected);
    }
}

