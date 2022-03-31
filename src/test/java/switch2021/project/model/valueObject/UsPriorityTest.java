package switch2021.project.model.valueObject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UsPriorityTest {

    @Test
    @DisplayName("Validate that priority is correct - limit bound")
    public void usPrioritySucessLimit1() {
        //Arrange
        int priority = 0;
        //Act
        UsPriority usPriority = new UsPriority(priority);
        //Assert
        assertEquals(priority, usPriority.getUsPriority());
    }

    @Test
    @DisplayName("Validate that priority is correct - limit bound")
    public void usPrioritySuccessLimit2() {
        //Arrange
        int priority2 = 5;
        //Act
        UsPriority usPriority2 = new UsPriority(priority2);
        //Assert
        assertEquals(priority2, usPriority2.getUsPriority());
    }

    @Test
    @DisplayName("Validate that priority is correct - other values")
    public void usPrioritySucessInBound() {
        //Arrange
        int priority = 3;
        //Act
        UsPriority usPriority = new UsPriority(priority);
        //Assert
        assertEquals(priority, usPriority.getUsPriority());
    }

    @Test
    @DisplayName("Validate that priority is correct - fail")
    public void usPriorityFailOutBoundNegative() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            int priority = -1;
            //Act
            new UsPriority(priority);
        });
    }

    @Test
    @DisplayName("Validate that priority is correct - fail")
    public void usPriorityFailOutBoundMoreThanFive() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            int priority2 = 6;
            //Act
            new UsPriority(priority2);
        });
    }
}
