package switch2021.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.stores.ProjectStore;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductBacklogTest {
    Company company;
    ProductBacklog productBacklog;
    private Project proj;
    private Project proj1;
    private Project proj2;
    private Project proj3;
    private ProjectStore projectList;
    private UserStory userStoryToRefine;

    @BeforeEach
    public void ini() {
        company = new Company();
        LocalDate date = LocalDate.of(2021, 12, 12);
        company.getBusinessSectorStore().addBusinessSector(company.getBusinessSectorStore().createBusinessSector("sector"));
        company.getCustomerStore().saveNewCustomer(company.getCustomerStore().createCustomer("Teste", "Teste", 123456789));
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        proj = company.getProjectStore().createProject("prototype", "test56", customer,
                typo, sector, date, 7, 5000);
        company.getProjectStore().saveNewProject(proj);
        productBacklog = proj.getProductBacklog();
        userStoryToRefine = productBacklog.createUserStory("US001", 4, "123testtest", 5);
        productBacklog.saveUserStory(userStoryToRefine);
    }


    @Test
    public void createUserStorySuccessFull() {
        // Arrange
        ProductBacklog productBacklog = new ProductBacklog();
        UserStoryStatus status = new UserStoryStatus("To do");
        int priority = 1;
        String description = "Default Story";
        String name = "usmake";

        // Act
        UserStory userStory = productBacklog.createUserStory(
                name, priority, description, 5);
        // Assert
        assertNotNull(userStory);
        assertEquals(status, userStory.getUserStoryStatus());
        assertEquals(priority, userStory.getPriority());
        assertEquals(description, userStory.getDescription().getDescriptionF());
        assertEquals(name, userStory.getName());
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
                    "US001", priority, description, 5);
        });
        // Assert
        assertTrue(exception.getMessage().contains("Description field requires at least " + 1 + " characters"));
    }


    @Test
    public void createUserStorySuccessFullOnLimit() {
        // Arrange
        ProductBacklog productBacklog = new ProductBacklog();
        UserStoryStatus status = new UserStoryStatus("To do");
        int priority = 1;
        String description = "D";
        String name = "US001";

        // Act
        UserStory userStory = productBacklog.createUserStory(
                name, priority, description, 5);
        // Assert
        assertNotNull(userStory);
        assertEquals(status, userStory.getUserStoryStatus());
        assertEquals(priority, userStory.getPriority());
        assertEquals(description, userStory.getDescription().getDescriptionF());
        assertEquals(name, userStory.getName());
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
                    "US001", priority, description, 5);
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
                    "US001", priority, description, 5);
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
                    "US001", priority, description, 5);
            productBacklog.saveUserStory(userStory);
            UserStory userStory2 = productBacklog.createUserStory(
                    "US001", priority, description, 5);
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
                "US001", priority, description, 5);
        productBacklog.saveUserStory(userStory);
        UserStory userStory2 = productBacklog.createUserStory(
                "US001", priority, description, 5);
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
        UserStoryStatus status = new UserStoryStatus("To do");
        int priority = 1;
        String description = "Create user story";

        UserStory userStory = productBacklog.createUserStory(
                "US001", priority, description, 5);
        // Act
        productBacklog.saveUserStory(userStory);
        // Assert
        assertNotNull(userStory);
        assertEquals(status, userStory.getUserStoryStatus());
        assertEquals(priority, userStory.getPriority());
        assertEquals(description, userStory.getDescription().getDescriptionF());
    }

    @Test
    public void addNewUserStoryAlreadyExist() {
        // Arrange
        ProductBacklog productBacklog = new ProductBacklog();
        UserStoryStatus status = new UserStoryStatus("In progress");
        int priority = 1;
        String description = "Create user story";

        UserStory userStory = productBacklog.createUserStory(
                "US001", priority, description, 5);
        productBacklog.saveUserStory(userStory);
        UserStory userStory2 = productBacklog.createUserStory(
                "US001", priority, description, 5);
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            productBacklog.saveUserStory(userStory2);
            productBacklog.saveUserStory(userStory);
        });
        // Assert
        assertTrue(exception.getMessage().contains("Repeated user story inserted, same code project and description."));
    }

    @Test
    public void addNewUserStoryWithSuccess() {
        // Arrange
        ProductBacklog productBacklog = new ProductBacklog();
        UserStoryStatus status = new UserStoryStatus("To do");
        int priority = 1;
        String description = "Create user story";

        UserStory userStory = productBacklog.createUserStory(
                "US001", priority, description, 5);
        // Act
        productBacklog.saveUserStory(userStory);
        // Assert
        assertNotNull(userStory);
        assertEquals(status, userStory.getUserStoryStatus());
        assertEquals(priority, userStory.getPriority());
        assertEquals(description, userStory.getDescription().getDescriptionF());
    }

    @Test
    @DisplayName("grants a list of US that is sorted by priority. It keeps the done and/or cancelled US on the end\n")
    public void getSortedListWithSuccess() {
        // Arrange
        ProductBacklog productBacklog = new ProductBacklog();
        UserStory userStory = productBacklog.createUserStory("US001", 1, "create user story", 5);
        productBacklog.saveUserStory(userStory);
        UserStory userStory1 = productBacklog.createUserStory("US001", 3, "sort user story", 5);
        productBacklog.saveUserStory(userStory1);
        UserStory userStory2 = productBacklog.createUserStory("US001", 2, "backlog sorted", 5);
        productBacklog.saveUserStory(userStory2);
        UserStory userStory3 = productBacklog.createUserStory("US001", 5, "show sorted", 5);
        productBacklog.saveUserStory(userStory3);

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
        ProductBacklog productBacklog = new ProductBacklog();
        UserStory userStory = productBacklog.createUserStory("US001", 1, "create user story", 5);
        userStory.setUserStoryStatus(company.getUserStoryStatusStore().getUserStoryStatusByDescription("Done"));
        productBacklog.saveUserStory(userStory);
        UserStory userStory1 = productBacklog.createUserStory("US001", 3, "sort user story", 5);
        userStory1.setUserStoryStatus(company.getUserStoryStatusStore().getUserStoryStatusByDescription("Cancelled"));
        productBacklog.saveUserStory(userStory1);
        UserStory userStory2 = productBacklog.createUserStory("US001", 1, "backlog sorted", 5);
        userStory2.setUserStoryStatus(company.getUserStoryStatusStore().getUserStoryStatusByDescription("To do"));
        productBacklog.saveUserStory(userStory2);
        UserStory userStory3 = productBacklog.createUserStory("US001", 5, "show sorted", 5);
        userStory3.setUserStoryStatus(company.getUserStoryStatusStore().getUserStoryStatusByDescription("In progress"));
        productBacklog.saveUserStory(userStory3);
        UserStory userStory4 = productBacklog.createUserStory("US001", 0, "show US", 5);
        userStory4.setUserStoryStatus(company.getUserStoryStatusStore().getUserStoryStatusByDescription("To do"));
        productBacklog.saveUserStory(userStory4);

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
        ProductBacklog productBacklog = new ProductBacklog();
        UserStory userStory = productBacklog.createUserStory("US001", 1, "create user story", 5);
        productBacklog.saveUserStory(userStory);
        UserStory userStory2 = productBacklog.createUserStory("US001", 2, "backlog sorted", 5);
        productBacklog.saveUserStory(userStory2);
        UserStory userStory3 = productBacklog.createUserStory("US001", 5, "show sorted", 5);
        productBacklog.saveUserStory(userStory3);

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            UserStory userStory1 = productBacklog.createUserStory("US001", 6, "sort user story", 5);
            productBacklog.saveUserStory(userStory1);
            List<UserStory> userStoryList = productBacklog.getUsSortedByPriority();
        });
        // Assert
        assertTrue(exception.getMessage().contains("Check priority, cannot be < 0 or superior to 5."));
    }

    @Test
    @DisplayName("Create UserStory Refine Success")
    public void createUserStoryRefineSucess() {
        UserStoryStatus userStoryStatus = company.getUserStoryStatusStore().getUserStoryStatusByDescription("To do");
        UserStory userStory = new UserStory(userStoryToRefine, userStoryStatus, 5, "234test234");

        assertEquals(userStory.getUserStoryStatus(), company.getUserStoryStatusStore().getUserStoryStatusByDescription("To do"));
        assertEquals(userStory.getParentUserStory(), userStoryToRefine);
        assertEquals(5, userStory.getPriority());
        assertEquals("234test234", userStory.getDescription().getDescriptionF());

    }

    @Test
    @DisplayName("Create UserStory Refine Fail - Description Empty")
    public void createUserStoryRefineDescriptionIsEmptyFail() {
        assertThrows(IllegalArgumentException.class, () -> {
            UserStoryStatus userStoryStatus = company.getUserStoryStatusStore().getUserStoryStatusByDescription("To do");
            UserStory userStory = new UserStory(userStoryToRefine, userStoryStatus, 5, "");
        });
    }

    @Test
    @DisplayName("Create UserStory Refine Fail - Description too Short")
    public void createUserStoryRefineSucessOnLimit() {
        UserStoryStatus userStoryStatus = company.getUserStoryStatusStore().getUserStoryStatusByDescription("To do");
        UserStory userStory = new UserStory(userStoryToRefine, userStoryStatus, 5, "2");

        assertEquals(userStory.getUserStoryStatus(), company.getUserStoryStatusStore().getUserStoryStatusByDescription("To do"));
        assertEquals(userStory.getParentUserStory(), userStoryToRefine);
        assertEquals(5, userStory.getPriority());
        assertEquals("2", userStory.getDescription().getDescriptionF());
    }

    @Test
    @DisplayName("Create/save UserStory Refine Fail - Description already Exist")
    public void createUserStoryRefineDescriptionAlreadyExistFail() {
        assertThrows(IllegalArgumentException.class, () -> {
            UserStoryStatus userStoryStatus = company.getUserStoryStatusStore().getUserStoryStatusByDescription("To do");
            UserStory userStory = new UserStory(userStoryToRefine, userStoryStatus, 5, "123testtest");
            productBacklog.saveUserStory(userStory);
        });
    }

    @Test
    @DisplayName("Create/save UserStory Refine Fail - Priority Low")
    public void createUserStoryRefinePriorityLowFail() {
        assertThrows(IllegalArgumentException.class, () -> {
            UserStoryStatus userStoryStatus = company.getUserStoryStatusStore().getUserStoryStatusByDescription("To do");
            UserStory userStory = new UserStory(userStoryToRefine, userStoryStatus, -1, "456testtest");
        });
    }

    @Test
    @DisplayName("Create/save UserStory Refine Fail - Priority High")
    public void createUserStoryRefinePriorityHighFail() {
        assertThrows(IllegalArgumentException.class, () -> {
            UserStoryStatus userStoryStatus = company.getUserStoryStatusStore().getUserStoryStatusByDescription("To do");
            UserStory userStory = new UserStory(userStoryToRefine, userStoryStatus, 6, "456testtest");
        });
    }

    @Test
    @DisplayName("get User Story By Id Success")
    public void getUserStoryByIdSucess() {
        assertEquals(userStoryToRefine, company.getProjectStore().getProjectByCode("Project_2022_1").getProductBacklog().getUserStoryById(1));
    }

    @Test
    @DisplayName("get User Story By Id Fail")
    public void getUserStoryByIdFail() {
        assertNull(company.getProjectStore().getProjectByCode("Project_2022_1").getProductBacklog().getUserStoryById(2));
    }

    @Test
    @DisplayName("getActiveUserStoryList_fail_not active")
    public void getActiveUserStoryListFailCompleted() {
        // Arrange
        ProductBacklog productBacklog = new ProductBacklog();
        int priority = 1;
        String description = "Create user story";

        UserStory userStory = productBacklog.createUserStory(
                "US001", priority, description, 5);
        userStory.setUserStoryStatus(new UserStoryStatus("Completed"));
        productBacklog.saveUserStory(userStory);
        // Act
        List<UserStory> userStoryList = productBacklog.getActiveUserStoryList();
        // Assert
        assertEquals(0, userStoryList.size());

    }

    @Test
    @DisplayName("set user story list")
    public void setUserStoryList() {
        // Arrange
        ProductBacklog productBacklog = new ProductBacklog();

        UserStory userStory = productBacklog.createUserStory("US001", 1, "create user story", 5);
        userStory.setUserStoryStatus(company.getUserStoryStatusStore().getUserStoryStatusByDescription("Done"));
        productBacklog.saveUserStory(userStory);

        UserStory userStory1 = productBacklog.createUserStory("US001", 3, "sort user story", 5);
        userStory1.setUserStoryStatus(company.getUserStoryStatusStore().getUserStoryStatusByDescription("Cancelled"));
        productBacklog.saveUserStory(userStory1);

        UserStory userStory2 = productBacklog.createUserStory("US001", 1, "backlog sorted", 5);
        userStory2.setUserStoryStatus(company.getUserStoryStatusStore().getUserStoryStatusByDescription("To do"));
        productBacklog.saveUserStory(userStory2);

        List<UserStory> userStories = new ArrayList<>();
        userStories.add(userStory);
        userStories.add(userStory1);
        userStories.add(userStory2);

        // Act
        productBacklog.setUserStoryList(userStories);
        // Assert
        assertEquals(3, productBacklog.getUserStoryList().size());

    }

    @Test
    @DisplayName("validate ID user Story")
    public void validateID() {
        // Arrange
        ProductBacklog productBacklog = new ProductBacklog();

        UserStory userStory = productBacklog.createUserStory("US001", 1, "create user story", 5);
        userStory.setUserStoryStatus(company.getUserStoryStatusStore().getUserStoryStatusByDescription("Done"));
        userStory.getIdUserStory();

        UserStory userStory1 = productBacklog.createUserStory("US001", 3, "sort user story", 5);
        userStory1.setUserStoryStatus(company.getUserStoryStatusStore().getUserStoryStatusByDescription("Cancelled"));
        userStory1.getIdUserStory();

        UserStory userStory2 = productBacklog.createUserStory("US001", 1, "backlog sorted", 5);
        userStory2.setUserStoryStatus(company.getUserStoryStatusStore().getUserStoryStatusByDescription("To do"));
        userStory2.setIdUserStory(1);

        List<UserStory> userStories = new ArrayList<>();
        userStories.add(userStory);
        userStories.add(userStory1);
        userStories.add(userStory2);

        // Act
        productBacklog.saveUserStory(userStory);
        productBacklog.saveUserStory(userStory1);
        productBacklog.saveUserStory(userStory2);

        // Assert

        assertEquals(2, productBacklog.getUserStoryList().size());

    }
}