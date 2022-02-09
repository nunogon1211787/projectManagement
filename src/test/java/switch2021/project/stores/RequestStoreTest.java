package switch2021.project.stores;

import org.junit.jupiter.api.Test;
import switch2021.project.model.Company;
import switch2021.project.model.Request;
import switch2021.project.model.SystemUser;
import switch2021.project.model.UserProfile;
import static org.junit.jupiter.api.Assertions.*;

class RequestStoreTest {

    @Test
    void createProfileRequestTestFailProfileAlreadyAssigned() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            Company company = new Company();
            UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
            SystemUser user = new SystemUser("Rogério Moreira", "xxx@isep.ipp.pt", "Devop", "1234567",
                    "1234567", "123", profile);
            Request request = new Request(profile, user);
            RequestStore requestTestList = new RequestStore();
            //Act
            requestTestList.addProfileRequest(request);
        });

    }

    @Test
    void createProfileRequestTestFailAlreadyExists() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            Company company = new Company();
            UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
            SystemUser user = new SystemUser("Rogério Moreira", "xxx@isep.ipp.pt", "Devop", "1234567",
                    "1234567", "123", userProfile);
            UserProfile newUserProfile = new UserProfile("Admin");
            Request request = new Request(newUserProfile, user);
            //Act
            RequestStore requestTestList = new RequestStore();
            requestTestList.saveRequest(request);
            requestTestList.addProfileRequest(request);
        });
    }

    @Test
    void addProfileRequestTestSuccess() {
        //Arrange
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("Rogério Moreira", "xxx@isep.ipp.pt", "Devop", "1234567",
                "1234567", "123", userProfile);
        UserProfile newUserProfile = new UserProfile("Admin");
        Request request = new Request(newUserProfile, user);
        RequestStore requestTestList = new RequestStore();
        //Assert and Act
        assertTrue(requestTestList.addProfileRequest(request));
        assertEquals(1,requestTestList.getRequestProfileList().size());

    }
    @Test
    void removeProfileRequestTestSuccess() {
        //Arrange
        UserProfile userProfile = new UserProfile("Visitor");
        SystemUser user = new SystemUser("Rogério Moreira", "xxx@isep.ipp.pt", "Devop", "1234567",
                "1234567", "123", userProfile);
        UserProfile newUserProfile = new UserProfile("Admin");
        Request request = new Request(newUserProfile, user);
        RequestStore requestTestList = new RequestStore();
        //Assert and Act
        assertTrue(requestTestList.addProfileRequest(request));
        assertEquals(1, requestTestList.getRequestProfileList().size());
        assertTrue(requestTestList.removeProfileRequest(request));
        assertEquals(0, requestTestList.getRequestProfileList().size());

    }

    @Test
    void alterProfileRequestStatusTest() {
        UserProfile userProfile = new UserProfile("Visitor");
        SystemUser user = new SystemUser("Rogério Moreira", "xxx@isep.ipp.pt", "Devop", "1234567",
                "1234567", "123", userProfile);
        UserProfile newUserProfile = new UserProfile("Admin");
        Request request = new Request(newUserProfile, user);
        //Assert
        assertFalse(request.isRequestStatus());
        //Act
        request.changeRequestStatus();
        //Assert
        assertTrue(request.isRequestStatus());
    }

    @Test
    public void testStatusTest() {
        //Arrange and Act
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("Test", "xxxx@isep.ipp.pt",
                "tester", "img_123", "img_123", "123456", profile);
        Request request = new Request(profile, user);
        request.changeRequestStatus();
        //Assert
        assertTrue(request.isRequestStatus());
    }

    @Test
    public void validateRequestTestSuccess() {
        //Arrange
        Company company = new Company();
        RequestStore requestStore = company.getRequestStore();
        UserProfile userProfile = new UserProfile("Visitor");
        SystemUser user = new SystemUser("yoga", "xxx@isep.ipp.pt", "Devop", "1234567",
                "1234567", "123", userProfile);
        UserProfile newUserProfile = new UserProfile("Admin");
        //Act
        Request request = new Request(newUserProfile, user);
        //Assert
        assertTrue(requestStore.saveRequest(request));
    }

    @Test
    public void validateRequestTestFail() {
        //Arrange
        Company company = new Company();
        RequestStore requestStore = company.getRequestStore();
        UserProfile userProfile = new UserProfile("Visitor");
        SystemUser user = new SystemUser("yoga", "xxx@isep.ipp.pt", "Devop", "1234567",
                "1234567", "123", userProfile);
        UserProfile newUserProfile = new UserProfile("Admin");
        //Act
        Request request = new Request(newUserProfile, user);
        requestStore.saveRequest(request);
        //Assert
        assertFalse(requestStore.saveRequest(request));
    }

    @Test
    public void setID_RequestTest() {
        //Arrange
        UserProfile userProfile = new UserProfile("Visitor");
        SystemUser user = new SystemUser("yoga", "xxx@isep.ipp.pt", "Devop", "1234567",
                "1234567", "123", userProfile);
        UserProfile newUserProfile = new UserProfile("Admin");
        //Act
            //Request 1
        Request request = new Request(newUserProfile, user);
        RequestStore requestTestList = new RequestStore();
        requestTestList.addProfileRequest(request);
            //Request 2
        Request request2 = new Request(newUserProfile, user);
        requestTestList.addProfileRequest(request2);
        //Assert
        assertEquals(1, request.getIdRequest());
        assertEquals(2, request2.getIdRequest());
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
        SystemUser user3 = company.getSystemUserStore().createSystemUser("shun", "zzzz@isep.ipp.pt",
                "tester", "img_123", "img_123", "123456", profile);
        //Act
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
        assertNotNull(request1);
    }

}