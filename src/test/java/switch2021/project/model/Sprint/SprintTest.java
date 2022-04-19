package switch2021.project.model.Sprint;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.factory.SprintFactory;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;


public class SprintTest {


    @Test
    @DisplayName("Constructor test, verification of success, get ID")
    public void sprintConstructorSuccess_getID() {
        //Arrange
        Sprint sprint = new Sprint(1, "Project_2022_1_Sprint 1", "SprintName");
        //Assert
        assertEquals("Project_2022_1_Sprint 1", sprint.getSprintID().toString());
    }

    @Test
    @DisplayName("Constructor test, verification of success, get name")
    public void sprintConstructorSuccess_getName() {
        //Arrange
        Sprint sprint = new Sprint(1, "Project_2022_1_Sprint 1", "Sprint Name");
        //Act
        String name = sprint.getSprintName().getText();
        //Assert
        assertEquals("Sprint Name", name);
    }

    @Test
    @DisplayName("Constructor test, verification of failure by ID")
    public void sprintConstructorFail_ID() {
        //Arrange
        SprintStore sprintList = new SprintStore(new SprintFactory());
        //Act
        Sprint sprint = sprintList.createAndSaveSprint(1, "Project_2022_1_Sprint 1", "Sprint Name", 2);
        String x = sprint.getSprintID().toString();
        //Assert
        assertNotEquals("Project_2022_1_Sprint 2", x);
    }

    @Test
    @DisplayName("Constructor test, verification of failure by Name")
    public void sprintConstructorFail_Name() {
        //Arrange
        SprintStore sprintStore = new SprintStore(new SprintFactory());
        //Act
        sprintStore.createAndSaveSprint(1, "Project_2022_1_Sprint 1", "Sprint Name", 2);
        Sprint sprint = new Sprint(1, "Project_2022_1_Sprint 2", "Name of the Sprint");
        String name = sprint.getSprintName().getText();
        //Assert
        assertNotEquals("Sprint Name", name);
    }

    @Test
    @DisplayName("Constructor test, verification of total failure (all parameters)")
    public void sprintConstructorFail_All() {
        //Arrange
        SprintStore sprintStore = new SprintStore(new SprintFactory());
        //Act
        Sprint sprint1 = sprintStore.createAndSaveSprint(1, "Project_2022_1_Sprint 1", "Sprint Name", 2);
        String x = sprint1.getSprintName().getText();
        Sprint sprint2 = new Sprint(1, "Project_2022_1_Sprint 2", "Name of the Sprint");
        String name = sprint2.getSprintName().getText();
        //Assert
        assertNotEquals(x, name);
        assertNotEquals("Project_2022_1_Sprint 1", name);
    }

    @Test
    @DisplayName("Constructor Test, to catch the exception of having a empty name.")
    public void sprintConstructorNameEmptyFail() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            new Sprint(1, "Project_2022_1_Sprint 1", " ");
        });
    }

    @Test
    @DisplayName("Verification test if EndDate is set with failure")
    public void changeEndDateFail() {
        //Assert
        Sprint sprint = new Sprint(1, "Project_2022_1_Sprint 1", "Sprint Name");
        sprint.changeEndDate(300);
        LocalDate endDate = sprint.getEndDate();
        //Assert
        assertNotEquals(LocalDate.of(2022, 2, 16), endDate);
    }

    @Test
    @DisplayName("Verification test if is a current Sprint")
    public void isCurrentSprintFalseTest() {
        Sprint sprint = new Sprint(1, "Project_2022_1_Sprint 1", "Sprint Name");
        sprint.setStartDate(LocalDate.of(2021, 1, 1));
        sprint.setEndDate(LocalDate.of(2020, 1, 1));
        assertFalse(sprint.isCurrentSprint());
    }

    @Test
    @DisplayName("Verification test if is a current Sprint")
    public void isCurrentSprintTrueTest() {
        Sprint sprint = new Sprint(1, "Project_2022_1_Sprint 1", "Sprint Name");
        sprint.setStartDate(LocalDate.of(2022, 1, 1));
        sprint.setEndDate(LocalDate.of(2022, 1, 15));
        sprint.changeEndDate(14);
        assertTrue(sprint.isCurrentSprint());
    }

    @Test
    @DisplayName("Verification test if is a current Sprint")
    public void isCurrentSprintEndDateNullTest() {
        assertThrows(NullPointerException.class, () -> {
            Sprint sprint = new Sprint(1, "Project_2022_1_Sprint 1", "Sprint Name");
            sprint.isCurrentSprint();
        });
    }

    @Test
    @DisplayName("Verification test of hasSprintID method")
    public void hasSprintIDTest() {
        Sprint sprint = new Sprint(1, "Project_2022_1_Sprint 1", "SprintName");
        SprintID sprintID = new SprintID("Project_2022_1_Sprint New");
        sprint.setSprintID(sprintID);
        assertTrue(sprint.hasSprintID(sprintID));
    }

    @Test
    @DisplayName("Verification test of hasSprintID method")
    public void hasSprintIDTest_Fail() {
        //Arrange
        Sprint sprint = new Sprint(1, "Project_2022_1_Sprint 1", "Sprint Name");
        SprintID sprintID = new SprintID("Project_2022_1_Sprint 1");
        //Assert
        assertFalse(sprint.hasSprintID(sprintID));
    }


    @Test
    @DisplayName("HashCode Verification")
    public void hashCodeTest() {
        //Arrange
        Sprint sprint = new Sprint(1, "Project_2022_1_Sprint 1", "Sprint Name");
        Sprint sprint2 = new Sprint(1, "Project_2022_1_Sprint 1", "Sprint Name");
        Sprint sprint3 = new Sprint(1,"Project_2022_1_Sprint 2", "Sprint New");
        //Assert
        assertEquals(sprint.hashCode(), sprint2.hashCode());
        assertTrue(sprint.equals(sprint));
        assertNotEquals(sprint3.hashCode(), sprint2.hashCode());
        assertNotEquals(sprint3, sprint2);
    }

    @Test
    @DisplayName("Test to validate start date")
    public void validateStartDate() {
        //Arrange
        SprintStore sprintList = new SprintStore(new SprintFactory());
        Sprint sprint1 = sprintList.createAndSaveSprint(1, "Project_2022_1_Sprint 1", "New Sprint", 2);
        Sprint sprint2 = sprintList.createAndSaveSprint(1, "Project_2022_1_Sprint 2", "New New Sprint", 2);
        sprint1.setStartDate(LocalDate.of(2022, 4, 1));
        sprint2.setStartDate(LocalDate.of(2022, 4, 1));
        sprint1.setEndDate(LocalDate.of(2022, 5, 1));
        sprint2.setEndDate(LocalDate.of(2022, 5, 1));
        //Assert
        assertNotEquals(sprint1, sprint2);
        assertEquals(sprint1.getStartDate(),sprint2.getStartDate());
        assertEquals(sprint1.getEndDate(),sprint2.getEndDate());
    }
}
