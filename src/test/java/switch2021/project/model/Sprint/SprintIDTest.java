package switch2021.project.model.Sprint;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.model.valueObject.Budget;
import switch2021.project.model.valueObject.Description;
import switch2021.project.model.valueObject.ProjectCode;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SprintIDTest {


    @DisplayName("Validate if ID is correct")
    @Test
    public void sprintIDSuccess() {
        //Arrange
        ProjectCode projectCode = mock(ProjectCode.class);
        when(projectCode.getCode()).thenReturn("Project_2022_1");
        Description description = mock(Description.class);
        when(description.getText()).thenReturn("Sprint 1");
        //Act
        SprintID sprintID = new SprintID(projectCode,description);
        String expected = "Project_2022_1_Sprint 1";
        //Assert
        assertEquals(expected, sprintID.toString());
    }


    @DisplayName("Validate if ID is incorrect")
    @Test
    public void sprintIDFail() {
        //Arrange
        ProjectCode projectCode = mock(ProjectCode.class);
        when(projectCode.getCode()).thenReturn("Project_2022_1");
        Description description = mock(Description.class);
        when(description.getText()).thenReturn("Sprint 1");
        //Act
        SprintID sprintID = new SprintID(projectCode,description);
        String expected = "Project_2022_1_Sprint";
        //Assert
        assertNotEquals(expected, sprintID.toString());
    }

    @DisplayName("Validate that Sprint ID has the same ID")
    @Test
    public void sameSprintID() {
        //Arrange
        ProjectCode projectCode = mock(ProjectCode.class);
        when(projectCode.getCode()).thenReturn("Project_2022_1");
        Description description = mock(Description.class);
        when(description.getText()).thenReturn("Sprint 1");
        //Act
        SprintID sprintID1 = new SprintID(projectCode,description);
        SprintID sprintID2 = new SprintID(projectCode,description);
        //Assert
        assertEquals(sprintID1, sprintID2);
    }


    @DisplayName("Validate that Sprint ID hasn't the same ID")
    @Test
    public void notSameSprintID() {
        //Arrange
        ProjectCode projectCode = mock(ProjectCode.class);
        when(projectCode.getCode()).thenReturn("Project_2022_1");
        Description description = mock(Description.class);
        when(description.getText()).thenReturn("Sprint 1");
        ProjectCode projectCode1 = mock(ProjectCode.class);
        when(projectCode1.getCode()).thenReturn("Project_2022_2");
        Description description1 = mock(Description.class);
        when(description1.getText()).thenReturn("Sprint 2");
        //Act
        SprintID sprintID1 = new SprintID(projectCode,description);
        SprintID sprintID2 = new SprintID(projectCode1,description1);
        //Assert
        assertNotSame(sprintID1, sprintID2);
    }


    @DisplayName("Project Code Null")
    @Test
    public void projectCodeNull() {
        //Arrange
        Description description = mock(Description.class);
        when(description.getText()).thenReturn("Sprint");
        //Act
        SprintID sprintID = new SprintID(null, description);
        //Assert
        assertNull(sprintID.getProjectID());
    }

    @DisplayName("Sprint Name Null")
    @Test
    public void sprintNameNull() {
        //Arrange
        ProjectCode projectCode = mock(ProjectCode.class);
        when(projectCode.getCode()).thenReturn("Project_2022_1");
        //Act
        SprintID sprintID = new SprintID(projectCode, null);
        //Assert
        assertNull(sprintID.getSprintName());
    }

    @DisplayName("Test hashcode conditions - Success Scenario")
    @Test
    public void hashCodeSuccess() {
        //Arrange
        ProjectCode projectCode = mock(ProjectCode.class);
        when(projectCode.getCode()).thenReturn("Project_2022_1");
        Description description = mock(Description.class);
        when(description.getText()).thenReturn("Sprint 1");
        //Act
        SprintID sprintID1 = new SprintID(projectCode, description);
        SprintID sprintID2 = new SprintID(projectCode, description);
        //Assert
        assertEquals(sprintID1.hashCode(), sprintID2.hashCode());
    }

    @DisplayName("Test hashcode conditions - Failure Scenario")
    @Test
    public void hashCodeFailure() {
        //Arrange
        ProjectCode projectCode = mock(ProjectCode.class);
        when(projectCode.getCode()).thenReturn("Project_2022_1");
        Description description = mock(Description.class);
        when(description.getText()).thenReturn("Sprint 1");
        ProjectCode projectCode1 = mock(ProjectCode.class);
        when(projectCode1.getCode()).thenReturn("Project_2022_2");
        Description description1 = mock(Description.class);
        when(description1.getText()).thenReturn("Sprint 2");
        //Act
        SprintID sprintID1 = new SprintID(projectCode, description);
        SprintID sprintID2 = new SprintID(projectCode1, description1);
        //Assert
        assertNotEquals(sprintID1, sprintID2);
    }

    @DisplayName("Override Test - Success")
    @Test
    public void overrideTestSuccess() {
        //Arrange
        ProjectCode projectCode = mock(ProjectCode.class);
        when(projectCode.getCode()).thenReturn("Project_2022_1");
        Description description = mock(Description.class);
        when(description.getText()).thenReturn("Sprint");
        //Act
        SprintID sprintID = new SprintID(projectCode, description);
        //Assert
        assertEquals(sprintID, sprintID);
    }

    @DisplayName("Override Test - Failure")
    @Test
    public void overrideTestFailure() {
        //Arrange
        ProjectCode projectCode = mock(ProjectCode.class);
        when(projectCode.getCode()).thenReturn("Project_2022_1");
        Description description = mock(Description.class);
        when(description.getText()).thenReturn("Sprint 1");
        ProjectCode projectCode1 = mock(ProjectCode.class);
        when(projectCode1.getCode()).thenReturn("Project_2022_1");
        Description description1 = mock(Description.class);
        when(description1.getText()).thenReturn("Sprint 1");
        //Act
        SprintID sprintID = new SprintID(projectCode, description);
        Budget budget = new Budget(2000);
        //Assert
        assertNotEquals(sprintID, budget);
    }

    @DisplayName("Same Value As - Override Test")
    @Test
    public void sameValueAs() {
        //Arrange
        ProjectCode projectCode = mock(ProjectCode.class);
        when(projectCode.getCode()).thenReturn("Project_2022_1");
        Description description = mock(Description.class);
        when(description.getText()).thenReturn("Sprint 1");
        //Act
        SprintID sprintID = new SprintID(projectCode, description);
        //Assert
        assertFalse(sprintID.sameValueAs(sprintID));
    }

    @DisplayName("To String - Override Test")
    @Test
    public void sprintIDToString () {
        //Arrange
        ProjectCode projectCode = mock(ProjectCode.class);
        when(projectCode.getCode()).thenReturn("Project_2022_1");
        Description description = mock(Description.class);
        when(description.getText()).thenReturn("Sprint 1");
        //Act
        SprintID sprintID = new SprintID(projectCode, description);
        //
        assertTrue(sprintID.toString().equals(sprintID.toString()));
    }

    @DisplayName("Check If ProjectID is correct")
    @Test
    public void getProjectIDSuccess() {
        //Arrange
        ProjectCode projectCode = mock(ProjectCode.class);
        when(projectCode.getCode()).thenReturn("Project_2022_1");
        Description description = mock(Description.class);
        when(description.getText()).thenReturn("Sprint 1");
        //Act
        SprintID sprintID = new SprintID(projectCode, description);
        //Assert
        assertEquals("Project_2022_1", sprintID.getProjectID().getCode());
    }

    @DisplayName("Check If Sprint Name is correct")
    @Test
    public void getSprintNameSuccess() {
        ProjectCode projectCode = mock(ProjectCode.class);
        when(projectCode.getCode()).thenReturn("Project_2022_1");
        Description description = mock(Description.class);
        when(description.getText()).thenReturn("Sprint 1");
        //Act
        SprintID sprintID = new SprintID(projectCode, description);
        //Assert
        assertEquals("Sprint 1", sprintID.getSprintName().getText());
    }
}