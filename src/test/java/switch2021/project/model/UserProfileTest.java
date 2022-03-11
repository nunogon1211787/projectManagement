package switch2021.project.model;

import org.junit.jupiter.api.Test;
import switch2021.project.stores.UserProfileStore;

import static org.junit.jupiter.api.Assertions.*;

class UserProfileTest {
    Company company;

    @Test
    void getNameTest() {
        //input
        UserProfile test = new UserProfile("admin");
        //Expected
        String expected = "admin";
        String result = test.getUserProfileName();
        //Result
        assertEquals(expected, result);
    }

    @Test
    void setNameTest() {
        //Arrange
        UserProfile test = new UserProfile("admin");
        String newName = "visitor";
        test.setUserProfileName(newName);
        //Act
        String expected = "visitor";
        String result = test.getUserProfileName();
        //Assert
        assertEquals(expected, result);
    }

/*    @Test
    void isValidIdWith5Profile() {
        //Arrange
        company = new Company();
        UserProfile test1 = new UserProfile("SET");
        UserProfile test2 = new UserProfile("DET");

        //Act
        company.getUserProfileStore().saveUserProfile(test2);
        company.getUserProfileStore().saveUserProfile(test1);

        //Assert
        assertEquals(4, test1.getIdProfile());
        assertEquals(5, test2.getIdProfile());
    }*/

    @Test
    void overrideTest() {

        UserProfile test1 = new UserProfile("admin");
        UserProfile test2 = new UserProfile("user");
        UserProfile test3 = new UserProfile("admin");
        UserProfile test4 = new UserProfile("null");
        boolean result = test4.equals(null);

        assertNotEquals(test1, test2);
        assertNotSame(test3, test2);
        assertEquals(test3.getClass(), test2.getClass());
        assertNotEquals(null, test1);
        assertFalse(result);
        assertNotEquals(test1.hashCode(), test2.hashCode());
        assertEquals(test1.hashCode(), test3.hashCode());
        assertEquals(test1.getUserProfileName(), test3.getUserProfileName());
        assertNotEquals(test1.getUserProfileName(), test2.getUserProfileName());

    }

}

