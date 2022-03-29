package switch2021.project.controller;

import org.junit.jupiter.api.Test;
import switch2021.project.model.Company;
import switch2021.project.model.SystemUser.SystemUser;
import static org.junit.jupiter.api.Assertions.assertTrue;


class NonRegisteredUserActivationControllerTest {

    @Test
    public void activateNonRegisteredUserbyEmailSuccess () {
        //Arrange
        Company company = new Company(); //objeto do tipo company
        NonRegisteredUserActivationController controller = new NonRegisteredUserActivationController(company);
        SystemUser user = new SystemUser("Ana", "1211748@isep.ipp.pt",
                                            "User_12", "Qwerty_1", "Qwerty_1", "",company.getUserProfileStore().getUserProfile("Visitor"));
        company.getSystemUserStore().saveSystemUser(user);

        // Act
        boolean result = controller.activateNonRegisteredUser("1211748@isep.ipp.pt");

        //Assert
        assertTrue(result);
    }
}
