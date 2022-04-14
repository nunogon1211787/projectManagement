package switch2021.project.model.SystemUser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.model.Company;
import switch2021.project.model.UserProfile.UserProfile;
import switch2021.project.model.valueObject.Description;
import switch2021.project.model.valueObject.SystemUserId;
import switch2021.project.model.valueObject.UserProfileId;
import switch2021.project.stores.SystemUserStore;
import switch2021.project.stores.UserProfileStore;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SystemUserServiceTest {

    @Test
    public void createAndSaveSystemUserSuccess() {
        //Arrange
        Company company = new Company();
        SystemUserStore systemUserStore = company.getSystemUserStore();
        UserProfileStore userProfileStore = company.getUserProfileStore();
        SystemUserService systemUserService = new SystemUserService(systemUserStore, userProfileStore);
        String userName = "manueloliveira";
        String email = "manueloliveira@beaver.com";
        String password = "Qwerty_1";
        String passwordConfirmation = "Qwerty_1";
        String function = "tester";
        String photo = "photo.png";
        //Act + Assert
        assertTrue(systemUserService.createAndSaveSystemUser(userName, email, function, password, passwordConfirmation, photo));
    }

    @Test
    @DisplayName("email already registered")
    public void createAndSaveSystemUserFail() {
        //Arrange
        Company company = new Company();
        SystemUserStore systemUserStore = company.getSystemUserStore();
        UserProfileStore userProfileStore = company.getUserProfileStore();
        SystemUserService systemUserService = new SystemUserService(systemUserStore, userProfileStore);
        String userName = "manueloliveira";
        String email = "manueloliveira@beaver.com";
        String password = "Qwerty_1";
        String passwordConfirmation = "Qwerty_1";
        String function = "tester";
        String photo = "photo.png";

        UserProfile userProfile = mock(UserProfile.class);
        UserProfileId userProfileId = mock(UserProfileId.class);
        Description description = mock(Description.class);
        when(userProfile.getUserProfileId()).thenReturn(userProfileId);
        when(userProfileId.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("Visitor");
        SystemUser oldUser = new SystemUser(userName, email, function, password, passwordConfirmation, photo, userProfileId);
        systemUserStore.saveSystemUser(oldUser);
        //Act + Assert
        assertFalse(systemUserService.createAndSaveSystemUser(userName, email, function, password, passwordConfirmation, photo));
    }

}
