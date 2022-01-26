package switch2021.project.model;

import org.junit.jupiter.api.Test;
import switch2021.project.stores.SystemUserStore;
import switch2021.project.stores.UserProfileStore;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SystemUserTest {

    @Test
    public void verifyEmail() {

        //Arrange
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser ivan = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "123456",
                "123456", "img_123456", userProfile);
        //Act
        String emailCheck = "xxxx";
        //Assert
        assertTrue(ivan.isYourEmail(emailCheck));
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
        SystemUser joana = new SystemUser("Joana", "1211770@isep.ipp.pt", "Aluna_10", "HELLO", "HELLO", "png_123", userProfile);
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
        SystemUser joana = new SystemUser("Joana", "1211770@isep.ipp.pt", "Aluna_10",
                "HELLO_01", "HELLO_01", "png_123", userProfile);
        //Act
        assertFalse(joana.updatePassword("HELLO_02", "GOODBYE", "GOODBYE"));

    }

    @Test
    public void setAllDataSuccess() {

        //Arrange
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser joana = new SystemUser("Joana", "1211770@isep.ipp.pt", "Aluna_10",
                "123", "123", "img_123", userProfile);
        //Act and Assert
        assertTrue(joana.setAllData("Joana Silva", "Aluna_100", "img_900"));
    }

    @Test
    public void setAllDataFailUsernameEmpty() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            UserProfile tes = new UserProfile("ddd");
            SystemUser newUser = new SystemUser(" ", "1211770@isep.ipp.pt",
                    "Aluna_10", "123", "123", "img_900", tes);
            newUser.checkAllData(" ", "Aluna_10", "img_123");
        });
    }

    @Test
    public void setAllDataFailUsernameLowLength() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            UserProfile tes = new UserProfile("ddd");
            SystemUser newUser = new SystemUser("J", "1211770@isep.ipp.pt",
                    "Aluna_10", "123", "123", "img_900", tes);
            newUser.checkAllData("J", "Aluna_10", "img_123");
        });
    }

    @Test
    public void setAllDataFailFunctionEmpty() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            UserProfile tes = new UserProfile("ddd");
            SystemUser newUser = new SystemUser("Joana Silva", "1211770@isep.ipp.pt",
                    " ", "123", "123", "img_900", tes);
            newUser.checkAllData("Joana Silva", " ", "img_123");
        });
    }

    @Test
    public void setAllDataFailFunctionLowLength() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            UserProfile tes = new UserProfile("ddd");
            SystemUser newUser = new SystemUser("Joana Silva", "1211770@isep.ipp.pt",
                    "Aluna_10", "123", "123", "img_900", tes);
            newUser.checkAllData("Joana Silva", "A", "img_123");
        });
    }

    @Test
    public void setAllDataFailPhotoEmpty() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            UserProfile tes = new UserProfile("ddd");
            SystemUser newUser = new SystemUser("Joana Silva", "1211770@isep.ipp.pt",
                    "Aluna_10", "123", "123", "", tes);
            newUser.checkAllData("Joana Silva", "Aluna_10", "");
        });
    }

    @Test
    public void createSystemUserSuccess() {
        //Arrange
        Company company = new Company();
        String userName = "manueloliveira";
        String email = "manueloliveira@beaver.com";
        String password = "ghi";
        String passwordConfirmation = "ghi";
        String function = "tester";
        String photo = "photo";

        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser(userName, email, function, password, passwordConfirmation, photo, profile);

        List<UserProfile> assignedProfileExpected = new ArrayList<>();
        assignedProfileExpected.add(profile);
        String passwordExpected = "ÊËÌ";
        //Assert
        assertEquals(userName, newUser.getUserName());
        assertEquals(email, newUser.getEmail());
        assertEquals(passwordExpected, newUser.getPassword());
        assertEquals(function, newUser.getFunction());
        assertEquals(photo, newUser.getPhoto());
        assertFalse(newUser.getActivateUserStatus());
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
            SystemUser newUser = new SystemUser(userName, email, function, password, passwordConfirmation, photo, profile);
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
            SystemUser newUser = new SystemUser(userName, email, function, password, passwordConfirmation, photo, profile);
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
            SystemUser newUser = new SystemUser(userName, email, function, password, passwordConfirmation, photo, profile);
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
            SystemUser newUser = new SystemUser(userName, email, function, password, passwordConfirmation, photo, profile);
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
            SystemUser newUser = new SystemUser(userName, email, function, password, passwordConfirmation, photo, profile);
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
            SystemUser newUser = new SystemUser(userName, email, function, password, passwordConfirmation, photo, profile);
        });
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
        assertFalse(newUser.getActivateUserStatus());
        //Act
        newUser.setActivateUser();
        //Assert
        assertTrue(newUser.getActivateUserStatus());
    }

//    @Test
//    void hasThisDataWithAll() {
//        //Input
//        UserProfile pro = new UserProfile("ddd", "pro");
//        SystemUser ivan = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "123456", "123456", "img_123", pro);
//        String name = "ivan";
//        String email = "xxxx";
//        String func = "test";
//        int state = 0; // -1 == null / 0 == false / 1 == true
//        int[] profiles = {}; // profileId
//        //Result
//        assertTrue(ivan.hasThisData(name, email, func, state, profiles));
//    }
//
//    @Test
//    void hasThisDataWithoutAll() {
//        //Input
//        UserProfile pro = new UserProfile("ddd", "pro");
//        SystemUser ivan = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "123456", "123456", "img_123", pro);
//        String name = "";
//        String email = "";
//        String func = "";
//        int state = -1; //-1 == null / 0 == false / 1 == true
//        int[] profiles = {}; // profile Id
//        //Result
//        assertFalse(ivan.hasThisData(name, email, func, state, profiles));
//    }
//
//    @Test
//    void hasThisDataWithNameSuccess() {
//        //Input
//        UserProfile pro = new UserProfile("ddd", "pro");
//        SystemUser ivan = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "123456", "123456", "img_123", pro);
//        String name = "ivan";
//        String email = "";
//        String func = "";
//        int state = -1; //-1 == null / 0 == false / 1 == true
//        int[] profiles = {}; // profile Id
//        //Result
//        assertTrue(ivan.hasThisData(name, email, func, state, profiles));
//    }
//
//    @Test
//    void hasThisDataWithEmailSuccess() {
//        //Input
//        UserProfile pro = new UserProfile("ddd", "pro");
//        SystemUser ivan = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "123456", "123456", "img_123", pro);
//        String name = "";
//        String email = "xxxx";
//        String func = "";
//        int state = -1; //-1 == null / 0 == false / 1 == true
//        int[] profiles = {}; // profile Id
//        //Result
//        assertTrue(ivan.hasThisData(name, email, func, state, profiles));
//    }
//
//    @Test
//    void hasThisDataWithFunctionSuccess() {
//        //Input
//        UserProfile pro = new UserProfile("ddd", "pro");
//        SystemUser ivan = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "123456", "123456", "img_123", pro);
//        String name = "";
//        String email = "";
//        String func = "test";
//        int state = -1; //-1 == null / 0 == false / 1 == true
//        int[] profiles = {}; // profile Id
//        //Result
//        assertTrue(ivan.hasThisData(name, email, func, state, profiles));
//    }
//
//    @Test
//    void hasThisDataWithStateSuccess() {
//        //Input
//        UserProfile pro = new UserProfile("ddd", "pro");
//        SystemUser ivan = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "123456", "123456", "img_123", pro);
//        String name = "";
//        String email = "";
//        String func = "";
//        int state = 0; //isActiveUser : -1 == null / 0 == false / 1 == true
//        int[] profiles = {}; // profile Id
//        //Result
//        assertTrue(ivan.hasThisData(name, email, func, state, profiles));
//    }
//
//    /*@Test
//    void hasThisDataWithProfilesSuccess() {
//        //Input
//        Profile pro = new Profile("ddd", "pro");
//        SystemUser ivan = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "123456", pro);
//        String name = "";
//        String email = "";
//        String func = "";
//        int state = -1; //isActiveUser : -1 == null / 0 == false / 1 == true
//        int[] profiles = {0}; // profile Id
//        //Result
//        assertTrue(ivan.hasThisData(name, email, func, state, profiles));
//    }*/
//
//    /*@Test
//    void hasThisDataWithTwoProfilesSuccess() {
//        //Input
//        Profile pro = new Profile("ddd", "pro");
//        Profile pre = new Profile("aaa", "pre");
//        SystemUser ivan = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "123456", pro);
//        ivan.addProfileToList(pre);
//        String name = "";
//        String email = "";
//        String func = "";
//        int state = -1; //isActiveUser : -1 == null / 0 == false / 1 == true
//        int[] profiles = {1}; // profile Id
//        //Result
//        assertTrue(ivan.hasThisData(name, email, func, state, profiles));
//    }*/
//
//    @Test
//    void hasThisDataWithTwoParametersSuccess() {
//        //Input
//        UserProfile pro = new UserProfile("ddd", "pro");
//        SystemUser ivan = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "123456", "123456", "img_123", pro);
//        String name = "ivan";
//        String email = "xxxx";
//        String func = "";
//        int state = -1; //isActiveUser : -1 == null / 0 == false / 1 == true
//        int[] profiles = {}; // profile Id
//        //Result
//        assertTrue(ivan.hasThisData(name, email, func, state, profiles));
//    }
//
//    @Test
//    void hasThisDataWithThreeParametersSuccess() {
//        //Input
//        UserProfile pro = new UserProfile("ddd", "pro");
//        SystemUser ivan = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "123456", "123456", "img_123", pro);
//        String name = "ivan";
//        String email = "xxxx";
//        String func = "test";
//        int state = -1; //isActiveUser : -1 == null / 0 == false / 1 == true
//        int[] profiles = {}; // profile Id
//        //Result
//        assertTrue(ivan.hasThisData(name, email, func, state, profiles));
//    }
//
//    @Test
//    void hasThisDataWithFourParametersSuccess() {
//        //Input
//        UserProfile pro = new UserProfile("ddd", "pro");
//        SystemUser ivan = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "123456", "123456", "img_123", pro);
//        String name = "ivan";
//        String email = "xxxx";
//        String func = "test";
//        int state = 0; //isActiveUser : -1 == null / 0 == false / 1 == true
//        int[] profiles = {}; // profile Id
//        //Result
//        assertTrue(ivan.hasThisData(name, email, func, state, profiles));
//    }
//
//    /**
//     * FAIL TESTS
//     */
//
//    @Test
//    void hasThisDataWithAllFail() {
//        //Input
//        UserProfile pro = new UserProfile("ddd", "pro");
//        SystemUser ivan = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "123456", "123456", "img_123", pro);
//        String name = "avan";
//        String email = "xxxx";
//        String func = "test";
//        int state = 0; // -1 == null / 0 == false / 1 == true
//        int[] profiles = {0}; // profileId
//        //Result
//        assertFalse(ivan.hasThisData(name, email, func, state, profiles));
//    }
//
//    @Test
//    void hasThisDataWithNameFail() {
//        //Input
//        UserProfile pro = new UserProfile("ddd", "pro");
//        SystemUser ivan = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "123456", "123456", "img_123", pro);
//        String name = "avan";
//        String email = "";
//        String func = "";
//        int state = -1; //-1 == null / 0 == false / 1 == true
//        int[] profiles = {}; // profile Id
//        //Result
//        assertFalse(ivan.hasThisData(name, email, func, state, profiles));
//    }
//
//    @Test
//    void hasThisDataWithEmailFail() {
//        //Input
//        UserProfile pro = new UserProfile("ddd", "pro");
//        SystemUser ivan = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "123456", "123456", "img_123", pro);
//        String name = "";
//        String email = "yxxx";
//        String func = "";
//        int state = -1; //-1 == null / 0 == false / 1 == true
//        int[] profiles = {}; // profile Id
//        //Result
//        assertFalse(ivan.hasThisData(name, email, func, state, profiles));
//    }
//
//    @Test
//    void hasThisDataWithFunctionFail() {
//        //Input
//        UserProfile pro = new UserProfile("ddd", "pro");
//        SystemUser ivan = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "123456", "123456", "img_123", pro);
//        String name = "";
//        String email = "";
//        String func = "tesq";
//        int state = -1; //-1 == null / 0 == false / 1 == true
//        int[] profiles = {}; // profile Id
//        //Result
//        assertFalse(ivan.hasThisData(name, email, func, state, profiles));
//    }
//
//    @Test
//    void hasThisDataWithStateFail() {
//        //Input
//        UserProfile pro = new UserProfile("ddd", "pro");
//        SystemUser ivan = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "123456", "123456", "img_123", pro);
//        String name = "";
//        String email = "";
//        String func = "";
//        int state = 1; //isActiveUser : -1 == null / 0 == false / 1 == true
//        int[] profiles = {}; // profile Id
//        //Result
//        assertFalse(ivan.hasThisData(name, email, func, state, profiles));
//    }
//
//    @Test
//    void hasThisDataWithProfilesFail() {
//        //Input
//        UserProfile pro = new UserProfile("ddd", "pro");
//        SystemUser ivan = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "123456", "123456", "img_123", pro);
//        String name = "";
//        String email = "";
//        String func = "";
//        int state = -1; //isActiveUser : -1 == null / 0 == false / 1 == true
//        int[] profiles = {1}; // profile Id
//        //Result
//        assertFalse(ivan.hasThisData(name, email, func, state, profiles));
//    }
//
//    @Test
//    void hasThisDataWithTwoProfilesFail() {
//        //Input
//        UserProfile pro = new UserProfile("ddd", "pro");
//        UserProfile pre = new UserProfile("aaa", "pre");
//        SystemUser ivan = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "123456", "123456", "img_123", pro);
//        ivan.assignProfileToUser(pre);
//        String name = "";
//        String email = "";
//        String func = "";
//        int state = -1; //isActiveUser : -1 == null / 0 == false / 1 == true
//        int[] profiles = {0, 2}; // profile Id
//        //Result
//        assertFalse(ivan.hasThisData(name, email, func, state, profiles));
//    }
//
//    @Test
//    void hasThisDataWithTwoParametersFail() {
//        //Input
//        UserProfile pro = new UserProfile("ddd", "pro");
//        SystemUser ivan = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "123456", "123456", "img_123", pro);
//        String name = "ivan";
//        String email = "axxx";
//        String func = "";
//        int state = -1; //isActiveUser : -1 == null / 0 == false / 1 == true
//        int[] profiles = {}; // profile Id
//        //Result
//        assertFalse(ivan.hasThisData(name, email, func, state, profiles));
//    }
//
//    @Test // Exmeplo corrigido
//    void hasThisDataWithThreeParametersFail() {
//        //Input
//        Company company = new Company();
//        UserProfile visitor = company.getUserProfileStore().getProfile("visitor");
//        SystemUser ivan = new SystemUserStore().createSystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt",
//                "tester", "12345", "12345", "img_123", visitor);
//
//        String name = "ivan";
//        String email = "xxxx";
//        String func = "aest";
//        int state = -1; //isActiveUser : -1 == null / 0 == false / 1 == true
//        int[] profiles = {}; // profile Id
//        //Result
//        assertFalse(ivan.hasThisData(name, email, func, state, profiles));
//    }
//
//    @Test
//    void hasThisDataWithFourParametersFail() {
//        //Input
//        UserProfile pro = new UserProfile("ddd", "pro");
//        SystemUser ivan = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "123456", "123456", "img_123", pro);
//        String name = "ivan";
//        String email = "xxxx";
//        String func = "test";
//        int state = 1; //isActiveUser : -1 == null / 0 == false / 1 == true
//        int[] profiles = {}; // profile Id
//        //Result
//        assertFalse(ivan.hasThisData(name, email, func, state, profiles));
//    }
//
//    @Test
//    public void activationUser() {
//
//        //Test to activate the user
//        //Arrange
//        UserProfile tes = new UserProfile("ddd", "pro");
//        SystemUser ana = new SystemUser("Ana", "1211748@isep.ipp.pt", "User_12", "png_234", "HELLO", "HELLO", tes);
//        //Act
//        ana.setActivateUser();
//        //Assert
//        assertTrue(ana.getUserActivated());
//    }
//
//
}