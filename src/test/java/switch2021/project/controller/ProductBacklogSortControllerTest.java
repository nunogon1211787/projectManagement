package switch2021.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.dto.ProjectDTO;
import switch2021.project.dto.UserStoryDto;
import switch2021.project.factory.ResourceFactory;
import switch2021.project.mapper.ProductBacklogMapper;
import switch2021.project.mapper.ProjectsMapper;
import switch2021.project.model.*;
import switch2021.project.model.Project.*;
import switch2021.project.model.SystemUser.SystemUser;
import switch2021.project.model.valueObject.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class ProductBacklogSortControllerTest {


    @Test
    @DisplayName("check if the list size is correct")
    public void getProjectListByUserEmailWith2Projects() {
        //Arrange
        Company company = new Company();
        ProjectsMapper mapper = new ProjectsMapper();
        ProductBacklogMapper mapperPB = new ProductBacklogMapper();
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        Project project = company.getProjectStore().createProject("prototype", "test56", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        Project project2 = company.getProjectStore().createProject("prototype", "test56", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser("xyz", "cris@ipp.pt", "des", "Qwerty_1", "Qwerty_1", "photo.png", userProfile);
        LocalDate startDate = LocalDate.of(2021, 12, 31);
        LocalDate endDate = LocalDate.of(2022, 1, 5);
        Resource input = new Resource(newUser, startDate, endDate, new CostPerHour(100), new PercentageOfAllocation(.5));
        company.getProjectStore().saveNewProject(project);
        company.getProjectStore().saveNewProject(project2);
        project.addResource(input);
        project2.addResource(input);
        // Act
        ProductBacklogSortController productBacklogSortController = new ProductBacklogSortController(company, mapper, mapperPB);
        List<ProjectDTO> projectList = productBacklogSortController.getProjectListByUserEmail("cris@ipp.pt");
        // Assert
        assertEquals(2, projectList.size());

    }

    @Test
    @DisplayName("check if the created DTO project list contains correct information")
    void getProjectListDTOSuccess() {
        //Arrange
        Company company = new Company();
        ProjectsMapper mapper = new ProjectsMapper();
        ProductBacklogMapper mapperPB = new ProductBacklogMapper();
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        Project project = company.getProjectStore().createProject("prototype", "test56", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        Project project2 = company.getProjectStore().createProject("prototype", "test56", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser("xyz", "cris@ipp.pt", "des", "Qwerty_1", "Qwerty_1", "photo.png", userProfile);
        LocalDate startDate = LocalDate.of(2021, 12, 31);
        LocalDate endDate = LocalDate.of(2022, 1, 5);
        Resource input = new Resource(newUser, startDate, endDate, new CostPerHour(100), new PercentageOfAllocation(.5));
        company.getProjectStore().saveNewProject(project);
        company.getProjectStore().saveNewProject(project2);
        project.addResource(input);
        project2.addResource(input);
        // Act
        ProductBacklogSortController productBacklogSortController = new ProductBacklogSortController(company, mapper, mapperPB);
        List<ProjectDTO> projectList = productBacklogSortController.getProjectListByUserEmail("cris@ipp.pt");
        // Assert
        assertEquals(project.getCode(), projectList.get(0).getCode());
        assertEquals(project.getProjectName().getText(), projectList.get(0).getProjectName());
        assertEquals(project.getDescription().getText(), projectList.get(0).getDescription());
        assertEquals(project2.getCode(), projectList.get(1).getCode());
        assertEquals(project2.getProjectName().getText(), projectList.get(1).getProjectName());
        assertEquals(project2.getDescription().getText(), projectList.get(1).getDescription());
    }

    @Test
    @DisplayName("get exception message  \"Email cannot be blank“;\n")
    public void getProjectListByUserEmailIsBlank() {
        //Arrange
        Company company = new Company();
        ProjectsMapper mapper = new ProjectsMapper();
        ProductBacklogMapper mapperPB = new ProductBacklogMapper();
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        Project project = company.getProjectStore().createProject("prototype", "test56", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        Project project2 = company.getProjectStore().createProject("prototype", "test56", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser("xyz", "cris@ipp.pt", "des", "Qwerty_1", "Qwerty_1", "photo.png", userProfile);
        LocalDate startDate = LocalDate.of(2021, 12, 31);
        LocalDate endDate = LocalDate.of(2022, 1, 5);
        Resource input = new Resource(newUser, startDate, endDate, new CostPerHour(100), new PercentageOfAllocation(.5));

        company.getProjectStore().saveNewProject(project);
        company.getProjectStore().saveNewProject(project2);
        project.addResource(input);
        project2.addResource(input);
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ProductBacklogSortController productBacklogSortController = new ProductBacklogSortController(company, mapper, mapperPB);
            productBacklogSortController.getProjectListByUserEmail("");
        });
        // Assert
        assertEquals("Email cannot be blank", exception.getMessage());
    }

    @Test
    @DisplayName("get exception message  \"Email is not associated with any project")
    public void getAllProjectListByUserEmailDontExist() {
        //Arrange
        Company company = new Company();
        ProjectsMapper mapper = new ProjectsMapper();
        ProductBacklogMapper mapperPB = new ProductBacklogMapper();
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        Project project = company.getProjectStore().createProject("prototype", "test56", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        Project project2 = company.getProjectStore().createProject("prototype", "test56", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser("xyz", "cris@ipp.pt", "des", "Qwerty_1", "Qwerty_1", "photo.png", userProfile);
        LocalDate startDate = LocalDate.of(2021, 12, 31);
        LocalDate endDate = LocalDate.of(2022, 1, 5);
        Resource input = new Resource(newUser, startDate, endDate, new CostPerHour(100), new PercentageOfAllocation(.5));

        company.getProjectStore().saveNewProject(project);
        company.getProjectStore().saveNewProject(project2);
        project.addResource(input);
        project2.addResource(input);
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ProductBacklogSortController productBacklogSortController = new ProductBacklogSortController(company, mapper, mapperPB);
            productBacklogSortController.getProjectListByUserEmail("dani@ipp.pt");
        });
        // Assert
        assertEquals("Email is not associated with any project", exception.getMessage());

    }

    @Test
    @DisplayName("grants a list of US that is sorted by priority. It keeps the done and/or cancelled US on the end\n")
    public void getSortedListWithSuccess() {
        Company company = new Company();
        ProjectsMapper mapper = new ProjectsMapper();
        ProductBacklogMapper mapperPB = new ProductBacklogMapper();
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        Project project = company.getProjectStore().createProject("prototype", "test56", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser("xyz", "cris@ipp.pt", "des", "Qwerty_1", "Qwerty_1", "photo.png", userProfile);
        LocalDate startDate = LocalDate.of(2021, 12, 31);
        LocalDate endDate = LocalDate.of(2022, 1, 5);
        Resource input = new Resource(newUser, startDate, endDate, new CostPerHour(100), new PercentageOfAllocation(.5));
        ResourceFactory resFac = mock(ResourceFactory.class);
        ProjectTeam projectTeam = new ProjectTeam(resFac);// Arrange

        company.getProjectStore().saveNewProject(project);
        project.getProductBacklog().createAndSaveUserStory("As a PO, i want to test this string", 2, "create user story", 5);
        project.getProductBacklog().createAndSaveUserStory("As a PO, i want to test this string", 1, "sort user story", 5);
        project.getProductBacklog().createAndSaveUserStory("As a PO, i want to test this string", 3, "backlog sorted", 5);
        project.getProductBacklog().createAndSaveUserStory("As a PO, i want to test this string", 5, "show sorted", 5);

        projectTeam.saveResource(input);
        project.setProjectTeam(projectTeam);
        // Act
        ProductBacklogSortController productBacklogSortController = new ProductBacklogSortController(company, mapper, mapperPB);
        productBacklogSortController.getProjectListByUserEmail("cris@ipp.pt");
        List<UserStoryDto> userStoryListDtoList = productBacklogSortController.getUsSortedByPriority("Project_2022_1");
        // Assert
        assertEquals(4, userStoryListDtoList.size());
        assertEquals(1, userStoryListDtoList.get(0).getPriority());
        assertEquals(2, userStoryListDtoList.get(1).getPriority());
        assertEquals(3, userStoryListDtoList.get(2).getPriority());
        assertEquals(5, userStoryListDtoList.get(3).getPriority());
    }

    @Test
    @DisplayName("get exception message \"Check priority, cannot be < 0 or superior to 5.“")

    public void getSortedListFailWrongPriority() {
        // Arrange
        Company company = new Company();
        ProjectsMapper mapper = new ProjectsMapper();
        ProductBacklogMapper mapperPB = new ProductBacklogMapper();
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        Project project = company.getProjectStore().createProject("prototype", "test56", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser("xyz", "cris@ipp.pt", "des", "Qwerty_1", "Qwerty_1", "photo.png", userProfile);
        LocalDate startDate = LocalDate.of(2021, 12, 31);
        LocalDate endDate = LocalDate.of(2022, 1, 5);
        Resource input = new Resource(newUser, startDate, endDate, new CostPerHour(100), new PercentageOfAllocation(.5));
        ResourceFactory resFac = mock(ResourceFactory.class);
        ProjectTeam projectTeam = new ProjectTeam(resFac);
        company.getProjectStore().saveNewProject(project);
        project.getProductBacklog().createAndSaveUserStory("As a PO, i want to test this string", 2, "create user story", 5);
        project.getProductBacklog().createAndSaveUserStory("As a PO, i want to test this string", 1, "sort user story", 5);
        project.getProductBacklog().createAndSaveUserStory("As a PO, i want to test this string", 3, "backlog sorted", 5);

        projectTeam.saveResource(input);
        project.setProjectTeam(projectTeam);

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ProductBacklogSortController productBacklogSortController = new ProductBacklogSortController(company, mapper, mapperPB);
            productBacklogSortController.getProjectListByUserEmail("cris@ipp.pt");
            project.getProductBacklog().createAndSaveUserStory("As a PO, i want to test this string", 6, "show sorted", 5);
            productBacklogSortController.getUsSortedByPriority("123testcode");
        });
        // Assert
        assertTrue(exception.getMessage().contains("Check priority, cannot be < 0 or superior to 5"));
    }

    @Test
    @DisplayName("grants a list DTO of US that is sorted by priority. It keeps the done and/or cancelled US on the end\n")
    public void getSortedListWithSuccessCorrectDTOInfo() {
        Company company = new Company();
        ProjectsMapper mapper = new ProjectsMapper();
        ProductBacklogMapper mapperPB = new ProductBacklogMapper();
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        Project project = company.getProjectStore().createProject("prototype", "test56", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser("xyz", "cris@ipp.pt", "des", "Qwerty_1", "Qwerty_1", "photo.png", userProfile);
        LocalDate startDate = LocalDate.of(2021, 12, 31);
        LocalDate endDate = LocalDate.of(2022, 1, 5);
        Resource input = new Resource(newUser, startDate, endDate, new CostPerHour(100), new PercentageOfAllocation(.5));
        ResourceFactory resFac = mock(ResourceFactory.class);
        ProjectTeam projectTeam = new ProjectTeam(resFac);// Arrange

        company.getProjectStore().saveNewProject(project);
        project.getProductBacklog().createAndSaveUserStory("As a PO, i want to test this string", 2, "create user story", 5);
        project.getProductBacklog().createAndSaveUserStory("As a PO, i want to test this string", 1, "sort user story", 5);
        project.getProductBacklog().createAndSaveUserStory("As a PO, i want to test this string", 3, "backlog sorted", 5);
        project.getProductBacklog().createAndSaveUserStory("As a PO, i want to test this string", 5, "show sorted", 5);

        projectTeam.saveResource(input);
        project.setProjectTeam(projectTeam);
        // Act
        ProductBacklogSortController productBacklogSortController = new ProductBacklogSortController(company, mapper, mapperPB);
        productBacklogSortController.getProjectListByUserEmail("cris@ipp.pt");
        List<UserStoryDto> userStoryListDtoList = productBacklogSortController.getUsSortedByPriority("Project_2022_1");
        // Assert
        assertEquals(project.getProductBacklog().getUserStoryList().get(0).getTitle().getUsTitle(), userStoryListDtoList.get(0).getTitle());
        assertEquals(project.getProductBacklog().getUserStoryList().get(0).getUserStoryStatus(), userStoryListDtoList.get(0).getUserStoryStatus());
        assertEquals(project.getProductBacklog().getUserStoryList().get(0).getPriority().getUsPriority(), userStoryListDtoList.get(0).getPriority());
        assertEquals(project.getProductBacklog().getUserStoryList().get(0).getDescription().getText(), userStoryListDtoList.get(0).getDescription().getText());
        assertEquals(project.getProductBacklog().getUserStoryList().get(1).getTitle().getUsTitle(), userStoryListDtoList.get(1).getTitle());
        assertEquals(project.getProductBacklog().getUserStoryList().get(1).getUserStoryStatus(), userStoryListDtoList.get(1).getUserStoryStatus());
        assertEquals(project.getProductBacklog().getUserStoryList().get(1).getPriority().getUsPriority(), userStoryListDtoList.get(1).getPriority());
        assertEquals(project.getProductBacklog().getUserStoryList().get(1).getDescription().getText(), userStoryListDtoList.get(1).getDescription().getText());
        assertEquals(project.getProductBacklog().getUserStoryList().get(2).getTitle().getUsTitle(), userStoryListDtoList.get(2).getTitle());
        assertEquals(project.getProductBacklog().getUserStoryList().get(2).getUserStoryStatus(), userStoryListDtoList.get(2).getUserStoryStatus());
        assertEquals(project.getProductBacklog().getUserStoryList().get(2).getPriority().getUsPriority(), userStoryListDtoList.get(2).getPriority());
        assertEquals(project.getProductBacklog().getUserStoryList().get(2).getDescription().getText(), userStoryListDtoList.get(2).getDescription().getText());
        assertEquals(project.getProductBacklog().getUserStoryList().get(3).getTitle().getUsTitle(), userStoryListDtoList.get(3).getTitle());
        assertEquals(project.getProductBacklog().getUserStoryList().get(3).getUserStoryStatus(), userStoryListDtoList.get(3).getUserStoryStatus());
        assertEquals(project.getProductBacklog().getUserStoryList().get(3).getPriority().getUsPriority(), userStoryListDtoList.get(3).getPriority());
        assertEquals(project.getProductBacklog().getUserStoryList().get(3).getDescription().getText(), userStoryListDtoList.get(3).getDescription().getText());
    }
}