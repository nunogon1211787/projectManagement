package switch2021.project.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import switch2021.project.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChangePriorityUSControllerTest {

    private Company company;
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
    private UserProfile userProfile;
    private UserProfile userProfile2;
    private SystemUser newUser;
    private SystemUser newUser2;
    private UserStoryStatus userStoryStatus;

    @BeforeEach
    void ChangePriorityUSControllerTestWorld(){
    LocalDate startDate2 = LocalDate.of(2022, 12, 31);
    LocalDate startDate3 = LocalDate.of(2022, 12, 31);
    company = new Company();
    typo = company.getTypologyStore().getTypology("Fixed Cost");
    customer = company.getCustomerStore().getCustomerByName("ISEP");
    sector = company.getBusinessSectorStore().getBusinessSectorByDescription("Balloons");
    project = company.getProjectStore().createProject("XPTO2000", "prototype2", "test56", customer,
            typo, sector, startDate2, 7, 5000);
    project2 = company.getProjectStore().createProject("XPTO3000", "prototype5", "test56", customer,
            typo, sector, startDate3, 7, 5000);
    project3 = company.getProjectStore().createProject("XPTO7000", "prototype8", "test56", customer,
            typo, sector, startDate3, 4, 3000);
    userProfile = new UserProfile("Apresentador");
    userProfile2 = new UserProfile("Duo");
    newUser = new SystemUser("batatinha", "batatinha@cartoon.com", "des", "gth", "gth", "", userProfile);
    newUser2 = new SystemUser("companhia", "companhia@cartoon.com", "des", "gth", "gth", "", userProfile2);
    LocalDate startDate = LocalDate.of(2021, 12, 31);
    LocalDate endDate = LocalDate.of(2022, 3, 5);
    input = new Resource(newUser, startDate, endDate, 100, .5);
    input2 = new Resource(newUser2, startDate, endDate, 100, .5);
    userStoryStatus = new UserStoryStatus("coiso" );
    userStory = new UserStory(userStoryStatus, 2, "Fazer tal");
    userStory2 = new UserStory(userStoryStatus, 3, "Fazer tal e coiso");
    userStory3 = new UserStory(userStoryStatus, 4, "Fazer tal e coiso também");
        project.getProductBacklog().addUserStory(userStory);
        project.getProductBacklog().addUserStory(userStory2);
        project.getProductBacklog().addUserStory(userStory3);
        company.getProjectStore().addProject(project);
        company.getProjectStore().addProject(project2);
        company.getProjectStore().addProject(project3);

    }
    /*@Test//David tirei esta validação do método em ProjectStore que estás aqui a testar
        // pois o email já está no sistema não é introduzido pelo utilizador, logo já foi validado quando foi criado o user.
    void getCurrentProjectListByUserEmailIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> {//Arrange
            company.getProjectStore().addProject(project);
            company.getProjectStore().addProject(project2);
            project.addResource(input);
            project2.addResource(input);

            // Act
            List<Project> projectList = company.getProjectStore().getCurrentProjectListByUserEmail("batatinhacartoon.com");
            // Assert

        });
    }*/

    @Test
    void getCurrentProjectListByUserEmailSizeTest() {
        //Arrange
        project.addResource(input);
        project2.addResource(input);

        // Act
        List<Project> projectList = company.getProjectStore().getCurrentProjectListByUserEmail("batatinha@cartoon.com");
        // Assert
        assertEquals(2, projectList.size());

    }

    @Test
    void getCurrentProjectListByUserEmailOnlyActiveTest() {
        //Arrange

        project.addResource(input);
        project2.addResource(input);
        LocalDate endDate = LocalDate.of(2021,1,2);
        company.getProjectStore().getProjectByCode("XPTO2000").setEndDate(endDate);


        // Act
        List<Project> projectList = company.getProjectStore().getCurrentProjectListByUserEmail("batatinha@cartoon.com");
        // Assert
        assertEquals(1, projectList.size());


    }

    @Test
    void getCurrentProjectListByUserEmailCorrectList() {
        //Arrange

        LocalDate endDate = LocalDate.of(2021,1,2);
        company.getProjectStore().getProjectByCode("XPTO2000").setEndDate(endDate);
        project.addResource(input);
        project2.addResource(input);
        project3.addResource(input);

        // Act
        List<Project> projectList2 = new ArrayList<>();
                projectList2.add(project2);
                projectList2.add(project3);
        List<Project> projectList = company.getProjectStore().getCurrentProjectListByUserEmail("batatinha@cartoon.com");
        // Assert
        assertEquals(projectList, projectList2);

    }

    @Test
    void getProjectByCodeTest() {
        project.addResource(input);
        project2.addResource(input);
        // Act
        Project project3 = company.getProjectStore().getProjectByCode("XPTO2000");
        // Assert
        assertEquals(project,project3);
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

        List<UserStory> usList = company.getProjectStore().getProjectByCode("XPTO2000").getProductBacklog().getUserStoryList();

        assertEquals(usList,this.project.getProductBacklog().getUserStoryList());
    }

    @Test
    void getUserStoryListFromProjectSizeTest() {
        project.addResource(input);

        List<UserStory> usList = company.getProjectStore().getProjectByCode("XPTO2000").getProductBacklog().getUserStoryList();

//        assertArrayEquals(usList, this.);
        assertEquals(3,usList.size());
    }

    @Test
    void getUserStoryListFromProjectOnlyActive() {
        project.addResource(input);

        UserStoryStatus usStatus2 = new UserStoryStatus("Completed");
        userStory3.setUserStoryStatus(usStatus2);

        List<UserStory> usList = company.getProjectStore().getProjectByCode("XPTO2000").getProductBacklog().getActiveUserStoryList();

        assertEquals(2,usList.size());
    }

//    @Test
//    void getUSCorrect() {
//        project.addResource(input);
//
//        UserStory us1 = project.getProductBacklog().getUserStoryById(23);
//
//        assertEquals(userStory2,us1);
//    }

    @Test
    void setPriorityCorrect() {
        project.addResource(input);

        project.getProductBacklog().addUserStory(userStory3);

        userStory3.setPriority(3);

        assertEquals(3,userStory3.getPriority());

    }

    @Test
    void setPriorityInvalid() {
        project.addResource(input);

        project.getProductBacklog().addUserStory(userStory3);

        userStory3.setPriority(6);

        assertEquals(4,userStory3.getPriority());

    }
}