package switch2021.project.model;

import org.junit.jupiter.api.Test;
import switch2021.project.Immutables.Function;
import switch2021.project.stores.SystemUserStore;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SystemUserTest {

    @Test
    public void verifyEmail() {

        //Arrange
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser test = new SystemUser("Test", "xxxx@isep.ipp.pt", "tester", "123456",
                "123456", "img_123456", userProfile);
        //Act
        String emailCheck = "xxxx@isep.ipp.pt";
        //Assert
        assertTrue(test.isYourEmail(emailCheck));
    }

    @Test
    public void verifyEmailSuccess() {

        //Arrange
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser test = new SystemUser("Test", "xx", "tester", "123456",
                "123456", "img_123456", userProfile);
        //Act
        String emailCheck = "xx";
        //Assert
        assertTrue(test.isYourEmail(emailCheck));
    }

    @Test
    public void verifyEmailFail() {

        //Arrange
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser joana = new SystemUser("Joana Silva", "1234@isep.ipp.pt", "Aluna", "abcde", "abcde", "123_img", userProfile);
        //Act
        String emailCheck = "4321@isep.ipp.pt";
        //Assert
        assertFalse(joana.isYourEmail(emailCheck));
    }

    @Test
    public void verifyUserNameSuccess() {

        //Arrange
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser test = new SystemUser("Te", "xx", "tester", "123456",
                "123456", "img_123456", userProfile);
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
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser joana = new SystemUser(" ", "1234@isep.ipp.pt", "Aluna", "abcde", "abcde", "123_img", userProfile);
        });
    }

    @Test
    public void UpdateProfile() {
        //Arrange
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("xxx", "xxx@isep.ipp.pt", "tester", "123456", "123456", "img_123", profile);
        //Act
        UserProfile newProfile = company.getUserProfileStore().getUserProfile("User");
        // Assert
        assertTrue(user.updateProfile(profile, newProfile));
    }

    @Test
    public void UpdateProfileAlreadyExist() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            Company company = new Company();
            UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
            SystemUser user = new SystemUser("xxx", "xxx@isep.ipp.pt", "tester", "123456", "123456", "img_123", profile);
            //Act
            UserProfile newProfile = company.getUserProfileStore().getUserProfile("Visitor");
            user.updateProfile(profile, newProfile);
        });
    }

    @Test
    public void verifyUpdateAndEncryptationOfPassword() {

        //Test to verify if the oldpassword is updated by the newpassword, and this last one is
        //stored in system user with the encryptation method.

        //Arrange
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser joana = new SystemUser("Joana", "112@isep.ipp.pt", "Aluna_10", "HELLO", "HELLO", "png_123", userProfile);
        //Act
        joana.updatePassword("HELLO", "ghi", "ghi");
        //Assert
        assertEquals("ÊËÌ", joana.getPassword());
    }

    @Test
    public void verifyOldPassword() {

        //Test to verify if the oldpassword, stored in the system user, is equal or diferent from the
        //password that came from User Interface (UI).
        //Arrange
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser joana = new SystemUser("Joana", "112@isep.ipp.pt", "Aluna_10",
                "HELLO_01", "HELLO_01", "png_123", userProfile);
        //Act
        assertFalse(joana.updatePassword("HELLO_02", "GOODBYE", "GOODBYE"));

    }

    @Test
    public void InvalidPasswordInput() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            Company company = new Company();
            UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
            SystemUser user = new SystemUser("xxx", "xxx@isep.ipp.pt", "tester", "*#&", "123456", "img_123", profile);
            //Act
            UserProfile newProfile = company.getUserProfileStore().getUserProfile("Visitor");
            user.updateProfile(profile, newProfile);
        });
    }

    @Test
    public void setAllDataSuccess() {

        //Arrange
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser joana = new SystemUser("Joana", "112@isep.ipp.pt", "Aluna_10",
                "123", "123", "img_123", userProfile);
        //Act and Assert
        assertTrue(joana.setAllData("Joana Silva", "Aluna_100", "img_900"));
        assertEquals("Joana Silva", joana.getUserName().getNameF());
        assertEquals("Aluna_100", joana.getFunction().getText());
        assertEquals("img_900", joana.getPhoto());
    }

    @Test
    public void checkAllDataSuccess() {

        //Arrange
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser cris = new SystemUser("Joana", "1211770@isep.ipp.pt", "Aluna_10",
                "123", "123", "img_123", userProfile);
        //Act and Assert
        assertTrue(cris.checkAllData("CDC", "ALN", "img_900"));
    }

    @Test
    public void checkAllDataFailUsernameEmpty() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            UserProfile tes = new UserProfile("ddd");
            SystemUser newUser = new SystemUser(" ", "112@isep.ipp.pt",
                    "Aluna_10", "123", "123", "img_900", tes);
            newUser.checkAllData(" ", "Aluna_10", "img_123");
        });
    }


    @Test
    public void checkAllDataFailUsernameLowLength() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            UserProfile tes = new UserProfile("ddd");
            SystemUser newUser = new SystemUser("J", "112@isep.ipp.pt",
                    "Aluna_10", "123", "123", "img_900", tes);
            newUser.checkAllData("J", "Aluna_10", "img_123");
        });
    }

    @Test
    public void checkAllDataFailUsernameLowLength2() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            UserProfile tes = new UserProfile("ddd");
            SystemUser newUser = new SystemUser("J", "112@isep.ipp.pt",
                    "Aluna_10", "123", "123", "img_900", tes);
            newUser.checkAllData("JJ", "Aluna_10", "img_123");
        });
    }

    @Test
    public void checkAllDataFailUsernameLowLength3() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            UserProfile tes = new UserProfile("ddd");
            SystemUser newUser = new SystemUser("J", "112@isep.ipp.pt",
                    "Aluna_10", "123", "123", "img_900", tes);
            newUser.checkAllData("", "Aluna_10", "img_123");
        });
    }

    @Test
    public void setAllDataFailUsernameLowLength2() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            UserProfile tes = new UserProfile("ddd");
            SystemUser newUser = new SystemUser("JJJJ", "112@isep.ipp.pt",
                    "Aluna_10", "123", "123", "img_900", tes);
            newUser.setAllData("J", "A", "img_900");

        });
    }

    @Test
    public void setAllDataFailEmpty() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            UserProfile tes = new UserProfile("ddd");
            SystemUser newUser = new SystemUser("JJJJ", "112@isep.ipp.pt",
                    "Aluna_10", "123", "123", "img_900", tes);
            newUser.setAllData("  ", "", "");
        });
    }

    @Test
    public void setAllDataFailUsernameLowLength4() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            UserProfile tes = new UserProfile("ddd");
            SystemUser newUser = new SystemUser("JJJJ", "112@isep.ipp.pt",
                    "Aluna_10", "123", "123", "img_900", tes);
            newUser.setAllData("JJ", "Al", "img_900");
        });
    }

    @Test
    public void setAllDataFailNull() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            UserProfile tes = new UserProfile("ddd");
            SystemUser newUser = new SystemUser("JJJJ", "112@isep.ipp.pt",
                    "Aluna_10", "123", "123", "img_900", tes);
            newUser.setAllData(null, null, null);
        });
    }

    @Test
    public void checkAllDataFailFunctionEmpty() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            UserProfile tes = new UserProfile("ddd");
            SystemUser newUser = new SystemUser("Joana Silva", "112@isep.ipp.pt",
                    "", "123", "123", "img_900", tes);
            newUser.checkAllData("Joana Silva", "", "img_123");
        });
    }

    @Test
    public void checkAllDataFailFunctionLowLength() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            UserProfile tes = new UserProfile("ddd");
            SystemUser newUser = new SystemUser("Joana Silva", "112@isep.ipp.pt",
                    "Aluna_10", "123", "123", "img_900", tes);
            newUser.checkAllData("Joana Silva", "A", "img_123");
        });
    }

    @Test
    public void setAllDataFailFunctionLowLength2() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            UserProfile tes = new UserProfile("ddd");
            SystemUser newUser = new SystemUser("Joana Silva", "112@isep.ipp.pt",
                    "Aluna_10", "123", "123", "img_900", tes);
            newUser.setAllData("Joana Silva", "A", "img_123");
        });
    }

    @Test
    public void checkAllDataFailFunctionLowLength4() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            UserProfile tes = new UserProfile("ddd");
            SystemUser newUser = new SystemUser("Joana Silva", "112@isep.ipp.pt",
                    "Aluna_10", "123", "123", "img_900", tes);
            newUser.checkAllData("Joana Silva", "AA", "img_123");
        });
    }

    @Test
    public void setAllDataFailFunctionLowLength3() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            UserProfile tes = new UserProfile("ddd");
            SystemUser newUser = new SystemUser("Joana Silva", "112@isep.ipp.pt",
                    "Aluna_10", "123", "123", "img_900", tes);
            newUser.setAllData("Joana Silva", "   ", "img_123");
        });
    }

    @Test
    public void checkAllDataFailFunctionLowLength5() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            UserProfile tes = new UserProfile("ddd");
            SystemUser newUser = new SystemUser("Joana Silva", "112@isep.ipp.pt",
                    "Aluna_10", "123", "123", "img_900", tes);
            newUser.checkAllData("Joana Silva", "   ", "img_123");
        });
    }

    @Test
    public void checkAllDataFailPhotoEmpty() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            UserProfile tes = new UserProfile("ddd");
            SystemUser newUser = new SystemUser("Joana Silva", "112@isep.ipp.pt",
                    "Aluna_10", "123", "123", "", tes);
            newUser.checkAllData("Joana Silva", "Aluna_10", "");
        });
    }

    @Test
    public void checkAllDataFailPhotoEmpty2() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            UserProfile tes = new UserProfile("ddd");
            SystemUser newUser = new SystemUser("Joana Silva", "112@isep.ipp.pt",
                    "Aluna_10", "123", "123", "", tes);
            newUser.checkAllData("Joana Silva", "Aluna_10", "  ");
        });
    }

    @Test
    public void setAllDataFailPhotoEmpty2() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            UserProfile tes = new UserProfile("ddd");
            SystemUser newUser = new SystemUser("Joana Silva", "112@isep.ipp.pt",
                    "Aluna_10", "123", "123", "", tes);
            newUser.setAllData("Joana Silva", "Aluna_10", "");
        });
    }

    @Test
    public void createSystemUserSuccess() {
        //Arrange
        Company company = new Company();
        String userName = "manueloliveira";
        String email = "manueloliveira@beaver.com";
        String password = "gh";
        String passwordConfirmation = "gh";
        String function = "teeee";
        String photo = "photo";

        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser(userName, email, function, password, passwordConfirmation, photo, profile);

        List<UserProfile> assignedProfileExpected = new ArrayList<>();
        assignedProfileExpected.add(profile);
        String passwordExpected = "ÊË";
        //Assert
        assertEquals(userName, newUser.getUserName().getNameF());
        assertEquals(email, newUser.getEmail());
        assertEquals(passwordExpected, newUser.getPassword());
        assertEquals(function, newUser.getFunction().getText());
        assertEquals(photo, newUser.getPhoto());
        assertFalse(newUser.isActivateUser());
        assertEquals(assignedProfileExpected, newUser.getAssignedProfileList());
    }

    @Test
    public void encryptPasswordSuccess() {
        //Arrange
        String userName = "manueloliveira";
        String email = "manueloliveira@beaver.com";
        String password = "ghi";
        String passwordConfirmation = "ghi";
        String function = "tester";
        String photo = "photo";
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser(userName, email, function, password, passwordConfirmation, photo, profile);
        //Act
        String result = newUser.encryptPassword(password);
        //Assert
        assertEquals("ÊËÌ", result);
    }

    @Test
    public void encryptPasswordFail() {
        //Arrange
        String userName = "manueloliveira";
        String email = "manueloliveira@beaver.com";
        String password = "ghi";
        String passwordConfirmation = "ghi";
        String function = "tester";
        String photo = "photo";
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser(userName, email, function, password, passwordConfirmation, photo, profile);
        //Act
        String result = newUser.encryptPassword(password);
        //Assert
        assertNotEquals("ghi", result);
    }

    @Test
    public void decryptPasswordSuccess() {
        //Arrange
        String userName = "manueloliveira";
        String email = "manueloliveira@beaver.com";
        String password = "a1b2c3";
        String passwordConfirmation = "a1b2c3";
        String function = "tester";
        String photo = "photo";
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser(userName, email, function, password, passwordConfirmation, photo, profile);

        String encryptedPassword = newUser.getPassword();//encryptedPassword = "Ä\u0094Å\u0095Æ\u0096";
        //Act
        String result = newUser.decryptPassword(encryptedPassword);
        //Assert
        assertEquals("a1b2c3", result);
    }

    @Test
    public void decryptPasswordFail() {
        //Arrange
        String userName = "manueloliveira";
        String email = "manueloliveira@beaver.com";
        String password = "a1b2c3";
        String passwordConfirmation = "a1b2c3";
        String function = "tester";
        String photo = "photo";
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser(userName, email, function, password, passwordConfirmation, photo, profile);

        String encryptedPassword = newUser.getPassword();//encryptedPassword = "Ä\u0094Å\u0095Æ\u0096";
        //Act
        String result = newUser.decryptPassword(encryptedPassword);
        //Assert
        assertNotEquals("Ä\\u0094Å\\u0095Æ\\u0096", result);
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
            Company company = new Company();
            UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
            new SystemUser(userName, email, function, password, passwordConfirmation, photo, profile);
        });
    }

    @Test
    public void createSystemUserFailUserNameIsShort() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String userName = "M";
            String email = "manueloliveira@beaver.com";
            String password = "ghi";
            String passwordConfirmation = "ghi";
            String function = "tester";
            String photo = "photo";
            Company company = new Company();
            UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
            new SystemUser(userName, email, function, password, passwordConfirmation, photo, profile);
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
            Company company = new Company();
            UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
            new SystemUser(userName, email, function, password, passwordConfirmation, photo, profile);
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
            Company company = new Company();
            UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
            new SystemUser(userName, email, function, password, passwordConfirmation, photo, profile);
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
            Company company = new Company();
            UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
            new SystemUser(userName, email, function, password, passwordConfirmation, photo, profile);
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
            Company company = new Company();
            UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
            new SystemUser(userName, email, function, password, passwordConfirmation, photo, profile);
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
            Company company = new Company();
            UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
            new SystemUser(userName, email, function, password, passwordConfirmation, photo, profile);
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
            Company company = new Company();
            UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
            new SystemUser(userName, email, function, password, passwordConfirmation, photo, profile);
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
            Company company = new Company();
            UserProfile profile = company.getUserProfileStore().getUserProfile("User");
            new SystemUser(userName, email, function, password, passwordConfirmation, photo, profile);
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
            Company company = new Company();
            UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
            new SystemUser(userName, email, function, password, passwordConfirmation, photo, profile);
        });
    }

    @Test
    public void overrideAndHashCodeTest() {
        //Arrange
        String userName = "manueloliveira";
        String email = "manueloliveira@beaver.com";
        String password = "ghi";
        String passwordConfirmation = "ghi";
        String function = "tester";
        String photo = "photo";
        String email2 = "maneloliveira@beaver.com";
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        //newUser and newUser2 are equals
        SystemUser newUser = company.getSystemUserStore().createSystemUser(userName, email, function, password, passwordConfirmation, photo, profile);
        SystemUser newUser2 = company.getSystemUserStore().createSystemUser(userName, email, function, password, passwordConfirmation, photo, profile);
        //newUser3 is different (different email)
        SystemUser newUser3 = company.getSystemUserStore().createSystemUser(userName, email2, function, password, passwordConfirmation, photo, profile);
        //Assert
        assertNotSame(newUser, newUser2);
        assertEquals(newUser, newUser2);
        assertEquals(newUser.hashCode(), newUser2.hashCode());
        assertNotEquals(newUser, newUser3);
        assertNotEquals(newUser.hashCode(), newUser3.hashCode());
    }

    @Test
    public void setActivateUser() {
        //Arrange
        String userName = "manueloliveira";
        String email = "manueloliveira@beaver.com";
        String password = "ghi";
        String passwordConfirmation = "ghi";
        String function = "tester";
        String photo = "photo";
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser(userName, email, function, password, passwordConfirmation, photo, profile);
        assertFalse(newUser.isActivateUser());
        //Act
        newUser.setActivateUser(true);
        //Assert
        assertTrue(newUser.isActivateUser());
    }

    @Test
    void hasThisDataWithAll() {
        //Input
        UserProfile pro = new UserProfile("Visitor");
        SystemUser test = new SystemUser("Test tester", "xxxx@isep.pt", "tester",
                "123456", "123456", "img_123", pro);
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
                "123456", "123456", "img_123", pro);
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
                "123456", "123456", "img_123", pro);
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
        SystemUser test = new SystemUser("Test", "xxxx@isep.ipp.pt",
                "tester", "123456", "123456", "img_123", pro);
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
                "123456", "123456", "img_123", pro);
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
                "123456", "123456", "img_123", pro);
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
                "123456", "123456", "img_123", pro);
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
    void hasThisDataWithTwoProfilesSuccess() {
        //Arrange
        UserProfile pro = new UserProfile("Visitor");
        UserProfile pro1 = new UserProfile("Visitor");
        SystemUser test = new SystemUser("Test", "xxxx@isep.ipp.pt", "tester",
                "123456", "123456", "img_123", pro);
        //Act
        test.assignProfileToUser(pro1);
        //Assert
        assertTrue(test.hasProfile(pro1));
        assertTrue(test.hasProfile(pro));
    }

    @Test
    void hasThisDataWithTwoParametersSuccess() {
        //Arrange
        UserProfile pro = new UserProfile("Visitor");
        SystemUser test = new SystemUser("Test", "xxxx@isep.ipp.pt",
                "tester", "123456", "123456", "img_123", pro);
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
                "tester", "123456", "123456", "img_123", pro);
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
                "tester", "123456", "123456", "img_123", pro);
        String name = "Test";
        String email = "xxxx";
        String func = "test";
        int state = 0; //isActiveUser : -1 == null / 0 == false / 1 == true
        List<UserProfile> profiles = new ArrayList<>(); // profileId
        //Assert
        assertTrue(test.hasThisData(name, email, func, state, profiles));
    }


    /**
     * FAIL TESTS
     */
    @Test
    void hasThisDataWithAllFail() {
        //Arrange
        UserProfile pro = new UserProfile("Visitor");
        SystemUser test = new SystemUser("test", "xxxx@isep.ipp.pt",
                "tester", "123456", "123456", "img_123", pro);
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
        SystemUser test = new SystemUser("Test", "xxxx@isep.pt", "tester", "123456", "123456", "img_123", pro);
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
                "tester", "123456", "123456", "img_123", pro);
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
                "tester", "123456", "123456", "img_123", pro);
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
                "123456", "123456", "img_123", pro);
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
                "123456", "123456", "img_123", pro);
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
                "123456", "123456", "img_123", pro);
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
                "123456", "123456", "img_123", pro);
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
        SystemUser test = new SystemUser("Test", "xxxx@isep.ipp.pt", "tester", "123456", "123456", "img_123", pro);
        String name = "";
        String email = "";
        String func = "";
        int state = -1; //isActiveUser : -1 == null / 0 == false / 1 == true
        List<UserProfile> profiles = new ArrayList<>(); // profileId
        //Assert
        assertFalse(test.hasThisData(name, email, func, state, profiles));
    }

    @Test
    void hasThisDataWithTwoProfilesFail() {
        //Arrange
        UserProfile pro = new UserProfile("Visitor");
        UserProfile pre = new UserProfile("Visitor");
        SystemUser test = new SystemUser("Test", "xxxx@isep.ipp.pt", "tester",
                "123456", "123456", "img_123", pro);
        test.assignProfileToUser(pre);
        test.assignProfileToUser(pro);
        String name = "";
        String email = "";
        String func = "";
        int state = -1; //isActiveUser : -1 == null / 0 == false / 1 == true
        List<UserProfile> profiles = new ArrayList<>(); // profileId
        //Assert
        assertFalse(test.hasThisData(name, email, func, state, profiles));
    }

    @Test
    void hasThisDataWithTwoProfilesFail2() {
        //Arrange
        UserProfile pro = new UserProfile("Visitor");
        UserProfile pre = new UserProfile("A");
        SystemUser test = new SystemUser("Test", "xxxx@isep.ipp.pt", "tester",
                "123456", "123456", "img_123", pro);
        test.assignProfileToUser(pre);
        test.assignProfileToUser(pro);
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
        SystemUser test = new SystemUser("Test", "xxxx@isep.ipp.pt", "tester", "123456", "123456", "img_123", pro);
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
        SystemUser test = new SystemUserStore().createSystemUser("Test", "xxxx@isep.ipp.pt",
                "tester", "12345", "12345", "img_123", visitor);

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
                "123456", "123456", "img_123", pro);
        String name = "test";
        String email = "xxxx";
        String func = "test";
        int state = 1; //isActiveUser : -1 == null / 0 == false / 1 == true
        List<UserProfile> profiles = new ArrayList<>(); // profileId
        //Assert
        assertFalse(test.hasThisData(name, email, func, state, profiles));
    }

    @Test
    public void activationUser() {
        //Test to activate the user
        //Arrange
        UserProfile tes = new UserProfile("Visitor");
        SystemUser ana = new SystemUser("Ana", "1211@isep.ipp.pt", "User_12",
                "HELLO", "HELLO", "HELLO", tes);
        //Act
        ana.setActivateUser(true);
        //Assert
        assertTrue(ana.isActivateUser());
    }

    @Test
    public void inactivationUser() {
        //Test to inactivate the user
        //Arrange
        UserProfile tes = new UserProfile("Visitor");
        SystemUser ana = new SystemUser("Ana", "1211@isep.ipp.pt", "User_12",
                "HELLO", "HELLO", "HELLO", tes);
        //Act
        ana.setActivateUser(false);
        //Assert
        assertFalse(ana.isActivateUser());
    }

    @Test
    public void setAllDataFail() {

        //Arrange

        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser joana = new SystemUser("Joana", "112@isep.ipp.pt", "Aluna_10",
                "123", "123", "img_123", userProfile);
        //Act and Assert
        assertThrows(IllegalArgumentException.class, () -> {
            assertFalse(joana.setAllData("", "User_12", "img_900"));
        });
    }

    @Test
    public void setAllDataFail2() {

        //Arrange

        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser joana = new SystemUser("Joana", "112@isep.ipp.pt", "Aluna_10",
                "123", "123", "img_123", userProfile);
        //Act and Assert
        assertThrows(IllegalArgumentException.class, () -> {
            assertFalse(joana.setAllData("Cris", "X", "img_900"));
        });
    }

    @Test
    public void setAllDataFail3() {

        //Arrange

        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser joana = new SystemUser("Joana", "112@isep.ipp.pt", "Aluna_10",
                "123", "123", "img_123", userProfile);
        //Act and Assert
        assertThrows(IllegalArgumentException.class, () -> {
            assertFalse(joana.setAllData("Cris", "User_12", ""));
        });
    }

    @Test
    public void setUserNameIsEmpty() {

        //Arrange

        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser teste = new SystemUser("Cris", "112@isep.ipp.pt", "Aluna_10",
                "123", "123", "img_123", userProfile);
        String originalValue = teste.getUserName().getNameF();
        SystemUser teste2 = new SystemUser("Cris", "112@isep.ipp.pt", "Aluna_10",
                "123", "123", "img_123", userProfile);
        String originalValue2 = teste.getUserName().getNameF();
        SystemUser teste3 = new SystemUser("Cris", "112@isep.ipp.pt", "Aluna_10",
                "123", "123", "img_123", userProfile);
        String originalValue3 = teste.getUserName().getNameF();
        SystemUser teste4 = new SystemUser("Cris", "112@isep.ipp.pt", "Aluna_10",
                "123", "123", "img_123", userProfile);
        String originalValue4 = teste.getUserName().getNameF();

        //Act

        teste.setUserName("D");
        teste2.setUserName("");
        teste3.setUserName("CDC");
        teste4.setUserName("CD");

        // Assert
        assertNotEquals(originalValue, teste.getUserName().getNameF());
        assertEquals(originalValue2, teste2.getUserName().getNameF());
        assertNotEquals(originalValue3, teste3.getUserName().getNameF());
        assertEquals("CDC", teste3.getUserName().getNameF());
        assertEquals("CD", teste4.getUserName().getNameF());
    }

    @Test
    public void setFunction() {

        //Arrange

        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser teste = new SystemUser("Cris", "112@isep.ipp.pt", "Aluna_10",
                "123", "123", "img_123", userProfile);
        String originalValue = teste.getFunction().getText();
        SystemUser teste2 = new SystemUser("Cris", "112@isep.ipp.pt", "Aluna_10",
                "123", "123", "img_123", userProfile);
        String originalValue2 = teste.getFunction().getText();
        SystemUser teste3 = new SystemUser("Cris", "112@isep.ipp.pt", "Aluna_10",
                "123", "123", "img_123", userProfile);
        String originalValue3 = teste.getFunction().getText();
        SystemUser teste4 = new SystemUser("Cris", "112@isep.ipp.pt", "Aluna_10",
                "123", "123", "img_123", userProfile);
        String originalValue4 = teste.getFunction().getText();
        SystemUser teste5 = new SystemUser("Cris", "112@isep.ipp.pt", "Aluna_10",
                "123", "123", "img_123", userProfile);
        String originalValue5 = teste5.getFunction().getText();

        //Act

        teste.setFunction("D");
        teste2.setFunction("  ");
        teste3.setFunction("CDC");
        teste4.setFunction("CD");
        teste5.setFunction("C G");

        // Assert
        assertEquals(originalValue, teste.getFunction().getText());
        assertEquals(originalValue2, teste2.getFunction().getText());
        assertNotEquals(originalValue3, teste3.getFunction().getText());
        assertEquals("CDC", teste3.getFunction().getText());
        assertNotEquals("CD", teste4.getFunction().getText());
        assertNotEquals(originalValue5, teste5.getFunction().getText());
    }

    @Test
    public void setFunctionNull() { assertThrows(NullPointerException.class, () -> {
        //Arrange
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser teste5 = new SystemUser("Cris", "112@isep.ipp.pt", "Aluna_10",
                "123", "123", "img_123", userProfile);
        //Act
        teste5.setFunction(null);
    });
}

    @Test
    public void setPhoto() {

        //Arrange

        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser teste = new SystemUser("Cris", "1211770@isep.ipp.pt", "Aluna_10",
                "123", "123", "img_123", userProfile);
        String originalValue = teste.getPhoto();
        SystemUser teste2 = new SystemUser("Cris", "1211770@isep.ipp.pt", "Aluna_10",
                "123", "123", "img_123", userProfile);
        String originalValue2 = teste.getPhoto();

        //Act

        teste.setPhoto("D");
        teste2.setPhoto("");


        // Assert
        assertEquals(originalValue2, teste2.getPhoto());
        assertNotEquals(originalValue, teste.getPhoto());
        assertEquals("D", teste.getPhoto());

    }


}