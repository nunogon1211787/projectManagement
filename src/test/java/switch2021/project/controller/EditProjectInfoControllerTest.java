package switch2021.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.factory.ResourceFactory;
import switch2021.project.model.*;
import switch2021.project.model.Project.*;
import switch2021.project.model.Resource.Resource;
import switch2021.project.model.SystemUser.SystemUser;
import switch2021.project.model.Typology.Typology;
import switch2021.project.model.UserProfile.UserProfile;
import switch2021.project.repositories.ProjectTeam;
import switch2021.project.model.valueObject.*;
import switch2021.project.repositories.ProjectStore;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

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
        assertEquals(expected, actual);
    }

    @Test
    void getProjectListTest_1() {
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
        company.getProjectStore().saveNewProject(project);
        company.getProjectStore().saveNewProject(project2);
        company.getProjectStore().saveNewProject(project3);

        //Act

        int expected = edit.getProjectList().size();

        //Assert
        assertEquals(expected, 3);
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
        String code = project.getProjectCode().getCode();
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
        ResourceFactory resFac = mock(ResourceFactory.class);
        ProjectTeam projectTeam2 = new ProjectTeam(resFac);
        ProjectStatusEnum projectStatus = ProjectStatusEnum.TRANSITION;

        this.projectStore.saveNewProject(project);

        EditProjectInfoController edit = new EditProjectInfoController(company);
        edit.getProjectRequested(project.getProjectCode().getCode());
        //Act
        edit.editProject("proto", "test44", LocalDate.of(2022, 12, 1),
                LocalDate.of(2023, 12, 1), 10, 10000,
                7, projectStatus, projectTeam2);


        //Assert
        assertEquals("proto", project.getProjectName().getText());
        assertEquals("test44", project.getDescription().getText());
        assertEquals(project.getStartDate(), LocalDate.of(2022, 12, 1));
        assertEquals(project.getEndDate(), LocalDate.of(2023, 12, 1));
        assertEquals(10, project.getNumberOfSprints());
        assertEquals(10000, project.getBudget().getBudgetP());
        assertEquals(7, project.getSprintDuration().getSprintDurationDays());
        assertEquals(10000, project.getBudget().getBudgetP());
        assertEquals(7, project.getSprintDuration().getSprintDurationDays());
        assertEquals(project.getProjectStatus(), projectStatus);
        assertEquals(project.getProjectTeam(), projectTeam2);
    }

    @Test
    void editProjectTest_Success() {
        //Arrange
        company = new Company();
        this.projectStore = company.getProjectStore();
        LocalDate startDate2 = LocalDate.of(2022, 12, 31);
        typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        customer = company.getCustomerStore().getCustomerByName("ISEP");
        sector = company.getBusinessSectorStore().getBusinessSectorByDescription("Balloons");
        project = company.getProjectStore().createProject("prototype2", "test56", customer,
                typo, sector, startDate2, 7, 5000);
        ProjectStatusEnum projectStatus = ProjectStatusEnum.INCEPTION;

        //Resource 1
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user1 = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile);
        Resource manuelbras = new Resource(user1, LocalDate.of(2021, 11, 1), LocalDate.of(2022, 11, 15), new CostPerHour(100), new PercentageOfAllocation(.5));
        //Resource 2
        SystemUser user2 = new SystemUser("manuelmartins", "manuelmartins@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile);
        Resource manuelmartins = new Resource(user2, LocalDate.now().minusDays(6), LocalDate.now().plusDays(7), new CostPerHour(100), new PercentageOfAllocation(1));
        //Resource 3
        SystemUser user3 = new SystemUser("manueljose", "manueljose@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile);
        Resource manueljose = new Resource(user3, LocalDate.of(2021, 11, 1), LocalDate.of(2021, 11, 15), new CostPerHour(100), new PercentageOfAllocation(.5));
        //Resource 4
        SystemUser user4 = new SystemUser("manueloliveira", "manueloliveira@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile);
        Resource manueloliveira = new Resource(user4, LocalDate.now().minusWeeks(1), LocalDate.now().plusWeeks(3), new CostPerHour(100), new PercentageOfAllocation(.3333));
        //Resource 5
        SystemUser user5 = new SystemUser("manuelfernandes", "manuelfernandes@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile);
        Resource manuelfernandes = new Resource(user5, LocalDate.of(2021, 11, 16), LocalDate.of(2021, 11, 30), new CostPerHour(100), new PercentageOfAllocation(1));
        //Resource 6
        SystemUser user6 = new SystemUser("manuelgoncalves", "manuelgoncalves@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile);
        Resource manuelgoncalves = new Resource(user6, LocalDate.of(2021, 11, 16), LocalDate.of(2021, 11, 30), new CostPerHour(100), new PercentageOfAllocation(1));
        //Add resources
        project.getProjectTeam().saveResource(manuelbras);
        project.getProjectTeam().saveResource(manueljose);
        project.getProjectTeam().saveResource(manueloliveira);
        ResourceFactory resFac = mock(ResourceFactory.class);

        ProjectTeam newProjectTeam = new ProjectTeam(resFac);
        newProjectTeam.saveResource(manuelfernandes);
        newProjectTeam.saveResource(manuelgoncalves);
        newProjectTeam.saveResource(manuelmartins);

        this.projectStore.saveNewProject(project);

        EditProjectInfoController edit = new EditProjectInfoController(company);
        edit.getProjectRequested(project.getProjectCode().getCode());
        //Act
        edit.editProject("proto", "test44", LocalDate.of(2020, 12, 1),
                LocalDate.of(2023, 12, 1), 10, 10000,
                7, projectStatus, newProjectTeam);


        //Assert
        ProjectTeam x = project.getProjectTeam();
        assertEquals(newProjectTeam, x);
    }

    @Test
    void editProjectTest_Fail() {
        //Arrange
        company = new Company();
        this.projectStore = company.getProjectStore();
        LocalDate startDate2 = LocalDate.of(2022, 12, 31);
        typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        customer = company.getCustomerStore().getCustomerByName("ISEP");
        sector = company.getBusinessSectorStore().getBusinessSectorByDescription("Balloons");
        project = company.getProjectStore().createProject("prototype2", "test56", customer,
                typo, sector, startDate2, 7, 5000);
        ResourceFactory resFac = mock(ResourceFactory.class);
        ProjectTeam projectTeam2 = new ProjectTeam(resFac);
        ProjectStatusEnum projectStatus = ProjectStatusEnum.TRANSITION;
        this.projectStore.saveNewProject(project);

        EditProjectInfoController edit = new EditProjectInfoController(company);
        edit.getProjectRequested(project.getProjectCode().getCode());
        //Act
        boolean x = edit.editProject("prototype2", "test56", LocalDate.of(2022, 12, 31),
                LocalDate.of(2023, 12, 1), 0, 5000,
                3, projectStatus, projectTeam2);


        //Assert
        assertFalse(x);
    }

    @DisplayName("Edit project - with project team")
    @Test
    void editProjectTestWithProjectTeam() {
        //Arrange
        company = new Company();
        this.projectStore = company.getProjectStore();
        LocalDate startDate2 = LocalDate.of(2022, 12, 31);
        typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        customer = company.getCustomerStore().getCustomerByName("ISEP");
        sector = company.getBusinessSectorStore().getBusinessSectorByDescription("Balloons");
        project = company.getProjectStore().createProject("prototype2", "test56", customer,
                typo, sector, startDate2, 7, 5000);
        ProjectStatusEnum projectStatus = ProjectStatusEnum.INCEPTION;
        CostPerHour costPerHour = new CostPerHour(100);
        PercentageOfAllocation percentageOfAllocation = new PercentageOfAllocation(0.5);
        PercentageOfAllocation percentageOfAllocation1 = new PercentageOfAllocation(1);
        PercentageOfAllocation percentageOfAllocation2 = new PercentageOfAllocation(.3333);

        //Resource 1
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user1 = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile);
        Resource manuelbras = new Resource(user1, LocalDate.of(2021, 11, 1), LocalDate.of(2022, 11, 15), costPerHour, percentageOfAllocation);
        //Resource 2
        SystemUser user2 = new SystemUser("manuelmartins", "manuelmartins@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile);
        Resource manuelmartins = new Resource(user2, LocalDate.now().minusDays(6), LocalDate.now().plusDays(7), costPerHour, percentageOfAllocation1);
        //Resource 3
        SystemUser user3 = new SystemUser("manueljose", "manueljose@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile);
        Resource manueljose = new Resource(user3, LocalDate.of(2021, 11, 1), LocalDate.of(2021, 11, 15), costPerHour, percentageOfAllocation);
        //Resource 4
        SystemUser user4 = new SystemUser("manueloliveira", "manueloliveira@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile);
        Resource manueloliveira = new Resource(user4, LocalDate.now().minusWeeks(1), LocalDate.now().plusWeeks(3), costPerHour, percentageOfAllocation2);
        //Resource 5
        SystemUser user5 = new SystemUser("manuelfernandes", "manuelfernandes@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile);
        Resource manuelfernandes = new Resource(user5, LocalDate.of(2021, 11, 16), LocalDate.of(2021, 11, 30), costPerHour, percentageOfAllocation1);
        //Resource 6
        SystemUser user6 = new SystemUser("manuelgoncalves", "manuelgoncalves@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile);
        Resource manuelgoncalves = new Resource(user6, LocalDate.of(2021, 11, 16), LocalDate.of(2021, 11, 30), costPerHour, percentageOfAllocation1);
        //Add resources
        project.getProjectTeam().saveResource(manuelbras);
        project.getProjectTeam().saveResource(manueljose);
        project.getProjectTeam().saveResource(manueloliveira);
        ResourceFactory resFac = mock(ResourceFactory.class);

        ProjectTeam newProjectTeam = new ProjectTeam(resFac);
        newProjectTeam.saveResource(manuelfernandes);
        newProjectTeam.saveResource(manuelgoncalves);
        newProjectTeam.saveResource(manuelmartins);

        this.projectStore.saveNewProject(project);

        EditProjectInfoController edit = new EditProjectInfoController(company);
        edit.getProjectRequested(project.getProjectCode().getCode());
        //Act
        edit.editProject("proto", "test44", LocalDate.of(2020, 12, 1),
                LocalDate.of(2023, 12, 1), 10, 10000,
                7, projectStatus, newProjectTeam);


        //Assert
        ProjectTeam x = project.getProjectTeam();
        assertEquals(newProjectTeam, x);
    }

    @Test
    public void validateProjectFieldsProjectNumberOfSprints0() {
        //Arrange
        company = new Company();
        this.projectStore = company.getProjectStore();
        LocalDate startDate2 = LocalDate.of(2022, 12, 31);
        typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        customer = company.getCustomerStore().getCustomerByName("ISEP");
        sector = company.getBusinessSectorStore().getBusinessSectorByDescription("Balloons");
        project = company.getProjectStore().createProject("prototype2", "test56", customer,
                typo, sector, startDate2, 7, 5000);
        ResourceFactory resFac = mock(ResourceFactory.class);
        ProjectTeam projectTeam2 = new ProjectTeam(resFac);
        ProjectStatusEnum projectStatus = ProjectStatusEnum.INCEPTION;

        this.projectStore.saveNewProject(project);

        EditProjectInfoController edit = new EditProjectInfoController(company);
        edit.getProjectRequested(project.getProjectCode().getCode());
        int numberOfSprints = 0;
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
        typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        customer = company.getCustomerStore().getCustomerByName("ISEP");
        sector = company.getBusinessSectorStore().getBusinessSectorByDescription("Balloons");
        project = company.getProjectStore().createProject("prototype2", "test56", customer,
                typo, sector, startDate2, 7, 5000);
        ResourceFactory resFac = mock(ResourceFactory.class);
        ProjectTeam projectTeam2 = new ProjectTeam(resFac);
        ProjectStatusEnum projectStatus = ProjectStatusEnum.INCEPTION;

        this.projectStore.saveNewProject(project);

        EditProjectInfoController edit = new EditProjectInfoController(company);
        edit.getProjectRequested(project.getProjectCode().getCode());
        int numberOfSprints = -5;
        // Act

        boolean isEdited = edit.editProject("proto", "test44", LocalDate.of(2022, 12, 1),
                LocalDate.of(2023, 12, 1), numberOfSprints, 10000,
                3, projectStatus, projectTeam2);
        //Assert
        assertFalse(isEdited);
    }


}