package switch2021.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SystemUserTest {
    @Test
    public void registerSystemUser() {
        //Input
        SystemUser ivan = new SystemUser("Ivan Aguiar", "1211768@isep.ipp.pt", "123456");
        //Expected
        String name = ivan.getUserName();
        String valueName = "Ivan Aguiar";
        String email = ivan.getEmail();
        String valueEmail = "1211768@isep.ipp.pt";
        String password = ivan.getPassword();
        String valuePassword = "123456";
        boolean activateUser = ivan.activateUser();
        //Results
        assertEquals(valueName, name);
        assertEquals(email, valueEmail);
        assertEquals(password, valuePassword);
        assertTrue(activateUser);
    }

    @Test
    public void registerSystemUserWithPhoto() {
        //Input
        SystemUser ivan = new SystemUser("Ivan Aguiar", "1211768@isep.ipp.pt", "tester", "img_123456", "123456");
        //Expected
        String name = ivan.getUserName();
        String valueName = "Ivan Aguiar";
        String email = ivan.getEmail();
        String valueEmail = "1211768@isep.ipp.pt";
        String function = ivan.getFunction();
        String valueFunction = "tester";
        String photo = ivan.getPhoto();
        String valuePhoto = "img_123456";
        String password = ivan.getPassword();
        String valuePassword = "123456";
        boolean activateUser = ivan.activateUser();
        //Result
        assertEquals(valueName, name);
        assertEquals(email, valueEmail);
        assertEquals(function, valueFunction);
        assertEquals(photo, valuePhoto);
        assertEquals(password, valuePassword);
        assertTrue(activateUser);
    }

    @Test
    public void verifyEmailTest() {

        //Input
        SystemUser ivan = new SystemUser("Ivan Aguiar", "1211768@isep.ipp.pt", "tester", "img_123456", "123456");
        String emailCheck = "1211768";
        //Result
        assertTrue(ivan.isYourEmail(emailCheck));

    }

    @Test
    public void verifyUpdatePassword() {

        //Input
        SystemUser joana = new SystemUser("Joana", "1211770@isep.ipp.pt", "Aluna_10", "png_123", "HELLO");
        //RESULT
        assertTrue(joana.updatePassword("HELLO", "GOODBYE"));
        assertEquals("GOODBYE", joana.getPassword());
    }


    @Test
    void hasThisDataTest() {
        //Input
        SystemUser joana = new SystemUser("Joana", "1211770@isep.ipp.pt", "Aluna_10", "png_123", "HELLO");
        String name = "";
        String email = "";
        String func = "";
        int isActive = -1;
        int[] profiles = {};
        //Result
        assertTrue(joana.hasThisData(name, email, func, isActive, profiles));

    }
}