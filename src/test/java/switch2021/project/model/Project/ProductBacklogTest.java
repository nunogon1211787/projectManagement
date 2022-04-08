package switch2021.project.model.Project;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.factory.UserStoryFactory;
import switch2021.project.model.Company;
import switch2021.project.model.UserStory.UserStory;
import switch2021.project.repositories.ProductBacklog;
import switch2021.project.model.valueObject.Description;
import switch2021.project.model.valueObject.UserStoryStatus;

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
        String title = "As a PO, i want to test this string";

        UserStory newUserStory = new UserStory(title, priority, descriptionUS, 5);
        when(status.getDescription()).thenReturn(description);
        when(description.getText()).thenReturn("To do");

        when(userStoryFactory.createUserStory(title, priority, descriptionUS, 5)).thenReturn(newUserStory);
        //Act
        productBacklog.createAndSaveUserStory(title, priority, descriptionUS, 5);
        // Assert
        assertEquals(status.getDescription().getText(), productBacklog.getUserStoryList().get(0).getUserStoryStatus().getDescription().getText());
        assertEquals(priority, productBacklog.getUserStoryList().get(0).getPriority().getPriorityUs());
        assertEquals(descriptionUS, productBacklog.getUserStoryList().get(0).getDescription().getText());
        assertEquals(title, productBacklog.getUserStoryList().get(0).getTitle().getTitleUs());
    }

    @Test
    @DisplayName("Create and save user story fail - empty description")
    public void createUserStorydescriptionInvalidEmpty() {
        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            // Arrange
            UserStoryFactory userStoryFactory = new UserStoryFactory();
            ProductBacklog productBacklog = new ProductBacklog(userStoryFactory);
            int priority = 1;
            String description = "";
            // Act
            productBacklog.createAndSaveUserStory("US001", priority, description, 5);
        });

    }

    @Test
    @DisplayName("Create and save user story fail - empty description")
    public void createUserStorydescriptionInvalidEmptyMock() {
        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            // Arrange
            UserStoryFactory userStoryFactory = mock(UserStoryFactory.class);
            ProductBacklog productBacklog = new ProductBacklog(userStoryFactory);
            UserStory userStory = mock(UserStory.class);
            userStory.setIdUserStory(1);

            String descriptionUS = "";
            int priority = 1;

            when(userStoryFactory.createUserStory("As director i want create US", priority, descriptionUS, 5)).thenThrow(new IllegalArgumentException("Description field requires at least " + 1 + " characters"));
            // Act
            productBacklog.createAndSaveUserStory("As director i want create US", priority, descriptionUS, 5);
        });

    }


    @Test
    @DisplayName("Create and save user story success - description with one character")
    public void createUserStorySuccessFullOnLimit() {
        // Arrange
        UserStoryFactory userStoryFactory = new UserStoryFactory();
        ProductBacklog productBacklog = new ProductBacklog(userStoryFactory);
        UserStoryStatus status = new UserStoryStatus("To do", true);
        int priority = 1;
        String description = "D";
        String title = "As a PO, i want to test this string";
        //Act
        productBacklog.createAndSaveUserStory(title, priority, description, 5);
        // Assert
        assertEquals(status, productBacklog.getUserStoryList().get(0).getUserStoryStatus());
        assertEquals(priority, productBacklog.getUserStoryList().get(0).getPriority().getPriorityUs());
        assertEquals(description, productBacklog.getUserStoryList().get(0).getDescription().getText());
        assertEquals(title, productBacklog.getUserStoryList().get(0).getTitle().getTitleUs());
    }


    @Test
    @DisplayName("Create and save user story fail - invalid priority inferior")
    public void createUserStoryPriorityInvalidInf() {
        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            // Arrange
            UserStoryFactory userStoryFactory = new UserStoryFactory();
            ProductBacklog productBacklog = new ProductBacklog(userStoryFactory);
            int priority = -1;
            String description = "Create user story";
            // Act
            productBacklog.createAndSaveUserStory("US001", priority, description, 5);
        });


    }


    @Test
    @DisplayName("Create and save user story fail - invalid priority superior")
    public void createUserStoryPriorityInvalidSup() {
        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            // Arrange
            UserStoryFactory userStoryFactory = new UserStoryFactory();
            ProductBacklog productBacklog = new ProductBacklog(userStoryFactory);
            int priority = 6;
            String description = "Create user story";
            // Act
            productBacklog.createAndSaveUserStory("US001", priority, description, 5);
        });
    }


    @Test
    @DisplayName("Create and save user story fail - already exists")
    public void createUserStoryAlreadyExist() {
        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            // Arrange
            UserStoryFactory userStoryFactory = new UserStoryFactory();
            ProductBacklog productBacklog = new ProductBacklog(userStoryFactory);
            int priority = 1;
            String description = "Create user story";
            // Act
            productBacklog.createAndSaveUserStory("US001", priority, description, 5);
            productBacklog.createAndSaveUserStory("US001", priority, description, 5);
        });
    }


    @Test
    @DisplayName("grants a list of US that is sorted by priority. It keeps the done and/or cancelled US on the end\n")
    public void getSortedListWithSuccess() {
        // Arrange
        UserStoryFactory userStoryFactory = new UserStoryFactory();
        ProductBacklog productBacklog = new ProductBacklog(userStoryFactory);
        productBacklog.createAndSaveUserStory("As a PO, i want to test this string", 1, "create user story", 5);
        productBacklog.createAndSaveUserStory("As a PO, i want to test this string", 3, "sort user story", 5);
        productBacklog.createAndSaveUserStory("As a PO, i want to test this string", 2, "backlog sorted", 5);
        productBacklog.createAndSaveUserStory("As a PO, i want to test this string", 5, "show sorted", 5);

        // Act
        List<UserStory> userStoryList = productBacklog.getUsSortedByPriority();

        // Assert
        assertEquals(4, userStoryList.size());
        assertEquals(1, userStoryList.get(0).getPriority().getPriorityUs());
        assertEquals(2, userStoryList.get(1).getPriority().getPriorityUs());
        assertEquals(3, userStoryList.get(2).getPriority().getPriorityUs());
        assertEquals(5, userStoryList.get(3).getPriority().getPriorityUs());

    }

    @Test
    @DisplayName("get exception message \"Check priority, cannot be < 0 or superior to 5.“")
    public void getSortedListFailWrongPriority() {
        // Arrange
        UserStoryFactory userStoryFactory = new UserStoryFactory();
        ProductBacklog productBacklog = new ProductBacklog(userStoryFactory);
        productBacklog.createAndSaveUserStory("As a PO, i want to test this string", 1, "create user story", 5);
        productBacklog.createAndSaveUserStory("As a PO, i want to test this string", 2, "backlog sorted", 5);
        productBacklog.createAndSaveUserStory("As a PO, i want to test this string", 5, "show sorted", 5);

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            productBacklog.createAndSaveUserStory("As a PO, i want to test this string", 6, "sort user story", 5);
            productBacklog.getUsSortedByPriority();
        });

        // Assert
        assertTrue(exception.getMessage().contains("Check priority, cannot be < 0 or superior to 5"));
    }

    @Test
    @DisplayName("Create UserStory Refine Success")
    public void createUserStoryRefineSucess() {
        // Arrange
        Company company = new Company();
        UserStoryFactory userStoryFactory = new UserStoryFactory();
        ProductBacklog productBacklog = new ProductBacklog(userStoryFactory);
        productBacklog.createAndSaveUserStory("As a PO, i want to test this string", 4, "123testtest", 5);
        UserStoryStatus userStoryStatus = company.getUserStoryStatusStore().getUserStoryStatusByDescription("To do");

        // Act
        UserStory userStory = new UserStory(productBacklog.getUserStoryList().get(0), userStoryStatus, 5, "234test234");

        // Assert
        assertEquals(userStory.getUserStoryStatus(), company.getUserStoryStatusStore().getUserStoryStatusByDescription("To do"));
        assertEquals(userStory.getParentUserStory(), productBacklog.getUserStoryList().get(0));
        assertEquals(5, userStory.getPriority().getPriorityUs());
        assertEquals("234test234", userStory.getDescription().getText());

    }


    @Test
    @DisplayName("Create UserStory Refine Fail - Description Empty")
    public void createUserStoryRefineDescriptionIsEmptyFail() {
        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            // Arrange
            Company company = new Company();
            UserStoryFactory userStoryFactory = new UserStoryFactory();
            ProductBacklog productBacklog = new ProductBacklog(userStoryFactory);
            productBacklog.createAndSaveUserStory("As a PO, i want to test this string", 4, "123testtest", 5);
            UserStoryStatus userStoryStatus = company.getUserStoryStatusStore().getUserStoryStatusByDescription("To do");
            // Act
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
        productBacklog.createAndSaveUserStory("As a PO, i want to test this string", 4, "123testtest", 5);
        UserStoryStatus userStoryStatus = company.getUserStoryStatusStore().getUserStoryStatusByDescription("To do");

        // Act
        UserStory userStory = new UserStory(productBacklog.getUserStoryList().get(0), userStoryStatus, 5, "2");
        // Assert
        assertEquals(userStory.getUserStoryStatus(), company.getUserStoryStatusStore().getUserStoryStatusByDescription("To do"));
        assertEquals(userStory.getParentUserStory(), productBacklog.getUserStoryList().get(0));
        assertEquals(5, userStory.getPriority().getPriorityUs());
        assertEquals("2", userStory.getDescription().getText());
    }

    @Test
    @DisplayName("Create/save UserStory Refine Fail - Description already Exist")
    public void createUserStoryRefineDescriptionAlreadyExistFail() {
        Company company = new Company();
        company.getProductBacklog().createAndSaveUserStory("As a PO, i want to test this string", 4, "123testtest", 5);

        assertThrows(IllegalArgumentException.class, () -> {
            UserStoryStatus userStoryStatus = company.getUserStoryStatusStore().getUserStoryStatusByDescription("To do");
            company.getProductBacklog().refineUserStory(company.getProductBacklog().getUserStoryList().get(0), userStoryStatus, 4, "123testtest");
        });
    }

    @Test
    @DisplayName("Create/save UserStory Refine Fail - Priority Low")
    public void createUserStoryRefinePriorityLowFail() {
        Company company = new Company();
        company.getProductBacklog().createAndSaveUserStory("As a PO, i want to test this string", 4, "123testtest", 5);

        assertThrows(IllegalArgumentException.class, () -> {
            UserStoryStatus userStoryStatus = company.getUserStoryStatusStore().getUserStoryStatusByDescription("To do");
            new UserStory(company.getProductBacklog().getUserStoryList().get(0), userStoryStatus, -1, "456testtest");
        });
    }

    @Test
    @DisplayName("Create/save UserStory Refine Fail - Priority High")
    public void createUserStoryRefinePriorityHighFail() {
        Company company = new Company();
        company.getProductBacklog().createAndSaveUserStory("As a PO, i want to test this string", 4, "123testtest", 5);

        assertThrows(IllegalArgumentException.class, () -> {
            UserStoryStatus userStoryStatus = company.getUserStoryStatusStore().getUserStoryStatusByDescription("To do");
            new UserStory(company.getProductBacklog().getUserStoryList().get(0), userStoryStatus, 6, "456testtest");
        });
    }

    @Test
    @DisplayName("get User Story By Id Success")
    public void getUserStoryByIdSucess() {
        Company company = new Company();
        company.getProductBacklog().createAndSaveUserStory("As a PO, i want to test this string", 4, "123testtest", 5);

        assertEquals(company.getProductBacklog().getUserStoryList().get(0), company.getProductBacklog().getUserStoryById(1));
    }

    @Test
    @DisplayName("get User Story By Id Fail")
    public void getUserStoryByIdFail() {
        Company company = new Company();
        company.getProductBacklog().createAndSaveUserStory("As a PO, i want to test this string", 4, "123testtest", 5);

        assertNull(company.getProductBacklog().getUserStoryById(2));
        assertNotEquals(1, company.getProductBacklog().getUserStoryById(0));

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
                "As a PO, i want to test this string", priority, description, 5);
        productBacklog.getUserStoryList().get(0).setUserStoryStatus(new UserStoryStatus("Completed", true));

        // Act
        List<UserStory> userStoryList = productBacklog.getActiveUserStoryList();
        // Assert
        assertEquals(0, userStoryList.size());

    }

    @Test
    @DisplayName("set user story list")
    public void CreateAndSaveUserStorySuccess() {
        // Arrange
        Company company = mock(Company.class);
        UserStoryFactory userStoryFactory = mock(UserStoryFactory.class);
        ProductBacklog productBacklog = new ProductBacklog(userStoryFactory);
        when(company.getProductBacklog()).thenReturn(productBacklog);
        UserStory userStory = mock(UserStory.class);
        userStory.setIdUserStory(1);
        UserStory userStory2 = mock(UserStory.class);
        userStory2.setIdUserStory(2);
        Description description1 = mock(Description.class);
        Description description2 = mock(Description.class);

        when(userStory.getDescription()).thenReturn(description1);
        when(userStory2.getDescription()).thenReturn(description2);
        when(description1.getText()).thenReturn("create user story");
        when(description2.getText()).thenReturn("sort user story");

        when(userStoryFactory.createUserStory("US001", 1, "create user story", 5)).thenReturn(userStory);
        productBacklog.createAndSaveUserStory("US001", 1, "create user story", 5);

        when(userStoryFactory.createUserStory("US002", 3, "sort user story", 5)).thenReturn(userStory2);
        productBacklog.createAndSaveUserStory("US002", 3, "sort user story", 5);

        // Assert
        assertEquals(2, productBacklog.getUserStoryList().size());


    }

    @Test
    @DisplayName("validate ID user Story")
    public void validateID() {
        // Arrange
        UserStoryFactory userStoryFactory = new UserStoryFactory();
        ProductBacklog productBacklog = new ProductBacklog(userStoryFactory);

        // Act
        productBacklog.createAndSaveUserStory("As a PO, i want to test this string", 1, "create user story", 5);
        productBacklog.createAndSaveUserStory("As a PO, i want to test this string", 3, "sort user story", 5);
        productBacklog.createAndSaveUserStory("As a PO, i want to test this string", 1, "backlog sorted", 5);

        // Assert
        assertEquals(3, productBacklog.getUserStoryList().size());
        assertEquals(userStoryFactory,productBacklog.getUserStoryFactory());
        assertEquals(1, productBacklog.getUserStoryList().get(0).getIdUserStory());
        assertEquals(2, productBacklog.getUserStoryList().get(1).getIdUserStory());
        assertEquals(3, productBacklog.getUserStoryList().get(2).getIdUserStory());
    }

    @Test
    @DisplayName("getUserStoryFactory - success")
    public void getUsFactory() {
        // Arrange & Act
        UserStoryFactory userStoryFactory = mock(UserStoryFactory.class);
        ProductBacklog productBacklog = new ProductBacklog(userStoryFactory);
        // Assert
        assertEquals(userStoryFactory,productBacklog.getUserStoryFactory());
        assertNotNull(userStoryFactory);
    }
}