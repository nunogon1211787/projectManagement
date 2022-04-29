//package switch2021.project.stores;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import switch2021.project.factory.SprintFactory;
//import switch2021.project.model.*;
//import switch2021.project.model.Project.*;
//import switch2021.project.model.Resource.old.Resource;
//import switch2021.project.model.Sprint.Sprint;
//import switch2021.project.model.Sprint.SprintID;
//import switch2021.project.model.Sprint.SprintStore;
//import switch2021.project.model.SystemUser.SystemUser;
//import switch2021.project.model.Typology.Typology;
//import switch2021.project.model.UserProfile.UserProfile;
//import switch2021.project.model.valueObject.*;
//import switch2021.project.repositories.ProjectStore;
//import java.time.LocalDate;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class SprintStoreTest {
//
//    private Project project;
//    LocalDate date;
//    private Sprint sprint;
//    private Sprint sprint3;
//    private Sprint sprint4;
//    private Sprint sprint8;
//    private Sprint sprint9;
//    private Sprint sprint10;
//
//
//    @BeforeEach
//    public void initialize() {
//        Company company = new Company();
//        ProjectStore projectStore = company.getProjectStore();
//        Typology typo = company.getTypologyRepository().findTypologyByDescription("Fixed Cost");
//        company.getBusinessSectorStore().createAndAddBusinessSector("sector");
//        company.getCustomerStore().createAndAddCustomer("Teste", "Teste@teste.com", 123456789);
//        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
//        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
//        date = LocalDate.of(2022, 1, 1);
//        project = projectStore.createAndSaveProject("prototype", "test1234", customer,
//                typo, sector, date, 7, 5000);
//        project.setSprintDuration(new SprintDuration(14));
//        SprintStore sprintList = new SprintStore();
//        SprintFactory sprintFactory = new SprintFactory();
//        sprint = sprintFactory.createSprint("Project_2022_1", "Project_2022_1_Sprint 1", "Sprint Name 1");
//        sprintList.saveSprint(sprint);
//        sprint.setStartDate(LocalDate.of(2022, 1, 1));
//        Sprint sprint2 = sprintFactory.createSprint("Project_2022_1", "Project_2022_1_Sprint 2", "Sprint Name 2");
//        sprintList.saveSprint(sprint2);
//        sprint3 = sprintFactory.createSprint("Project_2022_1", "Project_2022_1_Sprint 3", "Sprint Name 3");
//        sprintList.saveSprint(sprint3);
//        sprint4 = sprintFactory.createSprint("Project_2022_1", "Project_2022_1_Sprint 4", "Sprint Name 4");
//        sprintList.saveSprint(sprint4);
//        Sprint sprint5 = sprintFactory.createSprint("Project_2022_1", "Project_2022_1_Sprint 5", "Sprint Name 5");
//        Sprint sprint6 = sprintFactory.createSprint("Project_2022_1", "Project_2022_1_Sprint 6", "Sprint Name 6");
//        Sprint sprint7 = sprintFactory.createSprint("Project_2022_1","Project_2022_1_Sprint 7", "Sprint Name 7");
//        sprint8 = sprintFactory.createSprint("Project_2022_1", "Project_2022_1_Sprint 8", "Sprint Name 8");
//        sprintList.saveSprint(sprint8);
//        sprint9 = sprintFactory.createSprint("Project_2022_1", "SProject_2022_1_Sprint 9", "Sprint Name 9");
//        sprintList.saveSprint(sprint9);
//        sprint10 = sprintFactory.createSprint("Project_2022_1", "Project_2022_1_Sprint 10", "Sprint Name 10");
//        sprintList.saveSprint(sprint10);
//
//
//    }
//
//    @Test
//    @DisplayName("Verification Test, to create a Sprint with success")
//    public void createSprintSuccess() {
//        //Act
//        Sprint x = new Sprint("Project_2022_1", "Project_2022_1_Sprint 1", "Sprint_X");
//        x.setStartDate(LocalDate.of(2022, 1, 1));
//        x.setEndDate(LocalDate.of(2022, 1, 15));
//        //Assert
//        assertEquals("Sprint_X", x.getSprintName().getText());
//        assertEquals(LocalDate.of(2022, 1,1), x.getStartDate());
//        assertEquals(LocalDate.of(2022, 1, 15), x.getEndDate());
//    }
//
//    @Test
//    @DisplayName("Verification Test, to create a Sprint with name failure")
//    public void createSprintFail_Name() {
//        //Act
//        Sprint y = new Sprint("Project_2022_1", "Project_2022_1_Sprint 1", "Sprint_Z");
//        y.setStartDate(LocalDate.of(2022, 1, 1));
//        y.setEndDate(LocalDate.of(2022, 1, 15));
//        //Assert
//        assertNotEquals("Sprint_Z", y.getSprintName());
//        assertEquals(LocalDate.of(2022, 1, 1), y.getStartDate());
//        assertEquals(LocalDate.of(2022, 1, 15), y.getEndDate());
//
//    }
//
//    @Test
//    @DisplayName("Verification Test, to create a Sprint with start date failure")
//    public void createSprintFail_StartDate() {
//        //Arrange
//        SprintStore sprintListTest = new SprintStore();
//        SprintFactory sprintFactory = new SprintFactory();
//        Sprint sprintTest = sprintFactory.createSprint("Project_2022_1", "Project_2022_1_Sprint 1",
//                "Sprint_1");
//        sprintListTest.saveSprint(sprintTest);
//        //Act
//        String name = "Sprint_1";
//        sprintTest.setStartDate(LocalDate.of(2022, 1, 1));
//        sprintTest.setEndDate(LocalDate.of(2022, 1, 15));
//        //Assert
//        assertEquals(name, sprintTest.getSprintName().getText());
//        assertNotEquals(LocalDate.of(2022, 1, 3), sprintTest.getStartDate());
//        assertEquals(LocalDate.of(2022, 1, 15), sprintTest.getEndDate());
//
//    }
//
//    @Test
//    @DisplayName("Verification Test, to create a Sprint with sprint duration failure")
//    public void createSprintFail_SprintDuration() {
//        //Arrange
//        SprintStore sprintListTest = new SprintStore();
//        SprintFactory sprintFactory = new SprintFactory();
//        Sprint sprintTest = sprintFactory.createSprint("Project_2022_1", "Project_2022_1_Sprint 1", "Sprint_1");
//        sprintListTest.saveSprint(sprintTest);
//        sprintTest.setStartDate(LocalDate.of(2022, 1, 1));
//        //Act
//        String name = "Sprint_1";
//
//        //Assert
//        assertEquals(name, sprintTest.getSprintName().getText());
//        assertEquals(date, sprintTest.getStartDate());
//        assertNotEquals(LocalDate.of(2022, 1, 16), sprintTest.getEndDate());
//
//    }
//
//
//    @Test
//    @DisplayName("Verification Test, to create a Sprint with failure (all parameters)")
//    public void createSprintFailAll() {
//        //Arrange
//        SprintStore sprintListTest = new SprintStore();
//        SprintFactory sprintFactory = new SprintFactory();
//        Sprint sprintTest = sprintFactory.createSprint("Project_2022_1", "Project_2022_1_Sprint 1",
//                "Sprint Name");
//        sprintListTest.saveSprint(sprintTest);
//        //Act
//        String name = "Sprint_2";
//
//        //Assert
//        assertNotEquals(name, sprintTest.getSprintName());
//        assertNotEquals(date, LocalDate.of(2020, 1, 1));
//        assertNotEquals(LocalDate.of(2022, 2, 16), sprintTest.getEndDate());
//
//    }
//
//
//    @Test
//    @DisplayName("Test to verify the creation of 2 sprints with different information")
//    public void createNotEqualsSprint() {
//
//        //Arrange
//        SprintStore sprintStore1 = new SprintStore();
//        SprintFactory sprintFactory = new SprintFactory();
//        Sprint sprint1 = sprintFactory.createSprint("Project_2022_1", "Project_2022_1_Sprint 1", "Sprint Name 0");
//        sprintStore1.saveSprint(sprint1);
//        //Act
//        Sprint sprint2 = sprintFactory.createSprint("Project_2022_1", "Project_2022_1_Sprint 2", "Sprint Name 0");
//        sprintStore1.saveSprint(sprint2);
//        //Assert
//        assertNotEquals(sprint1, sprint2);
//
//    }
//
//    @Test
//    @DisplayName("Test to search sprint by id")
//    public void catchSprintByID() {
//        //Arrange
//        SprintID sprintID = new SprintID("Project_2022_1_Sprint 1");
//        String x = sprintID.toString();
//        //Assert
//        assertEquals("Project_2022_1_Sprint 1", x);
//    }
//
//    @Test
//    @DisplayName("Test to search sprint by id - Test1")
//    public void catchSprintByID_1() {
//        //Arrange
//        Sprint sprint = new Sprint("Project_2022_1", "Project_2022_1_Sprint 1", "Sprint Name");
//        SprintID x = sprint.getSprintID();
//        //Assert
//        assertTrue(x.equals(x));
//    }
//
//    @Test
//    @DisplayName("Test to verify if sprint already exists")
//    public void validateSprintExists() {
//
//        //Arrange
//        SprintStore sprintListTest = new SprintStore();
//        SprintFactory sprintFactory = new SprintFactory();
//        Sprint sprintTest = sprintFactory.createSprint("Project_2022_1", "Project_2022_1_Sprint 1",
//                "Sprint Name");
//        sprintListTest.saveSprint(sprintTest);
//        //Assert
//        assertTrue(sprintListTest.validateIfSprintAlreadyExists(sprintTest));
//    }
//
//    @Test
//    @DisplayName("Test to verify if the sprint was saved")
//    public void checkIfSprintWasCreatedAndSaved() {
//
//        //Arrange
//        SprintStore sprintListTest = new SprintStore();
//        SprintFactory sprintFactory = new SprintFactory();
//        Sprint x = sprintFactory.createSprint("Project_2022_1", "Project_2022_1_Sprint 1", "Sprint Name");
//        sprintListTest.saveSprint(x);
//        SprintDuration sprintDuration = new SprintDuration(14);
//        project.setSprintDuration(sprintDuration);
//        x.setStartDate(LocalDate.of(2022, 1, 1));
//        x.setEndDate(LocalDate.of(2022, 1, 15));
//        //Assert
//        assertTrue(x.isCurrentSprint());
//    }
//
//    @Test
//    @DisplayName("Get the next start Sprint")
//    public void getCurrentSprintList() {
//        //Arrange
//        SprintStore storeTest = new SprintStore();
//        SprintFactory sprintFactory = new SprintFactory();
//        Sprint x = sprintFactory.createSprint("Project_2022_1", "Project_2022_1_Sprint 1", "Sprint Name");
//        storeTest.saveSprint(x);
//        Sprint y = sprintFactory.createSprint("Project_2022_1", "Project_2022_1_Sprint 2", "Sprint Name 1");
//        storeTest.saveSprint(y);
//        //Assert
//        assertEquals(2, storeTest.findSprints().size());
//    }
//
//    @Test
//    @DisplayName("Get NullPointerException for the next Sprint")
//    public void getNullCurrentSprintTest() {
//        //Assert
//        assertThrows(NullPointerException.class, () -> {
//            //Arrange
//            SprintStore storeTest = new SprintStore();
//            Sprint x = new Sprint("Project_2022_1", "Project_2022_1_Sprint 1", "Sprint Name");
//            x.setStartDate(LocalDate.of(2022, 5, 1));
//            //Act
//            storeTest.findCurrentSprint();
//        });
//    }
//
//    @Test
//    @DisplayName("Verification Test, to Start a Sprint")
//    public void startASprintSuccess() {
//        //Arrange
//        Company company = new Company();
//        Typology typo = company.getTypologyRepository().findTypologyByDescription("Fixed Cost");
//        Customer customer = company.getCustomerStore().getCustomerByName("isep");
//        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
//        //Project 1
//        Project proj1 = company.getProjectStore().createAndSaveProject("prototype1", "proj1Prototype", customer,
//                typo, sector, LocalDate.of(2022, 1, 1), 2, 3000);
//        proj1.setEndDate(LocalDate.of(2022, 12, 31));
//        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
//        //Resource 1
//        SystemUser joana1 = new SystemUser("joanaum", "joana1@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
//        LocalDate startDatej1 = LocalDate.of(2022, 1, 1);
//        LocalDate endDatej1 = LocalDate.of(2022, 1, 30);
//        Resource joana1R = proj1.createResource(joana1, startDatej1, endDatej1, 100, .5);
//        joana1R.setRole(company.getProjectRoleStore().getProjectRole("Scrum Master"));
//        proj1.getProjectTeam().saveResource(joana1R);
//        //Resource 2
//        SystemUser joana2 = new SystemUser("joanadois", "joana2@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
//        LocalDate startDatej2 = LocalDate.of(2022, 1, 1);
//        LocalDate endDatej2 = LocalDate.of(2022, 1, 30);
//        Resource joana2R = proj1.createResource(joana2, startDatej2, endDatej2, 100, 1);
//        joana2R.setRole(company.getProjectRoleStore().getProjectRole("Product Owner"));
//        proj1.getProjectTeam().saveResource(joana2R);
//        //Resource 3
//        SystemUser joana3 = new SystemUser("joanatres", "joana3@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
//        LocalDate startDatej3 = LocalDate.of(2022, 1, 1);
//        LocalDate endDatej3 = LocalDate.of(2022, 12, 31);
//        Resource joana3R = proj1.createResource(joana3, startDatej3, endDatej3, 100, .5);
//        joana3R.setRole(company.getProjectRoleStore().getProjectRole("Project Manager"));
//        proj1.getProjectTeam().saveResource(joana3R);
//        //Resource 4
//        SystemUser joana4 = new SystemUser("joanaquatro", "joana4@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
//        LocalDate startDatej4 = LocalDate.of(2022, 1, 1);
//        LocalDate endDatej4 = LocalDate.of(2022, 12, 31);
//        Resource joana4R = proj1.createResource(joana4, startDatej4, endDatej4, 100, .3333);
//        joana4R.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
//        proj1.getProjectTeam().saveResource(joana4R);
//        //Create a Sprint
//        SprintStore sprintListTest1 = new SprintStore();
//        SprintFactory sprintFactory = new SprintFactory();
//        Sprint sprint1 = sprintFactory.createSprint("Project_2022_1", "Project_2022_1_Sprint 1", "Sprint Name");
//        sprintListTest1.saveSprint(sprint1);
//        String sprintID = new SprintID("Project_2022_1_Sprint 1").toString();
//        sprint1.setStartDate(LocalDate.of(2022, 1, 1));
//        LocalDate date = LocalDate.of(2022, 1, 1);
//        //Assert
//        assertTrue(sprintListTest1.startASprint(sprintID, date, proj1.getProjectTeam(), 2));
//    }
//
//    @Test
//    @DisplayName("Fail Test, to Start a Sprint - Without project team")
//    public void startASprintFail_ProjectTeam() {
//        //Arrange
//        Company company = new Company();
//        Typology typo = company.getTypologyRepository().findTypologyByDescription("Fixed Cost");
//        Customer customer = company.getCustomerStore().getCustomerByName("isep");
//        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
//
//        //Project 1
//        Project proj1 = company.getProjectStore().createAndSaveProject("prototype1", "proj1Prototype", customer,
//                typo, sector, LocalDate.of(2022, 1, 1), 2, 3000);
//        proj1.setEndDate(LocalDate.of(2022, 12, 31));
//        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
//
//        //Resource 1
//        SystemUser joana1 = new SystemUser("joanaum", "joana1@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
//        LocalDate startDatej1 = LocalDate.of(2022, 1, 1);
//        LocalDate endDatej1 = LocalDate.of(2022, 1, 30);
//        Resource joana1R = proj1.createResource(joana1, startDatej1, endDatej1, 100, .5);
//        joana1R.setRole(company.getProjectRoleStore().getProjectRole("Scrum Master"));
//        proj1.getProjectTeam().saveResource(joana1R);
//
//        //Resource 2
//        SystemUser joana2 = new SystemUser("joanadois", "joana2@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
//        LocalDate startDatej2 = LocalDate.of(2022, 1, 1);
//        LocalDate endDatej2 = LocalDate.of(2022, 1, 30);
//        Resource joana2R = proj1.createResource(joana2, startDatej2, endDatej2, 100, 1);
//        joana2R.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
//        proj1.getProjectTeam().saveResource(joana2R);
//
//        //Resource 3
//        SystemUser joana3 = new SystemUser("joanatres", "joana3@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
//        LocalDate startDatej3 = LocalDate.of(2022, 1, 1);
//        LocalDate endDatej3 = LocalDate.of(2022, 12, 31);
//        Resource joana3R = proj1.createResource(joana3, startDatej3, endDatej3, 100, .5);
//        joana3R.setRole(company.getProjectRoleStore().getProjectRole("Project Manager"));
//        proj1.getProjectTeam().saveResource(joana3R);
//
//        //Resource 4
//        SystemUser joana4 = new SystemUser("joanaquatro", "joana4@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
//        LocalDate startDatej4 = LocalDate.of(2022, 1, 1);
//        LocalDate endDatej4 = LocalDate.of(2022, 12, 31);
//        Resource joana4R = proj1.createResource(joana4, startDatej4, endDatej4, 100, .3333);
//        joana4R.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
//        proj1.getProjectTeam().saveResource(joana4R);
//
//        //Create a Sprint
//        SprintStore sprintListTest1 = new SprintStore();
//        SprintFactory sprintFactory = new SprintFactory();
//        Sprint sprint1 = sprintFactory.createSprint("Project_2022_1", "Project_2022_1_Sprint 1",
//                "Sprint Name");
//        sprintListTest1.saveSprint(sprint1);
//        String sprintID = new SprintID("Project_2022_1_Sprint 1").toString();
//
//        //Assert
//        assertFalse(sprintListTest1.startASprint(sprintID, LocalDate.of(2022, 2, 5),
//                proj1.getProjectTeam(), 2));
//    }
//
//    @Test
//    @DisplayName("Fail Test, to Start a Sprint - Wrong Sprint ID")
//    public void startASprintFail_SprintID() {
//        //Arrange
//        Company company = new Company();
//        Typology typo = company.getTypologyRepository().findTypologyByDescription("Fixed Cost");
//        Customer customer = company.getCustomerStore().getCustomerByName("isep");
//        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
//
//        //Project 1
//        Project proj1 = company.getProjectStore().createAndSaveProject("prototype1", "proj1Prototype", customer,
//                typo, sector, LocalDate.of(2022, 1, 1), 2, 3000);
//        proj1.setEndDate(LocalDate.of(2022, 12, 31));
//        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
//
//        //Resource 1
//        SystemUser joana1 = new SystemUser("joanaum", "joana1@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
//        LocalDate startDatej1 = LocalDate.of(2022, 1, 1);
//        LocalDate endDatej1 = LocalDate.of(2022, 1, 30);
//        Resource joana1R = proj1.createResource(joana1, startDatej1, endDatej1, 100, .5);
//        joana1R.setRole(company.getProjectRoleStore().getProjectRole("Scrum Master"));
//        proj1.getProjectTeam().saveResource(joana1R);
//
//        //Resource 2
//        SystemUser joana2 = new SystemUser("joanadois", "joana2@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
//        LocalDate startDatej2 = LocalDate.of(2022, 1, 1);
//        LocalDate endDatej2 = LocalDate.of(2022, 1, 30);
//        Resource joana2R = proj1.createResource(joana2, startDatej2, endDatej2, 100, 1);
//        joana2R.setRole(company.getProjectRoleStore().getProjectRole("Product Owner"));
//        proj1.getProjectTeam().saveResource(joana2R);
//
//        //Resource 3
//        SystemUser joana3 = new SystemUser("joanatres", "joana3@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
//        LocalDate startDatej3 = LocalDate.of(2022, 1, 1);
//        LocalDate endDatej3 = LocalDate.of(2022, 12, 31);
//        Resource joana3R = proj1.createResource(joana3, startDatej3, endDatej3, 100, .5);
//        joana3R.setRole(company.getProjectRoleStore().getProjectRole("Project Manager"));
//        proj1.getProjectTeam().saveResource(joana3R);
//
//        //Resource 4
//        SystemUser joana4 = new SystemUser("joanaquatro", "joana4@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
//        LocalDate startDatej4 = LocalDate.of(2022, 1, 1);
//        LocalDate endDatej4 = LocalDate.of(2022, 12, 31);
//        Resource joana4R = proj1.createResource(joana4, startDatej4, endDatej4, 100, .3333);
//        joana4R.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
//        proj1.getProjectTeam().saveResource(joana4R);
//
//        //Create a Sprint
//        SprintStore sprintListTest1 = new SprintStore();
//        SprintFactory sprintFactory = new SprintFactory();
//        Sprint sprint1 = sprintFactory.createSprint("Project_2022_1", "Project_2022_1_Sprint 1",
//                "Sprint Name");
//        sprintListTest1.saveSprint(sprint1);
//        String sprintID = new SprintID("Project_2022_1_Sprint 13").toString();
//
//        //Assert
//        assertFalse(sprintListTest1.startASprint(sprintID, LocalDate.of(2022, 2, 1),
//                proj1.getProjectTeam(), 2));
//    }
//
//    @Test
//    @DisplayName("Fail Test, to Start a Sprint - Wrong Start Date")
//    public void startASprintFail_StartDate() {
//        //Arrange
//        Company company = new Company();
//        Typology typo = company.getTypologyRepository().findTypologyByDescription("Fixed Cost");
//        Customer customer = company.getCustomerStore().getCustomerByName("isep");
//        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
//
//        //Project 1
//        Project proj1 = company.getProjectStore().createAndSaveProject("prototype1", "proj1Prototype", customer,
//                typo, sector, LocalDate.of(2022, 1, 1), 2, 3000);
//        proj1.setEndDate(LocalDate.of(2022, 12, 31));
//        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
//
//        //Resource 1
//        SystemUser joana1 = new SystemUser("joanaum", "joana1@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
//        LocalDate startDatej1 = LocalDate.of(2022, 1, 1);
//        LocalDate endDatej1 = LocalDate.of(2022, 1, 30);
//        Resource joana1R = proj1.createResource(joana1, startDatej1, endDatej1, 100, .5);
//        joana1R.setRole(company.getProjectRoleStore().getProjectRole("Scrum Master"));
//        proj1.getProjectTeam().saveResource(joana1R);
//
//        //Resource 2
//        SystemUser joana2 = new SystemUser("joanadois", "joana2@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
//        LocalDate startDatej2 = LocalDate.of(2022, 1, 1);
//        LocalDate endDatej2 = LocalDate.of(2022, 1, 30);
//        Resource joana2R = proj1.createResource(joana2, startDatej2, endDatej2, 100, 1);
//        joana2R.setRole(company.getProjectRoleStore().getProjectRole("Product Owner"));
//        proj1.getProjectTeam().saveResource(joana2R);
//
//        //Resource 3
//        SystemUser joana3 = new SystemUser("joanatres", "joana3@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
//        LocalDate startDatej3 = LocalDate.of(2022, 1, 1);
//        LocalDate endDatej3 = LocalDate.of(2022, 12, 31);
//        Resource joana3R = proj1.createResource(joana3, startDatej3, endDatej3, 100, .5);
//        joana3R.setRole(company.getProjectRoleStore().getProjectRole("Project Manager"));
//        proj1.getProjectTeam().saveResource(joana3R);
//
//        //Resource 4
//        SystemUser joana4 = new SystemUser("joanaquatro", "joana4@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
//        LocalDate startDatej4 = LocalDate.of(2022, 1, 1);
//        LocalDate endDatej4 = LocalDate.of(2022, 12, 31);
//        Resource joana4R = proj1.createResource(joana4, startDatej4, endDatej4, 100, .3333);
//        joana4R.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
//        proj1.getProjectTeam().saveResource(joana4R);
//
//        //Create a Sprint
//        SprintStore sprintListTest1 = new SprintStore();
//        SprintFactory sprintFactory = new SprintFactory();
//        Sprint sprint1 = sprintFactory.createSprint("Project_2022_1", "Project_2022_1_Sprint 1",
//                "Sprint Name");
//        sprintListTest1.saveSprint(sprint1);
//        String sprintID = new SprintID("Project_2022_1_Sprint 1").toString();
//
//        //Assert
//        assertFalse(sprintListTest1.startASprint(sprintID, LocalDate.of(2021, 12, 31),
//                proj1.getProjectTeam(), 2));
//    }
//
//    @Test
//    @DisplayName("Fail Global Test, to Start a Sprint - Without project team, with wrong id sprint and wrong startDate")
//    public void startASprintFail() {
//        //Arrange
//        Company company = new Company();
//        Typology typo = company.getTypologyRepository().findTypologyByDescription("Fixed Cost");
//        Customer customer = company.getCustomerStore().getCustomerByName("isep");
//        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
//
//        //Project 1
//        Project proj1 = company.getProjectStore().createAndSaveProject("prototype1", "proj1Prototype", customer,
//                typo, sector, LocalDate.of(2022, 1, 1), 2, 3000);
//        proj1.setEndDate(LocalDate.of(2022, 12, 31));
//        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
//
//        //Resource 1
//        SystemUser joana1 = new SystemUser("joanaum", "joana1@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
//        LocalDate startDatej1 = LocalDate.of(2022, 1, 1);
//        LocalDate endDatej1 = LocalDate.of(2022, 1, 30);
//        Resource joana1R = proj1.createResource(joana1, startDatej1, endDatej1, 100, .5);
//        joana1R.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
//        proj1.getProjectTeam().saveResource(joana1R);
//
//        //Resource 2
//        SystemUser joana2 = new SystemUser("joanadois", "joana2@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
//        LocalDate startDatej2 = LocalDate.of(2022, 1, 1);
//        LocalDate endDatej2 = LocalDate.of(2022, 1, 30);
//        Resource joana2R = proj1.createResource(joana2, startDatej2, endDatej2, 100, 1);
//        joana2R.setRole(company.getProjectRoleStore().getProjectRole("Project Manager"));
//        proj1.getProjectTeam().saveResource(joana2R);
//
//        //Resource 3
//        SystemUser joana3 = new SystemUser("joanatres", "joana3@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
//        LocalDate startDatej3 = LocalDate.of(2022, 1, 1);
//        LocalDate endDatej3 = LocalDate.of(2022, 12, 31);
//        Resource joana3R = proj1.createResource(joana3, startDatej3, endDatej3, 100, .5);
//        joana3R.setRole(company.getProjectRoleStore().getProjectRole("Project Manager"));
//        proj1.getProjectTeam().saveResource(joana3R);
//
//        //Resource 4
//        SystemUser joana4 = new SystemUser("joanaquatro", "joana4@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
//        LocalDate startDatej4 = LocalDate.of(2022, 1, 1);
//        LocalDate endDatej4 = LocalDate.of(2022, 12, 31);
//        Resource joana4R = proj1.createResource(joana4, startDatej4, endDatej4, 100, .3333);
//        joana4R.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
//        proj1.getProjectTeam().saveResource(joana4R);
//
//        //Create a Sprint
//        SprintStore sprintListTest1 = new SprintStore();
//        SprintFactory sprintFactory = new SprintFactory();
//        Sprint x = sprintFactory.createSprint("Project_2022_1", "Project_2022_1_Sprint 1", "Sprint Name");
//        sprintListTest1.saveSprint(x);
//        String sprintID = new SprintID("Project_2022_1_Sprint 14").toString();
//
//        //Assert
//        assertFalse(sprintListTest1.startASprint(sprintID, LocalDate.of(2022, 2, 1),
//                proj1.getProjectTeam(), 2));
//    }
//
//    @Test
//    @DisplayName("Validate start date")
//    public void validateStartDate() {
//        //Arrange
//        LocalDate startDatej4 = LocalDate.now();
//
//        SprintStore sprintListTest = new SprintStore();
//        SprintFactory sprintFactory = new SprintFactory();
//        Sprint sprintTest = sprintFactory.createSprint("Project_2022_1", "Project_2022_1_Sprint 1", "Sprint Name");
//        sprintListTest.saveSprint(sprintTest);
//        sprintTest.setStartDate(LocalDate.now());
//        Sprint sprintTest2 = sprintFactory.createSprint("Project_2022_1", "Project_2022_1_Sprint 1","Sprint Name");
//        sprintListTest.saveSprint(sprintTest2);
//        //Assert
//        assertEquals(startDatej4, sprintTest.getStartDate());
//    }
//
//
//    @Test
//    @DisplayName("Test to search all sprint by project id")
//    public void catchAllSprintsByProjectID() {
//        //Arrange
//        Customer costumer = new Customer("Customer_1", "customer1@email.com", 123456789);
//        Typology typology = new Typology("Typology_1");
//        BusinessSector businessSector = new BusinessSector("sector_1");
//        ProjectID projectCode = new ProjectID(1);
//        Project project = new Project("Project_1", "Project_named_1", costumer, typology,
//                businessSector, LocalDate.of(2022, 1, 1),
//                10, 30000);
//        project.setEndDate(LocalDate.of(2022, 12, 31));
//        ProjectStore projectStore = new ProjectStore();
//        SprintStore sprintStore = new SprintStore();
//        SprintFactory sprintFactory = new SprintFactory();
//        Sprint sprint = sprintFactory.createSprint("Project_2022_1", "Project_2022_1_Sprint 0",
//                "Sprint Name 0");
//        sprintStore.saveSprint(sprint);
//        Sprint sprint1 = sprintFactory.createSprint("Project_2022_1", "Project_2022_1_Sprint 1",
//                "Sprint Name 1");
//        sprintStore.saveSprint(sprint1);
//        Sprint sprint2 = sprintFactory.createSprint("Project_2022_1", "Project_2022_1_Sprint 2",
//                "Sprint Name 2");
//        sprintStore.saveSprint(sprint2);
//        //Assert
//        assertEquals(0, sprintStore.findAllSprintsByProjectID("Project_2022_1").size());
//
//    }
//
//    @Test
//    @DisplayName("Test to delete a Sprint - Test 2")
//    public void deleteSprint() {
//        //Arrange
//        Customer costumer = new Customer("Customer_1", "customer1@email.com", 123456789);
//        Typology typology = new Typology("Typology_1");
//        BusinessSector businessSector = new BusinessSector("sector_1");
//        Project project = new Project("Project_1", "Project_named_1", costumer, typology,
//                businessSector, LocalDate.of(2022, 1, 1),
//                10, 30000);
//        project.setEndDate(LocalDate.of(2022, 12, 31));
//        SprintStore sprintStore = new SprintStore();
//        SprintFactory sprintFactory = new SprintFactory();
//        Sprint sprint = sprintFactory.createSprint("Project_2022_1", "Project_2022_1_Sprint 0",
//                "Sprint Name 0");
//        sprintStore.saveSprint(sprint);
//        Sprint sprint1 = sprintFactory.createSprint("Project_2022_1", "Project_2022_1_Sprint 1",
//                "Sprint Name 1");
//        sprintStore.saveSprint(sprint1);
//        Sprint sprint2 = sprintFactory.createSprint("Project_2022_1", "Project_2022_1_Sprint 2",
//                "Sprint Name 2");
//        sprintStore.saveSprint(sprint2);
//        //Assert
//        assertTrue(sprintStore.deleteSprint(sprint2));
//    }
//
//    @Test
//    @DisplayName("Test to delete a Sprint - Test 1")
//    public void deleteSprint_SizeList() {
//        //Arrange
//        Customer costumer = new Customer("Customer_1", "customer1@email.com", 123456789);
//        Typology typology = new Typology("Typology_1");
//        BusinessSector businessSector = new BusinessSector("sector_1");
//        Project project = new Project("Project_1", "Project_named_1", costumer, typology,
//                businessSector, LocalDate.of(2022, 1, 1),
//                10, 30000);
//        project.setEndDate(LocalDate.of(2022, 12, 31));
//        SprintStore sprintStore = new SprintStore();
//        SprintFactory sprintFactory = new SprintFactory();
//        Sprint sprint = sprintFactory.createSprint("Project_2022_1", "Project_2022_1_Sprint 0",
//                "Sprint Name 0");
//        sprintStore.saveSprint(sprint);
//        Sprint sprint1 = sprintFactory.createSprint("Project_2022_1", "Project_2022_1_Sprint 1",
//                "Sprint Name 1");
//        sprintStore.saveSprint(sprint1);
//        Sprint sprint2 = sprintFactory.createSprint("Project_2022_1", "Project_2022_1_Sprint 2",
//                "Sprint Name 2");
//        sprintStore.saveSprint(sprint2);
//        sprintStore.deleteSprint(sprint2);
//        //Assert
//        assertEquals(2,sprintStore.getSprints().size());
//    }
//
//}
//
