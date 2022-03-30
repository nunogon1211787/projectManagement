package switch2021.project.valueObject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UsPriorityTest {

    @Test
    @DisplayName("Validate that priority is correct - limit bound")
    public void usPrioritySucess() {
        //Arrange
        int priority = 0;
        int priority2 = 5;
        //Act
        UsPriority usPriority = new UsPriority(priority);
        UsPriority usPriority2 = new UsPriority(priority2);
        //Assert
        assertEquals(priority, usPriority.getUsPriority());
        assertEquals(priority2, usPriority2.getUsPriority());
        assertNotNull(usPriority);
    }

    @Test
    @DisplayName("Validate that priority is correct - other values")
    public void usPrioritySucess2() {
        //Arrange
        int priority = 1;
        int priority2 = 4;
        //Act
        UsPriority usPriority = new UsPriority(priority);
        UsPriority usPriority2 = new UsPriority(priority2);
        //Assert
        assertEquals(priority, usPriority.getUsPriority());
        assertEquals(priority2, usPriority2.getUsPriority());
    }

    @Test
    @DisplayName("Validate that priority is correct - fail")
    public void usPriorityFailOutBound() {
        //Arrange
        int priority = -1;
        int priority2 = 6;
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new UsPriority(priority);
        });
        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> {
            new UsPriority(priority2);
        });
        //Assert
        assertTrue(exception.getMessage().equals("Check priority, cannot be < 0 or superior to 5"));
        assertTrue(exception2.getMessage().equals("Check priority, cannot be < 0 or superior to 5"));
    }

}
