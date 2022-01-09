package switch2021.project.controller;

import org.junit.jupiter.api.Test;
import switch2021.project.model.Company;
import switch2021.project.model.Profile;
import switch2021.project.model.SystemUser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChangePasswordControllerTest {

        @Test
        public void changePasswordTestUS0011() {
            //Arrange
            Company company = new Company();
            SystemUser user = new SystemUser("Joana", "123@isep.ipp.pt",
                    "Aluna", "AAA", company.getProfile("visitor"));
            ChangePasswordController controllerTest = new ChangePasswordController(company, user);
            company.addSystemUser(user);
            //Assert
            assertTrue(controllerTest.changePassword("123@isep.ipp.pt", "AAA", "BBB"));
        }
    }
