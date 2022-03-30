package switch2021.project.valueObject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class TaskEffortTest {

    @Test
    @DisplayName("create task effort with success")
    public void createTaskEffortSuccess() {
        //Arrange
        int effortHours = 4;
        int effortMinutes = 30;
        Date effortDate = new Date (LocalDate.of(2022, 1, 27));
        String comment = "design";
        String attachment = "photo";
        //Act
        TaskEffort taskEffort = new TaskEffort(effortHours,effortMinutes, effortDate, comment, attachment);
        //Assert
        assertEquals(effortHours, taskEffort.getEffort().getEffortHours());
        assertEquals(effortMinutes, taskEffort.getEffort().getEffortMinutes());
        assertEquals(effortDate, taskEffort.getEffortDate());
        assertEquals(comment, taskEffort.getComment());
        assertEquals(attachment, taskEffort.getAttachment());
    }

    @Test
    @DisplayName("create effort with no comments or attachments")
    public void createTaskEffortSuccessOptionalValuesEmpty() {
        //Arrange
        int effortHours = 4;
        int effortMinutes = 30;
        Date effortDate = new Date (LocalDate.of(2022, 1, 27));
        String comment = "";
        String attachment = "";
        //Act
        TaskEffort taskEffort = new TaskEffort(effortHours,effortMinutes, effortDate, comment, attachment);
        //Assert
        assertEquals(effortHours, taskEffort.getEffort().getEffortHours());
        assertEquals(effortMinutes, taskEffort.getEffort().getEffortMinutes());
        assertEquals(effortDate, taskEffort.getEffortDate());
        assertEquals(comment, taskEffort.getComment());
        assertEquals(attachment, taskEffort.getAttachment());
    }

    @Test
    @DisplayName("create effort only with minutes")
    public void createTaskEffortSuccessWithOnlyMinutes() {
        //Arrange
        int effortHours = 0;
        int effortMinutes = 30;
        Date effortDate = new Date (LocalDate.of(2022, 1, 27));
        String comment = "design";
        String attachment = "photo";
        //Act
        TaskEffort taskEffort = new TaskEffort(effortHours, effortMinutes, effortDate, comment, attachment);
        //Assert
        assertEquals(effortHours, taskEffort.getEffort().getEffortHours());
        assertEquals(effortMinutes, taskEffort.getEffort().getEffortMinutes());
        assertEquals(effortDate, taskEffort.getEffortDate());
        assertEquals(comment, taskEffort.getComment());
        assertEquals(attachment, taskEffort.getAttachment());
    }

    @Test
    @DisplayName("create effort only with hours")
    public void createTaskEffortSuccessWithOnlyHours() {
        //Arrange
        int effortHours = 4;
        int effortMinutes = 0;
        Date effortDate = new Date (LocalDate.of(2022, 1, 27));
        String comment = "design";
        String attachment = "photo";
        //Act
        TaskEffort taskEffort = new TaskEffort(effortHours, effortMinutes, effortDate, comment, attachment);
        //Assert
        assertEquals(effortHours, taskEffort.getEffort().getEffortHours());
        assertEquals(effortMinutes, taskEffort.getEffort().getEffortMinutes());
        assertEquals(effortDate, taskEffort.getEffortDate());
        assertEquals(comment, taskEffort.getComment());
        assertEquals(attachment, taskEffort.getAttachment());
    }

    //Fails
    @Test
    @DisplayName("create effort with negative hours field")
    public void createTaskEffortFailWorkHoursNegative() {
        //Arrange
        int effortHours = -4;
        int effortMinutes = 30;
        Date effortDate = new Date (LocalDate.of(2022, 1, 27));
        String comment = "design";
        String attachment = "";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            TaskEffort taskEffort = new TaskEffort(effortHours,effortMinutes, effortDate, comment, attachment);
        });
        //Assert
        assertTrue(exception.getMessage().equals("Not valid work time values." + " Minute interval: [" + 0 + " - " + 60 + "]" +
                " || Hour interval: [" + 0 + " - " + 23 + "]"));
    }

    @Test
    @DisplayName("create effort with negative minutes field")
    public void createTaskEffortFailWorkMinutesNegative() {
        //Arrange
        int effortHours = 4;
        int effortMinutes = -30;
        Date effortDate = new Date (LocalDate.of(2022, 1, 27));
        String comment = "design";
        String attachment = "";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            TaskEffort taskEffort = new TaskEffort(effortHours,effortMinutes, effortDate, comment, attachment);
        });
        //Assert
        assertTrue(exception.getMessage().equals("Not valid work time values." + " Minute interval: [" + 0 + " - " + 60 + "]" +
                " || Hour interval: [" + 0 + " - " + 23 + "]"));
    }

    @Test
    @DisplayName("create effort with hours and minutes without time")
    public void createTaskEffortFailNoWorkingTime() {
        //Arrange
        int effortHours = 0;
        int effortMinutes = 0;
        Date effortDate = new Date (LocalDate.of(2022, 1, 27));
        String comment = "design";
        String attachment = "";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            TaskEffort taskEffort = new TaskEffort(effortHours,effortMinutes, effortDate, comment, attachment);
        });
        //Assert
        assertTrue(exception.getMessage().equals("Not valid work time values." + " Minute interval: [" + 0 + " - " + 60 + "]" +
                " || Hour interval: [" + 0 + " - " + 23 + "]"));
    }

    @Test
    @DisplayName("create effort with over 24 hours")
    public void createTaskEffortFailWorkingHoursMoreThanADay() {
        //Arrange
        int effortHours = 25;
        int effortMinutes = 10;
        Date effortDate = new Date (LocalDate.of(2022, 1, 27));
        String comment = "design";
        String attachment = "";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            TaskEffort taskEffort = new TaskEffort(effortHours,effortMinutes, effortDate, comment, attachment);
        });
        //Assert
        assertTrue(exception.getMessage().equals("Not valid work time values." + " Minute interval: [" + 0 + " - " + 60 + "]" +
                " || Hour interval: [" + 0 + " - " + 23 + "]"));
    }

    @Test
    @DisplayName("create effort with 24 hours")
    public void createTaskEffortSuccessWorkingHoursOnLimit() {
        //Arrange
        int effortHours = 23;
        int effortMinutes = 59;
        Date effortDate = new Date (LocalDate.of(2022, 1, 27));
        String comment = "design";
        String attachment = "photo";
        //Act
        TaskEffort taskEffort = new TaskEffort(effortHours,effortMinutes, effortDate, comment, attachment);
        //Assert
        assertEquals(effortHours, taskEffort.getEffort().getEffortHours());
        assertEquals(effortMinutes, taskEffort.getEffort().getEffortMinutes());
        assertEquals(effortDate, taskEffort.getEffortDate());
        assertEquals(comment, taskEffort.getComment());
        assertEquals(attachment, taskEffort.getAttachment());
    }


    @Test
    @DisplayName("create effort with 60 min")
    public void createTaskEffortFailMinutesOnLimit() {
        //Arrange
        int effortHours = 1;
        int effortMinutes = 60;
        Date effortDate = new Date (LocalDate.of(2022, 1, 27));
        String comment = "design";
        String attachment = "";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            TaskEffort taskEffort = new TaskEffort(effortHours,effortMinutes, effortDate, comment, attachment);
        });
        //Assert
        assertTrue(exception.getMessage().equals("Not valid work time values." + " Minute interval: [" + 0 + " - " + 60 + "]" +
                " || Hour interval: [" + 0 + " - " + 23 + "]"));
    }

    @Test
    @DisplayName("create effort with over 60 minutes")
    public void createTaskEffortFailWorkingMinutesMoreThanAHour() {
        //Arrange
        int effortHours = 0;
        int effortMinutes = 61;
        Date effortDate = new Date (LocalDate.of(2022, 1, 27));
        String comment = "design";
        String attachment = "";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            TaskEffort taskEffort = new TaskEffort(effortHours,effortMinutes, effortDate, comment, attachment);
        });
        //Assert
        assertTrue(exception.getMessage().equals("Not valid work time values." + " Minute interval: [" + 0 + " - " + 60 + "]" +
                " || Hour interval: [" + 0 + " - " + 23 + "]"));
    }

    @Test
    @DisplayName("create effort with register effort is in the future")
    public void createTaskEffortFailWorkDateAfterToday() {
        //Arrange
        int effortHours = 4;
        int effortMinutes = 30;
        String comment = "design";
        String attachment = "";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Date effortDate = new Date (LocalDate.now().plusDays(1));
            TaskEffort taskEffort = new TaskEffort(effortHours,effortMinutes, effortDate, comment, attachment);
        });
        //Assert
        assertTrue(exception.getMessage().equals("Invalid workHours value."));
    }

}
