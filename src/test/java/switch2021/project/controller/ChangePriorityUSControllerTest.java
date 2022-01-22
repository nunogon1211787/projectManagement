package switch2021.project.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import switch2021.project.model.*;
import switch2021.project.stores.ProjectStore;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ChangePriorityUSControllerTest {

    private Company company;
    private Project project;
    private Project project2;
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
    void ChangePriorityUSControllerTest2(){
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

    }
    @Test
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
    }

    @Test
    void getCurrentProjectListByUserEmailSizeTest() {
        //Arrange
        company.getProjectStore().addProject(project);
        company.getProjectStore().addProject(project2);
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
        company.getProjectStore().addProject(project);
        company.getProjectStore().addProject(project2);
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
    void getCurrentProjectListByUserEmail() {


    }

    @Test
    void getProjectTest() {
        company.getProjectStore().addProject(project);
        company.getProjectStore().addProject(project2);
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
    void getUserStoryListFromProject() {
        company.getProjectStore().addProject(project);
        company.getProjectStore().addProject(project2);
        project.addResource(input);
        project2.addResource(input);

        project.getProductBacklog().addUserStory(userStory);
        project.getProductBacklog().addUserStory(userStory2);
        project.getProductBacklog().addUserStory(userStory3);

        List<UserStory> usList = company.getProjectStore().getProjectByCode("XPTO2000").getProductBacklog().getUserStoryList();

//        assertArrayEquals(usList, this.);
    }

    @Test
    void getUS() {
    }

    @Test
    void setPriority() {
    }
}