package switch2021.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class SprintTest {


    @Test
    @DisplayName("Constructor test, verification of success")
    public void sprintConstructorSuccess() {
        //Arrange
        Sprint sprint = new Sprint("Sprint_1", LocalDate.of(2022, 1, 1));
        //Act
        long x = sprint.getId_Sprint();
        String name = sprint.getName();
        LocalDate date = sprint.getStartDate();
        //Assert
        assertEquals(1, x);
        assertEquals("Sprint_1", name);
        assertEquals(LocalDate.of(2022, 1, 1), date);
    }

    @Test
    @DisplayName("Constructor test, verification of failure by ID")
    public void sprintConstructorFail_ID() {
        //Arrange
        Sprint sprint = new Sprint(1, "Sprint_1", LocalDate.of(2022, 1, 1));
        //Act
        long x = sprint.getId();
        String name = sprint.getName();
        LocalDate date = sprint.getStartDate();
        //Assert
        assertNotEquals(2, x);
        assertEquals("Sprint_1", name);
        assertEquals(LocalDate.of(2022, 1, 1), date);
    }

    @Test
    @DisplayName("Constructor test, verification of failure by Name")
    public void sprintConstructorFail_Name() {
        //Arrange
        Sprint sprint = new Sprint(1, "Sprint_1", LocalDate.of(2022, 1, 1));
        //Act
        long x = sprint.getId();
        String name = sprint.getName();
        LocalDate date = sprint.getStartDate();
        //Assert
        assertEquals(1, x);
        assertNotEquals("Sprint_2", name);
        assertEquals(LocalDate.of(2022, 1, 1), date);
    }

    @Test
    @DisplayName("Constructor test, verification of failure by StartDate")
    public void sprintConstructorFail_Date() {
        //Arrange
        Sprint sprint = new Sprint(1, "Sprint_1", LocalDate.of(2022, 1, 1));
        //Act
        long x = sprint.getId();
        String name = sprint.getName();
        LocalDate date = sprint.getStartDate();
        //Assert
        assertEquals(1, x);
        assertEquals("Sprint_1", name);
        assertNotEquals(LocalDate.of(2022, 1, 2), date);
    }

    @Test
    @DisplayName("Constructor test, verification of total failure (all parameters)")
    public void sprintConstructorFail_All() {
        //Arrange
        Sprint sprint = new Sprint(1, "Sprint_1", LocalDate.of(2022, 1, 1));
        //Act
        long x = sprint.getId();
        String name = sprint.getName();
        LocalDate date = sprint.getStartDate();
        //Assert
        assertNotEquals(2, x);
        assertNotEquals("Sprint_2", name);
        assertNotEquals(LocalDate.of(2022, 1, 2), date);
    }

    @Test
    @DisplayName("Constructor Test, to catch the exception of having a empty name.")
    public void sprintConstructorNameEmptyFail() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            Sprint sprint = new Sprint(" ", LocalDate.now());
        });
    }

    @Test
    @DisplayName("Constructor Test, to catch the exception of having a low length name.")
    public void sprintConstructorNameLowLengthFail() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            Sprint sprint = new Sprint("S", LocalDate.now());
        });
    }

    @Test
    @DisplayName("Verification test if EndDate is set with success")
    public void changeEndDateSuccess() {

        //Assert
        Sprint sprint = new Sprint("Sprint_1", LocalDate.of(2022, 2, 1));
        sprint.changeEndDate(2);
        LocalDate endadate = sprint.getEndDate();
        //Assert
        assertEquals(LocalDate.of(2022, 2, 15), endadate);
    }

    @Test
    @DisplayName("Verification test if EndDate is set with failure")
    public void changeEndDateFail() {

        //Assert
        Sprint sprint = new Sprint(1, "Sprint_1", LocalDate.of(2022, 2, 1));
        sprint.changeEndDate(2);
        LocalDate endadate = sprint.getEndDate();
        //Assert
        assertNotEquals(LocalDate.of(2022, 2, 16), endadate);
    }


}
