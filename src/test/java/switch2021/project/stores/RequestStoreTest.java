package switch2021.project.stores;

import org.junit.jupiter.api.Test;
import switch2021.project.model.Request;
import switch2021.project.model.SystemUser;
import switch2021.project.model.UserProfile;
import switch2021.project.stores.RequestStore;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class RequestStoreTest {


    UserProfile profile = new UserProfile("name");
    //UserProfile profile2 = new UserProfile("name");
    SystemUser user = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt",
            "tester", "img_123", "img_123", "123456", profile);


    Request request = new Request(profile, user);

    //expected

    java.time.LocalDate datateste = LocalDate.now();

    @Test
    void createProfileRequestFailProfileAlreadyAssigned() {

        assertThrows(IllegalArgumentException.class, () -> {
            UserProfile profile = new UserProfile("Rogério Moreira");
            SystemUser user = new SystemUser("Rogério Moreira", "xxx@isep.ipp.pt", "Devop", "1234567",
                    "1234567", "123", profile);
            Request request = new Request(profile, user);


            RequestStore requestTestList = new RequestStore();
            requestTestList.addProfileRequest(request);
        });

    }

    @Test
    void createProfileRequestFailAlreadyExists() {

        assertThrows(IllegalArgumentException.class, () -> {

            UserProfile userProfile = new UserProfile("Visitor");
            SystemUser user = new SystemUser("Rogério Moreira", "xxx@isep.ipp.pt", "Devop", "1234567",
                    "1234567", "123", userProfile);

            UserProfile newUserProfile = new UserProfile("Admin");

            Request request = new Request(newUserProfile, user);

            RequestStore requestTestList = new RequestStore();
            requestTestList.saveRequest(request);
            requestTestList.addProfileRequest(request);
        });
    }

    @Test
    void addProfileRequestSuccess() {
        UserProfile userProfile = new UserProfile("Visitor");
        SystemUser user = new SystemUser("Rogério Moreira", "xxx@isep.ipp.pt", "Devop", "1234567",
                "1234567", "123", userProfile);

        UserProfile newUserProfile = new UserProfile("Admin");

        Request request = new Request(newUserProfile, user);

        RequestStore requestTestList = new RequestStore();

        assertTrue(requestTestList.addProfileRequest(request));
    }

    @Test
    public void testStatus() {

        request.changeRequestStatus(request);
        assertTrue(request.isRequestStatus());
    }

    @Test

    public void overrideTest() {
        //Arrange

        UserProfile profile = new UserProfile("name");
        UserProfile profile2 = new UserProfile("name2");
        UserProfile profile3 = new UserProfile("name3");
        SystemUser user = new SystemUser("shyriu", "xxxx@isep.ipp.pt",
                "tester", "img_123", "img_123", "123456", profile);
        SystemUser user2 = new SystemUser("ikki", "yyyy@isep.ipp.pt",
                "tester", "img_123", "img_123", "123456", profile2);
        SystemUser user3 = new SystemUser("shun", "zzzz@isep.ipp.pt",
                "tester", "img_123", "img_123", "123456", profile3);

        Request request1 = new Request(profile, user2);
        Request request2 = new Request(profile2,user3);
        Request request3 = new Request(profile3,user);

        RequestStore requestTestList1 = new RequestStore();
        RequestStore requestTestList2 = new RequestStore();
        RequestStore requestTestList3 = new RequestStore();

        requestTestList1.addProfileRequest(request1);
        requestTestList2.addProfileRequest(request1);
        requestTestList3.addProfileRequest(request3);

        //Assert
        assertEquals(requestTestList1,requestTestList2);
        assertNotEquals(requestTestList1,requestTestList3);
        assertNotEquals(requestTestList1.hashCode(),requestTestList3.hashCode());
    }



}