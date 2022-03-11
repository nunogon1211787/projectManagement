package switch2021.project.imutables;

import org.junit.jupiter.api.Test;
import switch2021.project.Imutables.Description;

import static org.junit.jupiter.api.Assertions.*;

class DescriptionTest {

    @Test
    public void shouldCreateAValidDescription() {
        //Arrange
        Description description = new Description("Teste");
        //Assert
        assertEquals("Teste", description.getDescription());
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
            StringBuilder test_string = new StringBuilder();
            test_string.setLength(1001);
            //Assert
            new Description(test_string.toString());
        });
    }

}