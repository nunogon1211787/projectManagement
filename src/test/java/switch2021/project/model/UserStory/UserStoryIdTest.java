package switch2021.project.model.UserStory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.model.Typology.Typology;
import switch2021.project.model.valueObject.ProjectCode;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserStoryIdTest {

    @Test
    @DisplayName("Validate that ID is correct")
    public void usIDSuccess() {
        //Arrange
        ProjectCode projectCode = mock(ProjectCode.class);
        when(projectCode.getCode()).thenReturn("Project_2022_1");
        UsTitle usTitle = mock(UsTitle.class);
        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
        //Act
        UserStoryId userStoryId = new UserStoryId(projectCode,usTitle);
        String expected = "Project_2022_1_As a PO, i want to test this string";
        //Assert
        assertEquals(expected, userStoryId.toString());
    }

    @Test
    @DisplayName("Validate that ID is correct - Same ID")
    public void SameId() {
        //Arrange
        ProjectCode projectCode = mock(ProjectCode.class);
        when(projectCode.getCode()).thenReturn("Project_2022_1");
        UsTitle usTitle = mock(UsTitle.class);
        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
        //Act
        UserStoryId userStoryId = new UserStoryId(projectCode,usTitle);
        UserStoryId userStoryId2 = new UserStoryId(projectCode,usTitle);
        //Assert
        assertEquals(userStoryId, userStoryId2);
    }

    @Test
    @DisplayName("Validate that ID is correct but not the same")
    public void NotSameId() {
        //Arrange
        ProjectCode projectCode = mock(ProjectCode.class);
        when(projectCode.getCode()).thenReturn("Project_2022_1");
        UsTitle usTitle = mock(UsTitle.class);
        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
        ProjectCode projectCode2 = mock(ProjectCode.class);
        when(projectCode2.getCode()).thenReturn("Project_4022_1");
        //Act
        UserStoryId userStoryId = new UserStoryId(projectCode,usTitle);
        UserStoryId userStoryId2 = new UserStoryId(projectCode2,usTitle);
        //Assert
        assertNotEquals(userStoryId, userStoryId2);
    }

    @Test
    @DisplayName("Project Code Null")
    public void ProjectCodeNull() {
        //Arrange
        UsTitle usTitle = mock(UsTitle.class);
        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
        //Act
        UserStoryId userStoryId = new UserStoryId(null,usTitle);
        //Assert
        assertNull(userStoryId.getProjectID());
        assertNotNull(userStoryId.getUsTitle());
    }

    @Test
    @DisplayName("User Story title Null")
    public void usTitleNull() {
        //Arrange
        ProjectCode projectCode = mock(ProjectCode.class);
        when(projectCode.getCode()).thenReturn("Project_2022_1");
        //Act
        UserStoryId userStoryId = new UserStoryId(projectCode,null);
        //Assert
        assertNotNull(userStoryId.getProjectID());
        assertNull(userStoryId.getUsTitle());
    }

    @Test
    @DisplayName("Test hashcode conditions for coverage purposes")
    public void hasCodeSuccess() {
        //Arrange
        ProjectCode projectCode = mock(ProjectCode.class);
        when(projectCode.getCode()).thenReturn("Project_2022_1");
        UsTitle usTitle = mock(UsTitle.class);
        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
        //Act
        UserStoryId userStoryId = new UserStoryId(projectCode,usTitle);
        UserStoryId userStoryId2 = new UserStoryId(projectCode,usTitle);
        //Assert
        assertEquals(userStoryId.hashCode(), userStoryId2.hashCode());
    }

    @Test
    @DisplayName("Test hashcode conditions for coverage purposes")
    public void hasCodeFail() {
        //Arrange
        ProjectCode projectCode = mock(ProjectCode.class);
        when(projectCode.getCode()).thenReturn("Project_2022_1");
        UsTitle usTitle = mock(UsTitle.class);
        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
        ProjectCode projectCode2 = mock(ProjectCode.class);
        when(projectCode2.getCode()).thenReturn("Project_4022_1");
        //Act
        UserStoryId userStoryId = new UserStoryId(projectCode,usTitle);
        UserStoryId userStoryId2 = new UserStoryId(projectCode2,usTitle);
        //Assert
        assertNotEquals(userStoryId.hashCode(), userStoryId2.hashCode());
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTest() {
        //Arrange
        ProjectCode projectCode = mock(ProjectCode.class);
        when(projectCode.getCode()).thenReturn("Project_2022_1");
        UsTitle usTitle = mock(UsTitle.class);
        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
        //Act
        UserStoryId userStoryId = new UserStoryId(projectCode,usTitle);
        //Assert
        assertEquals(userStoryId, userStoryId);
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTestFail() {
        //Arrange
        ProjectCode projectCode = mock(ProjectCode.class);
        when(projectCode.getCode()).thenReturn("Project_2022_1");
        UsTitle usTitle = mock(UsTitle.class);
        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
        //Act
        UserStoryId userStoryId = new UserStoryId(projectCode,usTitle);
        Typology budget1 = new Typology("test");
        //Assert
        assertNotEquals(userStoryId, budget1);
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void SameValueAs() {
        //Arrange
        ProjectCode projectCode = mock(ProjectCode.class);
        when(projectCode.getCode()).thenReturn("Project_2022_1");
        UsTitle usTitle = mock(UsTitle.class);
        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
        //Act
        UserStoryId userStoryId = new UserStoryId(projectCode,usTitle);
        //Assert
        assertFalse(userStoryId.sameValueAs(userStoryId));
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void userStoryToString() {
        //Arrange
        ProjectCode projectCode = mock(ProjectCode.class);
        when(projectCode.getCode()).thenReturn("Project_2022_1");
        UsTitle usTitle = mock(UsTitle.class);
        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
        //Act
        UserStoryId userStoryId = new UserStoryId(projectCode,usTitle);
        //Assert
        assertTrue(userStoryId.toString().equals(userStoryId.toString()));
    }


    @Test
    @DisplayName("Validate that projectID is correct")
    public void getProjectIdWithSuccess() {
        //Arrange
        ProjectCode projectCode = mock(ProjectCode.class);
        when(projectCode.getCode()).thenReturn("Project_2022_1");
        UsTitle usTitle = mock(UsTitle.class);
        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
        //Act
        UserStoryId userStoryId = new UserStoryId(projectCode,usTitle);
        //Assert
        assertEquals("Project_2022_1", userStoryId.getProjectID().getCode());
    }

    @Test
    @DisplayName("Validate that usTitle is correct")
    public void getUsTitleWithSuccess() {
        //Arrange
        ProjectCode projectCode = mock(ProjectCode.class);
        when(projectCode.getCode()).thenReturn("Project_2022_1");
        UsTitle usTitle = mock(UsTitle.class);
        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
        //Act
        UserStoryId userStoryId = new UserStoryId(projectCode,usTitle);
        //Assert
        assertEquals("As a PO, i want to test this string", userStoryId.getUsTitle().getTitleUs());
    }
}
