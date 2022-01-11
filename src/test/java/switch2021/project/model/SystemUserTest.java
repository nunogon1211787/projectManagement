package switch2021.project.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SystemUserTest {
    @Test
    public void registerSystemUser() {
        //Input
        Profile pro = new Profile("ddd", "pro");
        SystemUser ivan = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "123456", pro);
        ivan.setActivateUser();
        //Expected
        Profile tes = new Profile("ddd", "pro");
        SystemUser expected = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "123456", tes);
        expected.setActivateUser();
        //Results
        assertEquals(ivan, expected);
    }

    @Test
    public void registerSystemUserWithPhoto() {
        //Input
        Profile pro = new Profile("ddd", "pro");
        SystemUser ivan1 = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "img_123456", "123456", pro);
        ivan1.setActivateUser();
        //Expected
        Profile tes = new Profile("ddd", "pro");
        SystemUser expected2 = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "img_123456", "123456", tes);
        expected2.setActivateUser();
        //Result
        assertEquals(ivan1, expected2);
    }

    @Test
    public void verifyEmail() {

        //Arrange
        Profile tes = new Profile("ddd", "pro");
        SystemUser ivan = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "img_123456", "123456", tes);
        //Act
        String emailCheck = "xxxx";
        //Assert
        assertTrue(ivan.isYourEmail(emailCheck));
    }

    @Test
    public void verifyEmailFail() {

        //Arrange
        Profile tes = new Profile("ddd", "pro");
        SystemUser joana = new SystemUser("Joana Silva", "1234@isep.ipp.pt", "Aluna", "123_img", "abcde", tes);
        //Act
        String emailCheck = "4321@isep.ipp.pt";
        //Assert
        assertFalse(joana.isYourEmail(emailCheck));
    }

    @Test
    public void verifyUpdateProfile(){
        //Arrange
        Company com = new Company();
        SystemUser user = new SystemUser("xxx","xxx@isep.ipp.pt","tester","123456", com.getProfile(0));
        //Act
        com.addSystemUser(user);
        // Assert
        assertTrue(user.updateProfile(com.getProfile(0), com.getProfile(1)));
    }

    @Test
    public void verifyUpdateAndEncryptationOfPassword() {

        //Test to verify if the oldpassword is updated by the newpassword, and this last one is
        //stored in system user with the encryptation method.

        //Arrange
        Profile tes = new Profile("ddd", "pro");
        SystemUser joana = new SystemUser("Joana", "1211770@isep.ipp.pt", "Aluna_10", "png_123", "HELLO", tes);
        //Act
        joana.updatePassword("HELLO", "GOODBYE");
        //Assert
        assertEquals("GOODBYE", joana.getPassword());
    }

    @Test
    public void verifyOldPassword() {

        //Test to verify if the oldpassword, stored in the system user, is equal or diferent from the
        //password that came from User Interface (UI).
        //Arrange
        Profile tes = new Profile("ddd", "pro");
        SystemUser joana = new SystemUser("Joana", "1211770@isep.ipp.pt", "Aluna_10",
                "png_123", "HELLO_01", tes);
        //Act
        assertFalse(joana.updatePassword("HELLO_02", "GOODBYE"));

    }

    @Test
    public void setPasswordSucess() {

        //Arrange
        Profile tes = new Profile("ddd", "pro");
        SystemUser joana = new SystemUser("Joana", "1211770@isep.ipp.pt", "Aluna_10", "123", tes);
        //Act
        joana.setPassword("123");
        //Assert
        assertEquals(joana.getPassword(), "123");
    }

    @Test
    public void setPasswordFail() {

        //Arrange
        Profile tes = new Profile("ddd", "pro");
        SystemUser joana = new SystemUser("Joana", "1211770@isep.ipp.pt", "Aluna_10", "123", tes);
        //Act
        joana.setPassword("123");
        //Assert
        assertNotEquals(joana.getPassword(), "321");
    }


    @Test
    public void setUserNameSucess() {

        //Arrange
        Profile tes = new Profile("ddd", "pro");
        SystemUser joana = new SystemUser("Joana", "1211770@isep.ipp.pt", "Aluna_10", "123", tes);
        //Act
        joana.setUserName("Joana");
        //Assert
        assertEquals(joana.getUserName(), "Joana");
    }

    @Test
    public void setUserNameFail() {

        //Arrange
        Profile tes = new Profile("ddd", "pro");
        SystemUser joana = new SystemUser("Joana", "1211770@isep.ipp.pt", "Aluna_10", "123", tes);
        //Act
        joana.setUserName("Joana");
        //Assert
        assertNotEquals(joana.getUserName(), "Isabel");
    }

    @Test
    public void setFunctionSucess() {

        //Arrange
        Profile tes = new Profile("ddd", "pro");
        SystemUser joana = new SystemUser("Joana", "1211770@isep.ipp.pt", "Aluna_10", "123", tes);
        //Act
        joana.setFunction("Aluna_10");
        //Assert
        assertEquals(joana.getFunction(), "Aluna_10");
    }

    @Test
    public void setFuncionFail() {

        //Arrange
        Profile tes = new Profile("ddd", "pro");
        SystemUser joana = new SystemUser("Joana", "1211770@isep.ipp.pt", "Aluna_10", "123", tes);
        //Act
        joana.setFunction("Aluna_10");
        //Assert
        assertNotEquals(joana.getFunction(), "Project Manager");
    }

    @Test
    public void setPhotoSucess() {

        //Arrange
        Profile tes = new Profile("ddd", "pro");
        SystemUser joana = new SystemUser("Joana", "1211770@isep.ipp.pt", "Aluna_10",
                "img_789", "123", tes);
        //Act
        joana.setPhoto("img_789");
        //Assert
        assertEquals(joana.getPhoto(), "img_789");
    }

    @Test
    public void setPhotoFail() {

        //Arrange
        Profile tes = new Profile("ddd", "pro");
        SystemUser joana = new SystemUser("Joana", "1211770@isep.ipp.pt", "Aluna_10",
                "img_789", "123", tes);
        //Act
        joana.setPhoto("img_789");
        //Assert
        assertNotEquals(joana.getPhoto(), "img_000");
    }

    @Test
    public void createSystemUserWithPhotoSuccess() {
        //Arrange
        String userName = "manueloliveira";
        String email = "manueloliveira@beaver.com";
        String password = "ghi";
        String function = "tester";
        String photo = "photo";
        Profile profileTest = new Profile("Visitor", "System Profile");
        SystemUser newUser = new SystemUser(userName, email, function, photo, password, profileTest);

        String userNameExpected = "manueloliveira";
        String emailExpected = "manueloliveira@beaver.com";
        String passwordExpected = "ÊËÌ";
        String functionExpected = "tester";
        String photoExpected = "photo";
        Profile pro = new Profile("Visitor", "System Profile");
        List<Profile> assignedProfileExpected = new ArrayList<>();
        assignedProfileExpected.add(pro);
        //Act
        String userNameResult = newUser.getUserName();
        String emailResult = newUser.getEmail();
        String passwordResult = newUser.getPassword();
        String functionResult = newUser.getFunction();
        String photoResult = newUser.getPhoto();
        boolean activateUserResult = newUser.getUserActivated();
        List<Profile> assignedProfileResult = newUser.getAssignedProfileList();
        //Assert
        assertEquals(userNameExpected, userNameResult);
        assertEquals(emailExpected, emailResult);
        assertEquals(passwordExpected, passwordResult);
        assertEquals(functionExpected, functionResult);
        assertEquals(photoExpected, photoResult);
        assertFalse(activateUserResult);
        assertEquals(assignedProfileExpected, assignedProfileResult);
    }

    @Test
    public void createSystemUserWithoutPhotoSuccess() {
        //Arrange
        String userName = "manueloliveira";
        String email = "manueloliveira@beaver.com";
        String password = "ghi";
        String function = "tester";
        Profile profileTest = new Profile("Visitor", "System Profile");
        SystemUser newUser = new SystemUser(userName, email, function, password, profileTest);

        String userNameExpected = "manueloliveira";
        String emailExpected = "manueloliveira@beaver.com";
        String passwordExpected = "ÊËÌ";
        String functionExpected = "tester";
        Profile pro = new Profile("Visitor", "System Profile");
        List<Profile> assignedProfileExpected = new ArrayList<>();
        assignedProfileExpected.add(pro);
        //Act
        String userNameResult = newUser.getUserName();
        String emailResult = newUser.getEmail();
        String passwordResult = newUser.getPassword();
        String functionResult = newUser.getFunction();
        boolean activateUserResult = newUser.getUserActivated();
        List<Profile> assignedProfileResult = newUser.getAssignedProfileList();
        //Assert
        assertEquals(userNameExpected, userNameResult);
        assertEquals(emailExpected, emailResult);
        assertEquals(passwordExpected, passwordResult);
        assertEquals(functionExpected, functionResult);
        assertFalse(activateUserResult);
        assertEquals(assignedProfileExpected, assignedProfileResult);
    }

    @Test
    public void encryptPasswordSuccess() {
        //Arrange
        String userName = "manueloliveira";
        String email = "manueloliveira@beaver.com";
        String password = "ghi";
        String function = "tester";
        String photo = "photo";
        Profile profileTest = new Profile("Visitor", "System Profile");
        SystemUser newUser = new SystemUser(userName, email, function, photo, password, profileTest);
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
        String function = "tester";
        String photo = "photo";
        Profile profileTest = new Profile("Visitor", "System Profile");
        SystemUser newUser = new SystemUser(userName, email, function, photo, password, profileTest);
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
        String function = "tester";
        String photo = "photo";
        Profile profileTest = new Profile("Visitor", "System Profile");
        SystemUser newUser = new SystemUser(userName, email, function, photo, password, profileTest);

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
        String function = "tester";
        String photo = "photo";
        Profile profileTest = new Profile("Visitor", "System Profile");
        SystemUser newUser = new SystemUser(userName, email, function, photo, password, profileTest);

        String encryptedPassword = newUser.getPassword();//encryptedPassword = "Ä\u0094Å\u0095Æ\u0096";
        //Act
        String result = newUser.decryptPassword(encryptedPassword);
        //Assert
        assertNotEquals("Ä\\u0094Å\\u0095Æ\\u0096", result);
    }

    @Test
    public void createSystemUserWithPhotoFailUserNameIsEmpty() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String userName = "";
            String email = "manueloliveira@beaver.com";
            String password = "ghi";
            String function = "tester";
            String photo = "photo";
            Company company = new Company();
            Profile profile = company.getArrayProfile().get(0);
            SystemUser newUser = new SystemUser(userName, email, function, photo, password, profile);
        });
    }

    @Test
    public void createSystemUserWithPhotoFailUserNameIsShort() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String userName = "M";
            String email = "manueloliveira@beaver.com";
            String password = "ghi";
            String function = "tester";
            String photo = "photo";
            Company company = new Company();
            Profile profile = company.getArrayProfile().get(0);
            SystemUser newUser = new SystemUser(userName, email, function, photo, password, profile);
        });
    }

    @Test
    public void createSystemUserWithPhotoFailEmailIsEmpty() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String userName = "manueloliveira";
            String email = "";
            String password = "ghi";
            String function = "tester";
            String photo = "photo";
            Company company = new Company();
            Profile profile = company.getArrayProfile().get(0);
            SystemUser newUser = new SystemUser(userName, email, function, photo, password, profile);
        });
    }

    @Test
    public void createSystemUserWithPhotoFailEmailIsShort() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String userName = "manueloliveira";
            String email = "m";
            String password = "ghi";
            String function = "tester";
            String photo = "photo";
            Company company = new Company();
            Profile profile = company.getArrayProfile().get(0);
            SystemUser newUser = new SystemUser(userName, email, function, photo, password, profile);
        });
    }

    /*@Test
    void hasThisDataTest() {
        //Input
        Profile tes = new Profile("ddd", "pro");
        SystemUser joana = new SystemUser("Joana", "1211770@isep.ipp.pt", "Aluna_10", "png_123", "HELLO", tes);
        String name = "";
        String email = "";
        String func = "";
        int isActive = -1;
        int[] profiles = {};
        //Result
        assertTrue(joana.hasThisData(name, email, func, isActive, profiles));

    }
*/
    @Test
    void hasThisDataWithAll() {
        //Input
        Profile pro = new Profile("ddd", "pro");
        SystemUser ivan = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "123456", pro);
        String name = "ivan";
        String email = "xxxx";
        String func = "test";
        int state = 0; // -1 == null / 0 == false / 1 == true
        int[] profiles = {}; // profileId
        //Result
        assertTrue(ivan.hasThisData(name, email, func, state, profiles));
    }

    @Test
    void hasThisDataWithoutAll() {
        //Input
        Profile pro = new Profile("ddd", "pro");
        SystemUser ivan = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "123456", pro);
        String name = "";
        String email = "";
        String func = "";
        int state = -1; //-1 == null / 0 == false / 1 == true
        int[] profiles = {}; // profile Id
        //Result
        assertFalse(ivan.hasThisData(name, email, func, state, profiles));
    }

    @Test
    void hasThisDataWithNameSuccess() {
        //Input
        Profile pro = new Profile("ddd", "pro");
        SystemUser ivan = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "123456", pro);
        String name = "ivan";
        String email = "";
        String func = "";
        int state = -1; //-1 == null / 0 == false / 1 == true
        int[] profiles = {}; // profile Id
        //Result
        assertTrue(ivan.hasThisData(name, email, func, state, profiles));
    }

    @Test
    void hasThisDataWithEmailSuccess() {
        //Input
        Profile pro = new Profile("ddd", "pro");
        SystemUser ivan = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "123456", pro);
        String name = "";
        String email = "xxxx";
        String func = "";
        int state = -1; //-1 == null / 0 == false / 1 == true
        int[] profiles = {}; // profile Id
        //Result
        assertTrue(ivan.hasThisData(name, email, func, state, profiles));
    }

    @Test
    void hasThisDataWithFunctionSuccess() {
        //Input
        Profile pro = new Profile("ddd", "pro");
        SystemUser ivan = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "123456", pro);
        String name = "";
        String email = "";
        String func = "test";
        int state = -1; //-1 == null / 0 == false / 1 == true
        int[] profiles = {}; // profile Id
        //Result
        assertTrue(ivan.hasThisData(name, email, func, state, profiles));
    }

    @Test
    void hasThisDataWithStateSuccess() {
        //Input
        Profile pro = new Profile("ddd", "pro");
        SystemUser ivan = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "123456", pro);
        String name = "";
        String email = "";
        String func = "";
        int state = 0; //isActiveUser : -1 == null / 0 == false / 1 == true
        int[] profiles = {}; // profile Id
        //Result
        assertTrue(ivan.hasThisData(name, email, func, state, profiles));
    }

    /*@Test
    void hasThisDataWithProfilesSuccess() {
        //Input
        Profile pro = new Profile("ddd", "pro");
        SystemUser ivan = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "123456", pro);
        String name = "";
        String email = "";
        String func = "";
        int state = -1; //isActiveUser : -1 == null / 0 == false / 1 == true
        int[] profiles = {0}; // profile Id
        //Result
        assertTrue(ivan.hasThisData(name, email, func, state, profiles));
    }*/

    /*@Test
    void hasThisDataWithTwoProfilesSuccess() {
        //Input
        Profile pro = new Profile("ddd", "pro");
        Profile pre = new Profile("aaa", "pre");
        SystemUser ivan = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "123456", pro);
        ivan.addProfileToList(pre);
        String name = "";
        String email = "";
        String func = "";
        int state = -1; //isActiveUser : -1 == null / 0 == false / 1 == true
        int[] profiles = {1}; // profile Id
        //Result
        assertTrue(ivan.hasThisData(name, email, func, state, profiles));
    }*/

    @Test
    void hasThisDataWithTwoParametersSuccess() {
        //Input
        Profile pro = new Profile("ddd", "pro");
        SystemUser ivan = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "123456", pro);
        String name = "ivan";
        String email = "xxxx";
        String func = "";
        int state = -1; //isActiveUser : -1 == null / 0 == false / 1 == true
        int[] profiles = {}; // profile Id
        //Result
        assertTrue(ivan.hasThisData(name, email, func, state, profiles));
    }

    @Test
    void hasThisDataWithThreeParametersSuccess() {
        //Input
        Profile pro = new Profile("ddd", "pro");
        SystemUser ivan = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "123456", pro);
        String name = "ivan";
        String email = "xxxx";
        String func = "test";
        int state = -1; //isActiveUser : -1 == null / 0 == false / 1 == true
        int[] profiles = {}; // profile Id
        //Result
        assertTrue(ivan.hasThisData(name, email, func, state, profiles));
    }

    @Test
    void hasThisDataWithFourParametersSuccess() {
        //Input
        Profile pro = new Profile("ddd", "pro");
        SystemUser ivan = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "123456", pro);
        String name = "ivan";
        String email = "xxxx";
        String func = "test";
        int state = 0; //isActiveUser : -1 == null / 0 == false / 1 == true
        int[] profiles = {}; // profile Id
        //Result
        assertTrue(ivan.hasThisData(name, email, func, state, profiles));
    }

    /**
     * FAIL TESTS
     */

    @Test
    void hasThisDataWithAllFail() {
        //Input
        Profile pro = new Profile("ddd", "pro");
        SystemUser ivan = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "123456", pro);
        String name = "avan";
        String email = "xxxx";
        String func = "test";
        int state = 0; // -1 == null / 0 == false / 1 == true
        int[] profiles = {0}; // profileId
        //Result
        assertFalse(ivan.hasThisData(name, email, func, state, profiles));
    }

    @Test
    void hasThisDataWithNameFail() {
        //Input
        Profile pro = new Profile("ddd", "pro");
        SystemUser ivan = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "123456", pro);
        String name = "avan";
        String email = "";
        String func = "";
        int state = -1; //-1 == null / 0 == false / 1 == true
        int[] profiles = {}; // profile Id
        //Result
        assertFalse(ivan.hasThisData(name, email, func, state, profiles));
    }

    @Test
    void hasThisDataWithEmailFail() {
        //Input
        Profile pro = new Profile("ddd", "pro");
        SystemUser ivan = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "123456", pro);
        String name = "";
        String email = "yxxx";
        String func = "";
        int state = -1; //-1 == null / 0 == false / 1 == true
        int[] profiles = {}; // profile Id
        //Result
        assertFalse(ivan.hasThisData(name, email, func, state, profiles));
    }

    @Test
    void hasThisDataWithFunctionFail() {
        //Input
        Profile pro = new Profile("ddd", "pro");
        SystemUser ivan = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "123456", pro);
        String name = "";
        String email = "";
        String func = "tesq";
        int state = -1; //-1 == null / 0 == false / 1 == true
        int[] profiles = {}; // profile Id
        //Result
        assertFalse(ivan.hasThisData(name, email, func, state, profiles));
    }

    @Test
    void hasThisDataWithStateFail() {
        //Input
        Profile pro = new Profile("ddd", "pro");
        SystemUser ivan = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "123456", pro);
        String name = "";
        String email = "";
        String func = "";
        int state = 1; //isActiveUser : -1 == null / 0 == false / 1 == true
        int[] profiles = {}; // profile Id
        //Result
        assertFalse(ivan.hasThisData(name, email, func, state, profiles));
    }

    @Test
    void hasThisDataWithProfilesFail() {
        //Input
        Profile pro = new Profile("ddd", "pro");
        SystemUser ivan = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "123456", pro);
        String name = "";
        String email = "";
        String func = "";
        int state = -1; //isActiveUser : -1 == null / 0 == false / 1 == true
        int[] profiles = {1}; // profile Id
        //Result
        assertFalse(ivan.hasThisData(name, email, func, state, profiles));
    }

    @Test
    void hasThisDataWithTwoProfilesFail() {
        //Input
        Profile pro = new Profile("ddd", "pro");
        Profile pre = new Profile("aaa", "pre");
        SystemUser ivan = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "123456", pro);
        ivan.addProfileToList(pre);
        String name = "";
        String email = "";
        String func = "";
        int state = -1; //isActiveUser : -1 == null / 0 == false / 1 == true
        int[] profiles = {0, 2}; // profile Id
        //Result
        assertFalse(ivan.hasThisData(name, email, func, state, profiles));
    }

    @Test
    void hasThisDataWithTwoParametersFail() {
        //Input
        Profile pro = new Profile("ddd", "pro");
        SystemUser ivan = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "123456", pro);
        String name = "ivan";
        String email = "axxx";
        String func = "";
        int state = -1; //isActiveUser : -1 == null / 0 == false / 1 == true
        int[] profiles = {}; // profile Id
        //Result
        assertFalse(ivan.hasThisData(name, email, func, state, profiles));
    }

    @Test
    void hasThisDataWithThreeParametersFail() {
        //Input
        Profile pro = new Profile("ddd", "pro");
        SystemUser ivan = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "123456", pro);
        String name = "ivan";
        String email = "xxxx";
        String func = "aest";
        int state = -1; //isActiveUser : -1 == null / 0 == false / 1 == true
        int[] profiles = {}; // profile Id
        //Result
        assertFalse(ivan.hasThisData(name, email, func, state, profiles));
    }

    @Test
    void hasThisDataWithFourParametersFail() {
        //Input
        Profile pro = new Profile("ddd", "pro");
        SystemUser ivan = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "123456", pro);
        String name = "ivan";
        String email = "xxxx";
        String func = "test";
        int state = 1; //isActiveUser : -1 == null / 0 == false / 1 == true
        int[] profiles = {}; // profile Id
        //Result
        assertFalse(ivan.hasThisData(name, email, func, state, profiles));
    }

    @Test
    public void activationUser() {

        //Test to activate the user
        //Arrange
        Profile tes = new Profile("ddd", "pro");
        SystemUser ana = new SystemUser("Ana", "1211748@isep.ipp.pt", "User_12", "png_234", "HELLO", tes);
        //Act
        ana.setActivateUser();
        //Assert
        assertTrue(ana.getUserActivated());
    }


}