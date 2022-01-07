package switch2021.project.controller;

import org.junit.jupiter.api.Test;
import switch2021.project.model.Company;
import switch2021.project.model.Profile;
import switch2021.project.model.ProjectSettings;
import switch2021.project.model.SystemUser;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class US006Test {

    @Test
    public void constructorUS006Test() {
        //Arrange
        Company company = new Company();
        US006Controller controllerTest = new US006Controller(company, new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt",
                "tester", "123456", new Profile("sss", "sss")));
        SystemUser user = controllerTest.getUser("xxxx@isep.ipp.pt");
        //Act
        Company company2 = new Company();
        US006Controller controllerTest2 = new US006Controller(company2, new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt",
                "tester", "123456", new Profile("sss", "sss")));
        SystemUser expected = controllerTest2.getUser("xxxx@isep.ipp.pt");
        //Assert
        assertEquals(user, expected);
    }

    @Test
    public void updateProfileTest() {
        //Arrange
        Company company = new Company();
        SystemUser user = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt",
                "tester", "123456", company.getProfile("Administrator"));
        US006Controller controllerTest = new US006Controller(company, user);
        //Act
        SystemUser expected =  new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt",
                "tester", "123456", company.getProfile("Visitor"));
        controllerTest.updateProfile(company.getProfile("Visitor"), company.getProfile("Administrator"));

        //Assert
        assertEquals(user, expected);
    }
}