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
                                            "User_12", "111", "111", "",company.getUserProfile("Visitor"));
        company.getSystemUserStore().addSystemUser(user);

        //NonRegisteredUserActivationController controller = new NonRegisteredUserActivationController(user);

        //Assert
        assertTrue(company.getSystemUserStore().getUserByEmail("1211748@isep.ipp.pt").setActivateUser());
    }
}
