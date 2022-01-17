package switch2021.project.model;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
class RequestTest {


     //arrange

    @org.junit.jupiter.api.Test
    public void constructorTest(){

        UserProfile profile = new UserProfile("name");
        UserProfile profile2 = new UserProfile("name");
        SystemUser user = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt",
                "tester", "img_123", "img_123", "123456", profile);

        SystemUser user2 = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt",
                "tester", "img_123", "img_123", "123456", profile);

        Request request = new Request (profile,user);

        //expected

        java.time.LocalDate datateste = LocalDate.now();



        //
        assertEquals(request.getRequestDate(),datateste);
        assertEquals(request.getProfile(),profile2);
        assertEquals(request.getUser(),user2);
        //assertFalse(request.getStatusRequest());


        }

  
}