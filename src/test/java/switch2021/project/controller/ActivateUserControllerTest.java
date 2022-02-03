package switch2021.project.controller;

import org.junit.jupiter.api.Test;
import switch2021.project.model.Company;
import switch2021.project.model.SystemUser;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ActivateUserControllerTest {


    @Test
    public void activeUser() {
        //Arrange
        Company company = new Company();
        ActivateUserController controller = new ActivateUserController(company);
        SystemUser user = new SystemUser("Ana", "1211748@isep.ipp.pt",
                "User_12", "111", "111", "",company.getUserProfileStore().getUserProfile("Visitor"));
        company.getSystemUserStore().saveSystemUser(user);

        // Act
        boolean result= controller.activateUser("1211748@isep.ipp.pt");

        //Assert
        assertTrue(result);
    }
}

