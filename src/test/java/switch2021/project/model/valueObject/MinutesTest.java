package switch2021.project.model.valueObject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MinutesTest {

    @Test
    @DisplayName("create effort minutes with success - with values")
    public void MinutesWithSuccess() {
        //Arrange
        int effortMinutes = 10;
        //Act
        Minutes expected = new Minutes(effortMinutes);
        //Assert
        assertEquals(expected.getEffortMinutes(), effortMinutes);
    }

    @Test
    @DisplayName("create effort minutes with success, minutes field without value")
    public void MinutesToZero() {
        //Arrange
        int effortMinutes = 0;
        //Act
        Minutes expected = new Minutes(effortMinutes);
        //Assert
        assertEquals(expected.getEffortMinutes(), effortMinutes);
    }

    @Test
    @DisplayName("create effort minutes - field on limit")
    public void MinutesOnLimit() {
        //Arrange
        int effortMinutes = 59;
        //Act
        Minutes expected = new Minutes(effortMinutes);
        //Assert
        assertEquals(expected.getEffortMinutes(), effortMinutes);
    }

    @Test
    @DisplayName("create effort minutes - field with negative value")
    public void minuteNegativeValue() {
        //Arrange
        int effortMinutes = -3;
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Minutes(effortMinutes);
        });
        //Assert
        assertTrue(exception.getMessage().equals("Not valid work time values." + " Minute interval: [" + 0 + " - " + 60 + "]"));
    }

    @Test
    @DisplayName("create effort minutes - More than 60 minutes")
    public void minuteMoreThan60Minutes() {
        //Arrange
        int effortMinutes = 60;
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Minutes(effortMinutes);
        });
        //Assert
        assertTrue(exception.getMessage().equals("Not valid work time values." + " Minute interval: [" + 0 + " - " + 60 + "]"));
    }
}


