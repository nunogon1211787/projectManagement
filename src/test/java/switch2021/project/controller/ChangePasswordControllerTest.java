package switch2021.project.controller;

import org.junit.jupiter.api.Test;
import switch2021.project.model.Company;
import switch2021.project.model.UserProfile;
import switch2021.project.model.SystemUser;

import static org.junit.jupiter.api.Assertions.*;

public class ChangePasswordControllerTest {

        @Test
        public void changePassword() {
            //Arrange
            Company company = new Company();
            SystemUser user = new SystemUser("Joana", "123@isep.ipp.pt",
                    "Aluna", "AAA","AAA","", company.getUserProfileStore().getUserProfile("visitor"));
            ChangePasswordController controllerTest = new ChangePasswordController(company, user);
            company.getSystemUserStore().addSystemUser(user);
            //Assert
            assertTrue(controllerTest.changePassword("123@isep.ipp.pt", "AAA", "BBB"));
        }

    @Test
    public void changePasswordFail() {
        //Arrange
        Company company = new Company();
        SystemUser user = new SystemUser("Joana", "123@isep.ipp.pt",
                "Aluna", "AAA","AAA","", company.getUserProfileStore().getUserProfile("visitor"));
        ChangePasswordController controllerTest = new ChangePasswordController(company, user);
        company.getSystemUserStore().addSystemUser(user);
        //Assert
        assertTrue(controllerTest.changePassword("123@isep.ipp.pt", "BBB", "CCC"));
    }
    }
