package switch2021.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfileTest {

    @Test
    void getNameTest() {
        //input
        Profile test = new Profile("admin", "system");
        //Expected
        String expected = "admin";
        String result = test.getName();
        //Result
        assertEquals(expected, result);
    }

    @Test
    void setNameTest() {
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
    void isValidIdwith1Profile() {
        //input
        Profile test = new Profile("admin", "system");
        int check = 0;
        //Result
        assertTrue(test.isValidId(check));
    }

    @Test
    void isValidIdwith5Profile() {
        //input
        Profile test1 = new Profile("admin", "system");
        Profile test2 = new Profile("visitor", "system");
        Profile test3 = new Profile("guest", "system");
        Profile test4 = new Profile("user", "system");
        Profile test5 = new Profile("director", "system");
        int check1 = 0;
        int check2 = 1;
        int check3 = 2;
        int check4 = 3;
        int check5 = 4;
        //Result
        assertTrue(test1.isValidId(check1));
        assertTrue(test2.isValidId(check2));
        assertTrue(test3.isValidId(check3));
        assertTrue(test4.isValidId(check4));
        assertTrue(test5.isValidId(check5));
    }

    @Test
    void copyConstructorTestName() {
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
        assertEquals("admin", nameOriginal);
    }

    @Test
    void copyConstructorTestType() {
        //input
        Profile test = new Profile("admin", "system");
        Profile copyTest = new Profile(test);
        String newType = "project";
        copyTest.setType(newType);
        //Expected
        String expected = "project";
        String result = copyTest.getType();
        String typeOriginal = test.getType();
        //Result
        assertEquals(expected, result);
        assertEquals("system", typeOriginal);
    }

    @Test
    void copyConstructorTest(){
        //input
        Profile test = new Profile("admin", "system");
        Profile copyTest = new Profile(test);
        //result
        assertEquals(test, copyTest);
    }
}