package switch2021.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.model.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductBacklogControllerTest {

    Company company = new Company();
    Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
    Customer customer = company.getCustomerStore().getCustomerByName("Teste");
    BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
    Project project = company.getProjectStore().createProject("123testcode", "prototype", "test56", customer,
            typo, sector, LocalDate.now(), 7, 5000);
    Project project2 = company.getProjectStore().createProject("123testcode", "prototype", "test56", customer,
            typo, sector, LocalDate.now(), 7, 5000);
    UserProfile userProfile = new UserProfile("zzz");
    SystemUser newUser = new SystemUser("xyz", "cris@ipp.pt", "des", "gth", "gth", "", userProfile);
    LocalDate startDate = LocalDate.of(2021, 12, 31);
    LocalDate endDate = LocalDate.of(2022, 1, 5);
    Resource input = new Resource(newUser, startDate, endDate, 100, .5);
    ProjectTeam projectTeam = new ProjectTeam();


    @Test
    @DisplayName("check if the list size is correct")
    public void getProjectListByUserEmailWith2Projects() {
        //Arrange
        company.getProjectStore().addProject(project);
        company.getProjectStore().addProject(project2);
        project.addResource(input);
        project2.addResource(input);

        // Act
        ProductBacklogController productBacklogController = new ProductBacklogController(company);
        List<Project> projectList = productBacklogController.getProjectListByUserEmail("cris@ipp.pt");
        // Assert
        assertEquals(2, projectList.size());

    }

    @Test
    @DisplayName("get exception message  \"Email cannot be blank“;\n")
    public void getProjectListByUserEmailIsBlank() {
        //Arrange
        company.getProjectStore().addProject(project);
        company.getProjectStore().addProject(project2);
        project.addResource(input);
        project2.addResource(input);

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ProductBacklogController productBacklogController = new ProductBacklogController(company);
            List<Project> projectList = productBacklogController.getProjectListByUserEmail("");
        });
        // Assert
        assertEquals("Email cannot be blank", exception.getMessage());

    }

    @Test
    @DisplayName("get exception message  \"Email don't exist in system")
    public void getAllProjectListByUserEmailDontExist() {
        //Arrange
        company.getProjectStore().addProject(project);
        company.getProjectStore().addProject(project2);
        project.addResource(input);
        project2.addResource(input);

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        ProductBacklogController productBacklogController = new ProductBacklogController(company);
        List<Project> projectList = productBacklogController.getProjectListByUserEmail("dani@ipp.pt");
        });
        // Assert
            assertEquals("Email don't exist in system", exception.getMessage());

    }
    @Test
    @DisplayName("check the provided project is the correct")
    public void getProjectByCodeWithSuccess(){
        // Arrange
        company.getProjectStore().addProject(this.project);
        ProductBacklogController productBacklogController = new ProductBacklogController(company);
        // Act
        Project project = productBacklogController.getProject("123testcode");
        // Assert
        assertEquals(this.project,project);
    }

    @Test
    @DisplayName("check the provided return null")
    public void getProjectByCodeFail(){
        // Arrange
        company.getProjectStore().addProject(this.project);
        ProductBacklogController productBacklogController = new ProductBacklogController(company);
        // Act
        Project project = productBacklogController.getProject("testcode");
        // Assert
        assertNull(project);
    }

    @Test
    @DisplayName("grants a list of US that is sorted by priority. It keeps the done and/or cancelled US on the end\n")
    public void getSortedListWithSuccess(){
        // Arrange
        company.getProjectStore().addProject(project);
        UserStory userStory =project.getProductBacklog().createUserStory(new UserStoryStatus("In progress"),2,"create user story");
        project.getProductBacklog().saveUserStory(userStory);
        UserStory userStory1 =project.getProductBacklog().createUserStory(new UserStoryStatus("In progress"),1,"sort user story");
        project.getProductBacklog().saveUserStory(userStory1);
        UserStory userStory2 =project.getProductBacklog().createUserStory(new UserStoryStatus("In progress"),3,"backlog sorted");
        project.getProductBacklog().saveUserStory(userStory2);
        UserStory userStory3 =project.getProductBacklog().createUserStory(new UserStoryStatus("In progress"),5,"show sorted");
        project.getProductBacklog().saveUserStory(userStory3);

        projectTeam.addResourceToTeam(input);
        project.setProjectTeam(projectTeam);

        // Act
        ProductBacklogController productBacklogController = new ProductBacklogController(company);
        productBacklogController.getProjectListByUserEmail("cris@ipp.pt");
        productBacklogController.getProject("123testcode");
        List<UserStory> usSortedByPriority = productBacklogController.getUsSortedByPriority();

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
        company.getProjectStore().addProject(project);
        UserStory userStory =project.getProductBacklog().createUserStory(new UserStoryStatus("In progress"),2,"create user story");
        project.getProductBacklog().saveUserStory(userStory);
        UserStory userStory1 =project.getProductBacklog().createUserStory(new UserStoryStatus("In progress"),1,"sort user story");
        project.getProductBacklog().saveUserStory(userStory1);
        UserStory userStory2 =project.getProductBacklog().createUserStory(new UserStoryStatus("In progress"),3,"backlog sorted");
        project.getProductBacklog().saveUserStory(userStory2);


        projectTeam.addResourceToTeam(input);
        project.setProjectTeam(projectTeam);

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        ProductBacklogController productBacklogController = new ProductBacklogController(company);
        productBacklogController.getProjectListByUserEmail("cris@ipp.pt");
        productBacklogController.getProject("123testcode");
            UserStory userStory3 =project.getProductBacklog().createUserStory(new UserStoryStatus("In progress"),6,"show sorted");
            project.getProductBacklog().saveUserStory(userStory3);
        List<UserStory> usSortedByPriority = productBacklogController.getUsSortedByPriority();
        });
        // Assert
        assertTrue(exception.getMessage().contains("Check priority, cannot be < 0 or superior to 5."));
    }


}
