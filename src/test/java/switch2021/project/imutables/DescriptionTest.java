package switch2021.project.imutables;

import org.junit.jupiter.api.Test;
import switch2021.project.Imutables.Description;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class DescriptionTest {

    @Test
    public void shouldCreateAValidDescription() {
        //Arrange
        Description description = new Description("Teste");
        //Assert
        assertEquals("Teste", description.getDescription_field());
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
        description.setDescription_field("modified_test");

        assertEquals("modified_test", description.getDescription_field());
    }
}