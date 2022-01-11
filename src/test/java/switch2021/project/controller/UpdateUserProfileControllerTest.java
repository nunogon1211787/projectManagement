package switch2021.project.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import switch2021.project.model.Company;
import switch2021.project.model.Profile;
import switch2021.project.model.SystemUser;
import switch2021.project.utils.App;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UpdateUserProfileControllerTest {

    private Company company;
    private SystemUser user;

    @BeforeEach
    public void init() {
        company = App.getInstance().getCompany(); // sempre a mesma instancia
        user = new SystemUser(new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt",
                "tester", "123456", new Profile("sss", "sss")));
                user.updateProfile(company.getUserProfile(0), company.getUserProfile(1));
        company.getArraySyUser().add(user);
    }

    @Test
    public void constructorUpdateUserProfileTest() {
        //Arrange
        Company company = new Company();
        UpdateUserProfileController controllerTest = new UpdateUserProfileController(company);
        SystemUser user = controllerTest.getUser("xxxx@isep.ipp.pt");
        //Act
        Company company2 = new Company();
        UpdateUserProfileController controllerTest2 = new UpdateUserProfileController(company2);
        SystemUser expected = controllerTest2.getUser("xxxx@isep.ipp.pt");
        //Assert
        assertEquals(user, expected);
    }

    @Test
    public void updateProfileTest() {
        //Arrange
        SystemUser user = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt",
                "tester", "123456", company.getUserProfile("Visitor"));
        UpdateUserProfileController controllerTest = new UpdateUserProfileController(company);
        //Act
        company.getArraySyUser().add(user);
        //Assert
        assertTrue(controllerTest.updateProfile(user, company.getUserProfile(0), company.getUserProfile(1)));
    }
}