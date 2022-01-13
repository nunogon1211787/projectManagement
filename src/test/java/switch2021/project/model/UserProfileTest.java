package switch2021.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserProfileTest {

    @Test
    void getNameTest() {
        //input
        UserProfile test = new UserProfile("admin", "system");
        //Expected
        String expected = "admin";
        String result = test.getName();
        //Result
        assertEquals(expected, result);
    }

    @Test
    void setNameTest() {
        //input
        UserProfile test = new UserProfile("admin", "system");
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
       UserProfile test = new UserProfile("admin", "system");
        int check = 0;
        //Result
        assertTrue(test.isValidId(check));
    }

    //achar uma alternativa para testar o erro
   /* @Test
    public void validateRequestTestOutOFBound () throws IndexOutOfBoundsException{
       Company com = new Company();
        Profile pro = com.arrayProfile.get(7);
        Throwable exception = assertThrows(IndexOutOfBoundsException.class,
                () -> { pro.isValidId(20); });
        Assertions.assertTrue(true);
    }*/


    @Test
    void isValidIdwith5Profile() {
        //input
        UserProfile test1 = new UserProfile("admin", "system");
        UserProfile test2 = new UserProfile("visitor", "system");
        UserProfile test3 = new UserProfile("guest", "system");
        UserProfile test4 = new UserProfile("user", "system");
        UserProfile test5 = new UserProfile("director", "system");
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
        UserProfile test = new UserProfile("admin", "system");
        UserProfile copyTest = new UserProfile(test);
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
        UserProfile test = new UserProfile("admin", "system");
        UserProfile copyTest = new UserProfile(test);
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
        UserProfile test = new UserProfile("admin", "system");
        UserProfile copyTest = new UserProfile(test);
        //result
        assertEquals(test, copyTest);
    }

    @Test
    void isvalinameteste(){
        UserProfile teste = new UserProfile("admin", "system");
    }
}