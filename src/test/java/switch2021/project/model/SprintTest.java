package switch2021.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.depracated.UserStoryOfSprint;
import switch2021.project.stores.SprintList;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class SprintTest {


    @Test
    @DisplayName("Constructor test, verification of success")
    public void sprintConstructorSuccess() {
        //Arrange
        Sprint sprint = new Sprint("Sprint_1", LocalDate.of(2022, 1, 1));
        //Act
        int x = sprint.getIdSprint();
        String name = sprint.getName();
        LocalDate date = sprint.getStartDate();
        //Assert
        assertEquals(0, x);
        assertEquals("Sprint_1", name);
        assertEquals(LocalDate.of(2022, 1, 1), date);
    }

    @Test
    @DisplayName("Constructor test, verification of failure by ID")
    public void sprintConstructorFail_ID() {
        //Arrange
        Sprint sprint = new Sprint("Sprint_1", LocalDate.of(2022, 1, 1));
        SprintList sprintList = new SprintList();
        //Act
        sprintList.saveSprint(sprint);
        int x = sprint.getIdSprint();
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
        Sprint sprint = new Sprint("Sprint_1", LocalDate.of(2022, 1, 1));
        //Act
        int x = sprint.getIdSprint();
        String name = sprint.getName();
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
        String name = sprint.getName();
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
        sprint.changeEndDate(2);
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
    public void haschCodeTest() {

        Sprint sprint = new Sprint("Sprint_1", LocalDate.now().minusWeeks(1));
        Sprint sprint2 = new Sprint("Sprint_1", LocalDate.now().minusWeeks(1));
        Sprint sprint3 = new Sprint("Sprint_2", LocalDate.now().minusWeeks(2));


        assertEquals(sprint, sprint2);
        assertNotEquals(sprint3, sprint2);
    }

    @Test
    public void sprintBacklogEmpty() {
        LocalDate startDate = LocalDate.of(2022, 1, 2);

        Sprint sprint = new Sprint("Add an apple", startDate);
        assertTrue(sprint.getSprintBacklog().getUserStoryOfSprintList().isEmpty());
    }

    @Test
    @DisplayName("List with 1 size")
    public void sprintBacklogWithSize() {
        LocalDate startDate = LocalDate.of(2022, 1, 2);
        Sprint sprint = new Sprint("Add an apple", startDate);
        UserStoryStatus status = new UserStoryStatus("To Do");
        UserStory userstory = new UserStory("US001", 5, "AnaBoss",5);
        UserStoryOfSprint userStoryOfSprint = new UserStoryOfSprint(userstory, 5 , 1 );
        sprint.getSprintBacklog().getUserStoryOfSprintList().add(userStoryOfSprint);
        assertEquals(1, sprint.getSprintBacklog().getUserStoryOfSprintList().size());
    }
}
