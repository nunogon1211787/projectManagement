package switch2021.project.Immutables;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.model.Typology;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class FunctionTest {

    @Test
    public void functionConstructorSuccess() {
        //Arrange
        String atual = "Developer";
        //Act
        Function function = new Function(atual);
        //Assert
        assertEquals(function.getText(), atual);
    }

    @Test
    public void functionConstructorSuccessUpLimit() {
        //Arrange
        String atual = "DeveloperDeveloperOO";
        //Act
        Function function = new Function(atual);
        //Assert
        assertEquals(function.getText(), atual);
    }

    @Test
    public void checkFunctionEmpty() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String atual = "";
            //Act
            Function expected = new Function(atual);
        });
    }

    @Test
    public void checkFunctionEmpty1() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String atual = null;
            //Act
            Function expected = new Function(atual);
        });
    }

    @Test
    public void checkNameEmpty2() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String atual = "   ";
            //Act
            Function expected = new Function(atual);
        });
    }

    @Test
    public void checkFunctionMaxChars() {
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            char[] data = new char[21];
            Arrays.fill(data, 'a');
            String str = new String(data);
            //Assert
            new Function(str);
        });
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTests() {
        // Arrange
        Function function = new Function("Teste");
        Function function1 = new Function("Teste");
        Function function2 = null;
        Description test = new Description("test");
        // Act
        assertEquals(function,function1);
        assertNotEquals(function, function2);
        assertNotEquals(function, test);
        assertEquals(function,function);
    }
}