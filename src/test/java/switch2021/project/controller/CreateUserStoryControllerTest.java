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
    public void createUserStoryTimeEstimateInvalid() {
        // Arrange
        CreateUserStoryController createUserStoryController = new CreateUserStoryController(this.company);
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            boolean isUserStoryCreated = createUserStoryController.createUserStory(project.getCode(),
                    new UserStoryStatus("To Do"), 12, "New Story", -6);
        });
        // Assert
        assertTrue(exception.getMessage().contains("Check time estimate, cannot be < 0."));
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
        assertTrue(exception.getMessage().contains("Repeated user story description inserted."));
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
        assertTrue(exception.getMessage().contains("Project does not exist."));
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
                "Product Owner", "AAA", "AAA", "", company.getUserProfile("Product Owne")));
        company.getProjectStore().addProject(project);
        // Act
        List<Project> projectList = createUserStoryController.getProjectListWithPORight("123@isep.ipp.pt");
        //Assert
        assertEquals(2, projectList.size());

    }

}

