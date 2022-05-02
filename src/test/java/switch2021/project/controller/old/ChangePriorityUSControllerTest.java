//package switch2021.project.controller.old;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import switch2021.project.controller.old.ChangePriorityUSController;
//import switch2021.project.model.*;
//import switch2021.project.model.Project.*;
//import switch2021.project.model.Resource.old.Resource;
//import switch2021.project.model.SystemUser.SystemUser;
//import switch2021.project.model.Typology.Typology;
//import switch2021.project.model.UserProfile.UserProfile;
//import switch2021.project.model.UserStory.UserStory;
//import switch2021.project.model.valueObject.*;
//import switch2021.project.repositories.ProjectStore;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class ChangePriorityUSControllerTest {
//
//    private Company company;
//    private ProjectStore projectStore;
//    private Project project;
//    private Project project2;
//    private Project project3;
//    private Resource input;
//    private Resource input2;
//    private UserStory userStory;
//    private UserStory userStory2;
//    private UserStory userStory3;
//    private Typology typo;
//    private Customer customer;
//    private BusinessSector sector;
//    //    private UserProfile userProfile;
//    private SystemUser newUser;
//    private SystemUser newUser2;
//    private UserStoryStatus userStoryStatus;
////    private ProductBacklog productBacklog;
//
//
//    @BeforeEach
//    void ChangePriorityUSControllerTestWorld() {
//        LocalDate startDate2 = LocalDate.of(2022, 12, 31);
//        LocalDate startDate3 = LocalDate.of(2022, 12, 31);
//        company = new Company();
//        typo = company.getTypologyRepository().findTypologyByDescription("Fixed Cost");
//        customer = company.getCustomerStore().getCustomerByName("ISEP");
//        sector = company.getBusinessSectorStore().getBusinessSectorByDescription("Balloons");
//        project = company.getProjectStore().createAndSaveProject("prototype2", "test56", customer,
//                typo, sector, startDate2, 7, 5000);
//        project2 = company.getProjectStore().createAndSaveProject("prototype5", "test56", customer,
//                typo, sector, startDate3, 7, 5000);
//        project3 = company.getProjectStore().createAndSaveProject("prototype8", "test56", customer,
//                typo, sector, startDate3, 4, 3000);
//        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
//        newUser = new SystemUser("batatinha", "batatinha@cartoon.com", "des", "Qwerty_1", "Qwerty_1", "photo.png", userProfile.getUserProfileId());
//        newUser2 = new SystemUser("companhia", "companhia@cartoon.com", "des", "Qwerty_1", "Qwerty_1", "photo1.png", userProfile.getUserProfileId());
//        LocalDate startDate = LocalDate.of(2021, 12, 31);
//        LocalDate endDate = LocalDate.of(2023, 3, 5);
//        input = new Resource(newUser, startDate, endDate, new CostPerHour(100), new PercentageOfAllocation(.5));
//        input2 = new Resource(newUser2, startDate, endDate, new CostPerHour(100), new PercentageOfAllocation(.5));
//        userStory = new UserStory("Project_" + LocalDate.now().getYear() + "_" + 1,"Project_2022_1_As a PO, i want to test this string","As a PO, i want to test this string", 2, "Fazer tal", 5);
//        userStory2 = new UserStory("Project_" + LocalDate.now().getYear() + "_" + 1,"Project_2022_1_As a PO, i want to test this string","As a diretor, i want to test this string", 3, "Fazer tal e coiso", 5);
//        userStory3 = new UserStory("Project_" + LocalDate.now().getYear() + "_" + 1,"Project_2022_1_As a PO, i want to test this string","As a SM, i want to test this string", 4, "Fazer tal e coiso também", 5);
//        project.getUserStoryStore().getUserStoryList().add(userStory);
//        project.getUserStoryStore().getUserStoryList().add(userStory2);
//        project.getUserStoryStore().getUserStoryList().add(userStory3);
//        project.addResource(input);
//        project2.addResource(input);
//
//    }
//
//
//    @Test
//    void getCurrentProjectListByUserEmailSuccess() {
//        //Arrange
//        ChangePriorityUSController change = new ChangePriorityUSController(company);
//        // Act
//        List<Project> actual = new ArrayList<>();
//        actual.add(project);
//        actual.add(project2);
//        // Assert
//        assertEquals(actual, change.getCurrentProjectListByUserEmail("batatinha@cartoon.com"));
//    }
//
//    @Test
//    void getProjectByCodeSuccess() {
//        //Arrange
//        ChangePriorityUSController change = new ChangePriorityUSController(company);
//
//        // Act
//        Project expected = project;
//        Project actual = change.getProjectByCode(project.getProjectCode().getCode());
//
//        // Assert
//        assertEquals(expected, actual);
//
//    }
//
//    @Test
//    void getProjectStoreTest() {
//        //Arrange
//        ChangePriorityUSController change = new ChangePriorityUSController(company);
//        this.projectStore = this.company.getProjectStore();
//        // Act
//        ProjectStore expected = change.getProjectStore();
//
//        // Assert
//        assertEquals(expected, this.projectStore);
//
//    }
//
//    @Test
//    void getUserStoryListSuccess() {
//        //Arrange
//        ChangePriorityUSController change = new ChangePriorityUSController(company);
//        change.getProject(project.getProjectCode().getCode());
//        // Act
//        List<UserStory> expected = change.getUserStoryList();
//        change.getUserStoryList().add(userStory);
//        change.getUserStoryList().add(userStory2);
//        change.getUserStoryList().add(userStory3);
//        List<UserStory> actual = change.getUserStoryList();
//
//        // Assert
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void getUSSuccess() {
//        //Arrange
//        ChangePriorityUSController change = new ChangePriorityUSController(company);
//        change.getProject(project.getProjectCode().getCode());
//        change.getUserStoryStore();
//        // Act
//        UserStory expected = userStory2;
//        UserStory actual = change.getUS(userStory2.getUserStoryID());
//        // Assert
//        assertEquals(expected.getUserStoryID(), actual.getUserStoryID());
//    }
//
//    @Test
//    void setPrioritySuccess() {
//        //Arrange
//        ChangePriorityUSController change = new ChangePriorityUSController(company);
//        change.getProject(project.getProjectCode().getCode());
//        change.getUserStoryStore();
//        change.getUserStory(userStory.getUserStoryID());
//
//        // Act
//        change.setPriority(4);
//        int expected = 4;
//        int actual = userStory.getPriority().getPriorityUs();
//
//        // Assert
//        assertEquals(expected, actual);
//    }
//
//
//    @Test
//    void getCurrentProjectListByUserEmailSizeTest() {
//        //Arrange
//        project.addResource(input);
//        project2.addResource(input);
//
//        // Act
//        List<Project> projectList = company.getProjectStore().getCurrentProjectsByUserEmail("batatinha@cartoon.com");
//        // Assert
//        assertEquals(2, projectList.size());
//    }
//
//    @Test
//    void getCurrentProjectListByUserEmailOnlyActiveTest() {
//        //Arrange
//
//        project.addResource(input);
//        project2.addResource(input);
//        LocalDate endDate = LocalDate.of(2021, 1, 2);
//        company.getProjectStore().findById("Project_2022_1").setEndDate(endDate);
//
//
//        // Act
//        List<Project> projectList = company.getProjectStore().getCurrentProjectsByUserEmail("batatinha@cartoon.com");
//        // Assert
//        assertEquals(1, projectList.size());
//    }
//
//    @Test
//    void getCurrentProjectListByUserEmailCorrectList() {
//        //Arrange
//
//        LocalDate endDate = LocalDate.of(2021, 1, 2);
//        company.getProjectStore().findById("Project_2022_1").setEndDate(endDate);
//        project.addResource(input);
//        project2.addResource(input);
//        project3.addResource(input);
//
//        // Act
//        List<Project> projectList2 = new ArrayList<>();
//        projectList2.add(project2);
//        projectList2.add(project3);
//        List<Project> projectList = company.getProjectStore().getCurrentProjectsByUserEmail("batatinha@cartoon.com");
//        // Assert
//        assertEquals(projectList, projectList2);
//
//    }
//
//    @Test
//    void getProjectByCodeTest() {
//        project.addResource(input);
//        project2.addResource(input);
//        // Act
//        Project project3 = company.getProjectStore().findById("Project_2022_1");
//        // Assert
//        assertEquals(project, project3);
//    }
//
//    // TESTE AINDA NÃO FUNCIONA PORQUE O CODE DO PROJECT NAO TA VALIDADO
////    @Test
////    void getCurrentProjectByInvalidCode() {
////        assertThrows(IllegalArgumentException.class, () -> {//Arrange
////            company.getProjectStore().addProject(project);
////            company.getProjectStore().addProject(project2);
////            project.addResource(input);
////            project2.addResource(input);
////
////            // Act
////            Project project3 = company.getProjectStore().getProjectByCode("");
////            // Assert
////
////        });
////    }
//
//    @Test
//    void getUserStoryListFromProjectCorrect() {
//
//        project.addResource(input);
//
//        List<UserStory> usList = company.getProjectStore().findById("Project_2022_1").getUserStoryStore().getUserStoryList();
//
//        assertEquals(usList, this.project.getUserStoryStore().getUserStoryList());
//    }
//
//    @Test
//    void getUserStoryListFromProjectSizeTest() {
//        project.addResource(input);
//
//        List<UserStory> usList = company.getProjectStore().findById("Project_2022_1").getUserStoryStore().getUserStoryList();
//
//        assertEquals(3, usList.size());
//    }
//
//    @Test
//    void getUserStoryListFromProjectOnlyActive() {
//        project.addResource(input);
//
//        List<UserStory> usList = company.getProjectStore().findById("Project_2022_1").getUserStoryStore().findActiveUserStoryList();
//
//        assertEquals(3, usList.size());
//    }
//
//    //        TEST NOT WORKING SINCE US ID GENERATOR IS NOT WORKING PROPERLY
////    @Test
////    void getUSCorrect() {
////        project.addResource(input);
////
////        UserStory us1 = project.getUserStoryStore().getUserStoryById(2);
////
////        assertEquals(userStory2,us1);
////    }
//
//    @Test
//    void setPriorityCorrect() {
//        project.addResource(input);
//
//        userStory3.setPriority(3);
//
//        assertEquals(3, userStory3.getPriority().getPriorityUs());
//
//    }
//
//    @Test
//    void setPriorityInvalid() {
//        project.addResource(input);
//
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//        userStory3.setPriority(6);
//        });
//
//        assertEquals("Check priority, cannot be < 0 or superior to 5", exception.getMessage());
//
//    }
//
//    @Test
//    void setPriorityCorrect() {
//        project.addResource(input);
//
//        userStory3.setPriority(3);
//
//        assertEquals(3, userStory3.getPriority().getPriorityUs());
//
//    }
//
//    @Test
//    void setPriorityInvalid() {
//        project.addResource(input);
//
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//        userStory3.setPriority(6);
//        });
//
//        assertEquals("Check priority, cannot be < 0 or superior to 5", exception.getMessage());
//
//    }*/
//}
//}