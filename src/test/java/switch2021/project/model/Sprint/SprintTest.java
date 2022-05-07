package switch2021.project.model.Sprint;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.model.valueObject.Description;
import switch2021.project.model.valueObject.ProjectID;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class SprintTest {


    @Test
    @DisplayName("Constructor Test, with success")
    void CreateSprintWithSuccess() {
        //Arrange
        SprintID sprintID = mock(SprintID.class);
        Sprint sprint = new Sprint(sprintID);
        ProjectID projectID = mock(ProjectID.class);
        Description sprintName = mock(Description.class);
        //Act
        when(sprintID.getProjectID()).thenReturn(projectID);
        when(projectID.getCode()).thenReturn("Project_2022_1");
        when(sprintID.getSprintName()).thenReturn(sprintName);
        when(sprintName.getText()).thenReturn("Sprint Name");
        //Assert
        assertEquals(sprintID, sprint.getSprintID());
    }

    @Test
    @DisplayName("Constructor Test, with failure")
    void CreateSprintWithFailure() {
        //Arrange
        SprintID sprintID = mock(SprintID.class);
        Sprint sprint = new Sprint(sprintID);
        ProjectID projectID = mock(ProjectID.class);
        Description sprintName = mock(Description.class);
        //Act
        when(sprintID.getProjectID()).thenReturn(projectID);
        when(projectID.getCode()).thenReturn("Project_2022_1");
        when(sprintID.getSprintName()).thenReturn(sprintName);
        when(sprintName.getText()).thenReturn("Sprint Name");
        //Assert
        assertNotEquals("Project_2022_1_Sprint", sprint.getSprintID().toString());
    }

    @Test
    @DisplayName("Verification test if EndDate is set with failure")
    public void changeEndDateFail() {
        //Assert
        SprintID sprintID = new SprintID("Project_2022_1_Sprint 1");
        Sprint sprint = new Sprint(sprintID);
        sprint.changeEndDate(300);
        LocalDate endDate = sprint.getEndDate();
        //Assert
        assertNotEquals(LocalDate.of(2022, 2, 16), endDate);
    }

    @Test
    @DisplayName("Verification test if is a current Sprint")
    public void isCurrentSprintFalseTest() {
        SprintID sprintID = new SprintID("Project_2022_1_Sprint 1");
        Sprint sprint = new Sprint(sprintID);
        sprint.setStartDate(LocalDate.of(2021, 1, 1));
        sprint.setEndDate(LocalDate.of(2020, 1, 1));
        assertFalse(sprint.isCurrentSprint());
    }

    @Test
    @DisplayName("Verification test if is a current Sprint")
    public void isCurrentSprintTrueTest() {
        SprintID sprintID = new SprintID("Project_2022_1_Sprint 1");
        Sprint sprint = new Sprint(sprintID);
        sprint.setStartDate(LocalDate.of(2022, 1, 1));
        sprint.setEndDate(LocalDate.of(2022, 1, 15));
        sprint.changeEndDate(14);
        assertTrue(sprint.isCurrentSprint());
    }

    @Test
    @DisplayName("Verification test if is a current Sprint")
    public void isCurrentSprintEndDateNullTest() {
        assertThrows(NullPointerException.class, () -> {
            SprintID sprintID = new SprintID("Project_2022_1_Sprint 1");
            Sprint sprint = new Sprint(sprintID);
            sprint.isCurrentSprint();
        });
    }

    @Test
    @DisplayName("Verification test of hasSprintID method")
    public void hasSprintIDTest() {
        SprintID sprintID = new SprintID("Project_2022_1_Sprint 1");
        Sprint sprint = new Sprint(sprintID);
        sprint.setSprintID(sprintID);
        assertTrue(sprint.hasSprintID("Project_2022_1_Sprint 1"));
    }

    @Test
    @DisplayName("Verification test of hasSprintID method")
    public void hasSprintIDTest_Fail() {
        //Arrange
        SprintID sprintID = new SprintID("Project_2022_1_Sprint 2");
        Sprint sprint = new Sprint(sprintID);
        //Assert
        assertFalse(sprint.hasSprintID("Project_2022_1_Sprint New"));
    }


    @Test
    @DisplayName("HashCode Verification")
    public void hashCodeTest() {
        //Arrange
        SprintID sprintID1 = new SprintID("Project_2022_1_Sprint 1");
        Sprint sprint = new Sprint(sprintID1);
        SprintID sprintID2 = new SprintID("Project_2022_1_Sprint 2");
        Sprint sprint2 = new Sprint(sprintID1);
        Sprint sprint3 = new Sprint(sprintID2);
        //Assert
        assertEquals(sprint.hashCode(), sprint2.hashCode());
        assertTrue(sprint.equals(sprint));
        assertNotEquals(sprint3.hashCode(), sprint2.hashCode());
        assertNotEquals(sprint3, sprint2);
    }
//
//    @Test
//    @DisplayName("Test to validate start date")
//    public void validateStartDate() {
//        //Arrange
//        SprintStore sprintList = new SprintStore();
//        SprintFactory sprintFactory = new SprintFactory();
//        Sprint sprint1 = sprintFactory.createSprint("Project_2022_1", "Project_2022_1_Sprint 1", "New Sprint");
//        sprintList.saveSprint(sprint1);
//        Sprint sprint2 = sprintFactory.createSprint("Project_2022_1", "Project_2022_1_Sprint 2", "New New Sprint");
//        sprintList.saveSprint(sprint2);
//        sprint1.setStartDate(LocalDate.of(2022, 4, 1));
//        sprint2.setStartDate(LocalDate.of(2022, 4, 1));
//        sprint1.setEndDate(LocalDate.of(2022, 5, 1));
//        sprint2.setEndDate(LocalDate.of(2022, 5, 1));
//        //Assert
//        assertNotEquals(sprint1, sprint2);
//        assertEquals(sprint1.getStartDate(),sprint2.getStartDate());
//        assertEquals(sprint1.getEndDate(),sprint2.getEndDate());
//    }
}
