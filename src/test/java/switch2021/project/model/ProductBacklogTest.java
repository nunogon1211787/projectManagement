package switch2021.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.factory.UserStoryFactory;
import switch2021.project.immutable.Description;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ProductBacklogTest {


    @Test
    @DisplayName("Create and save user story with success")
    public void createAndSaveUserStorySuccessFull() {
        // Arrange
        UserStoryFactory userStoryFactory = mock(UserStoryFactory.class);
        ProductBacklog productBacklog = new ProductBacklog(userStoryFactory);

        UserStoryStatus status = mock(UserStoryStatus.class);
        Description description = mock(Description.class);

        int priority = 1;
        String descriptionUS = "Default Story";
        String name = "usmake";

        UserStory newUserStory = new UserStory(name, priority, descriptionUS, 5);
        when(status.getDescription()).thenReturn(description);
        when(description.getText()).thenReturn("To do");

       when(userStoryFactory.createUserStory(name, priority, descriptionUS, 5)).thenReturn(newUserStory);


        // Act
        boolean isUserStoryCreated = productBacklog.createAndSaveUserStory(name, priority, descriptionUS, 5);

        // Assert
        assertNotNull(isUserStoryCreated);
        assertEquals(status.getDescription().getText(), productBacklog.getUserStoryList().get(0).getUserStoryStatus().getDescription().getText());
        assertEquals(priority, productBacklog.getUserStoryList().get(0).getPriority());
        assertEquals(descriptionUS, productBacklog.getUserStoryList().get(0).getDescription().getText());
        assertEquals(name, productBacklog.getUserStoryList().get(0).getTitle());
    }


    @Test
    @DisplayName("Create and save user story fail - empty description")
    public void createUserStorydescriptionInvalidEmpty() {
        // Arrange
        UserStoryFactory userStoryFactory = new UserStoryFactory();
        ProductBacklog productBacklog = new ProductBacklog(userStoryFactory);
        int priority = 1;
        String description = "";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            boolean userStory = productBacklog.createAndSaveUserStory(
                    "US001", priority, description, 5);
        });

        // Assert
        assertTrue(exception.getMessage().contains("Description field requires at least " + 1 + " characters"));
    }


    @Test
    @DisplayName("Create and save user story success - description with one character")
    public void createUserStorySuccessFullOnLimit() {
        // Arrange
        UserStoryFactory userStoryFactory = new UserStoryFactory();
        ProductBacklog productBacklog = new ProductBacklog(userStoryFactory);
        UserStoryStatus status = new UserStoryStatus("To do");
        int priority = 1;
        String description = "D";
        String name = "US001";

        // Act
        boolean userStory = productBacklog.createAndSaveUserStory(
                name, priority, description, 5);

        // Assert
        assertNotNull(userStory);
        assertEquals(status, productBacklog.getUserStoryList().get(0).getUserStoryStatus());
        assertEquals(priority, productBacklog.getUserStoryList().get(0).getPriority());
        assertEquals(description, productBacklog.getUserStoryList().get(0).getDescription().getText());
        assertEquals(name, productBacklog.getUserStoryList().get(0).getTitle());
    }


    @Test
    @DisplayName("Create and save user story fail - invalid priority inferior")
    public void createUserStoryPriorityInvalidInf() {
        // Arrange
        UserStoryFactory userStoryFactory = new UserStoryFactory();
        ProductBacklog productBacklog = new ProductBacklog(userStoryFactory);
        int priority = -1;
        String description = "Create user story";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            boolean userStory = productBacklog.createAndSaveUserStory(
                    "US001", priority, description, 5);
        });

        // Assert
        assertTrue(exception.getMessage().contains("Check priority, cannot be < 0 or superior to 5."));
    }


    @Test
    @DisplayName("Create and save user story fail - invalid priority superior")
    public void createUserStoryPriorityInvalidSup() {
        // Arrange
        UserStoryFactory userStoryFactory = new UserStoryFactory();
        ProductBacklog productBacklog = new ProductBacklog(userStoryFactory);
        int priority = 6;
        String description = "Create user story";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            boolean userStory = productBacklog.createAndSaveUserStory(
                    "US001", priority, description, 5);
        });
        // Assert
        assertTrue(exception.getMessage().contains("Check priority, cannot be < 0 or superior to 5."));
    }


    @Test
    @DisplayName("Create and save user story fail - already exists")
    public void createUserStoryAlreadyExist() {
        // Arrange
        UserStoryFactory userStoryFactory = new UserStoryFactory();
        ProductBacklog productBacklog = new ProductBacklog(userStoryFactory);
        int priority = 1;
        String description = "Create user story";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            boolean userStory = productBacklog.createAndSaveUserStory(
                    "US001", priority, description, 5);
            boolean userStory2 = productBacklog.createAndSaveUserStory(
                    "US001", priority, description, 5);
        });

        // Assert
        assertTrue(exception.getMessage().contains("Repeated user story inserted, same code project and description."));
    }


    @Test
    @DisplayName("grants a list of US that is sorted by priority. It keeps the done and/or cancelled US on the end\n")
    public void getSortedListWithSuccess() {
        // Arrange
        UserStoryFactory userStoryFactory = new UserStoryFactory();
        ProductBacklog productBacklog = new ProductBacklog(userStoryFactory);
        productBacklog.createAndSaveUserStory("US001", 1, "create user story", 5);
        productBacklog.createAndSaveUserStory("US001", 3, "sort user story", 5);
        productBacklog.createAndSaveUserStory("US001", 2, "backlog sorted", 5);
        productBacklog.createAndSaveUserStory("US001", 5, "show sorted", 5);

        // Act
        List<UserStory> userStoryList = productBacklog.getUsSortedByPriority();

        // Assert
        assertEquals(4, userStoryList.size());
        assertEquals(1, userStoryList.get(0).getPriority());
        assertEquals(2, userStoryList.get(1).getPriority());
        assertEquals(3, userStoryList.get(2).getPriority());
        assertEquals(5, userStoryList.get(3).getPriority());

    }

    @Test
    @DisplayName("grants a list of US that is sorted by priority. It keeps the done and/or cancelled US on the end\n")
    public void getSortedListWithSuccess2() {
        // Arrange
        Company company = new Company();
        UserStoryFactory userStoryFactory = new UserStoryFactory();
        ProductBacklog productBacklog = new ProductBacklog(userStoryFactory);
        productBacklog.createAndSaveUserStory("US001", 1, "create user story", 5);
        productBacklog.createAndSaveUserStory("US001", 3, "sort user story", 5);
        productBacklog.createAndSaveUserStory("US001", 1, "backlog sorted", 5);
        productBacklog.createAndSaveUserStory("US001", 5, "show sorted", 5);
        productBacklog.createAndSaveUserStory("US001", 0, "show US", 5);
        productBacklog.getActiveUserStoryList().get(0).setUserStoryStatus((company.getUserStoryStatusStore().getUserStoryStatusByDescription("Done")));
        productBacklog.getActiveUserStoryList().get(1).setUserStoryStatus((company.getUserStoryStatusStore().getUserStoryStatusByDescription("Cancelled")));
        productBacklog.getActiveUserStoryList().get(2).setUserStoryStatus((company.getUserStoryStatusStore().getUserStoryStatusByDescription("To do")));
        productBacklog.getActiveUserStoryList().get(3).setUserStoryStatus((company.getUserStoryStatusStore().getUserStoryStatusByDescription("In progress")));
        productBacklog.getActiveUserStoryList().get(4).setUserStoryStatus((company.getUserStoryStatusStore().getUserStoryStatusByDescription("To do")));

        // Act
        List<UserStory> userStoryList = productBacklog.getUsSortedByPriority();

        // Assert
        assertEquals(5, userStoryList.size());
        assertEquals(1, userStoryList.get(0).getPriority());
        assertEquals(5, userStoryList.get(1).getPriority());
        assertEquals(0, userStoryList.get(2).getPriority());
        assertEquals(1, userStoryList.get(3).getPriority());
        assertEquals(3, userStoryList.get(4).getPriority());

    }

    @Test
    @DisplayName("get exception message \"Check priority, cannot be < 0 or superior to 5.“")
    public void getSortedListFailWrongPriority() {
        // Arrange
        UserStoryFactory userStoryFactory = new UserStoryFactory();
        ProductBacklog productBacklog = new ProductBacklog(userStoryFactory);
        productBacklog.createAndSaveUserStory("US001", 1, "create user story", 5);
        productBacklog.createAndSaveUserStory("US001", 2, "backlog sorted", 5);
        productBacklog.createAndSaveUserStory("US001", 5, "show sorted", 5);

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            productBacklog.createAndSaveUserStory("US001", 6, "sort user story", 5);
            productBacklog.getUsSortedByPriority();
        });

        // Assert
        assertTrue(exception.getMessage().contains("Check priority, cannot be < 0 or superior to 5."));
    }

    @Test
    @DisplayName("Create UserStory Refine Success")
    public void createUserStoryRefineSucess() {
        // Arrange
        Company company = new Company();
        UserStoryFactory userStoryFactory = new UserStoryFactory();
        ProductBacklog productBacklog = new ProductBacklog(userStoryFactory);
        productBacklog.createAndSaveUserStory("US001", 4, "123testtest", 5);
        UserStoryStatus userStoryStatus = company.getUserStoryStatusStore().getUserStoryStatusByDescription("To do");

        // Act
        UserStory userStory = new UserStory(productBacklog.getUserStoryList().get(0), userStoryStatus, 5, "234test234");

        // Assert
        assertEquals(userStory.getUserStoryStatus(), company.getUserStoryStatusStore().getUserStoryStatusByDescription("To do"));
        assertEquals(userStory.getParentUserStory(), productBacklog.getUserStoryList().get(0));
        assertEquals(5, userStory.getPriority());
        assertEquals("234test234", userStory.getDescription().getText());

    }


    @Test
    @DisplayName("Create UserStory Refine Fail - Description Empty")
    public void createUserStoryRefineDescriptionIsEmptyFail() {
        // Arrange
        Company company = new Company();
        UserStoryFactory userStoryFactory = new UserStoryFactory();
        ProductBacklog productBacklog = new ProductBacklog(userStoryFactory);
        productBacklog.createAndSaveUserStory("US001", 4, "123testtest", 5);
        UserStoryStatus userStoryStatus = company.getUserStoryStatusStore().getUserStoryStatusByDescription("To do");

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new UserStory(productBacklog.getUserStoryList().get(0), userStoryStatus, 5, "");
        });
    }

    @Test
    @DisplayName("Create UserStory Refine Fail - Description too Short")
    public void createUserStoryRefineSucessOnLimit() {
        // Arrange
        Company company = new Company();
        UserStoryFactory userStoryFactory = new UserStoryFactory();
        ProductBacklog productBacklog = new ProductBacklog(userStoryFactory);
        productBacklog.createAndSaveUserStory("US001", 4, "123testtest", 5);
        UserStoryStatus userStoryStatus = company.getUserStoryStatusStore().getUserStoryStatusByDescription("To do");

        // Act
        UserStory userStory = new UserStory(productBacklog.getUserStoryList().get(0), userStoryStatus, 5, "2");
        // Assert
        assertEquals(userStory.getUserStoryStatus(), company.getUserStoryStatusStore().getUserStoryStatusByDescription("To do"));
        assertEquals(userStory.getParentUserStory(), productBacklog.getUserStoryList().get(0));
        assertEquals(5, userStory.getPriority());
        assertEquals("2", userStory.getDescription().getText());
    }

    @Test
    @DisplayName("Create/save UserStory Refine Fail - Description already Exist")
    public void createUserStoryRefineDescriptionAlreadyExistFail() {
        Company company = new Company();
        company.getProductBacklog().createAndSaveUserStory("US001", 4, "123testtest", 5);

        assertThrows(IllegalArgumentException.class, () -> {
            UserStoryStatus userStoryStatus = company.getUserStoryStatusStore().getUserStoryStatusByDescription("To do");
            company.getProductBacklog().createUserStoryRefine(company.getProductBacklog().getUserStoryList().get(0), userStoryStatus, 4, "123testtest");
        });
    }

    @Test
    @DisplayName("Create/save UserStory Refine Fail - Priority Low")
    public void createUserStoryRefinePriorityLowFail() {
        Company company = new Company();
        company.getProductBacklog().createAndSaveUserStory("US001", 4, "123testtest", 5);

        assertThrows(IllegalArgumentException.class, () -> {
            UserStoryStatus userStoryStatus = company.getUserStoryStatusStore().getUserStoryStatusByDescription("To do");
            new UserStory(company.getProductBacklog().getUserStoryList().get(0), userStoryStatus, -1, "456testtest");
        });
    }

    @Test
    @DisplayName("Create/save UserStory Refine Fail - Priority High")
    public void createUserStoryRefinePriorityHighFail() {
        Company company = new Company();
        company.getProductBacklog().createAndSaveUserStory("US001", 4, "123testtest", 5);

        assertThrows(IllegalArgumentException.class, () -> {
            UserStoryStatus userStoryStatus = company.getUserStoryStatusStore().getUserStoryStatusByDescription("To do");
            new UserStory(company.getProductBacklog().getUserStoryList().get(0), userStoryStatus, 6, "456testtest");
        });
    }

    @Test
    @DisplayName("get User Story By Id Success")
    public void getUserStoryByIdSucess() {
        Company company = new Company();
        company.getProductBacklog().createAndSaveUserStory("US001", 4, "123testtest", 5);

        assertEquals(company.getProductBacklog().getUserStoryList().get(0), company.getProductBacklog().getUserStoryById(1));
    }

    @Test
    @DisplayName("get User Story By Id Fail")
    public void getUserStoryByIdFail() {
        Company company = new Company();
        company.getProductBacklog().createAndSaveUserStory("US001", 4, "123testtest", 5);

        assertNull(company.getProductBacklog().getUserStoryById(2));
    }

    @Test
    @DisplayName("getActiveUserStoryList_fail_not active")
    public void getActiveUserStoryListFailCompleted() {
        // Arrange
        UserStoryFactory userStoryFactory = new UserStoryFactory();
        ProductBacklog productBacklog = new ProductBacklog(userStoryFactory);
        int priority = 1;
        String description = "Create user story";

        productBacklog.createAndSaveUserStory(
                "US001", priority, description, 5);
        productBacklog.getUserStoryList().get(0).setUserStoryStatus(new UserStoryStatus("Completed"));

        // Act
        List<UserStory> userStoryList = productBacklog.getActiveUserStoryList();
        // Assert
        assertEquals(0, userStoryList.size());

    }

    @Test
    @DisplayName("set user story list")
    public void setUserStoryList() {
        // Arrange
        Company company = new Company();
        UserStoryFactory userStoryFactory = new UserStoryFactory();
        ProductBacklog productBacklog = new ProductBacklog(userStoryFactory);

        productBacklog.createAndSaveUserStory("US001", 1, "create user story", 5);
        productBacklog.createAndSaveUserStory("US001", 3, "sort user story", 5);
        productBacklog.createAndSaveUserStory("US001", 1, "backlog sorted", 5);
        // Act
        productBacklog.getUserStoryList().get(0).setUserStoryStatus(company.getUserStoryStatusStore().getUserStoryStatusByDescription("Done"));
        productBacklog.getUserStoryList().get(1).setUserStoryStatus(company.getUserStoryStatusStore().getUserStoryStatusByDescription("Cancelled"));
        productBacklog.getUserStoryList().get(2).setUserStoryStatus(company.getUserStoryStatusStore().getUserStoryStatusByDescription("To do"));
        // Assert
        assertEquals(3, productBacklog.getUserStoryList().size());

    }

    @Test
    @DisplayName("validate ID user Story")
    public void validateID() {
        // Arrange
        UserStoryFactory userStoryFactory = new UserStoryFactory();
        ProductBacklog productBacklog = new ProductBacklog(userStoryFactory);

        // Act
        productBacklog.createAndSaveUserStory("US001", 1, "create user story", 5);
        productBacklog.createAndSaveUserStory("US001", 3, "sort user story", 5);
        productBacklog.createAndSaveUserStory("US001", 1, "backlog sorted", 5);

        // Assert

        assertEquals(3, productBacklog.getUserStoryList().size());
        assertEquals(1, productBacklog.getUserStoryList().get(0).getIdUserStory());
        assertEquals(2, productBacklog.getUserStoryList().get(1).getIdUserStory());
        assertEquals(3, productBacklog.getUserStoryList().get(2).getIdUserStory());
    }
}