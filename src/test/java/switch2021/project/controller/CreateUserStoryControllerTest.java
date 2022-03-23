package switch2021.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.dto.ProjectDTO;
import switch2021.project.dto.UserStoryDto;
import switch2021.project.mapper.ProjectsMapper;
import switch2021.project.mapper.UserStoryMapper;
import switch2021.project.model.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateUserStoryControllerTest {

    Company company = new Company();
    ProjectsMapper mapper = new ProjectsMapper();
    UserStoryMapper mapperUS = new UserStoryMapper();
    Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
    Customer customer = company.getCustomerStore().getCustomerByName("Teste");
    BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
    Project project = company.getProjectStore().createProject("prototype", "test56", customer,
            typo, sector, LocalDate.now(), 7, 5000);
    Project project2 = company.getProjectStore().createProject("prototype", "test56", customer,
            typo, sector, LocalDate.now(), 7, 5000);
    UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
    SystemUser newUser = new SystemUser("xyz", "cris@ipp.pt", "des", "Qwerty_1", "Qwerty_1", "", userProfile);
    LocalDate startDate = LocalDate.of(2021, 12, 31);
    LocalDate endDate = LocalDate.of(2022, 1, 5);
    Resource input = new Resource(newUser, startDate, endDate, 100, .5);


    @Test
    @DisplayName("Test create userStory - US009")
    public void createUserStoryPriorityInvalidInf() {
        //Arrange
        company.getProjectStore().saveNewProject(project);
        CreateUserStoryController createUserStoryController = new CreateUserStoryController(company, mapper, mapperUS);
        int priority = -1;
        String description = "teste";
        UserStoryStatus status = new UserStoryStatus("To do");
        UserStoryDto userStoryDto = new UserStoryDto("Teste", status, priority, description);


        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                createUserStoryController.createUserStory("Project_2022_1", userStoryDto));
        //Assert
        assertEquals("Check priority, cannot be < 0 or superior to 5.", exception.getMessage());
    }


    @Test
    public void createUserStoryDescriptionInvalidEmpty() {
        //Arrange
        company.getProjectStore().saveNewProject(project);

        CreateUserStoryController createUserStoryController = new CreateUserStoryController(company, mapper, mapperUS);
        int priority = 1;
        String description = "";
        String description2 = "   ";
        String description3 = null;
        UserStoryStatus status = new UserStoryStatus("To do");

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> createUserStoryController.createUserStory("Project_2022_1", new UserStoryDto("Teste", status, priority, description)));
        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> createUserStoryController.createUserStory("Project_2022_1", new UserStoryDto("Teste", status, priority, description2)));
        Exception exception3 = assertThrows(NullPointerException.class, () -> createUserStoryController.createUserStory("Project_2022_1", new UserStoryDto("Teste", status, priority, description3)));
        //Assert
        assertTrue(exception.getMessage().contains("Description field requires at least " + 1 + " characters"));
        assertTrue(exception2.getMessage().contains("Description field requires at least " + 1 + " characters"));
        assertTrue(exception3.getMessage().contains("Description field requires at least " + 1 + " characters"));
    }


    @Test
    public void createUserStoryNameInvalid() {
        //Arrange
        company.getProjectStore().saveNewProject(project);

        CreateUserStoryController createUserStoryController = new CreateUserStoryController(company, mapper, mapperUS);
        int priority = 1;
        String description = "Make test";
        String name = null;
        String name2 = "d";
        String name3 = "dd";
        String name4 = "";
        UserStoryStatus status = new UserStoryStatus("To do");
        UserStoryDto userStoryDto = new UserStoryDto(name, status, priority, description);
        UserStoryDto userStoryDto2 = new UserStoryDto(name2, status, priority, description);
        UserStoryDto userStoryDto3 = new UserStoryDto(name3, status, priority, description);
        UserStoryDto userStoryDto4 = new UserStoryDto(name4, status, priority, description);

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> createUserStoryController.createUserStory("Project_2022_1", userStoryDto));
        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> createUserStoryController.createUserStory("Project_2022_1", userStoryDto2));
        Exception exception3 = assertThrows(IllegalArgumentException.class, () -> createUserStoryController.createUserStory("Project_2022_1", userStoryDto3));
        Exception exception4 = assertThrows(IllegalArgumentException.class, () -> createUserStoryController.createUserStory("Project_2022_1", userStoryDto4));


        //Assert
        assertTrue(exception.getMessage().contains("Name cannot be blank."));
        assertTrue(exception2.getMessage().contains("Name must be at least 3 characters"));
        assertTrue(exception3.getMessage().contains("Name must be at least 3 characters"));
        assertTrue(exception4.getMessage().contains("Name cannot be blank."));

    }

    @Test
    public void createUserStoryPriorityInvalidSup() {
        //Arrange

        company.getProjectStore().saveNewProject(project);

        CreateUserStoryController createUserStoryController = new CreateUserStoryController(company, mapper, mapperUS);
        int priority = 6;
        String description = "teste";
        UserStoryStatus status = new UserStoryStatus("To do");
        UserStoryDto userStoryDto = new UserStoryDto("Teste", status, priority, description);
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> createUserStoryController.createUserStory("Project_2022_1", userStoryDto));
        assertEquals("Check priority, cannot be < 0 or superior to 5.", exception.getMessage());
    }

    @Test
    public void createUserStorySuccessFull() {
        //Arrange
        company.getProjectStore().saveNewProject(project);
        CreateUserStoryController createUserStoryController = new CreateUserStoryController(company, mapper, mapperUS);
        int priority = 1;
        int priority2 = 5;
        int priority3 = 0;
        String description = "teste";
        String name = "teste";
        UserStoryStatus status = new UserStoryStatus("To do");
        UserStoryDto userStoryDto = new UserStoryDto(name, status, priority, description);

        // Act
        createUserStoryController.createUserStory("Project_2022_1", userStoryDto);

        //Assert
        assertNotNull(userStoryDto);
        assertEquals(priority, userStoryDto.getPriority());
        assertEquals(description, userStoryDto.getDescription().getText());
        assertEquals(name, userStoryDto.getTitle());

    }

    @Test
    public void createUserStorySuccessFullDescriptionOnLimitSize() {
        //Arrange
        company.getProjectStore().saveNewProject(project);

        CreateUserStoryController createUserStoryController = new CreateUserStoryController(company, mapper, mapperUS);
        int priority = 1;
        String description = "t";
        String name = "teste";
        UserStoryStatus status = new UserStoryStatus("To do");
        UserStoryDto userStoryDto = new UserStoryDto(name, status, priority, description);

        // Act
        createUserStoryController.createUserStory("Project_2022_1", userStoryDto);

        //Assert
        assertNotNull(userStoryDto);
        assertEquals(priority, userStoryDto.getPriority());
        assertEquals(description, userStoryDto.getDescription().getText());
        assertEquals(name, userStoryDto.getTitle());

    }

    @Test
    public void createUserStorySuccessFullValidateInfo() {
        company.getProjectStore().saveNewProject(project);
        UserStoryStatus status = new UserStoryStatus("To do");
        int priority = 1;
        String description = "teste";
        UserStoryDto userStoryDto = new UserStoryDto("Teste", status, priority, description);
        CreateUserStoryController createUserStoryController = new CreateUserStoryController(company, mapper, mapperUS);
        createUserStoryController.createUserStory("Project_2022_1", userStoryDto);

        ProductBacklog project_2022_1 = company.getProjectStore().getProjectByCode("Project_2022_1").getProductBacklog();
        assertEquals(1, project_2022_1.getUserStoryList().size());
        assertEquals(userStoryDto.getTitle(), project_2022_1.getUserStoryList().get(0).getTitle());
        assertEquals(userStoryDto.getPriority(), project_2022_1.getUserStoryList().get(0).getPriority());
        assertEquals(userStoryDto.getUserStoryStatus(), project_2022_1.getUserStoryList().get(0).getUserStoryStatus());
        assertEquals(userStoryDto.getDescription().getText(), project_2022_1.getUserStoryList().get(0).getDescription().getText());
    }


    @Test
    public void getAllProjectListByUserEmail() {
        //Arrange
        company.getProjectStore().saveNewProject(project);
        company.getProjectStore().saveNewProject(project2);
        project.addResource(input);
        project2.addResource(input);
        // Act
        CreateUserStoryController createUserStoryController = new CreateUserStoryController(company, mapper, mapperUS);
        List<ProjectDTO> projectList = createUserStoryController.getProjectListByUserEmail("cris@ipp.pt");
        // Assert
        assertEquals(2, projectList.size());
        assertEquals(project.getCode(), projectList.get(0).getCode());
        assertEquals(project.getProjectName(), projectList.get(0).getProjectName());
        assertEquals(project.getDescription().getText(), projectList.get(0).getDescription());
        assertEquals(project.getEndDate(), projectList.get(0).getEndDate());
        assertEquals(project2.getCode(), projectList.get(1).getCode());
        assertEquals(project2.getProjectName(), projectList.get(1).getProjectName());
        assertEquals(project2.getDescription().getText(), projectList.get(1).getDescription());
        assertEquals(project2.getEndDate(), projectList.get(1).getEndDate());
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
            CreateUserStoryController createUserStoryController = new CreateUserStoryController(company, mapper, mapperUS);
            createUserStoryController.getProjectListByUserEmail("");
        });
        // Assert
        assertEquals("Email cannot be blank", exception.getMessage());
    }

    @Test
    @DisplayName("Create company + projets(2) + system user + resource ")
    public void getAllProjectListByUserEmailDontExist() {
        //Arrange
        company.getProjectStore().saveNewProject(project);
        company.getProjectStore().saveNewProject(project2);
        project.addResource(input);
        project2.addResource(input);
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            CreateUserStoryController createUserStoryController = new CreateUserStoryController(company, mapper, mapperUS);
            createUserStoryController.getProjectListByUserEmail("dani@ipp.pt");
        });
        // Assert
        assertEquals("Email is not associated with any project", exception.getMessage());
    }
}


