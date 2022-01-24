package switch2021.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.List;

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

    @Test
    public void addNewUserStoryAlreadyExist() {
        // Arrange
        ProductBacklog productBacklog = new ProductBacklog();
        UserStoryStatus status = new UserStoryStatus("In progress");
        int priority = 1;
        String description = "Create user story";

        UserStory userStory = productBacklog.createUserStory(
                status, priority, description);
        productBacklog.addUserStory(userStory);
        productBacklog.addUserStory(userStory);
        UserStory userStory2 = productBacklog.createUserStory(
                status, priority, description);
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            productBacklog.addUserStory(userStory2);
            productBacklog.saveUserStory(userStory2);

        });
        // Assert
        assertTrue(exception.getMessage().contains("Repeated user story inserted, same code project and description."));
    }

    @Test
    public void addNewUserStoryWithSuccess() {
        // Arrange
        ProductBacklog productBacklog = new ProductBacklog();
        UserStoryStatus status = new UserStoryStatus("In progress");
        int priority = 1;
        String description = "Create user story";

        UserStory userStory = productBacklog.createUserStory(
                status, priority, description);
        // Act
        productBacklog.addUserStory(userStory);
        // Assert
        assertNotNull(userStory);
        assertEquals(status, userStory.getUserStoryStatus());
        assertEquals(priority, userStory.getPriority());
        assertEquals(description, userStory.getDescription());
    }

    @Test
    @DisplayName("grants a list of US that is sorted by priority. It keeps the done and/or cancelled US on the end\n")
    public void getSortedListWithSuccess(){
        // Arrange
        ProductBacklog productBacklog=new ProductBacklog();
        UserStory userStory =productBacklog.createUserStory(1,"create user story");
        productBacklog.saveUserStory(userStory);
        UserStory userStory1 =productBacklog.createUserStory(3,"sort user story");
        productBacklog.saveUserStory(userStory1);
        UserStory userStory2 =productBacklog.createUserStory(new UserStoryStatus("In progress"),2,"backlog sorted");
        productBacklog.saveUserStory(userStory2);
        UserStory userStory3 =productBacklog.createUserStory(new UserStoryStatus("In progress"),5,"show sorted");
        productBacklog.saveUserStory(userStory3);

        // Act
        List<UserStory> userStoryList =  productBacklog.getUsSortedByPriority();

        // Assert
        assertEquals(4, userStoryList.size());

        assertEquals(1, userStoryList.get(0).getPriority());
        assertEquals(2, userStoryList.get(1).getPriority());
        assertEquals(3, userStoryList.get(2).getPriority());
        assertEquals(5, userStoryList.get(3).getPriority());

    }

    @Test
    @DisplayName("garantes a list of US is sorted by priority, mantain for the last US done and/or even cancelled")
    public void getSortedListWithSuccess2(){
        // Arrange
        ProductBacklog productBacklog=new ProductBacklog();
        UserStory userStory =productBacklog.createUserStory(new UserStoryStatus("Done"), 1,"create user story");
        productBacklog.saveUserStory(userStory);
        UserStory userStory1 =productBacklog.createUserStory(new UserStoryStatus("Cancelled"), 3,"sort user story");
        productBacklog.saveUserStory(userStory1);
        UserStory userStory2 =productBacklog.createUserStory(new UserStoryStatus("To do"),2,"backlog sorted");
        productBacklog.saveUserStory(userStory2);
        UserStory userStory3 =productBacklog.createUserStory(new UserStoryStatus("In progress"),5,"show sorted");
        productBacklog.saveUserStory(userStory3);

        // Act
        List<UserStory> userStoryList =  productBacklog.getUsSortedByPriority();

        // Assert
        assertEquals(4, userStoryList.size());

        assertEquals(2, userStoryList.get(0).getPriority());
        assertEquals(5, userStoryList.get(1).getPriority());
        assertEquals(1, userStoryList.get(2).getPriority());
        assertEquals(3, userStoryList.get(3).getPriority());

    }

    @Test
    @DisplayName("get exception message \"Check priority, cannot be < 0 or superior to 5.“")
    public void getSortedListFailWrongPriority(){
        // Arrange
        ProductBacklog productBacklog=new ProductBacklog();
        UserStory userStory =productBacklog.createUserStory(new UserStoryStatus("In progress"),1,"create user story");
        productBacklog.saveUserStory(userStory);
        UserStory userStory2 =productBacklog.createUserStory(new UserStoryStatus("In progress"),2,"backlog sorted");
        productBacklog.saveUserStory(userStory2);
        UserStory userStory3 =productBacklog.createUserStory(new UserStoryStatus("In progress"),5,"show sorted");
        productBacklog.saveUserStory(userStory3);

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            UserStory userStory1 =productBacklog.createUserStory(new UserStoryStatus("In progress"),6,"sort user story");
            productBacklog.saveUserStory(userStory1);
        List<UserStory> userStoryList =  productBacklog.getUsSortedByPriority();
        });
        // Assert
        assertTrue(exception.getMessage().contains("Check priority, cannot be < 0 or superior to 5."));
    }

}
