package switch2021.project.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import switch2021.project.model.*;
import switch2021.project.utils.App;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UpdateUserProfileControllerTest {
/*
    private Company company;
    private UserProfileStore userProfileStore;
    private SystemUser user;


        //REVER        ***************************


    @BeforeEach
    public void init() {
        company = App.getInstance().getCompany(); // sempre a mesma instancia
        user = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt",
                "tester", "123456", "123456","IMG_123", company.getUserProfile(0));
                user.updateProfile(company.getUserProfile(0), company.getUserProfile(1));
        company.getSystemUserStore().getSystemUserList().add(user);
    }
                  // REVER METODO ***************
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

    /////    REVER METODO ****************************
    @Test
    public void updateProfileTest() {
        //Arrange
        SystemUser user = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt",
                "tester", "123456","123456","", company.getUserProfile("Visitor"));
        UpdateUserProfileController controllerTest = new UpdateUserProfileController(company);
        //Act
        company.getSystemUserStore().getSystemUserList().add(user);
        //Assert
       // assertTrue(controllerTest.updateProfile(user, userProfileStore.getOriginalUserProfileList().get(0), company.getUserProfile(1)));
    } */
}