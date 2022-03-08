package switch2021.project.controller;

import org.junit.jupiter.api.DisplayName;
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

    @DisplayName("Edit project - with project team")
    @Test
    void editProjectTestWithProjectTeam() {
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

        //Resource 1
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user1 = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        Resource manuelbras = new Resource(user1, LocalDate.of(2021, 11, 1), LocalDate.of(2022, 11, 15), 100, .5);
        //Resource 2
        SystemUser user2 = new SystemUser("manuelmartins", "manuelmartins@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        Resource manuelmartins = new Resource(user2, LocalDate.now().minusDays(6), LocalDate.now().plusDays(7), 100, 1);
        //Resource 3
        SystemUser user3 = new SystemUser("manueljose", "manueljose@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        Resource manueljose = new Resource(user3, LocalDate.of(2021, 11, 1), LocalDate.of(2021, 11, 15), 100, .5);
        //Resource 4
        SystemUser user4 = new SystemUser("manueloliveira", "manueloliveira@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        Resource manueloliveira = new Resource(user4, LocalDate.now().minusWeeks(1), LocalDate.now().plusWeeks(3), 100, .3333);
        //Resource 5
        SystemUser user5 = new SystemUser("manuelfernandes", "manuelfernandes@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        Resource manuelfernandes = new Resource(user5, LocalDate.of(2021, 11, 16), LocalDate.of(2021, 11, 30), 100, 1);
        //Resource 6
        SystemUser user6 = new SystemUser("manuelgoncalves", "manuelgoncalves@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        Resource manuelgoncalves = new Resource(user6, LocalDate.of(2021, 11, 16), LocalDate.of(2021, 11, 30), 100, 1);
        //Add resources
        project.getProjectTeam().saveResource(manuelbras);
        project.getProjectTeam().saveResource(manueljose);
        project.getProjectTeam().saveResource(manueloliveira);

        ProjectTeam newProjectTeam = new ProjectTeam();
        newProjectTeam.saveResource(manuelfernandes);
        newProjectTeam.saveResource(manuelgoncalves);
        newProjectTeam.saveResource(manuelmartins);

        this.projectStore.saveNewProject(project);

        EditProjectInfoController edit = new EditProjectInfoController(company);
        edit.getProjectRequested(project.getCode());
        //Act
        edit.editProject("proto", "test44", LocalDate.of(2019,12,1),
                LocalDate.of(2023,12,1), 10,10000,
                3, projectStatus, newProjectTeam);


        //Assert
        assertEquals( newProjectTeam, project.getProjectTeam());
//        assertEquals( "test44", project.getDescription());
//        assertEquals(project.getStartDate(),LocalDate.of(2022,12,1));
//        assertEquals(project.getEndDate(),LocalDate.of(2023,12,1));
//        assertEquals( 10, project.getNumberOfSprints());
//        assertEquals(10000, project.getBudget());
//        assertEquals( 3, project.getSprintDuration());
//        assertEquals(project.getProjectStatus(),projectStatus);
//        assertEquals(project.getProjectTeam(), projectTeam2);
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