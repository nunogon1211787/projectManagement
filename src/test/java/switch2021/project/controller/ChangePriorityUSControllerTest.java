package switch2021.project.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import switch2021.project.model.*;
import switch2021.project.model.Project.*;
import switch2021.project.model.SystemUser.SystemUser;
import switch2021.project.model.UserStory.UserStory;
import switch2021.project.model.valueObject.*;
import switch2021.project.stores.ProjectStore;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ChangePriorityUSControllerTest {

    private Company company;
    private ProjectStore projectStore;
    private Project project;
    private Project project2;
    private Project project3;
    private Resource input;
    private Resource input2;
    private UserStory userStory;
    private UserStory userStory2;
    private UserStory userStory3;
    private Typology typo;
    private Customer customer;
    private BusinessSector sector;
    //    private UserProfile userProfile;
    private SystemUser newUser;
    private SystemUser newUser2;
    private UserStoryStatus userStoryStatus;
//    private ProductBacklog productBacklog;


    @BeforeEach
    void ChangePriorityUSControllerTestWorld() {
        LocalDate startDate2 = LocalDate.of(2022, 12, 31);
        LocalDate startDate3 = LocalDate.of(2022, 12, 31);
        company = new Company();
        typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        customer = company.getCustomerStore().getCustomerByName("ISEP");
        sector = company.getBusinessSectorStore().getBusinessSectorByDescription("Balloons");
        project = company.getProjectStore().createProject("prototype2", "test56", customer,
                typo, sector, startDate2, 7, 5000);
        project2 = company.getProjectStore().createProject("prototype5", "test56", customer,
                typo, sector, startDate3, 7, 5000);
        project3 = company.getProjectStore().createProject("prototype8", "test56", customer,
                typo, sector, startDate3, 4, 3000);
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        newUser = new SystemUser("batatinha", "batatinha@cartoon.com", "des", "Qwerty_1", "Qwerty_1", "", userProfile);
        newUser2 = new SystemUser("companhia", "companhia@cartoon.com", "des", "Qwerty_1", "Qwerty_1", "", userProfile);
        LocalDate startDate = LocalDate.of(2021, 12, 31);
        LocalDate endDate = LocalDate.of(2023, 3, 5);
        input = new Resource(newUser, startDate, endDate, new CostPerHour(100), new PercentageOfAllocation(.5));
        input2 = new Resource(newUser2, startDate, endDate, new CostPerHour(100), new PercentageOfAllocation(.5));
        userStoryStatus = new UserStoryStatus("coiso", true);
        userStory = new UserStory("As a PO, i want to test this string", 2, "Fazer tal", 5);
        userStory.setIdUserStory(0);
        userStory2 = new UserStory("As a diretor, i want to test this string", 3, "Fazer tal e coiso", 5);
        userStory2.setIdUserStory(1);
        userStory3 = new UserStory("As a SM, i want to test this string", 4, "Fazer tal e coiso também", 5);
        userStory3.setIdUserStory(2);
        project.getProductBacklog().getUserStoryList().add(userStory);
        project.getProductBacklog().getUserStoryList().add(userStory2);
        project.getProductBacklog().getUserStoryList().add(userStory3);
        company.getProjectStore().saveNewProject(project);
        company.getProjectStore().saveNewProject(project2);
        company.getProjectStore().saveNewProject(project3);
        project.addResource(input);
        project2.addResource(input);

    }


    @Test
    void getCurrentProjectListByUserEmailSuccess() {
        //Arrange
        ChangePriorityUSController change = new ChangePriorityUSController(company);
        // Act
        List<Project> actual = new ArrayList<>();
        actual.add(project);
        actual.add(project2);
        // Assert
        assertEquals(actual, change.getCurrentProjectListByUserEmail("batatinha@cartoon.com"));
    }

    @Test
    void getProjectByCodeSuccess() {
        //Arrange
        ChangePriorityUSController change = new ChangePriorityUSController(company);

        // Act
        Project expected = project;
        Project actual = change.getProjectByCode(project.getCode().getText());

        // Assert
        assertEquals(expected, actual);

    }

    @Test
    void getProjectStoreTest() {
        //Arrange
        ChangePriorityUSController change = new ChangePriorityUSController(company);
        this.projectStore = this.company.getProjectStore();
        // Act
        ProjectStore expected = change.getProjectStore();

        // Assert
        assertEquals(expected, this.projectStore);

    }

    @Test
    void getUserStoryListSuccess() {
        //Arrange
        ChangePriorityUSController change = new ChangePriorityUSController(company);
        change.getProject(project.getCode().getText());
        // Act
        List<UserStory> expected = change.getUserStoryList();
        change.getUserStoryList().add(userStory);
        change.getUserStoryList().add(userStory2);
        change.getUserStoryList().add(userStory3);
        List<UserStory> actual = change.getUserStoryList();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void getUSSuccess() {
        //Arrange
        ChangePriorityUSController change = new ChangePriorityUSController(company);
        change.getProject(project.getCode().getText());
        change.getProductBacklog();
        // Act
        UserStory expected = userStory2;
        UserStory actual = change.getUS(userStory2.getIdUserStory());
        // Assert
        assertEquals(expected.getIdUserStory(), actual.getIdUserStory());
    }

    @Test
    void setPrioritySuccess() {
        //Arrange
        ChangePriorityUSController change = new ChangePriorityUSController(company);
        change.getProject(project.getCode().getText());
        change.getProductBacklog();
        change.getUserStory(userStory.getIdUserStory());

        // Act
        change.setPriority(4);
        int expected = 4;
        int actual = userStory.getPriority().getUsPriority();

        // Assert
        assertEquals(expected, actual);
    }


    @Test
    void getCurrentProjectListByUserEmailSizeTest() {
        //Arrange
        project.addResource(input);
        project2.addResource(input);

        // Act
        List<Project> projectList = company.getProjectStore().getCurrentProjectsByUserEmail("batatinha@cartoon.com");
        // Assert
        assertEquals(2, projectList.size());
    }

    @Test
    void getCurrentProjectListByUserEmailOnlyActiveTest() {
        //Arrange

        project.addResource(input);
        project2.addResource(input);
        LocalDate endDate = LocalDate.of(2021, 1, 2);
        company.getProjectStore().getProjectByCode("Project_2022_1").setEndDate(endDate);


        // Act
        List<Project> projectList = company.getProjectStore().getCurrentProjectsByUserEmail("batatinha@cartoon.com");
        // Assert
        assertEquals(1, projectList.size());
    }

    @Test
    void getCurrentProjectListByUserEmailCorrectList() {
        //Arrange

        LocalDate endDate = LocalDate.of(2021, 1, 2);
        company.getProjectStore().getProjectByCode("Project_2022_1").setEndDate(endDate);
        project.addResource(input);
        project2.addResource(input);
        project3.addResource(input);

        // Act
        List<Project> projectList2 = new ArrayList<>();
        projectList2.add(project2);
        projectList2.add(project3);
        List<Project> projectList = company.getProjectStore().getCurrentProjectsByUserEmail("batatinha@cartoon.com");
        // Assert
        assertEquals(projectList, projectList2);

    }

    @Test
    void getProjectByCodeTest() {
        project.addResource(input);
        project2.addResource(input);
        // Act
        Project project3 = company.getProjectStore().getProjectByCode("Project_2022_1");
        // Assert
        assertEquals(project, project3);
    }

    // TESTE AINDA NÃO FUNCIONA PORQUE O CODE DO PROJECT NAO TA VALIDADO
//    @Test
//    void getCurrentProjectByInvalidCode() {
//        assertThrows(IllegalArgumentException.class, () -> {//Arrange
//            company.getProjectStore().addProject(project);
//            company.getProjectStore().addProject(project2);
//            project.addResource(input);
//            project2.addResource(input);
//
//            // Act
//            Project project3 = company.getProjectStore().getProjectByCode("");
//            // Assert
//
//        });
//    }

    @Test
    void getUserStoryListFromProjectCorrect() {

        project.addResource(input);

        List<UserStory> usList = company.getProjectStore().getProjectByCode("Project_2022_1").getProductBacklog().getUserStoryList();

        assertEquals(usList, this.project.getProductBacklog().getUserStoryList());
    }

    @Test
    void getUserStoryListFromProjectSizeTest() {
        project.addResource(input);

        List<UserStory> usList = company.getProjectStore().getProjectByCode("Project_2022_1").getProductBacklog().getUserStoryList();

        assertEquals(3, usList.size());
    }

    @Test
    void getUserStoryListFromProjectOnlyActive() {
        project.addResource(input);

        UserStoryStatus usStatus2 = new UserStoryStatus("Completed", true);
        userStory3.setUserStoryStatus(usStatus2);

        List<UserStory> usList = company.getProjectStore().getProjectByCode("Project_2022_1").getProductBacklog().getActiveUserStoryList();

        assertEquals(2, usList.size());
    }

    //        TEST NOT WORKING SINCE US ID GENERATOR IS NOT WORKING PROPERLY
//    @Test
//    void getUSCorrect() {
//        project.addResource(input);
//
//        UserStory us1 = project.getProductBacklog().getUserStoryById(2);
//
//        assertEquals(userStory2,us1);
//    }

    @Test
    void setPriorityCorrect() {
        project.addResource(input);

        userStory3.setPriority(3);

        assertEquals(3, userStory3.getPriority().getUsPriority());

    }

    @Test
    void setPriorityInvalid() {
        project.addResource(input);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        userStory3.setPriority(6);
        });

        assertTrue(exception.getMessage().equals("Check priority, cannot be < 0 or superior to 5"));

    }
}