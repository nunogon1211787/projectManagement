package switch2021.project.stores;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.model.*;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class SprintListTest {

    private Company company;
    private ProjectStore projectStore;
    private Project project;
    private SprintList sprintList;
    LocalDate date;
    private Sprint sprint;
    private Sprint sprint2;
    private Sprint sprint3;
    private Sprint sprint4;
    private Sprint sprint5;
    private Sprint sprint6;
    private Sprint sprint7;
    private Sprint sprint8;
    private Sprint sprint9;
    private Sprint sprint10;


    @BeforeEach
    public void initialize() {
        company = new Company();
        projectStore = company.getProjectStore();
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        company.getBusinessSectorStore().addBusinessSector(company.getBusinessSectorStore().createBusinessSector("sector"));
        company.getCustomerStore().saveNewCustomer(company.getCustomerStore().createCustomer("Teste", "Teste", 123456789));
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        date = LocalDate.of(2022, 1, 1);
        project = projectStore.createProject("prototype", "test1234", customer,
                typo, sector, date, 7, 5000);
        project.setSprintDuration(2);
        sprintList = new SprintList();
        sprint = sprintList.createSprint("Sprint_1", LocalDate.of(2022, 1, 1), project.getSprintDuration());
        sprint2 = sprintList.createSprint("Sprint_2", LocalDate.of(2022, 1, 16), project.getSprintDuration());
        sprint3 = sprintList.createSprint("Sprint_3", LocalDate.of(2021, 12, 19), project.getSprintDuration());
        sprint4 = sprintList.createSprint("Sprint_4", LocalDate.of(2021, 12, 5), project.getSprintDuration());
        sprint5 = sprintList.createSprint("Sprint_5", LocalDate.of(2022, 1, 30), project.getSprintDuration());
        sprint6 = sprintList.createSprint("Sprint_6", LocalDate.of(2022, 2, 13), project.getSprintDuration());
        sprint7 = sprintList.createSprint("Sprint_7", LocalDate.of(2022, 2, 27), project.getSprintDuration());
        sprint8 = sprintList.createSprint("Sprint_8", LocalDate.now().minusWeeks(2), project.getSprintDuration());
        sprint9 = sprintList.createSprint("Sprint_9", LocalDate.now(), project.getSprintDuration());
        sprint10 = sprintList.createSprint("Sprint_10", LocalDate.now().plusWeeks(2), project.getSprintDuration());
        sprintList.saveSprint(sprint);
        sprintList.saveSprint(sprint2);
        sprintList.saveSprint(sprint3);
        sprintList.saveSprint(sprint4);
        sprintList.saveSprint(sprint5);
        sprintList.saveSprint(sprint6);
        sprintList.saveSprint(sprint7);
        sprintList.saveSprint(sprint8);
        sprintList.saveSprint(sprint9);
        sprintList.saveSprint(sprint10);

    }

    @Test
    @DisplayName("Verification Test, to create a Sprint with success")
    public void createSprintSuccess() {
        //Act
        String name = "Sprint_1";
        //Assert
        assertEquals(name, sprint.getSprintName().getText());
        assertEquals(date, sprint.getStartDate());
        assertEquals(LocalDate.of(2022, 1, 14), sprint.getEndDate());

    }

    @Test
    @DisplayName("Verification Test, to create a Sprint with name failure")
    public void createSprintFail_Name() {
        //Act
        String name = "Sprint_2";

        //Assert
        assertNotEquals(name, sprint.getSprintName());
        assertEquals(date, sprint.getStartDate());
        assertEquals(LocalDate.of(2022, 1, 14), sprint.getEndDate());

    }

    @Test
    @DisplayName("Verification Test, to create a Sprint with start date failure")
    public void createSprintFail_StartDate() {
        //Arrange
        SprintList sprintListTest = new SprintList();
        Sprint sprintTest = sprintListTest.createSprint("Sprint_1", LocalDate.of(2022, 1, 2), project.getSprintDuration());
        //Act
        String name = "Sprint_1";

        //Assert
        assertEquals(name, sprintTest.getSprintName().getText());
        assertNotEquals(date, sprintTest.getStartDate());
        assertEquals(LocalDate.of(2022, 1, 15), sprintTest.getEndDate());

    }

    @Test
    @DisplayName("Verification Test, to create a Sprint with sprint duration failure")
    public void createSprintFail_SprintDuration() {
        //Arrange
        SprintList sprintListTest = new SprintList();
        Sprint sprintTest = sprintListTest.createSprint("Sprint_1", LocalDate.of(2022, 1, 1), project.getSprintDuration());
        //Act
        String name = "Sprint_1";

        //Assert
        assertEquals(name, sprintTest.getSprintName().getText());
        assertEquals(date, sprintTest.getStartDate());
        assertNotEquals(LocalDate.of(2022, 1, 16), sprintTest.getEndDate());

    }


    @Test
    @DisplayName("Verification Test, to create a Sprint with failure (all parameters)")
    public void createSprintFailAll() {
        //Arrange
        SprintList sprintListTest = new SprintList();
        Sprint sprintTest = sprintListTest.createSprint("Sprint_1", LocalDate.of(2022, 1, 2), project.getSprintDuration());
        //Act
        String name = "Sprint_2";

        //Assert
        assertNotEquals(name, sprintTest.getSprintName());
        assertNotEquals(date, sprintTest.getStartDate());
        assertNotEquals(LocalDate.of(2022, 2, 16), sprintTest.getEndDate());

    }


    @Test
    @DisplayName("Test to verify the creation of 2 sprints with the same information")
    public void createEqualsSprint() {

        //Arrange
        SprintList sprintStore1 = new SprintList();
        Sprint sprint1 = sprintStore1.createSprint("Sprint_0", LocalDate.of(2022, 1, 1), 2);
        //Act
        Sprint sprint2 = sprintStore1.createSprint("Sprint_0", LocalDate.of(2022, 1, 1), 2);
        //Assert
        assertEquals(sprint1, sprint2);

    }

    @Test
    @DisplayName("Test to search sprint by id")
    public void catchSprintByID() {

        //Arrange
        SprintList sprintListTest = new SprintList();
        Sprint sprintTest = sprintListTest.createSprint("Sprint_0", LocalDate.of(2022, 1, 1), 2);
        //Act
        sprintListTest.saveSprint(sprintTest);
        //Assert
        assertEquals(sprintTest, sprintListTest.getSprint(1));

    }

    @Test
    @DisplayName("Test to verify if sprint already exists")
    public void validateSprintExists() {

        //Arrange
        SprintList sprintListTest = new SprintList();
        Sprint sprintTest = sprintListTest.createSprint("Sprint_0", LocalDate.of(2022, 1, 1), 2);
        //Act
        sprintListTest.saveSprint(sprintTest);
        //Assert
        assertTrue(sprintListTest.validateIfSprintAlreadyExists(sprintTest));
    }

    @Test
    @DisplayName("Test to verify if the act of saving a sprint is correct")
    public void checkSaveTheSprint() {

        //Arrange
        SprintList sprintListTest = new SprintList();
        Sprint sprintTest = sprintListTest.createSprint("String_0", LocalDate.of(2022, 1, 1), 2);
        //Act and Assert
        assertTrue(sprintListTest.saveSprint(sprintTest));

    }

    @Test
    @DisplayName("Test to verify if the sprint was saved")
    public void checkIfSprintWasSaved() {

        //Arrange
        SprintList sprintListTest = new SprintList();
        Sprint sprintTest = sprintListTest.createSprint("String_0", LocalDate.of(2022, 1, 1), 2);
        //Act
        sprintListTest.saveSprint(sprintTest);
        // Assert
        assertEquals(sprintTest, sprintListTest.getSprint(1));

    }

    @Test
    @DisplayName("Get the next start Sprint")
    public void getCurrentSprintTest() {
        //Arrange
        SprintList storeTest = new SprintList();
        storeTest.saveSprint(sprint8);
        storeTest.saveSprint(sprint9);
        storeTest.saveSprint(sprint10);
        //Act
        Sprint sprintTest = storeTest.getCurrentSprint();
        //Assert
        assertEquals(sprint9, sprintTest);
    }

    @Test
    @DisplayName("Get NullPointerException for the next Sprint")
    public void getNullCurrentSprintTest() {
        //Assert
        assertThrows(NullPointerException.class, () -> {
            //Arrange
            SprintList storeTest = new SprintList();
            storeTest.saveSprint(sprint);
            storeTest.saveSprint(sprint3);
            storeTest.saveSprint(sprint4);
            //Act
            storeTest.getCurrentSprint();
        });
    }

    @Test
    @DisplayName("Verification Test, to Start a Sprint")
    public void startASprintSuccess() {
        //Arrange
        Company company = new Company();
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");

        //Project 1
        Project proj1 = company.getProjectStore().createProject("prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.of(2022, 1, 1), 2, 3000);
        proj1.setEndDate(LocalDate.of(2022, 12, 31));
        company.getProjectStore().saveNewProject(proj1);
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");

        //Resource 1
        SystemUser joana1 = new SystemUser("joanaum", "joana1@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej1 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej1 = LocalDate.of(2022, 1, 30);
        Resource joana1R = proj1.createResource(joana1, startDatej1, endDatej1, 100, .5);
        joana1R.setRole(company.getProjectRoleStore().getProjectRole("Scrum Master"));
        proj1.getProjectTeam().saveResource(joana1R);

        //Resource 2
        SystemUser joana2 = new SystemUser("joanadois", "joana2@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej2 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej2 = LocalDate.of(2022, 1, 30);
        Resource joana2R = proj1.createResource(joana2, startDatej2, endDatej2, 100, 1);
        joana2R.setRole(company.getProjectRoleStore().getProjectRole("Product Owner"));
        proj1.getProjectTeam().saveResource(joana2R);

        //Resource 3
        SystemUser joana3 = new SystemUser("joanatres", "joana3@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej3 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej3 = LocalDate.of(2022, 12, 31);
        Resource joana3R = proj1.createResource(joana3, startDatej3, endDatej3, 100, .5);
        joana3R.setRole(company.getProjectRoleStore().getProjectRole("Project Manager"));
        proj1.getProjectTeam().saveResource(joana3R);

        //Resource 4
        SystemUser joana4 = new SystemUser("joanaquatro", "joana4@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej4 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej4 = LocalDate.of(2022, 12, 31);
        Resource joana4R = proj1.createResource(joana4, startDatej4, endDatej4, 100, .3333);
        joana4R.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
        proj1.getProjectTeam().saveResource(joana4R);

        //Create a Sprint
        SprintList sprintListTest1 = new SprintList();
        Sprint sprint1 = sprintListTest1.createSprint("Sprint_1", LocalDate.of(2022, 1, 1),
                project.getSprintDuration());
        sprintListTest1.saveSprint(sprint1);
        Sprint sprint2 = sprintListTest1.createSprint("Sprint_2", LocalDate.of(2022, 1, 15),
                project.getSprintDuration());
        sprintListTest1.saveSprint(sprint2);

        //Assert
        assertTrue(sprintListTest1.startASprint(2, LocalDate.of(2022, 1, 15),
                proj1.getProjectTeam(), 2));
    }

    @Test
    @DisplayName("Fail Test, to Start a Sprint - Without project team")
    public void startASprintFail_ProjectTeam() {
        //Arrange
        Company company = new Company();
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");

        //Project 1
        Project proj1 = company.getProjectStore().createProject("prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.of(2022, 1, 1), 2, 3000);
        proj1.setEndDate(LocalDate.of(2022, 12, 31));
        company.getProjectStore().saveNewProject(proj1);
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");

        //Resource 1
        SystemUser joana1 = new SystemUser("joanaum", "joana1@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej1 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej1 = LocalDate.of(2022, 1, 30);
        Resource joana1R = proj1.createResource(joana1, startDatej1, endDatej1, 100, .5);
        joana1R.setRole(company.getProjectRoleStore().getProjectRole("Scrum Master"));
        proj1.getProjectTeam().saveResource(joana1R);

        //Resource 2
        SystemUser joana2 = new SystemUser("joanadois", "joana2@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej2 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej2 = LocalDate.of(2022, 1, 30);
        Resource joana2R = proj1.createResource(joana2, startDatej2, endDatej2, 100, 1);
        joana2R.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
        proj1.getProjectTeam().saveResource(joana2R);

        //Resource 3
        SystemUser joana3 = new SystemUser("joanatres", "joana3@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej3 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej3 = LocalDate.of(2022, 12, 31);
        Resource joana3R = proj1.createResource(joana3, startDatej3, endDatej3, 100, .5);
        joana3R.setRole(company.getProjectRoleStore().getProjectRole("Project Manager"));
        proj1.getProjectTeam().saveResource(joana3R);

        //Resource 4
        SystemUser joana4 = new SystemUser("joanaquatro", "joana4@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej4 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej4 = LocalDate.of(2022, 12, 31);
        Resource joana4R = proj1.createResource(joana4, startDatej4, endDatej4, 100, .3333);
        joana4R.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
        proj1.getProjectTeam().saveResource(joana4R);

        //Create a Sprint
        SprintList sprintListTest1 = new SprintList();
        Sprint sprint1 = sprintListTest1.createSprint("Sprint_1", LocalDate.of(2022, 1, 1),
                project.getSprintDuration());
        sprintListTest1.saveSprint(sprint1);

        //Assert
        assertFalse(sprintListTest1.startASprint(2, LocalDate.of(2022, 2, 5),
                proj1.getProjectTeam(), 2));
    }

    @Test
    @DisplayName("Fail Test, to Start a Sprint - Wrong Sprint ID")
    public void startASprintFail_SprintID() {
        //Arrange
        Company company = new Company();
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");

        //Project 1
        Project proj1 = company.getProjectStore().createProject("prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.of(2022, 1, 1), 2, 3000);
        proj1.setEndDate(LocalDate.of(2022, 12, 31));
        company.getProjectStore().saveNewProject(proj1);
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");

        //Resource 1
        SystemUser joana1 = new SystemUser("joanaum", "joana1@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej1 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej1 = LocalDate.of(2022, 1, 30);
        Resource joana1R = proj1.createResource(joana1, startDatej1, endDatej1, 100, .5);
        joana1R.setRole(company.getProjectRoleStore().getProjectRole("Scrum Master"));
        proj1.getProjectTeam().saveResource(joana1R);

        //Resource 2
        SystemUser joana2 = new SystemUser("joanadois", "joana2@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej2 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej2 = LocalDate.of(2022, 1, 30);
        Resource joana2R = proj1.createResource(joana2, startDatej2, endDatej2, 100, 1);
        joana2R.setRole(company.getProjectRoleStore().getProjectRole("Product Owner"));
        proj1.getProjectTeam().saveResource(joana2R);

        //Resource 3
        SystemUser joana3 = new SystemUser("joanatres", "joana3@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej3 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej3 = LocalDate.of(2022, 12, 31);
        Resource joana3R = proj1.createResource(joana3, startDatej3, endDatej3, 100, .5);
        joana3R.setRole(company.getProjectRoleStore().getProjectRole("Project Manager"));
        proj1.getProjectTeam().saveResource(joana3R);

        //Resource 4
        SystemUser joana4 = new SystemUser("joanaquatro", "joana4@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej4 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej4 = LocalDate.of(2022, 12, 31);
        Resource joana4R = proj1.createResource(joana4, startDatej4, endDatej4, 100, .3333);
        joana4R.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
        proj1.getProjectTeam().saveResource(joana4R);

        //Create a Sprint
        SprintList sprintListTest1 = new SprintList();
        Sprint sprint1 = sprintListTest1.createSprint("Sprint_1", LocalDate.of(2022, 1, 1),
                project.getSprintDuration());
        sprintListTest1.saveSprint(sprint1);

        //Assert
        assertFalse(sprintListTest1.startASprint(2, LocalDate.of(2022, 2, 1),
                proj1.getProjectTeam(), 2));
    }

    @Test
    @DisplayName("Fail Test, to Start a Sprint - Wrong Start Date")
    public void startASprintFail_StartDate() {
        //Arrange
        Company company = new Company();
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");

        //Project 1
        Project proj1 = company.getProjectStore().createProject("prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.of(2022, 1, 1), 2, 3000);
        proj1.setEndDate(LocalDate.of(2022, 12, 31));
        company.getProjectStore().saveNewProject(proj1);
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");

        //Resource 1
        SystemUser joana1 = new SystemUser("joanaum", "joana1@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej1 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej1 = LocalDate.of(2022, 1, 30);
        Resource joana1R = proj1.createResource(joana1, startDatej1, endDatej1, 100, .5);
        joana1R.setRole(company.getProjectRoleStore().getProjectRole("Scrum Master"));
        proj1.getProjectTeam().saveResource(joana1R);

        //Resource 2
        SystemUser joana2 = new SystemUser("joanadois", "joana2@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej2 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej2 = LocalDate.of(2022, 1, 30);
        Resource joana2R = proj1.createResource(joana2, startDatej2, endDatej2, 100, 1);
        joana2R.setRole(company.getProjectRoleStore().getProjectRole("Product Owner"));
        proj1.getProjectTeam().saveResource(joana2R);

        //Resource 3
        SystemUser joana3 = new SystemUser("joanatres", "joana3@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej3 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej3 = LocalDate.of(2022, 12, 31);
        Resource joana3R = proj1.createResource(joana3, startDatej3, endDatej3, 100, .5);
        joana3R.setRole(company.getProjectRoleStore().getProjectRole("Project Manager"));
        proj1.getProjectTeam().saveResource(joana3R);

        //Resource 4
        SystemUser joana4 = new SystemUser("joanaquatro", "joana4@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej4 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej4 = LocalDate.of(2022, 12, 31);
        Resource joana4R = proj1.createResource(joana4, startDatej4, endDatej4, 100, .3333);
        joana4R.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
        proj1.getProjectTeam().saveResource(joana4R);

        //Create a Sprint
        SprintList sprintListTest1 = new SprintList();
        Sprint sprint1 = sprintListTest1.createSprint("Sprint_1", LocalDate.of(2022, 1, 1),
                project.getSprintDuration());
        sprintListTest1.saveSprint(sprint1);

        //Assert
        assertFalse(sprintListTest1.startASprint(2, LocalDate.of(2021, 12, 31),
                proj1.getProjectTeam(), 2));
    }

    @Test
    @DisplayName("Fail Global Test, to Start a Sprint - Without project team, with wrong id sprint and wrong startDate")
    public void startASprintFail() {
        //Arrange
        Company company = new Company();
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");

        //Project 1
        Project proj1 = company.getProjectStore().createProject("prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.of(2022, 1, 1), 2, 3000);
        proj1.setEndDate(LocalDate.of(2022, 12, 31));
        company.getProjectStore().saveNewProject(proj1);
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");

        //Resource 1
        SystemUser joana1 = new SystemUser("joanaum", "joana1@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej1 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej1 = LocalDate.of(2022, 1, 30);
        Resource joana1R = proj1.createResource(joana1, startDatej1, endDatej1, 100, .5);
        joana1R.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
        proj1.getProjectTeam().saveResource(joana1R);

        //Resource 2
        SystemUser joana2 = new SystemUser("joanadois", "joana2@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej2 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej2 = LocalDate.of(2022, 1, 30);
        Resource joana2R = proj1.createResource(joana2, startDatej2, endDatej2, 100, 1);
        joana2R.setRole(company.getProjectRoleStore().getProjectRole("Project Manager"));
        proj1.getProjectTeam().saveResource(joana2R);

        //Resource 3
        SystemUser joana3 = new SystemUser("joanatres", "joana3@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej3 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej3 = LocalDate.of(2022, 12, 31);
        Resource joana3R = proj1.createResource(joana3, startDatej3, endDatej3, 100, .5);
        joana3R.setRole(company.getProjectRoleStore().getProjectRole("Project Manager"));
        proj1.getProjectTeam().saveResource(joana3R);

        //Resource 4
        SystemUser joana4 = new SystemUser("joanaquatro", "joana4@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej4 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej4 = LocalDate.of(2022, 12, 31);
        Resource joana4R = proj1.createResource(joana4, startDatej4, endDatej4, 100, .3333);
        joana4R.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
        proj1.getProjectTeam().saveResource(joana4R);

        //Create a Sprint
        SprintList sprintListTest1 = new SprintList();
        Sprint sprint1 = sprintListTest1.createSprint("Sprint_1", LocalDate.of(2022, 1, 1),
                project.getSprintDuration());
        sprintListTest1.saveSprint(sprint1);

        //Assert
        assertFalse(sprintListTest1.startASprint(2, LocalDate.of(2022, 2, 1),
                proj1.getProjectTeam(), 2));
    }

    @Test
    @DisplayName("Validate start date")
    public void validateStartDate() {
        //Arrange
        LocalDate startDatej4 = LocalDate.of(2022, 1, 15);
        LocalDate startDatej5 = LocalDate.of(2022, 1, 1);

        SprintList sprintListTest = new SprintList();
        Sprint sprintTest = sprintListTest.createSprint("String_0", startDatej4, 2);
        Sprint sprintTest2 = sprintListTest.createSprint("String_0", startDatej5, 2);
        //Act
        sprintListTest.saveSprint(sprintTest);
        sprintListTest.saveSprint(sprintTest2);

        //Assert
        assertEquals(startDatej4, sprintTest.getStartDate());
    }

}

