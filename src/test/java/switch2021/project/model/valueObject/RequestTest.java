package switch2021.project.model.valueObject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.model.SystemUser.SystemUser;
import switch2021.project.model.UserProfile.UserProfile;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RequestTest {

    @Test
    @DisplayName("create and save profile request - profile already assigned")
    void createAndSaveProfileRequestFail() {
        //Arrange
        UserProfileId visitorProfileIdDouble = mock(UserProfileId.class);
        Description descriptionDouble = mock(Description.class);
        when(visitorProfileIdDouble.getUserProfileName()).thenReturn(descriptionDouble);
        when(descriptionDouble.getText()).thenReturn("Visitor");

        Request testRequest = new Request(visitorProfileIdDouble);
        //Act + Assert
        assertEquals("Visitor", testRequest.getProfileIdRequested().getUserProfileName().getText());
        assertEquals(LocalDate.now(), testRequest.getRequestDate());
    }

    @Test
    public void overrideTest() {
        //Arrange
        SystemUser user = mock(SystemUser.class);
        UserProfileId profileId = mock(UserProfileId.class);
        //Act
        Request req = new Request(profileId);
        Request req2 = new Request(profileId);
        Request req3 = null;
        //Assert
        assertEquals(req, req2);
        assertNotEquals(req, req3);
        assertNotEquals(req, user);
        assertEquals(req.hashCode(), req2.hashCode());
    }
}