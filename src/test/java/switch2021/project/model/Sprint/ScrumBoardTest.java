package switch2021.project.model.Sprint;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.model.UserStory.UserStory;
import switch2021.project.model.Typology.Typology;
import switch2021.project.model.UserStory.UserStoryID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ScrumBoardTest {

    ScrumBoard scrumBoard = new ScrumBoard();

    @Test
    @DisplayName("save user story to sprint backlog fail")
    public void saveStoryToBacklogFail() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            // Arrange
            int priority = 5;
            String description = "Validate";
            String projectID = "Project_2022_1";
            UserStory userstory = new UserStory(projectID, "Project_2022_1_As a PO, i want to test this string", "US001", priority, description, 5);
            scrumBoard.saveUserStoryToSprintBacklog(userstory);
            // Act
            scrumBoard.saveUserStoryToSprintBacklog(userstory);
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
            String userStoryId = "Project_2022_1_As a PO, i want to test this string";
            String projectID = "Project_2022_1";
            UserStory userstory = new UserStory(projectID, userStoryId, "US001", priority, description, 5);
            scrumBoard.saveUserStoryToSprintBacklog(userstory);
            // Act
            scrumBoard.saveUserStoryToSprintBacklog(null);
        });
    }

    @Test
    @DisplayName("save user story to sprint backlog null")
    public void getUserStory1() {
        // Arrange
        int priority = 5;
        String description = "Validate";
        String userStoryId = "Project_2022_1_As a PO, i want to test this string";
        String projectID = "Project_2022_1";
        UserStory userstory = new UserStory(projectID, userStoryId, "As a PO, i want to test this string", priority, description, 5);
        scrumBoard.saveUserStoryToSprintBacklog(userstory);
        // Act
        assertEquals(scrumBoard.getUserStory(userstory.getUserStoryId()), userstory);
    }

    @Test
    public void getUserStoryMock() {
        //Arrange
        ScrumBoard scrumBoard = new ScrumBoard();

        UserStory newUserStory = mock(UserStory.class);
        when(newUserStory.hasCode(new UserStoryID("Project_2022_1_As a PO, i want to test this string"))).thenReturn(true);

        scrumBoard.saveUserStoryToSprintBacklog(newUserStory);
        //Act
        UserStory test = scrumBoard.getUserStory(new UserStoryID("Project_2022_1_As a PO, i want to test this string"));
        //Assert
        assertEquals(newUserStory, test);
    }

    @Test
    @DisplayName("save user story to sprint backlog null")
    public void getUserStory2() {
        // Arrange
        int priority = 5;
        String description = "Validate";
        String userStoryId = "Project_2022_1_As a PO, i want to test this string";
        String projectID = "Project_2022_1";
        UserStory userstory = new UserStory(projectID, userStoryId, "As a PO, i want to test this string", priority, description, 5);
        scrumBoard.saveUserStoryToSprintBacklog(userstory);
        UserStory userstory2 = new UserStory(projectID, userStoryId, "As a PO, i want to test this string", priority, description, 5);
        // Act
        assertNotSame(scrumBoard.getUserStory(userstory.getUserStoryId()), userstory2);
    }

    @Test
    @DisplayName("save user story to sprint backlog null")
    public void getUserStoryNull() {
        // Arrange
        int priority = 5;
        String description = "Validate";
        String userStoryId = "Project_2022_1_As a PO, i want to test this string";
        String projectID = "Project_2022_1";
        UserStory userstory = new UserStory(projectID, userStoryId, "As a PO, i want to test this string", priority, description, 5);
        scrumBoard.saveUserStoryToSprintBacklog(userstory);
        // Act
        assertNull(scrumBoard.getUserStory(null));
    }

    @Test
    @DisplayName("Test override conditions")
    public void overrideTestTrue() {
        // Arrange
        ScrumBoard scrumBoard = new ScrumBoard();
        ScrumBoard scrumBoard1 = new ScrumBoard();
        // Act
        assertEquals(scrumBoard, scrumBoard1);
    }

    @Test
    @DisplayName("Test override conditions")
    public void overrideTestTrue_1() {
        // Arrange
        ScrumBoard scrumBoard = new ScrumBoard();
        ScrumBoard scrumBoard1 = new ScrumBoard();
        // Act
        assertTrue(scrumBoard.equals(scrumBoard1));
    }

    @Test
    @DisplayName("Test override conditions")
    public void overrideTestTrue_2() {
        // Arrange
        ScrumBoard scrumBoard = new ScrumBoard();
        ScrumBoard scrumBoard1 = new ScrumBoard();
        String userStoryId = "Project_2022_1_As a PO, i want to test this string";
        String projectID = "Project_2022_1";
        // Act
        UserStory userStory = new UserStory(projectID, userStoryId, "As Product Owner i want to create something", 1, "Userstory", 5);
        scrumBoard.saveUserStoryToSprintBacklog(userStory);
        //Assert
        assertFalse(scrumBoard.equals(scrumBoard1));
    }

    @Test
    @DisplayName("Test to check HashCode Method.")
    void hashCodeTest_Success() {
        //Arrange
        ScrumBoard scrumBoard = new ScrumBoard();
        ScrumBoard scrumBoard1 = new ScrumBoard();
        //Act
        Object x = scrumBoard1.hashCode();
        Object y = scrumBoard.hashCode();
        //Assert
        assertEquals(x, y);
    }

    @Test
    @DisplayName("Test to check HashCode Method.")
    void hashCodeTest_Success_1() {
        //Arrange
        ScrumBoard scrumBoard = new ScrumBoard();
        ScrumBoard scrumBoard1 = new ScrumBoard();
        //Act and Assert
        assertEquals(scrumBoard.getUserStoryList(), scrumBoard1.getUserStoryList());
    }

    @Test
    @DisplayName("Test to check HashCode Method.")
    void hashCodeTest_Success_List() {
        //Arrange
        ScrumBoard scrumBoard = new ScrumBoard();
        ScrumBoard scrumBoard1 = new ScrumBoard();
        //Act and Assert
        assertEquals(scrumBoard.getUserStoryList().hashCode(), scrumBoard1.getUserStoryList().hashCode());
    }

    @Test
    @DisplayName("Test to check HashCode Method.")
    void hashCodeTest_Fail_List() {
        //Arrange
        ScrumBoard scrumBoard = new ScrumBoard();
        ScrumBoard scrumBoard1 = new ScrumBoard();
        String userStoryId = "Project_2022_1_As a PO, i want to test this string";
        String projectID = "Project_2022_1";
        UserStory userStory = new UserStory(projectID, userStoryId, "As Product Owner i want to create something", 1, "Userstory", 5);
        //Act
        scrumBoard.saveUserStoryToSprintBacklog(userStory);
        //Assert
        assertNotEquals(scrumBoard.getUserStoryList().hashCode(), scrumBoard1.getUserStoryList().hashCode());
    }


    @Test
    @DisplayName("Test to check HashCode Method.")
    void hashCodeTest_Success_2() {
        //Arrange
        ScrumBoard scrumBoard = new ScrumBoard();
        ScrumBoard scrumBoard1 = new ScrumBoard();
        //Act and Assert
        assertEquals(scrumBoard.getUserStoryList().size(), scrumBoard1.getUserStoryList().size());
    }

    @Test
    @DisplayName("Test override conditions")
    public void overrideTestFalse() {
        // Arrange
        ScrumBoard scrumBoard = new ScrumBoard();
        Typology test = new Typology("test");
        // Act
        assertNotEquals(scrumBoard, test);
    }

    @Test
    @DisplayName("Test override conditions")
    public void overrideTestNull() {
        // Arrange
        ScrumBoard scrumBoard = new ScrumBoard();
        // Act
        assertNotEquals(null, scrumBoard);
    }

    @Test
    @DisplayName("save user story to sprint backlog with success")
    public void saveUserStorySuccess() {
        // Arrange
        int priority = 5;
        String description = "Validate";
        String userStoryId = "Project_2022_1_As a PO, i want to test this string";
        String projectID = "Project_2022_1";
        UserStory userstory = new UserStory(projectID, userStoryId, "As a PO, i want to test this string", priority, description, 5);
        boolean test = scrumBoard.saveUserStoryToSprintBacklog(userstory);
        // Act
        assertTrue(test);
    }
}