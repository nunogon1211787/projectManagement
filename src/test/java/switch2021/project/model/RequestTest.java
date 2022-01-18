package switch2021.project.model;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
class RequestTest {


     //arrange

    @org.junit.jupiter.api.Test
    public void constructorTest(){

        UserProfile profileTest = new UserProfile("name");
        SystemUser user = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt",
                "tester", "img_123", "img_123", "123456", profileTest);
        Request request = new Request (profileTest,user);

        java.time.LocalDate datateste = LocalDate.now();

        //expected
        UserProfile profileResult=request.getProfile();

        assertEquals(datateste,request.getRequestDate());
        assertEquals(profileTest,profileResult);
        assertEquals(user,request.getUser());
        assertFalse(request.getRequestStatus());

    }







  
}