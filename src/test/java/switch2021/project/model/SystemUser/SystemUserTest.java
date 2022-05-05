package switch2021.project.model.SystemUser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.interfaces.ISystemUserRepo;
import switch2021.project.model.valueObject.Email;
import switch2021.project.model.valueObject.Password;
import switch2021.project.model.valueObject.SystemUserID;
import switch2021.project.repositories.SystemUserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SystemUserTest {
    /*
   @Test
    public void verifyEmail() {

        //Arrange
        UserProfileId profileId = mock(UserProfileId.class);
        Description description = mock(Description.class);
        when(profileId.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("Visitor");
        SystemUser test = new SystemUser("Test", "xxxx@isep.ipp.pt", "tester", "Qwerty_1",
                "Qwerty_1", "photo.png", profileId);
        //Act
        String emailCheck = "xxxx@isep.ipp.pt";
        //Assert
        assertTrue(test.isYourEmail(emailCheck));
    }

    @Test
    public void verifyEmailSuccess() {

        //Arrange
        UserProfileId profileId = mock(UserProfileId.class);
        Description description = mock(Description.class);
        when(profileId.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("Visitor");
        SystemUser test = new SystemUser("Test","xxkjbjsdaf@gndfsf.com", "tester", "Qwerty_1",
                "Qwerty_1", "photo.png", profileId);
        //Act
        String emailCheck = "xx";
        //Assert
        assertTrue(test.isYourEmail(emailCheck));
    }

    @Test
    public void verifyEmailFail() {

        //Arrange
        UserProfileId profileId = mock(UserProfileId.class);
        Description description = mock(Description.class);
        when(profileId.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("Visitor");
        SystemUser joana = new SystemUser("Joana Silva", "1234@isep.ipp.pt", "Aluna", "Qwerty_1", "Qwerty_1", "photo.png", profileId);
        //Act
        String emailCheck = "4321@isep.ipp.pt";
        //Assert
        assertFalse(joana.isYourEmail(emailCheck));
    }

    @Test
    public void verifyUserNameSuccess() {

        //Arrange
        UserProfileId profileId = mock(UserProfileId.class);
        Description description = mock(Description.class);
        when(profileId.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("Visitor");
        SystemUser test = new SystemUser("Te", "xxkjfnsd@alksda.com", "tester", "Querty_1",
                "Querty_1", "photo.png", profileId);
        //Act
        String userName = "Te";
        //Assert
        assertTrue(test.hasName(userName));
    }

    @Test
    public void verifyNameFail() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            UserProfileId profileId = mock(UserProfileId.class);
            Description description = mock(Description.class);
            when(profileId.getUserProfileName()).thenReturn(description);
            when(description.getText()).thenReturn("Visitor");
            new SystemUser(" ", "1234@isep.ipp.pt", "Aluna", "abcde", "abcde", "123_img", profileId);
        });
    }

    @Test
    public void UpdateProfile() {
        //Arrange
        UserProfile userProfile = mock(UserProfile.class);
        UserProfileId userProfileId = mock(UserProfileId.class);
        Description description = mock(Description.class);
        when(userProfile.getUserProfileId()).thenReturn(userProfileId);
        when(userProfileId.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("Visitor");
        SystemUser user = new SystemUser("xxx", "xxx@isep.ipp.pt", "tester", "Qwerty_1", "Qwerty_1", "photo.png", userProfileId);
        //Act
        UserProfile newProfile = mock(UserProfile.class);
        UserProfileId newUserProfileId = mock(UserProfileId.class);
        Description newDescription = mock(Description.class);
        when(newProfile.getUserProfileId()).thenReturn(newUserProfileId);
        when(newUserProfileId.getUserProfileName()).thenReturn(newDescription);
        when(description.getText()).thenReturn("User");
        // Assert
        assertTrue(user.updateProfile(userProfile, newProfile));
    }

    @Test
    public void UpdateProfileAlreadyExist() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            UserProfile userProfile = mock(UserProfile.class);
            UserProfileId userProfileId = mock(UserProfileId.class);
            Description description = mock(Description.class);
            when(userProfile.getUserProfileId()).thenReturn(userProfileId);
            when(userProfileId.getUserProfileName()).thenReturn(description);
            when(description.getText()).thenReturn("Visitor");
            SystemUser user = new SystemUser("xxx", "xxx@isep.ipp.pt", "tester", "Qwerty_1", "Qwerty_1", "photo.png", userProfileId);
            //Act
            UserProfile newProfile = mock(UserProfile.class);
            UserProfileId newUserProfileId = mock(UserProfileId.class);
            Description newDescription = mock(Description.class);
            when(newProfile.getUserProfileId()).thenReturn(newUserProfileId);
            when(newUserProfileId.getUserProfileName()).thenReturn(newDescription);
            when(newDescription.getText()).thenReturn("Visitor");
            user.updateProfile(userProfile, newProfile);
        });
    }

    @Test
    public void NewUserWithProfileDifferentFromVisitor() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            UserProfile userProfile = mock(UserProfile.class);
            UserProfileId userProfileId = mock(UserProfileId.class);
            Description description = mock(Description.class);
            when(userProfile.getUserProfileId()).thenReturn(userProfileId);
            when(userProfileId.getUserProfileName()).thenReturn(description);
            when(description.getText()).thenReturn("Notvisitor");
            new SystemUser("xxx", "xxx@isep.ipp.pt", "tester", "Qwerty_1", "Qwerty_1", "img_123", userProfileId);
        });
    }

    @Test
    public void verifyUpdateAndEncryptationOfPassword() {

        //Test to verify if the oldpassword is updated by the newpassword, and this last one is
        //stored in system user with the encryptation method.

        //Arrange
        UserProfile userProfile = mock(UserProfile.class);
        UserProfileId userProfileId = mock(UserProfileId.class);
        Description description = mock(Description.class);
        when(userProfile.getUserProfileId()).thenReturn(userProfileId);
        when(userProfileId.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("Visitor");
        SystemUser joana = new SystemUser("Joana", "112@isep.ipp.pt", "Aluna_10", "Qwerty_1", "Qwerty_1", "photo.png", userProfileId);
        //Act
        joana.updatePassword("Qwerty_1", "Qwerty_2", "Qwerty_2");
        Password pwdExp = new Password("Qwerty_2");

        //Assert
        assertEquals(pwdExp, joana.getPassword());
    }

    @Test
    public void verifyOldPassword() {

        //Test to verify if the oldpassword, stored in the system user, is equal or diferent from the
        //password that came from User Interface (UI).
        //Arrange
        UserProfile userProfile = mock(UserProfile.class);
        UserProfileId userProfileId = mock(UserProfileId.class);
        Description description = mock(Description.class);
        when(userProfile.getUserProfileId()).thenReturn(userProfileId);
        when(userProfileId.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("Visitor");
        SystemUser joana = new SystemUser("Joana", "112@isep.ipp.pt", "Aluna_10",
                "Qwerty_1", "Qwerty_1", "photo.png", userProfileId);
        //Act
        assertFalse(joana.updatePassword("HElLO_02", "GOODBYE", "GOODBYE"));

    }

    @Test
    public void setAllDataSuccess_2() {

        //Arrange
        UserProfile userProfile = mock(UserProfile.class);
        UserProfileId userProfileId = mock(UserProfileId.class);
        Description description = mock(Description.class);
        when(userProfile.getUserProfileId()).thenReturn(userProfileId);
        when(userProfileId.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("Visitor");
        SystemUser joana = new SystemUser("Joana", "112@isep.ipp.pt", "Aluna_10",
                "Qwerty_1", "Qwerty_1", "photo.png", userProfileId);
        //Act and Assert
        assertEquals("Joana", joana.getUserName().getNameF());
    }


    @Test
    public void setAllDataSuccess_3() {

        //Arrange
        UserProfile userProfile = mock(UserProfile.class);
        UserProfileId userProfileId = mock(UserProfileId.class);
        Description description = mock(Description.class);
        when(userProfile.getUserProfileId()).thenReturn(userProfileId);
        when(userProfileId.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("Visitor");
        SystemUser joana = new SystemUser("Joana", "112@isep.ipp.pt", "Aluna_10",
                "Qwerty_1", "Qwerty_1", "photo.png", userProfileId);
        //Act and Assert
        assertEquals("Aluna_10", joana.getFunction().getText());
    }


    @Test
    public void setAllDataSuccess_4() {

        //Arrange
        UserProfile userProfile = mock(UserProfile.class);
        UserProfileId userProfileId = mock(UserProfileId.class);
        Description description = mock(Description.class);
        when(userProfile.getUserProfileId()).thenReturn(userProfileId);
        when(userProfileId.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("Visitor");
        SystemUser joana = new SystemUser("Joana", "112@isep.ipp.pt", "Aluna_10",
                "Qwerty_1", "Qwerty_1", "photo.png", userProfileId);
        //Act and Assert
        assertEquals("photo.png", joana.getPhoto().getExtension());
    }


    @Test
    public void createSystemUserSuccess() {
        //Arrange
        String userName = "manueloliveira";
        String email = "manueloliveira@beaver.com";
        String password = "Qwerty_1";
        String passwordConfirmation = "Qwerty_1";
        String function = "teeee";
        String photo = "photo.png";

        UserProfile userProfile = mock(UserProfile.class);
        UserProfileId userProfileId = mock(UserProfileId.class);
        Description description = mock(Description.class);
        when(userProfile.getUserProfileId()).thenReturn(userProfileId);
        when(userProfileId.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("Visitor");
        SystemUser newUser = new SystemUser(userName, email, function, password, passwordConfirmation, photo, userProfileId);

        List<UserProfileId> assignedProfileIdExpected = new ArrayList<>();
        assignedProfileIdExpected.add(userProfileId);

        Password passwordExpected = new Password("Qwerty_1");
        //Assert
        assertEquals(userName, newUser.getUserName().getNameF());
        assertEquals(email, newUser.getSystemUserId().getEmail().getEmail());
        assertEquals(passwordExpected, newUser.getPassword());
        assertEquals(function, newUser.getFunction().getText());
        assertEquals(photo, newUser.getPhoto().getExtension());
        assertFalse(newUser.isActive());
        assertEquals(assignedProfileIdExpected, newUser.getAssignedProfiles());
    }


    @Test
    public void createSystemUserFailUserNameIsEmpty() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String userName = "";
            String email = "manueloliveira@beaver.com";
            String password = "ghi";
            String passwordConfirmation = "ghi";
            String function = "tester";
            String photo = "photo";
            UserProfile userProfile = mock(UserProfile.class);
            UserProfileId userProfileId = mock(UserProfileId.class);
            Description description = mock(Description.class);
            when(userProfile.getUserProfileId()).thenReturn(userProfileId);
            when(userProfileId.getUserProfileName()).thenReturn(description);
            when(description.getText()).thenReturn("Visitor");
            new SystemUser(userName, email, function, password, passwordConfirmation, photo, userProfileId);
        });
    }

    @Test
    public void createSystemUserFailUserNameIsShort() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String userName = "";
            String email = "manueloliveira@beaver.com";
            String password = "ghi";
            String passwordConfirmation = "ghi";
            String function = "tester";
            String photo = "photo";
            UserProfile userProfile = mock(UserProfile.class);
            UserProfileId userProfileId = mock(UserProfileId.class);
            Description description = mock(Description.class);
            when(userProfile.getUserProfileId()).thenReturn(userProfileId);
            when(userProfileId.getUserProfileName()).thenReturn(description);
            when(description.getText()).thenReturn("Visitor");
            new SystemUser(userName, email, function, password, passwordConfirmation, photo, userProfileId);
        });
    }

    @Test
    public void createSystemUserFailEmailIsEmpty() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String userName = "manueloliveira";
            String email = "";
            String password = "ghi";
            String passwordConfirmation = "ghi";
            String function = "tester";
            String photo = "photo";
            UserProfile userProfile = mock(UserProfile.class);
            UserProfileId userProfileId = mock(UserProfileId.class);
            Description description = mock(Description.class);
            when(userProfile.getUserProfileId()).thenReturn(userProfileId);
            when(userProfileId.getUserProfileName()).thenReturn(description);
            when(description.getText()).thenReturn("Visitor");
            new SystemUser(userName, email, function, password, passwordConfirmation, photo, userProfileId);
        });
    }

    @Test
    public void createSystemUserFailEmailIsShort() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String userName = "manueloliveira";
            String email = "m";
            String password = "ghi";
            String passwordConfirmation = "ghi";
            String function = "tester";
            String photo = "photo";
            UserProfile userProfile = mock(UserProfile.class);
            UserProfileId userProfileId = mock(UserProfileId.class);
            Description description = mock(Description.class);
            when(userProfile.getUserProfileId()).thenReturn(userProfileId);
            when(userProfileId.getUserProfileName()).thenReturn(description);
            when(description.getText()).thenReturn("Visitor");
            new SystemUser(userName, email, function, password, passwordConfirmation, photo, userProfileId);
        });
    }

    @Test
    public void createSystemUserFailFunctionIsEmpty() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String userName = "manueloliveira";
            String email = "manueloliveira@beaver.com";
            String password = "ghi";
            String passwordConfirmation = "ghi";
            String function = "";
            String photo = "photo";
            UserProfile userProfile = mock(UserProfile.class);
            UserProfileId userProfileId = mock(UserProfileId.class);
            Description description = mock(Description.class);
            when(userProfile.getUserProfileId()).thenReturn(userProfileId);
            when(userProfileId.getUserProfileName()).thenReturn(description);
            when(description.getText()).thenReturn("Visitor");
            new SystemUser(userName, email, function, password, passwordConfirmation, photo, userProfileId);
        });
    }

    @Test
    public void createSystemUserFailFunctionIsShort() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String userName = "manueloliveira";
            String email = "manueloliveira@beaver.com";
            String password = "ghi";
            String passwordConfirmation = "ghi";
            String function = "t";
            String photo = "photo";
            UserProfile userProfile = mock(UserProfile.class);
            UserProfileId userProfileId = mock(UserProfileId.class);
            Description description = mock(Description.class);
            when(userProfile.getUserProfileId()).thenReturn(userProfileId);
            when(userProfileId.getUserProfileName()).thenReturn(description);
            when(description.getText()).thenReturn("Visitor");
            new SystemUser(userName, email, function, password, passwordConfirmation, photo, userProfileId);
        });
    }

    @Test
    public void createSystemUserFailPasswordIsEmpty() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String userName = "manueloliveira";
            String email = "manueloliveira@beaver.com";
            String password = "";
            String passwordConfirmation = "";
            String function = "tester";
            String photo = "photo";
            UserProfile userProfile = mock(UserProfile.class);
            UserProfileId userProfileId = mock(UserProfileId.class);
            Description description = mock(Description.class);
            when(userProfile.getUserProfileId()).thenReturn(userProfileId);
            when(userProfileId.getUserProfileName()).thenReturn(description);
            when(description.getText()).thenReturn("Visitor");
            new SystemUser(userName, email, function, password, passwordConfirmation, photo, userProfileId);
        });
    }

    @Test
    public void createSystemUserFailPasswordIsShort() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String userName = "manueloliveira";
            String email = "manueloliveira@beaver.com";
            String password = "g";
            String passwordConfirmation = "g";
            String function = "tester";
            String photo = "photo";
            UserProfile userProfile = mock(UserProfile.class);
            UserProfileId userProfileId = mock(UserProfileId.class);
            Description description = mock(Description.class);
            when(userProfile.getUserProfileId()).thenReturn(userProfileId);
            when(userProfileId.getUserProfileName()).thenReturn(description);
            when(description.getText()).thenReturn("Visitor");
            new SystemUser(userName, email, function, password, passwordConfirmation, photo, userProfileId);
        });
    }

    @Test
    public void createSystemUserFailWrongFunction() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String userName = "manueloliveira";
            String email = "manueloliveira@beaver.com";
            String password = "ghi";
            String passwordConfirmation = "ghi";
            String function = "tester";
            String photo = "photo";
            UserProfile userProfile = mock(UserProfile.class);
            UserProfileId userProfileId = mock(UserProfileId.class);
            Description description = mock(Description.class);
            when(userProfile.getUserProfileId()).thenReturn(userProfileId);
            when(userProfileId.getUserProfileName()).thenReturn(description);
            when(description.getText()).thenReturn("Visitor");
            new SystemUser(userName, email, function, password, passwordConfirmation, photo, userProfileId);
        });
    }

    @Test
    public void createSystemUserFailPasswordsNotMatch() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String userName = "manueloliveira";
            String email = "m";
            String password = "ghi";
            String passwordConfirmation = "abc";
            String function = "tester";
            String photo = "photo";
            UserProfile userProfile = mock(UserProfile.class);
            UserProfileId userProfileId = mock(UserProfileId.class);
            Description description = mock(Description.class);
            when(userProfile.getUserProfileId()).thenReturn(userProfileId);
            when(userProfileId.getUserProfileName()).thenReturn(description);
            when(description.getText()).thenReturn("Visitor");
            new SystemUser(userName, email, function, password, passwordConfirmation, photo, userProfileId);
        });
    }

    @Test
    public void overrideAndHashCodeTest() {
        //Arrange
        String userName = "manueloliveira";
        String email = "manueloliveira@beaver.com";
        String password = "Qwerty_1";
        String passwordConfirmation = "Qwerty_1";
        String function = "tester";
        String photo = "photo.png";
        String email2 = "maneloliveira@beaver.com";
        UserProfile userProfile = mock(UserProfile.class);
        UserProfileId userProfileId = mock(UserProfileId.class);
        Description description = mock(Description.class);
        when(userProfile.getUserProfileId()).thenReturn(userProfileId);
        when(userProfileId.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("Visitor");
        Company company = new Company();
        //newUser and newUser2 are equals
        SystemUser newUser = new SystemUser(userName, email, function, password, passwordConfirmation, photo, userProfileId);
        SystemUser newUser2 = new SystemUser(userName, email, function, password, passwordConfirmation, photo, userProfileId);
        //newUser3 is different (different email)
        SystemUser newUser3 = new SystemUser(userName, email2, function, password, passwordConfirmation, photo, userProfileId);
        //Assert
        assertNotSame(newUser, newUser2);
        assertEquals(newUser, newUser2);
        assertEquals(newUser2.hashCode(), newUser2.hashCode());
        assertNotEquals(newUser, newUser3);
        assertNotEquals(newUser.hashCode(), newUser3.hashCode());
    }

    @Test
    public void setActivateUser() {
        //Arrange
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
        SystemUser newUser = new SystemUser(userName, email, function, password, passwordConfirmation, photo, userProfileId);
        assertFalse(newUser.isActive());
        //Act
        newUser.setActive(true);
        //Assert
        assertTrue(newUser.isActive());
    }

    @Test
    void hasThisDataWithAll() {
        //Input
        UserProfile pro = new UserProfile("Visitor");
        SystemUser test = new SystemUser("Test tester", "xxxx@isep.pt", "tester",
                "Qwerty_1", "Qwerty_1", "photo.png", pro.getUserProfileId());
        String name = "Test tester";
        String email = "xxxx";
        String func = "test";
        int state = 0; // -1 == null / 0 == false / 1 == true
        List<UserProfile> profiles = new ArrayList<>(); // profileId
        //Result
        assertTrue(test.hasThisData(name, email, func, state, profiles));
    }

    @Test
    void hasThisDataWithoutAll() {
        //Input
        UserProfile pro = new UserProfile("Visitor");
        SystemUser test = new SystemUser("Test", "xxxx@isep.ipp.pt", "tester",
                "Querty_1", "Querty_1", "photo.png", pro.getUserProfileId());
        String name = "";
        String email = "";
        String func = "";
        int state = -1; //-1 == null / 0 == false / 1 == true
        List<UserProfile> profiles = new ArrayList<>(); // profileId
        //Result
        assertFalse(test.hasThisData(name, email, func, state, profiles));
    }

    @Test
    void hasThisDataWithNameSuccess() {
        //Input
        UserProfile pro = new UserProfile("Visitor");
        SystemUser test = new SystemUser("Test", "xxxx@isep.ipp.pt", "tester",
                "Qwerty_1", "Qwerty_1", "photo.png", pro.getUserProfileId());
        String name = "test";
        String email = "";
        String func = "";
        int state = -1; //-1 == null / 0 == false / 1 == true
        List<UserProfile> profiles = new ArrayList<>(); // profileId
        //Result
        assertTrue(test.hasThisData(name, email, func, state, profiles));
    }

    @Test
    void hasThisDataWithEmailSuccess() {
        //Input
        UserProfile pro = new UserProfile("Visitor");
        SystemUser test = new SystemUser("Test","xxxx@isep.ipp.pt",
                "tester", "Qwerty_1", "Qwerty_1", "photo.png", pro.getUserProfileId());
        String name = "";
        String email = "xxxx";
        String func = "";
        int state = -1; //-1 == null / 0 == false / 1 == true
        List<UserProfile> profiles = new ArrayList<>(); // profileId
        //Result
        assertTrue(test.hasThisData(name, email, func, state, profiles));
    }

    @Test
    void hasThisDataWithFunctionSuccess() {
        //Input
        UserProfile pro = new UserProfile("Visitor");
        SystemUser test = new SystemUser("Test", "xxxx@isep.ipp.pt", "tester",
                "Qwerty_1", "Qwerty_1", "photo.png", pro.getUserProfileId());
        String name = "";
        String email = "";
        String func = "test";
        int state = -1; //-1 == null / 0 == false / 1 == true
        List<UserProfile> profiles = new ArrayList<>(); // profileId
        //Result
        assertTrue(test.hasThisData(name, email, func, state, profiles));
    }

    @Test
    void hasThisDataWithStateSuccess() {
        //Input
        UserProfile pro = new UserProfile("Visitor");
        SystemUser test = new SystemUser("Test", "xxxx@isep.ipp.pt", "tester",
                "Qwerty_1", "Qwerty_1", "photo.png", pro.getUserProfileId());
        String name = "";
        String email = "";
        String func = "";
        int state = 0; //isActiveUser : -1 == null / 0 == false / 1 == true
        List<UserProfile> profiles = new ArrayList<>(); // profileId
        //Result
        assertTrue(test.hasThisData(name, email, func, state, profiles));
    }

    @Test
    void hasThisDataWithProfilesSuccess() {
        //Arrange
        UserProfile pro = new UserProfile("Visitor");
        SystemUser test = new SystemUser("Test", "xxxx@isep.ipp.pt", "tester",
                "Qwerty_1", "Qwerty_1", "photo.png", pro.getUserProfileId());
        String name = "Test";
        String email = "xxxx@isep.ipp.pt";
        String func = "tester";
        int state = -1; //isActiveUser : -1 == null / 0 == false / 1 == true
        List<UserProfile> profiles = new ArrayList<>();// profileId
        profiles.add(pro);
        //Assert
        assertTrue(test.hasThisData(name, email, func, state, profiles));
    }

    @Test
    void hasThisDataWithTwoParametersSuccess() {
        //Arrange
        UserProfile pro = new UserProfile("Visitor");
        SystemUser test = new SystemUser("Test", "xxxx@isep.ipp.pt",
                "tester", "Qwerty_1", "Qwerty_1", "photo.png", pro.getUserProfileId());
        String name = "Test";
        String email = "xxxx";
        String func = "";
        int state = -1; //isActiveUser : -1 == null / 0 == false / 1 == true
        List<UserProfile> profiles = new ArrayList<>(); // profileId
        //Assert
        assertTrue(test.hasThisData(name, email, func, state, profiles));
    }

    @Test
    void hasThisDataWithThreeParametersSuccess() {
        //Arrange
        UserProfile pro = new UserProfile("Visitor");
        SystemUser test = new SystemUser("Test", "xxxx@isep.ipp.pt",
                "tester", "Querty_1", "Querty_1", "photo.png", pro.getUserProfileId());
        String name = "Test";
        String email = "xxxx";
        String func = "test";
        int state = -1; //isActiveUser : -1 == null / 0 == false / 1 == true
        List<UserProfile> profiles = new ArrayList<>(); // profileId
        //Assert
        assertTrue(test.hasThisData(name, email, func, state, profiles));
    }

    @Test
    void hasThisDataWithFourParametersSuccess() {
        //Arrange
        UserProfile pro = new UserProfile("Visitor");
        SystemUser test = new SystemUser("Test", "xxxx@isep.ipp.pt",
                "tester", "Qwerty_1", "Qwerty_1", "photo.png", pro.getUserProfileId());
        String name = "Test";
        String email = "xxxx";
        String func = "test";
        int state = 0; //isActiveUser : -1 == null / 0 == false / 1 == true
        List<UserProfile> profiles = new ArrayList<>(); // profileId
        //Assert
        assertTrue(test.hasThisData(name, email, func, state, profiles));
    }
*/
    /*
    /**
     * FAIL TESTS
     */
/*
    @Test
    void hasThisDataWithAllFail() {
        //Arrange
        UserProfile pro = new UserProfile("Visitor");
        SystemUser test = new SystemUser("test", "xxxx@isep.ipp.pt",
                "tester", "Qwerty_1", "Qwerty_1", "photo.png", pro.getUserProfileId());
        String name = "lest";
        String email = "xxxx";
        String func = "test";
        int state = 0; // -1 == null / 0 == false / 1 == true
        List<UserProfile> profiles = new ArrayList<>(); // profileId
        //Assert
        assertFalse(test.hasThisData(name, email, func, state, profiles));
    }

    @Test
    void hasThisDataWithNameFail() {
        //Arrange
        UserProfile pro = new UserProfile("Visitor");
        SystemUser test = new SystemUser("Test", "xxxx@isep.pt", "tester", "Querty_1", "Querty_1", "photo.png", pro.getUserProfileId());
        String name = "tesq";
        String email = "";
        String func = "";
        int state = -1; //-1 == null / 0 == false / 1 == true
        List<UserProfile> profiles = new ArrayList<>(); // profileId
        //Assert
        assertFalse(test.hasThisData(name, email, func, state, profiles));
    }

    @Test
    void hasThisDataWithEmailFail() {
        //Arrange
        UserProfile pro = new UserProfile("Visitor");
        SystemUser test = new SystemUser("Test", "xxxx@isep.ipp.pt",
                "tester", "Querty_1", "Querty_1", "photo.png", pro.getUserProfileId());
        String name = "";
        String email = "yxxx";
        String func = "";
        int state = -1; //-1 == null / 0 == false / 1 == true
        List<UserProfile> profiles = new ArrayList<>(); // profileId
        //Assert
        assertFalse(test.hasThisData(name, email, func, state, profiles));
    }

    @Test
    void hasThisDataWithFunctionFail() {
        //Arrange
        UserProfile pro = new UserProfile("Visitor");
        SystemUser test = new SystemUser("Test", "xxxx@isep.ipp.pt",
                "tester", "Querty_1", "Querty_1", "photo.png", pro.getUserProfileId());
        String name = "";
        String email = "";
        String func = "tesq";
        int state = -1; //-1 == null / 0 == false / 1 == true
        List<UserProfile> profiles = new ArrayList<>(); // profileId
        //Assert
        assertFalse(test.hasThisData(name, email, func, state, profiles));
    }

    @Test
    void hasThisDataWithStateFail() {
        //Arrange
        UserProfile pro = new UserProfile("Visitor");
        SystemUser test = new SystemUser("Test", "xxxx@isep.ipp.pt", "tester",
                "Querty_1", "Querty_1", "photo.png", pro.getUserProfileId());
        String name = "";
        String email = "";
        String func = "";
        int state = 1; //isActiveUser : -1 == null / 0 == false / 1 == true
        List<UserProfile> profiles = new ArrayList<>(); // profileId
        //Assert
        assertFalse(test.hasThisData(name, email, func, state, profiles));
    }

    @Test
    void hasThisDataWithStateFail2() {
        //Arrange
        UserProfile pro = new UserProfile("Visitor");
        SystemUser test = new SystemUser("Test", "xxxx@isep.ipp.pt", "tester",
                "Qwerty_1", "Qwerty_1", "photo.png", pro.getUserProfileId());
        String name = "";
        String email = "";
        String func = "";
        int state = 2; //isActiveUser : -1 == null / 0 == false / 1 == true
        List<UserProfile> profiles = new ArrayList<>(); // profileId
        //Assert
        assertFalse(test.hasThisData(name, email, func, state, profiles));
    }

    @Test
    void hasThisDataWithStateFail3() {
        //Arrange
        UserProfile pro = new UserProfile("Visitor");
        SystemUser test = new SystemUser("Test", "xxxx@isep.ipp.pt", "tester",
                "Qwerty_1", "Qwerty_1", "photo.png", pro.getUserProfileId());
        String name = "";
        String email = "";
        String func = "";
        int state = -3; //isActiveUser : -1 == null / 0 == false / 1 == true
        List<UserProfile> profiles = new ArrayList<>(); // profileId
        //Assert
        assertFalse(test.hasThisData(name, email, func, state, profiles));
    }

    @Test
    void hasThisDataWithStateFail4() {
        //Arrange
        UserProfile pro = new UserProfile("Visitor");
        SystemUser test = new SystemUser("Test", "xxxx@isep.ipp.pt", "tester",
                "Qwerty_1", "Qwerty_1", "photo.png", pro.getUserProfileId());
        String name = "";
        String email = "";
        String func = "";
        int state = -1; //isActiveUser : -1 == null / 0 == false / 1 == true
        List<UserProfile> profiles = new ArrayList<>(); // profileId
        //Assert
        assertFalse(test.hasThisData(name, email, func, state, profiles));
    }

    @Test
    void hasThisDataWithProfilesFail() {
        //Arrange
        UserProfile pro = new UserProfile("Visitor");
        SystemUser test = new SystemUser("Test", "xxxx@isep.ipp.pt", "tester", "Qwerty_1", "Qwerty_1", "photo.png", pro.getUserProfileId());
        String name = "";
        String email = "";
        String func = "";
        int state = -1; //isActiveUser : -1 == null / 0 == false / 1 == true
        List<UserProfile> profiles = new ArrayList<>(); // profileId
        //Assert
        assertFalse(test.hasThisData(name, email, func, state, profiles));
    }

    @Test
    void hasThisDataWithTwoParametersFail() {
        //Arrange
        UserProfile pro = new UserProfile("Visitor");
        SystemUser test = new SystemUser("Test", "xxxx@isep.ipp.pt", "tester", "Qwerty_1", "Qwerty_1", "photo.png", pro.getUserProfileId());
        String name = "test";
        String email = "axxx";
        String func = "";
        int state = -1; //isActiveUser : -1 == null / 0 == false / 1 == true
        List<UserProfile> profiles = new ArrayList<>(); // profileId
        //Assert
        assertFalse(test.hasThisData(name, email, func, state, profiles));
    }

    @Test
    void hasThisDataWithThreeParametersFail() {
        //Arrange
        Company company = new Company();
        UserProfile visitor = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser test = new SystemUser("Test", "xxxx@isep.ipp.pt",
                "tester", "Qwerty_1", "Qwerty_1", "photo.png", visitor.getUserProfileId());

        String name = "test";
        String email = "xxxx";
        String func = "aest";
        int state = -1; //isActiveUser : -1 == null / 0 == false / 1 == true
        List<UserProfile> profiles = new ArrayList<>(); // profileId
        //Assert
        assertFalse(test.hasThisData(name, email, func, state, profiles));
    }

    @Test
    void hasThisDataWithFourParametersFail() {
        //Arrange
        UserProfile pro = new UserProfile("Visitor");
        SystemUser test = new SystemUser("Test", "xxxx@isep.ipp.pt", "tester",
                "Qwerty_1", "Qwerty_1", "photo.png", pro.getUserProfileId());
        String name = "test";
        String email = "xxxx";
        String func = "test";
        int state = 1; //isActiveUser : -1 == null / 0 == false / 1 == true
        List<UserProfile> profiles = new ArrayList<>(); // profileId
        //Assert
        assertFalse(test.hasThisData(name, email, func, state, profiles));
    }

    @Test
    void hasAllProfilesInTheListContainsFalse() {
        //Arrange
        UserProfile pro = new UserProfile("Visitor");
        UserProfile tes = new UserProfile("Director");
        SystemUser test = new SystemUser("Test", "xxxx@isep.ipp.pt", "tester",
                "Qwerty_1", "Qwerty_1", "photo.png", pro.getUserProfileId());
        String name = "test";
        String email = "xxxx";
        String func = "test";
        int state = 1; //isActiveUser : -1 == null / 0 == false / 1 == true
        List<UserProfile> profiles = new ArrayList<>(); // profileId
        //Act
        profiles.add(tes);
        //Assert
        assertFalse(test.hasThisData(name, email, func, state, profiles));
    }

    @Test
    public void activationUser() {
        //Test to activate the user
        //Arrange
        UserProfile tes = new UserProfile("Visitor");
        SystemUser ana = new SystemUser("Ana", "1211@isep.ipp.pt", "User_12",
                "Qwerty_1", "Qwerty_1", "photo.png", tes.getUserProfileId());
        //Act
        ana.setActive(true);
        //Assert
        assertTrue(ana.isActive());
    }

    @Test
    public void inactivationUser() {
        //Test to inactivate the user
        //Arrange
        UserProfile tes = new UserProfile("Visitor");
        SystemUser ana = new SystemUser("Ana", "1211@isep.ipp.pt", "User_12",
                "Qwerty_1", "Qwerty_1", "photo.png", tes.getUserProfileId());
        //Act
        ana.setActive(false);
        //Assert
        assertFalse(ana.isActive());
    }

    @Test
    public void setUserNameIsEmpty() {
        //Arrange
        UserProfile userProfile = mock(UserProfile.class);
        UserProfileId userProfileId = mock(UserProfileId.class);
        Description description = mock(Description.class);
        when(userProfile.getUserProfileId()).thenReturn(userProfileId);
        when(userProfileId.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("Visitor");
        SystemUser teste = new SystemUser("Cris", "112@isep.ipp.pt", "Aluna_10",
                "Qwerty_1", "Qwerty_1", "photo.png", userProfileId);
        String originalValue = teste.getUserName().getNameF();
        SystemUser teste3 = new SystemUser("Cris", "112@isep.ipp.pt", "Aluna_10",
                "Qwerty_1", "Qwerty_1", "photo.png", userProfileId);
        String originalValue3 = teste.getUserName().getNameF();
        SystemUser teste4 = new SystemUser("Cris", "112@isep.ipp.pt", "Aluna_10",
                "Qwerty_1", "Qwerty_1", "photo.png", userProfileId);
        //Act
        teste.setUserName("D");
        teste3.setUserName("CDC");
        teste4.setUserName("CD");
        // Assert
        assertNotEquals(originalValue, teste.getUserName().getNameF());
        assertNotEquals(originalValue3, teste3.getUserName().getNameF());
        assertEquals("CDC", teste3.getUserName().getNameF());
        assertEquals("CD", teste4.getUserName().getNameF());
    }

    @Test
    public void setFunction() {

        //Arrange
        UserProfile userProfile = mock(UserProfile.class);
        UserProfileId userProfileId = mock(UserProfileId.class);
        Description description = mock(Description.class);
        when(userProfile.getUserProfileId()).thenReturn(userProfileId);
        when(userProfileId.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("Visitor");
        SystemUser teste = new SystemUser("Cris", "112@isep.ipp.pt", "Aluna_10",
                "Qwerty_1", "Qwerty_1", "photo.png", userProfileId);
        SystemUser teste2 = new SystemUser("Cris", "112@isep.ipp.pt", "Aluna_10",
                "Qwerty_1", "Qwerty_1", "photo.png", userProfileId);
        SystemUser teste3 = new SystemUser("Cris", "112@isep.ipp.pt", "Aluna_10",
                "Qwerty_1", "Qwerty_1", "photo.png", userProfileId);
        SystemUser teste4 = new SystemUser("Cris", "112@isep.ipp.pt", "Aluna_10",
                "Qwerty_1", "Qwerty_1", "photo.png", userProfileId);
        SystemUser teste5 = new SystemUser("Cris", "112@isep.ipp.pt", "Aluna_10",
                "Qwerty_1", "Qwerty_1", "photo.png", userProfileId);
        //Act
        teste.setFunction("Dop");
        teste2.setFunction("Pop");
        teste3.setFunction("CDC");
        teste4.setFunction("CDg");
        teste5.setFunction("CkG");
        // Assert
        assertEquals("Dop", teste.getFunction().getText());
        assertEquals("Pop", teste2.getFunction().getText());
        assertEquals("CDC", teste3.getFunction().getText());
        assertEquals("CDg", teste4.getFunction().getText());
        assertEquals("CkG", teste5.getFunction().getText());

    }

    @Test
    public void setPhoto() {
        //Arrange
        UserProfile userProfile = mock(UserProfile.class);
        UserProfileId userProfileId = mock(UserProfileId.class);
        Description description = mock(Description.class);
        when(userProfile.getUserProfileId()).thenReturn(userProfileId);
        when(userProfileId.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("Visitor");
        SystemUser teste = new SystemUser("Cris", "1211770@isep.ipp.pt", "Aluna_10",
                "Qwerty_1", "Qwerty_1", "photo.png", userProfileId);
        String originalValue = teste.getPhoto().getExtension();
        SystemUser teste2 = new SystemUser("Cris", "1211770@isep.ipp.pt", "Aluna_10",
                "Qwerty_1", "Qwerty_1", "photo.png", userProfileId);
        String originalValue2 = teste.getPhoto().getExtension();
        //Act
        teste.setPhoto("photo1.jpg");
        teste2.setPhoto("photo.png");
        // Assert
        assertEquals(originalValue2, teste2.getPhoto().getExtension());
        assertNotEquals(originalValue, teste.getPhoto().getExtension());
        assertEquals("photo1.jpg", teste.getPhoto().getExtension());
    }

    @Test
    @DisplayName("create and save profile request with success")
    void createAndSaveProfileRequestWithSuccess() {
        //Arrange
        UserProfile visitorProfile = mock(UserProfile.class);
        UserProfileId visitorProfileId = mock(UserProfileId.class);
        Description descriptionDouble = mock(Description.class);
        when(visitorProfile.getUserProfileId()).thenReturn(visitorProfileId);
        when(visitorProfileId.getUserProfileName()).thenReturn(descriptionDouble);
        when(descriptionDouble.getText()).thenReturn("Visitor");
        SystemUser testUser = new SystemUser("Manuel", "1211770@isep.ipp.pt", "Aluno",
                "Qwerty_1", "Qwerty_1", "photo.png", visitorProfileId);

        UserProfile regularProfile = mock(UserProfile.class);
        UserProfileId regularProfileId = mock(UserProfileId.class);
        Description descriptionDouble2 = mock(Description.class);
        when(regularProfile.getUserProfileId()).thenReturn(regularProfileId);
        when(regularProfileId.getUserProfileName()).thenReturn(descriptionDouble2);
        when(descriptionDouble2.getText()).thenReturn("Regular User");
        //Act
        boolean hasCreated = testUser.createAndSaveProfileRequest(regularProfileId);
        //Assert
        assertTrue(hasCreated);
        assertEquals(1, testUser.getRequestedProfiles().size());
        assertEquals(1, testUser.getAssignedProfiles().size());
    }

    @Test
    @DisplayName("create and save profile request - profile already assigned")
    void createAndSaveProfileRequestFail() {
        //Arrange
        UserProfile visitorProfile = mock(UserProfile.class);
        UserProfileId visitorProfileId = mock(UserProfileId.class);
        Description descriptionDouble = mock(Description.class);
        when(visitorProfile.getUserProfileId()).thenReturn(visitorProfileId);
        when(visitorProfileId.getUserProfileName()).thenReturn(descriptionDouble);
        when(descriptionDouble.getText()).thenReturn("Visitor");
        SystemUser testUser = new SystemUser("Manuel", "1211770@isep.ipp.pt", "Aluno",
                "Qwerty_1", "Qwerty_1", "photo.png", visitorProfileId);
        //Act
        boolean hasCreated = testUser.createAndSaveProfileRequest(visitorProfileId);
        //Assert
        assertFalse(hasCreated);
        assertEquals(0, testUser.getRequestedProfiles().size());
        assertEquals(1, testUser.getAssignedProfiles().size());
    }

    @Test
    @DisplayName("create and save profile request - request already made")
    void createAndSaveProfileRequestFail2() {
        //Arrange
        UserProfile visitorProfile = mock(UserProfile.class);
        UserProfileId visitorProfileId = mock(UserProfileId.class);
        Description descriptionDouble = mock(Description.class);
        when(visitorProfile.getUserProfileId()).thenReturn(visitorProfileId);
        when(visitorProfileId.getUserProfileName()).thenReturn(descriptionDouble);
        when(descriptionDouble.getText()).thenReturn("Visitor");
        SystemUser testUser = new SystemUser("Manuel", "1211770@isep.ipp.pt", "Aluno",
                "Qwerty_1", "Qwerty_1", "photo.png", visitorProfileId);

        UserProfile regularProfile = mock(UserProfile.class);
        UserProfileId regularProfileId = mock(UserProfileId.class);
        Description descriptionDouble2 = mock(Description.class);
        when(regularProfile.getUserProfileId()).thenReturn(regularProfileId);
        when(regularProfileId.getUserProfileName()).thenReturn(descriptionDouble2);
        when(descriptionDouble2.getText()).thenReturn("Regular User");
        testUser.createAndSaveProfileRequest(regularProfileId);
        //Act
        boolean hasCreated = testUser.createAndSaveProfileRequest(regularProfileId);
        //Assert
        assertFalse(hasCreated);
        assertEquals(1, testUser.getRequestedProfiles().size());
        assertEquals(1, testUser.getAssignedProfiles().size());
    }*/
}