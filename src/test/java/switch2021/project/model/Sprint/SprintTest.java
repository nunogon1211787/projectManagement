package switch2021.project.model.Sprint;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.factory.SprintFactory;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class SprintTest {


    @Test
    @DisplayName("Constructor test, verification of success")
    public void sprintConstructorSuccess() {
        //Arrange
        Sprint sprint = new Sprint("S");
        //Act
        int x = sprint.getIdSprint();
        String name = sprint.getSprintName().getText();
        //Assert
        assertEquals(0, x);
        assertEquals("S", name);
    }

    @Test
    @DisplayName("Constructor test, verification of failure by ID")
    public void sprintConstructorFail_ID() {
        //Arrange
        Sprint sprint = new Sprint("Sprint_1");
        SprintStore sprintList = new SprintStore(new SprintFactory());
        //Act
        sprintList.saveSprint(sprint);
        int x = sprint.getIdSprint();
        String name = sprint.getSprintName().getText();
        sprint.setStartDate(LocalDate.of(2022, 1, 1));
        //Assert
        assertNotEquals(2, x);
        assertEquals("Sprint_1", name);
        assertEquals(LocalDate.of(2022, 1, 1), sprint.getStartDate());
    }

    @Test
    @DisplayName("Constructor test, verification of failure by Name")
    public void sprintConstructorFail_Name() {
        //Arrange
        Sprint sprint = new Sprint("Sprint_1");
        //Act
        int x = sprint.getIdSprint();
        String name = sprint.getSprintName().getText();
        sprint.setStartDate(LocalDate.of(2022, 1, 1));
        //Assert
        assertNotEquals(1, x);
        assertNotEquals("Sprint_2", name);
        assertEquals(LocalDate.of(2022, 1, 1), sprint.getStartDate());
    }

    @Test
    @DisplayName("Constructor test, verification of failure by StartDate")
    public void sprintConstructorFail_Date() {
        //Arrange
        Sprint sprint = new Sprint("Sprint_1");
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
        Sprint sprint = new Sprint("Sprint_1");
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
            new Sprint(" ");
        });
    }

    @Test
    @DisplayName("Verification test if EndDate is set with failure")
    public void changeEndDateFail() {
        //Assert
        Sprint sprint = new Sprint("Sprint_1");
        sprint.changeEndDate(300);
        LocalDate endDate = sprint.getEndDate();
        //Assert
        assertNotEquals(LocalDate.of(2022, 2, 16), endDate);
    }

    @Test
    @DisplayName("Verification test if is a current Sprint")
    public void isCurrentSprintFalseTest() {
        Sprint sprint = new Sprint("Sprint_1");
        sprint.setStartDate(LocalDate.of(2021, 1, 1));
        sprint.setEndDate(LocalDate.of(2020, 1, 1));
        assertFalse(sprint.isCurrentSprint());
    }

    @Test
    @DisplayName("Verification test if is a current Sprint")
    public void isCurrentSprintTrueTest() {
        Sprint sprint = new Sprint("Sprint_1");
        sprint.setStartDate(LocalDate.of(2022, 1, 1));
        sprint.setEndDate(LocalDate.of(2022, 1, 15));
        sprint.changeEndDate(14);
        assertTrue(sprint.isCurrentSprint());
    }

    @Test
    @DisplayName("Verification test if is a current Sprint")
    public void isCurrentSprintEndDateNullTest() {
        assertThrows(NullPointerException.class, () -> {
            Sprint sprint = new Sprint("Sprint_1");
            sprint.isCurrentSprint();
        });
    }

    @Test
    @DisplayName("Verification test of hasSprintID method")
    public void hasSprintIDTest() {
        Sprint sprint = new Sprint("Sprint_1");
        sprint.setIdSprint(1);
        assertTrue(sprint.hasSprintID(1));
    }

    @Test
    @DisplayName("Verification test of hasSprintID method")
    public void hasSprintIDTest_Fail() {
        Sprint sprint = new Sprint("Sprint_1");
        sprint.setIdSprint(1);
        assertFalse(sprint.hasSprintID(9));
    }


    @Test
    @DisplayName("HashCode Verification")
    public void hashCodeTest() {
        //Arrange
        Sprint sprint = new Sprint("Sprint_1");
        Sprint sprint2 = new Sprint("Sprint_1");
        Sprint sprint3 = new Sprint("Sprint_2");
        //Assert
        assertEquals(sprint.hashCode(), sprint2.hashCode());
        assertEquals(sprint, sprint2);
        assertNotEquals(sprint3.hashCode(), sprint2.hashCode());
        assertNotEquals(sprint3, sprint2);
    }

    @Test
    @DisplayName("Override Verification")
    public void overrideTest_null() {
        //Arrange
        Sprint sprint = new Sprint("Sprint_1");
        Sprint sprint2 = null;
        //Assert
        assertNotEquals(sprint, sprint2);
       assertNull(sprint2);
    }

    @Test
    @DisplayName("Test to validate start date")
    public void validateStartDate() {
        Sprint sprint1 = new Sprint("Sprint_1");
        Sprint sprint2 = new Sprint("Sprint_12");
        SprintStore sprintList = new SprintStore(new SprintFactory());
        sprintList.saveSprint(sprint1);
        sprintList.saveSprint(sprint2);
        sprintList.findSprints().get(0).setEndDate(LocalDate.of(2022, 5, 1));
        sprintList.findSprints().get(1).setEndDate(LocalDate.of(2022, 5, 1));
        assertNotEquals(sprint1, sprint2);
        assertTrue(sprintList.validateIfSprintAlreadyExists(sprint2));
        assertEquals(sprint1.getStartDate(),sprint2.getStartDate());
        assertEquals(sprint1.getEndDate(),sprint2.getEndDate());
    }
}
