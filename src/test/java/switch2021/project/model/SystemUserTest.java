package switch2021.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SystemUserTest {
    @Test
    public void registerSystemUser() {
        //Input
        Profile pro = new Profile("ddd","pro");
        SystemUser ivan = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "123456", pro);
        ivan.activateUser();
        //Expected
        Profile tes = new Profile("ddd","pro");
        SystemUser expected = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "123456", tes);
        expected.activateUser();
        //Results
        assertEquals(ivan, expected);
    }

    @Test
    public void registerSystemUserWithPhoto() {
        //Input
        Profile pro = new Profile("ddd","pro");
        SystemUser ivan1 = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "img_123456", "123456",pro);
        ivan1.activateUser();
        //Expected
        Profile tes = new Profile("ddd","pro");
        SystemUser expexted2 = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "img_123456", "123456",tes);
        expexted2.activateUser();
        //Result
        assertEquals(ivan1, expexted2);
    }

    @Test
    public void verifyEmailTest() {

        //Input
        Profile tes = new Profile("ddd","pro");
        SystemUser ivan = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "img_123456", "123456",tes);
        String emailCheck = "xxxx";
        //Result
        assertTrue(ivan.isYourEmail(emailCheck));
    }

    @Test
    public void verifyUpdateAndEncryptationOfPassword() {

        //Arrange
        Profile tes = new Profile("ddd","pro");
        SystemUser joana = new SystemUser("Joana", "1211770@isep.ipp.pt", "Aluna_10", "png_123", "HELLO", tes);
        //Act
        //assertTrue(joana.updatePassword("HELLO", "GOODBYE"));
        joana.updatePassword("HELLO", "GOODBYE");
        //Assert
        assertEquals("GOODBYE",joana.getPassword());
    }

    @Test
    public void createSystemUserWithPhoto() {
        //Arrange
        String userName = "abc";
        String email = "def";
        String password = "ghi";
        String function = "jkl";
        String photo = "mno";
        Profile tes = new Profile("ddd","pro");
        SystemUser newUser = new SystemUser(userName, email, function, photo, password, tes);

        String userNameExpected = "abc";
        String emailExpected = "def";
        String passwordExpected = "ÊËÌ";
        String functionExpected = "jkl";
        String photoExpected = "mno";
        Profile pro = new Profile("ddd","pro");
        List<Profile> assignedProfileExpected = new ArrayList<>();
        assignedProfileExpected.add(pro);

        //Act
        String userNameResult = newUser.getUserName();
        String emailResult = newUser.getEmail();
        String passwordResult = newUser.getPassword();
        String functionResult = newUser.getFunction();
        String photoResult = newUser.getPhoto();
        boolean activateUserResult = newUser.isUserActivated();
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
    public void encryptPassword() {
        //Arrange
        String password = "ghi";
        Profile pro = new Profile("mno","pro");
        SystemUser newUser = new SystemUser("abc", "def", "ghi", "jkl", pro);
        //Act
        String result = newUser.encryptPassword(password);
        //Assert
        assertEquals("ÊËÌ", result);
    }

    @Test
    public void desencryptPassword() {
        //Arrange
        String password = "Ä\u0094Å\u0095Æ\u0096";
        Profile pro = new Profile("mno","pro");
        SystemUser newUser = new SystemUser("abc", "def", "ghi", "jkl", pro);
        //Act
        String result = newUser.decryptPassword(password);
        //Assert
        assertEquals("a1b2c3", result);
    }

    @Test
    void hasThisDataTest() {
        //Input
        Profile tes = new Profile("ddd","pro");
        SystemUser joana = new SystemUser("Joana", "1211770@isep.ipp.pt", "Aluna_10", "png_123", "HELLO", tes);
        String name = "";
        String email = "";
        String func = "";
        int isActive = -1;
        int[] profiles = {};
        //Result
        assertTrue(joana.hasThisData(name, email, func, isActive, profiles));

    }
}