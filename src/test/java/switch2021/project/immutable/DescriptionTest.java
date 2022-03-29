package switch2021.project.immutable;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.model.Typology.Typology;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class DescriptionTest {

    @Test
    public void shouldCreateAValidDescription() {
        //Arrange
        Description description = new Description("Test");
        //Assert
        assertEquals("Test", description.getText());
    }

    @Test
    public void shouldCreateAValidDescriptionLengthLimit() {
        //Arrange
        Description description = new Description("T");
        //Assert
        assertEquals("T", description.getText());
    }

    @Test
    public void shouldThrowException_becauseDescriptionIsNull() {
        assertThrows(NullPointerException.class, () -> {
            new Description(null);
        });
    }

    @Test
    public void shouldThrowException_becauseDescriptionIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Description("");
        });
    }

    @Test
    public void shouldThrowException_becauseDescriptionHigherThan() {
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            char[] data = new char[1000000];
            Arrays.fill(data, 'a');
            String str = new String(data);
            //Assert
            new Description(str);
        });
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTestEqual() {
        //Arrange
        Description description = new Description("Test");
        Description description2 = new Description("Test");
        //Assert
        assertTrue(description.equals(description2));
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTestEqualTrue() {
        //Arrange
        Description description = new Description("Test");
        Description description2 = description;
        //Assert
        assertTrue(description.equals(description2));
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTestNotEqual() {
        //Arrange
        Description description = new Description("Test1");
        Description description3 = new Description("Test");
        //Assert
        assertFalse(description.equals(description3));
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTestNull() {
        // Arrange
        Description description = new Description("Test");
        Description description3 = null;
        //Assert
        assertFalse(description.equals(description3));
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTestClass() {
        // Arrange
        Description description = new Description("Test");
        Typology test = new Typology("test");
        //Assert
        assertFalse(description.equals(test));
    }

    @Test
    public void hashCodeTestSuccess() {
        // Arrange
        Description description = new Description("Test");
        Description description2 = new Description("Test");
        //Assert
        assertEquals(description.hashCode(),description2.hashCode());
    }

    @Test
    public void hashCodeTestFail() {
        // Arrange
        Description description = new Description("Test");
        Typology description2 = new Typology("Test");
        //Assert
        assertNotEquals(description.hashCode(),description2.hashCode());
    }
}