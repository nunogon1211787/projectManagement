package switch2021.project.Immutables;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.model.Typology;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class DescriptionTest {

    @Test
    public void shouldCreateAValidDescription() {
        //Arrange
        Description description = new Description("Teste");
        //Assert
        assertEquals("Teste", description.getText());
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
    public void testSetter() {
        Description description = new Description("test");
        description.setText("modified_test");

        assertEquals("modified_test", description.getText());
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTests() {
        // Arrange
        Description description = new Description("Teste");
        Description description2 = new Description("Teste");
        Description description3 = null;
        Typology test = new Typology("test");
        // Act
        assertEquals(description,description2);
        assertNotEquals(description, description3);
        assertNotEquals(description, test);
    }
}