package switch2021.project.controller;

import org.junit.jupiter.api.Test;
import switch2021.project.model.Company;
import switch2021.project.model.SystemUser.SystemUser;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class InactivateUserControllerTest {


    @Test
    public void inactivateUser() {
        //Arrange
        Company company = new Company(); //objeto do tipo company
        InactivateUserController controller = new InactivateUserController(company);
        SystemUser user = new SystemUser("Ana", "1211748@isep.ipp.pt",
                "User_12", "Qwerty_1", "Qwerty_1", ".png",company.getUserProfileStore().getUserProfile("Visitor"));
        company.getSystemUserStore().saveSystemUser(user);

        // Act
        boolean result= controller.inactivateUser("1211748@isep.ipp.pt");

        //Assert
        assertTrue(result);
    }

}