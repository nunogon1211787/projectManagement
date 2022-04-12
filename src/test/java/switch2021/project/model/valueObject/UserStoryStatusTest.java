package switch2021.project.model.valueObject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.model.Typology.Typology;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserStoryStatusTest {

    @Test
    void hasDescriptionSeccess() {
        //Arrange
        UserStoryStatus status = new UserStoryStatus("To do", true);
        String statusName = "To do";
        //Assert
        assertTrue(status.hasDescription(statusName));
    }

    @Test
    void hasDescriptionFail() {
        //Arrange
        UserStoryStatus status = new UserStoryStatus("To do", true);
        String statusName = "Test";
        //Assert
        assertFalse(status.hasDescription(statusName));
    }

    @Test
    void hasDescriptionNull() {
        //Arrange
        UserStoryStatus status = new UserStoryStatus("To do", true);
        //Assert
        assertFalse(status.hasDescription(null));
    }

    @Test
    void getDescriptionTest() {
        //Arrange
        UserStoryStatus status1 = new UserStoryStatus("test1", true);
        Description des = mock(Description.class);
        when(des.getText()).thenReturn("test1");
        //Act
        Description desSt1 = status1.getDescription();
        //Assert
        assertNotEquals(desSt1, null);
    }

    @Test
    void getSprintAvailable() {
        //Arrange
        UserStoryStatus status1 = new UserStoryStatus("test1", true);
        //Assert
        assertTrue(status1.isSprintAvailable());
    }

    @Test
    void getSprintAvailableFalse() {
        //Arrange
        UserStoryStatus status1 = new UserStoryStatus("test1", false);
        //Assert
        assertNotEquals(true, status1.isSprintAvailable());
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTestTrue() {
        // Arrange
        UserStoryStatus status = new UserStoryStatus("Test", true);
        UserStoryStatus test = new UserStoryStatus("Test", true);
        // Act
        assertEquals(status, test);
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTestFalseBoolean() {
        // Arrange
        UserStoryStatus status = new UserStoryStatus("Test", true);
        UserStoryStatus test = new UserStoryStatus("Test", false);
        // Act
        assertNotEquals(status, test);
    }

//    @Test //TODO CDC - teste comentado vai deixar de ter status
//    @DisplayName("Test override conditions for coverage purposes")
//    public void overrideTestFalseDescription() {
//        // Arrange
//        UserStoryStatus status = new UserStoryStatus("Test", true);
//        UserStoryStatus testFalse = new UserStoryStatus("TestFalse", true);
//        // Act
//        assertNotEquals(status, testFalse);
//    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTestNull() {
        // Arrange
        UserStoryStatus status = new UserStoryStatus("Test", true);
        // Act
        assertNotEquals(status, null);
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTestClassFail() {
        // Arrange
        UserStoryStatus status = new UserStoryStatus("Test", true);
        Typology test = mock(Typology.class);
        // Act
        assertNotEquals(status, test);
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTestClassTrue() {
        // Arrange
        UserStoryStatus status = new UserStoryStatus("Test", true);
        UserStoryStatus test = new UserStoryStatus("Test", true);
        // Act
        assertEquals(status.getClass(), test.getClass());
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void hashCodeSucess() {
        //Arrange
        UserStoryStatus status = new UserStoryStatus("Test", true);
        UserStoryStatus test = new UserStoryStatus("Test", true);
        //Assert
        assertEquals(status.hashCode(), test.hashCode());
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void hashCodeFailDescription() {
        //Arrange
        UserStoryStatus status = new UserStoryStatus("Test", true);
        UserStoryStatus test = new UserStoryStatus("Test1", true);
        //Assert
        assertNotEquals(status.hashCode(), test.hashCode());
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void hashCodeFailBoolean() {
        //Arrange
        UserStoryStatus status = new UserStoryStatus("Test", true);
        UserStoryStatus test = new UserStoryStatus("Test", false);
        //Assert
        assertNotEquals(status.hashCode(), test.hashCode());
    }
}