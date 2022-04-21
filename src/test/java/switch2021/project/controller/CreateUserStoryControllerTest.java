package switch2021.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.dto.ProjectDTO;
import switch2021.project.dto.UserStoryDto;
import switch2021.project.mapper.ProjectsMapper;
import switch2021.project.mapper.UserStoryMapper;
import switch2021.project.model.*;
import switch2021.project.model.Project.*;
import switch2021.project.model.Resource.Resource;
import switch2021.project.model.SystemUser.SystemUser;
import switch2021.project.model.Typology.Typology;
import switch2021.project.model.UserProfile.UserProfile;
import switch2021.project.model.UserStory.UserStoryStore;
import switch2021.project.model.valueObject.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateUserStoryControllerTest {

    Company company = new Company();
    ProjectsMapper mapper = new ProjectsMapper();
    UserStoryMapper mapperUS = new UserStoryMapper();
    Typology typo = company.getTypologyRepository().findTypologyByDescription("Fixed Cost");
    Customer customer = company.getCustomerStore().getCustomerByName("Teste");
    BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
    Project project = company.getProjectStore().createAndSaveProject("prototype", "test56", customer,
            typo, sector, LocalDate.now(), 7, 5000);
    Project project2 = company.getProjectStore().createAndSaveProject("prototype", "test56", customer,
            typo, sector, LocalDate.now(), 7, 5000);
    UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
    SystemUser newUser = new SystemUser("xyz", "cris@ipp.pt", "des", "Qwerty_1", "Qwerty_1", ".png", userProfile.getUserProfileId());
    LocalDate startDate = LocalDate.of(2021, 12, 31);
    LocalDate endDate = LocalDate.of(2022, 1, 5);
    Resource input = new Resource(newUser, startDate, endDate, new CostPerHour(100), new PercentageOfAllocation(.5));


    @Test
    @DisplayName("Test create userStory - US009")
    public void createUserStoryPriorityInvalidInf() {
        //Arrange
        CreateUserStoryController createUserStoryController = new CreateUserStoryController(company, mapper, mapperUS);
        int priority = -1;
        String description = "teste";



        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->{
            UserStoryDto userStoryDto = new UserStoryDto("Project_2022_1","Project_2022_1_As a PO, i want to test this string","As a PO, i want to test this string", priority, description,10);
            createUserStoryController.createUserStory("Project_2022_1", userStoryDto);

    });
        //Assert
        assertEquals("Check priority, cannot be < 0 or superior to 5", exception.getMessage());
    }

//TODO CDC ver pq falha teste descrição

//    @Test
//    public void createUserStoryDescriptionInvalidEmpty() {
//        //Arrange
//        company.getProjectStore().saveNewProject(project);
//
//        CreateUserStoryController createUserStoryController = new CreateUserStoryController(company, mapper, mapperUS);
//        int priority = 1;
//
//        // Act
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> createUserStoryController.createUserStory("Project_2022_1", new UserStoryDto("Teste", priority, "",10)));
//        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> createUserStoryController.createUserStory("Project_2022_1", new UserStoryDto("Teste", priority, "   ",10)));
////        Exception exception3 = assertThrows(NullPointerException.class, () -> createUserStoryController.createUserStory("Project_2022_1", new UserStoryDto("Teste",  priority, null,8)));
//        //Assert
//        assertTrue(exception.getMessage().contains("Description field requires at least " + 1 + " characters"));
//        assertTrue(exception2.getMessage().contains("Description field requires at least " + 1 + " characters"));
////        assertTrue(exception3.getMessage().contains("Description field requires at least " + 1 + " characters"));
//    }


    @Test
    public void createUserStoryTitleInvalid() {
        //Arrange

        CreateUserStoryController createUserStoryController = new CreateUserStoryController(company, mapper, mapperUS);
        int priority = 1;
        String description = "Make test";
        String title2 = "d";
        String title3 = "As a PO, i to test this string";
        String title4 = "";
        String id = "Project_2022_1_As a PO, i want to test this string";
        String id2 = "Project_2022_2_As a PO, i want to test this string";
        String id3 = "Project_2022_3_As a PO, i want to test this string";

        // Act
        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> createUserStoryController.createUserStory("Project_2022_1", new UserStoryDto("Project_2022_1",id,title2, priority, description,7)));
        Exception exception3 = assertThrows(IllegalArgumentException.class, () -> createUserStoryController.createUserStory("Project_2022_1", new UserStoryDto("Project_2022_1",id2,title3,  priority, description,9)));
        Exception exception4 = assertThrows(IllegalArgumentException.class, () -> createUserStoryController.createUserStory("Project_2022_1", new UserStoryDto("Project_2022_1",id3,title4,  priority, description,54)));


        //Assert

        assertTrue(exception2.getMessage().contains("Title need to begin with - as"));
        assertTrue(exception3.getMessage().contains("Title don't contain the word want"));
        assertTrue(exception4.getMessage().contains("Title cannot be blank"));

    }

    @Test
    public void createUserStoryPriorityInvalidSup() {
        //Arrange
        CreateUserStoryController createUserStoryController = new CreateUserStoryController(company, mapper, mapperUS);
        int priority = 6;
        String description = "teste";
        String id = "Project_2022_1_As a PO, i want to test this string";
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> createUserStoryController.createUserStory("Project_2022_1", new UserStoryDto("Project_2022_1",id,"As a PO, i want to test this string", priority, description,40)));
        assertEquals("Check priority, cannot be < 0 or superior to 5", exception.getMessage());
    }

    @Test
    public void createUserStorySuccessFull() {
        //Arrange
        CreateUserStoryController createUserStoryController = new CreateUserStoryController(company, mapper, mapperUS);
        int priority = 1;
        String description = "teste";
        String title = "As a PO, i want to test this string";
        String id="Project_2022_1_As a PO, i want to test this string";
        UserStoryDto userStoryDto = new UserStoryDto("Project_2022_1",id,title, priority, description,4);

        // Act
        createUserStoryController.createUserStory("Project_2022_1", userStoryDto);

        //Assert
        assertNotNull(userStoryDto);
        assertEquals(priority, userStoryDto.getPriority().getPriorityUs());
        assertEquals(description, userStoryDto.getDescription().getText());
        assertEquals(title, userStoryDto.getTitle().getTitleUs());

    }

    @Test
    public void createUserStorySuccessFullDescriptionOnLimitSize() {
        //Arrange

        CreateUserStoryController createUserStoryController = new CreateUserStoryController(company, mapper, mapperUS);
        int priority = 1;
        String description = "t";
        String title = "As a PO, i want to test this string";
        String id="Project_2022_1_As a PO, i want to test this string";

        UserStoryDto userStoryDto = new UserStoryDto("Project_2022_1",id, title, priority, description,7);

        // Act
        createUserStoryController.createUserStory("Project_2022_1", userStoryDto);

        //Assert
        assertNotNull(userStoryDto);
        assertEquals(priority, userStoryDto.getPriority().getPriorityUs());
        assertEquals(description, userStoryDto.getDescription().getText());
        assertEquals(title, userStoryDto.getTitle().getTitleUs());

    }

    @Test
    public void createUserStorySuccessFullValidateInfo() {
        int priority = 1;
        String description = "teste";
        UserStoryDto userStoryDto = new UserStoryDto("Project_2022_1","Project_2022_1_As a PO, i want to test this string","As a PO, i want to test this string", priority, description,6);
        CreateUserStoryController createUserStoryController = new CreateUserStoryController(company, mapper, mapperUS);
        createUserStoryController.createUserStory("Project_2022_1", userStoryDto);

        UserStoryStore project_2022_1 = company.getProjectStore().findProjectByID("Project_2022_1").getUserStoryStore();
        assertEquals(1, project_2022_1.getUserStoryList().size());
        assertEquals(userStoryDto.getTitle().getTitleUs(), project_2022_1.getUserStoryList().get(0).getTitle().getTitleUs());
        assertEquals(userStoryDto.getPriority().getPriorityUs(), project_2022_1.getUserStoryList().get(0).getPriority().getPriorityUs());
        assertEquals(userStoryDto.getDescription().getText(), project_2022_1.getUserStoryList().get(0).getDescription().getText());
    }


    @Test
    public void getAllProjectListByUserEmail() {
        //Arrange
        project.addResource(input);
        project2.addResource(input);
        // Act
        CreateUserStoryController createUserStoryController = new CreateUserStoryController(company, mapper, mapperUS);
        List<ProjectDTO> projectList = createUserStoryController.getProjectListByUserEmail("cris@ipp.pt");
        // Assert
        assertEquals(2, projectList.size());
        assertEquals(project.getProjectCode().getCode(), projectList.get(0).code);
        assertEquals(project.getProjectName().getText(), projectList.get(0).projectName);
        assertEquals(project.getDescription().getText(), projectList.get(0).description);
        assertEquals(project2.getProjectCode().getCode(), projectList.get(1).code);
        assertEquals(project2.getProjectName().getText(), projectList.get(1).projectName);
        assertEquals(project2.getDescription().getText(), projectList.get(1).description);
    }

    @Test
    public void getAllProjectListByUserEmailIsBlank() {
        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            project.addResource(input);
            project2.addResource(input);
            // Act
            CreateUserStoryController createUserStoryController = new CreateUserStoryController(company, mapper, mapperUS);
            createUserStoryController.getProjectListByUserEmail("");
        });
    }

    @Test
    @DisplayName("Create company + projets(2) + system user + resource ")
    public void getAllProjectListByUserEmailDontExist() {
        //Arrange
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


