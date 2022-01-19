package switch2021.project.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import switch2021.project.model.*;
import switch2021.project.utils.App;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CreateUserStoryControllerTest {

    Company company;
    Project project;

/*
    @BeforeEach
    public void init() {
        company = new Company();
        project = company.getProjectStore().createProject("TEST", "Projecto Test", "criar us",
                new Customer("marreta@email.pt", "name"),
                company.getTypologyStore().getTypology("Fixed Cost"),
                new BusinessSector("description"), LocalDate.now(), 10, 100000);
        project.createUserStory(new UserStoryStatus("In progress"), 12, "Default Story", 6);
        project.setProductOwner(new SystemUser("Test User", "123@isep.ipp.pt",
                "Product Owner", "AAA", "AAA", "", company.getUserProfileStore().getUserProfile("Product Owne")));
        company.getProjectStore().addProject(project);

    }

    @Test
    public void createUserStorySuccessFull() {
        CreateUserStoryController createUserStoryController = new CreateUserStoryController(this.company);
        boolean isUserStoryCreated = createUserStoryController.createUserStory(project.getCode(),
                new UserStoryStatus("To Do"), 12, "New Story", 6);
        assertTrue(isUserStoryCreated);
    }


    @Test
    public void createUserStorydescriptionInvalidEmpty() {
        // Arrange
        CreateUserStoryController createUserStoryController = new CreateUserStoryController(this.company);
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            boolean isUserStoryCreated = createUserStoryController.createUserStory(project.getCode(),
                    new UserStoryStatus("To Do"), 12, "", 6);
        });
        // Assert
        assertTrue(exception.getMessage().contains("Description cannot be blank."));
    }

    @Test
    public void createUserStorydescriptionInvalidShort() {
        // Arrange
        CreateUserStoryController createUserStoryController = new CreateUserStoryController(this.company);
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            boolean isUserStoryCreated = createUserStoryController.createUserStory(project.getCode(),
                    new UserStoryStatus("To Do"), 12, "str", 6);
        });
        // Assert
        assertTrue(exception.getMessage().contains("Description must be at least 5 characters"));
    }

    @Test
    public void createUserStoryPriorityInvalid() {
        // Arrange
        CreateUserStoryController createUserStoryController = new CreateUserStoryController(this.company);
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            boolean isUserStoryCreated = createUserStoryController.createUserStory(project.getCode(),
                    new UserStoryStatus("To Do"), -12, "Fazer testes", 6);
        });
        // Assert
        assertTrue(exception.getMessage().contains("Check priority, cannot be < 0."));
    }

    @Test
    public void createUserStoryAlreadyExist() {
        // Arrange
        CreateUserStoryController createUserStoryController = new CreateUserStoryController(this.company);
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            boolean isUserStoryCreated = createUserStoryController.createUserStory(project.getCode(),
                    new UserStoryStatus("In progress"), 12, "Default Story", 6);
        });
        // Assert
        assertTrue(exception.getMessage().contains("Repeated user story inserted, same code project and description."));
    }

    @Test
    public void createUserStoryCodeProjectInvalidBlank() {
        // Arrange
        CreateUserStoryController createUserStoryController = new CreateUserStoryController(this.company);
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            boolean isUserStoryCreated = createUserStoryController.createUserStory("",
                    new UserStoryStatus("To Do"), 12, "Fazer testes", 6);
        });
        // Assert
        assertTrue(exception.getMessage().contains("Project code is empty."));
    }


    @Test
    public void createUserStoryCodeProjectInvalidNotExist() {
        // Arrange
        CreateUserStoryController createUserStoryController = new CreateUserStoryController(this.company);
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            boolean isUserStoryCreated = createUserStoryController.createUserStory("ssss",
                    new UserStoryStatus("To Do"), 12, "Fazer testes", 6);
        });
        // Assert
        assertTrue(exception.getMessage().contains("Project does not exist."));
    }

    @Test
    public void getProjectListWithPORightWithEmptyEmailAndGetEmptyList() {
        //Arrange
        CreateUserStoryController createUserStoryController = new CreateUserStoryController(this.company);
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            List<Project> projectList = createUserStoryController.getProjectListWithPORight("");
            List<Project> projectList2 = createUserStoryController.getProjectListWithPORight(null);
        });
        //Assert
        assertTrue(exception.getMessage().contains("Invalid email inserted"));
    }


    @Test
    public void getProjectListWithPORighListWithOneResults() {
        //Arrange
        CreateUserStoryController createUserStoryController = new CreateUserStoryController(this.company);
        // Act
        List<Project> projectList = createUserStoryController.getProjectListWithPORight("123@isep.ipp.pt");
        //Assert
        assertEquals(1, projectList.size());

    }

    @Test
    public void getProjectListWithPORighListWithTwoResults() {
        //Arrange
        CreateUserStoryController createUserStoryController = new CreateUserStoryController(this.company);
        project = company.getProjectStore().createProject("other TEST", "CDC", "criar projeto",
                new Customer("marreta@email.pt", "name"),
                company.getTypologyStore().getTypology("Fixed Cost"),
                new BusinessSector("description"), LocalDate.now(), 10, 100000);
        project.setProductOwner(new SystemUser("Test User", "123@isep.ipp.pt",
                "Product Owner", "AAA", "AAA", "", company.getUserProfileStore().getUserProfile("Product Owne")));
        company.getProjectStore().addProject(project);
        // Act
        List<Project> projectList = createUserStoryController.getProjectListWithPORight("123@isep.ipp.pt");
        //Assert
        assertEquals(2, projectList.size());

    }
    //TODO - ajustar estes testes ao controller
     *//**
     * >>>>>> Tests from userStory <<<<<<
     **/
/*
    // Test adding userStory to the project (Cris US009)
    @Test
    public void createUserStoryPriorityIsInvalid() {
        //Arrange

        CreateUserStoryController createUserStoryController = new CreateUserStoryController();
        UserStoryStatus status = new UserStoryStatus("In progress");
        int priority = -1;
        String description = "teste";
        // Act
           boolean userStory  = createUserStoryController.createUserStory(status, priority, description);
        //Assert
        assertFalse(userStory);
    }


    @Test
    public void createUserStoryUserStoryAlreadyExist() {
        //Arrange
        String code = "123d";
        newProject.setCode(code);
        UserStoryStatus status = UserStoryStatus.TODO;
        int priority = 1;
        String description = "teste";
        int timeEstimate = 7;
        newProject.createUserStory(status, priority, description, timeEstimate);
        // Act
        boolean isAdded = newProject.createUserStory(UserStoryStatus.IN_TEST, 2, description, 8);
        //Assert
        assertFalse(isAdded);
    }

    @Test
    public void createUserStoryTimeEstimateInvalid() {
        //Arrange
        String code = "123d";
        newProject.setCode(code);
        UserStoryStatus status = UserStoryStatus.TODO;
        int priority = 1;
        String description = "teste";
        int timeEstimate = -1;
        // Act
        boolean isAdded = newProject.createUserStory(status, priority, description, timeEstimate);
        //Assert
        assertFalse(isAdded);
    }

    @Test
    public void createUserStoryDescriptionInvalid() {
        //Arrange
        String code = "123d";
        newProject.setCode(code);
        UserStoryStatus status = UserStoryStatus.TODO;
        int priority = 1;
        String description = null;
        int timeEstimate = 1;
        // Act
        boolean isAdded = newProject.createUserStory(status, priority, description, timeEstimate);
        //Assert
        assertFalse(isAdded);
    }

    @Test
    public void createUserStoryWithSuccess() {
        //Arrange
        String code = "123d";
        newProject.setCode(code);
        UserStoryStatus status = UserStoryStatus.TODO;
        int priority = 1;
        String description = "teste";
        int timeEstimate = 7;
        // Act
        boolean isAdded = newProject.createUserStory(status, priority, description, timeEstimate);
        //Assert
        assertTrue(isAdded);
    }

*/
}

