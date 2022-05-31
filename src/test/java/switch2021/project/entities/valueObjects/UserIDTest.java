package switch2021.project.entities.valueObjects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.entities.valueObjects.vos.Description;
import switch2021.project.entities.valueObjects.vos.Email;
import switch2021.project.entities.valueObjects.vos.Name;
import switch2021.project.entities.valueObjects.vos.UserID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class UserIDTest {

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTests() {
        // Arrange
        UserID pwd = new UserID(new Email("oliveira@beaver.com"));
        UserID pwd1 = new UserID(new Email("oliveira@beaver.com"));
        UserID pwd2 = null;
        Description test = new Description("test");
        // Act
        assertEquals(pwd,pwd1);
        assertNotEquals(pwd, pwd2);
        assertNotEquals(pwd, test);
        assertEquals(pwd,pwd);
    }

    @Test
    @DisplayName("Test hashcode conditions for coverage purposes")
    void hashCodeTest_Success(){
        //Arrange
        UserID pwd = new UserID(new Email("oliveira@beaver.com"));
        UserID pwd1 = new UserID(new Email("oliveira@beaver.com"));
        //Act and Assert
        assertEquals(pwd.hashCode(),pwd1.hashCode());
    }

    @Test
    @DisplayName("Test hashcode conditions for coverage purposes")
    void hashCodeTest_Fail(){
        //Arrange
        UserID pwd = new UserID(new Email("oliveira@beaver.com"));
        Name pwd1 = new Name("Test");
        //Act and Assert
        assertNotEquals(pwd.hashCode(),pwd1.hashCode());
    }
}
