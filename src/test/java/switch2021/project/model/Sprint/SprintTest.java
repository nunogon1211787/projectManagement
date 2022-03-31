package switch2021.project.model.Sprint;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.factory.SprintFactory;
import switch2021.project.stores.SprintList;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class SprintTest {


    @Test
    @DisplayName("Constructor test, verification of success")
    public void sprintConstructorSuccess() {
        //Arrange
        Sprint sprint = new Sprint("S", LocalDate.of(2022, 1, 1));
        //Act
        int x = sprint.getIdSprint();
        String name = sprint.getSprintName().getText();
        LocalDate date = sprint.getStartDate();
        //Assert
        assertEquals(0, x);
        assertEquals("S", name);
        assertEquals(LocalDate.of(2022, 1, 1), date);
    }

    @Test
    @DisplayName("Constructor test, verification of failure by ID")
    public void sprintConstructorFail_ID() {
        //Arrange
        Sprint sprint = new Sprint("Sprint_1", LocalDate.of(2022, 1, 1));
        SprintList sprintList = new SprintList(new SprintFactory());
        //Act
        sprintList.saveSprint(sprint);
        int x = sprint.getIdSprint();
        String name = sprint.getSprintName().getText();
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
        Sprint sprint = new Sprint("Sprint_1", LocalDate.of(2022, 1, 1));
        //Act
        int x = sprint.getIdSprint();
        String name = sprint.getSprintName().getText();
        LocalDate date = sprint.getStartDate();
        //Assert
        assertNotEquals(1, x);
        assertNotEquals("Sprint_2", name);
        assertEquals(LocalDate.of(2022, 1, 1), date);
    }

    @Test
    @DisplayName("Constructor test, verification of failure by StartDate")
    public void sprintConstructorFail_Date() {
        //Arrange
        Sprint sprint = new Sprint("Sprint_1", LocalDate.of(2022, 1, 1));
        //Act
        int x = sprint.getIdSprint();
        String name = sprint.getSprintName().getText();
        LocalDate date = sprint.getStartDate();
        //Assert
        assertNotEquals(1, x);
        assertEquals("Sprint_1", name);
        assertNotEquals(LocalDate.of(2022, 1, 2), date);
    }

    @Test
    @DisplayName("Constructor test, verification of total failure (all parameters)")
    public void sprintConstructorFail_All() {
        //Arrange
        Sprint sprint = new Sprint("Sprint_1", LocalDate.of(2022, 1, 1));
        //Act
        int x = sprint.getIdSprint();
        String name = sprint.getSprintName().getText();
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
            new Sprint(" ", LocalDate.now());
        });
    }

    @Test
    @DisplayName("Verification test if EndDate is set with success")
    public void changeEndDateSuccess() {
        //Assert
        Sprint sprint = new Sprint("Sprint_1", LocalDate.of(2022, 2, 1));
        sprint.changeEndDate(14);
        LocalDate endadate = sprint.getEndDate();
        //Assert
        assertEquals(LocalDate.of(2022, 2, 14), endadate);
    }

    @Test
    @DisplayName("Verification test if EndDate is set with failure")
    public void changeEndDateFail() {
        //Assert
        Sprint sprint = new Sprint("Sprint_1", LocalDate.of(2022, 2, 1));
        sprint.changeEndDate(2);
        LocalDate endadate = sprint.getEndDate();
        //Assert
        assertNotEquals(LocalDate.of(2022, 2, 16), endadate);
    }

    @Test
    @DisplayName("Verification test if is a current Sprint")
    public void isCurrentSprintFalseTest() {
        Sprint sprint = new Sprint("Sprint_1", LocalDate.now().minusWeeks(3));
        sprint.changeEndDate(2);
        assertFalse(sprint.isCurrentSprint());
    }

    @Test
    @DisplayName("Verification test if is a current Sprint")
    public void isCurrentSprintTrueTest() {
        Sprint sprint = new Sprint("Sprint_1", LocalDate.now().minusWeeks(1));
        sprint.changeEndDate(14);
        assertTrue(sprint.isCurrentSprint());
    }

    @Test
    @DisplayName("Verification test if is a current Sprint")
    public void isCurrentSprintEndDateNullTest() {
        assertThrows(NullPointerException.class, () -> {
            Sprint sprint = new Sprint("Sprint_1", LocalDate.now().minusWeeks(1));
            sprint.isCurrentSprint();
        });
    }

    @Test
    @DisplayName("Verification test of hasSprintID method")
    public void hasSprintIDTest() {
        Sprint sprint = new Sprint("Sprint_1", LocalDate.now().minusWeeks(1));
        sprint.setIdSprint(1);
        assertTrue(sprint.hasSprintID(1));
    }

    @Test
    @DisplayName("HashCode Verification")
    public void hashCodeTest() {
        //Arrange
        Sprint sprint = new Sprint("Sprint_1", LocalDate.now().minusWeeks(1));
        Sprint sprint2 = new Sprint("Sprint_1", LocalDate.now().minusWeeks(1));
        Sprint sprint3 = new Sprint("Sprint_2", LocalDate.now().minusWeeks(2));
        //Assert
        assertEquals(sprint.hashCode(), sprint2.hashCode());
        assertEquals(sprint, sprint2);
        assertNotEquals(sprint3.hashCode(), sprint2.hashCode());
        assertNotEquals(sprint3, sprint2);
    }

    @Test
    @DisplayName("Test to validate start date")
    public void validateStartDate() {
        LocalDate startDate1 = LocalDate.of(2022, 1, 1);
        LocalDate startDate2 = LocalDate.of(2022, 1, 1);
        Sprint sprint1 = new Sprint("Sprint_1", startDate1);
        Sprint sprint2 = new Sprint("Sprint_12", startDate2);
        SprintList sprintList = new SprintList(new SprintFactory());
        sprintList.saveSprint(sprint1);
        sprintList.saveSprint(sprint2);
        assertNotEquals(sprint1, sprint2);
        assertTrue(sprintList.validateIfSprintAlreadyExists(sprint2));
    }
}
