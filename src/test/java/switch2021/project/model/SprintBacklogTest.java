package switch2021.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.model.Project.SprintBacklog;
import switch2021.project.model.Project.UserStory;
import switch2021.project.model.Typology.Typology;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SprintBacklogTest {

    SprintBacklog sprintBacklog = new SprintBacklog();

    @Test
    @DisplayName("save user story to sprint backlog fail")
    public void saveStoryToBacklogFail() {
        // Arrange
        int priority = 5;
        String description = "Validate";
        UserStory userstory = new UserStory("US001", priority, description, 5);
        sprintBacklog.saveUserStoryToSprintBacklog(userstory);
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            sprintBacklog.saveUserStoryToSprintBacklog(userstory);
        });
        //Assert
        assertTrue(exception.getMessage().contains("User Story already exists in the sprintbacklog or is null"));
    }

    @Test
    @DisplayName("save user story to sprint backlog null")
    public void saveStoryToBacklogNull() {
        // Arrange
        int priority = 5;
        String description = "Validate";
        UserStory userstory = new UserStory("US001", priority, description, 5);
        sprintBacklog.saveUserStoryToSprintBacklog(userstory);
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            sprintBacklog.saveUserStoryToSprintBacklog(null);
        });
        //Assert
        assertTrue(exception.getMessage().contains("User Story already exists in the sprintbacklog or is null"));
    }

    @Test
    @DisplayName("save user story to sprint backlog null")
    public void getUserStory1() {
        // Arrange
        int priority = 5;
        String description = "Validate";
        UserStory userstory = new UserStory("US001", priority, description, 5);
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
        public void getUserStory2 () {
            // Arrange
            int priority = 5;
            String description = "Validate";
            UserStory userstory = new UserStory("US001", priority, description, 5);
            sprintBacklog.saveUserStoryToSprintBacklog(userstory);
            UserStory userstory2 = new UserStory("US003", priority, description, 5);
            // Act

            assertNotEquals(sprintBacklog.getUserStory(userstory.getIdUserStory()), userstory2);
        }

    @Test
    @DisplayName("save user story to sprint backlog null")
    public void getUserStoryNull() {
        // Arrange
        int priority = 5;
        String description = "Validate";
        UserStory userstory = new UserStory("US001", priority, description, 5);
        sprintBacklog.saveUserStoryToSprintBacklog(userstory);

        // Act
        assertNull(sprintBacklog.getUserStory(55));
    }

    @Test
    @DisplayName("Test override conditions")
    public void overrideTests() {
        // Arrange
        SprintBacklog sprintBacklog = new SprintBacklog();
        SprintBacklog sprintBacklog1 = new SprintBacklog();
        SprintBacklog sprintBacklog2 = null;
        Typology test = new Typology("test");
        // Act
        assertEquals(sprintBacklog,sprintBacklog1);
        assertNotEquals(sprintBacklog, sprintBacklog2);
        assertNotEquals(sprintBacklog, test);
    }

    @Test
    @DisplayName("save user story to sprint backlog with success")
    public void saveUserStorySuccess() {
        // Arrange
        int priority = 5;
        String description = "Validate";
        UserStory userstory = new UserStory("US001", priority, description, 5);
        boolean test = sprintBacklog.saveUserStoryToSprintBacklog(userstory);
        // Act
        assertTrue(test);

    }
}