package switch2021.project.model;

import org.junit.jupiter.api.Test;
import switch2021.project.controller.ProfileRequestController;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class RequestTest {


     //arrange

    @Test
    public void newRequestTest(){
        //Arrange
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("joaquim", "xxxx@isep.ipp.pt",
                "tester", "img_123", "img_123", "123456", profile);
        java.time.LocalDate datateste = LocalDate.now();
        //Act
        Request req = new Request(company.getUserProfileStore().getUserProfile("Director"),user);
        //Assert
        assertEquals(datateste,req.getRequestDate());
        assertEquals(company.getUserProfileStore().getUserProfile("Director"),req.getProfileRequested());
        assertEquals(user,req.getUser());
        assertFalse(req.isRequestStatus());
    }


    @Test
    public void overrideTest(){
        //Arrange
        SystemUser user = mock(SystemUser.class);
        UserProfile profile = mock(UserProfile.class);

        //Act
        Request req = new Request(profile,user);
        Request req3 = null;

        //Assert
        assertEquals(req,req);
        assertNotEquals(req,req3);
        assertNotEquals(req,user);
    }




  
}