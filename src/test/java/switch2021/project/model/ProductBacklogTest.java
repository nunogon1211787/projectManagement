package switch2021.project.model;

import org.junit.jupiter.api.Test;


import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ProductBacklogTest {
    Project project;

    @Test
    public void createUserStorySuccessFull() {
        // Arrange
        ProductBacklog productBacklog = new ProductBacklog();
        UserStoryStatus status = new UserStoryStatus("In progress");
        int priority = 1;
        String description = "Default Story";

        // Act
        UserStory userStory = productBacklog.createUserStory(
                status, priority, description);
        // Assert
        assertNotNull(userStory);
        assertEquals(status, userStory.getUserStoryStatus());
        assertEquals(priority, userStory.getPriority());
        assertEquals(description, userStory.getDescription());
    }


    @Test
    public void createUserStorydescriptionInvalidEmpty() {
        // Arrange
        ProductBacklog productBacklog = new ProductBacklog();
        UserStoryStatus status = new UserStoryStatus("In progress");
        int priority = 1;
        String description = "";


        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            UserStory userStory = productBacklog.createUserStory(
                    status, priority, description);
        });
        // Assert
        assertTrue(exception.getMessage().contains("Description cannot be blank."));
    }


    @Test
    public void createUserStorydescriptionInvalidShort() {
        // Arrange
        ProductBacklog productBacklog = new ProductBacklog();
        UserStoryStatus status = new UserStoryStatus("In progress");
        int priority = 1;
        String description = "dd";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            UserStory userStory = productBacklog.createUserStory(
                    status, priority, description);
        });
        // Assert
        assertTrue(exception.getMessage().contains("Description must be at least 5 characters"));
    }

    @Test
    public void createUserStoryPriorityInvalidInf() {
        // Arrange
        ProductBacklog productBacklog = new ProductBacklog();
        UserStoryStatus status = new UserStoryStatus("In progress");
        int priority = -1;
        String description = "Create user story";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            UserStory userStory = productBacklog.createUserStory(
                    status, priority, description);
        });
        // Assert
        assertTrue(exception.getMessage().contains("Check priority, cannot be < 0 or superior to 5."));
    }

    @Test
    public void createUserStoryPriorityInvalidSup() {
        // Arrange
        ProductBacklog productBacklog = new ProductBacklog();
        UserStoryStatus status = new UserStoryStatus("In progress");
        int priority = 6;
        String description = "Create user story";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            UserStory userStory = productBacklog.createUserStory(
                    status, priority, description);
        });
        // Assert
        assertTrue(exception.getMessage().contains("Check priority, cannot be < 0 or superior to 5."));
    }


    @Test
    public void createUserStoryAlreadyExist() {
        // Arrange
        ProductBacklog productBacklog = new ProductBacklog();
        UserStoryStatus status = new UserStoryStatus("In progress");
        int priority = 1;
        String description = "Create user story";


        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            UserStory userStory = productBacklog.createUserStory(
                    status, priority, description);
            productBacklog.saveUserStory(userStory);
            UserStory userStory2 = productBacklog.createUserStory(
                    status, priority, description);
            productBacklog.saveUserStory(userStory2);
        });
        // Assert
        assertTrue(exception.getMessage().contains("Repeated user story inserted, same code project and description."));
    }


    @Test
    public void saveNewUserStoryAlreadyExist() {
        // Arrange
        ProductBacklog productBacklog = new ProductBacklog();
        UserStoryStatus status = new UserStoryStatus("In progress");
        int priority = 1;
        String description = "Create user story";

        UserStory userStory = productBacklog.createUserStory(
                status, priority, description);
        productBacklog.saveUserStory(userStory);
        UserStory userStory2 = productBacklog.createUserStory(
                status, priority, description);
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            productBacklog.saveUserStory(userStory2);

        });
        // Assert
        assertTrue(exception.getMessage().contains("Repeated user story inserted, same code project and description."));
    }

    @Test
    public void saveNewUserStoryWithSuccess() {
        // Arrange
        ProductBacklog productBacklog = new ProductBacklog();
        UserStoryStatus status = new UserStoryStatus("In progress");
        int priority = 1;
        String description = "Create user story";

        UserStory userStory = productBacklog.createUserStory(
                status, priority, description);
        // Act
        productBacklog.saveUserStory(userStory);
        // Assert
        assertNotNull(userStory);
        assertEquals(status, userStory.getUserStoryStatus());
        assertEquals(priority, userStory.getPriority());
        assertEquals(description, userStory.getDescription());
    }

}
