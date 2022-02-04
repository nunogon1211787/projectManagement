package switch2021.project.stores;

import org.junit.jupiter.api.Test;
import switch2021.project.model.Company;
import switch2021.project.model.Request;
import switch2021.project.model.SystemUser;
import switch2021.project.model.UserProfile;
import switch2021.project.stores.RequestStore;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class RequestStoreTest {

    Company company = new Company();
    UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
    //UserProfile profile2 = new UserProfile("name");
    SystemUser user = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt",
            "tester", "img_123", "img_123", "123456", profile);


    Request request = new Request(profile, user);

    //expected

    java.time.LocalDate datateste = LocalDate.now();

    @Test
    void createProfileRequestFailProfileAlreadyAssigned() {

        assertThrows(IllegalArgumentException.class, () -> {
            UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
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
            UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
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
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("Rogério Moreira", "xxx@isep.ipp.pt", "Devop", "1234567",
                "1234567", "123", userProfile);

        UserProfile newUserProfile = new UserProfile("Admin");

        Request request = new Request(newUserProfile, user);

        RequestStore requestTestList = new RequestStore();


        assertTrue(requestTestList.addProfileRequest(request));
        assertEquals(1,requestTestList.getRequestProfileList().size());

    }
    @Test
    void removeProfileRequestSuccess() {
        UserProfile userProfile = new UserProfile("Visitor");
        SystemUser user = new SystemUser("Rogério Moreira", "xxx@isep.ipp.pt", "Devop", "1234567",
                "1234567", "123", userProfile);

        UserProfile newUserProfile = new UserProfile("Admin");

        Request request = new Request(newUserProfile, user);

        RequestStore requestTestList = new RequestStore();

        assertTrue(requestTestList.addProfileRequest(request));
        assertEquals(1, requestTestList.getRequestProfileList().size());
        assertTrue(requestTestList.removeProfileRequest(request));
        assertEquals(0, requestTestList.getRequestProfileList().size());

    }

    @Test
    void alterProfileRequestStatus() {
        UserProfile userProfile = new UserProfile("Visitor");
        SystemUser user = new SystemUser("Rogério Moreira", "xxx@isep.ipp.pt", "Devop", "1234567",
                "1234567", "123", userProfile);

        UserProfile newUserProfile = new UserProfile("Admin");

        Request request = new Request(newUserProfile, user);

        RequestStore requestTestList = new RequestStore();

        assertFalse(request.isRequestStatus());
        request.changeRequestStatus();
        assertTrue(request.isRequestStatus());
    }

    @Test
    public void testStatus() {

        request.changeRequestStatus();
        assertTrue(request.isRequestStatus());
    }

    @Test
    public void validateRequest() {
        UserProfile userProfile = new UserProfile("Visitor");
        SystemUser user = new SystemUser("yoga", "xxx@isep.ipp.pt", "Devop", "1234567",
                "1234567", "123", userProfile);
        UserProfile newUserProfile = new UserProfile("Admin");

        Request request = new Request(newUserProfile, user);
        RequestStore requestTestList = new RequestStore();
        requestTestList.addProfileRequest(request);

        assertEquals(request,requestTestList.getRequestProfileList().get(0));

    }

    @Test
    public void overrideTest() {
        //Arrange
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        UserProfile profile2 = company.getUserProfileStore().getUserProfile("User");
        UserProfile profile3 = company.getUserProfileStore().getUserProfile("Director");

        SystemUser user = company.getSystemUserStore().createSystemUser("shyriu", "xxxx@isep.ipp.pt",
                "tester", "img_123", "img_123", "123456", profile);
        SystemUser user2 = company.getSystemUserStore().createSystemUser("ikki", "yyyy@isep.ipp.pt",
                "tester", "img_123", "img_123", "123456", profile);
        SystemUser user3 = company.getSystemUserStore().createSystemUser("shun", "zzzz@isep.ipp.pt",
                "tester", "img_123", "img_123", "123456", profile);

        RequestStore requestTestList1 = new RequestStore();
        Request request1 = company.getRequestStore().createProfileRequest(profile2, user);
        requestTestList1.addProfileRequest(request1);

        RequestStore requestTestList2 = new RequestStore();
        Request request2 = new Request(profile2,user);
        requestTestList2.addProfileRequest(request2);

        RequestStore requestTestList3 = new RequestStore();
        Request request3 = new Request(profile3,user3);
        requestTestList3.addProfileRequest(request3);
        //Assert
        assertEquals(requestTestList1,requestTestList2);
        assertEquals(requestTestList1.hashCode(),requestTestList2.hashCode());
        assertNotEquals(requestTestList1,requestTestList3);
        assertNotEquals(requestTestList1.hashCode(),requestTestList3.hashCode());
    }



}