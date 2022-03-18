package switch2021.project.controller;

import org.junit.jupiter.api.Test;
import switch2021.project.model.Company;
import switch2021.project.model.Request;
import switch2021.project.model.SystemUser;
import switch2021.project.model.UserProfile;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ProfileRequestControllerTest {

    @Test
    public void createRequestTest(){
        //Arrange
        Company company = new Company();
        ProfileRequestController controller = new ProfileRequestController(company);
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("joaquim", "xxxx@isep.ipp.pt",
                "tester", "img_123", "img_123", "123456", profile);
        company.getSystemUserStore().saveSystemUser(user);
        java.time.LocalDate datateste = LocalDate.now();
        //Act
        Request request = controller.createProfileRequest("xxxx@isep.ipp.pt","Director");
        controller.saveRequest();
        //Assert
        assertEquals(datateste,request.getRequestDate());
        assertEquals(company.getUserProfileStore().getUserProfile("Director"),request.getProfileRequested());
        assertEquals(user,request.getUser());
        assertFalse(request.isRequestStatus());
    }

    @Test
    public void saveRequest() {
        //Arrange
        Company company = new Company();
        ProfileRequestController controller = new ProfileRequestController(company);
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("joaquim", "xxxx@isep.ipp.pt",
                "tester", "img_123", "img_123", "123456", profile);
        company.getSystemUserStore().saveSystemUser(user);
        java.time.LocalDate datateste = LocalDate.now();
        //Act
        Request request = controller.createProfileRequest("xxxx@isep.ipp.pt","Director");
        boolean test = controller.saveRequest();
        //Assert
        assertEquals(datateste,request.getRequestDate());
        assertEquals(company.getUserProfileStore().getUserProfile("Director"),request.getProfileRequested());
        assertEquals(user,request.getUser());
        assertFalse(request.isRequestStatus());
        assertTrue(test);
    }

    @Test
    public void saveRequestFalse() {
        //Arrange
        Company company = new Company();
        ProfileRequestController controller = new ProfileRequestController(company);
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("joaquim", "xxxx@isep.ipp.pt",
                "tester", "img_123", "img_123", "123456", profile);
        company.getSystemUserStore().saveSystemUser(user);
        java.time.LocalDate datateste = LocalDate.now();
        //Act
        controller.createProfileRequest("xxxx@isep.ipp.pt","Director");
        controller.createProfileRequest("xxxx@isep.ipp.pt","Director");
        boolean test = controller.saveRequest();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        boolean test2 = controller.saveRequest();
        });
        //Assert
        assertFalse(exception.getMessage().equals("Requested profile is already assigned to the user."));
    }
}