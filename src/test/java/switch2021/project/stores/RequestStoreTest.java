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

    //SystemUser user2 = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt",
    //"tester", "img_123", "img_123", "123456", profile);

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
    }

    @Test
    void testStatus() {

        request.changeRequestStatus(request);
        assertTrue(request.isRequestStatus());
    }


}