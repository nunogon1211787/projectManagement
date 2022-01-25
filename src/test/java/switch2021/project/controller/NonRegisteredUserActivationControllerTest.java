package switch2021.project.controller;

import org.junit.jupiter.api.Test;
import switch2021.project.model.Company;
import switch2021.project.model.SystemUser;
import static org.junit.jupiter.api.Assertions.assertTrue;


class NonRegisteredUserActivationControllerTest {

    @Test
    public void activateNonRegisteredUserbyEmailSuccess () {
        //Arrange
        Company company = new Company(); //objeto do tipo company
        SystemUser user = new SystemUser("Ana", "1211748@isep.ipp.pt",
                                            "User_12", "111", "111", "",company.getUserProfileStore().getUserProfile("Visitor"));
          company.getSystemUserStore().saveSystemUser(user);

        //NonRegisteredUserActivationController controller = new NonRegisteredUserActivationController(user);

        // Act
        boolean result = company.getSystemUserStore().getUserByEmail("1211748@isep.ipp.pt").setActivateUser();

        //Assert
        assertTrue(result);
    }
}
