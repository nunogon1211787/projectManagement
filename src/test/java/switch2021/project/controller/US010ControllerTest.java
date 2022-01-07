package switch2021.project.controller;

import org.junit.jupiter.api.Test;
import switch2021.project.model.Company;
import switch2021.project.model.SystemUser;
import switch2021.project.model.Profile;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class US010ControllerTest {


    @Test
    public void getUserTestUS0010() {
        //Arrange
        Company company = new Company();
        US010Controller controllerTest = new US010Controller(company, new SystemUser("Joana", "123@isep.ipp.pt",
                "Aluna", "AAA", new Profile("TTT", "TTT")));
        SystemUser user = controllerTest.getUser("123@isep.ipp.pt");
        //Act
        Company company2 = new Company();
        US010Controller controllerTest2 = new US010Controller(company2, new SystemUser("Joana", "123@isep.ipp.pt",
                "Aluna", "AAA", new Profile("TT", "TT")));
        SystemUser expected = controllerTest2.getUser("123@isep.ipp.pt");
        //Assert
        assertEquals(user, expected);
    }

    @Test
    public void updateSystemUserDataTestUS0010() {
        //Arrange
        Company company = new Company();
        US010Controller controllerTest = new US010Controller(company, new SystemUser("Joana", "123@isep.ipp.pt",
                "Aluna", "imagem_123", "12345", new Profile("TT", "TT")));
        SystemUser user = controllerTest.updateSystemUserData("Joana Isabel Silva", "Aluna_numero10", "imagem_300");
        //Act
        SystemUser expected = new SystemUser("Joana Isabel Silva", "123@isep.ipp.pt",
                "Aluna_numero10", "imagem_300", "12345", new Profile("TT", "TT"));
        //Assert
        assertEquals(user, expected);
    }
}
