package switch2021.project.model;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class ProductBacklogTest {


    // Test adding userStory to the project (Cris US009)

    @Test
    public void createUserStoryPriorityIsInvalid() {
        //Arrange
        ProductBacklog productBacklog = new ProductBacklog();
        UserStoryStatus status = UserStoryStatus.TODO;
        int priority = -1;
        String description = "teste";
        int timeEstimate = 1;
        String code = "123";
        // Act
        UserStory userStory = productBacklog.createUserStory(code, status, priority, description, timeEstimate);
        //Assert
        assertNull(userStory);
    }

    @Test
    public void createUserStoryUserStoryAlreadyExist() {
        //Arrange
        ProductBacklog productBacklog = new ProductBacklog();
        String code = "123d";
        UserStoryStatus status = UserStoryStatus.TODO;
        int priority = 1;
        String description = "teste";
        int timeEstimate = 7;
        UserStory userStory1 = productBacklog.createUserStory(code, status, priority, description, timeEstimate);
        productBacklog.addUserStory(userStory1);
        // Act
        UserStory userStory2 = productBacklog.createUserStory(code, UserStoryStatus.IN_TEST, 1, description, 5);
        //Assert
        assertNull(userStory2);
    }

    @Test
    public void createUserStoryTimeEstimateInvalid() {
        //Arrange
        ProductBacklog productBacklog = new ProductBacklog();
        String code = "123d";
        UserStoryStatus status = UserStoryStatus.TODO;
        int priority = 1;
        String description = "teste";
        int timeEstimate = -1;
        // Act
        UserStory userStory = productBacklog.createUserStory(code, status, priority, description, timeEstimate);
        //Assert
        assertNull(userStory);
    }

    @Test
    public void createUserStoryDescriptionInvalid() {
        //Arrange
        ProductBacklog productBacklog = new ProductBacklog();
        String code = "123d";
        UserStoryStatus status = UserStoryStatus.TODO;
        int priority = 1;
        String description = null;
        int timeEstimate = 1;
        // Act
        UserStory userStory = productBacklog.createUserStory(code, status, priority, description, timeEstimate);
        //Assert
        assertNull(userStory);
    }

    @Test
    public void createUserStoryProjectCodeInvalid() {
        //Arrange
        ProductBacklog productBacklog = new ProductBacklog();
        UserStoryStatus status = UserStoryStatus.TODO;
        int priority = 1;
        String description = "description";
        int timeEstimate = 1;
        // Act
        UserStory userStory = productBacklog.createUserStory("", status, priority, description, timeEstimate);
        //Assert
        assertNull(userStory);
    }

    @Test
    public void createUserStoryWithSuccess() {
        //Arrange
        ProductBacklog productBacklog = new ProductBacklog();
        String code = "123d";
        UserStoryStatus status = UserStoryStatus.TODO;
        int priority = 1;
        String description = "teste";
        int timeEstimate = 7;
        // Act
        UserStory userStory = productBacklog.createUserStory(code, status, priority, description, timeEstimate);
        //Assert
        assertNotNull(userStory);
        assertEquals(code, userStory.getProjectCode());
        assertEquals(status, userStory.getUserStoryStatus());
        assertEquals(priority, userStory.getPriority());
        assertEquals(description, userStory.getDescription());
        assertEquals(timeEstimate, userStory.getTimeEstimate());
    }


}
