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
        company = App.getInstance().getCompany();
        project = company.createProject("TEST", "Projecto Test", "decricao", new Customer("marreta@email.pt"), new Typology("description"),
                new BusinessSector("description"), LocalDate.now(), 10, 100000);
        project.createUserStory(UserStoryStatus.TODO, 12, "Default Story", 6);
        project.setProductOwner(new SystemUser("Test User", "123@isep.ipp.pt",
                "Product Owner", "AAA", company.getProfile("Product Owne")));
        company.add(project);
    }

    @Test
    public void createUserStorySuccessFull() {
        CreateUserStoryController createUserStoryController = new CreateUserStoryController();
        boolean isUserStoryCreated = createUserStoryController.createUserStory(project.getCode(), UserStoryStatus.TODO.toString(), 12, "desc", 6);
        assertTrue(isUserStoryCreated);
    }

    @Test
    public void createUserStoryInvalidDuplicatedUserStory() {
        CreateUserStoryController createUserStoryController = new CreateUserStoryController();
        boolean isUserStoryCreated = createUserStoryController.createUserStory(project.getCode(), UserStoryStatus.IN_TEST.toString(), 1, "Default Story", 1);
        assertFalse(isUserStoryCreated);
    }

    @Test
    public void createUserStoryTimeEstimateInvalid() {
        //Arrange
        CreateUserStoryController createUserStoryController = new CreateUserStoryController();
        int priority = 1;
        String description = "teste";
        int timeEstimate = -1;
        // Act
        boolean isAdded = createUserStoryController.createUserStory(project.getCode(), UserStoryStatus.IN_TEST.toString(), priority, description, timeEstimate);
        //Assert
        assertFalse(isAdded);
    }

    @Test
    public void createUserStoryPriorityInvalid() {
        //Arrange
        CreateUserStoryController createUserStoryController = new CreateUserStoryController();
        int priority = -1;
        String description = "teste";
        int timeEstimate = 1;
        // Act
        boolean isAdded = createUserStoryController.createUserStory(project.getCode(), UserStoryStatus.IN_TEST.toString(), priority, description, timeEstimate);
        //Assert
        assertFalse(isAdded);
    }

    @Test
    public void createUserStoryUserStoryStatusInvalid() {
        //Arrange
        CreateUserStoryController createUserStoryController = new CreateUserStoryController();
        int priority = 1;
        String description = "teste";
        int timeEstimate = 1;
        // Act
        boolean isAdded = createUserStoryController.createUserStory(project.getCode(), "Erro", priority, description, timeEstimate);
        //Assert
        assertFalse(isAdded);
    }


    @Test
    public void createUserStoryDescriptionInvalid() {
        //Arrange
        CreateUserStoryController createUserStoryController = new CreateUserStoryController();
        int priority = 1;
        int timeEstimate = 1;
        // Act
        boolean isAdded = createUserStoryController.createUserStory(project.getCode(), UserStoryStatus.IN_TEST.toString(), priority, null, timeEstimate);
        boolean isAdded2 = createUserStoryController.createUserStory(project.getCode(), UserStoryStatus.IN_TEST.toString(), priority, "", timeEstimate);
        boolean isAdded3 = createUserStoryController.createUserStory(project.getCode(), UserStoryStatus.IN_TEST.toString(), priority, "   ", timeEstimate);
        //Assert
        assertFalse(isAdded);
        assertFalse(isAdded2);
        assertFalse(isAdded3);
    }

    //test obter lista de projectos com alguma coisa

 /*   @Test
    public void inicializeprofileslistwithdefaultprofiles() {
        //Input
        Company comTest = new Company();

        //Expected
        List<Profile> expected = new ArrayList<>();
        expected.add(new Profile("Visitor", "System Profile"));
        expected.add(new Profile("Administrator", "System Profile"));
        //Result
        assertEquals(expected, comTest.getArrayProfile());
    }


   //test obter lista de projectos vazia email nao existe
 */


    @Test
    public void getProjectListWithPORightEmptyList() {
        //Arrange
        CreateUserStoryController createUserStoryController = new CreateUserStoryController();
        // Act
        List<Project> projectList = createUserStoryController.getProjectListWithPORight("email");
        //Assert
        assertEquals(0, projectList.size());

    }

    @Test
    public void getProjectListWithPORightWithEmptyEmailAndGetEmptyList() {
        //Arrange
        CreateUserStoryController createUserStoryController = new CreateUserStoryController();
        // Act
        List<Project> projectList = createUserStoryController.getProjectListWithPORight("");
        List<Project> projectList2 = createUserStoryController.getProjectListWithPORight(null);
        //Assert
        assertEquals(0, projectList.size());
        assertEquals(0, projectList2.size());

    }

    @Test
    public void getProjectListWithPORighListWithResults() {
        //Arrange
        CreateUserStoryController createUserStoryController = new CreateUserStoryController();
        // Act
        List<Project> projectList = createUserStoryController.getProjectListWithPORight("123@isep.ipp.pt");
        //Assert
        assertNotEquals(0, projectList.size());

    }

}

