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
        UpdatePersonalDataController controllerTest = new UpdatePersonalDataController(company, new SystemUser("Joana", "123@isep.ipp.pt",
                "Aluna", "AAA","AAA","img_123", new UserProfile("TTT")));
        SystemUser user = controllerTest.getUser("123@isep.ipp.pt");
        //Act
        Company company2 = new Company();
        UpdatePersonalDataController controllerTest2 = new UpdatePersonalDataController(company2, new SystemUser("Joana", "123@isep.ipp.pt",
                "Aluna", "AAA","AAA","img_123", new UserProfile("TTT")));
        SystemUser expected = controllerTest2.getUser("123@isep.ipp.pt");
        //Assert
        assertEquals(user, expected);
    }

    @Test
    public void updateSystemUserData() {
        //Arrange
        Company company = new Company();
        UpdatePersonalDataController controllerTest = new UpdatePersonalDataController(company, new SystemUser("Joana", "123@isep.ipp.pt",
                "Aluna", "AAA","AAA","img_123", new UserProfile("TTT")));
        SystemUser user = controllerTest.updateSystemUserData("Joana Isabel Silva", "Aluna_numero10", "imagem_300");
        //Act
        SystemUser expected = new SystemUser("Joana Isabel Silva", "123@isep.ipp.pt",
                "Aluna_numero10", "AAA","AAA","imagem_300", new UserProfile("TTT"));
        //Assert
        assertEquals(user, expected);
    }

    @Test
    public void updateSystemUserWithWrongEmail() {
        //Arrange
        Company company = new Company();
        UpdatePersonalDataController controllerTest = new UpdatePersonalDataController(company, new SystemUser("Joana", "123@isep.ipp.pt",
                "Aluna", "AAA","AAA","img_12", new UserProfile("TTT")));
        SystemUser user = controllerTest.updateSystemUserData("Joana Isabel Silva", "Aluna_numero10", "imagem_300");
        //Act
        SystemUser expected = new SystemUser("Joana", "123@isep.ipp.pt",
                "Aluna", "AAA","AAA","img_12", new UserProfile("TTT"));
        //Assert
        assertNotEquals(user, expected);
    }
}

