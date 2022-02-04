package switch2021.project.controller;

import org.junit.jupiter.api.Test;
import switch2021.project.model.Company;
import switch2021.project.model.SystemUser;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class InnactivateUserControllerTest {


    @Test
    public void innactiveUser() {
        //Arrange
        Company company = new Company(); //objeto do tipo company
        InnactivateUserController controller = new InnactivateUserController(company);
        SystemUser user = new SystemUser("Ana", "1211748@isep.ipp.pt",
                "User_12", "111", "111", "",company.getUserProfileStore().getUserProfile("Visitor"));
        company.getSystemUserStore().saveSystemUser(user);

        // Act
        boolean result= controller.innactivateUser("1211748@isep.ipp.pt");

        //Assert
        assertTrue(result);
    }

}