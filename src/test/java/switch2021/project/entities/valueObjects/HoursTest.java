package switch2021.project.entities.valueObjects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.entities.valueObjects.vos.Hours;

import static org.junit.jupiter.api.Assertions.*;

public class HoursTest {

    @Test
    @DisplayName("create effort hours with success - with values")
    public void hoursWithSuccess() {
        //Arrange
        int effortHours = 10;
        //Act
        Hours expected = new Hours(effortHours);
        //Assert
        assertEquals(expected.getEffortHours(), effortHours);
    }

    @Test
    @DisplayName("create effort hours with success, hours field without value")
    public void hoursToZero()  {
        //Arrange
        int effortHours = 0;
        //Act
        Hours expected = new Hours(effortHours);
        //Assert
        assertEquals(expected.getEffortHours(), effortHours);
    }

    @Test
    @DisplayName("create effort hours - field on limit")
    public void hoursOnLimit()  {
        //Arrange
        int effortHours = 23;
        //Act
        Hours expected = new Hours(effortHours);
        //Assert
        assertEquals(expected.getEffortHours(), effortHours);
    }

    @Test
    @DisplayName("create effort hours - field with negative value")
    public void hourNegativeValue()  {
        //Arrange
        int effortHours = -3;
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->{
        new Hours(effortHours);
        });
        //Assert
        assertTrue(exception.getMessage().equals("Not valid work time values." + "Hour interval: [" + 0 + " - " + 23 + "]"));
    }

    @Test
    @DisplayName("create effort hours - More than 24 hours")
    public void hourMoreThan24Hours()  {
        //Arrange
        int effortHours = 24;
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->{
            new Hours(effortHours);
        });
        //Assert
        assertTrue(exception.getMessage().equals("Not valid work time values." + "Hour interval: [" + 0 + " - " + 23 + "]"));
    }
}
