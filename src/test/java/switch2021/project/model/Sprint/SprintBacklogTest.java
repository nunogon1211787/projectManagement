package switch2021.project.model.Sprint;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.model.UserStory.UserStory;
import switch2021.project.model.valueObject.TaskType;
import switch2021.project.model.valueObject.Typology;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SprintBacklogTest {

    SprintBacklog sprintBacklog = new SprintBacklog();

    @Test
    @DisplayName("save user story to sprint backlog fail")
    public void saveStoryToBacklogFail() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            // Arrange
            int priority = 5;
            String description = "Validate";
            UserStory userstory = new UserStory("US001", priority, description, 5);
            sprintBacklog.saveUserStoryToSprintBacklog(userstory);
            // Act
            sprintBacklog.saveUserStoryToSprintBacklog(userstory);
        });
    }

    @Test
    @DisplayName("save user story to sprint backlog null")
    public void saveStoryToBacklogNull() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            // Arrange
            int priority = 5;
            String description = "Validate";
            UserStory userstory = new UserStory("US001", priority, description, 5);
            sprintBacklog.saveUserStoryToSprintBacklog(userstory);
            // Act
            sprintBacklog.saveUserStoryToSprintBacklog(null);
        });
    }

    @Test
    @DisplayName("save user story to sprint backlog null")
    public void getUserStory1() {
        // Arrange
        int priority = 5;
        String description = "Validate";
        UserStory userstory = new UserStory("As a PO, i want to test this string", priority, description, 5);
        sprintBacklog.saveUserStoryToSprintBacklog(userstory);
        // Act
        assertEquals(sprintBacklog.getUserStory(userstory.getIdUserStory()), userstory);
    }

    @Test
    public void getUserStoryMock() {
        //Arrange
        SprintBacklog sprintBacklog = new SprintBacklog();

        UserStory newUserStory = mock(UserStory.class);
        when(newUserStory.hasCode(1)).thenReturn(true);

        sprintBacklog.saveUserStoryToSprintBacklog(newUserStory);
        //Act
        UserStory test = sprintBacklog.getUserStory(1);
        //Assert
        assertEquals(newUserStory, test);
    }

    @Test
    @DisplayName("save user story to sprint backlog null")
    public void getUserStory2() {
        // Arrange
        int priority = 5;
        String description = "Validate";
        UserStory userstory = new UserStory("As a PO, i want to test this string", priority, description, 5);
        sprintBacklog.saveUserStoryToSprintBacklog(userstory);
        UserStory userstory2 = new UserStory("As a PO, i want to test this string", priority, description, 5);
        // Act
        assertNotSame(sprintBacklog.getUserStory(userstory.getIdUserStory()), userstory2);
    }

    @Test
    @DisplayName("save user story to sprint backlog null")
    public void getUserStoryNull() {
        // Arrange
        int priority = 5;
        String description = "Validate";
        UserStory userstory = new UserStory("As a PO, i want to test this string", priority, description, 5);
        sprintBacklog.saveUserStoryToSprintBacklog(userstory);
        // Act
        assertNull(sprintBacklog.getUserStory(55));
    }

    @Test
    @DisplayName("Test override conditions")
    public void overrideTestTrue() {
        // Arrange
        SprintBacklog sprintBacklog = new SprintBacklog();
        SprintBacklog sprintBacklog1 = new SprintBacklog();
        // Act
        assertEquals(sprintBacklog, sprintBacklog1);
    }

    @Test
    @DisplayName("Test override conditions")
    public void overrideTestTrue_1() {
        // Arrange
        SprintBacklog sprintBacklog = new SprintBacklog();
        SprintBacklog sprintBacklog1 = new SprintBacklog();
        // Act
        assertTrue(sprintBacklog.equals(sprintBacklog1));
    }

    @Test
    @DisplayName("Test override conditions")
    public void overrideTestTrue_2() {
        // Arrange
        SprintBacklog sprintBacklog = new SprintBacklog();
        SprintBacklog sprintBacklog1 = new SprintBacklog();
        // Act
        UserStory userStory = new UserStory("As Product Owner i want to create something", 1, "Userstory", 5);
        sprintBacklog.saveUserStoryToSprintBacklog(userStory);
        //Assert
        assertFalse(sprintBacklog.equals(sprintBacklog1));
    }

    @Test
    @DisplayName("Test to check HashCode Method.")
    void hashCodeTest_Success(){
        //Arrange
        SprintBacklog sprintBacklog = new SprintBacklog();
        SprintBacklog sprintBacklog1 = new SprintBacklog();
        //Act
        Object x = sprintBacklog1.hashCode();
        Object y = sprintBacklog.hashCode();
        //Assert
        assertEquals(x,y);
    }

    @Test
    @DisplayName("Test to check HashCode Method.")
    void hashCodeTest_Success_1(){
        //Arrange
        SprintBacklog sprintBacklog = new SprintBacklog();
        SprintBacklog sprintBacklog1 = new SprintBacklog();
        //Act and Assert
        assertEquals(sprintBacklog.getUserStoryList(),sprintBacklog1.getUserStoryList());
    }

    @Test
    @DisplayName("Test to check HashCode Method.")
    void hashCodeTest_Success_List(){
        //Arrange
        SprintBacklog sprintBacklog = new SprintBacklog();
        SprintBacklog sprintBacklog1 = new SprintBacklog();
        //Act and Assert
        assertEquals(sprintBacklog.getUserStoryList().hashCode(),sprintBacklog1.getUserStoryList().hashCode());
    }

    @Test
    @DisplayName("Test to check HashCode Method.")
    void hashCodeTest_Fail_List(){
        //Arrange
        SprintBacklog sprintBacklog = new SprintBacklog();
        SprintBacklog sprintBacklog1 = new SprintBacklog();
        UserStory userStory = new UserStory("As Product Owner i want to create something", 1, "Userstory", 5);
        //Act
        sprintBacklog.saveUserStoryToSprintBacklog(userStory);
        //Assert
        assertNotEquals(sprintBacklog.getUserStoryList().hashCode(),sprintBacklog1.getUserStoryList().hashCode());
    }


    @Test
    @DisplayName("Test to check HashCode Method.")
    void hashCodeTest_Success_2(){
        //Arrange
        SprintBacklog sprintBacklog = new SprintBacklog();
        SprintBacklog sprintBacklog1 = new SprintBacklog();
        //Act and Assert
        assertEquals(sprintBacklog.getUserStoryList().size(),sprintBacklog1.getUserStoryList().size());
    }

    @Test
    @DisplayName("Test override conditions")
    public void overrideTestFalse() {
        // Arrange
        SprintBacklog sprintBacklog = new SprintBacklog();
        Typology test = new Typology("test");
        // Act
        assertNotEquals(sprintBacklog, test);
    }

    @Test
    @DisplayName("Test override conditions")
    public void overrideTestNull() {
        // Arrange
        SprintBacklog sprintBacklog = new SprintBacklog();
        // Act
        assertNotEquals(null, sprintBacklog);
    }

    @Test
    @DisplayName("save user story to sprint backlog with success")
    public void saveUserStorySuccess() {
        // Arrange
        int priority = 5;
        String description = "Validate";
        UserStory userstory = new UserStory("As a PO, i want to test this string", priority, description, 5);
        boolean test = sprintBacklog.saveUserStoryToSprintBacklog(userstory);
        // Act
        assertTrue(test);
    }
}