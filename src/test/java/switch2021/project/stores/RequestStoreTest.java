package switch2021.project.stores;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.immutable.Description;
import switch2021.project.immutable.Name;
import switch2021.project.model.Request;
import switch2021.project.model.SystemUser;
import switch2021.project.model.UserProfile;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RequestStoreTest {

    @Test
    @DisplayName("create profile request with success")
    void createProfileRequestWithSucces() {
        //Arrange
        UserProfile userProfile = mock(UserProfile.class);
        SystemUser systemUser = mock(SystemUser.class);
        RequestStore requestStore = new RequestStore();
        when(systemUser.getUserName()).thenReturn(new Name("Cris"));
        //Act
        Request profileRequest = requestStore.createProfileRequest(userProfile, systemUser);
        //Assert
        assertEquals(profileRequest.getUser().getUserName().getNameF(), systemUser.getUserName().getNameF());
    }


    @Test
    @DisplayName("add profile request - with success")
    void addProfileRequestSuccess() {
        //Arrange
        UserProfile userProfile = mock(UserProfile.class);
        SystemUser systemUser = mock(SystemUser.class);
        RequestStore requestStore = new RequestStore();
        when(systemUser.getUserName()).thenReturn(new Name("Cris"));
        Request request = requestStore.createProfileRequest(userProfile, systemUser);
        //Act
        boolean req = requestStore.addProfileRequest(request);
        //Assert
        assertTrue(req);
        assertEquals(1, requestStore.getRequestProfileList().size());
    }

    @Test
    @DisplayName("add profile request - already assigned")
    void addProfileRequestFail() {
        //Arrange
        UserProfile userProfile = mock(UserProfile.class);
        SystemUser systemUser = mock(SystemUser.class);
        RequestStore requestStore = new RequestStore();
        Description description = mock(Description.class);
        when(systemUser.getUserName()).thenReturn(new Name("Cris"));
        when(userProfile.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("Visitor");
        when(systemUser.hasProfile(userProfile)).thenReturn(true);
        Request request = requestStore.createProfileRequest(userProfile, systemUser);
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            requestStore.addProfileRequest(request);
        });
        //Assert
        assertTrue(exception.getMessage().equals("Requested profile is already assigned to the user."));
    }

    @Test
    @DisplayName("add profile request - already exists")
    void addProfileRequestFail2() {
        //Arrange
        UserProfile userProfile = mock(UserProfile.class);
        SystemUser systemUser = mock(SystemUser.class);
        RequestStore requestStore = new RequestStore();
        requestStore.getRequestProfileList();
        Description description = mock(Description.class);
        Description descrip = mock(Description.class);
        when(systemUser.getUserName()).thenReturn(new Name("Cris"));
        when(userProfile.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("Visitor");
        UserProfile newUserProfile = mock(UserProfile.class);
        when(newUserProfile.getUserProfileName()).thenReturn(descrip);
        when(descrip.getText()).thenReturn("Admin");
        Request request = requestStore.createProfileRequest(newUserProfile, systemUser);
        requestStore.addProfileRequest(request);
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            requestStore.addProfileRequest(request);
        });
        //Assert
        assertTrue(exception.getMessage().equals("Request already exists"));
    }


    @Test
    @DisplayName("Remove profile request with success")
    void removeProfileRequestTestSuccess() {
        //Arrange
        UserProfile userProfile = mock(UserProfile.class);
        SystemUser systemUser = mock(SystemUser.class);
        RequestStore requestStore = new RequestStore();
        requestStore.getRequestProfileList();
        Description description = mock(Description.class);
        when(systemUser.getUserName()).thenReturn(new Name("Cris"));
        when(userProfile.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("Visitor");
        Request request = requestStore.createProfileRequest(userProfile, systemUser);
        requestStore.addProfileRequest(request);
        //Act
        boolean remove = requestStore.removeProfileRequest(request);
        //Assert
        assertTrue(remove);
        assertEquals(0, requestStore.getRequestProfileList().size());
    }

    @Test
    @DisplayName("Modify profile request status - success")
    void alterProfileRequestStatusTest() {
        //Arrange
        UserProfile userProfile = mock(UserProfile.class);
        SystemUser systemUser = mock(SystemUser.class);
        RequestStore requestStore = new RequestStore();
        requestStore.getRequestProfileList();
        Description description = mock(Description.class);
        when(systemUser.getUserName()).thenReturn(new Name("Cris"));
        when(userProfile.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("Visitor");
        Request request = requestStore.createProfileRequest(userProfile, systemUser);
        requestStore.addProfileRequest(request);
        //Assert
        assertFalse(request.isRequestStatus());
        //Act
        request.changeRequestStatus();
        //Assert
        assertTrue(request.isRequestStatus());
    }

    @Test
    @DisplayName("Test Status")
    void testStatusTest() {
        //Arrange
        UserProfile userProfile = mock(UserProfile.class);
        SystemUser systemUser = mock(SystemUser.class);
        RequestStore requestStore = new RequestStore();
        requestStore.getRequestProfileList();
        Description description = mock(Description.class);
        when(systemUser.getUserName()).thenReturn(new Name("Cris"));
        when(userProfile.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("Visitor");
        Request request = requestStore.createProfileRequest(userProfile, systemUser);
        requestStore.addProfileRequest(request);
        //Act
        request.changeRequestStatus();
        //Assert
        assertTrue(request.isRequestStatus());
    }

    @Test
    @DisplayName("validate Request - True")
    void validateRequestTestTRUE() {
        //Arrange
        UserProfile userProfile = mock(UserProfile.class);
        SystemUser systemUser = mock(SystemUser.class);
        RequestStore requestStore = new RequestStore();
        requestStore.getRequestProfileList();
        Description description = mock(Description.class);
        Description descrip = mock(Description.class);
        when(systemUser.getUserName()).thenReturn(new Name("Cris"));
        when(userProfile.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("Visitor");
        UserProfile newUserProfile = mock(UserProfile.class);
        when(newUserProfile.getUserProfileName()).thenReturn(descrip);
        when(descrip.getText()).thenReturn("Admin");
        //Act
        Request request = requestStore.createProfileRequest(newUserProfile, systemUser);
        requestStore.addProfileRequest(request);
        request.changeRequestStatus();
        //Assert
        assertTrue(requestStore.validateStatusOfARequest(request));
    }

    @Test
    @DisplayName("validate Request - False")
    void validateRequestFalse() {
        //Arrange
        UserProfile userProfile = mock(UserProfile.class);
        SystemUser systemUser = mock(SystemUser.class);
        RequestStore requestStore = new RequestStore();
        requestStore.getRequestProfileList();
        Description description = mock(Description.class);
        Description descrip = mock(Description.class);
        when(systemUser.getUserName()).thenReturn(new Name("Cris"));
        when(userProfile.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("Visitor");
        UserProfile newUserProfile = mock(UserProfile.class);
        when(newUserProfile.getUserProfileName()).thenReturn(descrip);
        when(descrip.getText()).thenReturn("Admin");
        //Act
        Request request = requestStore.createProfileRequest(newUserProfile, systemUser);
        requestStore.addProfileRequest(request);
        request.changeRequestStatus();
        //Assert
        assertFalse(requestStore.validateRequest(request));
    }

    @Test
    @DisplayName("validate Request - True")
    void validateRequestTrue() {
        //Arrange
        UserProfile userProfile = mock(UserProfile.class);
        SystemUser systemUser = mock(SystemUser.class);
        RequestStore requestStore = new RequestStore();
        requestStore.getRequestProfileList();
        Description description = mock(Description.class);
        Description descrip = mock(Description.class);
        when(systemUser.getUserName()).thenReturn(new Name("Cris"));
        when(userProfile.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("Visitor");
        UserProfile newUserProfile = mock(UserProfile.class);
        when(newUserProfile.getUserProfileName()).thenReturn(descrip);
        when(descrip.getText()).thenReturn("Admin");
        //Act
        Request request = requestStore.createProfileRequest(newUserProfile, systemUser);
        requestStore.addProfileRequest(request);
        Request request2 = requestStore.createProfileRequest(newUserProfile, systemUser);
        //Assert
        assertTrue( requestStore.validateRequest(request2));
    }

    @Test
    @DisplayName("Validate ID of a request")
    void idGenerator() {
        //Arrange
        UserProfile userProfile = mock(UserProfile.class);
        SystemUser systemUser = mock(SystemUser.class);
        RequestStore requestStore = new RequestStore();
        requestStore.getRequestProfileList();
        Description description = mock(Description.class);
        Description descrip = mock(Description.class);
        when(systemUser.getUserName()).thenReturn(new Name("Cris"));
        when(userProfile.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("Visitor");
        UserProfile newUserProfile = mock(UserProfile.class);
        when(newUserProfile.getUserProfileName()).thenReturn(descrip);
        when(descrip.getText()).thenReturn("Admin");
        //Act
        Request request = requestStore.createProfileRequest(userProfile, systemUser);
        requestStore.addProfileRequest(request);
        Request request2 = requestStore.createProfileRequest(newUserProfile, systemUser);
        requestStore.addProfileRequest(request2);
        //Assert
        assertEquals(1, request.getIdRequest());
        assertEquals(2, request2.getIdRequest());
    }
}