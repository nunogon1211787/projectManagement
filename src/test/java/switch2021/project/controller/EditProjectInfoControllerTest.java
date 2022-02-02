package switch2021.project.controller;

import org.junit.jupiter.api.Test;
import switch2021.project.model.*;
import switch2021.project.stores.ProjectStore;
import switch2021.project.stores.UserStoryStatusStore;

import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

class EditProjectInfoControllerTest {


    private Company company;
    private ProjectStore projectStore;
    private Typology typo;
    private Customer customer;
    private BusinessSector sector;
    private Project project;
    private Project project2;
    private Project project3;

    @Test
    void getProjectListTest() {
        //Arrange
        company = new Company();
        EditProjectInfoController edit = new EditProjectInfoController(company);
        LocalDate startDate2 = LocalDate.of(2022, 12, 31);
        LocalDate startDate3 = LocalDate.of(2022, 12, 31);
        typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        customer = company.getCustomerStore().getCustomerByName("ISEP");
        sector = company.getBusinessSectorStore().getBusinessSectorByDescription("Balloons");
        project = company.getProjectStore().createProject("prototype2", "test56", customer,
                typo, sector, startDate2, 7, 5000);
        project2 = company.getProjectStore().createProject("prototype5", "test56", customer,
                typo, sector, startDate3, 7, 5000);
        project3 = company.getProjectStore().createProject("prototype8", "test56", customer,
                typo, sector, startDate3, 4, 3000);
        this.projectStore = company.getProjectStore();

        //Act

        List<Project> expected = edit.getProjectList();
        List<Project> actual = projectStore.getProjects();

        //Assert
        assertEquals(expected,actual);
    }

    @Test
    void getProjectTest() {
        //Arrange
        company = new Company();
        this.projectStore = company.getProjectStore();
        LocalDate startDate2 = LocalDate.of(2022, 12, 31);
        typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        customer = company.getCustomerStore().getCustomerByName("ISEP");
        sector = company.getBusinessSectorStore().getBusinessSectorByDescription("Balloons");
        project = company.getProjectStore().createProject("prototype2", "test56", customer,
                typo, sector, startDate2, 7, 5000);
        this.projectStore.saveNewProject(project);

        EditProjectInfoController edit = new EditProjectInfoController(company);

        //Act
        String code = project.getCode();
        Project expected = edit.getProjectRequested(code);


        //Assert
        assertEquals(expected, project);
    }

    @Test
    void editProjectTest() {
        //Arrange
        company = new Company();
        this.projectStore = company.getProjectStore();
        LocalDate startDate2 = LocalDate.of(2022, 12, 31);
        typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        customer = company.getCustomerStore().getCustomerByName("ISEP");
        sector = company.getBusinessSectorStore().getBusinessSectorByDescription("Balloons");
        project = company.getProjectStore().createProject("prototype2", "test56", customer,
                typo, sector, startDate2, 7, 5000);
        ProjectTeam projectTeam2 = new ProjectTeam();
        ProjectStatus projectStatus = new ProjectStatus("Quase");

        this.projectStore.saveNewProject(project);

        EditProjectInfoController edit = new EditProjectInfoController(company);
        edit.getProjectRequested(project.getCode());
        //Act
        edit.editProject("proto", "test44", LocalDate.of(2022,12,1),
                LocalDate.of(2023,12,1), 10,10000,
                3, projectStatus, projectTeam2);


        //Assert
        assertEquals(project.getProjectName(), "proto");
        assertEquals(project.getDescription(), "test44");
        assertEquals(project.getStartDate(),LocalDate.of(2022,12,1));
        assertEquals(project.getEndDate(),LocalDate.of(2023,12,1));
        assertEquals(project.getNumberOfSprints(), 10);
        assertEquals(project.getBudget(),10000);
        assertEquals(project.getSprintDuration(), 3);
        assertEquals(project.getProjectStatus(),projectStatus);
        assertEquals(project.getProjectTeam(), projectTeam2);


    }




//    @Test
//    void getProjectRequested() {
//    }
//
//    @Test
//    void editProject() {
//    }
//
//    @Test
//    void saveProject() {
//    }



}