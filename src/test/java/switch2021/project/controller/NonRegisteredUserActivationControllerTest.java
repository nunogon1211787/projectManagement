package switch2021.project.controller;

import org.junit.jupiter.api.Test;
import switch2021.project.model.Company;
import switch2021.project.model.SystemUser;
import switch2021.project.stores.SystemUserStore;

import static org.junit.jupiter.api.Assertions.assertTrue;


class NonRegisteredUserActivationControllerTest {

    @Test
    public void activateNonRegisteredUserbyEmailSuccess () {
        //Arrange
        Company company = new Company(); //objeto do tipo company
        NonRegisteredUserActivationController controller = new NonRegisteredUserActivationController(company);
        SystemUserStore systemUserStore = company.getSystemUserStore();

        String userEmail = "1211748@isep.ipp.pt";
        SystemUser user = systemUserStore.createSystemUser("Ana", userEmail,
                                            "User_12", "111", "111", "",company.getUserProfileStore().getUserProfile("Visitor"));
        systemUserStore.saveSystemUser(user);
        // Act
        boolean result = controller.activateNonRegisteredUser(userEmail);
        //Assert
        assertTrue(result);
        assertTrue(user.getActivateUserStatus());
    }
}
