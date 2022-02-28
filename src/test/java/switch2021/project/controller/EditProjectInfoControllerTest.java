package switch2021.project.controller;

import org.junit.jupiter.api.Test;
import switch2021.project.model.*;
import switch2021.project.stores.ProjectStore;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        typo = company.getTypologyStore().getTypology("Fixed Cost");
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
        typo = company.getTypologyStore().getTypology("Fixed Cost");
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
        typo = company.getTypologyStore().getTypology("Fixed Cost");
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
        assertEquals( "proto", project.getProjectName());
        assertEquals( "test44", project.getDescription());
        assertEquals(project.getStartDate(),LocalDate.of(2022,12,1));
        assertEquals(project.getEndDate(),LocalDate.of(2023,12,1));
        assertEquals( 10, project.getNumberOfSprints());
        assertEquals(10000, project.getBudget());
        assertEquals( 3, project.getSprintDuration());
        assertEquals(project.getProjectStatus(),projectStatus);
        assertEquals(project.getProjectTeam(), projectTeam2);


    }

    @Test
    public void validateProjectFieldsProjectNameLessThen3characters() {
        //Arrange
        company = new Company();
        this.projectStore = company.getProjectStore();
        LocalDate startDate2 = LocalDate.of(2022, 12, 31);
        typo = company.getTypologyStore().getTypology("Fixed Cost");
        customer = company.getCustomerStore().getCustomerByName("ISEP");
        sector = company.getBusinessSectorStore().getBusinessSectorByDescription("Balloons");
        project = company.getProjectStore().createProject("prototype2", "test56", customer,
                typo, sector, startDate2, 7, 5000);
        ProjectTeam projectTeam2 = new ProjectTeam();
        ProjectStatus projectStatus = new ProjectStatus("Quase");

        this.projectStore.saveNewProject(project);

        EditProjectInfoController edit = new EditProjectInfoController(company);
        edit.getProjectRequested(project.getCode());
        String name="1s";
        // Act

        boolean isEdited = edit.editProject(name, "test44", LocalDate.of(2022, 12, 1),
                LocalDate.of(2023, 12, 1), 10, 10000,
                3, projectStatus, projectTeam2);
        //Assert
        assertFalse(isEdited);
    }

    @Test
    public void validateProjectFieldsProjectNameEmpty() {
        //Arrange
        company = new Company();
        this.projectStore = company.getProjectStore();
        LocalDate startDate2 = LocalDate.of(2022, 12, 31);
        typo = company.getTypologyStore().getTypology("Fixed Cost");
        customer = company.getCustomerStore().getCustomerByName("ISEP");
        sector = company.getBusinessSectorStore().getBusinessSectorByDescription("Balloons");
        project = company.getProjectStore().createProject("prototype2", "test56", customer,
                typo, sector, startDate2, 7, 5000);
        ProjectTeam projectTeam2 = new ProjectTeam();
        ProjectStatus projectStatus = new ProjectStatus("Quase");

        this.projectStore.saveNewProject(project);

        EditProjectInfoController edit = new EditProjectInfoController(company);
        edit.getProjectRequested(project.getCode());
        String name="";
        // Act

        boolean isEdited = edit.editProject(name, "test44", LocalDate.of(2022, 12, 1),
                LocalDate.of(2023, 12, 1), 10, 10000,
                3, projectStatus, projectTeam2);
        //Assert
        assertFalse(isEdited);
    }

    @Test
    public void validateProjectFieldsProjectDescriptionEmpty() {
        //Arrange
        company = new Company();
        this.projectStore = company.getProjectStore();
        LocalDate startDate2 = LocalDate.of(2022, 12, 31);
        typo = company.getTypologyStore().getTypology("Fixed Cost");
        customer = company.getCustomerStore().getCustomerByName("ISEP");
        sector = company.getBusinessSectorStore().getBusinessSectorByDescription("Balloons");
        project = company.getProjectStore().createProject("prototype2", "test56", customer,
                typo, sector, startDate2, 7, 5000);
        ProjectTeam projectTeam2 = new ProjectTeam();
        ProjectStatus projectStatus = new ProjectStatus("Quase");

        this.projectStore.saveNewProject(project);

        EditProjectInfoController edit = new EditProjectInfoController(company);
        edit.getProjectRequested(project.getCode());
        String description="";
        // Act

        boolean isEdited = edit.editProject("proto", description, LocalDate.of(2022, 12, 1),
                LocalDate.of(2023, 12, 1), 10, 10000,
                3, projectStatus, projectTeam2);
        //Assert
        assertFalse(isEdited);
    }

    @Test
    public void validateProjectFieldsProjectDescriptionLessThen5() {
        //Arrange
        company = new Company();
        this.projectStore = company.getProjectStore();
        LocalDate startDate2 = LocalDate.of(2022, 12, 31);
        typo = company.getTypologyStore().getTypology("Fixed Cost");
        customer = company.getCustomerStore().getCustomerByName("ISEP");
        sector = company.getBusinessSectorStore().getBusinessSectorByDescription("Balloons");
        project = company.getProjectStore().createProject("prototype2", "test56", customer,
                typo, sector, startDate2, 7, 5000);
        ProjectTeam projectTeam2 = new ProjectTeam();
        ProjectStatus projectStatus = new ProjectStatus("Quase");

        this.projectStore.saveNewProject(project);

        EditProjectInfoController edit = new EditProjectInfoController(company);
        edit.getProjectRequested(project.getCode());
        String description="23r";
        // Act

        boolean isEdited = edit.editProject("proto", description, LocalDate.of(2022, 12, 1),
                LocalDate.of(2023, 12, 1), 10, 10000,
                3, projectStatus, projectTeam2);
        //Assert
        assertFalse(isEdited);
    }

    @Test
    public void validateProjectFieldsProjectNumberOfSprints0() {
        //Arrange
        company = new Company();
        this.projectStore = company.getProjectStore();
        LocalDate startDate2 = LocalDate.of(2022, 12, 31);
        typo = company.getTypologyStore().getTypology("Fixed Cost");
        customer = company.getCustomerStore().getCustomerByName("ISEP");
        sector = company.getBusinessSectorStore().getBusinessSectorByDescription("Balloons");
        project = company.getProjectStore().createProject("prototype2", "test56", customer,
                typo, sector, startDate2, 7, 5000);
        ProjectTeam projectTeam2 = new ProjectTeam();
        ProjectStatus projectStatus = new ProjectStatus("Quase");

        this.projectStore.saveNewProject(project);

        EditProjectInfoController edit = new EditProjectInfoController(company);
        edit.getProjectRequested(project.getCode());
        int numberOfSprints=0;
        // Act

        boolean isEdited = edit.editProject("proto", "test44", LocalDate.of(2022, 12, 1),
                LocalDate.of(2023, 12, 1), numberOfSprints, 10000,
                3, projectStatus, projectTeam2);
        //Assert
        assertFalse(isEdited);
    }

    @Test
    public void validateProjectFieldsProjectNumberOfSprintsNegative() {
        //Arrange
        company = new Company();
        this.projectStore = company.getProjectStore();
        LocalDate startDate2 = LocalDate.of(2022, 12, 31);
        typo = company.getTypologyStore().getTypology("Fixed Cost");
        customer = company.getCustomerStore().getCustomerByName("ISEP");
        sector = company.getBusinessSectorStore().getBusinessSectorByDescription("Balloons");
        project = company.getProjectStore().createProject("prototype2", "test56", customer,
                typo, sector, startDate2, 7, 5000);
        ProjectTeam projectTeam2 = new ProjectTeam();
        ProjectStatus projectStatus = new ProjectStatus("Quase");

        this.projectStore.saveNewProject(project);

        EditProjectInfoController edit = new EditProjectInfoController(company);
        edit.getProjectRequested(project.getCode());
        int numberOfSprints=-5;
        // Act

        boolean isEdited = edit.editProject("proto", "test44", LocalDate.of(2022, 12, 1),
                LocalDate.of(2023, 12, 1), numberOfSprints, 10000,
                3, projectStatus, projectTeam2);
        //Assert
        assertFalse(isEdited);
    }

    @Test
    public void validateProjectFieldsProjectBudget0() {
        //Arrange
        company = new Company();
        this.projectStore = company.getProjectStore();
        LocalDate startDate2 = LocalDate.of(2022, 12, 31);
        typo = company.getTypologyStore().getTypology("Fixed Cost");
        customer = company.getCustomerStore().getCustomerByName("ISEP");
        sector = company.getBusinessSectorStore().getBusinessSectorByDescription("Balloons");
        project = company.getProjectStore().createProject("prototype2", "test56", customer,
                typo, sector, startDate2, 7, 5000);
        ProjectTeam projectTeam2 = new ProjectTeam();
        ProjectStatus projectStatus = new ProjectStatus("Quase");

        this.projectStore.saveNewProject(project);

        EditProjectInfoController edit = new EditProjectInfoController(company);
        edit.getProjectRequested(project.getCode());
        int budget=0;
        // Act

        boolean isEdited = edit.editProject("proto", "test44", LocalDate.of(2022, 12, 1),
                LocalDate.of(2023, 12, 1), 10, budget,
                3, projectStatus, projectTeam2);
        //Assert
        assertFalse(isEdited);
    }

    @Test
    public void validateProjectFieldsProjectBudgetNegative() {
        //Arrange
        company = new Company();
        this.projectStore = company.getProjectStore();
        LocalDate startDate2 = LocalDate.of(2022, 12, 31);
        typo = company.getTypologyStore().getTypology("Fixed Cost");
        customer = company.getCustomerStore().getCustomerByName("ISEP");
        sector = company.getBusinessSectorStore().getBusinessSectorByDescription("Balloons");
        project = company.getProjectStore().createProject("prototype2", "test56", customer,
                typo, sector, startDate2, 7, 5000);
        ProjectTeam projectTeam2 = new ProjectTeam();
        ProjectStatus projectStatus = new ProjectStatus("Quase");

        this.projectStore.saveNewProject(project);

        EditProjectInfoController edit = new EditProjectInfoController(company);
        edit.getProjectRequested(project.getCode());
        int budget=-50000;
        // Act

        boolean isEdited = edit.editProject("proto", "test44", LocalDate.of(2022, 12, 1),
                LocalDate.of(2023, 12, 1), 10, budget,
                3, projectStatus, projectTeam2);
        //Assert
        assertFalse(isEdited);
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