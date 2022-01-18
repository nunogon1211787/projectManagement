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
        int timeEstimate = 7;
        String code = "123";

        // Act
        UserStory userStory = productBacklog.createUserStory(
                status, priority, description, timeEstimate);
        // Assert
        assertNotNull(userStory);
//        assertEquals(code, userStory.getProjectCode());
        assertEquals(status, userStory.getUserStoryStatus());
        assertEquals(priority, userStory.getPriority());
        assertEquals(description, userStory.getDescription());
        assertEquals(timeEstimate, userStory.getTimeEstimate());
    }

    @Test
    public void createUserStoryTimeEstimateInvalid() {
        // Arrange
        ProductBacklog productBacklog = new ProductBacklog();
        UserStoryStatus status = new UserStoryStatus("In progress");
        int priority = 1;
        String description = "Default Story";
        int timeEstimate = -7;
        String code = "123";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            UserStory userStory = productBacklog.createUserStory(
                    status, priority, description, timeEstimate);
        });
        // Assert
        assertTrue(exception.getMessage().contains("Check time estimate, cannot be < 0."));
    }

    @Test
    public void createUserStorydescriptionInvalidEmpty() {
        // Arrange
        ProductBacklog productBacklog = new ProductBacklog();
        UserStoryStatus status = new UserStoryStatus("In progress");
        int priority = 1;
        String description = "";
        int timeEstimate = 7;
        String code = "123";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            UserStory userStory = productBacklog.createUserStory(
                    status, priority, description, timeEstimate);
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
        int timeEstimate = 7;
        String code = "123";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            UserStory userStory = productBacklog.createUserStory(
                    status, priority, description, timeEstimate);
        });
        // Assert
        assertTrue(exception.getMessage().contains("Description must be at least 5 characters"));
    }

    @Test
    public void createUserStoryPriorityInvalid() {
        // Arrange
        ProductBacklog productBacklog = new ProductBacklog();
        UserStoryStatus status = new UserStoryStatus("In progress");
        int priority = -1;
        String description = "Create user story";
        int timeEstimate = 7;
        String code = "123";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            UserStory userStory = productBacklog.createUserStory(
                    status, priority, description, timeEstimate);
        });
        // Assert
        assertTrue(exception.getMessage().contains("Check priority, cannot be < 0."));
    }

    @Test
    public void createUserStoryAlreadyExist() {
        // Arrange
        ProductBacklog productBacklog = new ProductBacklog();
        UserStoryStatus status = new UserStoryStatus("In progress");
        int priority = 1;
        String description = "Create user story";
        int timeEstimate = 7;
        String code = "123";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            UserStory userStory = productBacklog.createUserStory(
                    status, priority, description, timeEstimate);
            productBacklog.saveUserStory(userStory);
            UserStory userStory2 = productBacklog.createUserStory(
                    status, priority, description, timeEstimate);
            productBacklog.saveUserStory(userStory2);
        });
        // Assert
        assertTrue(exception.getMessage().contains("Repeated user story inserted, same code project and description."));
    }

//    @Test
//    public void createUserStoryCodeProjectInvalidBlank() {
//        // Arrange
//        ProductBacklog productBacklog = new ProductBacklog();
//        UserStoryStatus status = new UserStoryStatus("In progress");
//        int priority = 1;
//        String description = "Create user story";
//        int timeEstimate = 7;
//        String code = "";
//
//        // Act
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//            UserStory userStory = productBacklog.createUserStory(
//                    status, priority, description, timeEstimate);
//        });
//        // Assert
//        assertTrue(exception.getMessage().contains("Project does not exist."));
//    }

    @Test
    public void saveNewUserStoryAlreadyExist() {
        // Arrange
        ProductBacklog productBacklog = new ProductBacklog();
        UserStoryStatus status = new UserStoryStatus("In progress");
        int priority = 1;
        String description = "Create user story";
        int timeEstimate = 7;
        String code = "123";
        UserStory userStory = productBacklog.createUserStory(
                status, priority, description, timeEstimate);
        productBacklog.saveUserStory(userStory);
        UserStory userStory2 = productBacklog.createUserStory(
                status, priority, description, timeEstimate);
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
        int timeEstimate = 7;
        String code = "123";
        UserStory userStory = productBacklog.createUserStory(
                status, priority, description, timeEstimate);
        // Act
        productBacklog.saveUserStory(userStory);
        // Assert
        assertNotNull(userStory);
//        assertEquals(code, userStory.getProjectCode());
        assertEquals(status, userStory.getUserStoryStatus());
        assertEquals(priority, userStory.getPriority());
        assertEquals(description, userStory.getDescription());
        assertEquals(timeEstimate, userStory.getTimeEstimate());
    }

}
