package switch2021.project.model.valueObject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.model.Typology.Typology;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SystemUserIDTest {

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTests() {
        // Arrange
        SystemUserID pwd = new SystemUserID(new Email("oliveira@beaver.com"));
        SystemUserID pwd1 = new SystemUserID(new Email("oliveira@beaver.com"));
        SystemUserID pwd2 = null;
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
        SystemUserID pwd = new SystemUserID(new Email("oliveira@beaver.com"));
        SystemUserID pwd1 = new SystemUserID(new Email("oliveira@beaver.com"));
        //Act and Assert
        assertEquals(pwd.hashCode(),pwd1.hashCode());
    }

    @Test
    @DisplayName("Test hashcode conditions for coverage purposes")
    void hashCodeTest_Fail(){
        //Arrange
        SystemUserID pwd = new SystemUserID(new Email("oliveira@beaver.com"));
        Typology pwd1 = new Typology("Test");
        //Act and Assert
        assertNotEquals(pwd.hashCode(),pwd1.hashCode());
    }
}
