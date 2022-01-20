package switch2021.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.model.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CreateUserStoryControllerTest {




    // Test create userStory (Cris US009)

    @Test
    public void createUserStoryPriorityInvalidInf() {
        //Arrange
        Company company = new Company();
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        Project project = company.getProjectStore().createProject("123testcode", "prototype", "test56", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        company.getProjectStore().addProject(project);

        CreateUserStoryController createUserStoryController = new CreateUserStoryController(company);
        createUserStoryController.getProjectByCode("123testcode");
        UserStoryStatus status = new UserStoryStatus("In progress");
        int priority = -1;
        String description = "teste";
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            createUserStoryController.createUserStory(status, priority, description);
        });
        //Assert
        assertEquals("Check priority, cannot be < 0 or superior to 5.", exception.getMessage());
    }

    @Test
    public void createUserStorydescriptionInvalidEmpty() {
        //Arrange
        Company company = new Company();
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        Project project = company.getProjectStore().createProject("123testcode", "prototype", "test56", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        company.getProjectStore().addProject(project);

        CreateUserStoryController createUserStoryController = new CreateUserStoryController(company);
        createUserStoryController.getProjectByCode("123testcode");
        UserStoryStatus status = new UserStoryStatus("In progress");
        int priority = 1;
        String description = "";
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            createUserStoryController.createUserStory(status, priority, description);
        });
        //Assert
        assertTrue(exception.getMessage().contains("Description cannot be blank."));
    }

    @Test
    public void createUserStorydescriptionInvalidShort() {
        //Arrange
        Company company = new Company();
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        Project project = company.getProjectStore().createProject("123testcode", "prototype", "test56", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        company.getProjectStore().addProject(project);

        CreateUserStoryController createUserStoryController = new CreateUserStoryController(company);
        createUserStoryController.getProjectByCode("123testcode");
        UserStoryStatus status = new UserStoryStatus("In progress");
        int priority = 1;
        String description = "dd";
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            createUserStoryController.createUserStory(status, priority, description);
        });
        //Assert
        assertTrue(exception.getMessage().contains("Description must be at least 5 characters"));
    }

    @Test
    public void createUserStoryPriorityInvalidSup() {
        //Arrange
        Company company = new Company();
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        Project project = company.getProjectStore().createProject("123testcode", "prototype", "test56", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        company.getProjectStore().addProject(project);

        CreateUserStoryController createUserStoryController = new CreateUserStoryController(company);
        createUserStoryController.getProjectByCode("123testcode");
        UserStoryStatus status = new UserStoryStatus("In progress");
        int priority = 6;
        String description = "teste";
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            createUserStoryController.createUserStory(status, priority, description);
        });
        //Assert
        assertEquals("Check priority, cannot be < 0 or superior to 5.", exception.getMessage());
    }

    @Test
    public void createUserStorySuccessFull() {
        //Arrange
        Company company = new Company();
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        Project project = company.getProjectStore().createProject("123testcode", "prototype", "test56", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        company.getProjectStore().addProject(project);

        CreateUserStoryController createUserStoryController = new CreateUserStoryController(company);
        createUserStoryController.getProjectByCode("123testcode");
        UserStoryStatus status = new UserStoryStatus("In progress");
        int priority = 1;
        String description = "teste";

        // Act
        boolean isUserStoryCreated = createUserStoryController.createUserStory(status, priority, description);
        //Assert
        assertNotNull(isUserStoryCreated);
    }

    @Test
    @DisplayName("Criar company + projetos(2) + system user + resource ")
    public void getAllProjectListByUserEmail() {
        //Arrange
        Company company = new Company();
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        Project project = company.getProjectStore().createProject("123testcode", "prototype", "test56", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        company.getProjectStore().addProject(project);
        Project project2 = company.getProjectStore().createProject("123testcode", "prototype", "test56", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        company.getProjectStore().addProject(project2);

        UserProfile userProfile = new UserProfile("zzz");
        SystemUser newUser = new SystemUser("xyz", "cris@ipp.pt", "des", "gth", "gth", "", userProfile);
        LocalDate startDate = LocalDate.of(2021, 12, 31);
        LocalDate endDate = LocalDate.of(2022, 1, 5);
        Resource input = new Resource(newUser, startDate, endDate, 100, .5);
        project.addResource(input);
        project2.addResource(input);
        // Act
        CreateUserStoryController createUserStoryController = new CreateUserStoryController(company);
        List<Project> projectList = createUserStoryController.getAllProjectListByUserEmail("cris@ipp.pt");
        // Assert
        assertEquals(2, projectList.size());
    }

    @Test
    @DisplayName("Criar company + projetos(2) + system user + resource ")
    public void getAllProjectListByUserEmailIsBlank() {
        //Arrange
        Company company = new Company();
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        Project project = company.getProjectStore().createProject("123testcode", "prototype", "test56", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        company.getProjectStore().addProject(project);
        Project project2 = company.getProjectStore().createProject("123testcode", "prototype", "test56", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        company.getProjectStore().addProject(project2);

        UserProfile userProfile = new UserProfile("zzz");
        SystemUser newUser = new SystemUser("xyz", "cris@ipp.pt", "des", "gth", "gth", "", userProfile);
        LocalDate startDate = LocalDate.of(2021, 12, 31);
        LocalDate endDate = LocalDate.of(2022, 1, 5);
        Resource input = new Resource(newUser, startDate, endDate, 100, .5);
        project.addResource(input);
        project2.addResource(input);
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            CreateUserStoryController createUserStoryController = new CreateUserStoryController(company);
            List<Project> projectList = createUserStoryController.getAllProjectListByUserEmail("");
        });
        // Assert
        assertEquals("Email cannot be blank", exception.getMessage());
    }

    @Test
    @DisplayName("Criar company + projetos(2) + system user + resource ")
    public void getAllProjectListByUserEmailDontExist() {
        //Arrange
        Company company = new Company();
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        Project project = company.getProjectStore().createProject("123testcode", "prototype", "test56", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        company.getProjectStore().addProject(project);
        Project project2 = company.getProjectStore().createProject("123testcode", "prototype", "test56", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        company.getProjectStore().addProject(project2);

        UserProfile userProfile = new UserProfile("zzz");
        SystemUser newUser = new SystemUser("xyz", "cris@ipp.pt", "des", "gth", "gth", "", userProfile);
        LocalDate startDate = LocalDate.of(2021, 12, 31);
        LocalDate endDate = LocalDate.of(2022, 1, 5);
        Resource input = new Resource(newUser, startDate, endDate, 100, .5);
        project.addResource(input);
        project2.addResource(input);
        // Act

        CreateUserStoryController createUserStoryController = new CreateUserStoryController(company);
        List<Project> projectList = createUserStoryController.getAllProjectListByUserEmail("dani@ipp.pt");

        // Assert
        assertEquals(0, projectList.size());
    }

}


