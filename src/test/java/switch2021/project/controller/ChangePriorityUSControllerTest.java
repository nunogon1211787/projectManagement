package switch2021.project.controller;


import org.junit.jupiter.api.Test;
import switch2021.project.model.*;
import switch2021.project.stores.ProjectStore;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChangePriorityUSControllerTest {

    private ProjectStore projectStore;
    private ProductBacklog productBacklog;
    private UserStory userStory;

    LocalDate startDate2 = LocalDate.of(2022, 12, 31);
    LocalDate startDate3 = LocalDate.of(2022, 12, 31);
    Company company = new Company();
    Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
    Customer customer = company.getCustomerStore().getCustomerByName("ISEP");
    BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("Balloons");
    Project project = company.getProjectStore().createProject("XPTO2000", "prototype2", "test56", customer,
            typo, sector, startDate2, 7, 5000);
    Project project2 = company.getProjectStore().createProject("XPTO3000", "prototype5", "test56", customer,
            typo, sector, startDate3, 7, 5000);

    UserProfile userProfile = new UserProfile("Apresentador");
    UserProfile userProfile2 = new UserProfile("Duo");
    SystemUser newUser = new SystemUser("batatinha", "batatinha@cartoon.com", "des", "gth", "gth", "", userProfile);
    SystemUser newUser2 = new SystemUser("companhia", "companhia@cartoon.com", "des", "gth", "gth", "", userProfile2);
    LocalDate startDate = LocalDate.of(2021, 12, 31);
    LocalDate endDate = LocalDate.of(2022, 1, 5);
    Resource input = new Resource(newUser, startDate, endDate, 100, .5);
    Resource input2 = new Resource(newUser2, startDate, endDate, 100, .5);

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
    void getProject() {
    }

    @Test
    void getUserStoryList() {
    }

    @Test
    void getUS() {
    }

    @Test
    void setPriority() {
    }
}