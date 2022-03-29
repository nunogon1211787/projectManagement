package switch2021.project.controller;

import org.junit.jupiter.api.Test;
import switch2021.project.model.Company;
import switch2021.project.model.SystemUser.SystemUser;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ActivateUserControllerTest {


    @Test
    public void activateUser() {
        //Arrange
        Company company = new Company();
        ActivateUserController controller = new ActivateUserController(company);
        SystemUser user = new SystemUser("Ana", "xxx@isep.ipp.pt",
                "User_12", "Qwerty_1", "Qwerty_1", "",company.getUserProfileStore().getUserProfile("Visitor"));
        company.getSystemUserStore().saveSystemUser(user);

        // Act
        boolean result= controller.activateUser("xxx@isep.ipp.pt");

        //Assert
        assertTrue(result);
    }
}

