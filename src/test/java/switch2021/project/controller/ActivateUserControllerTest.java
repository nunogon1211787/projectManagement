package switch2021.project.controller;

import org.junit.jupiter.api.Test;
import switch2021.project.controller.old.ActivateUserController;
import switch2021.project.model.Company;
import switch2021.project.model.SystemUser.SystemUser;
import switch2021.project.model.valueObject.SystemUserId;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class ActivateUserControllerTest {


    @Test
    public void activateUser() {
        //Arrange
        Company company = new Company();
        ActivateUserController controller = new ActivateUserController(company);
        SystemUserId userId = new SystemUserId("xxx@isep.ipp.pt");
        /*SystemUser user = new SystemUser("Ana", "xxx@isep.ipp.pt",
                "User_12", "Qwerty_1", "Qwerty_1", "photo.png",company.getUserProfileStore().getUserProfile("Visitor"));*/
        SystemUser user = new SystemUser("Ana", "xxx@isep.ipp.pt",
                "User_12", "Qwerty_1", "Qwerty_1", "photo.png",company.getUserProfileStore().getUserProfile("Visitor").getUserProfileId());
        company.getSystemUserStore().saveSystemUser(user);

        // Act
        boolean result= controller.activateUser("xxx@isep.ipp.pt");

        //Assert
        assertTrue(result);
    }
}

