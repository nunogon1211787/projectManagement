package switch2021.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.model.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductBacklogSortControllerTest {




    @Test
    @DisplayName("check if the list size is correct")
    public void getProjectListByUserEmailWith2Projects() {
        //Arrange
        Company company = new Company();
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
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
        ProjectTeam projectTeam = new ProjectTeam();
        company.getProjectStore().saveNewProject(project);
        company.getProjectStore().saveNewProject(project2);
        project.addResource(input);
        project2.addResource(input);

        // Act
        ProductBacklogSortController productBacklogSortController = new ProductBacklogSortController(company);
        List<Project> projectList = productBacklogSortController.getProjectListByUserEmail("cris@ipp.pt");
        // Assert
        assertEquals(2, projectList.size());

    }

    @Test
    @DisplayName("get exception message  \"Email cannot be blank“;\n")
    public void getProjectListByUserEmailIsBlank() {
        //Arrange
        Company company = new Company();
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
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
        ProjectTeam projectTeam = new ProjectTeam();

        company.getProjectStore().saveNewProject(project);
        company.getProjectStore().saveNewProject(project2);
        project.addResource(input);
        project2.addResource(input);

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ProductBacklogSortController productBacklogSortController = new ProductBacklogSortController(company);
            List<Project> projectList = productBacklogSortController.getProjectListByUserEmail("");
        });
        // Assert
        assertEquals("Email cannot be blank", exception.getMessage());

    }

    @Test
    @DisplayName("get exception message  \"Email don't exist in system")
    public void getAllProjectListByUserEmailDontExist() {
        //Arrange
        Company company = new Company();
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
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
        ProjectTeam projectTeam = new ProjectTeam();

        company.getProjectStore().saveNewProject(project);
        company.getProjectStore().saveNewProject(project2);
        project.addResource(input);
        project2.addResource(input);

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        ProductBacklogSortController productBacklogSortController = new ProductBacklogSortController(company);
        List<Project> projectList = productBacklogSortController.getProjectListByUserEmail("dani@ipp.pt");
        });
        // Assert
            assertEquals("Email don't exist in system", exception.getMessage());

    }
    @Test
    @DisplayName("check the provided project is the correct")
    public void getProjectByCodeWithSuccess(){
        // Arrange
        Company company = new Company();
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
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
        ProjectTeam projectTeam = new ProjectTeam();

        company.getProjectStore().saveNewProject(project);
        ProductBacklogSortController productBacklogSortController = new ProductBacklogSortController(company);
        // Act
        Project projectT = productBacklogSortController.getProject("Project_2022_1");
        // Assert
        assertEquals(projectT,project);
    }

    @Test
    @DisplayName("check the provided return null")
    public void getProjectByCodeFail(){
        // Arrange
        Company company = new Company();
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
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
        ProjectTeam projectTeam = new ProjectTeam();
        company.getProjectStore().saveNewProject(project);
        ProductBacklogSortController productBacklogSortController = new ProductBacklogSortController(company);
        // Act
        Project projectT = productBacklogSortController.getProject("testcode");
        // Assert
        assertNull(projectT);
    }

    @Test
    @DisplayName("grants a list of US that is sorted by priority. It keeps the done and/or cancelled US on the end\n")
    public void getSortedListWithSuccess(){
        Company company = new Company();
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
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
        ProjectTeam projectTeam = new ProjectTeam();// Arrange

        company.getProjectStore().saveNewProject(project);
        UserStory userStory =project.getProductBacklog().createUserStory("US001",2,"create user story",5);
        project.getProductBacklog().saveUserStory(userStory);
        UserStory userStory1 =project.getProductBacklog().createUserStory("US001",1,"sort user story",5);
        project.getProductBacklog().saveUserStory(userStory1);
        UserStory userStory2 =project.getProductBacklog().createUserStory("US001",3,"backlog sorted",5);
        project.getProductBacklog().saveUserStory(userStory2);
        UserStory userStory3 =project.getProductBacklog().createUserStory("US001",5,"show sorted",5);
        project.getProductBacklog().saveUserStory(userStory3);

        projectTeam.addResourceToTeam(input);
        project.setProjectTeam(projectTeam);

        // Act
        ProductBacklogSortController productBacklogSortController = new ProductBacklogSortController(company);
        productBacklogSortController.getProjectListByUserEmail("cris@ipp.pt");
        productBacklogSortController.getProject("Project_2022_1");
        List<UserStory> usSortedByPriority = productBacklogSortController.getUsSortedByPriority();

        // Assert
        assertEquals(4, usSortedByPriority.size());

        assertEquals(1, usSortedByPriority.get(0).getPriority());
        assertEquals(2, usSortedByPriority.get(1).getPriority());
        assertEquals(3, usSortedByPriority.get(2).getPriority());
        assertEquals(5, usSortedByPriority.get(3).getPriority());
    }

    @Test
    @DisplayName("get exception message \"Check priority, cannot be < 0 or superior to 5.“")

    public void getSortedListFailWrongPriority(){
        // Arrange
        Company company = new Company();
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
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
        ProjectTeam projectTeam = new ProjectTeam();
        company.getProjectStore().saveNewProject(project);
        UserStory userStory =project.getProductBacklog().createUserStory("US001",2,"create user story",5);
        project.getProductBacklog().saveUserStory(userStory);
        UserStory userStory1 =project.getProductBacklog().createUserStory("US001",1,"sort user story",5);
        project.getProductBacklog().saveUserStory(userStory1);
        UserStory userStory2 =project.getProductBacklog().createUserStory("US001",3,"backlog sorted",5);
        project.getProductBacklog().saveUserStory(userStory2);


        projectTeam.addResourceToTeam(input);
        project.setProjectTeam(projectTeam);

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        ProductBacklogSortController productBacklogSortController = new ProductBacklogSortController(company);
        productBacklogSortController.getProjectListByUserEmail("cris@ipp.pt");
        productBacklogSortController.getProject("123testcode");
            UserStory userStory3 =project.getProductBacklog().createUserStory("US001",6,"show sorted",5);
            project.getProductBacklog().saveUserStory(userStory3);
        List<UserStory> usSortedByPriority = productBacklogSortController.getUsSortedByPriority();
        });
        // Assert
        assertTrue(exception.getMessage().contains("Check priority, cannot be < 0 or superior to 5."));
    }


}
