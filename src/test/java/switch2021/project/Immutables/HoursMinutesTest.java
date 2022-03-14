package switch2021.project.Immutables;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HoursMinutesTest {

    @Test
    @DisplayName("create effort hours and effort minutes with success, both fields with values")
    public void hoursMinutesWithSuccess() {
        //Arrange
        int effortHours = 10;
        int effortMinutes = 10;
        //Act
        HoursMinutes expected = new HoursMinutes(effortHours, effortMinutes);
        //Assert
        assertEquals(expected.getEffortHours(), effortHours);
        assertEquals(expected.getEffortMinutes(), effortMinutes);
    }

    @Test
    @DisplayName("create effort hours and effort minutes with success, minutes field without value")
    public void minutesToZeroAndHoursWithValue() {
        //Arrange
        int effortHours = 10;
        int effortMinutes = 0;
        //Act
        HoursMinutes expected = new HoursMinutes(effortHours, effortMinutes);
        //Assert
        assertEquals(expected.getEffortHours(), effortHours);
        assertEquals(expected.getEffortMinutes(), effortMinutes);
    }

    @Test
    @DisplayName("create effort hours and effort minutes with success, hours field without value")
    public void hoursToZeroAndMinutesWithValue() {
        //Arrange
        int effortHours = 0;
        int effortMinutes = 10;
        //Act
        HoursMinutes expected = new HoursMinutes(effortHours, effortMinutes);
        //Assert
        assertEquals(expected.getEffortHours(), effortHours);
        assertEquals(expected.getEffortMinutes(), effortMinutes);
    }

    @Test
    @DisplayName("create effort hours and effort minutes with success, hours field on limit")
    public void hoursOnLimit() {
        //Arrange
        int effortHours = 23;
        int effortMinutes = 10;
        //Act
        HoursMinutes expected = new HoursMinutes(effortHours, effortMinutes);
        //Assert
        assertEquals(expected.getEffortHours(), effortHours);
        assertEquals(expected.getEffortMinutes(), effortMinutes);
    }

    @Test
    @DisplayName("create effort hours and effort minutes with success, minutes on limit")
    public void MinutesOnLimit() {
        //Arrange
        int effortHours = 3;
        int effortMinutes = 59;
        //Act
        HoursMinutes expected = new HoursMinutes(effortHours, effortMinutes);
        //Assert
        assertEquals(expected.getEffortHours(), effortHours);
        assertEquals(expected.getEffortMinutes(), effortMinutes);
    }


    @Test
    @DisplayName("create effort hours and effort minutes, hours field with negative value")
    public void hourNegativeValue() {
        //Arrange
        int effortHours = -4;
        int effortMinutes = 10;
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->{
                HoursMinutes expected = new HoursMinutes(effortHours, effortMinutes);
        });
        //Assert
        assertTrue(exception.getMessage().equals("Not valid work time values." + " Minute interval: [" + 0 + " - " + 60 + "]" +
                " || Hour interval: [" + 0 + " - " + 23 + "]"));
    }

    @Test
    @DisplayName("create effort hours and effort minutes, minutes field with negative value")
    public void minuteNegativeValue() {
        //Arrange
        int effortHours = 4;
        int effortMinutes = -10;
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->{
            HoursMinutes expected = new HoursMinutes(effortHours, effortMinutes);
        });
        //Assert
        assertTrue(exception.getMessage().equals("Not valid work time values." + " Minute interval: [" + 0 + " - " + 60 + "]" +
                " || Hour interval: [" + 0 + " - " + 23 + "]"));
    }

    @Test
    @DisplayName("create effort hours and effort minutes, hours field with negative value")
    public void MinutesAndHourEqualZero() {
        //Arrange
        int effortHours = 0;
        int effortMinutes = 0;
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->{
            HoursMinutes expected = new HoursMinutes(effortHours, effortMinutes);
        });
        //Assert
        assertTrue(exception.getMessage().equals("Not valid work time values." + " Minute interval: [" + 0 + " - " + 60 + "]" +
                " || Hour interval: [" + 0 + " - " + 23 + "]"));
    }

    @Test
    @DisplayName("create effort hours and effort minutes, hours field above 24hours")
    public void HoursMoreThanADay() {
        //Arrange
        int effortHours = 25;
        int effortMinutes = 10;
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->{
            HoursMinutes expected = new HoursMinutes(effortHours, effortMinutes);
        });
        //Assert
        assertTrue(exception.getMessage().equals("Not valid work time values." + " Minute interval: [" + 0 + " - " + 60 + "]" +
                " || Hour interval: [" + 0 + " - " + 23 + "]"));
    }

    @Test
    @DisplayName("create effort hours and effort minutes, minutes field above 60 minutes")
    public void MinutesMoreThan60() {
        //Arrange
        int effortHours = 5;
        int effortMinutes = 60;
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->{
            HoursMinutes expected = new HoursMinutes(effortHours, effortMinutes);
        });
        //Assert
        assertTrue(exception.getMessage().equals("Not valid work time values." + " Minute interval: [" + 0 + " - " + 60 + "]" +
                " || Hour interval: [" + 0 + " - " + 23 + "]"));
    }

}
