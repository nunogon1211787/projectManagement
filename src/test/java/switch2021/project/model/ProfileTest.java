package switch2021.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfileTest {

    @Test
    void getName() {
        //input
        Profile test = new Profile("admin", "system");
        //Expected
        String expected = "admin";
        String result = test.getName();
        //Result
        assertEquals(expected, result);
    }

    @Test
    void setName() {
        //input
        Profile test = new Profile("admin", "system");
        String newName = "visitor";
        test.setName(newName);
        //Expected
        String expected = "visitor";
        String result = test.getName();
        //Result
        assertEquals(expected, result);
    }

    @Test
    void isValidId() {
        //input
        Profile test = new Profile("admin", "system");
        int check = 1;
        //Result
        assertTrue(test.isValidId(check));
    }

    @Test
    void copyConstructor() {
        //input
        Profile test = new Profile("admin", "system");
        Profile copyTest = new Profile(test);
        String newName = "visitor";
        copyTest.setName(newName);
        //Expected
        String expected = "visitor";
        String result = copyTest.getName();
        String nameOriginal = test.getName();
        //Result
        assertEquals(expected, result);
    }
}