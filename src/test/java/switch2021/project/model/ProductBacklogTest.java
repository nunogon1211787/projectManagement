package switch2021.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.stores.ProjectStore;


import java.time.LocalDate;
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
    public void ini(){
        company = new Company(); // sempre a mesma instancia
        LocalDate date = LocalDate.of(2021, 12, 12);
        company.getBusinessSectorStore().addBusinessSector(company.getBusinessSectorStore().createBusinessSector("sector"));
        company.getCustomerStore().saveNewCustomer(company.getCustomerStore().createCustomer("Teste", "Teste"));
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        proj = company.getProjectStore().createProject( "prototype", "test56", customer,
                typo, sector, date, 7, 5000);
        company.getProjectStore().saveNewProject(proj);
        productBacklog = proj.getProductBacklog();
        userStoryToRefine = productBacklog.createUserStory(company.getUserStoryStatusStore().getUserStoryStatusByDescription("To do"),4,"123testtest");
        productBacklog.saveUserStory(userStoryToRefine);
    }

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
        productBacklog.saveUserStory(userStory);
        UserStory userStory2 = productBacklog.createUserStory(
                status, priority, description);
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
    @DisplayName("grants a list of US that is sorted by priority. It keeps the done and/or cancelled US on the end\n")
    public void getSortedListWithSuccess2(){
        // Arrange
        ProductBacklog productBacklog=new ProductBacklog();
        UserStory userStory =productBacklog.createUserStory(new UserStoryStatus("Done"), 1,"create user story");
        productBacklog.saveUserStory(userStory);
        UserStory userStory1 =productBacklog.createUserStory(new UserStoryStatus("Cancelled"), 3,"sort user story");
        productBacklog.saveUserStory(userStory1);
        UserStory userStory2 =productBacklog.createUserStory(new UserStoryStatus("To do"),1,"backlog sorted");
        productBacklog.saveUserStory(userStory2);
        UserStory userStory3 =productBacklog.createUserStory(new UserStoryStatus("In progress"),5,"show sorted");
        productBacklog.saveUserStory(userStory3);
        UserStory userStory4 =productBacklog.createUserStory(new UserStoryStatus("To do"),0,"show US");
        productBacklog.saveUserStory(userStory4);

        // Act
        List<UserStory> userStoryList =  productBacklog.getUsSortedByPriority();

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

    @Test
    @DisplayName("Create UserStory Refine Success")
    public void createUserStoryRefineSucess(){
        UserStoryStatus userStoryStatus = company.getUserStoryStatusStore().getUserStoryStatusByDescription("To do");
        UserStory userStory = new UserStory(userStoryToRefine, userStoryStatus, 5,"234test234");

        assertEquals(userStory.getUserStoryStatus(),company.getUserStoryStatusStore().getUserStoryStatusByDescription("To do"));
        assertEquals(userStory.getParentUserStory(),userStoryToRefine);
        assertEquals(userStory.getPriority(),5);
        assertEquals(userStory.getDescription(),"234test234");

    }
    @Test
    @DisplayName("Create UserStory Refine Fail - Description Empty")
    public void createUserStoryRefineDescriptionIsEmptyFail(){
        assertThrows(IllegalArgumentException.class, () -> {
        UserStoryStatus userStoryStatus = company.getUserStoryStatusStore().getUserStoryStatusByDescription("To do");
        UserStory userStory = new UserStory(userStoryToRefine, userStoryStatus, 5,"");
        });
    }
    @Test
    @DisplayName("Create UserStory Refine Fail - Description too Short")
    public void createUserStoryRefineDescriptiontooShortFail(){
        assertThrows(IllegalArgumentException.class, () -> {
            UserStoryStatus userStoryStatus = company.getUserStoryStatusStore().getUserStoryStatusByDescription("To do");
            UserStory userStory = new UserStory(userStoryToRefine, userStoryStatus, 5,"rd");
        });
    }
    @Test
    @DisplayName("Create/save UserStory Refine Fail - Description already Exist")
    public void createUserStoryRefineDescriptionAlreadyExistFail(){
        assertThrows(IllegalArgumentException.class, () -> {
            UserStoryStatus userStoryStatus = company.getUserStoryStatusStore().getUserStoryStatusByDescription("To do");
            UserStory userStory = new UserStory(userStoryToRefine, userStoryStatus, 5,"123testtest");
            productBacklog.saveUserStory(userStory);
        });
    }
    @Test
    @DisplayName("Create/save UserStory Refine Fail - Priority Low")
    public void createUserStoryRefinePriorityLowFail(){
        assertThrows(IllegalArgumentException.class, () -> {
            UserStoryStatus userStoryStatus = company.getUserStoryStatusStore().getUserStoryStatusByDescription("To do");
            UserStory userStory = new UserStory(userStoryToRefine, userStoryStatus, -1,"456testtest");
        });
    }
    @Test
    @DisplayName("Create/save UserStory Refine Fail - Priority High")
    public void createUserStoryRefinePriorityHighFail(){
        assertThrows(IllegalArgumentException.class, () -> {
            UserStoryStatus userStoryStatus = company.getUserStoryStatusStore().getUserStoryStatusByDescription("To do");
            UserStory userStory = new UserStory(userStoryToRefine, userStoryStatus, 6,"456testtest");
        });
    }
    @Test
    @DisplayName("get User Story By Id Success")
    public void getUserStoryByIdSucess(){
        assertEquals(userStoryToRefine,company.getProjectStore().getProductBacklog("Project_2022_1").getUserStoryById(1));
    }
    @Test
    @DisplayName("get User Story By Id Fail")
    public void getUserStoryByIdFail(){
        assertEquals(null,company.getProjectStore().getProductBacklog("Project_2022_1").getUserStoryById(2));
    }

}
