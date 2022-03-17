package switch2021.project.immutable;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NameTest {


    @Test
    public void nameConstructorSuccess() {
        //Arrange
        String atual = "Zé António";
        //Act
        Name expected = new Name(atual);
        //Assert
        assertEquals(expected.getNameF(), atual);
    }

    @Test
    void checkNameEmpty() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String atual = "";
            //Act
            Name expected = new Name(atual);
        });
    }

    @Test
    void checkNameEmpty2() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String atual = "   ";
            //Act
            Name expected = new Name(atual);
        });
    }

    @Test
    void checkIfNumber() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String atual = "Zé 44";
            //Act
            Name expected = new Name(atual);
        });
    }

    @Test
    void checkIfSpecialChar() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String atual = "#*ZÉ*#";
            //Act
            Name expected = new Name(atual);
        });
    }
}