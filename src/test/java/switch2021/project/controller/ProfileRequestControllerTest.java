package switch2021.project.controller;

import org.junit.jupiter.api.Test;
import switch2021.project.model.Company;
import switch2021.project.model.SystemUser.SystemUser;
import switch2021.project.valueObject.Request;
import switch2021.project.valueObject.UserProfile;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ProfileRequestControllerTest {

    @Test
    public void createRequestTest() {
        //Arrange
        Company company = new Company();
        ProfileRequestController controller = new ProfileRequestController(company);
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("joaquim", "xxxx@isep.ipp.pt",
                "tester", "Qwerty_1", "Qwerty_1", "123456", profile);
        company.getSystemUserStore().saveSystemUser(user);
        java.time.LocalDate datateste = LocalDate.now();
        //Act
        Request request = controller.createProfileRequest("xxxx@isep.ipp.pt", "Director");
        controller.saveRequest();
        //Assert
        assertEquals(datateste, request.getRequestDate());
        assertEquals(company.getUserProfileStore().getUserProfile("Director"), request.getProfileRequested());
        assertEquals(user, request.getUser());
        assertFalse(request.isRequestStatus());
    }

    @Test
    public void saveRequest() {
        //Arrange
        Company company = new Company();
        ProfileRequestController controller = new ProfileRequestController(company);
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("joaquim", "xxxx@isep.ipp.pt",
                "tester", "Qwerty_1", "Qwerty_1", "123456", profile);
        company.getSystemUserStore().saveSystemUser(user);
        java.time.LocalDate datateste = LocalDate.now();
        //Act
        Request request = controller.createProfileRequest("xxxx@isep.ipp.pt", "Director");
        boolean test = controller.saveRequest();
        //Assert
        assertEquals(datateste, request.getRequestDate());
        assertEquals(company.getUserProfileStore().getUserProfile("Director"), request.getProfileRequested());
        assertEquals(user, request.getUser());
        assertFalse(request.isRequestStatus());
        assertTrue(test);
    }

    @Test
    public void saveRequestFalse() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            Company company = new Company();
            ProfileRequestController controller = new ProfileRequestController(company);
            UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
            SystemUser user = new SystemUser("joaquim", "xxxx@isep.ipp.pt",
                    "tester", "Qwerty_1", "Qwerty_1", "123456", profile);
            company.getSystemUserStore().saveSystemUser(user);
            //Act
            controller.createProfileRequest("xxxx@isep.ipp.pt", "Director");
            controller.createProfileRequest("xxxx@isep.ipp.pt", "Director");
            controller.saveRequest();
            controller.saveRequest();
        });
    }
}