package switch2021.project.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class SystemUserServiceTest {
/*    @Test
    public void createAndSaveSystemUserSuccess() {
        //Arrange
        Company company = new Company();
        SystemUserRepository systemUserStore = company.getSystemUserStore();
        UserProfileRepository userProfileStore = company.getUserProfileStore();
        SystemUserFactory systemUserFactory = new SystemUserFactory();
        RegisterUserService systemUserService = new RegisterUserService(systemUserStore, userProfileStore, systemUserFactory);
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
        //Act + Assert
        assertTrue(systemUserService.createAndSaveSystemUser(userName, email, function, password, passwordConfirmation, photo));
    }

    @Test
    @DisplayName("email already registered")
    public void createAndSaveSystemUserFail() {
        //Arrange
        Company company = new Company();
        SystemUserRepository systemUserStore = company.getSystemUserStore();
        UserProfileRepository userProfileStore = company.getUserProfileStore();
        RegisterUserService systemUserService = new RegisterUserService(systemUserStore, userProfileStore);
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
    }*/
}
