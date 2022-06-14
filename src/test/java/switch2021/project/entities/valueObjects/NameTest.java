package switch2021.project.entities.valueObjects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.entities.aggregates.Typology.Typology;
import switch2021.project.entities.valueObjects.vos.Description;
import switch2021.project.entities.valueObjects.vos.Name;
import switch2021.project.entities.valueObjects.vos.TypologyID;
import static org.junit.jupiter.api.Assertions.*;

class NameTest {


    @Test
    public void nameConstructorSuccess() {
        //Arrange
        String actual = "Zé António";
        //Act
        Name expected = new Name(actual);
        //Assert
        assertEquals(expected.getText(), actual);
    }

    @Test
    void checkNameEmpty() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String atual = "";
            //Act
            new Name(atual);
        });
    }

    @Test
    void checkNameEmpty2() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String atual = "   ";
            //Act
            new Name(atual);
        });
    }

    @Test
    void checkIfNumber() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String atual = "Zé 44";
            //Act
            new Name(atual);
        });
    }

    @Test
    void checkIfSpecialChar() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String atual = "#*ZÉ*#";
            //Act
            new Name(atual);
        });
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTest_1() {
        //Arrange
        Name function = new Name("Test");
        Name function1 = new Name("Test");
        //Act and Assert
        assertEquals(function, function1);
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTest_2() {
        //Arrange
        Name name = new Name("Test");
        Name name1 = new Name("Test");
        //Act and Assert
        assertTrue(name.equals(name1));
    }


    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTest_3() {
        //Arrange
        Name function = new Name("TestTest");
        Name function1 = new Name("Test");
        //Act and Assert
        assertNotEquals(function, function1);
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTest_4() {
        //Arrange
        Name function = new Name("Test");
        Name function1 = null;
        //Act and Assert
        assertNotEquals(function, function1);
    }

    @Test
    @DisplayName("Test hashcode conditions for coverage purposes")
    void hashCodeTest_Success() {
        //Arrange
        Name function = new Name("Test");
        Name function1 = new Name("Test");
        //Act and Assert
        assertEquals(function.hashCode(), function1.hashCode());
    }

    @Test
    @DisplayName("Test hashcode conditions for coverage purposes")
    void hashCodeTest_Fail_1() {
        //Arrange
        Name function = new Name("Test");
        Typology function1 = new Typology(new TypologyID(new Description("Test")));
        //Act and Assert
        assertNotEquals(function.hashCode(), function1.hashCode());
    }
}