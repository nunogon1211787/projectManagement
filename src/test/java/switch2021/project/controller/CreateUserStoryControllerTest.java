package switch2021.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.model.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CreateUserStoryControllerTest {

    Company company = new Company();
    Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
    Customer customer = company.getCustomerStore().getCustomerByName("Teste");
    BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
    Project project = company.getProjectStore().createProject( "prototype", "test56", customer,
            typo, sector, LocalDate.now(), 7, 5000);
    Project project2 = company.getProjectStore().createProject( "prototype", "test56", customer,
            typo, sector, LocalDate.now(), 7, 5000);
    UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
    SystemUser newUser = new SystemUser("xyz", "cris@ipp.pt", "des", "gth", "gth", "", userProfile);
    LocalDate startDate = LocalDate.of(2021, 12, 31);
    LocalDate endDate = LocalDate.of(2022, 1, 5);
    Resource input = new Resource(newUser, startDate, endDate, 100, .5);


    @Test
    @DisplayName("Test create userStory - US009")
    public void createUserStoryPriorityInvalidInf() {
        //Arrange
        company.getProjectStore().saveNewProject(project);
        CreateUserStoryController createUserStoryController = new CreateUserStoryController(company);
        createUserStoryController.getProjectByCode("Project_2022_1");
        UserStoryStatus status = new UserStoryStatus("In progress");
        int priority = -1;
        String description = "teste";
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                createUserStoryController.createUserStory("US001", priority, description,5));
        //Assert
        assertEquals("Check priority, cannot be < 0 or superior to 5.", exception.getMessage());
    }

    @Test
    public void createUserStoryDescriptionInvalidEmpty() {
        //Arrange
               company.getProjectStore().saveNewProject(project);

        CreateUserStoryController createUserStoryController = new CreateUserStoryController(company);
        createUserStoryController.getProjectByCode("Project_2022_1");
        UserStoryStatus status = new UserStoryStatus("In progress");
        int priority = 1;
        String description = "";
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> createUserStoryController.createUserStory("US001", priority, description,5));
        //Assert
        assertTrue(exception.getMessage().contains("Description cannot be blank."));
    }

    @Test
    public void createUserStoryDescriptionInvalidShort() {
        //Arrange

        company.getProjectStore().saveNewProject(project);

        CreateUserStoryController createUserStoryController = new CreateUserStoryController(company);
        createUserStoryController.getProjectByCode("Project_2022_1");
        UserStoryStatus status = new UserStoryStatus("In progress");
        int priority = 1;
        String description = "dd";
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> createUserStoryController.createUserStory("US001", priority, description,5));
        //Assert
        assertTrue(exception.getMessage().contains("Description must be at least 5 characters"));
    }

    @Test
    public void createUserStoryPriorityInvalidSup() {
        //Arrange

        company.getProjectStore().saveNewProject(project);

        CreateUserStoryController createUserStoryController = new CreateUserStoryController(company);
        createUserStoryController.getProjectByCode("Project_2022_1");
        UserStoryStatus status = new UserStoryStatus("In progress");
        int priority = 6;
        String description = "teste";
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> createUserStoryController.createUserStory("US001", priority, description,5));
        //Assert
        assertEquals("Check priority, cannot be < 0 or superior to 5.", exception.getMessage());
    }

    @Test
    public void createUserStorySuccessFull() {
        //Arrange
        company.getProjectStore().saveNewProject(project);

        CreateUserStoryController createUserStoryController = new CreateUserStoryController(company);
        createUserStoryController.getProjectByCode("Project_2022_1");
        UserStoryStatus status = new UserStoryStatus("In progress");
        int priority = 1;
        String description = "teste";

        // Act
        boolean isUserStoryCreated = createUserStoryController.createUserStory("US001", priority, description,5);
        //Assert
        assertTrue(isUserStoryCreated);
    }

    @Test
    public void getAllProjectListByUserEmail() {
        //Arrange
        company.getProjectStore().saveNewProject(project);
        company.getProjectStore().saveNewProject(project2);
        project.addResource(input);
        project2.addResource(input);
        // Act
        CreateUserStoryController createUserStoryController = new CreateUserStoryController(company);
        List<Project> projectList = createUserStoryController.getProjectListByUserEmail("cris@ipp.pt");
        // Assert
        assertEquals(2, projectList.size());
    }

    @Test
    public void getAllProjectListByUserEmailIsBlank() {
        //Arrange
        company.getProjectStore().saveNewProject(project);
        company.getProjectStore().saveNewProject(project2);
        project.addResource(input);
        project2.addResource(input);
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            CreateUserStoryController createUserStoryController = new CreateUserStoryController(company);
            List<Project> projectList = createUserStoryController.getProjectListByUserEmail("");
        });
        // Assert
        assertEquals("Email cannot be blank", exception.getMessage());
    }

    @Test
    @DisplayName("Criar company + projetos(2) + system user + resource ")
    public void getAllProjectListByUserEmailDontExist() {
        //Arrange
        company.getProjectStore().saveNewProject(project);
        company.getProjectStore().saveNewProject(project2);
        project.addResource(input);
        project2.addResource(input);
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        CreateUserStoryController createUserStoryController = new CreateUserStoryController(company);
        List<Project> projectList = createUserStoryController.getProjectListByUserEmail("dani@ipp.pt");
        });
        // Assert
        assertEquals("Email don't exist in system", exception.getMessage());
    }

}


